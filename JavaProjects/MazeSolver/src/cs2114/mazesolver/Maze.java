package cs2114.mazesolver;

import java.util.Stack;

/**
 * @author LawrenceKoerner
 *@version 2018.04.13
 */
public class Maze implements IMaze {
    
    private MazeCell[][] grid;
    private Stack<Location> stack;
    private Location startLocation;
    private Location goalLocation;
    
    /**
     * Constructor for Maze
     * @param size grid size
     */
    public Maze(int size) {
        grid = new MazeCell[size][size];
        stack = new Stack<Location>();
        startLocation = new Location(0, 0);
        goalLocation = new Location(size - 1, size - 1);
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = MazeCell.UNEXPLORED;
            }
        }
    }

    @Override
    public int size() {
        return grid.length;
    }

    @Override
    public ILocation getStartLocation() {
        return startLocation;
    }

    @Override
    public void setStartLocation(ILocation location) {
        MazeCell start1 = grid[location.x()][location.y()];
        if (start1.equals(MazeCell.WALL)) {
            grid[location.x()][location.y()] = MazeCell.UNEXPLORED;
        }
        grid[location.x()][location.y()] = MazeCell.UNEXPLORED;
        startLocation = (Location)location;
    }

    @Override
    public ILocation getGoalLocation() {
        return goalLocation;
    }

    @Override
    public void setGoalLocation(ILocation location) {
        MazeCell end1 = grid[location.x()][location.y()];
        if (end1.equals(MazeCell.WALL)) {
            grid[location.x()][location.y()] = MazeCell.UNEXPLORED;
        }
        grid[location.x()][location.y()] = MazeCell.UNEXPLORED;
        goalLocation = (Location)location;
    }

    @Override
    public MazeCell getCell(ILocation location) {
        if (location.x() < 0 || location.x() > size() - 1 
                || location.y() < 0) {
            return MazeCell.INVALID_CELL;
        }
        else {
            return grid[location.x()][location.y()];
        }
    }
    
   
    @Override
    public void setCell(ILocation location, MazeCell cell) {
        if (cell.equals(MazeCell.WALL)) {
            if (!(location.x() == startLocation.x() 
                    && location.y() == startLocation.y())
                    && !(location.x() == goalLocation.x() 
                    && location.y() == goalLocation.y())) {
                grid[location.x()][location.y()] = cell;
            }
        }
        else { 
            grid[location.x()][location.y()] = cell;
        }
    }

    @Override
    public String solve() {
        stack.push(startLocation);
        setCell(startLocation, MazeCell.CURRENT_PATH);
        Location path = stack.peek();
        while (true) {
            if (getCell(path.north()) == MazeCell.UNEXPLORED) {
                stack.push((Location) path.north());
                path = stack.peek();
                setCell(path, MazeCell.CURRENT_PATH);
            }
            else if (getCell(path.south()) == MazeCell.UNEXPLORED) {
                stack.push((Location) path.south());
                path = stack.peek();
                setCell(path, MazeCell.CURRENT_PATH);
            }
            else if (getCell(path.east()) == MazeCell.UNEXPLORED) {
                stack.push((Location) path.east());
                path = stack.peek();
                setCell(path, MazeCell.CURRENT_PATH);
            }
            else if (getCell(path.south()) == MazeCell.UNEXPLORED) {
                stack.push((Location) path.east());
                path = stack.peek();
                setCell(path, MazeCell.CURRENT_PATH);
            }
            else {
                setCell(path, MazeCell.FAILED_PATH);
                stack.pop();
                if (!stack.isEmpty()) {
                    path = stack.peek();
                }
                else {
                    return null;
                }
            }
            if (path.equals(goalLocation)) {
                String solution = "";
                for (int i = 0; i < stack.size(); i++) {
                    Location coordinate =  stack.elementAt(i);
                    solution += coordinate.toString() + " ";
             
                    return solution;
                    
                
                }
            
            
            }
        }
    }
}

