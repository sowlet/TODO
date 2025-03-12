package TODO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class Main {
    public static ArrayList<Class> classes = new ArrayList<>();
    public ArrayList<Account> accounts;
    public Schedule currentlyEditing;
    public Search search;

    public static void main(String[] args) throws FileNotFoundException {
        //TODO: Implement main
        loadClassesFromJson("src/main/java/TODO/data_wolfe.json", classes);
        for(Class c : classes){
            System.out.println(c.getName());
            System.out.println(new Gson().toJson(c));
        }
    }
    public static void run (ArrayList<Class> classes){
        //TODO: Implement run

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
