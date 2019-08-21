package datastructures;

import datastructures.NodePriorityQueue;
import algorithm.Node;

import org.junit.*;
import static org.junit.Assert.*;

public class NodePriorityQueueTest {
  NodePriorityQueue pq;

  @Before
  public void createPriorityQueue() {
    pq = new NodePriorityQueue();
  }

  @Test
  public void constructorCreatesArrayWithSize11AndHeapSize0() {
    assertEquals(11, pq.getArraySize());
    assertEquals(0, pq.getHeapSize());
  }

  @Test
  public void peekReturnsTheHeadOfQueueButDoesNotRemove() {

    assertEquals(0, pq.getHeapSize());

    pq.insert(new Node('a', 2));

    assertEquals(1, pq.getHeapSize());

    assertEquals(new Node('a', 2), pq.peek());
    assertEquals(1, pq.getHeapSize());
  }
  
  @Test
  public void peekReturnsNullIfQueueIsEmpty() {
    assertEquals(null, pq.peek());
  }
}