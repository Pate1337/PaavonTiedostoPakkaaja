package datastructures;

import algorithm.Node;

public class NodePriorityQueue {
  private Node[] a;
  private int heapSize;

  public NodePriorityQueue() {
    this.a = new Node[11];
    this.heapSize = 0;
  }
  /** Returns the head of this queue, but does not remove.
  * Returns null if queue is empty.
  */
  public Node peek() {
    return a[0];
  }
  /** Returns and removes the head of this queue.
  * Returns null if this queue is empty.
  */
  /*public Node poll() {

  }*/
  public void insert(Node k) {
    this.heapSize++;
    int i = this.heapSize - 1;
    while (i > 0 && this.a[(i - 1) / 2].frequency > k.frequency)  {
      this.a[i] = this.a[(i - 1) / 2];
      i = (i - 1) / 2;
    }
    this.a[i] = k;
  }
  public int getHeapSize() {
    return this.heapSize;
  }
  public int getArraySize() {
    return this.a.length;
  }
}