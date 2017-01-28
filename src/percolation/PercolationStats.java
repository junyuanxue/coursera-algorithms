import percolation.Percolation;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private int trials;
    private double[] thresholds;

    public PercolationStats(int n, int numOfTrials) { // perform trials independent experiments on an n-by-n grid
        if (n < 1 || trials < 1) {
            throw new IllegalArgumentException();
        }
        trials = numOfTrials;
        thresholds = new double[trials];
        for (int i = 0; i < thresholds.length; i++)
        {
            thresholds[i] = calculateThreshold(n);
        }
    }

    private double calculateThreshold(int n) {
        double counter = 0;
        int row, col;
        Percolation percolation = new Percolation(n);
        while (!percolation.percolates()) {
            row = StdRandom.uniform(n) + 1;
            col = StdRandom.uniform(n) + 1;
            if (percolation(!isOpen(row, col))) {
                counter ++;
                percolation.open(row, col);
            }
        }
        return counter / (n * n);
    }

    public double mean() { // sample mean of percolation threshold

    }

    public double stddev() { // sample standard deviation of percolation threshold

    }

    public double confidenceLo() { // low  endpoint of 95% confidence interval

    }

    public double confidenceHi() { // high endpoint of 95% confidence interval

    }

    public static void main(String[] args) {
        System.out.print("hello");
    }
}
