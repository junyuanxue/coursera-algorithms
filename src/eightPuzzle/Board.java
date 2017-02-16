public class Board {
    private int n;
    private final int[][] board; // 2-d array

    /**
     * Construct a board from an n-by-n array of blocks
     * (where blocks[i][j] = block in row i, column j)
     */
    public Board(int[][] blocks) {
        n = blocks.length;
        board = copyBoard(blocks);
    }

    /**
     * board dimension n
     */
    public int dimension() {
        return n;
    }

    /**
     * number of blocks out of place
     */
    public int hamming() {
        int count = 0;
        for (int i = 0; i < n * n; i++) {
            int row = i / n;
            int col = i % n;
            if (board[row][col] == i + 1) count++;
        }
        return count;
    }

    /**
     * sum of Manhattan distances between blocks and goal
     */
    public int manhattan() {
        int count = 0;
        for (int i = 0; i < n * n; i++) {
            int row = i / n;
            int col = i % n;
            int currentNum = board[row][col]
            if (currentNum == 0) continue;
            int goalRow = (currentNum - 1) / n;
            int goalCol = (currentNum - 1) % n;
            count += Math.abs(goalRow - row) + Math.abs(goalCol - col);
        }
        return count;
    }

    public boolean isGoal() { // is this board the goal board?
        for (int i = 0; i < n * (n - 1); i++) {
            int row = i / n;
            int col = i % n;
            if (board[row][col] != i + 1) return false;
        }
        return true;
    }

    public Board twin() { // a board that is obtained by exchanging any pair of blocks

    }

    public boolean equals(Object y) { // does this board equal y?

    }

    public Iterable<Board> neighbors() { // all neighboring boards

    }

    public String toString() { // string representation of this board (in the output format specified below)

    }

    private int[][] copyBoard(int[][] blocks) {
        int[][] board = new int[blocks.length][blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                board[i][j] = blocks[i][j];
            }
        }
        return board;
    }

    public static void main(String[] args) {

    }
}
