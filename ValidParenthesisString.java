import java.util.*;

/*
    ============================================================
    QUESTION: VALID PARENTHESIS STRING WITH '*'
    ============================================================

    Given a string s containing:
        '(' , ')' and '*'

    Rules:
    - '(' must be matched with ')'
    - ')' must have a corresponding '(' before it
    - '*' can act as:
        → '(' OR ')' OR empty ""

    Return true if the string can be valid.

    ------------------------------------------------------------
    EXAMPLES:

    Input:  "()"
    Output: true

    Input:  "(*)"
    Output: true

    Input:  "(*))"
    Output: true

    ------------------------------------------------------------
    APPROACH (GREEDY + STACKS)
    ------------------------------------------------------------

    Key Idea:
    - Use TWO stacks to track positions:
        1. parenthesis stack → stores indices of '('
        2. asterisk stack → stores indices of '*'

    ------------------------------------------------------------
    STEP-BY-STEP:

    1. Traverse string:
        - If '(' → push index to parenthesis stack
        - If '*' → push index to asterisk stack

        - If ')':
            → Try to match with '(' first (priority)
            → If no '(' → use '*' as '('
            → If neither exists → return false

    2. After traversal:
        - Some '(' may still be unmatched

        → Match them with '*' (acting as ')')
        → BUT ensure:
            '*' index > '(' index
            (order matters)

    3. If all matched → return true

    ------------------------------------------------------------
    WHY GREEDY WORKS?
    ------------------------------------------------------------

    - Always prefer matching ')' with '(' first
    - Use '*' only when necessary

    Why?
    → '(' has fixed meaning
    → '*' is flexible (can be used later if needed)

    Second phase:
    → Use '*' as ')' only if it appears AFTER '('
    → Maintains correct ordering

    This ensures valid structure without backtracking.

    ------------------------------------------------------------
    TIME COMPLEXITY:
    ------------------------------------------------------------

        Single traversal → O(n)
        Stack operations → O(n)

        TC = O(n)

    ------------------------------------------------------------
    SPACE COMPLEXITY:
    ------------------------------------------------------------

        Two stacks → O(n)

        SC = O(n)

    ------------------------------------------------------------
    KEY INSIGHT:
    ------------------------------------------------------------

    - '*' is a "wildcard resource"
    - Use it only when necessary
    - Maintain order using indices
*/

public class ValidParenthesisString {

    public static boolean checkValidString(String s) {

        Stack<Integer> parenthesis = new Stack<>();
        Stack<Integer> asterisk = new Stack<>();

        char[] characters = s.toCharArray();

        // Step 1: Traverse string
        for (int i = 0; i < characters.length; i++) {

            if (characters[i] == '(') {
                parenthesis.push(i);
            }

            else if (characters[i] == ')') {

                if (parenthesis.isEmpty() && asterisk.isEmpty()) {
                    return false;
                }

                // Prefer '('
                if (!parenthesis.isEmpty()) {
                    parenthesis.pop();
                } else {
                    asterisk.pop();
                }
            }

            else { // '*'
                asterisk.push(i);
            }
        }

        // Step 2: Match remaining '(' with '*'
        while (!parenthesis.isEmpty()) {

            if (asterisk.isEmpty()) return false;

            int starIndex = asterisk.pop();
            int parIndex = parenthesis.pop();

            // '*' must come AFTER '('
            if (starIndex < parIndex) return false;
        }

        return true;
    }

    // MAIN METHOD
    public static void main(String[] args) {

        String s = "(*))";

        boolean result = checkValidString(s);

        System.out.println("Is valid string: " + result);
    }
}