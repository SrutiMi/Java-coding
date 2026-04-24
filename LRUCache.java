
/**
 * QUESTION: LRU Page Replacement Algorithm
 * Find the number of page faults given a sequence of pages and memory capacity.
 * * APPROACH: LinkedHashSet (The Hybrid Approach)
 * - We use LinkedHashSet because it provides O(1) lookup (like a Set)
 * and maintains insertion order (like a List).
 * - When a "Hit" occurs (page already in memory), we remove and re-add it 
 * to move it to the "Most Recently Used" (end) position.
 * - When a "Miss" occurs (Page Fault), we increment the count. If memory 
 * is full, we remove the "Least Recently Used" (the first element).
 * * TIME COMPLEXITY: O(N) 
 * - We iterate through N pages once. 
 * - Inside the loop, Set.contains(), Set.remove(), and Set.add() are all O(1).
 * * SPACE COMPLEXITY: O(C)
 * - We store at most 'C' (capacity) elements in the LinkedHashSet.
 */

import java.util.*;

public class LRUCache {

  public int pageFaults(int[] pages, int n, int capacity) {
    // LinkedHashSet keeps the order of elements and allows O(1) access
    LinkedHashSet<Integer> memory = new LinkedHashSet<>(capacity);
    int faults = 0;

    for (int page : pages) {
      // CASE 1: Page Hit (Already in memory)
      if (memory.contains(page)) {
        // To move it to the back (MRU), we must remove and re-add it
        memory.remove(page);
        memory.add(page);
      }
      // CASE 2: Page Miss (Page Fault)
      else {
        faults++; // Increment total faults

        // Check if memory is full before adding the new page
        if (memory.size() == capacity) {
          // Get the first element (the Least Recently Used)
          // .iterator().next() gives the oldest element in O(1)
          int oldest = memory.iterator().next();
          memory.remove(oldest);
        }

        // Add the new page to the end (Most Recently Used)
        memory.add(page);
      }
    }

    return faults;
  }

  public static void main(String[] args) {
    LRUCache lru = new LRUCache();

    int[] pages = { 1, 2, 1, 4, 2, 3, 5 };
    int capacity = 3;

    System.out.println("Total Page Faults: " + lru.pageFaults(pages, pages.length, capacity));
  }
}