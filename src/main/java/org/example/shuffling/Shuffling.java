package org.example.shuffling;

import java.util.Random;

public class Shuffling {

  public static void main(String[] args) {

    int[] arr = new int[10];

    for (int i = 0; i < arr.length; i++) {
      arr[i] = i;
    }

    // shuffling
    for (int i : arr) {
      Random random = new Random();
      int n = random.nextInt(arr.length);
      int m = random.nextInt(arr.length);
      int temp = arr[m];
      arr[m] = arr[n];
      arr[n] = temp;
    }

    for (int i : arr) {
      System.out.print(i + " ");
    }

  }
}
