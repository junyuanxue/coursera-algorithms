import java.util.ArrayList;
import java.util.Arrays;

/**
 * A BruteForce solution for finding collinear points in a set of points.
 */

public class BruteCollinearPoints {
    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) { // finds all line segments containing 4 points
        checkNoDuplicatedPoints(points);

        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);
        ArrayList<LineSegment> foundSegments = findSegments(pointsCopy);
        segments = foundSegments.toArray(new LineSegment[foundSegments.size()]);
    }

    private ArrayList<LineSegment> findSegments(Point[] points) {
        ArrayList<LineSegment> foundSegments = new ArrayList<>();
        for (int p = 0; p < pointsCopy.length - 3; p++) {
            for (int q = p + 1; q < pointsCopy.length - 2; q++) {
                for (int r = q + 1; r < pointsCopy.length - 1; q++) {
                    for (int s = r + 1; s < pointsCopy.length; s++) {
                        Point pp = points[p];
                        Point qq = points[q];
                        Point rr = points[r];
                        Point ss = points[s];
                        if (pp.slopeTo(qq) == pp.slopeTo(rr) &&
                                pp.slopeTo(qq) == pp.slopeTo(ss)) {
                            foundSegments.add(new LineSegment(pp, ss));
                        }
                    }
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
        for (i = 0; i < points.length; i++) {
            for (j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Duplicated points in input.");
                }
            }
        }
    }
}
