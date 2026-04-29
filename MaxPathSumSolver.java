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
 * APPROACH: Bottom-Up Recursive DFS
 * 1. THE "HEADS": Like your NLP project's shared backbone, the function does
 * two things:
 * - Updates the Global Max Path Sum (The "V-shape" path: Left + Right + Node).
 * - Returns the best single branch to the parent (Node + max(Left, Right)).
 * 2. THE ZERO-FILTER: If a subtree sum is negative, we treat it as 0 (we ignore
 * it).
 * * TIME COMPLEXITY: O(N) - Visit every node once.
 * SPACE COMPLEXITY: O(H) - Stack depth is the height of the tree.
 */
class MaxPathSumSolver {
  // Start with the lowest possible value to handle all-negative trees
  private int globalMax = Integer.MIN_VALUE;

  public int maxPathSum(TreeNode root) {
    calculateNodeMax(root);
    return globalMax;
  }

  private int calculateNodeMax(TreeNode node) {
    if (node == null)
      return 0;

    // "First Principle": Ignore subtrees that return a negative sum
    int leftMax = Math.max(0, calculateNodeMax(node.left));
    int rightMax = Math.max(0, calculateNodeMax(node.right));

    // 1. UPDATE GLOBAL MAX: Local "V-shaped" path through this node
    int currentPathSum = node.val + leftMax + rightMax;
    globalMax = Math.max(globalMax, currentPathSum);

    // 2. RETURN TO PARENT: Only the best single branch can be extended
    return node.val + Math.max(leftMax, rightMax);
  }

  public static void main(String[] args) {
    /*
     * Sample Tree: [-10, 9, 20, null, null, 15, 7]
     * -10
     * / \
     * 9 20
     * / \
     * 15 7
     * Max Path Sum: 42 (15 + 20 + 7)
     */
    TreeNode root = new TreeNode(-10);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right.left = new TreeNode(15);
    root.right.right = new TreeNode(7);

    MaxPathSumSolver solver = new MaxPathSumSolver();
    System.out.println("Maximum Path Sum: " + solver.maxPathSum(root));
  }
}