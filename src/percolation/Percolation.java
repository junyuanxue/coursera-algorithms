import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static int N;
    private WeightedQuickUnionUF unionFind;

    public Percolation(int n) { // create n-by-n grid, with all sites blocked
        N = n;
        unionFind = new WeightedQuickUnionUF(N * N + 2);
    }

    public void open(int row, int col) { // open site (row, col) if it is not open already
        if (isInBounds(row, col) && !isOpen(row, col)) {
            
        }


    }

    public boolean isOpen(int row, int col)  {

    }

    public boolean isFull(int row, int col) {

    }

    public int numberOfOpenSites() {

    }

    public boolean percolates() {

    }

    public static void main(String[] args) {

    }

    private boolean isInBounds(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IndexOutOfBoundsException();
        }
        return true;
    }
}