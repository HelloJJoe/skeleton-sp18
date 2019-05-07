package hw4.puzzle;

public class Board implements WorldState {

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    public Board(int[][] tiles) {

    }
    public int tileAt(int i, int j) {
        return 1;
    }
    public int size() {
        return 1;
    }
    public Iterable<WorldState> neighbors() {
        return null;
    }
    public int hamming() {
        return 1;
    }
    public int manhattan() {
        return 1;
    }
    public int estimatedDistanceToGoal() {
        return 1;
    }
    public boolean equals(Object y) {
        return false;
    }

}
