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
        if(hasClass(c)) {
            //If this works
            classes.remove(c);

            //Buf if it doesn't, we can use this, though at that point, we might as well eliminate the hasClass method and combine them
//            for(int i = 0; i < classes.size(); i++) {
//                if((c.getCourseCode().equals(classes.get(i).getCourseCode())) && (c.getSection() == classes.get(i).getSection())) {
//                    classes.remove(i);
//                }
//            }
        }
    }

    public boolean hasClass(Class c){
        for(int i = 0; i < classes.size(); i++) {
                if((c.getCourseCode().equals(classes.get(i).getCourseCode())) && (c.getSection() == classes.get(i).getSection())) {
                    return true;
                }
        }
        return false;
    }

    public boolean hasTimeConflict(Class c) {
        for(int i = 0; i < classes.size(); i++) {
            if((c.getDays().equals(classes.get(i).getDays())) && (c.getTime().equals(classes.get(i).getTime()))) {
                return true;
            }
        }
        return false;
    }
}
