import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static int N;
    private int topSite;
    private int bottomSite;
    private WeightedQuickUnionUF unionFind;
    private byte[] sites;
    private int numOfOpenSites;

    /**
     * @param n n-by-n grid
     */
    public Percolation(final int n) {
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

    /**
     * @param row row position of site
     * @param col col position of site
     */
    public void open(final int row, final int col) {
        isInBounds(row, col);
        if (isOpen(row, col)) {
            return;
        }

        int currentSite = convert2dTo1dPosition(row, col);
        sites[currentSite] = 1;
        numOfOpenSites++;

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

    /**
     * @param row row position of site
     * @param col col position of site
     */
    public boolean isOpen(final int row, final int col)  {
        isInBounds(row, col);
        int site = convert2dTo1dPosition(row, col);
        return sites[site] == 1;
    }

    /**
     * @param row row position of site
     * @param col col position of site
     */
    public boolean isFull(final int row, final int col) {
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
        return unionFind.connected(topSite, bottomSite);
    }

    /**
     * @param row row position of site
     * @param col col position of site
     */
    private boolean isInBounds(final int row, final int col) {
        if (row < 1 || row > N || col < 1 || col > N) {
            throw new IndexOutOfBoundsException();
        }
        return true;
    }

    /**
     * @param row row position of site
     * @param col col position of site
     */
    private int convert2dTo1dPosition(final int row, final int col) {
        return N * (row - 1) + col - 1;
    }

    /**
     * @param currentSite 1d position of current site
     * @param neighbourRow 2d position of neighbouring site
     * @param neighbourCol 2d position of neighbouring site
     */
    private void connectWithNeighbourSite(final int currentSite, final int neighbourRow, final int neighbourCol) {
        if (isOpen(neighbourRow, neighbourCol)) {
            int neighbourSite = convert2dTo1dPosition(neighbourRow, neighbourCol);
            unionFind.union(currentSite, neighbourSite);
        }
    }
}