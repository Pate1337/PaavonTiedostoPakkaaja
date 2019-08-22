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
    assertEquals(0, pq.size());
  }

  @Test
  public void peekReturnsTheHeadOfQueueButDoesNotRemove() {

    assertEquals(0, pq.size());

    pq.insert(new Node('a', 2));

    assertEquals(1, pq.size());

    assertEquals(new Node('a', 2), pq.peek());
    assertEquals(1, pq.size());
  }
  
  @Test
  public void peekReturnsNullIfQueueIsEmpty() {
    assertEquals(null, pq.peek());
    assertEquals(0, pq.size());
  }

  @Test
  public void insertWorksSoThatLeftAndRightNodesAreAlwaysBiggerThanParent() {

    pq.insert(new Node('a', 10));

    Node[] array = pq.getArray();
    assertEquals(new Node('a', 10), array[0]);

    pq.insert(new Node('b', 1));

    array = pq.getArray();
    assertEquals(new Node('b', 1), array[0]);
    assertEquals(new Node('a', 10), array[1]);

    pq.insert(new Node('c', 5));

    array = pq.getArray();
    assertEquals(new Node('b', 1), array[0]);
    assertEquals(new Node('a', 10), array[1]);
    assertEquals(new Node('c', 5), array[2]);

    pq.insert(new Node('d', 13));

    array = pq.getArray();
    assertEquals(new Node('b', 1), array[0]);
    assertEquals(new Node('a', 10), array[1]);
    assertEquals(new Node('c', 5), array[2]);
    assertEquals(new Node('d', 13), array[3]);

    pq.insert(new Node('e', 4));

    array = pq.getArray();
    assertEquals(new Node('b', 1), array[0]);
    assertEquals(new Node('e', 4), array[1]);
    assertEquals(new Node('c', 5), array[2]);
    assertEquals(new Node('d', 13), array[3]);
    assertEquals(new Node('a', 10), array[4]);

    pq.insert(new Node('f', 12));

    array = pq.getArray();
    assertEquals(new Node('b', 1), array[0]);
    assertEquals(new Node('e', 4), array[1]);
    assertEquals(new Node('c', 5), array[2]);
    assertEquals(new Node('d', 13), array[3]);
    assertEquals(new Node('a', 10), array[4]);
    assertEquals(new Node('f', 12), array[5]);
  }

  @Test
  public void insertWorksForNodesThatHaveSameFrequencies() {

    pq.insert(new Node('a', 3));
    pq.insert(new Node('b', 3));

    Node[] array = pq.getArray();
    assertEquals(new Node('a', 3), array[0]);
    assertEquals(new Node('b', 3), array[1]);

    pq.insert(new Node('c', 3));

    array = pq.getArray();
    assertEquals(new Node('a', 3), array[0]);
    assertEquals(new Node('b', 3), array[1]);
    assertEquals(new Node('c', 3), array[2]);
  }

  // TODO: kirjoita testi myös tapaukselle, jossa frequencyt samoja.
  @Test
  public void pollAlwaysReturnsTheNodeWithSmallestFrequencyAndRemovesIt() {

    pq.insert(new Node('a', 10));
    pq.insert(new Node('b', 1));
    pq.insert(new Node('c', 5));
    pq.insert(new Node('d', 13));
    pq.insert(new Node('e', 4));
    pq.insert(new Node('f', 12));

    assertEquals(6, pq.size());

    assertEquals(new Node('b', 1), pq.poll());
    assertEquals(5, pq.size());

    assertEquals(new Node('e', 4), pq.poll());
    assertEquals(4, pq.size());

    assertEquals(new Node('c', 5), pq.poll());
    assertEquals(3, pq.size());

    assertEquals(new Node('a', 10), pq.poll());
    assertEquals(2, pq.size());

    assertEquals(new Node('f', 12), pq.poll());
    assertEquals(1, pq.size());

    assertEquals(new Node('d', 13), pq.poll());
    assertEquals(0, pq.size());
  }

  @Test
  public void pollReturnsNullIfQueueIsEmpty() {
    assertEquals(null, pq.poll());
    assertEquals(0, pq.size());
  }

  @Test
  public void priorityQueuesArraySizeDoublesWhenNextNodeToAddWouldGoOutOfTheArray() {
    for (int i = 0; i < 11; i++) {
      pq.insert(new Node('a', 1));
    }
    assertEquals(11, pq.getArraySize());

    pq.insert(new Node('a', 1));

    // 12 nodes now

    assertEquals(22, pq.getArraySize());

    for (int i = 0; i < 11; i++) {
      pq.insert(new Node('a', 1));
    }

    // 23 nodes now

    assertEquals(44, pq.getArraySize());

    for (int i = 0; i < 90089; i++) {
      pq.insert(new Node('a', 1));
    }

    // 90112 nodes now

    assertEquals(90112, pq.getArraySize());

    pq.insert(new Node('a', 1));

    // 90113 nodes now

    assertEquals(131072, pq.getArraySize());

    pq.insert(new Node('a', 1));

    assertEquals(131072, pq.getArraySize());
  }

  @Test
  public void sizeMethodReturnsTheNumberOfNodesInQueue() {
    pq.insert(new Node('a', 1));

    assertEquals(1, pq.size());

    pq.insert(new Node('a', 1));

    assertEquals(2, pq.size());
  }
}