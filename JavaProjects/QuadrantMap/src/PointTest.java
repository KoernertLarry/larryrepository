import junit.framework.TestCase;

/**
 * @author LawrenceKoerner
 * @version 2018.04.18
 */
public class PointTest extends TestCase {
    
    private int x1;
    private int y1;
    private Point point1;

    /**
     * Constructor for PointTest()
     */
    public PointTest() {
        //Empty
    }
    
    public void setUp() {
        x1 = 1;
        y1 = 2;
        point1 = new Point(x1, y1);
        
    }
    
    /**
     * Unit test for getX()
     */
    public void testGetX() {
        assertEquals(1, point1.getX());
        
    }
    
    /**
     * Unit test for getY()
     */
    public void testGetY() {
        assertEquals(2, point1.getY());
    }
    
    /**
     * Unit test for setX()
     */
    public void testSetX() {
        point1.setX(2);
        assertEquals(2, point1.getX());
    }
    
    /**
     * Unit test for setY()
     */
    public void testSetY() {
        point1.setY(1);
        assertEquals(1, point1.getY());
    }
    
    /**
     * Unit test for equals()
     */
    public void testEquals() {
        Point point2 = new Point(1, 2);
        assertTrue(point2.equals(point1));
        
    }
    
    /**
     * Unit test for toString()
     */
    public void testToString() {
        
        assertEquals("(1, 2)", point1.toString());
    }
}
