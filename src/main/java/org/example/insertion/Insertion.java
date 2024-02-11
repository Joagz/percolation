package org.example.insertion;

import edu.princeton.cs.algs4.StdOut;

public class Insertion {

    Insertion() {
    }

    public void sort(int[] arr) {

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                count++;
                if (arr[j] < arr[i]) {
                    var temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }

        for (int i : arr) {
            StdOut.println(i);
        }


    }

    public static void main(String[] args) {
        int[] myArr = {345, 345, 345, 34, 56, 7, 56, 87, 56345, 564, 56, 7, 567456, 34};
        Insertion insertion = new Insertion();
        insertion.sort(myArr);
    }

}
