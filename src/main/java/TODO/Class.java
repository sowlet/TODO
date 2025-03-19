package TODO;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Class
{
    // Variables (same name and order as in the data file)
    private int credits;
    private String[] faculty;
    private boolean is_lab;
    private boolean is_open;
    private String location;
    private String name;
    private int number;
    private int open_seats;
    private char section;
    private String semester;
    private String subject;
    private ClassTime[] times;
    private int total_seats;

    //Variables (not from data file)
    private double rating;
    private int numRatings;

    // Constructor
    public Class(int credits, String[] faculty, boolean is_lab, boolean is_open, String location, String name, int number, int open_seats, char section, String semester, String subject, ClassTime[] times, int totalSeats){
        this.credits = credits;
        this.faculty = faculty;
        this.is_lab = is_lab;
        this.is_open = is_open;
        this.location = location;
        this.name = name;
        this.number = number;
        this.open_seats = open_seats;
        this.section = section;
        this.semester = semester;
        this.subject = subject;
        this.times = times;
        this.total_seats = totalSeats;

        this.rating = 0;
        this.numRatings = 0;
    }

    // Getters
    public int getCredits() {
        return credits;
    }

    public String[] getFaculty() {
        return faculty;
    }

    public boolean getIsLab() {
        return is_lab;
    }

    public boolean getIsOpen() {
        return is_open;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public int getOpenSeats() {
        return open_seats;
    }

    public char getSection() {
        return section;
    }

    public String getSemester() {
        return semester;
    }

    public String getSubject() {
        return subject;
    }

    public ClassTime[] getTimes() {
        return times;
    }

    public int getTotalSeats() {
        return total_seats;
    }

    public double getRating(){
        return rating;
    }

    @Override
    public String toString(){
        String classTimes = "";
        for (ClassTime cT : times) {
            classTimes += cT.getDay() + " " + cT.getStartTime() + " - " + cT.getEndTime() + "\n";
        }
        return subject + number + " " + section + "\n" + name + "\n" + classTimes + " " + semester + "\nlocation: " + location + "\ncredits:  " + credits + "\nseats: " + open_seats + "/" + total_seats + " seats\nislab: " + is_lab;
    }

    public String getClassNames(){
        return subject + number + " " + section + " : " + name;
    }


    /*
    method to recalulate the class rating based on input

    @param newRating the rating to be added that must be between 0 and 5
    @return 0 upon success or -1 upon failure
     */
    public int giveRating(double newRating){

        if (newRating >= 0 && newRating <= 5){
            rating = (rating * numRatings + newRating) / (numRatings + 1);
            numRatings ++;
            return 0;
        }
        return -1;

    }

    /*
    method to check if the class has a time conflict with another class

    @param otherClass class to compare to
    @return true if there is a time conflict false if there is no conflict
    @throws DateTimeParseException if any time does not match format HH:mm:ss
     */
    public boolean hasTimeConflict(Class otherClass)throws DateTimeParseException{
        boolean conflict = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // below code is modified ai generated code
        for (ClassTime time : this.times) {
            try {
                LocalTime timeStart = LocalTime.parse(time.getStartTime(), formatter);
                LocalTime timeEnd = LocalTime.parse(time.getEndTime(), formatter);
                for (ClassTime otherTime : otherClass.times) {
                    if (time.getDay().equals(otherTime.getDay())) {
                        String otet = otherTime.getEndTime();
                        String otst = otherTime.getStartTime();

                        try {
                            LocalTime otherTimeEndTime = LocalTime.parse(otet, formatter);
                            LocalTime otherTimeStartTime = LocalTime.parse(otst, formatter);

                            if (otherTimeStartTime.compareTo(timeStart) > -1 && otherTimeStartTime.compareTo(timeEnd) < 1) { // start time conflicts
                                conflict = true;
                            } else if (otherTimeEndTime.compareTo(timeStart) > -1 && otherTimeEndTime.compareTo(timeEnd) < 1) { // end time conflicts
                                conflict = true;
                            }
                        } catch (DateTimeParseException ce) {
                            throw ce;
                        }
                    }
                }
            } catch(DateTimeParseException e){
                System.err.println("Error parsing time string: " + e.getMessage());
                throw e;
            }
        }

        return conflict;
    }

}
