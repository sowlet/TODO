package TODO;

import java.sql.Time;

public class ClassTime {
    private String day;
    private Time end_time;
    private Time start_time;

    public ClassTime(String day, Time end_time, Time start_time){
        this.day = day;
        this.end_time = end_time;
        this.start_time = start_time;
    }

    public String getDay(){
        return day;
    }

    public Time getEndTime(){
        return end_time;
    }

    public Time getStartTime(){
        return start_time;
    }
}
