import java.util.*;

/**
 * Class: TopView
 * 
 * APPROACH:
 * 1. Dual Queues: Instead of a helper 'Pair' class, we use two queues
 * (nodeQueue and hdQueue)
 * to synchronize the node and its Horizontal Distance (HD).
 * 2. Horizontal Distance: Root is at 0. Left child = parent HD - 1. Right child
 * = parent HD + 1.
 * 3. BFS (Level Order): By processing nodes level by level, the first node to
 * reach
 * a specific HD is guaranteed to be the topmost.
 * 4. Sorting: We use a TreeMap to store HD as the key. This automatically sorts
 * our
 * result from leftmost (negative HD) to rightmost (positive HD).
 * 
 * TIME COMPLEXITY (TC): O(N log N)
 * - N is the number of nodes. We visit each node once: O(N).
 * - Each insertion into TreeMap takes O(log W), where W is the width of the
 * tree.
 * - Total: O(N log W).
 * 
 * SPACE COMPLEXITY (SC): O(N)
 * - Two queues for BFS: O(N).
 * - TreeMap to store vertical columns: O(W), where W <= N.
 */

class Node {
  int data;
  Node left, right;

  Node(int data) {
    this.data = data;
    this.left = null;
    this.right = null;
  }
}

public class TopView {

  public static ArrayList<Integer> getTopView(Node root) {
    ArrayList<Integer> result = new ArrayList<>();
    if (root == null)
      return result;

    // TreeMap keeps HDs in sorted order automatically
    TreeMap<Integer, Integer> map = new TreeMap<>();

    // Two queues to track nodes and their HD values in parallel
    Queue<Node> nodeQueue = new LinkedList<>();
    Queue<Integer> hdQueue = new LinkedList<>();

    nodeQueue.add(root);
    hdQueue.add(0);

    while (!nodeQueue.isEmpty()) {
      Node currNode = nodeQueue.poll();
      int currHD = hdQueue.poll();

      // Logic: If this HD hasn't been seen yet, this is the top node
      if (!map.containsKey(currHD)) {
        map.put(currHD, currNode.data);
      }

      // Left child logic
      if (currNode.left != null) {
        nodeQueue.add(currNode.left);
        hdQueue.add(currHD - 1);
      }

      // Right child logic
      if (currNode.right != null) {
        nodeQueue.add(currNode.right);
        hdQueue.add(currHD + 1);
      }
    }

    // Collect values from sorted TreeMap
    for (int val : map.values()) {
      result.add(val);
    }

    return result;
  }

  public static void main(String[] args) {
    /*
     * Constructing a sample tree:
     * 1 (0)
     * / \
     * 2 (-1) 3 (1)
     * \
     * 4 (0) -> Hidden by 1
     */
    Node root = new Node(1);
    root.left = new Node(2);
    root.right = new Node(3);
    root.left.right = new Node(4);

    ArrayList<Integer> view = getTopView(root);

    System.out.println("Top View of the Binary Tree (Left to Right):");
    System.out.println(view);
    // Expected Output: [2, 1, 3]
  }
}