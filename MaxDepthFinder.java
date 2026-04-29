import java.util.*;

/**
 * Definition for a binary tree node.
 */
class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int val) {
    this.val = val;
  }
}

/**
 * CLASS: MaxDepthFinder
 * HIGHLIGHTS: Demonstrates proficiency in both iterative (BFS) and
 * recursive/iterative (DFS) patterns.
 */
public class MaxDepthFinder {

  /**
   * APPROACH 1: Breadth-First Search (BFS) / Level Order
   * 1. LOGIC: We process the tree level by level using a Queue.
   * 2. DEPTH TRACKING: Every time we finish processing all nodes
   * on a specific "floor" (levelSize), we increment the depth counter.
   * 3. WHY BFS: It is very intuitive—once the queue is empty,
   * the number of levels we processed is our maximum depth.
   * * TIME COMPLEXITY: O(N) - Every node is visited once.
   * * SPACE COMPLEXITY: O(N) - In the worst case (perfect tree),
   * the queue holds N/2 nodes.
   */
  public int maxDepthBFS(TreeNode root) {
    if (root == null)
      return 0;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    int depth = 0;

    while (!queue.isEmpty()) {
      int levelSize = queue.size();
      // Process the current level entirely
      for (int i = 0; i < levelSize; i++) {
        TreeNode current = queue.poll();
        if (current.left != null)
          queue.add(current.left);
        if (current.right != null)
          queue.add(current.right);
      }
      // After the loop, one level is finished
      depth++;
    }
    return depth;
  }

  /**
   * APPROACH 2: Depth-First Search (DFS) - Recursive
   * 1. LOGIC: The depth of a tree is 1 (the current node) plus
   * the maximum of the depths of its left and right subtrees.
   * 2. BASE CASE: If the node is null, its depth is 0.
   * 3. RECURSION: depth = 1 + max(dfs(left), dfs(right)).
   * * TIME COMPLEXITY: O(N) - Every node is visited once.
   * * SPACE COMPLEXITY: O(H) - Max stack depth is the height of the tree.
   */
  public int maxDepthDFS(TreeNode root) {
    if (root == null)
      return 0;

    int leftHeight = maxDepthDFS(root.left);
    int rightHeight = maxDepthDFS(root.right);

    return Math.max(leftHeight, rightHeight) + 1;
  }

  public static void main(String[] args) {
    /*
     * Sample Tree Structure:
     * 3
     * / \
     * 9 20
     * / \
     * 15 7
     * Max Depth: 3
     */
    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right.left = new TreeNode(15);
    root.right.right = new TreeNode(7);

    MaxDepthFinder solution = new MaxDepthFinder();

    System.out.println("--- Maximum Depth Analysis ---");
    System.out.println("BFS Result: " + solution.maxDepthBFS(root));
    System.out.println("DFS Result: " + solution.maxDepthDFS(root));
  }
}