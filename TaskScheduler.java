import java.util.*;

/*
    ============================================================
    QUESTION: TASK SCHEDULER
    ============================================================

    You are given an array of CPU tasks, each labeled with a letter 
    from A to Z, and a number n.

    Each CPU interval can either execute one task or be idle.

    Constraint:
    - Same task must have at least n intervals gap between executions.

    Return the MINIMUM number of CPU intervals required to finish all tasks.

    ------------------------------------------------------------
    EXAMPLE:
    Input: tasks = [A, A, A, B, B, B], n = 2
    Output: 8

    One valid execution:
    A → B → idle → A → B → idle → A → B

    ------------------------------------------------------------
    APPROACH (HEAP + COOLDOWN QUEUE)
    ------------------------------------------------------------

    CORE IDEA:
    - Always execute the task with the highest remaining frequency
      → Use MAX HEAP

    - After execution, a task must wait for n intervals
      → Use QUEUE to manage cooldown

    ------------------------------------------------------------
    STEPS:

    1. Count frequency of each task
    2. Store frequencies in max heap (greedy choice)
    3. Use queue to store tasks in cooldown:
        → Each task stores:
            (remaining count, next available time)

    4. Simulate time:
        - At each time step:
            a) Increment time
            b) If any task cooldown is over → push back to heap
            c) If heap not empty → execute task
            d) Else → CPU is idle

    ------------------------------------------------------------
    TIME COMPLEXITY:
    ------------------------------------------------------------

    Let n = number of tasks

    - Frequency counting → O(n)
    - Heap operations → each task inserted/removed at most once
      → O(n log 26) ≈ O(n)

    - Queue operations → O(n)

    TOTAL:
        TC = O(n)

    WHY log(26)?
    - Only 26 possible tasks (A–Z), so heap size is bounded

    ------------------------------------------------------------
    SPACE COMPLEXITY:
    ------------------------------------------------------------

    - Frequency array → O(26)
    - Heap → O(26)
    - Queue → O(26)

    TOTAL:
        SC = O(1) (constant space)

    ------------------------------------------------------------
    KEY INSIGHT:
    ------------------------------------------------------------

    - Heap → decides WHAT to execute
    - Queue → decides WHEN it can be executed again
    - Time simulation ensures correctness
*/

public class TaskScheduler {

  // Inner class to represent a task
  static class Task {
    int count; // remaining frequency
    int readyTime; // when it can be reused

    Task(int count, int readyTime) {
      this.count = count;
      this.readyTime = readyTime;
    }
  }

  public static int leastInterval(char[] tasks, int n) {
    // counting the frequency
    int[] freq = new int[26];
    for (char c : tasks)
      freq[c - 'A']++;

    // MaxHeap
    PriorityQueue<Task> pq = new PriorityQueue<>(
        (a, b) -> b.count - a.count);
    for (int f : freq) {
      if (f > 0) {
        pq.add(new Task(f, 0));
      }

    }
    // Cooldown queue
    Queue<Task> q = new LinkedList<>();
    int time = 0;
    while (!pq.isEmpty() || !q.isEmpty()) {
      time++;
      // if any task completed cooldown ,push back to heap
      if (!q.isEmpty() && time - q.peek().readyTime > n) {
        pq.add(q.poll());
      }
      // Execute the task
      if (!pq.isEmpty()) {
        Task curr = pq.poll();
        curr.count--;
        if (curr.count > 0) {
          curr.readyTime = time;
          q.add(curr);
        }
      }
    }
    return time;

  }

  // MAIN METHOD
  public static void main(String[] args) {

    char[] tasks = { 'A', 'A', 'A', 'B', 'B', 'B' };
    int n = 2;

    int result = leastInterval(tasks, n);

    System.out.println("Minimum CPU intervals required: " + result);
  }
}