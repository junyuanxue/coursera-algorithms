import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int trials;
    private double[] thresholds;

    /**
     * @param n n-by-n grid
     * @param numOfTrials number of trials to be performed
     */
    public PercolationStats(final int n, final int numOfTrials) {
        if (n < 1 || numOfTrials < 1) {
            throw new IllegalArgumentException();
        }
        trials = numOfTrials;
        thresholds = new double[trials];
        for (int i = 0; i < thresholds.length; i++)
        {
            thresholds[i] = calculateThreshold(n);
        }
    }

    /**
     * @param n n-by-n grid
     */
    private double calculateThreshold(final int n) {
        double counter = 0;
        int row, col;
        Percolation percolation = new Percolation(n);
        while (!percolation.percolates()) {
            row = StdRandom.uniform(n) + 1;
            col = StdRandom.uniform(n) + 1;
            if (!percolation.isOpen(row, col)) {
                counter++;
                percolation.open(row, col);
            }
        }
        return counter / (n * n);
    }

    public double mean() { // sample mean of percolation threshold
        return StdStats.mean(thresholds);
    }

    public double stddev() { // sample standard deviation of percolation threshold
        return StdStats.stddev(thresholds);
    }

    public double confidenceLo() { // low  endpoint of 95% confidence interval
        return mean() - 1.96 * stddev() / Math.sqrt(trials);
    }

    public double confidenceHi() { // high endpoint of 95% confidence interval
        return mean() + 1.96 * stddev() / Math.sqrt(trials);
    }

    public static void main(final String[] args) {
        PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());
    }
}
