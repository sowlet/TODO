package TODO;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

class DateFilterTest {

    @Test
    void TRFilt() {
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


        Filter f = new DateFilter(Day.TR);
        ArrayList<Class> results = f.filt(sampleClasses);
        assertEquals(2,results.size());
        assertEquals("COST ACCOUNTING",results.get(0).getName());
        assertEquals("ADVANCED ACCOUNTING II", results.get(1).getName());
    }

    @Test
    void MWFFilt() {
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


        Filter f = new DateFilter(Day.MWF);
        ArrayList<Class> results = f.filt(sampleClasses);
        assertEquals(1,results.size());
        assertEquals("DATA ANALYTICS FOR ACCOUNTING",results.get(0).getName());
    }

    @Test
    void nightClassFilt() {
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

        // M only -> one day a week
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


        Filter f = new DateFilter(Day.MWF);
        ArrayList<Class> results = f.filt(sampleClasses);
        assertEquals(1,results.size());
        assertEquals("DATA ANALYTICS FOR ACCOUNTING",results.get(0).getName());
    }

    @Test
    void fourDaysMWFFilt() {
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

        //
        // MTWF
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
                "                    \"day\": \"T\",\n" +
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


        Filter f = new DateFilter(Day.MWF);
        ArrayList<Class> results = f.filt(sampleClasses);
        assertEquals(1,results.size());
        assertEquals("DATA ANALYTICS FOR ACCOUNTING",results.get(0).getName());
    }

    @Test
    void fourDaysTRFilt() {
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

        //
        // MTWF
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
                "                    \"day\": \"T\",\n" +
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


        Filter f = new DateFilter(Day.TR);
        ArrayList<Class> results = f.filt(sampleClasses);
        assertEquals(3,results.size());
        assertEquals("DATA ANALYTICS FOR ACCOUNTING",results.get(1).getName());
    }
}