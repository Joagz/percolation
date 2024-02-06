package org.example.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int[][] openClosedGrid;
    private int[][] grid;
    private WeightedQuickUnionUF weightedQuickUnionUF;
    private int topVirtualNode = 0;
    private int bottomVirtualNode;
    // Save grid proportions
    private int size;
    private int openSites = 0;

    public Percolation(int n) {
        size = n;
        if (n <= 0) {
            throw new IllegalArgumentException("Grid size must be greater than 0");
        }
        grid = new int[size][size];
        openClosedGrid = new int[size][size];
        weightedQuickUnionUF = new WeightedQuickUnionUF(size * size + 2);
        bottomVirtualNode = size * size;

        for (int i = 1; i <= size; i++) {
            weightedQuickUnionUF.union(i, topVirtualNode);
        }

        int colNumber = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0) {
                    weightedQuickUnionUF.union(colNumber, topVirtualNode);
                } else if (i == n - 1) {
                    weightedQuickUnionUF.union(colNumber, bottomVirtualNode);
                }
                grid[i][j] = colNumber;
                colNumber++;
            }
        }
        
    }


    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }

        if (row > size || col > size) {
            throw new IllegalArgumentException("Array index out of bounds, max col/row is " + size);
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
                weightedQuickUnionUF.union(n, grid[row - 1][col]); // Adjust indices to 1-index
            }
        }
        if (row != size - 1) {
            if (isOpen(row + 1, col)) {
                weightedQuickUnionUF.union(n, grid[row + 1][col]);
            }
        }

        openSites++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row >= size || col >= size) {
            throw new IllegalArgumentException("Array index out of bounds, max col/row is " + (size - 1));
        }
        int n = openClosedGrid[row][col]; // Adjust indices to 1-index
        return n == 1;
    }

    // is the site (row, col) connected to the top?
    public boolean isFull(int row, int col) {

        if (!isOpen(row, col)) {
            return false;
        }

        if (row > size || col > size) {
            throw new IllegalArgumentException("Array index out of bounds, max col/row is " + (size - 1));
        }

        int n = grid[row][col]; // Adjust indices to 1-index
        return weightedQuickUnionUF.connected(topVirtualNode, n);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return weightedQuickUnionUF.connected(topVirtualNode, bottomVirtualNode);
    }
}
