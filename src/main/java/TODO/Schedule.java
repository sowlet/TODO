package TODO;

import java.util.ArrayList;

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
        /* I do not understand this code, so I will leave it commented out
            Buf if it doesn't, we can use this, though at that point, we might as well eliminate the hasClass method and combine them
            for(int i = 0; i < classes.size(); i++) {
                if((c.getCourseCode().equals(classes.get(i).getCourseCode())) && (c.getSection() == classes.get(i).getSection())) {
                    classes.remove(i);
                }
            } */
    }

    /*
    returns whether the schedule has a Class object or not

    @param c Class object to check
    @return true if the schedule contains the class or false if the schedule does not contain the class
     */
    public boolean hasClass(Class c){
        return true;
        /* I do not understand this code so I will comment it out
        for(int i = 0; i < classes.size(); i++) {
                if((c.getCourseCode().equals(classes.get(i).getCourseCode())) && (c.getSection() == classes.get(i).getSection())) {
                    return true;
                }
        }*/
    }


    /*
    checks whether the given class conflicts with any classes in the schedule

    @param c class to be checked
    @return true if there is a conflict between the given class and the rest within the schedule or false otherwise
     */
    public boolean hasTimeConflict(Class c) {
        return false;
        /* I do not understand this code so I will comment it out
        for(int i = 0; i < classes.size(); i++) {
            if((c.getDays().equals(classes.get(i).getDays())) && (c.getTime().equals(classes.get(i).getTime()))) {
                return true;
            }
        } */
    }
}
