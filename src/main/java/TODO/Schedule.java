package TODO;

import java.util.ArrayList;

public class Schedule {

    // Variables
    private ArrayList<Class> classes;
    private String name;

    // Constructor
    public Schedule(String newName) {
        changeName(newName);
    }

    // Methods
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

    public void addClass(Class c) {
        if (!hasTimeConflict(c)) {
            classes.add(c);
        }
    }

    public void removeClass(Class c){
        //TODO: Implement removeClass
        if(hasClass(c)) {
            for(int i = 0; i < classes.size(); i++) {

            }
        }
    }

    public boolean hasClass(Class c){
        //TODO: Implement hasClass
        return false;
    }

    public boolean hasTimeConflict(Class c) {
        //TODO: Implement hasTimeConflict
        return false;
    }
}
