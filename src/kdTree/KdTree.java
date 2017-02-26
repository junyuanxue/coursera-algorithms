import java.util.LinkedList;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.TreeSet;

public class KdTree {
    private static class KdNode {
        private KdNode leftNode;
        private KdNode rightNode;
        private final boolean isVertical;
        private final double x;
        private final double y;

        public KdNode(final double x, final double y, final KdNode leftNode,
                      final KdNode rightNode, final boolean isVertical) {
            this.x = x;
            this.y = y;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
            this.isVertical = isVertical;
        }
    }

    private static final RectHV CONTAINER = new RectHV(0, 0, 1, 1);
    private KdNode rootNode;
    private int size;

    // construct an empty set of points
    public KdTree() {
        this.size = 0;
        this.rootNode = null;
    }

    // is the set empty?
    public boolean isEmpty() {
        return this.size == 0;
    }

    // number of points in the set
    public int size() {
        return this.size;
    }

    // add the point p to the set (if it is not already in the set)
    public void insert(final Point2D p) {
        this.rootNode = insert(this.rootNode, p, true);
    }

    private KdNode insert(final KdNode node, final Point2D p,
                          final boolean isVertical) {
        // if new node, create it
        if (node == null) {
            size++;
            return new KdNode(p.x(), p.y(), null, null, isVertical);
        }

        // if already in, return it
        if (node.x == p.x() && node.y == p.y()) {
            return node;
        }

        // insert it where corresponds (left - right recursive call)
        if (node.isVertical && p.x() < node.x || !node.isVertical
                && p.y() < node.y) {
            node.leftNode = insert(node.leftNode, p, !node.isVertical);
        } else {
            node.rightNode = insert(node.rightNode, p, !node.isVertical);
        }
        return node;
    }

    // does the set contain the point p?
    public boolean contains(Point2D p) {
        return contains(rootNode, p.x(), p.y());
    }

    private boolean contains(KdNode node, double x, double y) {
        if (node == null) {
            return false;
        }

        if (node.x == x && node.y == y) {
            return true;
        }

        if (node.isVertical && x < node.x || !node.isVertical && y < node.y) {
            return contains(node.leftNode, x, y);
        } else {
            return contains(node.rightNode, x, y);
        }
    }

    // draw all of the points to standard draw
    public void draw() {
        StdDraw.setScale(0, 1);

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius();
        CONTAINER.draw();

        draw(rootNode, CONTAINER);
    }

    private void draw(final KdNode node, final RectHV rect) {
        if (node == null) {
            return;
        }

        // draw the point
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        new Point2D(node.x, node.y).draw();

        // get the min and max points of division line
        Point2D min, max;
        if (node.isVertical) {
            StdDraw.setPenColor(StdDraw.RED);
            min = new Point2D(node.x, rect.ymin());
            max = new Point2D(node.x, rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            min = new Point2D(rect.xmin(), node.y);
            max = new Point2D(rect.xmax(), node.y);
        }

        // draw that division line
        StdDraw.setPenRadius();
        min.drawTo(max);

        // recursively draw children
        draw(node.leftNode, leftRect(rect, node));
        draw(node.rightNode, rightRect(rect, node));
    }

    private RectHV leftRect(final RectHV rect, final KdNode node) {
        if (node.isVertical) {
            return new RectHV(rect.xmin(), rect.ymin(), node.x, rect.ymax());
        } else {
            return new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.y);
        }
    }

    private RectHV rightRect(final RectHV rect, final KdNode node) {
        if (node.isVertical) {
            return new RectHV(node.x, rect.ymin(), rect.xmax(), rect.ymax());
        } else {
            return new RectHV(rect.xmin(), node.y, rect.xmax(), rect.ymax());
        }
    }

    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(final RectHV rect) {
        final TreeSet<Point2D> rangeSet = new TreeSet<Point2D>();
        range(rootNode, CONTAINER, rect, rangeSet);

        return rangeSet;
    }

    private void range(final KdNode node, final RectHV nrect,
                       final RectHV rect, final TreeSet<Point2D> rangeSet) {
        if (node == null)
            return;

        if (rect.intersects(nrect)) {
            final Point2D p = new Point2D(node.x, node.y);
            if (rect.contains(p))
                rangeSet.add(p);
            range(node.leftNode, leftRect(nrect, node), rect, rangeSet);
            range(node.rightNode, rightRect(nrect, node), rect, rangeSet);
        }
    }

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(final Point2D p) {
        return nearest(rootNode, CONTAINER, p.x(), p.y(), null);
    }

    private Point2D nearest(final KdNode node, final RectHV rect,
                            final double x, final double y, final Point2D candidate) {
        if (node == null){
            return candidate;
        }

        double dqn = 0.0;
        double drq = 0.0;
        RectHV left = null;
        RectHV rigt = null;
        final Point2D query = new Point2D(x, y);
        Point2D nearest = candidate;

        if (nearest != null) {
            dqn = query.distanceSquaredTo(nearest);
            drq = rect.distanceSquaredTo(query);
        }

        if (nearest == null || dqn > drq) {
            final Point2D point = new Point2D(node.x, node.y);
            if (nearest == null || dqn > query.distanceSquaredTo(point))
                nearest = point;

            if (node.isVertical) {
                left = new RectHV(rect.xmin(), rect.ymin(), node.x, rect.ymax());
                rigt = new RectHV(node.x, rect.ymin(), rect.xmax(), rect.ymax());

                if (x < node.x) {
                    nearest = nearest(node.leftNode, left, x, y, nearest);
                    nearest = nearest(node.rightNode, rigt, x, y, nearest);
                } else {
                    nearest = nearest(node.rightNode, rigt, x, y, nearest);
                    nearest = nearest(node.leftNode, left, x, y, nearest);
                }
            } else {
                left = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.y);
                rigt = new RectHV(rect.xmin(), node.y, rect.xmax(), rect.ymax());

                if (y < node.y) {
                    nearest = nearest(node.leftNode, left, x, y, nearest);
                    nearest = nearest(node.rightNode, rigt, x, y, nearest);
                } else {
                    nearest = nearest(node.rightNode, rigt, x, y, nearest);
                    nearest = nearest(node.leftNode, left, x, y, nearest);
                }
            }
        }

        return nearest;
    }
//    private static final boolean VERTICAL = true;
//    private static final boolean HORIZONTAL = false;
//
//    /** Node class to build the tree */
//    private class Node {
//        private Point2D value;
//        private Node left;
//        private Node right;
//        private boolean division;
//
//        Node(Point2D p) {
//            value = p;
//            left = null;
//            right = null;
//        }
//    }
//
//    private Node root;
//    private LinkedList<Point2D> containedPoints;
//    private Point2D nearestPoint;
//    private Point2D comparisonPoint;
//    private int size;
//
//    /**
//     * constructor, creates a SET of points
//     * */
//    public KdTree() {
//        root = null;
//    }
//
//    /**
//     * is the set empty?
//     * */
//    public boolean isEmpty() {
//        return size() == 0;
//    }
//
//    /**
//     * number of points in the set
//     * */
//    public int size() {
//        return root == null ? 0 : size;
//    }
//
//    /**
//     * add the point to the set (if it is not already in the set)
//     * */
//    public void insert(Point2D p) {
//        if (p == null) throw new NullPointerException();
//
//        Node newPoint = new Node(p);
//        Node currentPosition = root;
//        if (root == null) {
//            newPoint.division = VERTICAL;
//            root = newPoint;
//            size++;
//            return;
//        }
//
//        if (!p.equals(currentPosition.value)) {
//            if (currentPosition.division == VERTICAL) { // slice vertically
//                if (p.x() < currentPosition.value.x()) {
//                    if (currentPosition.left == null) {
//                        newPoint.division = HORIZONTAL;
//                        currentPosition.left = newPoint;
//                        size++;
//                        return;
//                    } else {
//                        currentPosition = currentPosition.left;
//                    }
//                } else {
//                    if (currentPosition.right == null) {
//                        newPoint.division = HORIZONTAL;
//                        currentPosition.right = newPoint;
//                        size++;
//                        return;
//                    } else {
//                        currentPosition = currentPosition.right;
//                    }
//                }
//            } else { // slice horizontally
//                if (p.y() < currentPosition.value.y()) {
//                    if (currentPosition.left == null) {
//                        newPoint.division = VERTICAL;
//                        currentPosition.left = newPoint;
//                        size++;
//                        return;
//                    } else {
//                        currentPosition = currentPosition.left;
//                    }
//                } else {
//                    if (currentPosition.right == null) {
//                        newPoint.division = VERTICAL;
//                        currentPosition.right = newPoint;
//                        size++;
//                        return;
//                    } else {
//                        currentPosition= currentPosition.right;
//                    }
//                }
//            }
//        }
//    }
//
//    /**
//     * does the set contain point p?
//     * */
//    public boolean contains(Point2D p) {
//        if (p == null) throw new NullPointerException();
//
//        Node currentPosition = root;
//        while (currentPosition != null) {
//            if (p.equals(currentPosition.value)) {
//                return true;
//            }
//
//            if (currentPosition.division == VERTICAL) { // use first bit to determine if level is even or odd
//                if (p.x() < currentPosition.value.x()) { // vertical slice
//                    currentPosition = currentPosition.left;
//                } else {
//                    currentPosition = currentPosition.right;
//                }
//            } else {
//                if (p.y() < currentPosition.value.y()) { // horizontal slice
//                    currentPosition = currentPosition.left;
//                } else {
//                    currentPosition = currentPosition.right;
//                }
//            }
//        }
//        return false;
//    }
//
//    /**
//     * draw all points to standard draw
//     * */
//    public void draw() {
//        if (root != null) {
//            drawNodes(root);
//        }
//    }
//
//    private void drawNodes(Node node) {
//        node.value.draw();
//
//        if (node.right != null) {
//            drawNodes(node.right);
//        }
//
//        if (node.left != null) {
//            drawNodes(node.left);
//        }
//    }
//
//    /**
//     * all points that are inside the rectangle
//     * */
//    public Iterable<Point2D> range(RectHV rect) {
//        if (rect == null) throw new NullPointerException();
//        containedPoints = new LinkedList<Point2D>();
//        checkPointsInRange(root, rect);
//        return containedPoints;
//    }
//
//    private void checkPointsInRange(Node node, RectHV rect) {
//        if (node == null) return;
//        if (node.division == VERTICAL) {
//            if (node.value.x() > rect.xmax()) {
//                checkPointsInRange(node.left, rect); // go to the left
//            } else if (node.value.x() < rect.xmin()) {
//                checkPointsInRange(node.right, rect); // go to the right
//            } else { // go both ways and check self
//                checkPointsInRange(node.left, rect);
//                checkPointsInRange(node.right, rect);
//                if (rect.contains(node.value)) {
//                    containedPoints.add(node.value);
//                }
//            }
//        } else {
//            if (node.value.y() > rect.ymax()) {
//                checkPointsInRange(node.left, rect); // go to the left
//            } else if (node.value.y() < rect.ymin()) {
//                checkPointsInRange(node.right, rect); // go to the right
//            } else { // go both ways and check self
//                checkPointsInRange(node.left, rect);
//                checkPointsInRange(node.right, rect);
//                if (rect.contains(node.value)) {
//                    containedPoints.add(node.value);
//                }
//            }
//        }
//    }
//
//    /**
//     * a nearest neighbor in the set to point p; null if the set is empty
//     * */
//    public Point2D nearest(Point2D p) {
//        if (p == null) throw new NullPointerException();
//        nearestPoint = null;
//        comparisonPoint = p;
//        checkNearest(root);
//        return nearestPoint;
//    }
//
//    private void checkNearest(Node node) {
//        if (node == null) return;
//
//        if (nearestPoint == null) {
//            nearestPoint = node.value;
//        } else if (comparisonPoint.distanceTo(nearestPoint) > comparisonPoint.distanceTo(node.value)) {
//            nearestPoint = node.value;
//        }
//
//        if (node.division == VERTICAL) {
//            // If this point is closer than champion, explore both subtrees
//            if (comparisonPoint.distanceTo(nearestPoint) > comparisonPoint.distanceTo(node.value)) {
//                if (node.value.x() >= comparisonPoint.x()) {
//                    checkNearest(node.left);
//                    checkNearest(node.right);
//                } else {
//                    checkNearest(node.right);
//                    checkNearest(node.left);
//                }
//                // Otherwise explore the subtree closer to the comparison point
//            } else {
//                if(node.value.x() > comparisonPoint.x()) {
//                    checkNearest(node.left);
//                } else if(node.value.x() < comparisonPoint.x()) {
//                    checkNearest(node.right);
//                } else {
//                    checkNearest(node.left);
//                    checkNearest(node.right);
//                }
//            }
//        } else {
//            // If this point is closer than champion, explore both subtrees
//            if (comparisonPoint.distanceTo(nearestPoint) > comparisonPoint.distanceTo(node.value)) {
//                if(node.value.y() >= comparisonPoint.y()) {
//                    checkNearest(node.left);
//                    checkNearest(node.right);
//                } else {
//                    checkNearest(node.right);
//                    checkNearest(node.left);
//                }
//                // Otherwise explore the subtree closer to the comparison point
//            } else {
//                if(node.value.y() > comparisonPoint.y()) {
//                    checkNearest(node.left);
//                } else if(node.value.y() < comparisonPoint.y()) {
//                    checkNearest(node.right);
//                } else {
//                    checkNearest(node.left);
//                    checkNearest(node.right);
//                }
//            }
//        }
//    }
}
