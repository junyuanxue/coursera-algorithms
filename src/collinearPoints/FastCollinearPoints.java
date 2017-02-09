import java.util.ArrayList;
import java.util.Arrays;

/**
 * A fast solution of finding colinear points in a set of points.
 */

public class FastCollinearPoints {
    private static final int MIN_COLLINEAR_COUNT = 4;
    private ArrayList<LineSegment> segments = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new java.lang.NullPointerException();
        checkNoDuplicatedPoints(points);
        findSegments(points);
    }

    /**
     * Given a point p, the following method determines whether p
     * participates in a set of 4 or more collinear points.
     * 1) Think of p as the origin.
     * 2) For each other point q, determine the slope it makes with p.
     * 3) Sort the points according to the slopes they makes with p.
     * 4) Check if any 3 (or more) adjacent points in the sorted order have equal slopes
     * with respect to p. If so, these points, together with p, are collinear.
     */

    private ArrayList<LineSegment> findSegments (Point[] points) {
        Point[] pointsCopy = points.clone();
        for (int i = 0; i < pointsCopy.length - (MIN_COLLINEAR_COUNT - 1); i++) {
            Arrays.sort(pointsCopy, pointsCopy[i].slopeOrder());

            for (int p = 0, first = 1, last = 2; last < pointsCopy.length; last++) {
                // find last collinear to p point
                while (last < pointsCopy.length
                        && pointsCopy[p].slopeTo(pointsCopy[first]) == pointsCopy[p].slopeTo(pointsCopy[last])) {
                    last++;
                }
                // if found at least 3 elements, make segment if it's unique
                if (last - first >= MIN_COLLINEAR_COUNT - 1
                        && pointsCopy[p].compareTo(pointsCopy[first]) != 0) {
                    segments.add(new LineSegment(pointsCopy[p], pointsCopy[last - 1]));
                }
                // Try to find next
                first = last;
            }
        }
    }

    public int numberOfSegments() { // the number of line segments
        return segments.size();
    }

    public LineSegment[] segments() { // the line segments
        return segments.toArray(new LineSegment[segments.size()]);
    }

    private void checkNoDuplicatedPoints(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Duplicated points in input.");
                }
            }
        }
    }
}
