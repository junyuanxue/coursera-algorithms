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

    public double mean() { // sample mean of percolation threshold

    }

    public double stddev() { // sample standard deviation of percolation threshold

    }

    public double confidenceLo() { // low  endpoint of 95% confidence interval

    }

    public double confidenceHi() { // high endpoint of 95% confidence interval

    }

    private double calculateThreshold(int n) {

    }

    public static void main(String[] args) {
        System.out.print("hello");
    }
}
