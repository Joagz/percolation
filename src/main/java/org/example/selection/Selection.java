package org.example.selection;

import edu.princeton.cs.algs4.StdOut;

public class Selection {

    Selection() {
    }

    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    System.out.println(arr[j] + " < " + arr[min]);
                    min = j;
                }
            }
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }

        for (int i : arr) {
            StdOut.println(i);
        }
    }

    public static void main(String[] args) {
        int[] myArr = {1, 5, 0, 4, 7, 9, -2, 4};
        Selection selection = new Selection();
        selection.sort(myArr);
    }
}
