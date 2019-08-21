package algorithm;

import algorithm.Node;

import org.junit.*;
import static org.junit.Assert.*;

public class NodeTest {

  @Test
  public void constructorWithTwoParametersWorks() {
    Node n = new Node('a', 2);

    assertEquals('a', n.character);
    assertEquals(2, n.frequency);
    assertEquals(null, n.left);
    assertEquals(null, n.right);
  }

  @Test
  public void constructorWithFourParametersWorks() {
    Node n = new Node('b', 3, new Node('c', 1), new Node('d', 4));

    assertEquals('b', n.character);
    assertEquals(3, n.frequency);
    assertEquals(new Node('c', 1), n.left);
    assertEquals(new Node('d', 4), n.right);
  }

  @Test
  public void equalityWorksBetweenTwoNodes() {
    Node a = new Node('f', 5);
    Node b = new Node('f', 5);

    assertTrue(a.equals(b));

    a = new Node('f', 5);
    b = new Node('f', 6);

    assertTrue(!a.equals(b));

    a = new Node('f', 5);
    b = new Node('a', 5);

    assertTrue(!a.equals(b));

    a = new Node('h', 6, new Node('a', 2), new Node('c', 3));
    b = new Node('h', 6, new Node('a', 2), new Node('c', 3));

    assertTrue(a.equals(b));

    a = new Node('h', 6, new Node('a', 2), new Node('g', 3));
    b = new Node('h', 6, new Node('a', 2), new Node('c', 3));

    assertTrue(!a.equals(b));

    a = new Node('h', 6, new Node('a', 2, new Node('a', 1), new Node('b', 2)), new Node('c', 3));
    b = new Node('h', 6, new Node('a', 2, new Node('a', 1), new Node('b', 2)), new Node('c', 3));

    assertTrue(a.equals(b));

    a = new Node('h', 6, new Node('a', 2, new Node('a', 1), new Node('b', 2)), new Node('c', 3));
    b = new Node('h', 6, new Node('a', 2, new Node('h', 1), new Node('b', 2)), new Node('c', 3));

    assertTrue(!a.equals(b));
  }
}