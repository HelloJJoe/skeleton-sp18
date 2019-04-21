package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.IllegalArgumentException;
import java.lang.IndexOutOfBoundsException;

public class Percolation {
    // create N-by-N grid, with all sites initially blocked
    private int grid;
    private boolean[] openSite;
    private WeightedQuickUnionUF wqu;
    private WeightedQuickUnionUF noBackWash;
    private int numberOfOpenSites;
    private int vTopSite;
    private int vBottomSite;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N is expected to be larger than zero");
        }
        grid = N;
        openSite = new boolean[N * N];
        numberOfOpenSites = 0;
        vTopSite = N * N;
        vBottomSite = N * N + 1;

        // Initiate the wquuf with N * N sites, 1 virtual top site and 1 virtual bottom site.
        wqu = new WeightedQuickUnionUF(N * N + 2);
        noBackWash = new WeightedQuickUnionUF(N * N + 1);
    }

    private int rsTo1D(int row, int col) {
        return row * grid + col;
    }
    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        int position = rsTo1D(row, col);

        if (isOpen(position)) return;

        openSite[position] = true;
        numberOfOpenSites++;

        if (row > 0 && isOpen(position - grid)) {
            wqu.union(position, position - grid);
            noBackWash.union(position, position - grid);
        }
        if (row < grid - 1 && isOpen(position + grid)) {
            wqu.union(position, position + grid);
            noBackWash.union(position, position + grid);
        }
        if (col > 0 && isOpen(position - 1)) {
            wqu.union(position, position - 1);
            noBackWash.union(position, position - 1);
        }
        if (col < grid - 1 && isOpen(position + 1)) {
            wqu.union(position, position + 1);
            noBackWash.union(position, position + 1);
        }
        if (row == 0) {
            wqu.union(position, vTopSite);
            noBackWash.union(position, vTopSite);
        }
        if (row == grid - 1) {
            wqu.union(position, vBottomSite);
        }
    }

    private void validate(int row, int col) {
        if (row < 0 || row > grid - 1 || col < 0 || col > grid - 1) {
            throw new IndexOutOfBoundsException("arguments are out of bounds");
        }
    }


    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        int position = rsTo1D(row, col);
        return isOpen(position);
    }


    // is the site (position) open?
    private boolean isOpen(int position) {
        return openSite[position];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        int position = rsTo1D(row, col);

        // Method 2: high efficiency
        return noBackWash.connected(position, vTopSite);

        // Method 1: low efficiency
//        if (isOpen(row, col) == false) return false;
//        for (int i = 0; i < grid; i++) {
//            if (isOpen(i) && wqu.connected(position, i)) {
//                return true;
//            }
//        }
//        return false;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        // Method 2: high efficiency
        return wqu.connected(vTopSite, vBottomSite);
        // Method 1: low efficiency
//        boolean isPercolates = false;
//        if (numberOfOpenSites() < grid) {
//            return false;
//        }
//        for (int i = 0; i < grid; i++) {
//            for (int j = 0; j < grid; j++) {
//                isPercolates = wqu.connected(i, grid * (grid - 1) + j);
//                if (isPercolates) {
//                    return true;
//                }
//            }
//        }
//        return isPercolates;
    }

    // use for unit testing (not required)
    public static void main(String[] args) {
        Percolation per = new Percolation(3);
        per.open(0,0);
        per.open(0,1);
        per.open(0,2);
        boolean test1 = per.isFull(0, 1);
        System.out.println(per.percolates());
        System.out.println(test1);
    }
}
