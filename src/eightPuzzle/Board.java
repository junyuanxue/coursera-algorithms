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

    public int hamming() { // number of blocks out of place
        int count = 0;
        for (int i = 0; i < n * n; i++) {
            int row = i / n;
            int col = i % n;
            if (board[row][col] == i + 1) count++;
        }
        return count;
    }

    public int manhattan() { // sum of Manhattan distances between blocks and goal

    }

    public boolean isGoal() { // is this board the goal board?

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
