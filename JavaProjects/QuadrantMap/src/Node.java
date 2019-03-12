import java.util.ArrayList;

/**
 * Represents a node with 
 * a generic data field 
 * and a list of Strings
 * 
 * @author Lawrence Koerner
 * Contribution: 33.3%
 * @author Oliwia Krupinska
 * Contribution: 33.3%
 * @author Allen Chan
 * Contribution: 33.3%
 * @version 2018.04.17
 * @param <T> 
 */
public class Node<T> implements Comparable<Node<T>> {
    /**
     * variable that holds the x and y coordinates
     */
    private T data;
    
    private double distance;
    /**
     * list that holds description of places
     */
    private ArrayList<String> list;
    private ArrayList<String> streets;
    
    /**
     * Constructor for Node
     * @param data the x and y coordinates of the point
     * @param list the names of places that can be found here
     * @param distance distance to new place
     * 
     * efficiency: O(1)
     */
    public Node(T data, ArrayList<String> list){
        this.data = data;
        this.list = list;
        distance=0;
        streets= new ArrayList<String>();
    }
    
    /**
     * Get the data
     * @return the current data
     * efficiency: O(1)
     */
    public T getData() {
        return data;
    }
    
    /**
     * Get the list
     * @return the current list
     * efficiency: O(1)
     */
    public ArrayList<String> getList(){
        return list;
    }
    
    /**
     * Get the list of streets
     * @return the current list
     * efficiency: O(1)
     */
    public ArrayList<String> getStreets(){
        return streets;
    }
    
    /**
     * Change the value of data
     * @param data is the new data
     * efficiency: O(1)
     */
    public void setData(T data) {
        this.data = data;
    }
    
    /**
     * Change the value of list
     * @param list is the new list
     * efficiency: O(1)
     */
    public void setList(ArrayList<String> list) {
        this.list = list;
    }
    
    /**
     * Converts the node to a readable string
     * @return A string containing the data and list
     * efficiency: O(n)
     */
    public String toString() {
        String build = data.toString();
        if(list.isEmpty()) {
            return build + ", There is nothing here.";
        }
        for(int i = 0; i < list.size(); i++) {
            build += ", " + list.get(i);
        }
        return build;
    }

    /**
     * @return the distance
     * efficiency: O(1)
     */
    public double getDistance() {
        return distance;
    }

    /**
     * @param distance the distance
     * efficiency: O(1)
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    /**
     * @param o object being compared
     * @return an int
     * efficiency: O(1)
     */
    public int compareTo(Node<T> o) {
        return (int)(this.getDistance() - o.getDistance());
    }
   
}


