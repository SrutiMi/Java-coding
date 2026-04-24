
/**
 * CLASS NAME: MinimumPlatforms
 * * APPROACH: The "Timeline" or "Two-Pointer" Greedy Approach.
 * * WHY THIS APPROACH?
 * 1. DECISION: Since every train must be served, we don't need to pick/choose meetings. 
 * We only need to find the "High Water Mark" of occupancy.
 * 2. DECOUPLING: We sort arrival and departure times independently because any 
 * freed platform can serve any arriving train. We don't care about train identity.
 * 3. OPTIMALITY: O(N log N) is the fastest possible time for this because 
 * we must process every arrival and departure in chronological order.
 * * BOTTLENECKS:
 * - Sorting the arrays is the most expensive operation (O(N log N)).
 * - If arrival and departure times are identical, we must process arrival 
 * first per problem constraints (requires one extra platform).
 * * COMPLEXITY:
 * - Time: O(N log N) for sorting. The two-pointer traversal is O(N).
 * - Space: O(1) (ignoring space taken by sorting algorithm).
 */

import java.util.Arrays;

public class MinimumPlatforms {

  public int findPlatform(int[] arr, int[] dep, int n) {
    // Step 1: Sort both independently to create a chronological flow
    Arrays.sort(arr);
    Arrays.sort(dep);

    int i = 0; // Next Arrival Pointer
    int j = 0; // Next Departure Pointer

    int currentOccupancy = 0;
    int peakOccupancy = 0;

    // Step 2: Traverse the timeline until all arrivals are finished
    // We stop at 'i < n' because once the last train arrives,
    // the occupancy can only go down from there.
    while (i < n) {
      // Case 1: A train arrives before or exactly when another departs
      // Per problem: arrival at same time as departure needs an extra platform
      if (arr[i] <= dep[j]) {
        currentOccupancy++;
        i++;
      }
      // Case 2: A train departs, freeing up a platform
      else {
        currentOccupancy--;
        j++;
      }

      // Step 3: Always check if the current occupancy is our new record
      peakOccupancy = Math.max(peakOccupancy, currentOccupancy);
    }

    return peakOccupancy;
  }

  public static void main(String[] args) {
    MinimumPlatforms solver = new MinimumPlatforms();

    // Example Case
    int[] arr = { 900, 940, 950, 1100, 1500, 1800 };
    int[] dep = { 910, 1200, 1120, 1130, 1900, 2000 };
    int n = arr.length;

    System.out.println("Minimum Platforms Required: " + solver.findPlatform(arr, dep, n));
  }
}