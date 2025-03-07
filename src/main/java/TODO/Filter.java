package TODO;

import java.util.ArrayList;

// default Filter
abstract class Filter {
    public Filter(){} // empty constructor
    public abstract ArrayList<Class> filt(ArrayList<Class> classes);
}



// filters by department i.e. subject
class DepartmentFilter extends Filter {
    private String dept;

    // constructor
    public DepartmentFilter(String dept) {
        super();
        this.dept = dept.toUpperCase(); // all subjects are upper case
    }

    /*
    filters by list of classes by internally stored department String

    @param classes list of classes to be filtered
    @return list of classes that match the department
     */
    @Override
    public ArrayList<Class> filt(ArrayList<Class> classes) {
        ArrayList<Class> fClasses = new ArrayList<>();

        for (Class c: classes) {
            if (c.getSubject().equals(dept)) {
                fClasses.add(c);
            }
        }

        return fClasses;
    }
}



// filters by Date
class DateFilter extends Filter {
    private Day days;

    public DateFilter(Day days) {
        //TODO: Implement filter by department
    }

    @Override
    public ArrayList<Class> filt(ArrayList<Class> classes) {
        //TODO
        return null;
    }
}



// Day options for classes
enum Day {
    MWF,
    TR;
}



// filters by time
class TimeFilter extends Filter {
    private String start;
    private String end;

    public TimeFilter(String startTime, String endTime) {
        //TODO: Implement filter by department
    }

    @Override
    public ArrayList<Class> filt(ArrayList<Class> classes) {
        //TODO
        return null;
    }
}


