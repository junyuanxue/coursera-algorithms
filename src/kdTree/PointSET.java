import java.util.LinkedList;
import java.util.List;
import java.lang.NullPointerException;

public class PointSET {
    private SET<Point2D> points;

    /**
     * constructor, creates a SET of points
     * */
    public PointSET() {
        points = new SET<Point2D>();
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
        return points.size();
    }

    /**
     * add the point to the set (if it is not already in the set)
     * */
    public void insert(Point2D p) {
        if (p == null) throw new NullPointerException();
        points.add(p);
    }

    /**
     * does the set contain point p?
     * */
    public boolean contains(Point2D p) {
        if (p == null) throw new NullPointerException();
        return points.contains(p);
    }

    /**
     * draw all points to standard draw
     * */
    public void draw() {
        for (Point2D point : points) {
            point.draw();
        }
    }

    /**
     * all points that are inside the rectangle
     * */
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new NullPointerException();

        List<Point2D> foundPoints = new LinkedList<Point2D>();
        for (Point2D point : points) {
            if (rect.contains(point)) {
                foundPoints.add(point);
            }
        }
        return foundPoints;
    }

    /**
     * a nearest neighbor in the set to point p; null if the set is empty
     * */
    public Point2D nearest(Point2D p) {
        if (p == null) throw new NullPointerException();

        Point2D nearestPoint = null;

        for (Point2D point : points) {
            if (nearestPoint == null) {
                nearestPoint = point;
            } else {
                if (p.distanceTo(point) < p.distanceTo(nearestPoint)) {
                    nearestPoint = point;
                }
            }
        }

        return nearestPoint;
    }
}
