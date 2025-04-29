package TODO;


public class ClassTime {
    private String day;
    private String end_time;
    private String start_time;

    public ClassTime(char day, String end_time, String start_time){
        this.day = Character.toString(day);
        this.end_time = end_time;
        this.start_time = start_time;
    }

    // getters
    public String getDay(){
        return day;
    }

    public String getEndTime(){
        return end_time;
    }

    public String getStartTime(){
        return start_time;
    }
}
