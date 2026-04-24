import java.util.*;

/*
    ============================================================
    QUESTION: FRACTIONAL KNAPSACK (Greedy)
    ============================================================

    Given:
    - val[] → values of items
    - wt[] → weights of items
    - capacity → maximum weight capacity of knapsack

    You can take FRACTIONS of items.

    Goal:
    Maximize the total value in the knapsack.

    ------------------------------------------------------------
    EXAMPLE:
    Input:
        val = [60, 100, 120]
        wt  = [10, 20, 30]
        capacity = 50

    Output:
        240.0

    Explanation:
        Take full items:
            (60,10), (100,20)
        Remaining capacity = 20
        Take 20/30 of (120,30) → value = 80

        Total = 60 + 100 + 80 = 240

    ------------------------------------------------------------
    APPROACH (GREEDY + MAX HEAP)
    ------------------------------------------------------------

    Step 1:
        Compute value/weight ratio for each item

    Step 2:
        Store items in a MAX HEAP based on ratio

    Step 3:
        Always pick item with highest ratio:
            - If it fits → take whole item
            - Else → take fraction

    Step 4:
        Continue until capacity is full

    ------------------------------------------------------------
    WHY GREEDY WORKS?
    ------------------------------------------------------------

    Key idea:
    → Always take the item with the highest value per unit weight

    Why?
    - This gives maximum value contribution per unit capacity
    - Any other choice gives less value

    This is provably optimal because:
    → Local best choice (highest ratio) leads to global optimum

    NOTE:
    - Works ONLY because fractions are allowed
    - For 0/1 Knapsack → greedy fails

    ------------------------------------------------------------
    TIME COMPLEXITY:
    ------------------------------------------------------------

    - Insert n items into heap → O(n log n)
    - Extract n items → O(n log n)

    Total:
        TC = O(n log n)

    ------------------------------------------------------------
    SPACE COMPLEXITY:
    ------------------------------------------------------------

    - Heap stores n items → O(n)

    ------------------------------------------------------------
    KEY INSIGHT:
    ------------------------------------------------------------

    - Sort or prioritize by value/weight ratio
    - Greedy works because of fractional property
*/

public class FractionalKnapsack {

  static class Item {
    int value, weight;
    double ratio;

    Item(int value, int weight) {
      this.value = value;
      this.weight = weight;
      this.ratio = (double) value / weight;
    }
  }

  public static double fractionalKnapsack(int[] val, int[] wt, int capacity) {

    int n = val.length;

    // Max Heap based on ratio
    PriorityQueue<Item> pq = new PriorityQueue<>(
        (a, b) -> Double.compare(b.ratio, a.ratio));

    // Load items
    for (int i = 0; i < n; i++) {
      pq.add(new Item(val[i], wt[i]));
    }

    double totalValue = 0.0;
    double remainingCapacity = capacity;

    // Process items
    while (remainingCapacity > 0 && !pq.isEmpty()) {

      Item curr = pq.poll();

      // Take whole item
      if (curr.weight <= remainingCapacity) {
        totalValue += curr.value;
        remainingCapacity -= curr.weight;
      }
      // Take fraction
      else {
        totalValue += curr.ratio * remainingCapacity;
        remainingCapacity = 0;
      }
    }

    return totalValue;
  }

  // MAIN METHOD
  public static void main(String[] args) {

    int[] val = { 60, 100, 120 };
    int[] wt = { 10, 20, 30 };
    int capacity = 50;

    double result = fractionalKnapsack(val, wt, capacity);

    System.out.println("Maximum value in Knapsack: " + result);
  }
}