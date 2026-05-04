
class Node {
  int data;
  Node left, right;

  Node(int data) {
    this.data = data;
    this.left = null;
    this.right = null;
  }
}

public class FindCeilBST {

  /**
   * Approach:
   * 1. Initialize 'ans' to -1 (default if no ceil exists).
   * 2. Traverse the tree starting from the root:
   * - If root.data == x: The ceil is the value itself. Return immediately.
   * - If root.data < x: This node is too small. To find a value >= x,
   * we must move to the RIGHT subtree.
   * - If root.data > x: This node is a potential candidate for Ceil.
   * Update 'ans' with root.data and move LEFT to see if a smaller
   * value exists that is still >= x.
   * 3. Return 'ans'.
   * * Time Complexity: O(h), where h is the height of the BST.
   * In the worst case (skewed tree), it is O(n).
   * Auxiliary Space: O(1) as we are using an iterative approach.
   */
  public static int findCeil(Node root, int x) {
    int ans = -1;

    while (root != null) {
      // Case 1: Exact match found
      if (root.data == x) {
        return root.data;
      }

      // Case 2: Current node is smaller than x, go right for larger values
      if (root.data < x) {
        root = root.right;
      }
      // Case 3: Current node is larger than x, it's a candidate
      else {
        ans = root.data;
        // Move left to find a "smaller" larger value (tighter ceil)
        root = root.left;
      }
    }

    return ans;
  }

  public static void main(String[] args) {
    /*
     * 10
     * / \
     * 5 11
     * / \
     * 4 7
     * \
     * 8
     */
    Node root = new Node(10);
    root.left = new Node(5);
    root.right = new Node(11);
    root.left.left = new Node(4);
    root.left.right = new Node(7);
    root.left.right.right = new Node(8);

    int target = 6;
    int result = findCeil(root, target);

    System.out.println("The Ceil of " + target + " is: " + result);

    // Testing with a value that doesn't have a ceil
    int target2 = 15;
    System.out.println("The Ceil of " + target2 + " is: " + findCeil(root, target2));
  }
}