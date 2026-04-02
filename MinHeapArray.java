public class MinHeapArray {
  private int size;
  private int capacity;
  private int[] heap;

  // Constructor
  MinHeapArray(int capacity) {
    this.capacity = capacity;
    heap = new int[capacity];
    this.size = 0;
  }

  // insertion
  public void insert(int value) {
    if (size == capacity) {
      System.out.println("No space in the heap");
      return;
    }
    heap[size] = value;
    heapifyUp(size);
    size++;

  }

  private void heapifyUp(int index) {
    int parentIdx = (index - 1) / 2;
    if (index > 0 && heap[index] < heap[parentIdx]) {
      swap(index, parentIdx);
      heapifyUp(parentIdx);
    }
  }

  private void swap(int index1, int index2) {
    int temp = heap[index1];
    heap[index1] = heap[index2];
    heap[index2] = temp;
  }

  // Extracting the minimum
  public int extractMin() {
    if (size == 0) {
      System.out.println("Heap is empty");
      return -1;
    }
    int minValue = heap[0];
    // take the last element and put it in the place of root
    heap[0] = heap[size - 1];
    size--;
    if (size > 0) {
      heapifyDown(0);
    }
    return minValue;
  }

  private void heapifyDown(int idx) {
    int leftChild = 2 * idx + 1;
    int rightChild = 2 * idx + 2;
    int minimum = idx;
    if (leftChild < size && heap[leftChild] < heap[minimum])
      minimum = leftChild;

    if (rightChild < size && heap[rightChild] < heap[minimum])
      minimum = rightChild;

    if (minimum != idx) {
      swap(idx, minimum);
      heapifyDown(minimum);
    }
  }

  // building the heap - turning a random array into a heap in O(n) time
  public void buildHeap(int[] arr) {
    this.heap = arr;
    this.size = arr.length;
    this.capacity = arr.length;

    // start from the last non-leap node and heapify down each node
    int i = (size / 2) - 1;
    for (; i >= 0; i--) {
      heapifyDown(i);
    }

  }

  public void printHeap() {
    for (int i = 0; i < size; i++) {
      System.out.print(heap[i] + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    MinHeapArray heap = new MinHeapArray(10);
    System.out.println("--- Testing Insertion ---");
    heap.insert(10);
    heap.insert(30);
    heap.insert(20);
    heap.insert(5);
    heap.insert(40);
    heap.printHeap();
    System.out.println("\n--- Testing Extract Min ---");
    System.out.println("Extracted: " + heap.extractMin()); // Should be 40
    heap.printHeap();

    System.out.println("\n--- Testing Build Heap O(n) ---");
    int[] unsorted = { 1, 5, 2, 8, 12, 7 };
    MinHeapArray builtHeap = new MinHeapArray(unsorted.length);
    builtHeap.buildHeap(unsorted);
    System.out.print("Built from {1, 5, 2, 8, 12, 7}: ");
    builtHeap.printHeap();
  }
}
