import java.util.ArrayList;
import java.util.Arrays;

/**
 * A fast solution of finding colinear points in a set of points.
 */

public class FastCollinearPoints {
    private static final int MIN_COLLINEAR_COUNT = 4;
    private LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new java.lang.NullPointerException();
        checkNoDuplicatedPoints(points);
        ArrayList<LineSegment> foundSegments = findSegments(points);
        segments = foundSegments.toArray(new LineSegment[foundSegments.size()]);
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
        ArrayList<LineSegment> foundSegments = new ArrayList<>();
        for(int i = 0; i < points.length - 1; i++) {
            Arrays.sort(points, i + 1, points.length, points[i].slopeOrder());
            double currentSlope = points[i].slopeTo(points[i + 1]);
            int consecutiveCount = 1;

            // Move through the rest of the array comparing slopes
            for(int j = i + 2; j < points.length; j++) {
                // When the same, increment the count because points are collinear
                if (points[i].slopeTo(points[j]) == currentSlope) {
                    consecutiveCount++;
                } else { // Otherwise, reset the count and set the slope to compare
                    currentSlope = points[i].slopeTo(points[j]);
                    consecutiveCount = 1;
                }

                if (consecutiveCount >= MIN_COLLINEAR_COUNT) {
                    foundSegments.add(new LineSegment(points[i], points[j]));
                }
            }
        }
        return foundSegments;
    }

    public int numberOfSegments() { // the number of line segments
        return segments.length;
    }

    public LineSegment[] segments() { // the line segments
        return segments;
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
