import java.util.*;

/**
 * Definition for a binary tree node.
 */
class Node {
  int data;
  Node left, right;

  Node(int val) {
    data = val;
    left = right = null;
  }
}

/**
 * Class Name: BinaryTreeSymmetry
 * This class provides a solution to check if a binary tree is a mirror of
 * itself.
 */
public class BinaryTreeSymmetry {

  /**
   * APPROACH: RECURSIVE (DFS)
   * 
   * FIRST PRINCIPLES LOGIC:
   * 1. A tree is symmetric if its left and right subtrees are mirrors of each
   * other.
   * 2. For two subtrees to be mirrors:
   * - Their root values must be identical.
   * - The "outside" branches must match (Left of A == Right of B).
   * - The "inside" branches must match (Right of A == Left of B).
   * 
   * TIME COMPLEXITY (TC): O(N)
   * - We visit every node in the tree exactly once to compare values.
   * 
   * SPACE COMPLEXITY (SC): O(H)
   * - Where H is the height of the tree. This is the space used by the
   * recursion stack. In the worst case (skewed tree), it could be O(N).
   */
  public boolean isSymmetric(Node root) {
    // First Principle: An empty tree is technically symmetric.
    if (root == null) {
      return true;
    }

    // We split the problem: Compare the left side against the right side.
    return isMirror(root.left, root.right);
  }

  private boolean isMirror(Node nodeA, Node nodeB) {
    // BASE CASE 1: Both reach null at the same time.
    // This means the path is perfectly symmetrical so far.
    if (nodeA == null && nodeB == null) {
      return true;
    }

    // BASE CASE 2: One side is null and the other isn't.
    // Structure mismatch means it's not a mirror.
    if (nodeA == null || nodeB == null) {
      return false;
    }

    // BASE CASE 3: Values are different.
    // Even if structure matches, the data must be identical.
    if (nodeA.data != nodeB.data) {
      return false;
    }

    /**
     * RECURSIVE STEP:
     * We check the 'mirrored' children.
     * Imagine folding the tree in half:
     * nodeA.left must match nodeB.right (The outer edges)
     * nodeA.right must match nodeB.left (The inner edges)
     */
    return isMirror(nodeA.left, nodeB.right) && isMirror(nodeA.right, nodeB.left);
  }

  /**
   * Main method to test the symmetry logic.
   */
  public static void main(String[] args) {
    BinaryTreeSymmetry treeChecker = new BinaryTreeSymmetry();

    /*
     * Constructing a Symmetric Tree:
     * 1
     * / \
     * 2 2
     * / \ / \
     * 3 4 4 3
     */
    Node symmetricRoot = new Node(1);
    symmetricRoot.left = new Node(2);
    symmetricRoot.right = new Node(2);
    symmetricRoot.left.left = new Node(3);
    symmetricRoot.left.right = new Node(4);
    symmetricRoot.right.left = new Node(4);
    symmetricRoot.right.right = new Node(3);

    /*
     * Constructing a Non-Symmetric Tree:
     * 1
     * / \
     * 2 2
     * \ \
     * 3 3
     */
    Node nonSymmetricRoot = new Node(1);
    nonSymmetricRoot.left = new Node(2);
    nonSymmetricRoot.right = new Node(2);
    nonSymmetricRoot.left.right = new Node(3);
    nonSymmetricRoot.right.right = new Node(3);

    System.out.println("Is first tree symmetric? " + treeChecker.isSymmetric(symmetricRoot));
    System.out.println("Is second tree symmetric? " + treeChecker.isSymmetric(nonSymmetricRoot));
  }
}