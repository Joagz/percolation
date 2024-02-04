package org.example.threesum;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class ThreeSum {

    public int[] arr;

    public ThreeSum(int n) {
        this.arr = new int[n];
        for (int i = 0; i < n; i++) {
            this.arr[i] = StdRandom.uniformInt(-50, 50);
        }
    }

    int threeSum() {
        Arrays.sort(arr);

        int sums = 0;
        for (int i = 0; i < arr.length - 2; i++) {

            int l = i + 1, r = arr.length - 1;

            while (l < r) {
                if (arr[i] + arr[l] + arr[r] == 0) {
                    sums++;
                    r--;
                }
                else if (arr[i] + arr[l] + arr[r] < 0)
                    l++;

                else // A[i] + A[l] + A[r] > sum
                    r--;
            }
        }

        return sums;

    }

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum(9);
        int sums = threeSum.threeSum();
        int sums2 = edu.princeton.cs.algs4.ThreeSum.count(threeSum.arr);

        for (int i : threeSum.arr) {
            StdOut.print(i + ", ");
        }
        StdOut.println();
        StdOut.println(sums);
        StdOut.println(sums2);
    }


}
