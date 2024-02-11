package org.example.shell;

public class Shell {

    Shell() {
    }

    public static void sort(int[] arr) {

        int N = arr.length;

        int h = 1;

        while (h < N / 3) h = 3 * h + 1;

        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && arr[j] < arr[j - h]; j -= h) {
                    var temp = arr[j];
                    arr[j]=arr[j-h];
                    arr[j-h]=temp;
                }
            }
            h = h / 3;
        }

        for(int i : arr){
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        int[] myArr={7,345,347,8,4,3,2,9,-45,34,0};
        Shell.sort(myArr);
    }

}
