/**
 * Class: LCA
 * Purpose: To find the Lowest Common Ancestor of two nodes in a Binary Search Tree (BST).
 * * APPROACH:
 * The algorithm uses the fundamental property of a BST:
 * 1. If both p and q are smaller than the current root, the LCA must be in the left subtree.
 * 2. If both p and q are larger than the current root, the LCA must be in the right subtree.
 * 3. If one is smaller and the other is larger (or one equals the root), the current root 
 * is the "split point" and thus the Lowest Common Ancestor.
 * * TIME COMPLEXITY: O(H)
 * - Where H is the height of the tree. In the worst case (a skewed tree), this is O(N).
 * - For a balanced BST, this is O(log N). We only visit one node per level.
 * * SPACE COMPLEXITY: O(H)
 * - Due to the recursion stack. In a balanced tree, this is O(log N).
 * - Note: This could be optimized to O(1) space by using an iterative approach.
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class LCA {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case: if root is null, return null
        if (root == null) return null;

        // If both nodes are smaller than root, LCA is in the left subtree
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        
        // If both nodes are larger than root, LCA is in the right subtree
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }

        // If we reach here, it means we found the split point.
        // Either p is on one side and q is on the other, or root is p or q.
        return root;
    }

    public static void main(String[] args) {
        LCA solution = new LCA();

        /* Constructing a sample BST:
                 6
               /   \
              2     8
             / \   / \
            0   4 7   9
               / \
              3   5
        */
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        // Test Case 1: LCA of 2 and 8 should be 6
        TreeNode p1 = root.left; // Node 2
        TreeNode q1 = root.right; // Node 8
        TreeNode result1 = solution.lowestCommonAncestor(root, p1, q1);
        System.out.println("LCA of " + p1.val + " and " + q1.val + " is: " + result1.val);

        // Test Case 2: LCA of 2 and 4 should be 2
        TreeNode p2 = root.left; // Node 2
        TreeNode q2 = root.left.right; // Node 4
        TreeNode result2 = solution.lowestCommonAncestor(root, p2, q2);
        System.out.println("LCA of " + p2.val + " and " + q2.val + " is: " + result2.val);
    }
}