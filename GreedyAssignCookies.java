import java.util.*;

/*
    ============================================================
    QUESTION: ASSIGN COOKIES (Greedy)
    ============================================================

    You are given:
    - g[] → greed factor of children
    - s[] → sizes of cookies

    Each child can get at most one cookie.

    A child is satisfied if:
        cookie size >= greed factor

    Goal:
    Return the MAX number of satisfied children.

    ------------------------------------------------------------
    EXAMPLE:
    Input:
        g = [1,2,3]
        s = [1,1]

    Output:
        1

    Explanation:
        Only one child can be satisfied.

    ------------------------------------------------------------
    APPROACH (GREEDY)
    ------------------------------------------------------------

    Step 1:
        Sort both arrays:
        - g → greed factors (ascending)
        - s → cookie sizes (ascending)

    Step 2:
        Use two pointers:
        - i → children (greed array)
        - j → cookies

    Step 3:
        Traverse:
        - If current cookie can satisfy current child:
              → assign cookie
              → move both pointers
        - Else:
              → try bigger cookie (move j)

    ------------------------------------------------------------
    WHY GREEDY WORKS?
    ------------------------------------------------------------

    Key idea:
    → Always satisfy the least greedy child first using the smallest
      possible cookie.

    Why?
    - If a small cookie can satisfy a small greed, we should not waste
      a bigger cookie.
    - Bigger cookies should be reserved for greedier children.

    This ensures maximum number of children are satisfied.

    Greedy choice is optimal because:
    → Local optimal (smallest match) leads to global optimal solution.

    ------------------------------------------------------------
    TIME COMPLEXITY:
    ------------------------------------------------------------

    Sorting:
        O(n log n) + O(m log m)

    Traversal:
        O(n + m)

    Total:
        TC = O(n log n + m log m)

    ------------------------------------------------------------
    SPACE COMPLEXITY:
    ------------------------------------------------------------

    In-place sorting (ignoring recursion stack):
        SC = O(1)

    ------------------------------------------------------------
    KEY INSIGHT:
    ------------------------------------------------------------

    - Don’t try all combinations ❌
    - Just match smallest valid pair ✔
*/

public class GreedyAssignCookies {

  public static int findContentChildren(int[] g, int[] s) {

    int count = 0;

    Arrays.sort(g);
    Arrays.sort(s);

    int i = 0; // pointer for children
    int j = 0; // pointer for cookies

    while (i < g.length && j < s.length) {

      // If cookie can satisfy child
      if (g[i] <= s[j]) {
        count++;
        i++;
        j++;
      }
      // Otherwise try next bigger cookie
      else {
        j++;
      }
    }

    return count;
  }

  // MAIN METHOD
  public static void main(String[] args) {

    int[] g = { 1, 2, 3 };
    int[] s = { 1, 1 };

    int result = findContentChildren(g, s);

    System.out.println("Maximum satisfied children: " + result);
  }
}