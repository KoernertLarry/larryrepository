package cs2114.mazesolver;

/**
 * @author LawrenceKoerner
 *@version 2018.04.13
 */
public class LocationTest extends student.TestCase {
/**
 * datafields
 */
    private Location location1;
    /**
     * datafields
     */
    private Location location2;
    /*
     * datafields
     */
    private Location location3;
    
    /**
     * Constuctor for LocationTest
     */
    public LocationTest() {
        //Empty
    }
    /**
     * initalizes data fields
     */
    public void setUp() {
        location1 = new Location(1, 2);
        location2 = new Location(2, 1);
        location3 = new Location(1, 1);
    }
    
    /**
     * Unit test for x()
     */
    public void testX() {
        assertEquals(1, location1.x());
        assertEquals(2, location2.x());
    }
    
    /**
     * Unit test for y()
     */
    public void testy() {
        assertEquals(2, location1.y());
        assertEquals(1, location2.y());
    }
    
    /**
     * Unit test for north()
     */
    public void testNorth() {
        assertEquals(location3, location1.north());
    }
    
    /**
     * Unit test for south()
     */
    public void testSouth() {
        location3 = new Location(1, 3);
        assertEquals(location3, location1.south());
    }
    
    /**
     * Unit test for west()
     */
    public void testWest() {
        assertEquals(location3, location2.west());
    }
    
    /**
     * Unit test for east()
     */
    public void testEast() {
        location3 = new Location(3, 1);
        assertEquals(location3, location2.east());
    }
    
    /**Unit test for equals()
     * 
     */
    public void testEquals() {
        assertEquals(false, location1.equals(location2));
        location1 = new Location(2, 1);
        assertEquals(true, location1.equals(location2));
    }
    
    /**
     * Unite test for toString
     */
    public void testToString() {
        assertEquals("(1, 2)", location1.toString());
    }
    
}
