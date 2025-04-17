package TODO;

import com.google.gson.*;

import java.sql.*;

public class DatabaseManager {
    public Connection db;

    //strings to create tables (look here to find the structure of the tables)

    //create tables to hold class info
    String create_classes_table = "CREATE TABLE IF NOT EXISTS classes (\n"
            + "id INTEGER PRIMARY KEY,\n"
            + "credits INTEGER NOT NULL,\n" //faculty
            + "is_lab BOOLEAN NOT NULL,\n"
            + "is_open BOOLEAN NOT NULL,\n"
            + "location TEXT NOT NULL,\n"
            + "name TEXT NOT NULL,\n"
            + "number INTEGER NOT NULL,\n"
            + "open_seats INTEGER NOT NULL,\n"
            + "section TEXT NOT NULL,\n"
            + "semester TEXT NOT NULL,\n"
            + "subject TEXT NOT NULL,\n" //classtimes
            + "total_seats INTEGER NOT NULL,\n"
            + "rating REAL NOT NULL,\n"
            + "numRatings INTEGER NOT NULL\n)";

    String create_class_time_table = "CREATE TABLE IF NOT EXISTS classTimes (\n"
            + "id INTEGER, \n"
            + "time_id INTEGER PRIMARY KEY AUTOINCREMENT, \n"
            + "day TEXT NOT NULL,\n"
            + "end_time TEXT NOT NULL,\n"
            + "start_time TEXT NOT NULL,\n"
            + "FOREIGN KEY (id) REFERENCES classes(id)\n)";

    String create_faculty_table = "CREATE TABLE IF NOT EXISTS faculty (\n"
            + "id INTEGER,\n"
            + "fullName TEXT NOT NULL,\n"
            + "PRIMARY KEY (id, fullName),\n"
            + "FOREIGN KEY (id) REFERENCES classes(id)\n)";

    //create tables to hold account info
    String create_accounts_table = "CREATE TABLE IF NOT EXISTS accounts (\n"
            + "username TEXT PRIMARY KEY NOT NULL,\n"
            + "password TEXT NOT NULL,\n"
            + "email TEXT NOT NULL\n)";

    String create_classesTaken_table = "CREATE TABLE IF NOT EXISTS classesTaken (\n"
            + "username TEXT NOT NULL,\n"
            + "id INTEGER NOT NULL,\n"
            + "hasRatedClass BOOLEAN NOT NULL,\n"
            + "PRIMARY KEY (username, id),\n"
            + "FOREIGN KEY (username) REFERENCES accounts(username),\n"
            + "FOREIGN KEY (id) REFERENCES classes(id)\n)";

    String create_schedules_table = "CREATE TABLE IF NOT EXISTS schedules (\n"
            + "username TEXT NOT NULL,\n"
            + "scheduleName TEXT NOT NULL,\n"
            + "PRIMARY KEY (username, scheduleName),\n"
            + "FOREIGN KEY (username) REFERENCES accounts(username)\n)";

    String create_scheduledClasses_table = "CREATE TABLE IF NOT EXISTS scheduledClasses (\n"
            + "username TEXT NOT NULL, \n"
            + "scheduleName TEXT NOT NULL, \n"
            + "id INTEGER NOT NULL, \n"
            + "PRIMARY KEY (username, scheduleName, id), \n"
            + "FOREIGN KEY (username) REFERENCES accounts(username), \n"
            + "FOREIGN KEY (id) REFERENCES classes(id)\n)";

    String create_majorsAndMinors_table = "CREATE TABLE IF NOT EXISTS majorsAndMinors (\n"
            + "username TEXT NOT NULL,\n"
            + "mName TEXT NOT NULL,\n"
            + "isMajor BOOLEAN NOT NULL,\n"
            + "PRIMARY KEY (username, mName, isMajor),\n"
            + "FOREIGN KEY (username) REFERENCES accounts(username)\n)";


    //constructor (establishes a connection to the database)
    public DatabaseManager() throws SQLException {
        db = connect();
    }

    //drop all tables
    public void dropAllTables(){
        String drop_classes_table = "DROP TABLE IF EXISTS classes";
        String drop_classTimes_table = "DROP TABLE IF EXISTS classTimes";
        String drop_faculty_table = "DROP TABLE IF EXISTS faculty";
        String drop_accounts_table = "DROP TABLE IF EXISTS accounts";
        String drop_classesTaken_table = "DROP TABLE IF EXISTS classesTaken";
        String drop_schedules_table = "DROP TABLE IF EXISTS schedules";
        String drop_scheduledClasses_table = "DROP TABLE IF EXISTS scheduledClasses";
        String drop_majorsAndMinors_table = "DROP TABLE IF EXISTS majorsAndMinors";

        try(Statement stat = db.createStatement()){
            stat.execute(drop_classes_table);
            stat.execute(drop_classTimes_table);
            stat.execute(drop_faculty_table);
            stat.execute(drop_accounts_table);
            stat.execute(drop_classesTaken_table);
            stat.execute(drop_schedules_table);
            stat.execute(drop_scheduledClasses_table);
            stat.execute(drop_majorsAndMinors_table);
        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }

    //for testing purposes, method checks if a table exists
    public boolean doesTableExist(String tableName){
        String table_check = "SELECT * FROM sqlite_schema WHERE type='table' AND name=?";

        try(PreparedStatement prep = db.prepareStatement(table_check)){
            prep.setString(1, tableName);
            ResultSet exists = prep.executeQuery();
            return exists.next();
        }catch (SQLException e) {
            System.out.println("Error adding to the class table: " + e.getMessage());
        }

        return false;
    }


    //use the strings above to create the tables
    public void createAllTables(){

        try(Statement stat = db.createStatement()){
            stat.execute(create_classes_table);
            stat.execute(create_class_time_table);
            stat.execute(create_faculty_table);
            stat.execute(create_accounts_table);
            stat.execute(create_classesTaken_table);
            stat.execute(create_schedules_table);
            stat.execute(create_scheduledClasses_table);
            stat.execute(create_majorsAndMinors_table);
        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }

    //methods to insert data into the tables
    public void addClassToDatabase(Class course, int ID){
        String insert_class = "INSERT INTO classes (id, credits, is_lab, is_open, location, name, number, open_seats, section, semester, subject, total_seats, rating, numRatings) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String insert_classTimes = "INSERT INTO classTimes (id, day, end_time, start_time) VALUES (?,?,?,?)";
        String insert_faculty = "INSERT INTO faculty (id, fullName) VALUES (?,?)";


        try(PreparedStatement prep = db.prepareStatement(insert_class)){
            prep.setInt(1, ID);
            prep.setInt(2, course.getCredits());
            prep.setBoolean(3, course.getIsLab());
            prep.setBoolean(4, course.getIsOpen());
            prep.setString(5, course.getLocation());
            prep.setString(6, course.getName());
            prep.setInt(7, course.getNumber());
            prep.setInt(8, course.getOpenSeats());
            prep.setString(9, String.valueOf(course.getSection()));
            prep.setString(10, course.getSemester());
            prep.setString(11, course.getSubject());
            prep.setInt(12, course.getTotalSeats());
            prep.setDouble(13, 0);
            prep.setInt(14, 0);

            prep.executeUpdate();
        }catch (SQLException e) {
            System.out.println("Error adding to the class table: " + e.getMessage());
        }

        try(PreparedStatement prep = db.prepareStatement(insert_faculty)){
            for (String faculty : course.getFaculty()){
                prep.setInt(1, ID);
                prep.setString(2, faculty);
                prep.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error adding to the faculty table: " + e.getMessage());
        }

        try(PreparedStatement prep = db.prepareStatement(insert_classTimes)){
            ClassTime[] times = course.getTimes();
            for (ClassTime time: times){
                prep.setInt(1, ID);
                prep.setString(2, time.getDay());
                prep.setString(3, time.getEndTime());
                prep.setString(4, time.getStartTime());
                prep.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error adding id " + ID + " to the classTime table: " + e.getMessage());
        }
    }

    public void addAccountToDatabase(String username, String password, String email) {
        String insert_account = "INSERT INTO accounts (username, password, email) VALUES (?,?,?)";

        try (PreparedStatement prep = db.prepareStatement(insert_account)) {
            prep.setString(1, username);
            prep.setString(2, password);
            prep.setString(3, email);
            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding to the accounts table: " + e.getMessage());
        }
    }

    public void removeAccountFromDatabase(String username, String password, String email) {
        String delete_account = "DELETE FROM accounts WHERE username=? AND password=? AND email=?";
        String delete_schedule = "DELETE FROM schedules WHERE username=?";
        String delete_scheduledClasses = "DELETE FROM scheduledClasses WHERE username=?";
        String delete_classesTaken = "DELETE FROM classesTaken WHERE username=?";
        String delete_majorsAndMinors = "DELETE FROM majorsAndMinors WHERE username=?";

        try (PreparedStatement prep = db.prepareStatement(delete_account)) {
            prep.setString(1, username);
            prep.setString(2, password);
            prep.setString(3, email);
            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error removing from the accounts table: " + e.getMessage());
        }

        try (PreparedStatement prep = db.prepareStatement(delete_schedule)) {
            prep.setString(1, username);
            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error removing from the schedules table: " + e.getMessage());
        }

        try (PreparedStatement prep = db.prepareStatement(delete_scheduledClasses)) {
            prep.setString(1, username);
            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error removing from the scheduledClasses table: " + e.getMessage());
        }

        try (PreparedStatement prep = db.prepareStatement(delete_classesTaken)) {
            prep.setString(1, username);
            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error removing from the classesTaken table: " + e.getMessage());
        }

        try (PreparedStatement prep = db.prepareStatement(delete_majorsAndMinors)) {
            prep.setString(1, username);
            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error removing from the majorsAndMinors table: " + e.getMessage());
        }
    }

    public void addScheduleToDatabase(String username, String name) {
        String insert_schedule = "INSERT INTO schedules (username, scheduleName) VALUES (?,?)";

        try (PreparedStatement prep = db.prepareStatement(insert_schedule)) {
            prep.setString(1, username);
            prep.setString(2, name);
            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding to the schedules table: " + e.getMessage());
        }
    }

    public void removeScheduleFromDatabase(String username, String name) {
        String delete_schedule = "DELETE FROM schedules WHERE username=? AND scheduleName=?";
        String delete_scheduledClasses = "DELETE FROM scheduledClasses WHERE username=? AND scheduleName=?";

        try (PreparedStatement prep = db.prepareStatement(delete_schedule)) {
            prep.setString(1, username);
            prep.setString(2, name);
            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error removing from the schedules table: " + e.getMessage());
        }

        try (PreparedStatement prep = db.prepareStatement(delete_scheduledClasses)) {
            prep.setString(1, username);
            prep.setString(2, name);
            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error removing from the scheduledClasses table: " + e.getMessage());
        }
    }

    public void addClassToSchedule(String username, String scheduleName, int ID) {
        String insert_scheduledClass = "INSERT INTO scheduledClasses (username, scheduleName, id) VALUES (?,?,?)";

        try (PreparedStatement prep = db.prepareStatement(insert_scheduledClass)) {
            prep.setString(1, username);
            prep.setString(2, scheduleName);
            prep.setInt(3, ID);
            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding to the scheduledClasses table: " + e.getMessage());
        }
    }

    public void removeClassFromSchedule(String username, String scheduleName, int ID) {
        String delete_scheduledClasses = "DELETE FROM scheduledClasses WHERE username=? AND scheduleName=? AND id=?";

        try (PreparedStatement prep = db.prepareStatement(delete_scheduledClasses)) {
            prep.setString(1, username);
            prep.setString(2, scheduleName);
            prep.setInt(3, ID);
            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error removing from the scheduledClasses table: " + e.getMessage());
        }
    }

    public void addClassAsTaken(String username, int ID) {
        String insert_classesTaken = "INSERT INTO classesTaken (username, id, hasRatedClass) VALUES (?,?,?)";

        try (PreparedStatement prep = db.prepareStatement(insert_classesTaken)) {
            prep.setString(1, username);
            prep.setInt(2, ID);
            prep.setBoolean(3, false);
            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding to the classesTaken table: " + e.getMessage());
        }
    }

    public void removeClassAsTaken(String username, int ID) {
        String delete_classesTaken = "DELETE FROM classesTaken WHERE username=? AND id=?";

        try (PreparedStatement prep = db.prepareStatement(delete_classesTaken)) {
            prep.setString(1, username);
            prep.setInt(2, ID);
            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error removing from the classesTaken table: " + e.getMessage());
        }
    }

    public void addMajorOrMinor(String username, String mName, boolean isMajor) {
        String insert_majorsAndMinors = "INSERT INTO majorsAndMinors (username, mName, isMajor) VALUES (?,?,?)";

        try (PreparedStatement prep = db.prepareStatement(insert_majorsAndMinors)) {
            prep.setString(1, username);
            prep.setString(2, mName);
            prep.setBoolean(3, isMajor);
            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding to the majorsAndMinors table: " + e.getMessage());
        }
    }

    public void removeMajorOrMinor(String username, String mName, boolean isMajor) {
        String delete_majorsAndMinors = "DELETE FROM majorsAndMinors WHERE username=? AND mName=? AND isMajor=?";

        try (PreparedStatement prep = db.prepareStatement(delete_majorsAndMinors)) {
            prep.setString(1, username);
            prep.setString(2, mName);
            prep.setBoolean(3, isMajor);
            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error removing from the majorsAndMinors table: " + e.getMessage());
        }
    }

    //search method
    public JsonArray search(String name){
        String search_classes = "SELECT * FROM classes WHERE name LIKE ?";
        String search_classTimes = "SELECT * FROM classTimes WHERE id=?";
        String search_faculty = "SELECT * FROM faculty WHERE id=?";
        int classID = 0;

        JsonArray searchResults = new JsonArray();
        JsonArray times = new JsonArray();
        JsonArray facs = new JsonArray();
        String colName = "";
        Object value = null;

        try(PreparedStatement classPrep = db.prepareStatement(search_classes)){
            classPrep.setString(1, "%" + name + "%");
            ResultSet classResults = classPrep.executeQuery();

            ResultSetMetaData classMD= classResults.getMetaData();
            int numClassCols = classMD.getColumnCount();

            //TODO: add filters here

            /*iterate through the classes to get their times and faculty and
            * return them in json format*/

            while(classResults.next()){
                classID = classResults.getInt("id");

                JsonObject classObj = new JsonObject();
                for(int i = 2; i <= numClassCols; i++){
                    colName = classMD.getColumnName(i);
                    value = classResults.getObject(i);
                    classObj.addProperty(colName, value != null ? value.toString() : null);
                }

                //add classTIme and faculty properties

                //get the classTimes of the class
                try(PreparedStatement timesPrep = db.prepareStatement(search_classTimes)){
                    timesPrep.setString(1, "%" + name + "%");
                    ResultSet timeResults = timesPrep.executeQuery();

                    ResultSetMetaData timeMD= timeResults.getMetaData();
                    int numTimeCols = timeMD.getColumnCount();

                    while(timeResults.next()){
                        JsonObject classTimeObj = new JsonObject();
                        for(int i = 2; i <= numTimeCols; i++){
                            colName = timeMD.getColumnName(i);
                            value = timeResults.getObject(i);
                            classTimeObj.addProperty(colName, value != null ? value.toString() : null);
                        }
                        times.add(classTimeObj);
                    }
                    classObj.add("classTimes", times);

                    //classObj.addProperty("", )
                }catch (SQLException e){
                    System.out.println("Error searching the classTimes table: " + e.getMessage());
                }

                //get the faculty of the class
                try(PreparedStatement facPrep = db.prepareStatement(search_faculty)){
                    facPrep.setString(1, "%" + name + "%");
                    ResultSet facResults = facPrep.executeQuery();

                    ResultSetMetaData facMD= facResults.getMetaData();
                    int numFacCols = facMD.getColumnCount();

                    while(facResults.next()){
                        JsonObject facultyObj = new JsonObject();
                        for(int i = 2; i <= numFacCols; i++){
                            colName = facMD.getColumnName(i);
                            value = facResults.getObject(i);
                            facultyObj.addProperty(colName, value != null ? value.toString() : null);
                        }
                        facs.add(facultyObj);
                    }
                    classObj.add("faculty", facs);

                }catch (SQLException e){
                    System.out.println("Error searching the faculty table: " + e.getMessage());
                }

                searchResults.add(classObj);
            }
        }catch (SQLException e){
            System.out.println("Error searching the classes table: " + e.getMessage());
        }

        return searchResults;
    }

    public void printClassInfo(){
        String select_all_classes = "SELECT * FROM classes";
        //String select_all_classTimes = "SELECT * FROM classTimes";

        try(Statement query = db.createStatement()){

            ResultSet allClasses = query.executeQuery(select_all_classes);
            //ResultSet allTimes = query.executeQuery(select_all_classTimes);

//            while(allTimes.next()){
//                System.out.println(allTimes.getInt("id"));
//                System.out.println(allTimes.getString("day") + "\n");
//            }

            while(allClasses.next()){
                System.out.println(allClasses.getInt("id"));
                System.out.println(allClasses.getString("subject"));
                System.out.println(allClasses.getInt("number"));
                System.out.println(allClasses.getString("section"));
                System.out.println(allClasses.getString("semester") + "\n");
            }

        }catch(SQLException e){
            System.out.println("Error selecting from classes: " + e.getMessage());
        }
    }



    //methods used to create and close the connection to the database
    public static Connection connect(){
        String url = "jdbc:sqlite:src/main/java/TODO_Database.db";  //path to the database
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url);
            if (conn != null){
                System.out.println("Connection to SQLite succeeded.");
            }
        }catch(SQLException e){
            System.out.println("Connection failed: " + e.getMessage());
        }

        return conn;
    }

    public void closeConnection(){
        try{
            db.close();
            System.out.println("Connection closed.");
        }catch(SQLException e){
            System.out.println("Failed to close connection: " + e.getMessage());
        }
    }
}
