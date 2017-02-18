import java.util.Comparator;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private class Move implements Comparable<Move> {
        private Move previous;
        private Board board;
        private int numOfMoves = 0;

        public Move(Board board) {
            this.board = board;
        }

        public Move(Board board, Move previous) {
            this.board = board;
            this.previous = previous;
            this.numOfMoves = previous.numOfMoves + 1;
        }

        public int compareTo(Move move) {
            return (this.board.manhattan() - move.board.manhattan()) + (this.numOfMoves - move.numOfMoves);
        }
    }

    private Move lastMove;

    public Solver(Board initial) {
        MinPQ<Move> moves = new MinPQ<Move>();
        moves.insert(new Move(initial));

        MinPQ<Move> twinMoves = new MinPQ<Move>();
        twinMoves.insert(new Move(initial.twin()));

        while (true) {
            lastMove = expand(moves);
            if (lastMove != null || expand(twinMoves) != null) return;
        }
    }

    private Move expand(MinPQ<Move> moves) {
        if (moves.isEmpty()) return null;
        Move currentMove = moves.delMin();
        if (currentMove.board.isGoal()) return currentMove;
        for (Board neighbor : currentMove.board.neighbors()) {
            if (currentMove.previous == null || !neighbor.equals(currentMove.previous.board)) {
                moves.insert(new Move(neighbor, currentMove));
            }
        }
        return null;
    }

    public boolean isSolvable() {
        return (lastMove != null);
    }

    public int moves() {
        return isSolvable() ? lastMove.numOfMoves : -1;
    }

    public Iterable<Board> solution() {
        if (!isSolvable()) return null;

        Stack<Board> moves = new Stack<Board>();
        while (lastMove != null) {
            moves.push(lastMove.board);
            lastMove = lastMove.previous;
        }

        return moves;
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

//    private MinPQ<Node> queue;
//    private MinPQ<Node> swapQueue;
//    private boolean solvable;
//    private boolean swapSolvable;
//    private Node endNode;
//
//    private class Node {
//        private Board board;
//        private Node parent;
//
//        public Node(Board board, Node parent) {
//            this.board = board;
//            this.parent = parent;
//        }
//    }
//
//    private static Comparator<Node> boardComparator = new Comparator<Node>() {
//        @Override
//        public int compare(Node first, Node second) {
//            return first.board.manhattan() - second.board.manhattan();
//        }
//    };
//
//    /**
//     * find a solution to the initial board (using the A* algorithm)
//     */
//    public Solver(Board initial) {
//        solvable = false;
//        swapSolvable = false;
//
//        queue = new MinPQ<Node>(boardComparator);
//        swapQueue = new MinPQ<Node>(boardComparator);
//
//        Node initialNode = new Node(initial, null);
//        queue.insert(initialNode);
//
//        Node initialSwap = new Node(initial.twin(), null);
//        swapQueue.insert(initialSwap);
//
//        while (!solvable && !swapSolvable) {
//            solvable = canSolveStep(queue);
//            swapSolvable = canSolveStep(swapQueue);
//        }
//    }
//
//    private boolean canSolveStep(MinPQ<Node> queue) {
//        // Pop min from queue, check if solved, if not, queue neighbours
//        Node current = queue.delMin();
//
//        if (current.board.isGoal()) {
//            endNode = current;
//            return true;
//        }
//
//        for (Board board : current.board.neighbors()) {
//            if (current.parent == null || !board.equals(current.parent.board)) {
//                Node neighbor = new Node(board, current);
//                queue.insert(neighbor);
//            }
//        }
//
//        return false;
//    }
//
//    /**
//     * is the initial board solvable?
//     */
//    public boolean isSolvable() {
//        return solvable;
//    }
//
//    /**
//     * min number of moves to solve initial board; -1 if unsolvable
//     */
//    public int moves() {
//        if (isSolvable()) {
//            Node current = endNode;
//            int moves = 0;
//            while (current.parent != null) {
//                moves++;
//                current = current.parent; // trace back to initial node
//            }
//            return moves;
//        }
//        return -1;
//    }
//
//    /**
//     * sequence of boards in a shortest solution; null if unsolvable
//     */
//    public Iterable<Board> solution() {
//        if (isSolvable()) {
//            Stack<Board> solution = new Stack<Board>();
//            Node current = endNode;
//            solution.push(endNode.board);
//            while (current.parent != null) {
//                solution.push(current.parent.board);
//                current = current.parent;
//            }
//            return solution;
//        }
//        return null;
//    }
//

}
