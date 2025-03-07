package TODO;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentFilterTest {
    @Test
    void basicFilt() {
        ArrayList<Class> sampleClasses = new ArrayList<>();

        // dummy data -> parody of json

        // course 1 -> accounting
        String json1 = "{\n" +
                "            \"credits\": 0,\n" +
                "            \"faculty\": [\n" +
                "                \"Inman, John G.\"\n" +
                "            ],\n" +
                "            \"is_lab\": false,\n" +
                "            \"is_open\": false,\n" +
                "            \"location\": \"Off Campus Course\",\n" +
                "            \"name\": \"COURSE 1\",\n" +
                "            \"number\": 300,\n" +
                "            \"open_seats\": 0,\n" +
                "            \"section\": \"A\",\n" +
                "            \"semester\": \"2023_Fall\",\n" +
                "            \"subject\": \"ACCT\",\n" +
                "            \"times\": [],\n" +
                "            \"total_seats\": 0\n" +
                "        }";

        // course 2 -> computer science
        String json2 = "{\n" +
                "            \"credits\": 0,\n" +
                "            \"faculty\": [\n" +
                "                \"Inman, John G.\"\n" +
                "            ],\n" +
                "            \"is_lab\": false,\n" +
                "            \"is_open\": false,\n" +
                "            \"location\": \"Off Campus Course\",\n" +
                "            \"name\": \"COURSE 2\",\n" +
                "            \"number\": 300,\n" +
                "            \"open_seats\": 0,\n" +
                "            \"section\": \"A\",\n" +
                "            \"semester\": \"2023_Fall\",\n" +
                "            \"subject\": \"COMP\",\n" +
                "            \"times\": [],\n" +
                "            \"total_seats\": 0\n" +
                "        }";

        // course 3 -> also computer science
        String json3 = "{\n" +
                "            \"credits\": 0,\n" +
                "            \"faculty\": [\n" +
                "                \"Inman, John G.\"\n" +
                "            ],\n" +
                "            \"is_lab\": false,\n" +
                "            \"is_open\": false,\n" +
                "            \"location\": \"off Campus Course\",\n" +
                "            \"name\": \"COURSE 3\",\n" +
                "            \"number\": 300,\n" +
                "            \"open_seats\": 0,\n" +
                "            \"section\": \"A\",\n" +
                "            \"semester\": \"2023_Fall\",\n" +
                "            \"subject\": \"COMP\",\n" +
                "            \"times\": [],\n" +
                "            \"total_seats\": 0\n" +
                "        }";
        String[] js = {json1,json2,json3};
        for (String j: js) {
            sampleClasses.add(new Gson().fromJson(j,Class.class));
        }

        Filter f = new DepartmentFilter("COMP");
        ArrayList<Class> results = f.filt(sampleClasses);

        assertEquals(2, results.size());
    }
}