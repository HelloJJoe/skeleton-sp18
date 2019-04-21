package hw2;
import java.lang.IllegalArgumentException;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] thresholds;
    private int T;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must be larger than zero");
        }
        this.T = T;
        int t = 0;
        thresholds = new double[T];
        while (!(t == T)) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0 , N);
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
        return mean() - 1.96 * Math.pow(stddev(), 0.5) / Math.pow(T, 0.5);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * Math.pow(stddev(), 0.5) / Math.pow(T, 0.5);
    }

    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(20, 30, pf);
        System.out.println(ps.mean());
        System.out.println(ps.confidenceHigh());
        System.out.println(ps.confidenceLow());
        
    }
}
