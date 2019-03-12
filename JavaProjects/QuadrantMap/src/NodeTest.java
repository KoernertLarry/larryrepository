import java.util.ArrayList;

/**
 * 
 * 
 * 
 */
public class NodeTest extends junit.framework.TestCase {
    
    private Point p1;
    private Point p2;
    private ArrayList<String> s1;
    private ArrayList<String> s2;
    private Node<Point> n1;
    private Node<Point> n2;
    
    /**
     * Constructor
     * efficiency: O(1)
     */
    public NodeTest() {
        //empty intentionally for tests
    }
    
    /**
     * Runs before every test
     * Uses constructors
     * efficiency: O(1)
     */
    public void setUp() {
        p1 = new Point(1,3);
        p2 = new Point(0,0);
        s1 = new ArrayList<String>();
        s1.add("Courthouse");
        s1.add("Apartments");
        s2 = new ArrayList<String>();
        n1 = new Node<Point>(p1, s1);
        n2 = new Node<Point>(p2, s2);
    }
    
    /**
     * Test method for getData
     * efficiency: O(1)
     */
    public void testGetData() {
        assertEquals(p1, n1.getData());
    }
    
    /**
     * Test method for getList
     * efficiency: O(1)
     */
    public void testGetList() {
        assertEquals(s1, n1.getList());
    }
    
    /**
     * Test method for setData
     * efficiency: O(1)
     */
    public void testSetData() {
        assertEquals(p1, n1.getData());
        n1.setData(p2);
        assertEquals(p2, n1.getData());
        n1.setData(null);
        assertEquals(null, n1.getData());
    }
    
    /**
     * Test method for setList
     * efficiency: O(1)
     */
    public void testSetList() {
        assertEquals(s1, n1.getList());
        n1.setList(s2);
        assertEquals(s2, n1.getList());
        n1.setList(null);
        assertEquals(null, n1.getList());
    }
    
    
    /**
     * Test method for toString
     * efficiency: O(n)
     */
    public void testToString() {
        String shouldBe = p1.toString() + ", " + "Courthouse, Apartments";
        assertEquals(shouldBe, n1.toString());
        n1.setList(s2);
        shouldBe = p1.toString() + ", There is nothing here.";
        assertEquals(shouldBe, n1.toString());
        
    }

        


    /**
     * Unit test for getDistance()
     */
    public void testGetDistance() {
    	assertEquals(0, n1.getDistance(), 0.01);
    	n1.setDistance(10);
    	assertEquals(10, n1.getDistance(), 0.01);
    }

    /**
     * Unit test for setDistance()
     */
    public void testSetDistance() {
    	n1.setDistance(99);
    	assertEquals(99, n1.getDistance(), 0.01);
    }
    
    /**
     * Unit test fo compareTo()
     */
    public void testCompareTo() {
    	assertEquals(0, n1.compareTo(n2), 0.01);
    	n1.setDistance(100);
    	n2.setDistance(90);
    	assertEquals(10, n1.compareTo(n2), 0.01); 
    }
    
      
  

}

