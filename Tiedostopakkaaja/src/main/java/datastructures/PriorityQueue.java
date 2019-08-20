package datastructures;

import java.util.Comparator;

/**
 * Generic class PriorityQueue.
 * @param <T> The type of the values being held in the PriorityQueue.
 */
public class PriorityQueue<T> {
  // private T t;
  private Comparator c;
  private Object[] a;
  private int heapSize;

  public PriorityQueue(Comparator<? super T> comparator) {
    this.a = new Object[11];
    this.c = comparator;
    this.heapSize = 0;
  }
  public <T> PriorityQueue() {
    this.a = new Object[11];
    this.heapSize = 0;
  }
  public void add(T value) {
    this.a[this.heapSize] = value;
    this.heapSize++;
  }
  public T get(int index) {
    return (T)a[index];
  }
  public void compareValues(int index1, int index2) {
    if (c.compare(a[index1], a[index2]) == 0) {
      System.out.println("Values are the same");
    } else if (c.compare(a[index1], a[index2]) < 0) {
      System.out.println("First value is smaller");
    } else {
      System.out.println("Second value is smaller");
    }
  }
  /*public void insert(T k) {
    this.heapSize++;
    int i = this.heapSize - 1;
    while (i > 0 && c.compare(this.a[this.a[(i - 2) / 2]], k) > 0) {
      this.a[i] = this.a[this.a[(i - 2) / 2]];
      i = this.a[(i - 2) / 2];
    }
    this.a[i] = k;
  }*/
}