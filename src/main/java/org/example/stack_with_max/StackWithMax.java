package org.example.stack_with_max;

import java.util.Arrays;

public class StackWithMax {

    private int[] arr;
    private int N;
    private int max;
    private int prevMax;

    public StackWithMax() {
        this.arr = new int[4];
        this.N = 0;
        this.max = 0;
    }

    void push(int i) {
        if (max < i) {
            prevMax = max;
            max = i;
        }
        if (arr.length == N) {
            changeSize(2);
        }
        arr[N++] = i;
    }

    void changeSize(double modifier) {
        int[] newArr = new int[(int) Math.round(arr.length * modifier)];
        int k = 0;
        for (int d : arr) {
            if (k != N - 1 && arr[k] > arr[k + 1]) {
                max = arr[k];
            }
            newArr[k] = d;
            k++;
        }
        this.arr = newArr;
    }

    double pop() {
        if (arr.length / 4 == N) {
            changeSize(0.5);
        }
        int value = arr[--N];
        if (value == max) {
            max = prevMax;
        }
        arr[N] = 0;
        return value;
    }

    int getMax() {
        return max;
    }

    @Override
    public String toString() {
        return "StackWithMax{" +
                "arr=" + Arrays.toString(arr) +
                ", N=" + N +
                ", max=" + max +
                '}';
    }

    public static void main(String[] args) {
        StackWithMax stack = new StackWithMax();

        stack.push(1);
        stack.push(5);
        stack.push(14);
        stack.push(65);
        stack.pop();
        System.out.println(stack);
        System.out.println(stack.getMax());
    }

}
