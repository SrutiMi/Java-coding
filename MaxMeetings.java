import java.util.*;

/*
    ============================================================
    QUESTION: MAXIMUM MEETINGS IN ONE ROOM (Greedy)
    ============================================================

    Given:
    - start[] → start times of meetings
    - end[]   → end times of meetings

    Goal:
    Find the maximum number of meetings that can be performed
    in a single meeting room without overlap.

    Constraint:
    - A meeting can only start AFTER the previous one ends

    ------------------------------------------------------------
    EXAMPLE:

    Input:
        start = [1, 3, 0, 5, 8, 5]
        end   = [2, 4, 6, 7, 9, 9]

    Output:
        4

    Explanation:
        Optimal meetings:
        (1,2), (3,4), (5,7), (8,9)

    ------------------------------------------------------------
    APPROACH (GREEDY)
    ------------------------------------------------------------

    Core Idea:
    → Always pick the meeting that finishes the earliest

    Why?
    - It frees up the room as early as possible
    - Leaves maximum space for future meetings

    ------------------------------------------------------------
    STEPS:

    1. Combine start[] and end[] into meeting objects
    2. Sort (or use heap) based on end time (ascending)
    3. Pick first meeting (earliest finish)
    4. For each next meeting:
        → If start > lastEnd → pick it
        → Else skip

    ------------------------------------------------------------
    WHY GREEDY WORKS?
    ------------------------------------------------------------

    - Choosing earliest finishing meeting maximizes remaining time
    - Any other choice blocks more future meetings

    This is a classic proof:
    → Local optimal (earliest end) = global optimal

    ------------------------------------------------------------
    TIME COMPLEXITY:
    ------------------------------------------------------------

    Using Heap:
        Insert n elements → O(n log n)
        Remove n elements → O(n log n)

        TC = O(n log n)

    (Sorting approach also gives same TC)

    ------------------------------------------------------------
    SPACE COMPLEXITY:
    ------------------------------------------------------------

        Heap / list → O(n)

    ------------------------------------------------------------
    KEY INSIGHT:
    ------------------------------------------------------------

    - Don’t pick longest meeting ❌
    - Don’t pick earliest start ❌
    - Always pick earliest END ✔
*/

public class MaxMeetings {

  static class Time {
    int start;
    int end;

    Time(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }

  public static int maxMeetings(int[] start, int[] end) {

    if (start.length == 0)
      return 0;

    // Min Heap based on end time
    PriorityQueue<Time> pq = new PriorityQueue<>(
        (a, b) -> a.end - b.end);

    for (int i = 0; i < start.length; i++) {
      pq.add(new Time(start[i], end[i]));
    }

    // Pick first meeting
    Time curr = pq.poll();
    int lastEnd = curr.end;
    int count = 1;

    // Process remaining meetings
    while (!pq.isEmpty()) {
      curr = pq.poll();

      // Only pick if strictly after last meeting
      if (curr.start > lastEnd) {
        count++;
        lastEnd = curr.end;
      }
    }

    return count;
  }

  // MAIN METHOD
  public static void main(String[] args) {

    int[] start = { 1, 3, 0, 5, 8, 5 };
    int[] end = { 2, 4, 6, 7, 9, 9 };

    int result = maxMeetings(start, end);

    System.out.println("Maximum meetings: " + result);
  }
}