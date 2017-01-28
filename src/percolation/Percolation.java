import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static int N;
    private int topSite;
    private int bottomSite;
    private WeightedQuickUnionUF unionFind;
    private byte[] sites;
    private int numOfOpenSites;

    public Percolation(int n) { // create n-by-n grid, with all sites blocked
        if (n < 1) {
            throw new IllegalArgumentException();
        }
        N = n;
        unionFind = new WeightedQuickUnionUF(N * N + 2);
        sites = new byte[N * N]; // 0 - closed site, 1 - open site, 2 - full site
        topSite = N * N; // imaginary site to be connected with top row
        bottomSite = N * N + 1; // imaginary site to be connected with bottom row
        numOfOpenSites = 0;
    }

    public void open(int row, int col) { // open site (row, col) if it is not open already
        isInBounds(row, col);
        if (isOpen(row, col)) {
            return;
        }

        int currentSite = convert2dTo1dPosition (row, col);
        sites[currentSite] = 1;
        numOfOpenSites ++;

        if (row == 1 && !unionFind.connected(currentSite, topSite)) {
            unionFind.union(currentSite, topSite);
        }

        if (row == N && !unionFind.connected(currentSite, bottomSite)) {
            unionFind.union(currentSite, bottomSite);
        }

        if (row < N) { // connect with site from bottom
            connectWithNeighbourSite(currentSite, row + 1, col);
        }

        if (row > 1) { // connect with upper site
            connectWithNeighbourSite(currentSite, row - 1, col);
        }

        if (col > 1) { // connect with site to the left
            connectWithNeighbourSite(currentSite, row, col - 1);
        }

        if (col < N) { // connect with site to the right
            connectWithNeighbourSite(currentSite, row, col + 1);
        }
    }

    public boolean isOpen(int row, int col)  {
        isInBounds(row, col);
        int site = convert2dTo1dPosition(row, col);
        return sites[site] == 1;
    }

    public boolean isFull(int row, int col) {
        isInBounds(row, col);
        int currentSite = convert2dTo1dPosition(row, col);
        if (isOpen(row, col) && unionFind.connected(topSite, currentSite)) {
            return true;
        } else {
            return false;
        }
    }

    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    public boolean percolates() {
        if (unionFind.connected(topSite, bottomSite)) {
            return true;
        } else {
            return false;
        }
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

    private void connectWithNeighbourSite (int currentSite, int neighbourRow, int neighbourCol) {
        if (isOpen(neighbourRow, neighbourCol)) {
            int neighbourSite = convert2dTo1dPosition(neighbourRow, neighbourCol);
            unionFind.union(currentSite, neighbourSite);
        }
    }
}