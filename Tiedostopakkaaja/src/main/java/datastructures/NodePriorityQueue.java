package datastructures;

import algorithm.Node;

import java.util.Arrays;

public class NodePriorityQueue {
  private Node[] a;
  private int heapSize;

  /** Creates a new NodePriorityQueue
  * The initial length of the array will be 11
  */
  public NodePriorityQueue() {
    this.a = new Node[11];
    this.heapSize = 0;
  }
  /** Creates a copy of the NodePriorityQueue given in the parameter
  * @param pq NodePriorityQueue that will be copied
  */
  public NodePriorityQueue(NodePriorityQueue pq) {
    this.a = pq.getArray();
    this.heapSize = pq.size();
  }
  /** Returns the head of this queue, but does not remove.
  * Returns null if queue is empty.
  * @return The head of this queue. null if queue is empty.
  */
  public Node peek() {
    return a[0];
  }
  /** Returns and removes the head of this queue.
  * Returns null if this queue is empty.
  * @return The head of this queue. null if queue is empty.
  */
  public Node poll() {
    if (this.heapSize == 0) return null;
    Node min = this.a[0];
    this.a[0] = this.a[this.heapSize - 1];
    this.heapSize--;
    heapify(0);
    return min;
  }
  /** Inserts a new Node to the queue.
  * @param k Node to insert to the queue
  * @see Node
  */
  public void insert(Node k) {
    this.heapSize++;
    if (this.heapSize > this.a.length) {
      doubleArraySize();
    }
    int i = this.heapSize - 1;
    while (i > 0 && this.a[(i - 1) / 2].frequency > k.frequency)  {
      this.a[i] = this.a[(i - 1) / 2];
      i = (i - 1) / 2;
    }
    this.a[i] = k;
  }
  private void heapify(int i) {
    // li on left index ja ri on right index
    int li = 2 * i + 1;
    int ri = 2 * i + 2;
    if (ri <= this.heapSize - 1) {
      int smallest = 0;
      if (this.a[li].frequency > this.a[ri].frequency) {
        smallest = ri;
      } else {
        smallest = li;
      }
      if (this.a[i].frequency > this.a[smallest].frequency) {
        Node temp = this.a[i];
        this.a[i] = this.a[smallest];
        this.a[smallest] = temp;
        heapify(smallest);
      }
    } else if (li == this.heapSize - 1 && this.a[i].frequency > this.a[li].frequency) {
      Node temp = this.a[i];
      this.a[i] = this.a[li];
      this.a[li] = temp;
    }
  }
  /** Returns the size of the heap, which is the number of Nodes in the queue
  * @return The number of nodes in the queue.
  */
  public int size() {
    return this.heapSize;
  }
  public int getArraySize() {
    return this.a.length;
  }
  public Node[] getArray() {
    return this.a;
  }
  private void doubleArraySize() {
    int newLength = this.a.length * 2;
    if (newLength > 4000000) {
      // Älä tee suurempaa
      newLength = 4000000;
    }
    this.a = Arrays.copyOf(this.a, newLength);
  }
}