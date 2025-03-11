package TODO;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        super();
        start = startTime;
        end = endTime;
    }

    /*
    returns sublist of classes that fit into stored time slot

    @param classes the list of classes to be filtered
    @return the list of filtered classes
    @throws DateTimeParseException if any of the Class objects have incompatible format to HH:mm:ss
     */
    @Override
    public ArrayList<Class> filt(ArrayList<Class> classes) throws DateTimeParseException {
        ArrayList<Class> fClasses = new ArrayList<>();

        // below code is ai generated and modified
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        try {
            LocalTime s = LocalTime.parse(start, formatter);
            LocalTime e = LocalTime.parse(end, formatter);
            for (Class c: classes) {
                boolean add = true;
                for(ClassTime ct: c.getTimes()) {
                    String cte = ct.getEndTime();
                    String cts = ct.getStartTime();

                    try {
                        LocalTime procClassTimeEnd = LocalTime.parse(cte, formatter);
                        LocalTime procClassTimeStart = LocalTime.parse(cts, formatter);

                        if (procClassTimeStart.isBefore(s) || procClassTimeEnd.isAfter(e)) {
                            add = false;
                        }
                    } catch (DateTimeParseException ce) {
                        System.err.println("Error parsing time string: " + ce.getMessage());
                        throw ce;
                    }
                }
                if (add) {
                    fClasses.add(c);
                }
            }
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing time string: " + e.getMessage());
            throw e;
        }

        return fClasses;
    }
}


