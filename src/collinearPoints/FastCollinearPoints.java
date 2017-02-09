import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

/**
 * A fast solution of finding colinear points in a set of points.
 */

public class FastCollinearPoints {
    private List<LineSegment> segments = new ArrayList<>();

    public FastCollinearPoints(Point[] points) { // finds all line segments containing 4 or more points
        checkNoDuplicatedPoints(points);
        compareSlopes(points);
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

    private void compareSlopes (Point[] points) {
        Point[] pointsCopy = Arrays.copyOf(points, points.length);

        for (Point startPoint : points) {
            Arrays.sort(pointsCopy, startPoint.slopeOrder());
            List<Point> pointsOnSameSlope = new ArrayList<>();
            double slope = 0;
            double previousSlope = Double.NEGATIVE_INFINITY;

            for (int i = 1; i < pointsCopy.length; i++) {
                Point currentPoint = pointsCopy[i];
                slope = startPoint.slopeTo(currentPoint);
                if (slope == previousSlope) {
                    pointsOnSameSlope.add(currentPoint);
                } else {
                    if (pointsOnSameSlope.size() >= 3) {
                        pointsOnSameSlope.add(startPoint);
                        addSegment(pointsOnSameSlope);
                    }
                    pointsOnSameSlope.clear(); // reset collection of points with the same slope
                    pointsOnSameSlope.add(currentPoint);
                }
                previousSlope = slope;
            }

            if (pointsOnSameSlope.size() >= 3) {
                pointsOnSameSlope.add(startPoint);
                addSegment(pointsOnSameSlope);
            }
        }
    }

    private void addSegment(List<Point> pointsOnSameSlope) {
        Collections.sort(pointsOnSameSlope);
        Point startPoint = pointsOnSameSlope.get(0);
        Point endPoint = pointsOnSameSlope.get(pointsOnSameSlope.size() - 1);
        segments.add(new LineSegment(startPoint, endPoint));
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
