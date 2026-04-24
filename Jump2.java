/**
 * QUESTION: Jump Game II
 * You are given a 0-indexed array of integers nums of length n.
 * You are initially positioned at index 0. Each element nums[i] represents
 * the maximum length of a forward jump from index i.
 * Return the minimum number of jumps to reach index n - 1.
 * * APPROACH: Greedy (Scout and Fence)
 * We maintain two boundaries:
 * 1. maxStep (The Scout): The absolute furthest index we can reach from
 * any of the steps taken within the current jump range.
 * 2. currJumpEnd (The Fence): The limit of our current jump.
 * * We iterate through the array. For every step, we update our 'Scout'.
 * When we hit the 'Fence', it means we have explored all options for
 * the current jump, so we must jump again. We increment the count
 * and move the Fence to the Scout's furthest discovery.
 * * BOTTLENECKS & EDGE CASES:
 * - Array of length 1: We are already at the destination (0 jumps).
 * - Large jump values: The 'Scout' must always track the maximum potential.
 * - Loop Boundary: We stop at nums.length - 1 because we don't need to
 * jump once we are already standing on the last index.
 * * TIME COMPLEXITY: $O(n)$ - We traverse the array once.
 * SPACE COMPLEXITY: $O(1)$ - We only use a few integer variables.
 */

public class Jump2 {

  public int jump(int[] nums) {
    int count = 0;
    int maxStep = 0;
    int currJumpEnd = 0;

    // We loop until length - 1 because we don't need to jump
    // if we are already at or past the last index.
    for (int i = 0; i < nums.length - 1; i++) {
      // The "Scout": Look for the furthest possible reach
      maxStep = Math.max(maxStep, i + nums[i]);

      // The "Fence": If we reach the end of our current jump's range...
      if (i == currJumpEnd) {
        count++; // We "commit" to a jump
        currJumpEnd = maxStep; // The new fence is the furthest point the scout found
      }
    }

    return count;
  }

  public static void main(String[] args) {
    Jump2 solver = new Jump2();

    // Test Case 1
    int[] nums1 = { 2, 3, 1, 1, 4 };
    System.out.println("Test Case 1: " + solver.jump(nums1)); // Expected: 2

    // Test Case 2
    int[] nums2 = { 2, 3, 0, 1, 4 };
    System.out.println("Test Case 2: " + solver.jump(nums2)); // Expected: 2

    // Test Case 3 (Edge Case: Length 1)
    int[] nums3 = { 0 };
    System.out.println("Test Case 3: " + solver.jump(nums3)); // Expected: 0
  }
}
