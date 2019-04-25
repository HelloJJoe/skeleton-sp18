package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
import edu.princeton.cs.introcs.Stopwatch;

public class PercolationStats {
    private double[] thresholds;
    private int T;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("arguments out of rule");
        }
        this.T = T;
        int t = 0;
        thresholds = new double[T];
        while (!(t == T)) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0, N);
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                }

            }
            double threshold = (double) p.numberOfOpenSites() / (N * N);
            thresholds[t] = threshold;
            t++;
        }

    }
    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

    public static void main(String[] args) {
        int counter = 0;
        int initN = 10;
        while (counter < 30) {
            PercolationFactory pf = new PercolationFactory();
            PercolationStats ps = new PercolationStats(initN, 30, pf);
            System.out.println(ps.stddev());
            initN += 10;
            counter ++;
        }

    }
}
