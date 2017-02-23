import java.util.LinkedList;
import java.util.List;

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
    public              void insert(Point2D p)              // add the point to the set (if it is not already in the set)
    public           boolean contains(Point2D p)            // does the set contain point p?
    public              void draw()                         // draw all points to standard draw
    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle
    public           Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args)                  // unit testing of the methods (optional)
}
