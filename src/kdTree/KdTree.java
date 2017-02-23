public class KdTree {
    private static final boolean VERTICAL = true;
    private static final boolean HORIZONTAL = false;

    /** Node class to build the tree */
    private class Node {
        private Point2D value;
        private Node left;
        private Node right;
        private boolean division;

        Node(Point2D p) {
            value = p;
            left = null;
            right = null;
        }
    }

    private Node root;
    private LinkedList<Point2D> containedPoints;
    private Point2D nearestPoint;
    private Point2D comparisonPoint;
    private int size;

    /**
     * constructor, creates a SET of points
     * */
    public KdTree() {
        root = null;
        size = 0;
    }

    /**
     * is the set empty?
     * */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * number of points in the set
     * */
    public int size() {
        return size;
    }

    /**
     * add the point to the set (if it is not already in the set)
     * */
    public void insert(Point2D p) {
        if (p == null) throw new NullPointerException();

        Node newPoint = new Node(p);
        Node currentPosition = root;
        if (root == null) {
            newPoint.division = VERTICAL;
            root = newPoint;
            size++;
            return;
        }

        if (!p.equals(currentPosition.value)) {
            if (currentPosition.division == VERTICAL) {

            }
        }
    }

    /**
     * does the set contain point p?
     * */
    public boolean contains(Point2D p) {

    }

    /**
     * draw all points to standard draw
     * */
    public void draw() {

    }

    /**
     * all points that are inside the rectangle
     * */
    public Iterable<Point2D> range(RectHV rect) {

    }

    /**
     * a nearest neighbor in the set to point p; null if the set is empty
     * */
    public Point2D nearest(Point2D p) {

    }
}
