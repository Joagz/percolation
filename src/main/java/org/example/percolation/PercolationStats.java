package org.example.percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

    private double T;
    private double[] openSites;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("Input parameters can't be negative or zero!");
        T = trials;
        openSites = new double[trials];
        int i = 0;

        while (i < trials) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int r = StdRandom.uniformInt(n);
                int c = StdRandom.uniformInt(n);
                p.open(r, c);
            }
            openSites[i] = (double) p.numberOfOpenSites() / (n * n);
            i++;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(openSites);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(openSites);
    }

    public double confidenceLo() {
        double mean = mean();
        double stddev = stddev();
        return mean - 1.96 * stddev / Math.sqrt(T);
    }

    public double confidenceHi() {
        double mean = mean();
        double stddev = stddev();
        return mean + 1.96 * stddev / Math.sqrt(T);
    }

    // test client (see below)
    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        PercolationStats s = new PercolationStats(100, 100);
        double time = stopwatch.elapsedTime();
        StdOut.println("mean:\t\t\t=\t" + s.mean());
        StdOut.println("sttdev:\t\t\t=\t" + s.stddev());
        StdOut.println("95% confidence interval:\t\t\t=\t[" + s.confidenceLo() + ", " + s.confidenceHi() + "]");
        StdOut.println("\ncompilation time: "+time);
    }
}
