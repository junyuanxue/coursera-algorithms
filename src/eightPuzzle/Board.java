import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

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
            int number = board[row][col];
            if (number != 0 && number != i + 1) count++;
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
            int currentNum = board[row][col];
            if (currentNum == 0) continue;
            int goalRow = (currentNum - 1) / n;
            int goalCol = (currentNum - 1) % n;
            count += Math.abs(goalRow - row) + Math.abs(goalCol - col);
        }
        return count;
    }

    /**
     * is this board the goal board?
     */
    public boolean isGoal() {
        for (int i = 0; i < n * (n - 1); i++) {
            int row = i / n;
            int col = i % n;
            if (board[row][col] != i + 1) return false;
        }
        return true;
    }

    /**
     * a board that is obtained by exchanging any pair of blocks
     */
    public Board twin() {
        int[][] copy = copyBoard(board);
        boolean hasSwapped = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (copy[i][j] > 0 && copy[i][j+1] > 0) {
                    int currentValue = copy[i][j];
                    copy[i][j] = copy[i][j+1];
                    copy[i][j+1] = currentValue;
                    hasSwapped = true;
                    break;
                }
            }
            if (hasSwapped) break;
        }
        return new Board(copy);
    }

    /**
     * does this board equal y?
     */
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (!(y instanceof Board)) return false;
        if (((Board) y).n != n) return false;
        for (int r = 0; r < this.n; r++) {
            if (!Arrays.equals(this.board[r], ((Board) y).board[r])) return false;
        }
        return true;
    }

    /**
     * all neighboring boards
     */
    public Iterable<Board> neighbors() {
        int spaceRow = 0;
        int spaceCol = 0;

        // Find the empty square
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    spaceRow = i;
                    spaceCol = j;
                }
            }
        }

        List<Board> neighbors = new LinkedList<Board>();

        // Down
        if (spaceRow < n - 1) {
            int[][] downBlocks = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    downBlocks[i][j] = board[i][j];
                }
            }

            int temp = downBlocks[spaceRow][spaceCol];
            downBlocks[spaceRow][spaceCol] = downBlocks[spaceRow + 1][spaceCol];
            downBlocks[spaceRow + 1][spaceCol] = temp;

            neighbors.add(new Board(downBlocks));
        }

        // Up
        if (spaceRow > 0) {
            int[][] upBlocks = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    upBlocks[i][j] = board[i][j];
                }
            }

            int temp = upBlocks[spaceRow][spaceCol];
            upBlocks[spaceRow][spaceCol] = upBlocks[spaceRow - 1][spaceCol];
            upBlocks[spaceRow - 1][spaceCol] = temp;

            neighbors.add(new Board(upBlocks));
        }

        // Left
        if (spaceCol > 0) {
            int[][] leftBlocks = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    leftBlocks[i][j] = board[i][j];
                }
            }

            int temp = leftBlocks[spaceRow][spaceCol];
            leftBlocks[spaceRow][spaceCol] = leftBlocks[spaceRow][spaceCol - 1];
            leftBlocks[spaceRow][spaceCol - 1] = temp;

            neighbors.add(new Board(leftBlocks));
        }

        //Right
        if (spaceCol < dimension() - 1) {
            int[][] rightBlocks = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    rightBlocks[i][j] = board[i][j];
                }
            }

            int temp = rightBlocks[spaceRow][spaceCol];
            rightBlocks[spaceRow][spaceCol] = rightBlocks[spaceRow][spaceCol + 1];
            rightBlocks[spaceRow][spaceCol + 1] = temp;

            neighbors.add(new Board(rightBlocks));
        }

        return neighbors;
//        List<Board> boards = new ArrayList<Board>();
//        int[] moveX = {1, -1, 0, 0};
//        int[] moveY = {0, 0, 1, -1};
//        boolean hasFound = false;
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if (board[i][j] == 0) {
//                    for (int k = 0; k < 4; k++) {
//                        int neighbourRow = i + moveX[k];
//                        int neighbourCol = i + moveY[k];
//                        if(neighbourRow >= 0
//                                && neighbourCol >= 0
//                                && neighbourRow < n
//                                && neighbourCol < n) {
//                            int[][] copy = copyBoard(board);
//                            copy[i][j] = board[neighbourRow][neighbourCol];
//                            copy[neighbourRow][neighbourCol] = board[i][j];
//                            Board foundBoard = new Board(copy);
//                            boards.add(foundBoard);
//                        }
//                    }
//                    hasFound = true;
//                    break;
//                }
//            }
//            if (hasFound) break;
//        }
//        return boards;
    }

    /**
     * string representation of this board
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                stringBuilder.append(this.board[i][j]+ " ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
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
