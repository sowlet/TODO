package TODO;
//import io.javalin.Javalin;
//import io.javalin.http.Context;
import java.util.ArrayList;

public class Search {
    private ArrayList<Class> searchResults;
    private String query;
    private ArrayList<Filter> filters;
    private ArrayList<Class> filterResults;



    // Class constructor
    public Search(){
        searchResults = new ArrayList<>();
        query = "";
        filters= new ArrayList<>();
        filterResults = new ArrayList<>();
    }


    /*
    searches through classes and returns an ArrayList of results based on query and stored filters

    @param query the name of the class requested
    @param classes the global ArrayList of classes
    @return an ArrayList of matching classes that pass the stored filters
     */
//    public ArrayList<Class> search(String query, ArrayList<Class> classes) {
//        this.query = query.toUpperCase();
//
//        for (Class c: classes) {
//            if (c.getName().contains(query)) {
//                searchResults.add(c);
//            }
//        }
//
//        filterResults = searchResults;
//
//        for (Filter f: filters) {
//            filterResults = f.filt(filterResults);
//        }
//
//        return filterResults;
//    }

    public ArrayList<Class> search(String query, ArrayList<Class> classes) {
        this.query = query.toUpperCase();

        for (Class c: classes) {
            if (c.getName().contains(query)) {
                searchResults.add(c);
            }
        }

        filterResults = searchResults;

        for (Filter f: filters) {
            filterResults = f.filt(filterResults);
        }

        return filterResults;
    }



    /* redundant method -> use search to modify the query
    public ArrayList<Class> modifyQuery(String query, ArrayList<Class> classes) {
        //TODO: Implement modifyQuery
        return null;
    }*/



    /*
    adds Filter object to filters ArrayList, replacing a current filter if there are duplicate types

    @param filter the Filter object to be added
    @return new results with updated Filters
     */
    public ArrayList<Class> modifyFilter(Filter filter){
        boolean added = false;

        for (int i = 0; i < filters.size(); i++) { // go through filters
            if (filter.getClass().equals(filters.get(i).getClass())) { // find duplicate types
                filters.remove(filters.get(i)); // remove old filter
                filters.add(filter); // store new filter
                added = true;
            }
        }

        if (!added) {
            filters.add(filter);
        }

        filterResults = searchResults;

        for (Filter f: filters) {
            filterResults = f.filt(filterResults);
        }

        return filterResults;
    }

    /*
    removes a filter specified by the type of the Filter object param if it is present

    @param filter object specifying the Filter type to be removed
    @return List of results without the removed filter object if it was removed
     */
    public ArrayList<Class> removeFilter(Filter filter) {
        for (int i = 0; i < filters.size(); i++) { // go through filters
            if (filter.getClass().equals(filters.get(i).getClass())) { // find duplicate types
                filters.remove(filters.get(i)); // remove old filter
            }
        }

        filterResults = searchResults;

        for (Filter f: filters) {
            filterResults = f.filt(filterResults);
        }

        return filterResults;
    }
}
