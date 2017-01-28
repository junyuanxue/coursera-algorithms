import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static int N;
    private int top;
    private int bottom;
    private WeightedQuickUnionUF unionFind;
    private byte[] sites;

    public Percolation(int n) { // create n-by-n grid, with all sites blocked
        N = n;
        unionFind = new WeightedQuickUnionUF(N * N + 2);
        sites = new byte[N * N]; // 0 - closed site, 1 - open site, 2 - full site
        top = N * N + 1; // imaginary site to be connected with top row
        bottom = N * N + 2; // imaginary site to be connected with bottom row
    }

    public void open(int row, int col) { // open site (row, col) if it is not open already
        isInBounds(row, col);
        if (isOpen(row, col)) {
            return;
        }

        int currentSite = convert2dTo1dPosition (row, col);
        this.sites[currentSite] = 1;


    }

    public boolean isOpen(int row, int col)  {
        return false;
    }

    public boolean isFull(int row, int col) {
        return true;
    }

    public int numberOfOpenSites() {
        return 0;
    }

    public boolean percolates() {
        return true;
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);
        percolation.open(2, 2);
    }

    private boolean isInBounds(int row, int col) {
        if (row < 1 || row > N || col < 1 || col > N) {
            throw new IndexOutOfBoundsException();
        }
        return true;
    }

    private int convert2dTo1dPosition (int row, int col) {
        return N * (row - 1) + col - 1;
    }
}