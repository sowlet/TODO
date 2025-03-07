package TODO;

import java.sql.Time;

public class ClassTime {
    private String day;
    private String end_time;
    private String start_time;

    public ClassTime(String day, String end_time, String start_time){
        this.day = day;
        this.end_time = end_time;
        this.start_time = start_time;
    }

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
