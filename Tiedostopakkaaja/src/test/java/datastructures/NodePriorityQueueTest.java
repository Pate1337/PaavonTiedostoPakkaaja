package datastructures;

import datastructures.NodePriorityQueue;
import algorithm.Node;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.PriorityQueue;
import java.util.Arrays;

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

   /* assertEquals(131072, pq.getArraySize());

    pq.insert(new Node('a', 1));

    assertEquals(131072, pq.getArraySize());*/
  }

  @Test
  public void sizeMethodReturnsTheNumberOfNodesInQueue() {
    pq.insert(new Node('a', 1));

    assertEquals(1, pq.size());

    pq.insert(new Node('a', 1));

    assertEquals(2, pq.size());
  }

  private Node[] createArrayOfNodes(int amount, boolean worstCase) {
    Node[] nodes = new Node[amount];
    int j = amount;
    for (int i = 0; i < amount; i++) {
      if (worstCase) {
        nodes[i] = new Node('a', j);
        j--;
      } else {
        nodes[i] = new Node('a', i);
      }
    }
    return nodes;
  }

  private long timeItTakesToInsertNodesToNodePriorityQueue(Node[] nodes) {

    int nodesLength = nodes.length;
    NodePriorityQueue npq = new NodePriorityQueue();

    long start = System.currentTimeMillis();
    for (int i = 0; i < nodesLength; i++) {
      npq.insert(nodes[i]);
    }
    long end = System.currentTimeMillis();

    return (end - start);
  }

  private long timeItTakesToInsertNodesToPriorityQueue(Node[] nodes) {

    PriorityQueue<Node> javaPq = new PriorityQueue<>((l, r) -> l.frequency - r.frequency);
    int nodesLength = nodes.length;

    long start = System.currentTimeMillis();
    for (int i = 0; i < nodesLength; i++) {
      javaPq.add(nodes[i]);
    }
    long end = System.currentTimeMillis();

    return (end - start);
  }

  private void createReportOnInsert(Node[] nodes) {

    System.out.println("TIME IT TAKES TO INSERT " + nodes.length + " NODES TO QUEUE\n");

    long sumOnNodePriorityQueue = 0;
    int timesToRun = 50;

    for (int i = 0; i < timesToRun; i++) {
      sumOnNodePriorityQueue += timeItTakesToInsertNodesToNodePriorityQueue(nodes);
    }
    long averageTimeOnNodePriorityQueue = sumOnNodePriorityQueue / timesToRun;

    System.out.println("NodePriorityQueue: " + Long.toString(averageTimeOnNodePriorityQueue) + " ms");

    long sumOnPriorityQueue = 0;

    for (int i = 0; i < timesToRun; i++) {
      sumOnPriorityQueue += timeItTakesToInsertNodesToPriorityQueue(nodes);
    }
    long averageTimeOnPriorityQueue = sumOnPriorityQueue / timesToRun;

    System.out.println("PriorityQueue: " + Long.toString(averageTimeOnPriorityQueue) + " ms\n");
  }

  @Test
  public void nodePriorityQueueVsJavaPriorityQueueOnInsert() {

    System.out.println("\nRUNNING TIME-EFFICIENCY TESTS...");
    System.out.println("This may take up to one minute\n");

    Node[] nodes = createArrayOfNodes(4000000, true);

    createReportOnInsert(Arrays.copyOfRange(nodes, 0, 10000));

    createReportOnInsert(Arrays.copyOfRange(nodes, 0, 100000));

    createReportOnInsert(Arrays.copyOfRange(nodes, 0, 1000000));

    createReportOnInsert(Arrays.copyOfRange(nodes, 0, 2000000));

    createReportOnInsert(Arrays.copyOfRange(nodes, 0, 4000000));

    assertTrue(true);
  }
}