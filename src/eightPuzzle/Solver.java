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

    /**
     * find a solution to the initial board (using the A* algorithm)
     */
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

    /**
     * is the initial board solvable?
     */
    public boolean isSolvable() {
        return (lastMove != null);
    }

    /**
     * min number of moves to solve initial board; -1 if unsolvable
     */
    public int moves() {
        if (isSolvable()) {
            return lastMove.numOfMoves;
        }
        return -1;
    }

    /**
     * sequence of boards in a shortest solution; null if unsolvable
     */
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;

        Stack<Board> moves = new Stack<Board>();
        while (lastMove != null) {
            moves.push(lastMove.board);
            lastMove = lastMove.previous;
        }

        return moves;
    }

    /**
     *  solve a slider puzzle
     */
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
