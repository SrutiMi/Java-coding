import java.util.*;

/*
    QUESTION:
    Find the Kth Largest Element in an Array.

    Given an integer array nums[] and an integer k,
    return the kth largest element in the array.

    NOTE:
    - It is the kth largest element in sorted order,
      NOT the kth distinct element.

    Example:
    Input: nums = {3,2,1,5,6,4}, k = 2
    Output: 5

    ------------------------------------------------------------
    APPROACH (Optimal - Min Heap of size k):
    ------------------------------------------------------------
    - Use a Min Heap (PriorityQueue)
    - Traverse the array:
        → Insert each element into heap
        → If heap size exceeds k, remove smallest element
    - At the end:
        → Heap contains k largest elements
        → Top (peek) = kth largest element

    ------------------------------------------------------------
    TIME COMPLEXITY ANALYSIS:
    ------------------------------------------------------------
    - We iterate through n elements → O(n)

    - For each element:
        → Insertion into heap takes O(log k)
          (because heap size is at most k)

        → Removal (poll) also takes O(log k), but happens only when size > k

    - So overall:
        TC = O(n log k)

    WHY NOT O(n log n)?
    - Because heap size is limited to k, not n

    ------------------------------------------------------------
    SPACE COMPLEXITY ANALYSIS:
    ------------------------------------------------------------
    - Heap stores at most k elements

        SC = O(k)

    ------------------------------------------------------------
    KEY INSIGHT:
    ------------------------------------------------------------
    - We don’t need all elements
    - We only care about top k largest
    - So we maintain only k elements → optimized solution
*/

public class KthLargestElement {

  public static int findKthLargest(int[] nums, int k) {

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    for (int num : nums) {
      minHeap.add(num);

      if (minHeap.size() > k) {
        minHeap.poll(); // remove smallest
      }
    }

    return minHeap.peek(); // kth largest element
  }

  public static void main(String[] args) {

    int[] nums = { 3, 2, 1, 5, 6, 4 };
    int k = 2;

    int result = findKthLargest(nums, k);

    System.out.println("Kth Largest Element: " + result);
  }
}