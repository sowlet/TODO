package TODO;

import com.google.gson.JsonArray;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.json.JsonMapper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

public class ScheduleController {
    private DatabaseManager dm = null;

    public ScheduleController(DatabaseManager dm){
        this.dm = dm;
    }

    public void registerRoutes(Javalin app) {
        app.get("/schedule", this::getAllSchedules);
        app.post("/schedule", this::addSchedule);
        app.delete("/schedule", this::deleteSchedule);
    }

    private void getAllSchedules(Context con){
        String username = con.queryParam("username");
        JsonArray schedules = dm.getSchedules(username);

        con.json(schedules);
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


}
