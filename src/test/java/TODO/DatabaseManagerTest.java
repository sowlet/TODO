package TODO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
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

    @Test
    void testSearch(){
        dm.createAllTables();
        ArrayList<Class> sampleClasses = new ArrayList<>();

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
        Class c1 = new Gson().fromJson(json1,Class.class);
        dm.addClassToDatabase(c1, 0);
        sampleClasses.add(c1);

        String json2 = "{\n" +
                "            \"credits\": 3,\n" +
                "            \"faculty\": [\n" +
                "                \"Zhang, David .H\"\n" +
                "            ],\n" +
                "            \"is_lab\": false,\n" +
                "            \"is_open\": true,\n" +
                "            \"location\": \"STEM 376\",\n" +
                "            \"name\": \"OPERATING SYSTEMS\",\n" +
                "            \"number\": 340,\n" +
                "            \"open_seats\": 14,\n" +
                "            \"section\": \"A\",\n" +
                "            \"semester\": \"2024_Spring\",\n" +
                "            \"subject\": \"COMP\",\n" +
                "            \"times\": [\n" +
                "                {\n" +
                "                    \"day\": \"T\",\n" +
                "                    \"end_time\": \"14:15:00\",\n" +
                "                    \"start_time\": \"13:00:00\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"day\": \"R\",\n" +
                "                    \"end_time\": \"14:15:00\",\n" +
                "                    \"start_time\": \"13:00:00\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"total_seats\": 30\n" +
                "        }";
        Class c2 = new Gson().fromJson(json2,Class.class);
        dm.addClassToDatabase(c2, 1);
        sampleClasses.add(c2);

        Search s = new Search();
        ArrayList<Class> results = s.search("OPERATING SYSTEMS",sampleClasses);

        JsonArray res = dm.search("OPERATING SYSTEMS");

        assertEquals(1, results.size());
        assertEquals("OPERATING SYSTEMS", results.get(0).getName());

        assertEquals(1, res.size());
        assertEquals("OPERATING SYSTEMS", res.get(0).getAsJsonObject().get("name").getAsString());

        String json3 = "{\n" +
                "            \"credits\": 3,\n" +
                "            \"faculty\": [\n" +
                "                \"Zhang, David .H\"\n" +
                "            ],\n" +
                "            \"is_lab\": false,\n" +
                "            \"is_open\": true,\n" +
                "            \"location\": \"STEM 376\",\n" +
                "            \"name\": \"OPERATING SYSTEMS\",\n" +
                "            \"number\": 340,\n" +
                "            \"open_seats\": 14,\n" +
                "            \"section\": \"B\",\n" +
                "            \"semester\": \"2024_Spring\",\n" +
                "            \"subject\": \"COMP\",\n" +
                "            \"times\": [\n" +
                "                {\n" +
                "                    \"day\": \"T\",\n" +
                "                    \"end_time\": \"10:15:00\",\n" +
                "                    \"start_time\": \"09:00:00\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"day\": \"R\",\n" +
                "                    \"end_time\": \"10:15:00\",\n" +
                "                    \"start_time\": \"09:00:00\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"total_seats\": 30\n" +
                "        }";
        Class c3 = new Gson().fromJson(json3,Class.class);
        dm.addClassToDatabase(c3, 2);
        sampleClasses.add(c3);

        s = new Search();
        results = s.search("OPERATING SYSTEMS",sampleClasses);

        res = dm.search("OPERATING SYSTEMS");

        assertEquals(2, results.size());
        assertEquals("OPERATING SYSTEMS", results.get(0).getName());
        assertEquals("OPERATING SYSTEMS", results.get(1).getName());

        assertEquals(2, res.size());
        assertEquals("OPERATING SYSTEMS", res.get(0).getAsJsonObject().get("name").getAsString());
        assertEquals("OPERATING SYSTEMS", res.get(1).getAsJsonObject().get("name").getAsString());
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

    //Test that a custom event can be added to a schedule
    @Test
    void addCustomEventToSchedule() throws SQLException, FileNotFoundException {
        dm.createAllTables();

        dm.addScheduleToDatabase("karlyripper", "my2025spring");
        dm.addCustomEvent("karlyripper", "my2025spring", "Scrum", "HBL 132", "W", "9:00:00", "9:15:00");

        String queryCustomEvent = "SELECT * FROM customEvents WHERE username = ? AND scheduleName = ? AND eventName = ?";
        String queryCustomEventTime = "SELECT * FROM customEventsTimes WHERE id = ?";

        int eventID = 0;
        try(PreparedStatement prepEvent = dm.db.prepareStatement(queryCustomEvent)) {
            prepEvent.setString(1, "karlyripper");
            prepEvent.setString(2, "my2025spring");
            prepEvent.setString(3, "Scrum");
            ResultSet rs = prepEvent.executeQuery();

            assertEquals("karlyripper", rs.getString("username"));
            assertEquals("my2025spring", rs.getString("scheduleName"));
            assertEquals("Scrum", rs.getString("eventName"));
            eventID = rs.getInt("id");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try(PreparedStatement prepEventTime = dm.db.prepareStatement(queryCustomEventTime)) {
            prepEventTime.setInt(1, eventID);
            ResultSet rsTime = prepEventTime.executeQuery();
            assertEquals("W", rsTime.getString("day"));
            assertEquals("9:00:00", rsTime.getString("start_time"));
            assertEquals("9:15:00", rsTime.getString("end_time"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void removeCustomEvent() throws SQLException, FileNotFoundException {
        dm.createAllTables();

        dm.addScheduleToDatabase("karlyripper", "my2025spring");
        dm.addCustomEvent("karlyripper", "my2025spring", "Scrum", "HBL 132", "W", "9:00:00", "9:15:00");

        int eventID = 0;
        String queryCustomEvent = "SELECT * FROM customEvents WHERE username=? AND scheduleName=? AND eventName=?";
        try(PreparedStatement prepEvent = dm.db.prepareStatement(queryCustomEvent)) {
            prepEvent.setString(1, "karlyripper");
            prepEvent.setString(2, "my2025spring");
            prepEvent.setString(3, "Scrum");
            ResultSet rs = prepEvent.executeQuery();
            eventID = rs.getInt("id");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        dm.removeCustomEvent(eventID);
        try(PreparedStatement prepEvent = dm.db.prepareStatement(queryCustomEvent)) {
            prepEvent.setString(1, "karlyripper");
            prepEvent.setString(2, "my2025spring");
            prepEvent.setString(3, "Scrum");
            ResultSet rs = prepEvent.executeQuery();

            assertNull(rs.getString("username"));
            assertNull(rs.getString("scheduleName"));
            assertNull(rs.getString("eventName"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String queryCustomEventTime = "SELECT * FROM customEventsTimes WHERE id=?";
        try(PreparedStatement prepEventTime = dm.db.prepareStatement(queryCustomEventTime)) {
            prepEventTime.setInt(1, eventID);
            ResultSet rsTime = prepEventTime.executeQuery();
            assertNull(rsTime.getString("day"));
            assertNull(rsTime.getString("start_time"));
            assertNull(rsTime.getString("end_time"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
