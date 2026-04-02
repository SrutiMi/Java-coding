import java.util.*;

/*
    QUESTION:
    Convert a Min Heap to a Max Heap.

    Given an array arr[] representing a Min Heap,
    convert it into a Max Heap in-place.

    Example:
    Input:  arr = {10, 20, 30, 40, 50, 60}
    Output: {60, 50, 30, 40, 20, 10}  (one valid max heap)

    Approach:
    - Start from last non-leaf node (N-1)/2
    - Apply heapifyDown to enforce max heap property
*/

public class MinHeapToMaxHeap {

  // Function to convert Min Heap to Max Heap
  static void convertMinToMaxHeap(int N, int arr[]) {

    for (int i = (N - 1) / 2; i >= 0; i--) {
      heapifyDown(arr, N, i);
    }
  }

  // Heapify Down (for Max Heap)
  private static void heapifyDown(int[] arr, int N, int i) {

    int left = 2 * i + 1;
    int right = 2 * i + 2;
    int maximum = i;

    if (left < N && arr[left] > arr[maximum]) {
      maximum = left;
    }

    if (right < N && arr[right] > arr[maximum]) {
      maximum = right;
    }

    if (maximum != i) {
      swap(arr, i, maximum);
      heapifyDown(arr, N, maximum);
    }
  }

  // Swap helper
  private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  // Print array
  private static void printArray(int[] arr) {
    for (int x : arr) {
      System.out.print(x + " ");
    }
    System.out.println();
  }

  // MAIN METHOD
  public static void main(String[] args) {

    // Input Min Heap
    int[] arr = { 10, 20, 30, 40, 50, 60 };

    System.out.println("Original Min Heap:");
    printArray(arr);

    // Convert
    convertMinToMaxHeap(arr.length, arr);

    System.out.println("Converted Max Heap:");
    printArray(arr);
  }
}