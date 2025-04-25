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
        JsonArray res = dm.search(query);

        if (res == null || res.size() == 0) {
            con.result("No classes found.");
            if(res != null){
                System.out.println(res.size());
            }
            return;
        }

        // Create a list to store the maps
        Map<String, Object> map = new HashMap<>();
        // Iterate through the JsonArray

        for (int i = 0; i < res.size(); i++) {
            JsonArray classTimes = res.get(i).getAsJsonObject().get("classTimes").getAsJsonArray();
            String days = "";
            String start_time = classTimes.get(0).getAsJsonObject().get("startTime").getAsString();
            String end_time = classTimes.get(0).getAsJsonObject().get("endTime").getAsString();

            map.put("id", res.get(i).getAsJsonObject().get("id").getAsInt());
            map.put("name", res.get(i).getAsJsonObject().get("name").getAsString());
            map.put("subject", res.get(i).getAsJsonObject().get("subject").getAsString());
            map.put("number", res.get(i).getAsJsonObject().get("number").getAsInt());
            map.put("section", res.get(i).getAsJsonObject().get("section").getAsString());
            map.put("semester", res.get(i).getAsJsonObject().get("semester").getAsString());


            for (JsonElement time : classTimes) {
                JsonObject timeObj = time.getAsJsonObject();
                days += timeObj.get("days").getAsString();
            }

            map.put("days", days);
            map.put("startTime", start_time);
            map.put("endTime", end_time);
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

        con.json(map);
    }

}
