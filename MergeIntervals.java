
/**
 * QUESTION: Merge Overlapping Intervals
 * Given an array of intervals, merge all overlapping intervals.
 * * APPROACH: Sorting + Linear Scan
 * 1. Why Sort by Start Time? Sorting by the beginning ensures that we 
 * process intervals in chronological order. This way, we only ever need 
 * to compare the "current" interval with the "next" one.
 * 2. Merging: If nextStart <= currentEnd, they overlap. We update 
 * currentEnd to be Math.max(currentEnd, nextEnd).
 * 3. Committing: If nextStart > currentEnd, they don't overlap. We add 
 * the current interval to our list and move our pointer to the next one.
 * * BOTTLENECKS:
 * - Sorting is O(N log N), which is the main time consumer.
 * - Result storage: We use an ArrayList because the final number of 
 * intervals is unknown.
 * * TIME COMPLEXITY: O(N log N) - for sorting.
 * SPACE COMPLEXITY: O(N) - to store the result list.
 */

import java.util.*;

public class MergeIntervals {
  public int[][] merge(int[][] intervals) {
    // Edge Case: No intervals or just one
    if (intervals.length <= 1)
      return intervals;

    // 1. Sort by Start Time (a[0])
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

    List<int[]> result = new ArrayList<>();

    // 2. Start with the first interval as our "current" block
    int[] currentInterval = intervals[0];

    // 3. Iterate through the rest
    for (int i = 1; i < intervals.length; i++) {
      int currentEnd = currentInterval[1];
      int nextStart = intervals[i][0];
      int nextEnd = intervals[i][1];

      // 4. Check for Overlap (Example: [1,4] and [4,5] overlap)
      if (nextStart <= currentEnd) {
        // Merge: Update the end of the current block to the maximum reach
        currentInterval[1] = Math.max(currentEnd, nextEnd);
      } else {
        // No Overlap: Commit the current block and move to the next
        result.add(currentInterval);
        currentInterval = intervals[i];
      }
    }

    // 5. THE LAST ONE: Add the final currentInterval after the loop ends
    result.add(currentInterval);

    // 6. Convert List<int[]> back to int[][]
    return result.toArray(new int[result.size()][]);
  }

  public static void main(String[] args) {
    MergeIntervals solver = new MergeIntervals();

    // Example 1
    int[][] input1 = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };
    int[][] res1 = solver.merge(input1);
    System.out.println("Merged 1: " + Arrays.deepToString(res1));
    // Output: [[1, 6], [8, 10], [15, 18]]

    // Example 2 (Touching boundaries)
    int[][] input2 = { { 1, 4 }, { 4, 5 } };
    int[][] res2 = solver.merge(input2);
    System.out.println("Merged 2: " + Arrays.deepToString(res2));
    // Output: [[1, 5]]

    // Example 3 (Nested Intervals)
    int[][] input3 = { { 1, 10 }, { 2, 3 }, { 4, 5 } };
    int[][] res3 = solver.merge(input3);
    System.out.println("Merged 3: " + Arrays.deepToString(res3));
    // Output: [[1, 10]]
  }
}