import java.util.ArrayList;
import java.util.Arrays;

/**
 * A BruteForce solution for finding collinear points in a set of points.
 */

public class BruteCollinearPoints {
    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new java.lang.NullPointerException();
        checkNoDuplicatedPoints(points);

        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);
        ArrayList<LineSegment> foundSegments = findSegments(pointsCopy);
        segments = foundSegments.toArray(new LineSegment[foundSegments.size()]);
    }

    /**
     * Examine 4 points at a time and checks whether they
     * all lie on the same line segment, returning all such line segments.
     *
     * To check whether the 4 points p, q, r, and s are collinear,
     * check whether the three slopes between p and q, between p and r,
     * and between p and s are all equal.
     *
     * @param points array of points in the plane
     */
    private ArrayList<LineSegment> findSegments(Point[] points) {
        ArrayList<LineSegment> foundSegments = new ArrayList<>();
        for (int p = 0; p < points.length - 3; p++) {
            for (int q = p + 1; q < points.length - 2; q++) {
                for (int r = q + 1; r < points.length - 1; r++) {
                    for (int s = r + 1; s < points.length; s++) {
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
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Duplicated points in input.");
                }
            }
        }
    }
}
