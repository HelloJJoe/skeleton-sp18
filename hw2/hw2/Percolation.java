package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.IllegalArgumentException;
import java.lang.IndexOutOfBoundsException;

public class Percolation {
    // create N-by-N grid, with all sites initially blocked
    private int grid;
    private boolean[] openSite;
    private WeightedQuickUnionUF wqu;
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
    }

    private int rsTo1D(int row, int col) {
        return row * grid + col;
    }
    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || row > grid || col < 0 || col > grid) {
            throw new IndexOutOfBoundsException("arguments are out of bounds");
        }
        if (isOpen(row, col)) return;

        int position = rsTo1D(row, col);
        openSite[position] = true;
        numberOfOpenSites++;

        // Top row of the N-by-N grids.
        if (position < grid) {
            checkUnion(position, bottomOfPosition(position));
            wqu.union(position, vTopSite);
            if (position == 0) {
                checkUnion(position, rightOfPosition(position));
            } else if (position == grid - 1) {
                checkUnion(position, leftOfPosition(position));
            } else {
                checkUnion(position, leftOfPosition(position));
                checkUnion(position, rightOfPosition(position));
            }
        //  Middle rows of the N-by-N grids.
        } else if (position >= grid && position < grid * (grid - 1)) {
            checkUnion(position, topOfPosition(position));
            checkUnion(position, bottomOfPosition(position));

            if (position % grid == 0) {
                checkUnion(position, rightOfPosition(position));
            } else if (position % grid == grid - 1) {
                checkUnion(position, leftOfPosition(position));
            } else {
                checkUnion(position, rightOfPosition(position));
                checkUnion(position, leftOfPosition(position));
            }
        // Bottom row of the N-by-N grid.
        } else {
            checkUnion(position, topOfPosition(position));
            wqu.union(position, vBottomSite);
            if (position == grid * (grid - 1)) {
                checkUnion(position, rightOfPosition(position));
            } else if (position == grid * grid - 1) {
                checkUnion(position, leftOfPosition(position));
            } else {
                checkUnion(position, rightOfPosition(position));
                checkUnion(position, leftOfPosition(position));
            }
        }
    }

    private int leftOfPosition(int position) {
        return position - 1;
    }

    private int rightOfPosition(int position) {
        return position + 1;
    }

    private int topOfPosition(int position) {
        return position - grid;
    }

    private int bottomOfPosition(int position) {
        return position + grid;
    }

    private void checkUnion(int position, int neighbor) {
        if (isOpen(neighbor)) {
            wqu.union(position, neighbor);

        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || row > grid || col < 0 || col > grid) {
            throw new IndexOutOfBoundsException("arguments are out of bounds");
        }
        int position = rsTo1D(row, col);
        return isOpen(position);
    }


    // is the site (position) open?
    private boolean isOpen(int position) {
        return openSite[position];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || row > grid || col < 0 || col > grid) {
            throw new IndexOutOfBoundsException("arguments are out of bounds");
        }
        int position = rsTo1D(row, col);
        // Method 2: high efficiency

//        return wqu.connected(position, vTopSite);
        // Method 1: low efficiency
        if (isOpen(row, col) == false) return false;
        for (int i = 0; i < grid; i++) {
            if (isOpen(i) && wqu.connected(position, i)) {
                return true;
            }
        }
        return false;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        // Method 2: high efficiency
//        return wqu.connected(vTopSite, vBottomSite);
        // Method 1: low efficiency
        boolean isPercolates = false;
        if (numberOfOpenSites() < grid) {
            return false;
        }
        for (int i = 0; i < grid; i++) {
            for (int j = 0; j < grid; j++) {
                isPercolates = wqu.connected(i, grid * (grid - 1) + j);
                if (isPercolates) {
                    return true;
                }
            }
        }
        return isPercolates;
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
