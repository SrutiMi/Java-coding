import java.util.*;

// Definition for a binary tree node
class Node {
  int data;
  Node left, right;

  Node(int val) {
    data = val;
    left = right = null;
  }
}

public class BinaryTreeRightSide {

  /**
   * APPROACH 1: ITERATIVE (BFS)
   * Logic: We traverse the tree level by level using a Queue.
   * For each level, we identify the last node processed in that level's loop.
   * That node is the one visible from the right side.
   * 
   * Time Complexity (TC): O(N) - We visit every node exactly once.
   * Space Complexity (SC): O(W) - Where W is the maximum width of the tree (Queue
   * size).
   */
  public List<Integer> rightSideViewBFS(Node root) {
    List<Integer> result = new ArrayList<>();
    if (root == null)
      return result;

    Queue<Node> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
      int levelSize = queue.size();

      for (int i = 0; i < levelSize; i++) {
        Node current = queue.poll();

        // If this is the last node of the current level, it's visible from the right
        if (i == levelSize - 1) {
          result.add(current.data);
        }

        // Standard BFS: Add children to the queue
        if (current.left != null)
          queue.add(current.left);
        if (current.right != null)
          queue.add(current.right);
      }
    }
    return result;
  }

  /**
   * APPROACH 2: RECURSIVE (DFS)
   * Logic: We use a modified Pre-order traversal (Root -> Right -> Left).
   * By going Right first, the first node to reach a new depth is the rightmost
   * node.
   * We use result.size() to check if we've already "seen" this level.
   * 
   * Time Complexity (TC): O(N) - We visit every node once.
   * Space Complexity (SC): O(H) - Where H is the height of the tree (due to
   * recursion stack).
   */
  public List<Integer> rightSideViewDFS(Node root) {
    List<Integer> result = new ArrayList<>();
    solve(root, 0, result);
    return result;
  }

  private void solve(Node root, int level, List<Integer> result) {
    if (root == null)
      return;

    // If current level equals result size, this is the first (rightmost) node of
    // this level
    if (level == result.size()) {
      result.add(root.data);
    }

    // Priority is given to the RIGHT child
    solve(root.right, level + 1, result);
    solve(root.left, level + 1, result);
  }

  public static void main(String[] args) {
    BinaryTreeRightSide tree = new BinaryTreeRightSide();

    /*
     * Constructing the tree from your example:
     * 1
     * / \
     * 2 3
     * /
     * 4
     * /
     * 5
     */
    Node root = new Node(1);
    root.left = new Node(2);
    root.right = new Node(3);
    root.left.left = new Node(4);
    root.left.left.left = new Node(5);

    System.out.println("Right Side View (BFS): " + tree.rightSideViewBFS(root));
    System.out.println("Right Side View (DFS): " + tree.rightSideViewDFS(root));
  }
}