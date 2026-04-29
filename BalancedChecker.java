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
 * APPROACH: Optimized Bottom-Up DFS
 * * 1. THE CORE IDEA: Instead of calling a separate height function for every
 * node
 * (which is O(N^2)), we calculate height and balance-status simultaneously.
 * * 2. THE SENTINEL: We use '-1' to represent an unbalanced state.
 * * 3. THE FLOW: If any subtree is unbalanced, it "infects" the parent with a
 * -1,
 * which eventually reaches the root.
 * * TIME COMPLEXITY: O(N) - Each node is visited once.
 * SPACE COMPLEXITY: O(H) - Recursion stack goes as deep as the tree height.
 */
public class BalancedChecker {

  public boolean isBalanced(TreeNode root) {
    // If the result of our helper is -1, it means the tree is unbalanced.
    return checkHeight(root) != -1;
  }

  private int checkHeight(TreeNode node) {
    // Base Case: An empty tree has a height of 0 and is balanced.
    if (node == null)
      return 0;

    // 1. Check the left subtree
    int leftHeight = checkHeight(node.left);
    if (leftHeight == -1)
      return -1; // Already unbalanced, propagate the signal

    // 2. Check the right subtree
    int rightHeight = checkHeight(node.right);
    if (rightHeight == -1)
      return -1; // Already unbalanced, propagate the signal

    // 3. Check the current node's balance
    // If the difference is more than 1, this node is the "break point"
    if (Math.abs(leftHeight - rightHeight) > 1) {
      return -1;
    }

    // 4. If balanced, return the actual height to the parent
    return Math.max(leftHeight, rightHeight) + 1;
  }

  public static void main(String[] args) {
    // Example: Unbalanced Tree [1, 2, 2, 3, 3, null, null, 4, 4]
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(2);
    root.left.left = new TreeNode(3);
    root.left.right = new TreeNode(3);
    root.left.left.left = new TreeNode(4);
    root.left.left.right = new TreeNode(4);

    BalancedChecker checker = new BalancedChecker();
    System.out.println("Is the tree balanced? " + checker.isBalanced(root));
    // Output: false
  }
}