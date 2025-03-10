package TODO;

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
    private int totalSeats;

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
        this.totalSeats = totalSeats;

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
        return totalSeats;
    }

    public double getRating(){
        return rating;
    }


    //method to recalulate the class rating based on input
    public int giveRating(double newRating){

        if (newRating >= 0 && newRating <= 5){
            rating = (rating * numRatings + newRating) / (numRatings + 1);
            numRatings ++;
            return 0;
        }
        return -1;

    }

    //method to check if the class has a time conflict with another class
    public boolean hasTimeConflict(Class otherClass){
        boolean conflict = false;

        for (ClassTime time : this.times){
            for (ClassTime otherTime : otherClass.times){
                if (time.getDay().equals(otherTime.getDay())){
                    if ((time.getStartTime().compareTo(otherTime.getEndTime()) < 0 && time.getEndTime().compareTo(otherTime.getStartTime()) > 0) || (otherTime.getStartTime().compareTo(time.getEndTime()) < 0 && otherTime.getEndTime().compareTo(time.getStartTime()) > 0)){
                        conflict = true;
                    }
                }
            }
        }

        return conflict;
    }

}
