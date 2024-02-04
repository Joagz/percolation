package org.example.binarysearch;

import edu.princeton.cs.algs4.StdOut;

public class BinarySearch {
    public static int binarySearch(int[] a, int key) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
                StdOut.println("mid: "+mid);
            if (key < a[mid]) {
                hi = mid - 1;
                StdOut.println("hi: " + hi);
            } else if (key > a[mid]) {
                lo = mid + 1;
                StdOut.println("lo: " + lo);
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = new int[10];
        for (int i = 0; i < 10; i++) {
            a[i] = i * 15;
        }
        int found = binarySearch(a, 45);
        StdOut.println(found);
    }
}
