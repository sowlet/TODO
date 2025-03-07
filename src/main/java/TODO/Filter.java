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



// filters by Date -> MWF or TTR
class DateFilter extends Filter {
    private Day days;

    public DateFilter(Day days) {
        super();
        this.days = days;
    }

    @Override
    public ArrayList<Class> filt(ArrayList<Class> classes) {
        ArrayList<Class> fClasses = new ArrayList<>();

        for (Class c: classes) {
            boolean added = false;
            for (ClassTime t: c.getTimes()) {
                if (added) {break;}
                switch(days) {
                    case MWF:
                        if (t.getDay().equals("M") || t.getDay().equals("W") || t.getDay().equals("F")) {
                            fClasses.add(c);
                            added = true;
                        }
                        break;
                    case TR:
                        if (t.getDay().equals("T") || t.getDay().equals("R")) {
                            fClasses.add(c);
                            added = true;
                        }
                        break;
                }
            }
        }

        return fClasses;
    }
}



// Day options for classes
enum Day {
    MWF,
    TR
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


