import java.util.ArrayList;

import junit.framework.TestCase;

public class StreetNodesTest extends TestCase{
    /**
     * Data Fields
     */
    private StreetNodes sN;
    private StreetNodes sN2;
    private Node<Point> n1;
    private Point p1;
    private ArrayList<String> s1;
    private String streetName1;
    private String streetName2;
    private ArrayList<Node<Point>> location1;
    private ArrayList<Node<Point>> location2;
    
    /**
     * Constructor for StreetNodesTest
     */
    public StreetNodesTest() {
        //empty
    }
    
    /**
     * Initializes variables
     */
    public void setUp() {
        streetName1 = "W Packer";
        streetName2 = "East 5th";
        sN = new StreetNodes(streetName1);
        location1 = null;
        p1 = new Point (0, 0);
        s1 = new ArrayList<String>();
        s1.add("FML");
        n1 = new Node<Point>(p1, s1);
        sN2 = new StreetNodes(streetName2);
        
    }
    
    /**
     * Unit test for getsName()
     */
    public void testGetsName() {
        assertEquals("W Packer", sN.getsName().toString());
        streetName1 = "Asa Packer";
        sN = new StreetNodes(streetName1);
        assertEquals("Asa Packer", sN.getsName().toString());
    }
    
    /**
     * Unit test for setsName()
     */
    public void testSetsName() {
        sN.setsName(streetName2);
        assertEquals("East 5th", sN.getsName().toString());
    }
    
    /**
     * Unit test for getsLocation()
     */
    public void testGetsLocation() {
        assertEquals("[]", sN.getsLocations().toString());
        location2 = null;
        sN.setsLocations(location2);
        assertEquals(location2, sN.getsLocations());
        
    }
    
    /**
     * Unit test for addPoint()
     */
    public void testAddPoint() {
        System.out.print(n1.getList().toString());
        sN.addPoint(n1);
        assertEquals("[(0, 0), FML]", sN.getsLocations().toString());
    }
    
    /**
     * Unit test for comapareTo()
     */
    public void testCompareTo() {
        assertEquals(0, sN.compareTo(sN));
        assertEquals(18, sN.compareTo(sN2));
        assertEquals(-18, sN2.compareTo(sN));
        
    }

}

