package TODO;

import java.util.*;

public class Schedule {


    // Variables
    private ArrayList<Class> classes;
    private String name;


    // Constructor
    public Schedule(String newName) {
        this.name = newName;
        this.classes = new ArrayList<>();
    }


    // getters and setters
    public ArrayList<Class> getClasses() {
        return classes;
    }

    public String getName() {
        return name;
    }
    public void changeName(String newName){
        //TODO: add checks to limit user input
        name = newName;
    }



    // methods
    /*
    adds class if there are no conflicts

    @param c Class object to be added
     */
    public void addClass(Class c) {
        if (!hasTimeConflict(c)) {
            classes.add(c);
        }
    }

    /*
    removes a given class from schedule

    @param c Class object to be added
     */
    public void removeClass(Class c){
        if(hasClass(c)) {
            //If this works
            classes.remove(c);


        }
    }

    /*
    returns whether the schedule has a Class object or not
    NOTE: all classes used in this project (for the MVP) are objects and all schedules use shallow copies of them
          thus, we are using the equality operator

    @param c Class object to check
    @return true if the schedule contains the class or false if the schedule does not contain the class
     */
    public boolean hasClass(Class c){
        for (Class scheduleClass: classes) {
            if (c == scheduleClass) {
                return true;
            }
        }
        return false;
    }


    /*
    checks whether the given class conflicts with any classes in the schedule

    @param c class to be checked
    @return true if there is a conflict between the given class and the rest within the schedule or false otherwise
     */
    public boolean hasTimeConflict(Class c) {
        for (Class scheduleClass: classes) {
            if (scheduleClass.hasTimeConflict(c)) {
                return true;
            }
        }
        return false;
    }

    /*
    checks whether the given class matches the semester of the other classes in the schedule

    @param c class to be checked
    @return true if there is a semester conflict between the given class or false if they all match
     */
    public boolean hasSemesterConflict(Class c) {
        for (Class scheduleClass: classes) {
            if (!c.getSemester().equals(scheduleClass.getSemester())) {
                return true;
            }
        }
        return false;
    }
}