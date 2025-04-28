package TODO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.json.JsonMapper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

public class ScheduleController {
    private DatabaseManager dm = null;

    public ScheduleController(DatabaseManager dm){
        this.dm = dm;
    }

    public void registerRoutes(Javalin app) {
        app.get("/schedule", this::getAllSchedules);
        app.post("/schedule", this::addSchedule);
        app.delete("/schedule", this::deleteSchedule);
        app.put("/schedule", this::updateSchedule);
    }

    private void getAllSchedules(Context con){
        String username = con.queryParam("username");
        JsonArray schedules = dm.getSchedules(username);
        for(JsonElement schedule: schedules){
            System.out.println(schedule.toString());
        }

        if (schedules == null || schedules.size() == 0) {
            con.status(404).result("No schedules found for the user.");
            if(schedules != null){
                System.out.println(schedules.size());
            }
            return;
        }
        ArrayList<Map<String, Object>> formattedSchedules = new ArrayList<>();
        for (JsonElement schedule : schedules) {
            // Extract schedule name
            String name = schedule.getAsJsonObject().get("name").getAsString();
            String semester = schedule.getAsJsonObject().get("semester").getAsString();

            // Extract classes
            ArrayList<Map<String, Object>> classes = new ArrayList<>();
            if (schedule.getAsJsonObject().has("classes")) {
                JsonArray classArray = schedule.getAsJsonObject().get("classes").getAsJsonArray();
                for (JsonElement classElement : classArray) {
                    Map<String, Object> classMap = new HashMap<>();
                    JsonObject classObj = classElement.getAsJsonObject();
                    for (Map.Entry<String, JsonElement> entry : classObj.entrySet()) {
                        JsonElement value = entry.getValue();
                        if (value.isJsonArray()) {
                            // Handle array values
                            JsonArray array = value.getAsJsonArray();
                            List<String> list = new ArrayList<>();
                            for (JsonElement element : array) {
                                list.add(element.toString());
                            }
                            classMap.put(entry.getKey(), list);
                        } else {
                            // Handle non-array values
                            classMap.put(entry.getKey(), value.getAsString());
                        }
                    }
                    classes.add(classMap);
                }
            }

            // Create a schedule map
            Map<String, Object> scheduleMap = new HashMap<>();
            scheduleMap.put("name", name);
            scheduleMap.put("semester", semester);
            scheduleMap.put("classes", classes);

            formattedSchedules.add(scheduleMap);
        }


        con.json(formattedSchedules);
    }

    private void addSchedule(Context con){
        String username = con.queryParam("username");
        String scheduleName = con.queryParam("name");
        String scheduleSem = con.queryParam("semester");
        Boolean addScheduleResult = dm.addScheduleToDatabase(username, scheduleName, scheduleSem);

        con.json(addScheduleResult);
    }

    private void deleteSchedule(Context con){
        String username = con.queryParam("username");
        String scheduleName = con.queryParam("name");
        Boolean deleteScheduleResult = dm.removeScheduleFromDatabase(username, scheduleName);

        con.json(deleteScheduleResult);
    }

    private void updateSchedule(Context con) {
        boolean successful = false;
        String username = con.queryParam("username");
        String scheduleName = con.queryParam("name");
        String body = con.body(); // Get body as JsonObject
        System.out.println(body);
        // convert "classes" from body to array of integers called classIds

        JsonObject jsonBody = new Gson().fromJson(body, JsonObject.class);
        JsonArray classesArray = jsonBody.getAsJsonArray("classes");
        int[] classIds = StreamSupport.stream(classesArray.spliterator(), false)
                .mapToInt(JsonElement::getAsInt)
                .toArray();

        if (username == null || scheduleName == null || classesArray == null) {
            con.status(400).result("Invalid input. Username, schedule name, and classes are required.");
            return;
        }

        try {
            // Remove all existing classes from the schedule
            JsonArray schedules = dm.getSchedules(username);
            JsonArray existingClasses = StreamSupport.stream(schedules.spliterator(), false)
                    .filter(schedule -> schedule.isJsonObject() && schedule.getAsJsonObject().get("name").getAsString().equals(scheduleName))
                    .findFirst()
                    .map(schedule -> schedule.getAsJsonObject().get("classes").getAsJsonArray())
                    .orElse(new JsonArray());

            for (JsonElement classElement : existingClasses) {
                int classID = classElement.getAsJsonObject().get("id").getAsInt();
                dm.removeClassFromSchedule(username, scheduleName, classID);
            }

            // Add new classes to the schedule
            for (JsonElement classElement : classesArray) {
                int classID = classElement.getAsInt();
                System.out.println(classID);
                dm.addClassToSchedule(username, scheduleName, classID);
            }

            successful = true;
            con.json(successful);
            System.out.println("Schedule updated successfully.");
        } catch (Exception e) {
            con.json(successful);
            System.out.println("An error occurred while updating the schedule: " + e.getMessage());
        }
    }

}
