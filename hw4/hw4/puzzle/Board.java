package hw4.puzzle;
import java.util.Set;
import java.util.HashSet;

public class Board implements WorldState {

    private final int[][] start;
    private final int[][] goal;
    private int size;
    private int blankCol;
    private int blankRow;

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    public Board(int[][] tiles) {

        size = tiles.length;
        goal = new int[size][size];
        start = new int[size][size];
        int counter = 1;

        // initial goal and get the blankCol and blankRow
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (tiles[r][c] == 0) {
                    blankCol = c;
                    blankRow = r;
                }

                goal[r][c] = counter % (size * size);
                counter++;
            }
            System.arraycopy(tiles[r], 0, start[r], 0, size);
        }
    }

    public int tileAt(int r, int c) {
        if (c < 0 || c > size || r < 0 || r > size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return start[r][c];
    }
    public int size() {
        return size;
    }

    public Iterable<WorldState> neighbors() {
        Set<WorldState> neighbors = new HashSet<>();
        if (blankRow > 0) {
            neighbors.add(new Board(swap(blankRow - 1, blankCol)));
        }
        if (blankRow < size - 1) {
            neighbors.add(new Board(swap(blankRow + 1, blankCol)));
        }
        if (blankCol > 0) {
            neighbors.add(new Board(swap(blankRow, blankCol - 1)));
        }
        if (blankCol < size - 1) {
            neighbors.add(new Board(swap(blankRow, blankCol + 1)));
        }
        return neighbors;
    }

    private int[][] swap(int neiRow, int neiCol) {

        int[][] toReturn = new int[start.length][start.length];
        for (int i = 0; i < start.length; i++) {
            System.arraycopy(start[i], 0, toReturn[i], 0, start.length);
        }

        int temp = tileAt(blankRow, blankCol);
        toReturn[blankRow][blankCol] = tileAt(neiRow, neiCol);
        toReturn[neiRow][neiCol] = temp;
        return toReturn;
    }

    public int hamming() {
        int toReturn = 0;
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (tileAt(r, c) != goal[r][c] && tileAt(r, c) != 0) {
                    toReturn++;
                }
            }
        }
        return toReturn;
    }
    public int manhattan() {
        int toReturn = 0;
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                int number = tileAt(r, c);
                if (number != 0) {
                    float cal = number / (float) size;
                    int goalRow = (int) Math.ceil(cal) - 1;
                    int goalCow = Math.floorMod((number % size) - 1, size);
                    toReturn += Math.abs(c - goalCow) + Math.abs(r - goalRow);
                }
            }
        }
        return toReturn;
    }

    public int estimatedDistanceToGoal() {
        return manhattan();
    }
    public boolean equals(Object y) {
        if (y == this) {
            return true;
        }
        if (y == null || y.getClass() != this.getClass()) {
            return false;
        }
        if (this.equals(y)) {
            return true;
        }

        Board board = (Board) y;
        if (board.size() != this.size()) {
            return false;
        }
        boolean flag = true;
        for (int r = 0; r < this.size; r++) {
            for (int c = 0; c < this.size; c++) {
                if (!flag) {
                    return false;
                }
                flag = (board.tileAt(r, c) == this.tileAt(r, c));
            }
        }
        return flag;
    }
    public int hashCode() {
        int result = start != null ? start.hashCode() : null;
        result = 31 * result + (goal != null ? goal.hashCode() : null);
        return result;
    }
}

