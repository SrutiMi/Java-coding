import java.util.*;

/*
    ============================================================
    QUESTION: LEMONADE CHANGE (Greedy)
    ============================================================

    You are selling lemonade for $5 per cup.

    Customers come one by one and pay with:
        - $5, $10, or $20

    You must provide correct change using the bills you have.

    Initially, you have no money.

    Return true if you can provide change to every customer,
    otherwise return false.

    ------------------------------------------------------------
    EXAMPLE:
    Input:  bills = [5,5,5,10,20]
    Output: true

    Input:  bills = [5,5,10,10,20]
    Output: false

    ------------------------------------------------------------
    APPROACH (GREEDY)
    ------------------------------------------------------------

    Key idea:
    - Always try to give change in a way that preserves smaller bills

    Maintain:
        five → number of $5 bills
        ten  → number of $10 bills

    Cases:

    1. bill == 5:
        → no change needed
        → increase five

    2. bill == 10:
        → need $5 as change
        → if no $5 → return false
        → else give one $5 and take $10

    3. bill == 20:
        → need $15 as change

        Prefer:
        → give $10 + $5 (better, preserves $5 bills)

        Else:
        → give 3 × $5

        Else:
        → not possible → return false

    ------------------------------------------------------------
    WHY GREEDY WORKS?
    ------------------------------------------------------------

    - We always try to preserve smaller bills ($5)
    - Because $5 is required for BOTH:
        → $10 change
        → $20 change

    - Using $10 first (when possible) reduces future risk

    This local optimal choice ensures global correctness.

    ------------------------------------------------------------
    TIME COMPLEXITY:
    ------------------------------------------------------------

        Single pass through array → O(n)

    ------------------------------------------------------------
    SPACE COMPLEXITY:
    ------------------------------------------------------------

        Only variables used → O(1)

    ------------------------------------------------------------
    KEY INSIGHT:
    ------------------------------------------------------------

    - Don’t track $20 → it’s useless for giving change
    - Focus only on what helps future decisions
*/

public class LemonadeChange {

  public static boolean lemonadeChange(int[] bills) {

    int five = 0;
    int ten = 0;

    for (int bill : bills) {

      // Case 1: Customer pays $5
      if (bill == 5) {
        five++;
      }

      // Case 2: Customer pays $10
      else if (bill == 10) {
        if (five == 0)
          return false;

        five--;
        ten++;
      }

      // Case 3: Customer pays $20
      else {

        // Prefer giving 10 + 5
        if (ten > 0 && five > 0) {
          ten--;
          five--;
        }
        // Otherwise give 3 fives
        else if (five >= 3) {
          five -= 3;
        } else {
          return false;
        }
      }
    }

    return true;
  }

  // MAIN METHOD
  public static void main(String[] args) {

    int[] bills = { 5, 5, 5, 10, 20 };

    boolean result = lemonadeChange(bills);

    System.out.println("Can provide change: " + result);
  }
}