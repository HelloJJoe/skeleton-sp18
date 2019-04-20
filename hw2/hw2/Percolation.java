package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // create N-by-N grid, with all sites initially blocked
    private int grid;
    private boolean[] openSite;
    private WeightedQuickUnionUF wqu;

    public Percolation(int N) {
        grid = N;
        openSite = new boolean[N * N];
        wqu = new WeightedQuickUnionUF(N * N);
    }

    private int rsTo1D(int row, int col) {
        return row * grid + col;
    }
    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        int position = rsTo1D(row, col);
        openSite[position] = true;


    }
    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int position = rsTo1D(row, col);
        return isOpen(position);
    }


    // is the site (position) open?
    private boolean isOpen(int position) {
        return openSite[position];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int position = rsTo1D(row, col);
        if (isOpen(row, col) == false) {
            return false;
        }
        for (int i = 0; i < grid; i++) {
            if (isOpen(i) && wqu.connected(position, i)) {
                return true;
            }
        }
        return false;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return 1;
    }

    // does the system percolate?
    public boolean percolates() {
        return true;
    }

    // use for unit testing (not required)
    public static void main(String[] args) {
        Percolation per = new Percolation(10);
        per.open(0,0);
        boolean test1 = per.isOpen(0,0);
        boolean test2 = per.isOpen(1, 0);
        System.out.println(test1);
        System.out.println(test2);
    }
}
