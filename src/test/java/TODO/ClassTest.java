package TODO;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ClassTest {

    @Test
    void giveValidRatings() {
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


        assertEquals(0,c.getRating());
        assertEquals(0, c.giveRating(5));
        assertEquals(0, c.giveRating(4));
        assertEquals(0, c.giveRating(1));
        assertEquals(0, c.giveRating(3));
        assertEquals(3.25,c.getRating());
    }

    @Test
    void giveInvalidRatings() {
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


        assertEquals(0,c.getRating());
        assertEquals(-1, c.giveRating(100));
        assertEquals(-1, c.giveRating(-3));
        assertEquals(-1, c.giveRating(-1));
        assertEquals(-1, c.giveRating(5.1));
        assertEquals(0,c.getRating());
    }

    @Test
    void giveComboRating() {
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


        assertEquals(0,c.getRating());
        assertEquals(0, c.giveRating(5));
        assertEquals(0, c.giveRating(4));
        assertEquals(-1, c.giveRating(5.000001));
        assertEquals(-1, c.giveRating(-0.00001));
        assertEquals(4.5,c.getRating());
    }


    @Test
    void endsInOtherConflict() {
    }

    @Test
    void startInOtherConflict() {
    }

    @Test
    void noConflict() {
    }
}