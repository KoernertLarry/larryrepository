import java.util.ArrayList;

/**
 * 
 * @author Lawrence Koerner Contribution: 33.3%
 * @author Oliwia Krupinska Contribution: 33.3%
 * @author Allen Chan Contribution: 33.3%
 * @version 2018.04.17
 */
public class Quad {
    // top left point of the current quad
    private Point topLeft;
    // bottom right point of the current quad
    private Point botRight;
    // node to store info of the point
    private Node<Point> node;
    // top left quad
    private Quad topLeftTree;
    // top right quad
    private Quad topRightTree;
    // bottom left quad
    private Quad botLeftTree;
    // bottom right quad
    private Quad botRightTree;
    // tree for searching a street
    private BinarySearchTree<StreetNodes> street;

    /**
     * constructor of the quad efficiency: O(1)
     * 
     * @param topLeft
     *            top left corner of quad
     * @param botRight
     *            bottom right corner of quad
     */
    public Quad(Point topLeft, Point botRight) {
        this.topLeft = topLeft;
        this.botRight = botRight;
        topLeftTree = null;
        topRightTree = null;
        botLeftTree = null;
        botRightTree = null;
        node = null;
        street = new BinarySearchTree<StreetNodes>();
    }

    /**
     * wrapper method for inserting a place into a location, creates a node with
     * the location and type of place to pass into the helper method efficiency:
     * O(log4(n))
     * 
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     * @param description
     *            the details of the place
     */
    public void insert(int x, int y, String description) {
        Point p1 = new Point(x, y);
        // if current node is null there is nothing to insert
        if (description == null) {
            return;
        }
        ArrayList<String> s1 = new ArrayList<String>();
        s1.add(description);
        Node<Point> newNode = new Node<Point>(p1, s1);
        insert(newNode);
    }

    /**
     * helper method, inserts a place into its location by dividing the map into
     * smaller quads until it reaches a single point quad then adds the
     * description of that place into an array of places at the same location
     * efficiency: O(log4(n))
     * 
     * 
     * @param newNode
     *            the new node to be inserted
     */
    public void insert(Node<Point> newNode) {
        // base cases

        // see if newNode is within bounds of the quad
        // if not, end recursion
        if (newNode.getData().getX() > this.botRight.getX()
                || newNode.getData().getY() > this.botRight.getY()
                || newNode.getData().getX() < this.topLeft.getX()
                || newNode.getData().getY() < this.topLeft.getY()) {
            return;
        }
        // see if we can not divide quad further
        if ((this.botRight.getX() - this.topLeft.getX())
                * (this.botRight.getY() - this.topLeft.getY()) == 1) {
            // top left node of the 1:1 quad
            if (newNode.getData().equals(this.topLeft)) {
                if (topLeftTree == null) {
                    Point n = new Point(newNode.getData().getX(),
                            newNode.getData().getY());
                    this.topLeftTree = new Quad(n, n);
                    topLeftTree.node = newNode;
                } else {
                    topLeftTree.node.getList()
                            .add(newNode.getList().get(0).toString());
                }
            }
            // bot right node of the 1:1 quad
            else if (newNode.getData().equals(this.botRight)) {
                if (botRightTree == null) {
                    Point n = new Point(newNode.getData().getX(),
                            newNode.getData().getY());
                    this.botRightTree = new Quad(n, n);
                    botRightTree.node = newNode;
                } else {
                    botRightTree.node.getList()
                            .add(newNode.getList().get(0).toString());
                }
            }
            // top right node of the 1:1 quad
            else if (newNode.getData().getX() == this.botRight.getX()) {
                if (topRightTree == null) {
                    Point n = new Point(newNode.getData().getX(),
                            newNode.getData().getY());
                    this.topRightTree = new Quad(n, n);
                    topRightTree.node = newNode;
                } else {
                    topRightTree.node.getList()
                            .add(newNode.getList().get(0).toString());
                }
            }
            // bot left node of the 1:1 quad
            else {
                if (botLeftTree == null) {
                    Point n = new Point(newNode.getData().getX(),
                            newNode.getData().getY());
                    this.botLeftTree = new Quad(n, n);
                    botLeftTree.node = newNode;
                } else {
                    botLeftTree.node.getList()
                            .add(newNode.getList().get(0).toString());
                }
            }
        }

        // recursive calls for each quad location
        // see if newNode in top left
        else if (((topLeft.getX() + botRight.getX()) / 2 > newNode.getData()
                .getX())) {
            if (((topLeft.getY() + botRight.getY()) / 2 > newNode.getData()
                    .getY())) {
                // if topLeftTree is empty
                if (topLeftTree == null) {
                    // set topLeftTree to a new quad with bounds
                    Point newTopLeft = new Point(topLeft.getX(),
                            topLeft.getY());
                    Point newBotRight = new Point(
                            (topLeft.getX() + botRight.getX()) / 2,
                            (topLeft.getY() + botRight.getY()) / 2);

                    topLeftTree = new Quad(newTopLeft, newBotRight);
                }
                // insert newNode into topLeft quad
                topLeftTree.insert(newNode);
            } else {
                if (botLeftTree == null) {
                    // set topLeftTree to a new quad with bounds
                    Point newTopLeft = new Point(topLeft.getX(),
                            ((topLeft.getY() + botRight.getY()) / 2));
                    Point newBotRight = new Point(
                            (topLeft.getX() + botRight.getX()) / 2,
                            botRight.getY());

                    botLeftTree = new Quad(newTopLeft, newBotRight);
                }
                botLeftTree.insert(newNode);
            }
        }

        // see if in top right
        else if ((topLeft.getY() + botRight.getY()) / 2 > newNode.getData()
                .getY()) {
            if (topRightTree == null) {
                // set topRightTree to a new quad with bounds
                Point newTopLeft = new Point(
                        (topLeft.getX() + botRight.getX()) / 2,
                        (topLeft.getY()));
                Point newBotRight = new Point(botRight.getX(),
                        (topLeft.getY() + botRight.getY()) / 2);

                topRightTree = new Quad(newTopLeft, newBotRight);
            }
            topRightTree.insert(newNode);
        }

        // see if in bot right
        else {
            if (botRightTree == null) {
                // set botRightTree to a new quad with bounds
                Point newTopLeft = new Point(
                        (topLeft.getX() + botRight.getX()) / 2,
                        ((topLeft.getY() + botRight.getY()) / 2));
                Point newBotRight = new Point(botRight.getX(), botRight.getY());

                botRightTree = new Quad(newTopLeft, newBotRight);
            }
            botRightTree.insert(newNode);
        }

    }

    /**
     * @param x
     * @param y
     * @param description
     * @param streets
     *            efficiency: O(n)
     */
    public void insert(int x, int y, String description, String... streets) {
        Point p1 = new Point(x, y);
        ArrayList<String> s1 = new ArrayList<String>();// O(1)
        // if current node is null there is nothing to insert
        if (description == null) {// O(1)
            return;
        }
        s1.add(description);// O(1)
        Node<Point> newNode = new Node<Point>(p1, s1);// O(1)
        // check if in the quad!!!!!
        if (this.search(p1) == null
                || !this.search(x, y).getList().contains(description)) {// O(1)
            insert(newNode);
        }
        ArrayList<String> streetNames = new ArrayList<String>(4);// O(1)
        for (int a = 0; a < streets.length; a++) {// O(n)
            streetNames.add(streets[a]);
            StreetNodes sNode = new StreetNodes(streets[a]);
            if (street.find(sNode) == null) {
                sNode.addPoint(newNode);
                street.insert(sNode);
            } else {
                street.find(sNode).addPoint(newNode);
            }
        }
    }

    /**
     * Search for a point in the quad efficiency: O(log4(n))
     * 
     * @param x
     *            the point's x coordinate
     * @param y
     *            the point's y coordinate
     * @return a node containing the point if it is in the quad or null
     *         otherwise
     */
    public Node<Point> search(int x, int y) {
        Point p = new Point(x, y);
        return search(p);
    }

    /**
     * Search for a point in the quad Recursive version of search efficiency:
     * O(log4(n))
     * 
     * @param p
     *            the point to search for
     * @return a node containing the point if its in the quad or null otherwise
     */
    public Node<Point> search(Point p) {
        // base case 1 out of bounds
        if (p.getX() > botRight.getX() || p.getY() > botRight.getY()
                || p.getX() < topLeft.getX() || p.getY() < topLeft.getY()) {
            return null;
        }
        // base case 2 found a leaf
        if (node != null) {
            return node;
        }
        if (checkQuad(p) != null) {
            return checkQuad(p).search(p);
        }
        return null;

    }

    /**
     * Wrapper for search type of place efficiency: O(n^2)
     * 
     * @param placeType
     *            a name of a type of place to search for
     * @return a list of nodes containing that place
     */
    public ArrayList<Node<Point>> search(String placeType) {
        ArrayList<Node<Point>> builder = new ArrayList<Node<Point>>();
        return search(placeType, builder);
    }

    /**
     * Helper method, searches for all points where a given type of place is an
     * returns the array list of the locations efficiency: O(n^2)
     * 
     * @param placeType
     *            a type of place to search for
     * @param builder
     *            a list of nodes containing that place type
     * @return a list of nodes containing that place type
     */
    public ArrayList<Node<Point>> search(String placeType,
            ArrayList<Node<Point>> builder) {

        // if the quad is a leaf
        if (this.topLeft.equals(this.botRight)) {
            // if the list has our place type
            if (node.getList().contains(placeType)) {
                builder.add(node); // add the node to the list
                return builder; // give back the new array list
            }
        }
        // go thru each existing sub quad...
        if (topLeftTree != null) {
            builder = topLeftTree.search(placeType, builder);
        }
        if (topRightTree != null) {
            builder = topRightTree.search(placeType, builder);
        }
        if (botLeftTree != null) {
            builder = botLeftTree.search(placeType, builder);
        }
        if (botRightTree != null) {
            builder = botRightTree.search(placeType, builder);
        }
        return builder;
    }

    /**
     * searches which child-quad the point should be in efficiency: O(1)
     * 
     * @param p
     *            the point to be searched
     * @return the quad stopped at
     */
    public Quad checkQuad(Point p) {
        int x = p.getX();
        int y = p.getY();

        if ((this.botRight.getX() - this.topLeft.getX())
                * (this.botRight.getY() - this.topLeft.getY()) == 1) {
            // top left node of the 1:1 quad
            if (p.equals(this.topLeft)) {
                return topLeftTree;
            }
            // bot right node of the 1:1 quad
            else if (p.equals(this.botRight)) {
                return botRightTree;
            }
            // top right node of the 1:1 quad
            else if (y == this.topLeft.getY()) {
                return topRightTree;
            }
            // bot left node of the 1:1 quad
            return botLeftTree;
        }

        // see if it is left
        if ((topLeft.getX() + botRight.getX()) / 2 > x) {
            // if top left
            if ((topLeft.getY() + botRight.getY()) / 2 > y) {
                return this.topLeftTree;
            }
            // else bottom left
            return this.botLeftTree;
        }
        // see if in top right
        if ((topLeft.getY() + botRight.getY()) / 2 > y) {
            return this.topRightTree;

        }
        return this.botRightTree;

    }

    /**
     * searches for a list of places on a certain street
     * 
     * 
     * @param streetName
     *            name of the street being searched
     * @return list of places on the street efficiency: O(nlog(n))
     */
    public ArrayList<Node<Point>> streetSearch(String streetName) {
        StreetNodes x = new StreetNodes(streetName);
        if (street.find(x) == null) {
            return new ArrayList<Node<Point>>();
        }
        return street.find(x).getsLocations();
    }

    /**
     * searches for a specific type of place on the given street
     * 
     * @param streetName
     *            name of the street being looked at
     * @param type_of_place
     *            type of plae bing searched for
     * @return list of specific places on the street efficiency: O((n^2)log(n))
     */
    public ArrayList<Node<Point>> streetSearch(String streetName,
            String type_of_place) {
        StreetNodes x = new StreetNodes(streetName);
        ArrayList<Node<Point>> list = street.find(x).getsLocations();// O(nLogn)
        ArrayList<Node<Point>> list2 = new ArrayList<Node<Point>>();// O(nLogn)

        for (int a = 0; a < list.size(); a++) {// O(n)
            if (list.get(a).getList().contains(type_of_place)) {
                list2.add(list.get(a));
            }
        }
        return list2;
    }

    /**
     * searches for specific places on a chosen street then organizes them based
     * off how fat they are from a given starting point
     * 
     * @param originX
     *            x coordinate of starting point
     * @param originY
     *            y coordinate of starting point
     * @param streetName
     *            name of the street being looked at
     * @param type_of_place
     *            type of place being searched for
     * @return an organized list of the final places efficiency:
     *         O(((n^2)(log(n)))^2)
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public ArrayList<Node<Point>> streetSearch(int originX, int originY,
            String streetName, String type_of_place) {
        ArrayList<Node<Point>> list = streetSearch(streetName, type_of_place);// O(nlogn)
        Comparable[] places = new Comparable[list.size()];
        // create an array for the places
        // create a loop to change distance to all nodes
        for (int a = 0; a < list.size(); a++) {// O(n)
            double distance = Math.sqrt(
                    Math.pow((originX - list.get(a).getData().getX()), 2) + Math
                            .pow((originY - list.get(a).getData().getY()), 2));
            list.get(a).setDistance(distance);
            places[a] = list.get(a);
        }
        MinHeap heap = new MinHeap(places, list.size(), list.size()); // O(nLogn)
        // form a heap out of the array
                                                                     
        ArrayList<Node<Point>> placeList = new ArrayList<Node<Point>>();
        // create the final point list of the locations
        // add places into the list by finding and removing the minimum distance
        // item.
        // use a while loop because every time an item is removed from the heap
        // the removal decrements the size
        while (heap.heapsize() > 0) {// O(n)
            placeList.add((Node<Point>) heap.removemin());
        }
        return placeList;
    }

}
