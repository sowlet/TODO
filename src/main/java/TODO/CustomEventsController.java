package TODO;

import com.google.gson.JsonArray;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class CustomEventsController {
    private DatabaseManager dm = null;

    public CustomEventsController(DatabaseManager dm){
        this.dm = dm;
    }

    public void registerRoutes(Javalin app) {
        app.get("/schedule-editor", this::addCustomEvent);
        app.delete("/schedule-editor", this::deleteCustomEvent);
    }

    private void addCustomEvent(Context con) {
        String username = con.queryParam("username");
        String scheduleName = con.queryParam("account");
        String eventName = con.queryParam("eventName");
        String day = con.queryParam("day");
        String startTime = con.queryParam("startTime");
        String endTime = con.queryParam("endTime");
        String location = con.queryParam("location");

        int CustomEventId = dm.addCustomEvent(username, scheduleName, eventName, day, startTime, endTime, location);

        con.json(CustomEventId);
    }

    private void deleteCustomEvent(Context con) {
        int id = Integer.parseInt(con.queryParam("id"));

        Boolean deleteCustomEventResult = dm.removeCustomEvent(id);

        con.json(deleteCustomEventResult);
    }
}
