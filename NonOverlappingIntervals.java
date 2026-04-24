
/**
 * NAME: NonOverlappingIntervals
 * * APPROACH: Greedy (Earliest Finish Time First)
 * 1. To minimize removals, we must maximize the number of intervals we keep.
 * 2. If we keep intervals that finish early, we leave more room for others.
 * 3. Therefore, we sort the intervals by their END times.
 * 4. We keep the first interval and mark its end as 'lastEnd'.
 * 5. For every subsequent interval:
 * - If its START time is less than 'lastEnd', it overlaps. We "remove" it (increment count).
 * - If its START time is >= 'lastEnd', it doesn't overlap. We "keep" it and update 'lastEnd'.
 * * THINGS TO THINK ABOUT:
 * - OVERFLOW: When sorting, use Integer.compare(a, b) instead of (a - b) to avoid 
 * subtraction overflow with large negative/positive coordinates.
 * - BOUNDARIES: The problem says [1,2] and [2,3] don't overlap. This means 
 * the condition for "keeping" is start >= lastEnd.
 * - SORTING CHOICE: Sorting by start time can work, but it requires more complex 
 * logic (updating the end time to the minimum of overlapping intervals). 
 * Sorting by end time is the "cleanest" greedy proof.
 * * COMPLEXITY:
 * - Time: O(N log N) due to sorting. The linear scan is O(N).
 * - Space: O(1) or O(log N) depending on the sort implementation's recursion stack.
 */

import java.util.Arrays;

public class NonOverlappingIntervals {

  public int eraseOverlapIntervals(int[][] intervals) {
    // Edge Case: If there are no intervals, no removals are needed.
    if (intervals == null || intervals.length <= 1) {
      return 0;
    }

    // 1. Sort by END time. This is the "Magic Key" for fitting
    // as many non-overlapping intervals as possible.
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

    int removedCount = 0;

    // 2. Initialize 'lastEnd' with the end of the first interval.
    // We always keep the one that finishes earliest.
    int lastEnd = intervals[0][1];

    // 3. Iterate through the rest of the intervals.
    for (int i = 1; i < intervals.length; i++) {
      int currentStart = intervals[i][0];
      int currentEnd = intervals[i][1];

      // 4. Overlap Check:
      // Since [1,2] and [2,3] are OK, an overlap ONLY happens
      // if the current start is strictly less than the last end.
      if (currentStart < lastEnd) {
        // It overlaps! We "remove" this interval.
        // We don't update lastEnd because we want to keep the
        // interval that finished EARLIER (which is our previous lastEnd).
        removedCount++;
      } else {
        // No overlap! We "keep" this interval.
        // Update our "finish line" to the end of this new interval.
        lastEnd = currentEnd;
      }
    }

    return removedCount;
  }

  public static void main(String[] args) {
    NonOverlappingIntervals solver = new NonOverlappingIntervals();

    // Edge Case 1: Empty or Single Interval
    System.out.println("Single Interval: " + solver.eraseOverlapIntervals(new int[][] { { 1, 2 } })); // 0

    // Edge Case 2: All identical intervals
    int[][] identical = { { 1, 2 }, { 1, 2 }, { 1, 2 } };
    System.out.println("Identical Intervals: " + solver.eraseOverlapIntervals(identical)); // 2

    // Edge Case 3: Intervals only touch at boundaries (Non-overlapping)
    int[][] touching = { { 1, 2 }, { 2, 3 }, { 3, 4 } };
    System.out.println("Touching Intervals: " + solver.eraseOverlapIntervals(touching)); // 0

    // Case 4: One large interval enclosing many small ones
    int[][] enclosing = { { 1, 10 }, { 2, 3 }, { 3, 4 }, { 4, 5 } };
    System.out.println("Enclosing Interval: " + solver.eraseOverlapIntervals(enclosing)); // 1 (Remove 1-10)

    // Standard Case
    int[][] standard = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 3 } };
    System.out.println("Standard Case: " + solver.eraseOverlapIntervals(standard)); // 1
  }
}