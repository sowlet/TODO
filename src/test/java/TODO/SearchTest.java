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
    void searchWithFilters() {
        ArrayList<Class> sampleClasses = new ArrayList<>();

        // small poriton of JSON data
        // TR
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

        // MWF
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

        // TR
        String json3 = "{\n" +
                "            \"credits\": 3,\n" +
                "            \"faculty\": [\n" +
                "                \"Shultz, Tricia Michele\"\n" +
                "            ],\n" +
                "            \"is_lab\": false,\n" +
                "            \"is_open\": true,\n" +
                "            \"location\": \"SHAL 308\",\n" +
                "            \"name\": \"ADVANCED ACCOUNTING II\",\n" +
                "            \"number\": 402,\n" +
                "            \"open_seats\": 10,\n" +
                "            \"section\": \"A\",\n" +
                "            \"semester\": \"2023_Fall\",\n" +
                "            \"subject\": \"ACCT\",\n" +
                "            \"times\": [\n" +
                "                {\n" +
                "                    \"day\": \"T\",\n" +
                "                    \"end_time\": \"10:45:00\",\n" +
                "                    \"start_time\": \"09:30:00\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"day\": \"R\",\n" +
                "                    \"end_time\": \"10:45:00\",\n" +
                "                    \"start_time\": \"09:30:00\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"total_seats\": 25\n" +
                "        }";
        String[] js = {json1,json2,json3};
        for (String j: js) {
            sampleClasses.add(new Gson().fromJson(j,Class.class));
        }

        Search s = new Search();

        s.modifyFilter(new TimeFilter("08:00:00","10:00:00"));
        s.modifyFilter(new DepartmentFilter("ACCT"));
        s.modifyFilter(new DateFilter(Day.MWF));

        ArrayList<Class> results = s.search("",sampleClasses);

        assertEquals(1, results.size());
        assertEquals("DATA ANALYTICS FOR ACCOUNTING", results.get(0).getName());
    }

    @Test
    void modifyFilterDepartment() {
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
                "            \"subject\": \"HUMA\",\n" +
                "            \"times\": [],\n" +
                "            \"total_seats\": 0\n" +
                "        }";
        String[] js = {json1,json2,json3};
        for (String j: js) {
            sampleClasses.add(new Gson().fromJson(j,Class.class));
        }

        Search s = new Search();
        s.search("COURSE",sampleClasses);

        ArrayList<Class> results = s.modifyFilter(new DepartmentFilter("HUMA"));

        assertEquals(1, results.size());
        assertEquals("COURSE 3", results.get(0).getName());
    }

    @Test
    void modifyFilterDate() {
        ArrayList<Class> sampleClasses = new ArrayList<>();

        // small poriton of JSON data
        // TR
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

        // MWF
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

        // TR
        String json3 = "{\n" +
                "            \"credits\": 3,\n" +
                "            \"faculty\": [\n" +
                "                \"Shultz, Tricia Michele\"\n" +
                "            ],\n" +
                "            \"is_lab\": false,\n" +
                "            \"is_open\": true,\n" +
                "            \"location\": \"SHAL 308\",\n" +
                "            \"name\": \"ADVANCED ACCOUNTING II\",\n" +
                "            \"number\": 402,\n" +
                "            \"open_seats\": 10,\n" +
                "            \"section\": \"A\",\n" +
                "            \"semester\": \"2023_Fall\",\n" +
                "            \"subject\": \"ACCT\",\n" +
                "            \"times\": [\n" +
                "                {\n" +
                "                    \"day\": \"T\",\n" +
                "                    \"end_time\": \"10:45:00\",\n" +
                "                    \"start_time\": \"09:30:00\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"day\": \"R\",\n" +
                "                    \"end_time\": \"10:45:00\",\n" +
                "                    \"start_time\": \"09:30:00\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"total_seats\": 25\n" +
                "        }";
        String[] js = {json1,json2,json3};
        for (String j: js) {
            sampleClasses.add(new Gson().fromJson(j,Class.class));
        }

        Search s = new Search();
        s.search("",sampleClasses);

        ArrayList<Class> results = s.modifyFilter(new DateFilter(Day.MWF));

        assertEquals(1, results.size());
        assertEquals("DATA ANALYTICS FOR ACCOUNTING", results.get(0).getName());
    }

    @Test
    void modifyFilterTime() {
        ArrayList<Class> sampleClasses = new ArrayList<>();

        // small poriton of JSON data
        // TR
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

        // MWF
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

        // TR
        String json3 = "{\n" +
                "            \"credits\": 3,\n" +
                "            \"faculty\": [\n" +
                "                \"Shultz, Tricia Michele\"\n" +
                "            ],\n" +
                "            \"is_lab\": false,\n" +
                "            \"is_open\": true,\n" +
                "            \"location\": \"SHAL 308\",\n" +
                "            \"name\": \"ADVANCED ACCOUNTING II\",\n" +
                "            \"number\": 402,\n" +
                "            \"open_seats\": 10,\n" +
                "            \"section\": \"A\",\n" +
                "            \"semester\": \"2023_Fall\",\n" +
                "            \"subject\": \"ACCT\",\n" +
                "            \"times\": [\n" +
                "                {\n" +
                "                    \"day\": \"T\",\n" +
                "                    \"end_time\": \"10:45:00\",\n" +
                "                    \"start_time\": \"09:30:00\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"day\": \"R\",\n" +
                "                    \"end_time\": \"10:45:00\",\n" +
                "                    \"start_time\": \"09:30:00\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"total_seats\": 25\n" +
                "        }";
        String[] js = {json1,json2,json3};
        for (String j: js) {
            sampleClasses.add(new Gson().fromJson(j,Class.class));
        }

        Search s = new Search();
        s.search("",sampleClasses);

        ArrayList<Class> results = s.modifyFilter(new TimeFilter("08:00:00","10:00:00"));

        assertEquals(2, results.size());
        assertEquals("COST ACCOUNTING", results.get(0).getName());
        assertEquals("DATA ANALYTICS FOR ACCOUNTING", results.get(1).getName());
    }

    @Test
    void modifyFilterTimeAndDate() {
        ArrayList<Class> sampleClasses = new ArrayList<>();

        // small poriton of JSON data
        // TR
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

        // MWF
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

        // TR
        String json3 = "{\n" +
                "            \"credits\": 3,\n" +
                "            \"faculty\": [\n" +
                "                \"Shultz, Tricia Michele\"\n" +
                "            ],\n" +
                "            \"is_lab\": false,\n" +
                "            \"is_open\": true,\n" +
                "            \"location\": \"SHAL 308\",\n" +
                "            \"name\": \"ADVANCED ACCOUNTING II\",\n" +
                "            \"number\": 402,\n" +
                "            \"open_seats\": 10,\n" +
                "            \"section\": \"A\",\n" +
                "            \"semester\": \"2023_Fall\",\n" +
                "            \"subject\": \"ACCT\",\n" +
                "            \"times\": [\n" +
                "                {\n" +
                "                    \"day\": \"T\",\n" +
                "                    \"end_time\": \"10:45:00\",\n" +
                "                    \"start_time\": \"09:30:00\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"day\": \"R\",\n" +
                "                    \"end_time\": \"10:45:00\",\n" +
                "                    \"start_time\": \"09:30:00\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"total_seats\": 25\n" +
                "        }";
        String[] js = {json1,json2,json3};
        for (String j: js) {
            sampleClasses.add(new Gson().fromJson(j,Class.class));
        }

        Search s = new Search();
        s.search("",sampleClasses);

        s.modifyFilter(new TimeFilter("08:00:00","10:00:00"));
        ArrayList<Class> results = s.modifyFilter(new DateFilter(Day.MWF));

        assertEquals(1, results.size());
        assertEquals("DATA ANALYTICS FOR ACCOUNTING", results.get(0).getName());
    }

    @Test
    void modifyFilterTimeAndDateAndDep() {
        ArrayList<Class> sampleClasses = new ArrayList<>();

        // small poriton of JSON data
        // TR
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

        // MWF
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

        // TR
        String json3 = "{\n" +
                "            \"credits\": 3,\n" +
                "            \"faculty\": [\n" +
                "                \"Shultz, Tricia Michele\"\n" +
                "            ],\n" +
                "            \"is_lab\": false,\n" +
                "            \"is_open\": true,\n" +
                "            \"location\": \"SHAL 308\",\n" +
                "            \"name\": \"ADVANCED ACCOUNTING II\",\n" +
                "            \"number\": 402,\n" +
                "            \"open_seats\": 10,\n" +
                "            \"section\": \"A\",\n" +
                "            \"semester\": \"2023_Fall\",\n" +
                "            \"subject\": \"ACCT\",\n" +
                "            \"times\": [\n" +
                "                {\n" +
                "                    \"day\": \"T\",\n" +
                "                    \"end_time\": \"10:45:00\",\n" +
                "                    \"start_time\": \"09:30:00\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"day\": \"R\",\n" +
                "                    \"end_time\": \"10:45:00\",\n" +
                "                    \"start_time\": \"09:30:00\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"total_seats\": 25\n" +
                "        }";
        String[] js = {json1,json2,json3};
        for (String j: js) {
            sampleClasses.add(new Gson().fromJson(j,Class.class));
        }

        Search s = new Search();
        s.search("",sampleClasses);

        s.modifyFilter(new TimeFilter("08:00:00","10:00:00"));
        s.modifyFilter(new DepartmentFilter("COMP"));
        ArrayList<Class> results = s.modifyFilter(new DateFilter(Day.MWF));

        assertEquals(0, results.size());
    }

    @Test
    void modifyFilterAllThreePlusMod() {
        ArrayList<Class> sampleClasses = new ArrayList<>();

        // small poriton of JSON data
        // TR
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

        // MWF
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

        // TR
        String json3 = "{\n" +
                "            \"credits\": 3,\n" +
                "            \"faculty\": [\n" +
                "                \"Shultz, Tricia Michele\"\n" +
                "            ],\n" +
                "            \"is_lab\": false,\n" +
                "            \"is_open\": true,\n" +
                "            \"location\": \"SHAL 308\",\n" +
                "            \"name\": \"ADVANCED ACCOUNTING II\",\n" +
                "            \"number\": 402,\n" +
                "            \"open_seats\": 10,\n" +
                "            \"section\": \"A\",\n" +
                "            \"semester\": \"2023_Fall\",\n" +
                "            \"subject\": \"ACCT\",\n" +
                "            \"times\": [\n" +
                "                {\n" +
                "                    \"day\": \"T\",\n" +
                "                    \"end_time\": \"10:45:00\",\n" +
                "                    \"start_time\": \"09:30:00\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"day\": \"R\",\n" +
                "                    \"end_time\": \"10:45:00\",\n" +
                "                    \"start_time\": \"09:30:00\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"total_seats\": 25\n" +
                "        }";
        String[] js = {json1,json2,json3};
        for (String j: js) {
            sampleClasses.add(new Gson().fromJson(j,Class.class));
        }

        Search s = new Search();
        s.search("",sampleClasses);

        s.modifyFilter(new TimeFilter("08:00:00","10:00:00"));
        s.modifyFilter(new DepartmentFilter("COMP"));
        s.modifyFilter(new DepartmentFilter("ACCT"));
        ArrayList<Class> results = s.modifyFilter(new DateFilter(Day.MWF));

        assertEquals(1, results.size());
        assertEquals("DATA ANALYTICS FOR ACCOUNTING", results.get(0).getName());
    }

    @Test
    void removeFilter() {

        ArrayList<Class> sampleClasses = new ArrayList<>();

        // small poriton of JSON data
        // TR
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

        // MWF
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

        // TR
        String json3 = "{\n" +
                "            \"credits\": 3,\n" +
                "            \"faculty\": [\n" +
                "                \"Shultz, Tricia Michele\"\n" +
                "            ],\n" +
                "            \"is_lab\": false,\n" +
                "            \"is_open\": true,\n" +
                "            \"location\": \"SHAL 308\",\n" +
                "            \"name\": \"ADVANCED ACCOUNTING II\",\n" +
                "            \"number\": 402,\n" +
                "            \"open_seats\": 10,\n" +
                "            \"section\": \"A\",\n" +
                "            \"semester\": \"2023_Fall\",\n" +
                "            \"subject\": \"ACCT\",\n" +
                "            \"times\": [\n" +
                "                {\n" +
                "                    \"day\": \"T\",\n" +
                "                    \"end_time\": \"10:45:00\",\n" +
                "                    \"start_time\": \"09:30:00\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"day\": \"R\",\n" +
                "                    \"end_time\": \"10:45:00\",\n" +
                "                    \"start_time\": \"09:30:00\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"total_seats\": 25\n" +
                "        }";
        String[] js = {json1,json2,json3};
        for (String j: js) {
            sampleClasses.add(new Gson().fromJson(j,Class.class));
        }

        Search s = new Search();
        s.search("",sampleClasses);

        s.modifyFilter(new DepartmentFilter("COMP"));
        s.modifyFilter(new TimeFilter("08:00:00","10:00:00"));
        ArrayList<Class> results = s.modifyFilter(new DateFilter(Day.MWF));

        assertEquals(0, results.size());

        results = s.removeFilter(new DepartmentFilter("Whatever!"));
        assertEquals(1, results.size());

        results = s.removeFilter(new DateFilter(Day.TR)); // can be anything
        assertEquals(2,results.size());

        results = s.removeFilter(new TimeFilter("whatever!", "whateva")); // just needs to be of the right type
        assertEquals(3,results.size());
    }
}