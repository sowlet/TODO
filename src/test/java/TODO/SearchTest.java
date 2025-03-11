package TODO;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class SearchTest {
    ArrayList<Class> sampleClasses;

    @Test
    void basicSearch() {
        ArrayList<Class> sampleClasses = new ArrayList<>();

        // dummy parody data named -> Course 1, Course 2, Course 3
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
                "            \"subject\": \"ABRD\",\n" +
                "            \"times\": [],\n" +
                "            \"total_seats\": 0\n" +
                "        }";
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
                "            \"subject\": \"ABRD\",\n" +
                "            \"times\": [],\n" +
                "            \"total_seats\": 0\n" +
                "        }";
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
                "            \"subject\": \"ABRD\",\n" +
                "            \"times\": [],\n" +
                "            \"total_seats\": 0\n" +
                "        }";
        String[] js = {json1,json2,json3};
        for (String j: js) {
            sampleClasses.add(new Gson().fromJson(j,Class.class));
        }

        Search s = new Search();
        ArrayList<Class> results = s.search("COURSE 1",sampleClasses);

        assertEquals(1, results.size());
        assertEquals("COURSE 1", results.get(0).getName());
    }

    @Test
    void modifyFilter() {
    }
}