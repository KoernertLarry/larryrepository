package cs2114.minesweeper;

/**
 * Tests MineSweeper Board class
 * 
 * @author LawrenceKoerner
 * @version (2018.02.13)
 *
 */
public class MineSweeperBoardTest extends student.TestCase {

    /**
     * declares a Mine Sweeper Board
     */
    private MineSweeperBoard b;

    /**
     * 
     */
    public MineSweeperBoardTest() {
        // Empty
    }

    /**
     * initializes the board with a width, height, and a number of mines
     */
    public void setUp() {
        b = new MineSweeperBoard(4, 4, 4);
    }

    /**
     * 
     * @param board
     *            board is returned
     * @param expected
     *            what is expected
     */
    public void assertBoard(MineSweeperBoard board, String... expected) {

        MineSweeperBoard expectedBoard = new MineSweeperBoard(
                expected[0].length(), expected.length, 0);
        expectedBoard.loadBoardState(expected);
        assertEquals(expectedBoard, board);
    }

    /**
     * Unit Test for width()
     */
    public void testWidth() {
        assertEquals(4, b.width());
    }

    /**
     * Unit Test for height()
     */
    public void testHeight() {
        assertEquals(4, b.height());
    }

    /**
     * Unit test for getCell
     */
    public void testGetCell() {
        b.loadBoardState("OOOO", "O++O", "OOOO", "OOOO");
        assertEquals(MineSweeperCell.MINE, b.getCell(2, 1));
    }

    /**
     * Unit Test for uncoverCell
     * 
     */
    public void testuncoverCell() {
        b.loadBoardState("+OOO", "OOOO", "OOOO", "OOOO");
        assertEquals(MineSweeperCell.MINE, b.getCell(0, 0));
        b.uncoverCell(0, 0);
        assertEquals(MineSweeperCell.UNCOVERED_MINE, b.getCell(0, 0));
        assertBoard(b, "*OOO", "OOOO", "OOOO", "OOOO");
        b.uncoverCell(0, 1);
        assertEquals(MineSweeperCell.ADJACENT_TO_1, b.getCell(0, 1));
        b.loadBoardState("+1OO", "OOOO", "OOOO", "OOOO");
        b.uncoverCell(0, 0);
        b.uncoverCell(0, 1);
        assertEquals(MineSweeperCell.UNCOVERED_MINE, b.getCell(0, 0));
        assertEquals(MineSweeperCell.ADJACENT_TO_1, b.getCell(0, 1));

    }

    /**
     * Unit Test for flagCell
     */
    public void testFlagCell() {
        b.loadBoardState("+OOO", "OOOO", "OOOO", "OOOO");
        b.flagCell(0, 0);
        assertEquals(MineSweeperCell.FLAGGED_MINE, b.getCell(0, 0));
        b.flagCell(0, 1);
        assertEquals(MineSweeperCell.FLAG, b.getCell(0, 1));
    }

    /**
     * Unit test for isGameLost
     */
    public void testIsGameLost() {
        b.loadBoardState("M   ", "    ", "    ", "    ");
        assertEquals(false, b.isGameLost());
        b.loadBoardState("OOOO", "OOOO", "OOOO", "OOOO");
        b.setCell(0, 0, MineSweeperCell.MINE);
        b.uncoverCell(0, 0);
        assertEquals(true, b.isGameLost());
    }

    /**
     * Unit test for isGameWon
     */
    public void testIsGameWon() {
        b.loadBoardState("O*+O", "O*+O", "OFFO", "OFFO");
        assertEquals(false, b.isGameWon());
        b.loadBoardState("FFFF", "FFFF", "FFFF", "FFFF");
        assertEquals(false, b.isGameWon());
        b.loadBoardState("****", "****", "****", "****");
        assertEquals(false, b.isGameWon());
        b.loadBoardState("++++", "++++", "++++", "++++");
        assertEquals(false, b.isGameWon());
        b.loadBoardState("M231", "4567", "8   ", "    ");
        assertEquals(true, b.isGameWon());
    }

    /**
     * Unit test for numberOfAjacentMines
     */
    public void testNumberOfAdjacentMines() {
        b.loadBoardState("OOOO", "OOOO", "OO+O", "OOOO");
        b.uncoverCell(3, 2);
        assertEquals(MineSweeperCell.ADJACENT_TO_1, b.getCell(3, 2));
        b.loadBoardState("OOOO", "OOM*", "OO+O", "OOOO");
        b.uncoverCell(3, 2);
        assertEquals(MineSweeperCell.ADJACENT_TO_3, b.getCell(3, 2));
    }

    /**
     * Unit test for revealBoard
     */
    public void testRevealBoard() {
        b.loadBoardState("OO+O", "OOOO", "OOOO", "OOOO");
        b.revealBoard();
        assertBoard(b, " 1*1", " 111", "    ", "    ");
    }

    /**
     * test for adjacent to test
     */
    public void testAdjacentTo() {
        MineSweeperCell c = MineSweeperCell.ADJACENT_TO_0;
        assertNotNull(c);
        // testing for exception
        Exception thrown = null;
        try {
            c = MineSweeperCell.adjacentTo(10);
        } 
        catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);

        thrown = null;
        try {
            MineSweeperCell.adjacentTo(-1);
        } 
        catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
        assertNotNull(MineSweeperCell.values());
        assertNotNull(MineSweeperCell
                .valueOf(MineSweeperCell.ADJACENT_TO_0.toString()));

    }

    /**
     * Unit test for loadBoardState
     */
    public void testloadBoardState() {
        MineSweeperBoard a = new MineSweeperBoard(2, 2, 1);
        Exception thrown = null;
        // loadBoardState testing
        // wrong number of rows
        try {
            a.loadBoardState("00");
        } 
        catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
        thrown = null;
        // wrong number of columns
        try {
            a.loadBoardState("0000 ", " ");
        } 
        catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
        // Wrong symbol in cell
        try {
            a.loadBoardState("00", "$+");
        } 
        catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }

    /**
     * * This method test Equals.
     */
    public void testEqual() {
        MineSweeperBoard mBoard1 = new MineSweeperBoard(4, 4, 6);
        MineSweeperBoard mBoard2 = new MineSweeperBoard(4, 4, 6);
        mBoard1.loadBoardState("    ", "OOOO", "O++O", "OOOO");
        mBoard2.loadBoardState("    ", "OOOO", "O++O", "OOOO");
        // test the same board same dimensions
        assertTrue(mBoard1.equals(mBoard2));
        // same board testing same board
        assertTrue(mBoard1.equals(mBoard1));
        // testing same dimensions board with different cell
        MineSweeperBoard mBoard3 = new MineSweeperBoard(4, 4, 6);
        mBoard3.loadBoardState("    ", "O+OO", "O++O", "OOOO");
        assertFalse(mBoard1.equals(mBoard3));
        MineSweeperBoard mBoard4 = new MineSweeperBoard(15, 1, 0);
        mBoard4.loadBoardState("OFM+* 123456788");
        assertFalse(mBoard1.toString().equals(mBoard3.toString()));
        // testing two string against a board
        assertFalse(mBoard4.toString().equals(mBoard2.toString()));
        // testing against a string
        assertFalse(mBoard1.equals("abc"));
        MineSweeperBoard nullBoard = null;
        assertFalse(mBoard1.equals(nullBoard));
        // same width but different height
        MineSweeperBoard mBoard6 = new MineSweeperBoard(4, 5, 6);
        mBoard6.loadBoardState("    ", "O+OO", "O++O", "OOOO", "OOOO");
        assertFalse(mBoard6.equals(mBoard1));
        // different width same height
        MineSweeperBoard mBoard5 = new MineSweeperBoard(5, 4, 6);
        mBoard5.loadBoardState("     ", "O+OOO", "O++OO", "OOOOO");
        assertFalse(mBoard5.equals(mBoard1));
    }

}
