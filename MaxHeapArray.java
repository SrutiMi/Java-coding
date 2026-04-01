public class MaxHeapArray {
  private int[] heap;
  private int size; // how many elements are currently in the heap
  private int capacity; // the maximum number of elements the heap can hold

  // Constructorfor an empty heap with a set limit
  public MaxHeapArray(int capacity) {
    heap = new int[capacity];
    this.size = 0;
    this.capacity = capacity;
  }

  // To add data while maintaining the complete binary tree property
  public void insert(int value) {
    if (size == capacity) {
      System.out.println("Heap is full. Cannot insert new value.");
      return;
    }
    heap[size] = value; // placing the new element at the end of the array
    int current = size;
    size++;
    heapifyUp(current); // restore the max-heap property by bubbling up the new element

  }

  private void heapifyUp(int index) {
    int parentIndex = (index - 1) / 2;
    if (index > 0 && heap[index] > heap[parentIndex]) {
      swap(index, parentIndex);
      heapifyUp(parentIndex); // recursively heapify up the parent index
    }
  }

  // Extract max - to get the highest priority element and reorganize the rest .
  public int extractMax() {
    if (size == 0) {
      System.out.println("Heap is empty. Cannot extract max.");
      return -1; // or throw an exception
    }
    int maxValue = heap[0]; // the root of the heap is the maximum element
    heap[0] = heap[size - 1]; // move the last element to the root
    size--; // effectively delete the last element

    if (size > 0) {
      heapifyDown(0); // restore the max-heap property by bubbling down the new root element
    }
    return maxValue;
  }

  // Compare with the children and swap with the largst child
  private void heapifyDown(int index) {
    int largest = index;
    int left = 2 * index + 1;
    int right = 2 * index + 2;
    // Check if left child exists and is greater than the current largest
    if (left < size && heap[left] > heap[largest]) {
      largest = left;
    }
    // check if right child exists and is greater than the current largest
    if (right < size && heap[right] > heap[largest]) {
      largest = right;
    }

    if (largest != index) {
      swap(index, largest);// the current root is swapped with the largest element found among its children
      heapifyDown(largest); // recursively heapify down the affected subtree
    }
  }

  // To turn a random array into a heap in O(n) time by calling heapifyDown on all
  // non-leaf nodes starting from the last non-leaf node down to the root.
  public void buildHeap(int[] arr) {
    this.heap = arr;
    this.size = arr.length;
    // Logic: Start from the last non-leaf node and move toward the root.
    // Formula for last non-leaf node: (n / 2) - 1
    for (int i = (size / 2) - 1; i >= 0; i--) {
      heapifyDown(i);
    }
  }

  private void swap(int i, int j) {
    int temp = heap[i];
    heap[i] = heap[j];
    heap[j] = temp;

  }

  public void printHeap() {
    System.out.print("Current Array:");
    for (int i = 0; i < size; i++) {
      System.out.print(heap[i] + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    MaxHeapArray myHeap = new MaxHeapArray(10);

    System.out.println("--- Testing Insertion ---");
    myHeap.insert(10);
    myHeap.insert(30);
    myHeap.insert(20);
    myHeap.insert(5);
    myHeap.insert(40);
    myHeap.printHeap();
    // Expected tree: 40 at root, children 30 and 20...

    System.out.println("\n--- Testing Extract Max ---");
    System.out.println("Extracted: " + myHeap.extractMax()); // Should be 40
    myHeap.printHeap();

    System.out.println("\n--- Testing Build Heap O(n) ---");
    int[] unsorted = { 1, 5, 2, 8, 12, 7 };
    MaxHeapArray builtHeap = new MaxHeapArray(unsorted.length);
    builtHeap.buildHeap(unsorted);
    System.out.print("Built from {1, 5, 2, 8, 12, 7}: ");
    builtHeap.printHeap();
  }
}