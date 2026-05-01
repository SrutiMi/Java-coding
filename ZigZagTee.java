import java.util.*;

// Definition for a binary tree node.
class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

public class ZigZagTee {

  /**
   * APPROACH 1: Standard BFS + Collections.Reverse
   * Logic:
   * 1. Perform a normal Level-Order Traversal (BFS) using a Queue.
   * 2. Store each level in a standard ArrayList (always Left-to-Right).
   * 3. After finishing a level, if the level index is odd, reverse the list.
   * Efficiency: TC: O(N) but touches odd-level nodes twice. SC: O(W).
   */
  public List<List<Integer>> zigzagLevelOrderStandard(TreeNode root) {
    List<List<Integer>> results = new ArrayList<>();
    if (root == null)
      return results;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    boolean shouldReverse = false;

    while (!queue.isEmpty()) {
      int size = queue.size();
      List<Integer> currentLevel = new ArrayList<>();

      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        currentLevel.add(node.val);

        if (node.left != null)
          queue.add(node.left);
        if (node.right != null)
          queue.add(node.right);
      }

      if (shouldReverse) {
        Collections.reverse(currentLevel); // O(k) secondary pass
      }
      results.add(currentLevel);
      shouldReverse = !shouldReverse;
    }
    return results;
  }

  /**
   * APPROACH 2: Optimized Queue + Deque (Your Logic)
   * Logic:
   * 1. Use a Queue for traversal to maintain a consistent "visit" order.
   * 2. Use a Deque (ArrayDeque) for the result of the current level.
   * 3. Instead of reversing later, use addFirst() or addLast() to
   * place the value in the correct spot immediately.
   * Efficiency: TC: O(N) - touches every node exactly once. SC: O(W).
   */
  public List<List<Integer>> zigzagLevelOrderOptimized(TreeNode root) {
    List<List<Integer>> results = new ArrayList<>();
    if (root == null)
      return results;

    Queue<TreeNode> traversalQueue = new LinkedList<>();
    traversalQueue.add(root);
    boolean leftToRight = true;

    while (!traversalQueue.isEmpty()) {
      int size = traversalQueue.size();
      // Using Deque for O(1) insertions at both ends
      Deque<Integer> levelValues = new ArrayDeque<>();

      for (int i = 0; i < size; i++) {
        TreeNode node = traversalQueue.poll();

        if (leftToRight) {
          levelValues.addLast(node.val);
        } else {
          levelValues.addFirst(node.val);
        }

        if (node.left != null)
          traversalQueue.add(node.left);
        if (node.right != null)
          traversalQueue.add(node.right);
      }

      results.add(new ArrayList<>(levelValues));
      leftToRight = !leftToRight;
    }
    return results;
  }

  public static void main(String[] args) {
    // Constructing a sample tree:
    // 1
    // / \
    // 2 3
    // / \ / \
    // 4 5 6 7
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);
    root.right.left = new TreeNode(6);
    root.right.right = new TreeNode(7);

    ZigZagTee solution = new ZigZagTee();

    System.out.println("Standard BFS Result:  " + solution.zigzagLevelOrderStandard(root));
    System.out.println("Optimized Deque Result: " + solution.zigzagLevelOrderOptimized(root));
  }
}