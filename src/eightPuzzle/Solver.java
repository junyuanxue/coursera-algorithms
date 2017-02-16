import java.util.Comparator;

public class Solver {
    private MinPQ<Node> queue;
    private MinPQ<Node> swapQueue;
    private boolean solvable;
    private boolean swapSolvable;
    private Node endNode;

    private class Node {
        private Board board;
        private Node parent;

        public Node(Board board, Node parent) {
            this.board = board;
            this.parent = parent;
        }
    }

    private static Comparator<Node> boardComparator = new Comparator<Node>() {
        @Override
        public int compare(Node first, Node second) {
            return first.board.manhattan() - second.board.manhattan();
        }
    };

    /**
     * find a solution to the initial board (using the A* algorithm)
     */
    public Solver(Board initial) {
        solvable = false;
        swapSolvable = false;

        queue = new MinPQ<Node>(boardComparator);
    }

    // is the initial board solvable?
    public boolean isSolvable() {

    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {

    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {

    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
