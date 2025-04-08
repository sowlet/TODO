package TODO;

import java.io.*;
import java.sql.Time;
import java.time.format.DateTimeParseException;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import static java.lang.System.exit;

public class Main {
    public static ArrayList<Class> classes = new ArrayList<>();
    public static ArrayList<Account> accounts = new ArrayList<>();
    public static Schedule currentlyEditing = null;
    public static Search search;
    public static ArrayList<Class> searchResults = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        loadClassesFromJson("src/main/java/TODO/data_wolfe.json", classes);
        loadAccountsFromJson("src/main/java/TODO/data_accounts.json", accounts);
        System.out.println(getSubjects());
        run();
    }

    public static void run() throws FileNotFoundException {
        Account currentAccount = null;
        Scanner scan = new Scanner(System.in);
        String input;

        while (true) {
            while (currentAccount == null) {
                System.out.println("Welcome!\nTo create an account: type 'c'\nTo login: type 'l'\nTo exit the application: type 'e'");
                input = scan.nextLine();

                switch (input) {
                    case "c":
                        createAccount(scan);
                        break;
                    case "l":
                        currentAccount = login(scan);
                        break;
                    case "e":
                        exit(0);
                    default:
                        System.out.println("Invalid input. Please try again.");
                }
            }

            while (currentAccount != null) {
                System.out.println("Welcome " + currentAccount.getUsername() + "!");
                System.out.println("Home:\nTo view your schedules: type 's'\nTo view account info: type 'a'\nTo logout and save your changes: type 'l'");
                input = scan.nextLine();

                switch(input) {
                    case "s":
                        manageSchedules(scan, currentAccount);
                        break;
                    case "a":
                        viewAccount(scan, currentAccount);
                        break;
                    case "l":
                        updateAccountsToJson("src/main/java/TODO/data_accounts.json", accounts);
                        currentAccount = null;
                        break;
                    default:
                        System.out.println("Invalid input. Please try again.");
                }
            }
        }
    }

    private static void viewAccount(Scanner scan, Account account) {
        while (true) {
            System.out.println("Account Info:\nUsername: " + account.getUsername() + "\nEmail: " + account.getEmail());
            System.out.println("Majors: " + account.getMajors());
            System.out.println("Minors: " + account.getMinors());
            System.out.println("Classes Taken: " + account.getClassesTaken());
            System.out.println("To add a major: type 'add major'\nTo remove a major: type 'remove major'\nTo add a minor: type 'add minor'\nTo remove a minor: type 'remove minor'\nTo go back: type 'b'");

            String input = scan.nextLine();

            if (input.equals("add major")) {
                System.out.println("Enter major to add: ");
                String major = scan.nextLine();
                if (!account.getMajors().contains(major)) {
                    account.addMajor(major);
                    System.out.println("Major added successfully");
                } else {
                    System.out.println("Major already exists");
                }
            } else if (input.equals("remove major")) {
                System.out.println("Enter major to remove: ");
                String major = scan.nextLine();
                if (account.getMajors().contains(major)) {
                    account.removeMajor(major);
                    System.out.println("Major removed successfully");
                } else {
                    System.out.println("Major does not exist");
                }
            } else if (input.equals("add minor")) {
                System.out.println("Enter minor to add: ");
                String minor = scan.nextLine();
                if (!account.getMinors().contains(minor)) {
                    account.addMinor(minor);
                    System.out.println("Minor added successfully");
                } else {
                    System.out.println("Minor already exists");
                }
            } else if (input.equals("remove minor")) {
                System.out.println("Enter minor to remove: ");
                String minor = scan.nextLine();
                if (account.getMinors().contains(minor)) {
                    account.removeMinor(minor);
                    System.out.println("Minor removed successfully");
                } else {
                    System.out.println("Minor does not exist");
                }
            } else if (input.equals("b")) {
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static void createAccount(Scanner scan) throws FileNotFoundException {
        System.out.println("Create an account\nEnter username: ");
        String username = scan.nextLine();
        if(usernameExists(username)) {
            System.out.println("This username already exists.");
        } else {
            System.out.println("Enter password: ");
            String password = scan.nextLine();
            System.out.println("Enter email: ");
            String email = scan.nextLine();
            if (emailExists(email)) {
                System.out.println("This email already has an account");
            } else {
                accounts.add(new Account(username, password, email));
                updateAccountsToJson("src/main/java/TODO/data_accounts.json", accounts);
                System.out.println("Account created successfully");
            }
        }
    }

    private static boolean usernameExists(String username) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private static boolean emailExists(String email) {
        for (Account account : accounts) {
            if (account.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private static Account login(Scanner scan) {
        System.out.println("Login\nEnter username: ");
        String username = scan.nextLine();
        System.out.println("Enter password: ");
        String password = scan.nextLine();

        for (Account account : accounts) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                System.out.println("Login successful");
                return account;
            }
        }
        System.out.println("Login failed. Please try again.");
        return null;
    }

    private static void manageSchedules(Scanner scan, Account currentAccount) {
        String input;
        while (true) {
            System.out.println("Home/Schedules:\nTo view a schedule: type 'v'\nTo create a new schedule: type 'c'\nTo edit a schedule: type 'e'\nTo delete a schedule: type 'd'\nTo go back: type 'b'");
            input = scan.nextLine();

            switch(input) {
                case "v":
                    viewSchedule(scan, currentAccount);
                    break;
                case "c":
                    createSchedule(scan, currentAccount);
                    break;
                case "e":
                    editSchedule(scan, currentAccount);
                    break;
                case "d":
                    deleteSchedule(scan, currentAccount);
                    break;
                case "b":
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static void viewSchedule(Scanner scan, Account currentAccount) {
        System.out.println("Enter the name of the schedule you would like to view: ");

        // display schedules
        for (Schedule schedule : currentAccount.schedules) {
            System.out.println(schedule.getName());
        }

        String scheduleName = scan.nextLine();
        boolean scheduleFound = false;
        for (Schedule schedule : currentAccount.schedules) {
            if (schedule.getName().equals(scheduleName)) {
                viewScheduleInCalendarFormat(schedule);
                scheduleFound = true;
                break;
            }
        }
        if (!scheduleFound) {
            System.out.println("Schedule not found.");
        }
    }
//    private static void viewScheduleStreamlined(Scanner scan, Account currentAccount) {
//        This had issues; dropping it to focus on actual new features
//
//        int scheduleNumber = 0;
//        if(currentAccount.schedules.isEmpty()) {
//            System.out.println("No schedules to view. Please create one.");
//        } else {
//            System.out.println("Enter the number of the schedule you would like to view: ");
//            // display schedules
//            for (int i = 0; i < currentAccount.schedules.size(); i++) {
//                System.out.println((i + 1) + ": " + currentAccount.schedules.get(i).getName());
//            }
//            try{
//                scheduleNumber = Integer.parseInt(scan.nextLine());
//                if((scheduleNumber - 1) <= currentAccount.schedules.size() && (scheduleNumber - 1) >= 1) {
//                    viewScheduleInCalendarFormat(currentAccount.schedules.get(scheduleNumber - 1));
//
//                } else {
//                    System.out.println("Invalid schedule number");
//                }
//            } catch (NumberFormatException e) {
//                System.out.println("Invalid input. Please enter a number.");
//            }
//        }
    }

//    public static void viewScheduleInCalendarFormatOLD(Schedule schedule) {
//        System.out.println("Schedule: " + schedule.getName());
//
//        // create map to store data for each class
//        Map<String, List<String>> classData = new HashMap<>();
//
//        // get the times, names, and days of the classes and add it to the respective class in the map
//        for (Class c : schedule.getClasses()) {
//            for (ClassTime t : c.getTimes()) {
//                String className = c.getName() + " " + c.getSection();
//                String time = c.getSubject() + " " + c.getNumber() + " " + c.getSection() + " " + t.getStartTime() + " - " + t.getEndTime();
//                String startTime = t.getStartTime();
//                String day = t.getDay();
//
//                if (!classData.containsKey(className)) {
//                    classData.put(className, new ArrayList<>());
//                }
//                classData.get(className).add(day + ": " + time);
//            }
//        }
//
//        // sort the class times
//        for (List<String> times : classData.values()) {
//            times.sort((time1, time2) -> {
//                String startTime1 = time1.split(": ")[1].split(" - ")[0];
//                String startTime2 = time2.split(": ")[1].split(" - ")[0];
//                return startTime1.compareTo(startTime2);
//            });
//        }
//
//        // display the schedule in a weekly calendar format
//        System.out.println("Weekly Schedule:");
//        System.out.println("-------------------------------------------------");
//        System.out.printf("%-20s|%-35s|%-35s|%-35s|%-35s|%-35s%n", "Class Name", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
//        System.out.println("-------------------------------------------------");
//        // display classes per day
//        for (String className : classData.keySet()) {
//            List<String> times = classData.get(className);
//            String[] days = new String[5];
//            Arrays.fill(days, ""); // Initialize days array with empty strings
//            for (String time : times) {
//                String[] parts = time.split(": ");
//                String day = parts[0];
//                String timeRange = parts[1];
//                switch (day) {
//                    case "M":
//                        days[0] += timeRange + " "; // Append time range to the respective day
//                        break;
//                    case "T":
//                        days[1] += timeRange + " ";
//                        break;
//                    case "W":
//                        days[2] += timeRange + " ";
//                        break;
//                    case "R":
//                        days[3] += timeRange + " ";
//                        break;
//                    case "F":
//                        days[4] += timeRange + " ";
//                        break;
//                }
//            }
//            System.out.printf("%-20s|%-35s|%-35s|%-35s|%-35s|%-35s%n", className, days[0], days[1], days[2], days[3], days[4]);
//        }
//    }

    public static void viewScheduleInCalendarFormat(Schedule schedule) {
        System.out.println("Schedule: " + schedule.getName());
        //Row across the top indicating the days of the week
        System.out.println("Weekly Schedule:");
        System.out.println("-------------------------------------------------");
        System.out.printf("%-20s|%-30s|%-30s|%-30s|%-30s|%-30s%n", "Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        System.out.println("-------------------------------------------------");

        //Variables for each line that gets printed off
        String time;

        //Loop through each half hour
        for(int i = 16; i < 42; i++) {
           if(i % 2 == 0) {
               time = (i / 2) + ":00:00";
           } else {
               time = (i / 2) + ":30:00";
           }

           //Creating the string for each day in here so that it gets reset for each line
            String M = "";
            String T = "";
            String W = "";
            String R = "";
            String F = "";

            //Look at each class and for each class's times check to see if they start at the half hour line we're on
            //If so, add the class's name to show up under the appropriate column.
           for(Class c : schedule.getClasses()) {
               for (ClassTime t : c.getTimes()) {
                   String startTime = t.getStartTime();
                   if(startTime.equals(time)) {
                       String day = t.getDay();
                       switch (day) {
                           case "M":
                            M = c.getSubject() + " " + c.getNumber() + " " + c.getSection();
                            break;
                        case "T":
                            T = c.getSubject() + " " + c.getNumber() + " " + c.getSection();
                            break;
                        case "W":
                            W = c.getSubject() + " " + c.getNumber() + " " + c.getSection();
                            break;
                        case "R":
                            R = c.getSubject() + " " + c.getNumber() + " " + c.getSection();
                            break;
                        case "F":
                            F = c.getSubject() + " " + c.getNumber() + " " + c.getSection();
                            break;
                       }
                   }
               }
           }

            if((i % 2) == 1) {
                time = "";
            }
            System.out.printf("%-20s|%-30s|%-30s|%-30s|%-30s|%-30s%n", time, M, T, W, R, F);
        }

    }

    private static void createSchedule(Scanner scan, Account currentAccount) {
        System.out.println("Enter the name of the schedule you would like to create: ");
        String scheduleName = scan.nextLine();
        for (Schedule schedule : currentAccount.schedules) {
            if (schedule.getName().equals(scheduleName)) {
                System.out.println("Schedule already exists");
                return;
            }
        }
        currentAccount.addSchedule(scheduleName);
    }

    private static void editSchedule(Scanner scan, Account currentAccount) {
        System.out.println("Schedules:\n");

        // display schedules
        //Again, this particular block of code was having issues; currently dropping it to focus on new features
//        int scheduleNumber = 0;
//        if(currentAccount.schedules.isEmpty()) {
//            System.out.println("No schedules to edit. Please create one.");
//            return;
//        } else {
//            System.out.println("Enter the number of the schedule you would like to view: ");
//            // display schedules
//            for (int i = 0; i < currentAccount.schedules.size(); i++) {
//                System.out.println((i + 1) + ": " + currentAccount.schedules.get(i).getName());
//            }
//            try{
//                scheduleNumber = Integer.parseInt(scan.nextLine());
//                if(scheduleNumber - 1 > currentAccount.schedules.size() || scheduleNumber - 1 < 0) {
//                    System.out.println("Invalid schedule number");
//                } else {
//                    currentlyEditing = currentAccount.schedules.get(scheduleNumber - 1);
//                }
//            } catch (NumberFormatException e) {
//                System.out.println("Invalid input. Please enter a number.");
//            }
//        }

        for (Schedule schedule : currentAccount.schedules) {
            System.out.println(schedule.getName());
        }

        System.out.println("Enter the name of the schedule you would like to edit: ");
        String scheduleName = scan.nextLine();
        for (Schedule schedule : currentAccount.schedules) {
            if (schedule.getName().equals(scheduleName)) {
                currentlyEditing = schedule;
                break;
            }
        }

        if (currentlyEditing != null) {
            String input;
            label:
            while (true) {
                System.out.println("Editing schedule: " + currentlyEditing.getName() +  "\n" + currentlyEditing.getClasses().toString() + "\nTo add a class: type 'a'\nTo remove a class: type 'r'\nTo go back: type 'b'");
                input = scan.nextLine();

                switch (input) {
                    case "a":
                        addClassToSchedule(scan);
                        break;
                    case "r":
                        removeClassFromSchedule(scan);
                        break;
                    case "b":
                        break label;
                    default:
                        System.out.println("Invalid input. Please try again.");
                        break;
                }
            }
        }
    }

    private static void addClassToSchedule(Scanner scan) {
        search = new Search();
        String input;
        while (true) {
            System.out.println("To search for classes: type 's'\nTo add a class to the schedule: type 'a'\nTo go back: type 'b'");
            input = scan.nextLine();

            switch (input) {
                case "s":
                    searchClasses(scan);
                    break;
                case "a":
                    System.out.println("Enter class name: ");
                    String className = scan.nextLine();
                    System.out.println("Enter class section: ");
                    char section = scan.nextLine().charAt(0);
                    System.out.println("Enter class semester: ");
                    String semester = scan.nextLine();

                    boolean classAdded = false;
                    boolean timeConflict = false;
                    for (Class c : classes) {
                        if (c.getName().equals(className) && c.getSection() == section && c.getSemester().equals(semester)) {
                            if (currentlyEditing.hasTimeConflict(c)) {
                                System.out.println("Class has a time conflict with current schedule");
                                timeConflict = true;
                            } else {
                                currentlyEditing.addClass(c);
                                System.out.println("Class successfully added to schedule!");
                                classAdded = true;
                                return;
                            }
                        }
                    }
                    if (!classAdded && !timeConflict) {
                        System.out.println("Class does not exist");
                    }
                    break;
                case "b":
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static void searchClasses(Scanner scan) {
        try {
            System.out.println("Enter search query: ");
            String query = scan.nextLine().toUpperCase();

            System.out.println("Would you like to filter by time? (y/n)");
            if (scan.nextLine().charAt(0) == 'y') {
                System.out.println("Enter start time (HH:mm:ss): ");
                String startTime = scan.nextLine();
                System.out.println("Enter end time (HH:mm:ss): ");
                String endTime = scan.nextLine();
                search.modifyFilter(new TimeFilter(startTime, endTime));
            }

            System.out.println("Would you like to filter by days/date? (y/n)");
            if (scan.nextLine().charAt(0) == 'y') {
                System.out.println("Type 'MWF' for MWF classes or 'TR' for TR classes");
                String days = scan.nextLine().toUpperCase();
                if (days.equals("MWF")) {
                    search.modifyFilter(new DateFilter(Day.MWF));
                } else if (days.equals("TR")) {
                    search.modifyFilter(new DateFilter(Day.TR));
                } else {
                    System.out.println("Not a valid date");
                }
            }

            System.out.println("Would you like to filter by course code? (y/n)");
            if (scan.nextLine().charAt(0) == 'y') {
                System.out.println("Enter course code: ");
                String subject = scan.nextLine().toUpperCase();
                if (getSubjects().contains(subject)) {
                    search.modifyFilter(new DepartmentFilter(subject));
                } else {
                    System.out.println("Not a valid course code");
                }
            }

            searchResults = search.search(query, classes);
            for (Class c : searchResults) {
                System.out.println(c + "\n");
            }
            // removing features so that next search will be clean
            search.removeFilter(new DepartmentFilter("Whatever!"));
            search.removeFilter(new DateFilter(Day.TR));
            search.removeFilter(new TimeFilter("00:00:00", "00:00:00"));
        } catch (Exception e){
            System.out.println(e.getMessage());
            search.removeFilter(new TimeFilter("00:00:00", "00:00:00"));
        }
    }

    private static void removeClassFromSchedule(Scanner scan) {
        System.out.println("Enter class name: ");
        String className = scan.nextLine();
        System.out.println("Enter class section: ");
        char section = scan.nextLine().charAt(0);
        System.out.println("Enter class semester: ");
        String semester = scan.nextLine();
        System.out.println(currentlyEditing.getClasses().toString());
        for (Class c : currentlyEditing.getClasses()) {
            if (c.getName().equals(className) && c.getSection() == section && c.getSemester().equals(semester)) {
                currentlyEditing.removeClass(c);
                System.out.println("Class successfully removed from schedule!");
                return;
            }
        }
        System.out.println("Class does not exist or cannot be removed from schedule.");
    }

    private static void deleteSchedule(Scanner scan, Account currentAccount) {
        System.out.println("Schedules:\n");

        // display schedules
        for (Schedule schedule : currentAccount.schedules) {
            System.out.println(schedule.getName());
        }

        System.out.println("Enter the name of the schedule you would like to delete: ");
        String scheduleName = scan.nextLine();
        for (Schedule schedule : currentAccount.schedules) {
            if (schedule.getName().equals(scheduleName)) {
                currentAccount.removeSchedule(schedule);
                currentlyEditing = null;
                System.out.println("Schedule deleted successfully");
                return;
            }
        }
        System.out.println("Schedule does not exist");
    }

    public static String getSubjects() {
        StringBuilder builder = new StringBuilder();
        Set<String> subjectsSeen = new HashSet<>();
        for (Class c : classes) {
            if (subjectsSeen.add(c.getSubject())) {
                builder.append(c.getSubject()).append("\t");
                if (subjectsSeen.size() % 9 == 0) {
                    builder.append("\n");
                }
            }
        }
        return builder.toString();
    }

    public static void loadClassesFromJson(String filePath, ArrayList<Class> classList) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            Gson gson = new Gson();
            Type classListType = new TypeToken<JsonWrapper>() {}.getType();
            JsonWrapper wrapper = gson.fromJson(reader, classListType);
            classList.addAll(wrapper.classes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class JsonWrapper {
        ArrayList<Class> classes;
    }

    public static void loadAccountsFromJson(String filePath, ArrayList<Account> accountList) throws FileNotFoundException {
        try (BufferedReader readerA = new BufferedReader(new FileReader(filePath))) {
            Gson gsonA = new Gson();
            Type accountListType = new TypeToken<JsonWrapperAccounts>() {}.getType();
            JsonWrapperAccounts wrapper = gsonA.fromJson(readerA, accountListType);
            accountList.addAll(wrapper.accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class JsonWrapperAccounts{
        ArrayList<Account> accounts;
    }

    static class JsonWrapperAccountObject{
        ArrayList<Account> accounts;
    }

    public static void updateAccountsToJson(String filePath, ArrayList<Account> accountList) throws FileNotFoundException {
        // Write the updated list back to the JSON file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            Gson gsonB = new Gson();
            JsonWrapperAccountObject wrapperB = new JsonWrapperAccountObject();
            wrapperB.accounts = accountList;
            gsonB.toJson(wrapperB, writer);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}