public class ValidBST {

    // Definition for a binary tree node provided by LeetCode
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean isValidBST(TreeNode root) {
        // FIRST PRINCIPLE: We start with the widest possible range.
        // We use 'long' because the node values can be Integer.MIN_VALUE or MAX_VALUE.
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * THE "CONTRACT" APPROACH:
     * Every time we move to a child, we pass down a "contract" (min and max).
     * 1. If we go LEFT: The child MUST be smaller than the current node. 
     * So, the 'high' boundary updates to the current node's value.
     * 2. If we go RIGHT: The child MUST be larger than the current node.
     * So, the 'low' boundary updates to the current node's value.
     */
    private boolean validate(TreeNode node, long low, long high) {
        // BASE CASE: An empty tree is technically a valid BST.
        if (node == null) {
            return true;
        }

        // THE TRUTH CHECK:
        // Does the current node's value break the contract?
        // It must be STRICTLY greater than 'low' and STRICTLY less than 'high'.
        if (node.val <= low || node.val >= high) {
            return false;
        }

        // THE RECURSION (Passing the contract):
        // Check the left side: New high is the current node's value.
        // Check the right side: New low is the current node's value.
        // Both sides MUST be true for the whole tree to be valid.
        return validate(node.left, low, node.val) && 
               validate(node.right, node.val, high);
    }

    public static void main(String[] args) {
        ValidBST solution = new ValidBST();

        // Testing your specific example: [5,4,6,null,null,3,7]
        // Structure:
        //      5
        //     / \
        //    4   6
        //       / \
        //      3   7  <-- The '3' here breaks the rule because it's not > 5
        
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(6);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(7);

        boolean result = solution.isValidBST(root);
        System.out.println("Is the tree a valid BST? " + result); 
        // Expected Output: false
    }
}