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
 * CLASS: TraversalIterative
 * * CORE LOGIC:
 * Both approaches simulate the system's recursion stack manually using
 * java.util.Stack. This avoids StackOverflowErrors on very deep trees
 * and demonstrates a deep understanding of memory management.
 */
public class TraversalIterative {

    /**
     * APPROACH: PREORDER (Root -> Left -> Right)
     * 1. PROCESS: Pop a node and immediately add its value to the result.
     * 2. LIFO BEHAVIOR: To ensure Left is processed before Right, we must
     * push the Right child first, then the Left child.
     * 3. TERMINATION: The loop continues until the stack is empty.
     * * TIME COMPLEXITY: O(N) - We visit every node once.
     * SPACE COMPLEXITY: O(H) - Max stack depth equals tree height.
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val); // Root

            // Right then Left because Stack is Last-In-First-Out
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
        return result;
    }

    /**
     * APPROACH: INORDER (Left -> Root -> Right)
     * 1. THE DIVE: Use a 'curr' pointer to move as far Left as possible,
     * pushing each node onto the stack to remember the parent.
     * 2. THE BACKTRACK: When curr hits null, pop from stack. This is
     * the "Root" in the L-R-R sequence.
     * 3. THE PIVOT: Move to the Right child and repeat the "Dive."
     * * TIME COMPLEXITY: O(N) - Each node is pushed and popped exactly once.
     * SPACE COMPLEXITY: O(H) - Stack stores the current path from root to leaf.
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        // Condition handles both unexplored nodes (curr) and pending parents (stack)
        while (curr != null || !stack.isEmpty()) {
            // Move A: Dive Left
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // Move B: Process the Root
            curr = stack.pop();
            result.add(curr.val);

            // Move C: Pivot Right
            curr = curr.right;
        }
        return result;
    }

    /**
     * MAIN METHOD: Demonstrating academic and practical foundations[cite: 8, 9].
     */
    public static void main(String[] args) {
        /*
         * Sample Tree Structure:
         * 1
         * / \
         * 2 3
         * / \
         * 4 5
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        TraversalIterative solution = new TraversalIterative();

        System.out.println("--- Iterative Binary Tree Traversals ---");

        // Expected Preorder: [1, 2, 4, 5, 3]
        System.out.println("Preorder Traversal: " + solution.preorderTraversal(root));

        // Expected Inorder: [4, 2, 5, 1, 3]
        System.out.println("Inorder Traversal:  " + solution.inorderTraversal(root));
    }
}