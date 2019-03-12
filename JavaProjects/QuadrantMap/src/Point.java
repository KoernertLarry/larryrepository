
/**
 * @author LawrenceKoerner
 * @version 2018.04.18
 */
public class Point {

    private int x;
    private int y;

    /**
     * @param newX
     *            x coordinate
     * @param newY
     *            y coordinate
     */
    public Point(int newX, int newY) {
        x = newX;
        y = newY;

    }

    /**
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x
     *            sets x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y
     *            set y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return true if point equals object o
     */
    public boolean equals(Object o) {
        if (o instanceof Point) {
            if (this.getX() == ((Point) o).getX()) {
                if (this.getY() == ((Point) o).getY()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    
    public String toString() {
        String result = "(" + this.getX() + ", " + this.getY() + ")";
        return result;
    }

}
