import java.util.*;

public class MaxHeapArrayList {
  private ArrayList<Integer> heap;

  MaxHeapArrayList() {
    this.heap = new ArrayList<>();
  }

  // insertion
  public void insert(int val) {
    heap.add(val);
    heapifyUp(heap.size() - 1);
  }

  private void heapifyUp(int idx) {
    if (idx == 0)
      return;
    int parentIdx = (idx - 1) / 2;
    if (heap.get(idx) > heap.get(parentIdx)) {
      swap(idx, parentIdx);
      heapifyUp(parentIdx);
    }
  }

  private void swap(int i, int j) {
    int temp = heap.get(i);
    heap.set(i, heap.get(j));
    heap.set(j, temp);
  }

  // extract max
  public int extractMax() {
    if (heap.size() == 0) {
      System.out.println("Heap is empty");
      return -1;
    }
    int max = heap.get(0);
    int last = heap.get(heap.size() - 1);
    if (heap.size() == 0) {
      heap.remove(0);
    } else {
      heap.set(0, heap.get(heap.size() - 1));
      heap.remove(heap.size() - 1);
      heapifyDown(0);
    }
    return max;
  }

  private void heapifyDown(int idx) {
    int left = 2 * idx + 1;
    int right = 2 * idx + 2;
    int largest = idx;
    if (left < heap.size() && heap.get(left) > heap.get(largest)) {
      largest = left;
    }
    if (right < heap.size() && heap.get(right) > heap.get(largest)) {
      largest = right;
    }
    if (largest != idx) {
      swap(idx, largest);
      heapifyDown(largest);
    }

  }

  public void printHeap() {
    System.out.println("ArrayList Heap: " + heap);
  }

  public static void main(String[] args) {
    MaxHeapArrayList myHeap = new MaxHeapArrayList();
    System.out.println("--- ArrayList Heap Testing ---");
    myHeap.insert(50);
    myHeap.insert(100);
    myHeap.insert(30);
    myHeap.insert(200);
    myHeap.printHeap();

    System.out.println("Extract Max: " + myHeap.extractMax());
    myHeap.printHeap();
  }
}
