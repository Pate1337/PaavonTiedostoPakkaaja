package mocks;

/** A class that returns the same hashCode for every object.
* Used to test the HashMap implementation.
*/
public class HashMapObject {
  private char c;

  public HashMapObject(Character c) {
    this.c = c;
  }

  @Override
  public int hashCode() {
    // Always returns the same hashCode
    return 1;
  }
  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    if (this == o) {
      return true;
    }
    HashMapObject hmo = (HashMapObject)o;

    return hmo.getChar() == this.c;
  }
  public char getChar() {
    return this.c;
  }
}