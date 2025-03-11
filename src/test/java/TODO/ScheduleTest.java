package TODO;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleTest {

    @Test
    void getName() {
        Schedule s = new Schedule("Test");
        //Test to fail. Does fail when I run gradle build.
        //assertEquals("SomethingElse", s.getName());
        //Test to pass
        assertEquals("Test", s.getName());
    }

    @Test
    void changeName() {
        Schedule s = new Schedule("Test");
        s.changeName("Changed");
        //Test to fail. Does fail when I run gradle build.
        //assertEquals("Test", s.getName());
        //Test to pass.
        assertEquals("Changed", s.getName());
    }

    @Test
    void basicAddClass() {
        Schedule s = new Schedule("Test");
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

        Class c = new Gson().fromJson(json1,Class.class);
        s.addClass(c);
        ArrayList<Class> classes = s.getClasses();
        assertEquals(1,classes.size());
        assertEquals(c,classes.get(0));
    }

    @Test
    void basicRemoveClass() {
        Schedule s = new Schedule("Test");
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

        Class c = new Gson().fromJson(json1,Class.class);
        s.addClass(c);
        s.removeClass(c);
        ArrayList<Class> classes = s.getClasses();
        assertEquals(0,classes.size());
    }

    @Test
    void hasClass() {
        Schedule s = new Schedule("Test");
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

        Class c = new Gson().fromJson(json1,Class.class);
        s.addClass(c);
        assertEquals(true, s.hasClass(c));
    }

    @Test
    void notHasClass() {
        Schedule s = new Schedule("Test");
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

        Class c = new Gson().fromJson(json1,Class.class);
        s.addClass(c);
        s.removeClass(c);
        assertEquals(false, s.hasClass(c));
    }

    @Test
    void hasTimeConflict() {
    }
}