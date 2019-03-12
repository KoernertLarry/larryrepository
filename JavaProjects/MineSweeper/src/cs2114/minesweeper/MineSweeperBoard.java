package cs2114.minesweeper;

import java.util.Random;

/**
 * Creates MineSweeper board and implements methods in Base
 * 
 * @author LawrenceKoerner
 * @version (2018.02.12)
 *
 */

public class MineSweeperBoard extends MineSweeperBoardBase {

    private MineSweeperCell[][] board;
    private int m = 0;

    /**
     * Constructor for MineSweeperBoard object Creates a board with a given
     * width and height. Places a given number of mines at random locations on
     * the board
     * 
     * @param width
     *            initializes the width of the board
     * @param height
     *            initializes the height of the board
     * @param mines
     *            initializes the number of mines placed
     */
    public MineSweeperBoard(int width, int height, int mines) {
        board = new MineSweeperCell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board[i][j] = MineSweeperCell.COVERED_CELL;
            }
        }

        while (m < mines) {
            Random random = new Random();
            int y = random.nextInt(height);
            int x = random.nextInt(width);

            if (board[x][y] != MineSweeperCell.MINE) {
                board[x][y] = MineSweeperCell.MINE;
                m++;
            }
        }
    }

    /**
     * @return width of the board
     */
    public int width() {
        return board.length;
    }

    /**
     * @return height of the board
     */
    public int height() {
        return board[0].length;
    }

    /**
     * getter for a cell
     * 
     * @param w
     *            width of the given cell
     * @param h
     *            height of the given cell
     * @return a given cell
     */
    public MineSweeperCell getCell(int w, int h) {
        return board[w][h];
    }

    /**
     * Uncovers any given cell. If it's a mine it uncovers a mine and you lose,
     * or it uncovers a cell and the number of adjacent mines if given
     * 
     * @param w
     *            width of the given cell
     * @param h
     *            height of the given cell
     */
    public void uncoverCell(int w, int h) {
        if (board[w][h] == MineSweeperCell.COVERED_CELL) {
            board[w][h] = MineSweeperCell
                    .adjacentTo(numberOfAdjacentMines(w, h));

        } 
        if (board[w][h] == MineSweeperCell.MINE) {
            board[w][h] = MineSweeperCell.UNCOVERED_MINE;
        }

    }

    /**
     * Either flags or un-flags any given cell if a mine is in the cell
     * 
     * @param w
     *            width of the given cell
     * @param h
     *            height of the given cell
     */
    public void flagCell(int w, int h) {
        if (board[w][h] == MineSweeperCell.COVERED_CELL) {
            board[w][h] = MineSweeperCell.FLAG;
        }
        if (board[w][h] == MineSweeperCell.MINE) {
            board[w][h] = MineSweeperCell.FLAGGED_MINE;
        }

    }

    /**
     * @return true if game is lost by hitting a mine
     */
    public boolean isGameLost() {
        for (int i = 0; i < width(); i++) {

            for (int j = 0; j < height(); j++) {
                if (board[i][j] == MineSweeperCell.UNCOVERED_MINE) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks every cell to see if game is won
     * 
     * @return true if game is won and no cells are left covered or flagged
     *         incorrect
     */
    public boolean isGameWon() {
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                if (board[i][j] == MineSweeperCell.MINE
                        || board[i][j] == MineSweeperCell.FLAG
                        || board[i][j] == MineSweeperCell.COVERED_CELL
                        || board[i][j] == MineSweeperCell.UNCOVERED_MINE) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Counts the number of mines that appear near each cell
     * 
     * @param width
     *            width of the board
     * @param height
     *            height of the board
     * @return the number of mines surrounding each cell
     */
    public int numberOfAdjacentMines(int width, int height) {
        int numMines = 0;

        for (int w = width - 1; w < width + 2; w++) {
            if (w == -1) {
                w++;
            } 
            else if (w == board.length) {
                break;
            }
            for (int h = height - 1; h < height + 2; h++) {
                if (h == -1) {
                    h++;
                } 
                else if (h == board[w].length) {
                    break;
                }
                if (board[w][h] == MineSweeperCell.MINE
                        || board[w][h] == MineSweeperCell.FLAGGED_MINE
                        || board[w][h] == MineSweeperCell.UNCOVERED_MINE) {
                    numMines++;
                }
            }
        }
        return numMines;
    }

    /**
     * reveals the board by calling on uncoverCell method
     */
    public void revealBoard() {
        for (int width = 0; width < board.length; width++) {
            for (int height = 0; height < board.length; height++) {
                uncoverCell(width, height);
            }
        }
    }

    /**
     * mutator for specific cell
     * 
     * @param width
     *            width of the board
     * @param height
     *            height of the board
     * @param value
     *            value of the enum
     */
    protected void setCell(int width, int height, MineSweeperCell value) {
        board[width][height] = value;
    }

}
