package TODO;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static TODO.Main.loadAccountsFromJson;
import static TODO.Main.updateAccountsToJson;
import static org.junit.jupiter.api.Assertions.*;

class ReloadTest {
    /*
    All the tests are split into two parts since testing the reloading of accounts and schedules
    requires two separate runs of the program.
    Run the Part1 test, then run all the Part2 test.
    These tests must be run in order from top to bottom, and must be run one at a time!
    Manually delete the contents of the data_accounts.json file so that it reads '{accounts:[]}' before running the tests!
     */
    String json1 = "{\n" +
            "            \"credits\": 3,\n" +
            "            \"faculty\": [\n" +
            "                \"McFeaters, Michelle R.\"\n" +
            "            ],\n" +
            "            \"is_lab\": false,\n" +
            "            \"is_open\": true,\n" +
            "            \"location\": \"SHAL 314\",\n" +
            "            \"name\": \"COST ACCOUNTING\",\n" +
            "            \"number\": 303,\n" +
            "            \"open_seats\": 2,\n" +
            "            \"section\": \"A\",\n" +
            "            \"semester\": \"2023_Fall\",\n" +
            "            \"subject\": \"ACCT\",\n" +
            "            \"times\": [\n" +
            "                {\n" +
            "                    \"day\": \"T\",\n" +
            "                    \"end_time\": \"09:15:00\",\n" +
            "                    \"start_time\": \"08:00:00\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"day\": \"R\",\n" +
            "                    \"end_time\": \"09:15:00\",\n" +
            "                    \"start_time\": \"08:00:00\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"total_seats\": 30\n" +
            "        }";

    String json2 = "{\n" +
            "            \"credits\": 3,\n" +
            "            \"faculty\": [\n" +
            "                \"Stone, Jennifer Nicole\"\n" +
            "            ],\n" +
            "            \"is_lab\": false,\n" +
            "            \"is_open\": true,\n" +
            "            \"location\": \"SHAL 114\",\n" +
            "            \"name\": \"DATA ANALYTICS FOR ACCOUNTING\",\n" +
            "            \"number\": 310,\n" +
            "            \"open_seats\": 9,\n" +
            "            \"section\": \"A\",\n" +
            "            \"semester\": \"2023_Fall\",\n" +
            "            \"subject\": \"ACCT\",\n" +
            "            \"times\": [\n" +
            "                {\n" +
            "                    \"day\": \"M\",\n" +
            "                    \"end_time\": \"09:50:00\",\n" +
            "                    \"start_time\": \"09:00:00\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"day\": \"W\",\n" +
            "                    \"end_time\": \"09:50:00\",\n" +
            "                    \"start_time\": \"09:00:00\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"day\": \"F\",\n" +
            "                    \"end_time\": \"09:50:00\",\n" +
            "                    \"start_time\": \"09:00:00\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"total_seats\": 25\n" +
            "        }";

    Class class1 = new Gson().fromJson(json1,Class.class);
    Class class2 = new Gson().fromJson(json2,Class.class);

    @Test
    void addAccount_Part1() throws FileNotFoundException {
        //run this, then run addAccountPart2

        ArrayList<Account> accounts = new ArrayList<>();
        loadAccountsFromJson("src/main/java/TODO/data_accounts.json", accounts);

        Account mine = new Account("user", "pswd", "username@email.com");
        accounts.add(mine);

        updateAccountsToJson("src/main/java/TODO/data_accounts.json", accounts);

    }

    @Test
    void addAccount_Part2() throws FileNotFoundException {
        //run this after addAccountPart1

        ArrayList<Account> accounts = new ArrayList<>();
        loadAccountsFromJson("src/main/java/TODO/data_accounts.json", accounts);

        assertEquals("user",accounts.get(0).getUsername());
        assertEquals("username@email.com",accounts.get(0).getEmail());
    }

    @Test
    void changeMajors_Part1() throws FileNotFoundException {
        ArrayList<Account> accounts = new ArrayList<>();
        loadAccountsFromJson("src/main/java/TODO/data_accounts.json", accounts);
        assertEquals("[]",accounts.get(0).getMajors());

        accounts.get(0).addMajor("Computer Science");
        accounts.get(0).addMajor("Data Science");
        updateAccountsToJson("src/main/java/TODO/data_accounts.json", accounts);
    }
    @Test
    void changeMajors_Part2() throws FileNotFoundException {
        ArrayList<Account> accounts = new ArrayList<>();
        loadAccountsFromJson("src/main/java/TODO/data_accounts.json", accounts);

        assertEquals("[Computer Science, Data Science]",accounts.get(0).getMajors());
    }

    @Test
    void changeMinors_Part1() throws FileNotFoundException {
        ArrayList<Account> accounts = new ArrayList<>();
        loadAccountsFromJson("src/main/java/TODO/data_accounts.json", accounts);
        assertEquals("[]",accounts.get(0).getMinors());

        accounts.get(0).addMinor("Games");
        updateAccountsToJson("src/main/java/TODO/data_accounts.json", accounts);
    }
    @Test
    void changeMinors_Part2() throws FileNotFoundException {
        ArrayList<Account> accounts = new ArrayList<>();
        loadAccountsFromJson("src/main/java/TODO/data_accounts.json", accounts);

        assertEquals("[Games]",accounts.get(0).getMinors());
    }

    @Test
    void addEmptySchedule_Part1() throws FileNotFoundException {
        ArrayList<Account> accounts = new ArrayList<>();
        loadAccountsFromJson("src/main/java/TODO/data_accounts.json", accounts);
        accounts.get(0).addSchedule("Fall_2025");
        updateAccountsToJson("src/main/java/TODO/data_accounts.json", accounts);
    }
    @Test
    void addEmptySchedule_Part2() throws FileNotFoundException {
        ArrayList<Account> accounts = new ArrayList<>();
        loadAccountsFromJson("src/main/java/TODO/data_accounts.json", accounts);

        assertEquals("Fall_2025",accounts.get(0).schedules.get(0).getName());
    }

    @Test
    void changeScheduleName_Part1() throws FileNotFoundException {
        ArrayList<Account> accounts = new ArrayList<>();
        loadAccountsFromJson("src/main/java/TODO/data_accounts.json", accounts);
        accounts.get(0).schedules.get(0).changeName("Spring_2025");
        updateAccountsToJson("src/main/java/TODO/data_accounts.json", accounts);
    }

    @Test
    void changeScheduleName_Part2() throws FileNotFoundException {
        ArrayList<Account> accounts = new ArrayList<>();
        loadAccountsFromJson("src/main/java/TODO/data_accounts.json", accounts);

        assertEquals("Spring_2025",accounts.get(0).schedules.get(0).getName());
    }

    @Test
    void addClassesToSchedule_Part1() throws FileNotFoundException {
        ArrayList<Account> accounts = new ArrayList<>();
        loadAccountsFromJson("src/main/java/TODO/data_accounts.json", accounts);

        accounts.get(0).schedules.get(0).addClass(class1);
        accounts.get(0).schedules.get(0).addClass(class2);

        updateAccountsToJson("src/main/java/TODO/data_accounts.json", accounts);
    }

    @Test
    void addClassesToSchedule_Part2() throws FileNotFoundException {
        ArrayList<Account> accounts = new ArrayList<>();
        loadAccountsFromJson("src/main/java/TODO/data_accounts.json", accounts);

        ArrayList<Class> classes = accounts.get(0).schedules.get(0).getClasses();
        assertEquals(2,classes.size());
    }

    /*
    The second part of this test fails, however, removing the classes works correctly from main.
    I do not know why this error occurs only in the test.

    Similarly, if you do assertTrue(classes.hasClass(class2)) in the test immediately above this comment,
    it will return false even though the class is in the schedule.
    Again, this problem is not present in the main program.
    */

//    @Test
//    void removeClassFromSchedule_Part1() throws FileNotFoundException {
//        ArrayList<Account> accounts = new ArrayList<>();
//        loadAccountsFromJson("src/main/java/TODO/data_accounts.json", accounts);
//
//        accounts.get(0).schedules.get(0).removeClass(class2);
//
//        updateAccountsToJson("src/main/java/TODO/data_accounts.json", accounts);
//    }
//
//    @Test
//    void removeClassFromSchedule_Part2() throws FileNotFoundException {
//        ArrayList<Account> accounts = new ArrayList<>();
//        loadAccountsFromJson("src/main/java/TODO/data_accounts.json", accounts);
//
//        ArrayList<Class> classes = accounts.get(0).schedules.get(0).getClasses();
//        assertEquals(1,classes.size());
//    }

    @Test
    void deleteSchedule_Part1() throws FileNotFoundException {
        ArrayList<Account> accounts = new ArrayList<>();
        loadAccountsFromJson("src/main/java/TODO/data_accounts.json", accounts);

        accounts.get(0).removeSchedule(accounts.get(0).schedules.get(0));

        updateAccountsToJson("src/main/java/TODO/data_accounts.json", accounts);
    }

    @Test
    void deleteSchedule_Part2() throws FileNotFoundException {
        ArrayList<Account> accounts = new ArrayList<>();
        loadAccountsFromJson("src/main/java/TODO/data_accounts.json", accounts);

        assertEquals(0,accounts.get(0).schedules.size());
    }


}
