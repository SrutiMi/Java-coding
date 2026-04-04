import java.util.*;

/*
    QUESTION:
    Replace each element of the array with its rank.

    The rank of an element is its position in the sorted array
    (starting from 1). If elements are equal, they get the same rank.

    Example:
    Input:  arr = {20, 15, 26, 2, 98, 6}
    Sorted: {2, 6, 15, 20, 26, 98}
    Output: {4, 3, 5, 1, 6, 2}

    ------------------------------------------------------------
    APPROACH 1: USING MIN HEAP (Heap-based)
    ------------------------------------------------------------
    - Insert all elements into a min heap
    - Extract elements in sorted order
    - Assign rank while handling duplicates
    - Map value → rank
    - Build result array

    TIME COMPLEXITY:
    - Insert n elements → O(n log n)
    - Remove n elements → O(n log n)
    - Total → O(n log n)

    SPACE COMPLEXITY:
    - Heap → O(n)
    - Map → O(n)
    - Result → O(n)

    ------------------------------------------------------------
    APPROACH 2: USING SORTING (Optimized in practice)
    ------------------------------------------------------------
    - Copy array and sort it
    - Assign ranks to unique elements
    - Map value → rank
    - Build result array

    TIME COMPLEXITY:
    - Sorting → O(n log n)
    - Mapping → O(n)
    - Total → O(n log n)

    SPACE COMPLEXITY:
    - Extra array + map → O(n)

    ------------------------------------------------------------
    KEY INSIGHT:
    ------------------------------------------------------------
    - Both have same Big-O complexity
    - Sorting is faster in practice (less overhead)
    - Heap is useful for streaming / top-k problems
*/

public class HeapRank {

  // -------------------------------
  // APPROACH 1: USING MIN HEAP
  // -------------------------------
  public static int[] rankUsingHeap(int[] arr, int n) {

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    // Insert all elements
    for (int val : arr) {
      minHeap.add(val);
    }

    // Assign ranks
    HashMap<Integer, Integer> map = new HashMap<>();
    int rank = 1;

    while (!minHeap.isEmpty()) {
      int val = minHeap.poll();

      if (!map.containsKey(val)) {
        map.put(val, rank++);
      }
    }

    // Build result
    int[] result = new int[n];
    for (int i = 0; i < n; i++) {
      result[i] = map.get(arr[i]);
    }

    return result;
  }

  // -------------------------------
  // APPROACH 2: USING SORTING
  // -------------------------------
  public static int[] rankUsingSorting(int[] arr, int n) {

    int[] temp = arr.clone();
    Arrays.sort(temp);

    HashMap<Integer, Integer> map = new HashMap<>();
    int rank = 1;

    for (int i = 0; i < n; i++) {
      if (!map.containsKey(temp[i])) {
        map.put(temp[i], rank++);
      }
    }

    int[] result = new int[n];
    for (int i = 0; i < n; i++) {
      result[i] = map.get(arr[i]);
    }

    return result;
  }

  // Utility function to print array
  public static void printArray(int[] arr) {
    for (int x : arr) {
      System.out.print(x + " ");
    }
    System.out.println();
  }

  // MAIN METHOD
  public static void main(String[] args) {

    int[] arr = { 20, 15, 26, 2, 98, 6 };
    int n = arr.length;

    System.out.println("Original Array:");
    printArray(arr);

    // Heap Approach
    int[] heapResult = rankUsingHeap(arr, n);
    System.out.println("Rank using Heap:");
    printArray(heapResult);

    // Sorting Approach
    int[] sortResult = rankUsingSorting(arr, n);
    System.out.println("Rank using Sorting:");
    printArray(sortResult);
  }
}