package org.example;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Main {
    // test client (see below)
    public static void main(String[] args) {
        PercolationStats s = new PercolationStats(100, 5000);
        System.out.println("mean:\t\t\t=\t" + s.mean());
        System.out.println("sttdev:\t\t\t=\t" + s.stddev());
        System.out.println("95% confidence interval:\t\t\t=\t[" + s.confidenceLo() + ", " + s.confidenceHi() + "]");
    }

}

class PercolationStats {

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
//            Total sites opened divided grid length
            openSites[i] = (double) p.numberOfOpenSites() / (n * n);
            i++;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        double X = 0;
        for (double xi : openSites) {
            X += xi;
        }

        return X / T;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double mean = mean();
        double X = 0;
        for (double xi : openSites) {
            X += Math.pow((xi - mean), 2);
        }
        return Math.sqrt(X) / T;
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


}


class Percolation {

    int[][] grid;
    int[][] openClosedGrid;
    WeightedQuickUnionUF weightedQuickUnionUF;
    int topVirtualNode = 0;
    int bottomVirtualNode;
    //    Save grid proportions
    int L;
    int openSites = 0;

    public Percolation(int n) {
        grid = new int[n][n];
        openClosedGrid = new int[n][n];
        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
        bottomVirtualNode = n * n;
        L = n;
        int colNumber = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    weightedQuickUnionUF.union(colNumber, topVirtualNode);
                } else if (i == n - 1) {
                    weightedQuickUnionUF.union(colNumber, bottomVirtualNode);
                }
                grid[i][j] = colNumber;
                colNumber++;
                openClosedGrid[i][j] = 0;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        if (row >= L || col >= L) {
            throw new IllegalArgumentException("Index " + row + " out of bounds");
        }

        int n = grid[row][col];
        if (col != 0) {
            weightedQuickUnionUF.union(n, n - 1);
        }
        if (col != L - 1) {
            weightedQuickUnionUF.union(n, n + 1);
        }
        if (row != 0) {
            weightedQuickUnionUF.union(n, n - L);
        }
        if (row != L - 1) {
            weightedQuickUnionUF.union(n, n + L);
        }

        openSites++;
        openClosedGrid[row][col] = 1;

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int n = openClosedGrid[row][col];
        return n == 0;
    }

    // is the site (row, col) connected to the top?
    public boolean isFull(int row, int col) {
        int n = grid[row][col];

        return weightedQuickUnionUF.connected(n, 0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return weightedQuickUnionUF.connected(0, L * L);
    }
}
