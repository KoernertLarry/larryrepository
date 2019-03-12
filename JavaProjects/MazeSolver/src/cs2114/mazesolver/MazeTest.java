package cs2114.mazesolver;

/**
 * @author LawrenceKoerner
 * @version 2018.03.13
 */
public class MazeTest extends student.TestCase {
    /**
     * Data field
     */
    private Maze maze1;
    /**
     * Data field
     */
    private Maze maze2;
    /**
     * Data field
     */
    private Location test;
    
    /**
     * Constructor for MazeTest
     */
    public MazeTest() {
        //Empty
    }
    /**
     *Initializes data fields
     */
    public void setUp() {
        maze1 = new Maze(10);
        maze2 = new Maze(2);
        test = new Location(0, 0);
        
    }
    
    /**
     * Unit test for size()
     */
    public void testSize() {
        assertEquals(10, maze1.size());
    }
    
    /**
     * Unit test for getStartLocation()
     */
    public void testGetStartLocation() {
        assertEquals(test, maze1.getStartLocation());
    }
    
    /**
     * Unit test for setStartLocation()
     */
    public void testSetStartLocation() {
        assertEquals(test, maze1.getStartLocation());
        test = new Location(0, 1);
        maze1.setStartLocation(test);
        assertEquals(test, maze1.getStartLocation());
        test = new Location(2, 2);
        maze1.setCell(test, MazeCell.WALL);
        maze1.setStartLocation(test);
        assertEquals(test, maze1.getStartLocation());
        
    }
    
    /**
     * Unit test for getGoalLocation()
     */
    public void testGetGoalLocation() {
        test = new Location(9, 9);
        assertEquals(test, maze1.getGoalLocation());
    }
    
    /**
     * Unit test for setGoalLocation()
     */
    public void testSetGoalLocation() {
        test = new Location(9, 9);
        assertEquals(test, maze1.getGoalLocation());
        
        test = new Location(1, 1);
        maze1.setGoalLocation(test);
        assertEquals(test, maze1.getGoalLocation());
        
        test = new Location(2, 2);
        maze1.setCell(test, MazeCell.WALL);
        maze1.setGoalLocation(test);
        assertEquals(test, maze1.getGoalLocation());
        
        
    }
    
    /**
     * Unit test for getCell()
     */
    public void testGetCell() {
        assertEquals(MazeCell.UNEXPLORED, maze1.getCell(test));
        test = new Location(10, 10);
        assertEquals(MazeCell.INVALID_CELL, maze1.getCell(test));
        test = new Location(-1, -1);
        assertEquals(MazeCell.INVALID_CELL, maze1.getCell(test));

        assertEquals(MazeCell.INVALID_CELL, maze1.getCell(test));
        test = new Location(-1, 0);
        assertEquals(MazeCell.INVALID_CELL, maze1.getCell(test));
        test = new Location(0, -1);
        assertEquals(MazeCell.INVALID_CELL, maze1.getCell(test));
    }
    
    /**
     * Unit test for setCell()
     */
    public void testSetCell() {
        maze1.setCell(test, MazeCell.WALL);
        assertEquals(MazeCell.UNEXPLORED, maze1.getCell(test));
        test = new Location(9, 9);
        maze1.setCell(test, MazeCell.WALL);
        assertEquals(MazeCell.UNEXPLORED, maze1.getCell(test));
        test = new Location(1, 0);
        maze1.setCell(test, MazeCell.WALL);
        assertEquals(MazeCell.WALL, maze1.getCell(test));
        test = new Location(0, 1);
        maze1.setCell(test, MazeCell.WALL);
        assertEquals(MazeCell.WALL, maze1.getCell(test));
        
        
        
        maze1.setCell(test, MazeCell.CURRENT_PATH);
        assertEquals(MazeCell.CURRENT_PATH, maze1.getCell(test));
        maze1.setCell(test, MazeCell.FAILED_PATH);
        assertEquals(MazeCell.FAILED_PATH, maze1.getCell(test));
        
    }
    
    /**
     * Tests values of enum
     */
    public void testEnum() {
        assertNotNull(MazeCell.valueOf(MazeCell.UNEXPLORED.toString()));
        assertNotNull(MazeCell.valueOf(MazeCell.INVALID_CELL.toString()));
        assertNotNull(MazeCell.valueOf(MazeCell.CURRENT_PATH.toString()));
        assertNotNull(MazeCell.valueOf(MazeCell.FAILED_PATH.toString()));
        assertNotNull(MazeCell.valueOf(MazeCell.WALL.toString()));
    }
    
    /**
     * Tests for solve()
     */
    public void testSolve() {
        test = new Location(0, 1);
        maze2.setCell(test, MazeCell.WALL);
        assertEquals("(1, 1) (1, 0) (0, 0) ", maze2.solve());
    }
    /**
     * tests for new solve
     */
    public void testSolve1() {
        test = new Location(0, 1);
        maze2.setCell(test, MazeCell.WALL);
        test = new Location(1, 1);
        maze2.setStartLocation(test);
        test = new Location(0, 0);
        maze2.setGoalLocation(test);
        assertEquals("(0, 0) (1, 0) (1, 1) ", maze2.solve());
    }
    /**
     * failed test paths for solution
     */
    public void testSolve2() {
        test = new Location(0, 1);
        maze2.setCell(test, MazeCell.WALL);
        test = new Location(1, 0);
        maze2.setCell(test, MazeCell.WALL);
        assertEquals(null, maze2.solve());
    }
    
    
 
}
