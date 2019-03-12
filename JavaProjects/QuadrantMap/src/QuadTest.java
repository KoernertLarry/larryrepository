
import java.util.ArrayList;
import junit.framework.TestCase;


/**
 * Test for Quad Class
 * 
 * @author Lawrence Koerner
 * Contribution: 33.3%
 * @author Oliwia Krupinska
 * Contribution: 33.3%
 * @author Allen Chan
 * Contribution: 33.3%
 * @version 2018.04.17
 */
public class QuadTest extends TestCase {
    /**
     * out of bounds node
     */
    private Node<Point> node2;
    /**
     * the map
     */
    private Quad map;
    /**
     * list to hold description of places
     */
    private ArrayList<String> list = new ArrayList<String>();

    public void setUp() {
        map = new Quad(new Point(0, 0), new Point(4, 4));
        list.add("school");
        node2 = new Node<Point>(new Point(0, 8), list);
    }

    /**
     * testing for all methods with all possible cases
     */
    public void testInsertAndSearch() {
        // case if node is null - nothing to add
        map.insert(0,0, null);
        assertEquals(null,map.search(0, 0));
        
        // cases if out of bounds
        // y too small
        map.insert(2, -1, "school");
        assertNull(map.search(2, -1));
        // y too large
        map.insert(node2);
        assertNull(map.search(0, 8));
        // x too large
        map.insert(7, 0, "school");
        assertNull(map.search(7, 0));
        // x too small
        map.insert(-6, 0, "school");
        assertNull(map.search(-6, 0));
        
        //bottom left
        map.insert(0, 4, "school");
        map.insert(0, 4, "store");
        assertEquals("[]", map.search("jail").toString());
        assertNotNull(map.search(0, 4));
        assertEquals("[(0,4), school, store]", map.search("school").toString());
        //top right
        map.insert(4, 0, "school");
        map.insert(4, 0, "jail");
        assertNotNull(map.search(4, 0));
        assertEquals("[(4,0), school, jail, (0,4), school, store]", map.search("school").toString());
        //top left
        map.insert(0, 0, "school");
        map.insert(0, 0, "home");
        assertNotNull(map.search(0, 0));
        assertEquals("[(0,0), school, home]", map.search("home").toString());
        //bottom right
        map.insert(4, 4, "boots");
        map.insert(4, 4, "jail");
        assertEquals("[boots, jail]", map.search(4, 4).getList().toString());
        assertEquals("[(4,4), boots, jail]", map.search("boots").toString());

    }
    /**
     * tests the search methods with distances and streets
     */
    public void testInsertWithStreet() {
        assertEquals("[]",map.streetSearch("Polk St").toString());
        map.insert(0, 0, null, "Polk St");
        assertEquals("[]",map.streetSearch("Polk St").toString());

        //one item on the street
        
        //tests the first search method
        map.insert(0, 0, "school", "Polk St");
        assertEquals("[(0,0), school]", map.streetSearch("Polk St").toString());
        //second search method
        assertEquals("[(0,0), school]", map.streetSearch("Polk St", "school").toString());
        //third search method
        double x=map.streetSearch("Polk St", "school").get(0).getDistance();
        assertEquals(0, x, .01);
        x=map.streetSearch(2, 0, "Polk St", "school").get(0).getDistance();
        assertEquals(2, x, .01);
        
        //multiple items on the street
        
        //first search method
        map.insert(0, 4, "bar", "Polk St");
        assertEquals("[(0,0), school, (0,4), bar]", map.streetSearch("Polk St").toString());
        
        //tests second search method
        map.insert(0, 2, "bar", "Polk St");
        assertEquals("[(0,4), bar, (0,2), bar]", map.streetSearch("Polk St", "bar").toString());
        
        //test third search method
        assertEquals("[(0,2), bar, (0,4), bar]", map.streetSearch(0, 0, "Polk St", "bar").toString());
        
        //when there are unaffiliated points
        
        map.insert(2, 3, "bar", "New St");
        //basic search does not include non-affiliated points
        assertEquals("[(2,3), bar]", map.streetSearch("New St").toString());
        //add multiple things to one point
        map.insert(0, 0, "bar", "Polk St");
        assertEquals("(0,0), school, bar", map.search(0,0).toString());
        //try adding something that already exists
        map.insert(0, 0, "bar", "Polk St");
        assertEquals("(0,0), school, bar", map.search(0,0).toString());
    }
}


