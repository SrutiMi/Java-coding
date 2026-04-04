import java.util.*;

/*
    QUESTION:
    Check if an array is k-sorted.

    An array is said to be k-sorted if every element is at most k positions
    away from its correct position in the sorted array.

    Return "Yes" if the array is k-sorted, otherwise return "No".

    Example:
    Input: arr = {50, 24, 43}, k = 1
    Sorted: {24, 43, 50}

    Element 50 moves from index 0 → 2 (distance = 2 > k)
    → Output: "No"

    ------------------------------------------------------------
    APPROACH:
    ------------------------------------------------------------
    Step 1:
    - Use a Min Heap of size (k+1) to SORT the array efficiently
    - This works because in a k-sorted array, the correct element
      must lie within the next k+1 elements

    Step 2:
    - Store sorted result in a new array

    Step 3:
    - Create a map of value → index in sorted array

    Step 4:
    - For each element in original array:
        → Check displacement from sorted position
        → If displacement > k → return "No"

    ------------------------------------------------------------
    TIME COMPLEXITY:
    ------------------------------------------------------------
    Step 1 (Heap sort):
        - n insertions → O(n log k)

    Step 2 (Mapping + checking):
        - O(n)

    Total:
        TC = O(n log k)

    ------------------------------------------------------------
    SPACE COMPLEXITY:
    ------------------------------------------------------------
    - Heap → O(k)
    - Result array → O(n)
    - HashMap → O(n)

    Total:
        SC = O(n)

    ------------------------------------------------------------
    IMPORTANT NOTE:
    ------------------------------------------------------------
    - Sorting alone is NOT enough to check k-sorted property
    - We must verify displacement explicitly
*/

class KSortedArray {

  static String isKSortedArray(int arr[], int n, int k) {

    int resultIdx = 0;

    // Min Heap
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    int[] result = new int[n];

    // Step 1: Build sorted array using k+1 heap
    for (int i : arr) {
      pq.add(i);

      if (pq.size() > k + 1) {
        result[resultIdx++] = pq.poll();
      }
    }

    // Empty remaining elements
    while (!pq.isEmpty()) {
      result[resultIdx++] = pq.poll();
    }

    // Step 2: Map value → index in sorted array
    HashMap<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < n; i++) {
      map.put(result[i], i);
    }

    // Step 3: Check displacement
    for (int i = 0; i < n; i++) {
      int correctIndex = map.get(arr[i]);

      if (Math.abs(i - correctIndex) > k) {
        return "No";
      }
    }

    return "Yes";
  }

  // MAIN METHOD
  public static void main(String[] args) {

    int[] arr = { 50, 24, 43 };
    int k = 1;

    String result = isKSortedArray(arr, arr.length, k);

    System.out.println("Is K-Sorted? " + result);
  }
}