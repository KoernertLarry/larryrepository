package cs2114.mazesolver;

/**
 * @author LawrenceKoerner (ltk219)
 * @version 2018.04.13
 *
 */
public class Location implements ILocation {

    private int x;
    private int y;

    /**
     * @param x
     *            x coordinate
     * @param y
     *            y coordinate
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int x() {
        return x;
    }

    @Override
    public int y() {

        return y;
    }

    @Override
    public ILocation north() {
        Location n = new Location(x, y - 1);
        return n;
    }

    @Override
    public ILocation south() {
        Location s = new Location(x, y + 1);
        return s;
    }

    @Override
    public ILocation west() {
        Location w = new Location(x - 1, y);
        return w;
    }

    @Override
    public ILocation east() {
        Location e = new Location(x + 1, y);
        return e;
    }

    /**
     * @return true if coordinates of object are equal to location
     * @param o 
     * is the object at the location
     */
    public boolean equals(Object o) {
        return (o instanceof Location && toString().equals(o.toString()));

    }

    /**
     * @return String of coordinates
     */
    public String toString() {
        return "(" + x() + ", " + y() + ")";
    }

}
