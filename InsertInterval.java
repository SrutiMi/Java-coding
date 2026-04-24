
/**
 * QUESTION: Insert Interval
 * * APPROACH: The "Three-Act Play" (Linear Scan)
 * ACT 1: Add all intervals that end before the newInterval starts.
 * ACT 2: Merge all intervals that overlap with the newInterval.
 * ACT 3: Add all remaining intervals that start after the merged block ends.
 * * TC: O(N) - We only traverse the array once.
 * SC: O(N) - To store the result list.
 */

import java.util.*;

public class InsertInterval {
  public int[][] insert(int[][] intervals, int[] newInterval) {
    List<int[]> result = new ArrayList<>();
    int i = 0;
    int n = intervals.length;

    // ACT 1: Add intervals that are strictly BEFORE the new interval
    // Condition: Current interval's end < newInterval's start
    while (i < n && intervals[i][1] < newInterval[0]) {
      result.add(intervals[i]);
      i++;
    }

    // ACT 2: Merge overlapping intervals
    // Condition: Current interval's start <= newInterval's end
    while (i < n && intervals[i][0] <= newInterval[1]) {
      // Update the newInterval's boundaries to encompass the overlap
      newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
      newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
      i++;
    }
    // Add the single "Giant Merged Interval" to our result
    result.add(newInterval);

    // ACT 3: Add all remaining intervals that are strictly AFTER
    while (i < n) {
      result.add(intervals[i]);
      i++;
    }

    // Convert List back to 2D Array
    return result.toArray(new int[result.size()][]);
  }

  public static void main(String[] args) {
    InsertInterval solver = new InsertInterval();
    int[][] intervals = { { 1, 3 }, { 6, 9 } };
    int[] newInterval = { 2, 5 };

    int[][] res = solver.insert(intervals, newInterval);
    System.out.println("Result: " + Arrays.deepToString(res));
    // Output: [[1, 5], [6, 9]]
  }
}