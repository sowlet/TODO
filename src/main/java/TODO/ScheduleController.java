package TODO;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.json.JsonMapper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
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

            // Extract classes
            ArrayList<Map<String, Object>> classes = new ArrayList<>();
            if (schedule.getAsJsonObject().has("classes")) {
                JsonArray classArray = schedule.getAsJsonObject().get("classes").getAsJsonArray();
                for (JsonElement classElement : classArray) {
                    Map<String, Object> classMap = new HashMap<>();
                    JsonObject classObj = classElement.getAsJsonObject();
                    for (Map.Entry<String, JsonElement> entry : classObj.entrySet()) {
                        classMap.put(entry.getKey(), entry.getValue().getAsString());
                    }
                    classes.add(classMap);
                }
            }

            // Create a schedule map
            Map<String, Object> scheduleMap = new HashMap<>();
            scheduleMap.put("name", name);
            scheduleMap.put("classes", classes);

            formattedSchedules.add(scheduleMap);
        }


        con.json(formattedSchedules);
    }

    private void addSchedule(Context con){
        String username = con.queryParam("username");
        String scheduleName = con.queryParam("name");
        Boolean addScheduleResult = dm.addScheduleToDatabase(username, scheduleName);

        con.json(addScheduleResult);
    }

    private void deleteSchedule(Context con){
        String username = con.queryParam("username");
        String scheduleName = con.queryParam("name");
        Boolean deleteScheduleResult = dm.removeScheduleFromDatabase(username, scheduleName);

        con.json(deleteScheduleResult);
    }

    private void updateSchedule(Context con) {
        String username = con.queryParam("username");
        String scheduleName = con.queryParam("name");
        int[] classes = con.bodyAsClass(int[].class); // Assuming the array of class IDs is sent in the request body

        if (username == null || scheduleName == null || classes == null) {
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
            for (int classID : classes) {
                dm.addClassToSchedule(username, scheduleName, classID);
            }

            con.status(200).result("Schedule updated successfully.");
        } catch (Exception e) {
            con.status(500).result("An error occurred while updating the schedule: " + e.getMessage());
        }
    }

}
