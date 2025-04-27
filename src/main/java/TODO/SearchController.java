package TODO;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchController {
    private DatabaseManager dm = null;

    public SearchController(DatabaseManager dm){
        this.dm = dm;
    }

    public void registerRoutes(Javalin app) {
        app.get("/search", this::handleSearch);
    }

    private void handleSearch(Context con) throws NullPointerException{
        String query = con.queryParam("query");
        String subject = con.queryParam("subject");
        String dayTime = con.queryParam("days");
        String startTime = con.queryParam("startTime") + ":00";
        String endTime = con.queryParam("endTime") + ":00";
        JsonArray res = dm.search(query, subject, dayTime, startTime, endTime);

        if (res == null || res.size() == 0) {
            con.result("No classes found.");
            if(res != null){
                System.out.println(res.size());
            }
            return;
        }

        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        // Create a list to store the map
        // Iterate through the JsonArray

        for (int i = 0; i < res.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            JsonArray classTimes = res.get(i).getAsJsonObject().get("classTimes").getAsJsonArray();
            String days = "";
            String start_time = "";
            String end_time = "";
            if(classTimes.size() != 0) {
                 start_time = classTimes.get(0).getAsJsonObject().get("start_time").getAsString();
                 end_time = classTimes.get(0).getAsJsonObject().get("end_time").getAsString();
            } else {
                 start_time = "No Time Available";
                 end_time = "No Time Available";
            }

            map.put("id", res.get(i).getAsJsonObject().get("id").getAsInt());
            map.put("name", res.get(i).getAsJsonObject().get("name").getAsString());
            map.put("subject", res.get(i).getAsJsonObject().get("subject").getAsString());
            map.put("number", res.get(i).getAsJsonObject().get("number").getAsInt());
            map.put("section", res.get(i).getAsJsonObject().get("section").getAsString());
            map.put("semester", res.get(i).getAsJsonObject().get("semester").getAsString());


            for (JsonElement time : classTimes) {
                JsonObject timeObj = time.getAsJsonObject();
                if (timeObj.get("day") != null) {
                    days += timeObj.get("day").getAsString();
                }
            }

            map.put("days", days);
            map.put("startTime", start_time);
            map.put("endTime", end_time);
            maps.add(map);
        }


//        for (JsonElement entry : res) {
//
//            if (entry.getValue().isJsonObject()) {
//                map.put(entry.getKey(), entry.getValue().getAsJsonObject().toString());
//            } else if (entry.getValue().isJsonArray()) {
//                JsonArray timeArray = entry.getValue().getAsJsonArray();
//                String days = "";
//                String start_time = timeArray.get(0).getAsJsonObject().get("startTime").getAsString();
//                String end_time = timeArray.get(0).getAsJsonObject().get("endTime").getAsString();
//
//                for (JsonElement timeElement : timeArray) {
//                    JsonObject timeObj = timeElement.getAsJsonObject();
//                    days += timeObj.get("days").getAsString();
//                }
//
//                map.put("days", days);
//                map.put("startTime", start_time);
//                map.put("endTime", end_time);
//            } else {
//                map.put(entry.getKey(), entry.getValue().toString());
//            }
//        }

        con.json(maps);
    }

}
