package org.example.bitonic;

public class Bitonic {

    int[] arr;

    public Bitonic(int[] arr) {
        this.arr = arr;
    }

    public int find(int key) {
        int p = findBitonic();
        if (key > arr[p]) return -1;
        if (key == arr[p]) return p;

        int k = searchAscending(key, p);
        if (k != -1) {
            return k;
        } else return searchDescending(key, p);

    }

    ;

    private int searchAscending(int key, int p) {

        int lo = p + 1,
                hi = arr.length - 1;

        while (lo <= hi) {

            p = lo + ((hi - lo) / (2));
            if (arr[p] == key) {
                return p;
            }
            if (arr[p] > key) {
                lo = p + 1;
            } else {
                hi = p - 1;
            }

        }


        return -1;
    }

    private int searchDescending(int key, int p) {
        int lo = 0,
                hi = p - 1;

        while (lo <= hi) {

            p = lo + ((hi - lo) / (2));
            if (arr[p] == key) {
                return p;
            }
            if (arr[p] < key) {
                lo = p + 1;
            } else {
                hi = p - 1;
            }

        }


        return -1;
    }


    private int findBitonic() {

        int mid = arr.length / 2;

        int i = mid;
        while (i < arr.length - 1 && i > 0) {

            if (arr[i] > arr[i + 1] && arr[i] > arr[i - 1]) {
                return i;
            } else if (arr[i] < arr[i + 1]) {
                i++;
            } else if (arr[i] < arr[i - 1]) {
                i--;
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 33, 66, 900, 29, 20, 10, 0};
        Bitonic b = new Bitonic(arr);
    }

}
