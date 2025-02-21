package TODO;

import java.util.ArrayList;

public class Search {
    // Variables
    private ArrayList<Class> searchResults;
    private String query;
    private ArrayList<Filter> filters;
    private ArrayList<Class> filterResults;

    // Constructor
    public Search(){
        searchResults = new ArrayList<>();
        query = "";
        filters= new ArrayList<>();
        filterResults = new ArrayList<>();
    }

    // Methods
    public ArrayList<Class> search(String query) {
        //TODO: Implement search
        return null;
    }

    public ArrayList<Class> modifyQuery(String query) {
        //TODO: Implement modifyQuery
        return null;
    }

    public ArrayList<Class> modifyFilter(Filter filter){
        //TODO: Implement modifyFilter
        return null;
    }

}
