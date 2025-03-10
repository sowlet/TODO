package TODO;

import java.util.ArrayList;

abstract class Filter {

    public Filter(){
        //TODO
    }

    public abstract ArrayList<Class> filt(ArrayList<Class> classes);
}

class DepartmentFilter extends Filter {
    private String dept;

    public void DepartmentFilter(String dept) {
        //TODO: Implement filter by department
    }

    @Override
    public ArrayList<Class> filt(ArrayList<Class> classes) {
        //TODO
        return null;
    }
}

class DateFilter extends Filter {
    private Day days;

    public void DateFilter(Day days) {
        //TODO: Implement filter by date
    }

    @Override
    public ArrayList<Class> filt(ArrayList<Class> classes) {
        //TODO
        return null;
    }
}

enum Day {
    MWF,
    TR;
}

class TimeFilter extends Filter {
    private String start;
    private String end;

    public void TimeFilter(String startTime, String endTime) {
        //TODO: Implement filter by time
    }

    @Override
    public ArrayList<Class> filt(ArrayList<Class> classes) {
        //TODO
        return null;
    }
}


