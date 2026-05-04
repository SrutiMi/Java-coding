/**
 * Definition for a binary tree node.
 */
class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

public class PreToBST {

  public TreeNode bstFromPreorder(int[] preorder) {
    return buildTree(preorder, 0, preorder.length - 1);
  }

  private TreeNode buildTree(int[] preorder, int start, int end) {
    // Base Case: If the range is invalid, this branch is empty.
    if (start > end)
      return null;

    /*
     * * FIRST PRINCIPLE 1: The Root
     * In Preorder (Root -> Left -> Right), the first element of any
     * range is guaranteed to be the Root of that subtree.
     */
    TreeNode root = new TreeNode(preorder[start]);

    /*
     * * FIRST PRINCIPLE 2: The BST Boundary
     * Because this is a BST, every node in the Left Subtree MUST be smaller than
     * the Root.
     * Because this is Preorder, the Left Subtree block follows the Root
     * immediately.
     * * Logic: We scan forward to find the first number LARGER than the root.
     * That number marks the beginning of the Right Subtree.
     */
    int breakPoint = start + 1;
    while (breakPoint <= end && preorder[breakPoint] < preorder[start]) {
      breakPoint++;
    }

    /*
     * FIRST PRINCIPLE 3: Recursive Construction
     * Left Subtree range: Everything after root up to (but not including) the
     * breakPoint.
     * Right Subtree range: From the breakPoint to the end of our current range.
     */
    root.left = buildTree(preorder, start + 1, breakPoint - 1);
    root.right = buildTree(preorder, breakPoint, end);

    return root;
  }

  // Helper method to print the tree (Inorder) to verify it's a valid BST
  public void printInorder(TreeNode node) {
    if (node == null)
      return;
    printInorder(node.left);
    System.out.print(node.val + " ");
    printInorder(node.right);
  }

  public static void main(String[] args) {
    PreToBST solver = new PreToBST();

    // Example from LeetCode
    int[] input = { 8, 5, 1, 7, 10, 12 };

    System.out.println("Constructing BST from Preorder: [8, 5, 1, 7, 10, 12]");
    TreeNode root = solver.bstFromPreorder(input);

    System.out.print("Inorder Traversal of resulting tree (should be sorted): ");
    solver.printInorder(root);
    // Expected Output: 1 5 7 8 10 12
  }
}