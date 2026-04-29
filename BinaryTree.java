import java.util.*;

class Node {
  int data;
  Node left; // pointer/reference to left child
  Node right; // pointer/reference to right child

  Node(int data) {
    this.data = data;
    this.left = null;
    this.right = null;
  }
}

class BinaryTree {
  Node root; // root node of the tree

  BinaryTree() {
    this.root = null; // initially tree is empty, the root is not created yet so there is no node to
                      // point to, so we set it to null
  }

  public void insert(int data) {
    Node newNode = new Node(data);
    if (root == null) {
      root = newNode;// the new node becomes the root
      return;
    }
    Queue<Node> q = new LinkedList<>();
    q.add(root);
    while (!q.isEmpty()) {
      Node temp = q.poll();
      // check left child
      if (temp.left == null) {
        temp.left = newNode;
        return;
      } else {
        q.add(temp.left);
      }
      if (temp.right == null) {
        temp.right = newNode;
        return;
      } else {
        q.add(temp.right);
      }
    }
  }

  public void inorder(Node node) {
    if (node == null)
      return;
    inorder(node.left);
    System.out.println(node.data + " ");
    inorder(node.right);
  }

  public void preorder(Node node) {
    if (node == null)
      return;
    System.out.println(node.data + " ");
    preorder(node.left);
    preorder(node.right);
  }

  public void postorder(Node node) {
    if (node == null)
      return;
    postorder(node.left);
    postorder(node.right);
    System.out.println(node.data + " ");
  }

  public void levelOrder() {
    if (root == null)
      return;
    Queue<Node> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      Node temp = queue.poll();
      System.out.println(temp.data + " ");
      if (temp.left != null)
        queue.add(temp.left);
      if (temp.right != null)
        queue.add(temp.right);
    }
  }

  public static void main(String[] args) {
    BinaryTree tree = new BinaryTree();
    tree.insert(1);
    tree.insert(2);
    tree.insert(3);
    tree.insert(4);
    tree.insert(5);
    System.out.println("Inorder traversal:");
    tree.inorder(tree.root);
    System.out.println("Preorder traversal:");
    tree.preorder(tree.root);
    System.out.println("Postorder traversal:");
    tree.postorder(tree.root);
    System.out.println("Level-order traversal:");
    tree.levelOrder();
  }
}
