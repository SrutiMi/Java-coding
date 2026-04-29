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
 * APPROACH: Bottom-Up DFS
 * 1. THE DUAL-PURPOSE: The helper function returns the HEIGHT of the node
 * to its parent, but calculates the DIAMETER locally at every step.
 * 2. THE FORMULA: Local Diameter = Left Height + Right Height.
 * 3. THE UPDATE: We use a global variable to keep track of the largest
 * diameter seen across the entire tree.
 * * TIME COMPLEXITY: O(N) - Every node is visited once.
 * SPACE COMPLEXITY: O(H) - Recursion stack depth is the height of the tree.
 */
class DiameterFinder {
  // Global variable to track the maximum diameter found so far
  private int maxDiameter = 0;

  public int diameterOfBinaryTree(TreeNode root) {
    calculateHeight(root);
    return maxDiameter;
  }

  private int calculateHeight(TreeNode node) {
    // Base Case: null nodes contribute 0 to the path length
    if (node == null)
      return 0;

    // Recursive calls to get heights of subtrees
    int leftHeight = calculateHeight(node.left);
    int rightHeight = calculateHeight(node.right);

    // 1. UPDATE GLOBAL MAX:
    // The diameter at this node is the sum of left and right heights
    maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);

    // 2. RETURN TO PARENT:
    // A parent's height is 1 + the height of its deepest child
    return Math.max(leftHeight, rightHeight) + 1;
  }

  public static void main(String[] args) {
    /*
     * Sample Tree:
     * 1
     * / \
     * 2 3
     * / \
     * 4 5
     * Diameter: 3 (Path: 4-2-5-1-3 or 4-2-1-3)
     */
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);

    DiameterFinder solution = new DiameterFinder();
    System.out.println("Maximum Diameter: " + solution.diameterOfBinaryTree(root));
  }
}