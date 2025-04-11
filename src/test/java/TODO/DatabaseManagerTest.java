package TODO;

import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.sql.*;

import static TODO.Main.loadClassesFromJson;
import static org.junit.jupiter.api.Assertions.*;
public class DatabaseManagerTest{
    DatabaseManager dm = null;

    @BeforeEach
    void setUp() throws SQLException {
        dm = new DatabaseManager();
        assertNotNull(dm);

        dm.dropAllTables();
    }

    @AfterEach
    void tearDown() {
        dm.closeConnection();
    }

    @Test
    void dropTables() throws SQLException{
        assertFalse(dm.doesTableExist("classes"));
        assertFalse(dm.doesTableExist("classTimes"));
        assertFalse(dm.doesTableExist("faculty"));
        assertFalse(dm.doesTableExist("classesTaken"));
        assertFalse(dm.doesTableExist("accounts"));
        assertFalse(dm.doesTableExist("schedules"));
        assertFalse(dm.doesTableExist("scheduledClasses"));
        assertFalse(dm.doesTableExist("majorsAndMinors"));
    }

    @Test
    void createTables() throws SQLException{
        dm.createAllTables();
        assertTrue(dm.doesTableExist("classes"));
        assertTrue(dm.doesTableExist("classTimes"));
        assertTrue(dm.doesTableExist("faculty"));
        assertTrue(dm.doesTableExist("classesTaken"));
        assertTrue(dm.doesTableExist("accounts"));
        assertTrue(dm.doesTableExist("schedules"));
        assertTrue(dm.doesTableExist("scheduledClasses"));
        assertTrue(dm.doesTableExist("majorsAndMinors"));
    }

    @Test
    void addOneClass() throws SQLException, FileNotFoundException {
        //passes if the classes are loaded in without printing an exception
        dm.createAllTables();

        String json1 = "{\n" +
                "            \"credits\": 3,\n" +
                "            \"faculty\": [\n" +
                "                \"McFeaters, Michelle R.\"\n" +
                "            ],\n" +
                "            \"is_lab\": false,\n" +
                "            \"is_open\": true,\n" +
                "            \"location\": \"SHAL 314\",\n" +
                "            \"name\": \"COST ACCOUNTING\",\n" +
                "            \"number\": 303,\n" +
                "            \"open_seats\": 2,\n" +
                "            \"section\": \"A\",\n" +
                "            \"semester\": \"2023_Fall\",\n" +
                "            \"subject\": \"ACCT\",\n" +
                "            \"times\": [\n" +
                "                {\n" +
                "                    \"day\": \"T\",\n" +
                "                    \"end_time\": \"09:15:00\",\n" +
                "                    \"start_time\": \"08:00:00\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"day\": \"R\",\n" +
                "                    \"end_time\": \"09:15:00\",\n" +
                "                    \"start_time\": \"08:00:00\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"total_seats\": 30\n" +
                "        }";

        Class c = new Gson().fromJson(json1,Class.class);

        dm.addClassToDatabase(c, 0);
        dm.printClassInfo();
    }

    @Test
    void fillOutAccount() throws SQLException, FileNotFoundException {
        //passes if the classes are loaded in without printing an exception
        dm.createAllTables();

        String json1 = "{\n" +
                "            \"credits\": 3,\n" +
                "            \"faculty\": [\n" +
                "                \"McFeaters, Michelle R.\"\n" +
                "            ],\n" +
                "            \"is_lab\": false,\n" +
                "            \"is_open\": true,\n" +
                "            \"location\": \"SHAL 314\",\n" +
                "            \"name\": \"COST ACCOUNTING\",\n" +
                "            \"number\": 303,\n" +
                "            \"open_seats\": 2,\n" +
                "            \"section\": \"A\",\n" +
                "            \"semester\": \"2023_Fall\",\n" +
                "            \"subject\": \"ACCT\",\n" +
                "            \"times\": [\n" +
                "                {\n" +
                "                    \"day\": \"T\",\n" +
                "                    \"end_time\": \"09:15:00\",\n" +
                "                    \"start_time\": \"08:00:00\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"day\": \"R\",\n" +
                "                    \"end_time\": \"09:15:00\",\n" +
                "                    \"start_time\": \"08:00:00\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"total_seats\": 30\n" +
                "        }";
        Class c = new Gson().fromJson(json1,Class.class);
        dm.addClassToDatabase(c, 0);


        dm.addAccountToDatabase("karlyripper", "yolo12", "ripper@email.com");
        dm.addScheduleToDatabase("karlyripper", "my2025spring");
        dm.addClassToSchedule("karlyripper", "my2025spring", 0);
        dm.addMajorOrMinor("karlyripper", "Computer Science", true);
        dm.addMajorOrMinor("karlyripper", "Mobile Development", false);
        dm.addClassAsTaken("karlyripper", 0);
    }

    @Test
    void deleteInfoFromAccount() throws SQLException, FileNotFoundException {
        //passes if the classes are loaded in without printing an exception
        dm.createAllTables();

        String json1 = "{\n" +
                "            \"credits\": 3,\n" +
                "            \"faculty\": [\n" +
                "                \"McFeaters, Michelle R.\"\n" +
                "            ],\n" +
                "            \"is_lab\": false,\n" +
                "            \"is_open\": true,\n" +
                "            \"location\": \"SHAL 314\",\n" +
                "            \"name\": \"COST ACCOUNTING\",\n" +
                "            \"number\": 303,\n" +
                "            \"open_seats\": 2,\n" +
                "            \"section\": \"A\",\n" +
                "            \"semester\": \"2023_Fall\",\n" +
                "            \"subject\": \"ACCT\",\n" +
                "            \"times\": [\n" +
                "                {\n" +
                "                    \"day\": \"T\",\n" +
                "                    \"end_time\": \"09:15:00\",\n" +
                "                    \"start_time\": \"08:00:00\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"day\": \"R\",\n" +
                "                    \"end_time\": \"09:15:00\",\n" +
                "                    \"start_time\": \"08:00:00\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"total_seats\": 30\n" +
                "        }";
        Class c = new Gson().fromJson(json1,Class.class);
        dm.addClassToDatabase(c, 0);

        //remove entire account
        dm.addAccountToDatabase("karlyripper", "yolo12", "ripper@email.com");
        dm.addScheduleToDatabase("karlyripper", "my2025spring");
        dm.addClassToSchedule("karlyripper", "my2025spring", 0);
        dm.addMajorOrMinor("karlyripper", "Computer Science", true);
        dm.addMajorOrMinor("karlyripper", "Mobile Development", false);
        dm.addClassAsTaken("karlyripper", 0);

        dm.removeAccountFromDatabase("karlyripper", "yolo12", "ripper@email.com");

        //remove individual pieces of information associated with the account
        dm.addAccountToDatabase("karlyripper", "yolo12", "ripper@email.com");
        dm.addScheduleToDatabase("karlyripper", "my2025spring");
        dm.addScheduleToDatabase("karlyripper", "my2025fall");
        dm.addClassToSchedule("karlyripper", "my2025spring", 0);
        dm.addMajorOrMinor("karlyripper", "Computer Science", true);
        dm.addMajorOrMinor("karlyripper", "Mobile Development", false);
        dm.addClassAsTaken("karlyripper", 0);

        dm.removeMajorOrMinor("karlyripper", "Mobile Development", false);
        dm.removeClassAsTaken("karlyripper", 0);
        dm.removeClassFromSchedule("karlyripper", "my2025spring", 0);
        dm.removeScheduleFromDatabase("karlyripper", "my2025fall");
    }

    //comment this one out most of the time, cause it takes a while
    @Test
    void loadInAllClasses() throws SQLException, FileNotFoundException {
        //passes if the classes are loaded in without printing an exception
        dm.createAllTables();

        ArrayList<Class> classes = new ArrayList<>();
        loadClassesFromJson("src/main/java/TODO/data_wolfe.json", classes);

        for(int i = 0; i < classes.size(); i++) {
            dm.addClassToDatabase(classes.get(i), i);
        }
    }
}
