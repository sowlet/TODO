package TODO;

import java.util.ArrayList;

public class Account {
    // Variables
    private ArrayList<Class> classesTaken;
    public ArrayList<Schedule> schedules;
    private String username;
    private String password;
    private String email;
    private ArrayList<String> majors;
    private ArrayList<String> minors;

    // Constructor
    public Account (String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.classesTaken = new ArrayList<Class>();
        this.schedules = new ArrayList<Schedule>();
        this.majors = new ArrayList<String>();
        this.minors = new ArrayList<String>();
    }

    // Methods
    public void addTakenClass(Class c) {
        classesTaken.add(c);
    }
    public void removeTakenClass(Class c){
        classesTaken.remove(c);
    }

    public String getUsername(){
        return this.username;
    }
    public String getPassword(){return this.password;}
    public String getEmail(){return this.email;}
    public String getMajors(){
        return this.majors.toString();
    }
    public String getMinors(){
        return this.minors.toString();
    }
    public String getClassesTaken() {
        String classes = "";
        for (Class c : classesTaken) {
            classes += c.getName() + " ";
        }
        return classes;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void addSchedule(String name){
        this.schedules.add(new Schedule(name));
    }
    public void removeSchedule(Schedule schedule){
        this.schedules.remove(schedule);
    }

    public void addMajor(String major){
        this.majors.add(major);
    }
    public void removeMajor(String major){
        this.majors.remove(major);
    }

    public void addMinor(String minor){
        this.minors.add(minor);
    }
    public void removeMinor(String minor){
        this.minors.remove(minor);
    }
}
