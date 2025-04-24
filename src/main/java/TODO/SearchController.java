package TODO;

import com.google.gson.JsonArray;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.ArrayList;

public class SearchController {
    private DatabaseManager dm = null;

    public SearchController(DatabaseManager dm){
        this.dm = dm;
    }

    public void registerRoutes(Javalin app) {
        app.get("/search", this::handleSearch);
    }

    private void handleSearch(Context con){
        String query = con.queryParam("query");
        JsonArray res = dm.search(query);
        String[] test = new String[res.size()];
        for (int i = 0; i < res.size(); i++){
            test[i] = res.get(i).getAsJsonObject().get("name").getAsString();
        }
        con.json(test);
    }

}
