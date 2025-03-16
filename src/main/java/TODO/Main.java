package TODO;

import java.io.*;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class Main {
    public static ArrayList<Class> classes = new ArrayList<>();
    public static ArrayList<Account> accounts = new ArrayList<>();
    public static Schedule currentlyEditing = null;
    public static Search search;
    public static ArrayList<Class> searchResults = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        loadClassesFromJson("src/main/java/TODO/data_wolfe.json", classes);
        System.out.println(getSubjects());
        run();
    }

    public static void run() {
        Account currentAccount = null;
        Scanner scan = new Scanner(System.in);
        String input;

        while (true) {
            while (currentAccount == null) {
                System.out.println("Welcome!\nTo create an account: type 'create'\nTo login: type 'login'");
                input = scan.nextLine();

                if (input.equals("create")) {
                    createAccount(scan);
                } else if (input.equals("login")) {
                    currentAccount = login(scan);
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            }

            while (currentAccount != null) {
                System.out.println("Welcome " + currentAccount.getUsername() + "!");
                System.out.println("Home:\nTo view your schedules: type 'schedules'\nTo view account info: type 'account'\nTo logout: type 'logout'");
                input = scan.nextLine();

                if (input.equals("schedules")) {
                    manageSchedules(scan, currentAccount);
                } else if (input.equals("account")) {
                    // display account info
                    viewAccount(scan, currentAccount);
                } else if (input.equals("logout")) {
                    currentAccount = null;
                } else {
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
            System.out.println("To add a major: type 'add major'\nTo remove a major: type 'remove major'\nTo add a minor: type 'add minor'\nTo remove a minor: type 'remove minor'\nTo go back: type 'back'");

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
            } else if (input.equals("back")) {
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static void createAccount(Scanner scan) {
        System.out.println("Create an account\nEnter username: ");
        String username = scan.nextLine();
        System.out.println("Enter password: ");
        String password = scan.nextLine();
        System.out.println("Enter email: ");
        String email = scan.nextLine();
        accounts.add(new Account(username, password, email));
        System.out.println("Account created successfully");
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
            System.out.println("Home/Schedules:\nTo view a schedule: type 'view'\nTo create a new schedule: type 'create'\nTo edit a schedule: type 'edit'\nTo delete a schedule: type 'delete'\nTo go back: type 'back'");
            input = scan.nextLine();

            if (input.equals("view")) {
                viewSchedule(scan, currentAccount);
            } else if (input.equals("create")) {
                createSchedule(scan, currentAccount);
            } else if (input.equals("edit")) {
                editSchedule(scan, currentAccount);
            } else if (input.equals("delete")) {
                deleteSchedule(scan, currentAccount);
            } else if (input.equals("back")) {
                break;
            } else {
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
        for (Schedule schedule : currentAccount.schedules) {
            if (schedule.getName().equals(scheduleName)) {
                for (Class c : schedule.getClasses()) {
                    System.out.println(c);
                }
            }
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
            while (true) {
                System.out.println("Editing schedule: " + currentlyEditing.getName() +  "\n" + currentlyEditing.getClasses().toString() + "\nTo add a class: type 'add'\nTo remove a class: type 'remove'\nTo go back: type 'back'");
                input = scan.nextLine();

                if (input.equals("add")) {
                    addClassToSchedule(scan);
                } else if (input.equals("remove")) {
                    removeClassFromSchedule(scan);
                } else if (input.equals("back")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            }
        }
    }

    private static void addClassToSchedule(Scanner scan) {
        search = new Search();
        String input;
        while (true) {
            System.out.println("To search for classes: type 'search'\nTo add a class to the schedule: type 'add'\nTo go back: type 'back'");
            input = scan.nextLine();

            if (input.equals("search")) {
                searchClasses(scan);
            } else if (input.equals("add")) {
                System.out.println("Enter class name: ");
                String className = scan.nextLine();
                System.out.println("Enter class section: ");
                char section = scan.nextLine().charAt(0);
                System.out.println("Enter class semester: ");
                String semester = scan.nextLine();

                for (Class c : classes) {
                    if (c.getName().equals(className) && c.getSection() == section && c.getSemester().equals(semester)) {
                        currentlyEditing.addClass(c);
                        System.out.println("Class successfully added to schedule!");
                        return;
                    }
                }
                System.out.println("Class does not exist or cannot be added to schedule.");
            } else if (input.equals("back")) {
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static void searchClasses(Scanner scan) {
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

        System.out.println("Would you like to filter by subject? (y/n)");
        if (scan.nextLine().charAt(0) == 'y') {
            System.out.println("Enter subject name: ");
            String subject = scan.nextLine().toUpperCase();
            if (getSubjects().contains(subject)) {
                search.modifyFilter(new DepartmentFilter(subject));
            } else {
                System.out.println("Not a valid subject");
            }
        }

        searchResults = search.search(query, classes);
        for (Class c : searchResults) {
            System.out.println(c + "\n");
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
}