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
 * APPROACH: Level Order Traversal (BFS)
 * * 1. THE FIRST PRINCIPLE: We use a Queue to visit nodes "width-first."
 * * 2. THE SNAPSHOT: queue.size() at the start of the while-loop tells us
 * exactly how many nodes are on the current level.
 * * 3. THE LOGIC: Process 'n' nodes, add their children to the back, repeat.
 */
public class LevelWiseBinary {

  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null)
      return result;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
      int levelSize = queue.size(); // Capture number of nodes on this level
      List<Integer> currentLevelData = new ArrayList<>();

      for (int i = 0; i < levelSize; i++) {
        TreeNode currentNode = queue.poll();
        currentLevelData.add(currentNode.val);

        if (currentNode.left != null)
          queue.add(currentNode.left);
        if (currentNode.right != null)
          queue.add(currentNode.right);
      }
      result.add(currentLevelData);
    }
    return result;
  }

  // THE MAIN METHOD
  public static void main(String[] args) {
    /*
     * Creating a sample tree:
     * 3
     * / \
     * 9 20
     * / \
     * 15 7
     */
    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right.left = new TreeNode(15);
    root.right.right = new TreeNode(7);

    LevelWiseBinary solution = new LevelWiseBinary();
    List<List<Integer>> result = solution.levelOrder(root);

    // Print the result
    System.out.println("Level Order Traversal:");
    System.out.println(result);
    // Expected Output: [[3], [9, 20], [15, 7]]
  }
}