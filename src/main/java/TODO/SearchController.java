package TODO;

import com.google.gson.JsonArray;
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

        ArrayList<Map<String, Object>> resultList = new ArrayList<>();
        for (int i = 0; i < res.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            res.get(i).getAsJsonObject().entrySet().forEach(entry -> {
                if (entry.getValue().isJsonArray()) {
                    // Convert JsonArray to a Map
                    Map<String, Object> nestedMap = new HashMap<>();
                    JsonArray jsonArray = entry.getValue().getAsJsonArray();
                    for (int j = 0; j < jsonArray.size(); j++) {
                        nestedMap.put(String.valueOf(j), jsonArray.get(j).getAsString());
                    }
                    map.put(entry.getKey(), nestedMap);
                } else {
                    map.put(entry.getKey(), entry.getValue().getAsString());
                }
            });
            resultList.add(map);
        }

        con.json(resultList);
    }

}
