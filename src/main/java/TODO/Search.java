package TODO;

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
    public ArrayList<Class> search(String query, ArrayList<Class> classes) {
        this.query = query;

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



    /* redundant method -> search can be used to modify the query
    public ArrayList<Class> modifyQuery(String query, ArrayList<Class> classes) {
        //TODO: Implement modifyQuery
        return null;
    }*/



    /*
    adds Filter object to filters ArrayList, replacing Filter subclasses if there are duplicate types

    @param filter the Filter object to be added
    @return new results with updated Filters
     */
    public ArrayList<Class> modifyFilter(Filter filter){
        //TODO: Implement modifyFilter
        return null;
    }

}
