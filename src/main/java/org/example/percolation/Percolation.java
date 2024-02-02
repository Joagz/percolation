package org.example.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    int[][] grid;
    int[][] openClosedGrid;
    WeightedQuickUnionUF weightedQuickUnionUF;
    int topVirtualNode = 0;
    int bottomVirtualNode;
    //    Save grid proportions
    int size;
    int openSites = 0;

    public Percolation(int n) {
        grid = new int[n][n];
        openClosedGrid = new int[n][n];
        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
        bottomVirtualNode = n * n;
        size = n;
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

        if(isOpen(row, col)){
            return;
        }

        if (row >= size || col >= size) {
            throw new IllegalArgumentException("Index " + row + " out of bounds");
        }
        int n = grid[row][col];

        openClosedGrid[row][col] = 1;

        if (col != 0) {
            if (isOpen(row, col - 1)) {
                weightedQuickUnionUF.union(n, grid[row][col - 1]);
            }
        }
        if (col != size - 1) {
            if (isOpen(row, col + 1)) {
                weightedQuickUnionUF.union(n, grid[row][col + 1]);
            }
        }
        if (row != 0) {
            if (isOpen(row - 1, col)) {
                weightedQuickUnionUF.union(n, grid[row-1][col]);
            }
        }
        if (row != size - 1) {
            if (isOpen(row + 1, col)) {
                weightedQuickUnionUF.union(n, grid[row+1][col]);
            }
        }

        openSites++;

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int n = openClosedGrid[row][col];
        return n == 1;
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
        return isFull(size-1, size-1);
    }
}
