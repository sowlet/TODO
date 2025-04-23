package TODO;

import com.google.gson.JsonArray;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.sql.ResultSet;

public class AccountController {
    private DatabaseManager dm = null;

    public AccountController(DatabaseManager dm){
        this.dm = dm;
    }

    public void registerRoutes(Javalin app) {
        app.get("/sign-in", this::validateLogin);
        app.post("/sign-in", this::signUp);
        app.get("/profile", this::getMajorAndMinor);
        app.post("/profile", this::updateMajorAndMinor);


        app.get("/schedule", this::getAllSchedules);
        app.post("/schedule", this::addSchedule);
        app.delete("/schedule", this::deleteSchedule);


    }

    private void validateLogin(Context con){
        String username = con.queryParam("username");
        String password = con.queryParam("password");
        String userResult = dm.validAccount(username, password);

        con.json(userResult);
    }

    private void signUp(Context con){
        String username = con.queryParam("username");
        String password = con.queryParam("password");
        String email = con.queryParam("email");
        Boolean addAccountResult = dm.addAccountToDatabase(username, password, email);

        con.json(addAccountResult);
    }

    private void getAllSchedules(Context con){
        String username = con.queryParam("username");
        ResultSet addScheduleResult = dm.getSchedules(username);

        con.json(addScheduleResult);
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

    private void updateMajorAndMinor(Context con){
        String username = con.queryParam("username");
        String majors = con.queryParam("major");
        String minors = con.queryParam("minor");
        Boolean addMResult = dm.addMajorMinor(username, majors, minors);

        con.json(addMResult);
    }

    private void getMajorAndMinor(Context con){
        String username = con.queryParam("username");
        String[] getMResult= dm.getMajorMinor(username);

        con.json(getMResult);
    }



}
