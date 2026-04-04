/*
QUESTION:
You are given an array of integers 'hand' where each element represents a card.
You are also given an integer 'groupSize'.

You need to check whether it is possible to divide the cards into groups such that:
1. Each group has exactly 'groupSize' cards
2. Each group contains consecutive numbers

Return true if possible, otherwise false.

------------------------------------------------------------

APPROACH (Heap + Frequency Map):

Core Idea:
- We need to form groups of consecutive numbers.
- Always start from the smallest available number.

Steps:
1. Count frequency of each number using a HashMap
2. Use a Min Heap (PriorityQueue) to always access the smallest number
   → We only insert UNIQUE numbers using freq.keySet()
3. For each smallest number:
   → Try to build a group of size 'groupSize'
   → For each number in the group:
       - Check if it exists in map
       - Reduce its frequency
       - If frequency becomes 0:
           → Remove from map
           → ALSO remove from heap (BUT ONLY if it's at top)

------------------------------------------------------------

IMPORTANT DESIGN:

Heap → maintains ORDER (smallest first)
Map → maintains COUNT (frequency)

------------------------------------------------------------

CODE:
*/

import java.util.*;

class NStraightHands {

  public boolean isNStraightHand(int[] hand, int groupSize) {

    // If total cards cannot be divided equally
    if (hand.length % groupSize != 0)
      return false;

    // Step 1: Frequency map
    Map<Integer, Integer> freq = new HashMap<>();
    for (int num : hand) {
      freq.put(num, freq.getOrDefault(num, 0) + 1);
    }

    // Step 2: Min Heap with UNIQUE elements only
    PriorityQueue<Integer> pq = new PriorityQueue<>(freq.keySet());

    // Step 3: Process groups
    while (!pq.isEmpty()) {

      int start = pq.peek(); // smallest element

      // Try forming one group
      for (int i = 0; i < groupSize; i++) {

        int curr = start + i;

        // ❌ Case 1: Missing number
        if (!freq.containsKey(curr))
          return false;

        // Use the number
        freq.put(curr, freq.get(curr) - 1);

        // If frequency becomes zero
        if (freq.get(curr) == 0) {

          // ❌ Case 2: Order violation (VERY IMPORTANT)
          if (curr != pq.peek())
            return false;

          // Remove from heap + map
          pq.poll();
          freq.remove(curr);
        }
      }
    }

    return true;
  }
}

/*
 * ------------------------------------------------------------
 * 
 * MAIN METHOD (for testing)
 * 
 */
class Main {
  public static void main(String[] args) {

    NStraightHands obj = new NStraightHands();

    int[] hand1 = { 1, 2, 3, 6, 2, 3, 4, 7, 8 };
    int groupSize1 = 3;
    System.out.println(obj.isNStraightHand(hand1, groupSize1)); // true

    int[] hand2 = { 1, 1, 2, 3 };
    int groupSize2 = 2;
    System.out.println(obj.isNStraightHand(hand2, groupSize2)); // false

    int[] hand3 = { 1, 2, 2, 3 };
    int groupSize3 = 2;
    System.out.println(obj.isNStraightHand(hand3, groupSize3)); // false
  }
}

/*
 * ------------------------------------------------------------
 * 
 * TIME COMPLEXITY:
 * 
 * - Building map → O(n)
 * - Heap operations → O(n log n)
 * 
 * Overall:
 * 👉 O(n log n)
 * 
 * ------------------------------------------------------------
 * 
 * SPACE COMPLEXITY:
 * 
 * - Map + Heap → O(n)
 * 
 * ------------------------------------------------------------
 * 
 * 🔥 YOUR CONFUSIONS (CLEARLY EXPLAINED)
 * 
 * 1. Why use freq.keySet() in heap?
 * 
 * 👉 Heap only needs UNIQUE numbers (order)
 * 👉 Map handles duplicates (count)
 * 
 * ------------------------------------------------------------
 * 
 * 2. Why check: if (!freq.containsKey(curr))?
 * 
 * 👉 Ensures consecutive number exists
 * 👉 If missing → cannot form group
 * 
 * ------------------------------------------------------------
 * 
 * 3. Why check: if (curr != pq.peek())?
 * 
 * 👉 Ensures we are removing elements in sorted order
 * 
 * Key Insight:
 * - Heap still contains elements even if removed from map
 * - We must remove ONLY from top
 * 
 * Meaning:
 * 👉 “Don’t skip smaller numbers”
 * 
 * ------------------------------------------------------------
 * 
 * 4. Why map and heap both?
 * 
 * 👉 Heap = ordering
 * 👉 Map = frequency
 * 
 * Both solve different problems
 * 
 * ------------------------------------------------------------
 * 
 * 5. Why your previous approach failed?
 * 
 * ❌ Reinserting elements into heap
 * ❌ Mixing array + heap
 * ❌ Trying to simulate instead of using frequency
 * 
 * ------------------------------------------------------------
 * 
 * 🎯 FINAL MENTAL MODEL:
 * 
 * Always:
 * - Start from smallest
 * - Build consecutive sequence
 * - Reduce frequency
 * - Remove only when exhausted AND at heap top
 * 
 * ------------------------------------------------------------
 */