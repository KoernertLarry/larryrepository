import java.util.ArrayList;

/**
 * @author Olive Krupinska
 * @author Allen Chan
 * @author Lawrence Koerner
 *
 */
public class StreetNodes implements Comparable<StreetNodes> {
    
    private String sName;
    private ArrayList<Node<Point>> sLocations;
    
    //constructor
    /**
     * @param street
     */
    public StreetNodes(String street) {
        this.setsName(street);
        sLocations = new ArrayList<Node<Point>> (5) ;
        
    }
    
    //adds a node to the list of nodes 
    /**
     * @param n inserted node
     * @return it had been added
     * efficiency O(1)
     */
    public boolean addPoint(Node<Point> n) {
        this.getsLocations().add(n);
        return true;
    }


        
    //toString method 
    /**
     * @return string with name and locations
     * efficiency: O(n) 
     */
    public String toString() {
        String build = sName + ": ";
        if(sLocations.isEmpty()) {
            return build + ", There is nothing here.";
        }
        for(int i = 0; i < sLocations.size()-1; i++) {
            build +=  sLocations.get(i) + ", ";
        }
        return build +=sLocations.get( sLocations.size()-1);
        
    }
    
    
    /**
     * compares the street names 
     * @param o object being compared
     * @return an int
     * efficiency: O(1)
     * 
     */
    @Override
    public int compareTo(StreetNodes o) {
        return this.sName.compareTo(o.sName);  
    }

    /**
     * @return node list
     * efficiency: O(1)
     */
    public ArrayList<Node<Point>> getsLocations() {
        return sLocations;
    }

    /**
     * @param sLocations locations
     * efficiency: O(1)
     */
    public void setsLocations(ArrayList<Node<Point>> sLocations) {
        this.sLocations = sLocations;
    }

    /**
     * @return name
     * efficiency: O(1)
     */
    public String getsName() {
        return sName;
    }

    /**
     * @param sName name
     * efficiency O(1)
     */
    public void setsName(String sName) {
        this.sName = sName;
    }
}


