package algorithm;

public class Node {
  public char character;
  public int frequency;
  public Node left = null, right = null;

  public Node(char c, int f) {
    this.character = c;
    this.frequency = f;
  }

  public Node(char c, int f, Node left, Node right) {
    this.character = c;
    this.frequency = f;
    this.left = left;
    this.right = right;
  }

  @Override
  public boolean equals(Object o) {
    // Jos parametrina saatu objekti on tämä, palauta true 
    if (o == this) { 
        return true; 
    }
    // Tämä tarkastaa myös jos o on null
    if (!(o instanceof Node)) { 
        return false; 
    }
    // Muuta o Node-olioksi 
    Node n = (Node) o; 

    // Jos character tai frequency erisuuret, palauta välittömästi false
    if (character != n.character || frequency != n.frequency) {
      return false;
    }

    // Nut vertaillaan left ja right.
    // equals() -metodia ei haluta kutsua parametrinaan null, koska se palauttaisi false
    boolean leftEqual = true;
    if (left != null && n.left != null) {
      // Tarkista, että vasemmat ovat samat
      leftEqual = left.equals(n.left);
    }
    boolean rightEqual = true;
    if (right != null && n.right != null) {
      // Tarkista, että oikeat ovat samat
      rightEqual = right.equals(n.right);
    }
    if (left == null && n.left == null) {
      leftEqual = true;
    }
    if (right == null && n.right == null) {
      rightEqual = true;
    }

    return (leftEqual && rightEqual);
  }
}