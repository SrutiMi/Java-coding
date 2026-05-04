public class BinarySearchTree {
  static class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
      this.data = data;
      left = right = null;
    }
  }

  static Node insert(Node root, int key) {
    if (root == null)
      return new Node(key);
    if (key < root.data) {
      root.left = insert(root.left, key);
    } else if (key > root.data) {
      root.right = insert(root.right, key);
    }
    return root;
  }

  static boolean search(Node root, int key) {
    if (root == null)
      return false;
    if (root.data == key)
      return true;
    if (key < root.data)
      return search(root.left, key);
    else
      return search(root.right, key);
  }

  static Node findMin(Node root) {
    while (root.left != null) {
      root = root.left;
    }
    return root;
  }

  static Node delete(Node root, int key) {
    if (root == null)
      return null;
    if (key < root.data) {
      root.left = delete(root.left, key);
    } else if (key > root.data) {
      root.right = delete(root.right, key);
    } else {
      if (root.left == null)
        return root.right;
      else if (root.right == null)
        return root.left;

      Node successor = findMin(root.right);
      root.data = successor.data;
      root.right = delete(root.right, successor.data);
    }
    return root;
  }

  static void inorder(Node root) {
    if (root != null) {
      inorder(root.left);
      System.out.print(root.data + " ");
      inorder(root.right);
    }
  }

  static Node lca(Node root, int n1, int n2) {
    if (root == null)
      return null;
    if (n1 < root.data && n2 < root.data) {
      return lca(root.left, n1, n2);
    }
    if (n1 > root.data && n2 > root.data)
      return lca(root.right, n1, n2);

    return root;
  }

  public static void main(String[] args) {
    Node root = null;
    int[] values = { 20, 10, 30, 5, 15, 25, 35 };
    for (int val : values) {
      root = insert(root, val);
    }

    System.out.print("Inorder Traversal: ");
    inorder(root);
    System.out.println();
    System.out.println("Search 15: " + search(root, 15)); // true
    root = delete(root, 10);

    System.out.print("After deleting 10: ");
    inorder(root);
    System.out.println();

    // LCA
    Node lcaNode = lca(root, 5, 15);
    if (lcaNode != null)
      System.out.println("LCA of 5 and 15: " + lcaNode.data);
  }
}