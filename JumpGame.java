import java.util.*;

/*
    ============================================================
    QUESTION: JUMP GAME (Greedy)
    ============================================================

    You are given an array nums where:
        nums[i] = maximum jump length from index i

    You start at index 0.

    Goal:
    Determine if you can reach the last index.

    ------------------------------------------------------------
    EXAMPLE:

    Input:  nums = [2,3,1,1,4]
    Output: true

    Explanation:
        0 → jump to 1 → jump to last

    Input:  nums = [3,2,1,0,4]
    Output: false

    Explanation:
        You get stuck at index 3

    ------------------------------------------------------------
    APPROACH (GREEDY)
    ------------------------------------------------------------

    Core Idea:
    → Track the furthest index you can reach at any point

    Maintain:
        maxReach = maximum reachable index so far

    ------------------------------------------------------------
    STEPS:

    1. Start from index 0
    2. For each index i:
        - If i > maxReach → we cannot reach here → return false
        - Update maxReach = max(maxReach, i + nums[i])
        - If maxReach reaches last index → return true

    ------------------------------------------------------------
    WHY GREEDY WORKS?
    ------------------------------------------------------------

    - We don't need to try all paths
    - At each step, we only care about the furthest reachable point

    If at any point:
        current index > maxReach
    → we are stuck forever

    This local decision ensures global correctness.

    ------------------------------------------------------------
    TIME COMPLEXITY:
    ------------------------------------------------------------

        Single traversal → O(n)

    ------------------------------------------------------------
    SPACE COMPLEXITY:
    ------------------------------------------------------------

        Only one variable → O(1)

    ------------------------------------------------------------
    KEY INSIGHT:
    ------------------------------------------------------------

    "Am I standing on reachable ground, and how far can I go next?"
*/

public class JumpGame {

  public static boolean canJump(int[] nums) {

    int maxReach = 0;

    for (int i = 0; i < nums.length; i++) {

      // If current index is not reachable
      if (i > maxReach)
        return false;

      // Update maximum reachable index
      maxReach = Math.max(maxReach, i + nums[i]);

      // If we can reach or cross last index
      if (maxReach >= nums.length - 1)
        return true;
    }

    return true;
  }

  // MAIN METHOD
  public static void main(String[] args) {

    int[] nums = { 2, 3, 1, 1, 4 };

    boolean result = canJump(nums);

    System.out.println("Can reach last index: " + result);
  }
}