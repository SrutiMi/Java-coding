import java.util.*;

public class MaxHeapPriorityQueue {
  public static void main(String[] args) {
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    maxHeap.add(10);
    maxHeap.add(40);
    maxHeap.add(20);
    maxHeap.add(50);
    System.out.println("Max heap:" + maxHeap);
    System.out.println("Top (peek): " + maxHeap.peek());
    System.out.println("Removed: " + maxHeap.poll());
    System.out.println("Max heap after removal:" + maxHeap);

    System.out.println("Size: " + maxHeap.size());
    System.out.println("Is empty: " + maxHeap.isEmpty());
  }
}
