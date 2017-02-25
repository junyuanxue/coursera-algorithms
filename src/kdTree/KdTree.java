import java.util.LinkedList;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

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
            if (currentPosition.division == VERTICAL) { // slice vertically
                if (p.x() < currentPosition.value.x()) {
                    if (currentPosition.left == null) {
                        newPoint.division = HORIZONTAL;
                        currentPosition.left = newPoint;
                        size++;
                        return;
                    } else {
                        currentPosition = currentPosition.left;
                    }
                } else {
                    if (currentPosition.right == null) {
                        newPoint.division = HORIZONTAL;
                        currentPosition.right = newPoint;
                        size++;
                        return;
                    } else {
                        currentPosition = currentPosition.right;
                    }
                }
            } else { // slice horizontally
                if (p.y() < currentPosition.value.y()) {
                    if (currentPosition.left == null) {
                        newPoint.division = VERTICAL;
                        currentPosition.left = newPoint;
                        size++;
                        return;
                    } else {
                        currentPosition = currentPosition.left;
                    }
                } else {
                    if (currentPosition.right == null) {
                        newPoint.division = VERTICAL;
                        currentPosition.right = newPoint;
                        size++;
                        return;
                    } else {
                        currentPosition= currentPosition.right;
                    }
                }
            }
        }
    }

    /**
     * does the set contain point p?
     * */
    public boolean contains(Point2D p) {
        if (p == null) throw new NullPointerException();

        Node currentPosition = root;
        while (currentPosition != null) {
            if(p.equals(currentPosition.value)) {
                return true;
            }

            if (currPos.division == VERTICAL) { // use first bit to determine if level is even or odd
                if (p.x() < currentPosition.value.x()) { // vertical slice
                    currentPosition = currentPosition.left;
                } else {
                    currentPosition = currentPosition.right;
                }
            } else {
                if(p.y() < currentPosition.value.y()) { // horizontal slice
                    currentPosition = currentPosition.left;
                } else {
                    currentPosition = currentPosition.right;
                }
            }
        }
        return false;
    }

    /**
     * draw all points to standard draw
     * */
    public void draw() {
        if (root != null) {
            drawNodes(root);
        }
    }

    private void drawNodes(Node node) {
        node.value.draw();

        if (node.right != null) {
            drawNodes(node.right);
        }

        if (node.left != null) {
            drawNodes(node.left);
        }g
    }

    /**
     * all points that are inside the rectangle
     * */
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new NullPointerException();
        containedPoints = new LinkedList<Point2D>();
        checkPointsInRange(root, rect);
        return containedPoints;
    }

    private checkPointsInRange(Node root, RectHV rect) {
        if (node == null) return;
        if (node.division == VERTICAL) {
            if (node.value.x() > rect.xmax()) {
                checkPointsInRange(node.left, rect); // go to the left
            } else if (node.value.x() < rect.xmin()) {
                checkPointsInRange(node.right, rect); // go to the right
            } else { // go both ways and check self
                checkPointsInRange(node.left, rect);
                checkPointsInRange(node.right, rect);
                if (rect.contains(node.value)) {
                    containedPoints.add(node.value);
                }
            }
        } else {
            if(node.value.y() > rect.ymax()) {
                checkPointsInRange(node.left, rect); // go to the left
            } else if (node.value.y() < rect.ymin()) {
                checkPointsInRange(node.right, rect); // go to the right
            } else { // go both ways and check self
                checkPointsInRange(node.left, rect);
                checkPointsInRange(node.right, rect);
                if (rect.contains(node.value)) {
                    containedPoints.add(node.value);
                }
            }
        }
    }

    /**
     * a nearest neighbor in the set to point p; null if the set is empty
     * */
    public Point2D nearest(Point2D p) {
        if (p == null) throw new NullPointerException();
        nearestPoint = null;
        comparisonPoint = p;
        checkNearest(root);
        return nearestPoint;
    }

    private checkNearst() {
        if (node == null) return;

        if (nearestPoint == null) {
            nearestPoint = node.value;
        } else if (comparisonPoint.distanceTo(nearestPoint) > comparisonPoint.distanceTo(node.value)) {
            nearestPoint = node.value;
        }

        if (node.division == VERTICAL) {
            // If this point is closer than champion, explore both subtrees
            if (comparisonPoint.distanceTo(nearestPoint) > comparisonPoint.distanceTo(node.value)) {
                if (node.value.x() >= comparisonPoint.x()) {
                    checkNearest(node.left);
                    checkNearest(node.right);
                } else {
                    checkNearest(node.right);
                    checkNearest(node.left);
                }
                // Otherwise explore the subtree closer to the comparison point
            } else {
                if(node.value.x() > comparisonPoint.x()) {
                    checkNearest(node.left);
                } else if(node.value.x() < comparisonPoint.x()) {
                    checkNearest(node.right);
                } else {
                    checkNearest(node.left);
                    checkNearest(node.right);
                }
            }
        } else {
            // If this point is closer than champion, explore both subtrees
            if (comparisonPoint.distanceTo(nearestPoint) > comparisonPoint.distanceTo(node.value)) {
                if(node.value.y() >= comparisonPoint.y()) {
                    checkNearest(node.left);
                    checkNearest(node.right);
                } else {
                    checkNearest(node.right);
                    checkNearest(node.left);
                }
                // Otherwise explore the subtree closer to the comparison point
            } else {
                if(node.value.y() > comparisonPoint.y()) {
                    checkNearest(node.left);
                } else if(node.value.y() < comparisonPoint.y()) {
                    checkNearest(node.right);
                } else {
                    checkNearest(node.left);
                    checkNearest(node.right);
                }
            }
        }
    }
}
