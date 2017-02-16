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
        swapQueue = new MinPQ<Node>(boardComparator);

        Node initialNode = new Node(initial, null);
        queue.insert(initialNode);

        Node initialSwap = new Node(initial.twin(), null);
        swapQueue.insert(initialSwap);

        while (!solvable && !swapSolvable) {
            solvable = canSolveStep(queue);
            swapSolvable = canSolveStep(swapQueue);
        }
    }

    private boolean canSolveStep(MinPQ<Node> queue) {
        // Pop min from queue, check if solved, if not, queue neighbours
        Node current = queue.delMin();

        if (current.board.isGoal()) {
            endNode = current;
            return true;
        }

        for (Board board : current.board.neighbors()) {
            if (current.parent == null || !board.equals(current.parent.board)) {
                Node neighbor = new Node(board, current);
                queue.insert(neighbor);
            }
        }

        return false;
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
