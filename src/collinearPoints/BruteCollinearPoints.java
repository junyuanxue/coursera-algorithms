import java.util.ArrayList;
import java.util.Arrays;

/**
 * A BruteForce solution for finding collinear points in a set of points.
 */

public class BruteCollinearPoints {
    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) { // finds all line segments containing 4 points
        checkNoDuplicatedPoints(points);

        ArrayList<LineSegment> foundSegments = new ArrayList<>();
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);

        for (int p = 0; p < pointsCopy.length - 3; p++) {
            for (int q = p + 1; q < pointsCopy.length - 2; q++) {
                for (int r = q + 1; r < pointsCopy.length - 1; q++) {
                    for (int s = r + 1; s < pointsCopy.length; s++) {

                    }
                }
            }
        }

    }

    private ArrayList<LineSegment> findSegments() {

    }

    public int numberOfSegments() { // the number of line segments

    }

    public LineSegment[] segments() { // the line segments

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
