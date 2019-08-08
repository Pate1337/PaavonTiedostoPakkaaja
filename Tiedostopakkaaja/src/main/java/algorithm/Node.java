package algorithm;

public class Node {
  char character;
  int frequency;
  Node left = null, right = null;

  Node(char c, int f) {
    this.character = c;
    this.frequency = f;
  }

  public Node(char c, int f, Node left, Node right) {
    this.character = c;
    this.frequency = f;
    this.left = left;
    this.right = right;
  }
}