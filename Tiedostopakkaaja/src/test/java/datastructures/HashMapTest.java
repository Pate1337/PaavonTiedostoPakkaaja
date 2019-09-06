package datastructures;

import datastructures.HashMap;
import mocks.HashMapObject;
import datastructures.Entry;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class HashMapTest {

  @Test
  public void contructorWorks() {
    HashMap<Character, Integer> hm = new HashMap<>();

    assertEquals(701, hm.getArraySize());

    assertEquals(0, hm.size());

    HashMap<Character, String> hm2 = new HashMap<>();

    assertEquals(701, hm2.getArraySize());

    assertEquals(0, hm2.size());
  }

  @Test
  public void putIncreasesTheSizeOfHashMapIfTheKeyDoesNotYetExistInHashMap() {
    HashMap<Character, Integer> hm = new HashMap<>();

    hm.put('a', 2);

    assertEquals(1, hm.size());

    hm.put('b', 8);

    assertEquals(2, hm.size());
  }

  @Test
  public void getReturnsTheCorrectValue() {
    HashMap<Character, Integer> hm = new HashMap<>();

    hm.put('a', 2);
    hm.put('b', 3);
    hm.put('c', 4);

    assertEquals(3, (int)hm.get('b'));
    assertEquals(2, (int)hm.get('a'));
    assertEquals(4, (int)hm.get('c'));
  }

  @Test
  public void ifThereAlreadyIsAnEntryWithTheSameKeyThenReplaceIt() {
    HashMap<Character, Integer> hm = new HashMap<>();

    hm.put('a', 2);

    assertEquals(1, hm.size());
    assertEquals(2, (int)hm.get('a'));

    hm.put('a', 5);

    assertEquals(1, hm.size());
    assertEquals(5, (int)hm.get('a'));
  }
  @Test
  public void addingToHashMapWorksInCaseOfHashCodeCollisions() {
    // HashMapObject always returns the hashCode 1 for every instance.
    HashMap<HashMapObject, Integer> hm = new HashMap<>();

    hm.put(new HashMapObject('a'), 4);

    assertEquals(1, hm.size());

    hm.put(new HashMapObject('b'), 3);

    assertEquals(2, hm.size());

    hm.put(new HashMapObject('c'), 5);

    assertEquals(3, hm.size());
  }

  @Test
  public void getWorksWhenThereAreKeysWithSameHashCodes() {
    HashMap<HashMapObject, Integer> hm = new HashMap<>();

    hm.put(new HashMapObject('a'), 4);
    hm.put(new HashMapObject('b'), 3);
    hm.put(new HashMapObject('c'), 5);

    assertEquals(4, (int)hm.get(new HashMapObject('a')));
    assertEquals(3, (int)hm.get(new HashMapObject('b')));
    assertEquals(5, (int)hm.get(new HashMapObject('c')));
  }

  @Test
  public void entrySetMethodReturnsEmptyArrayIfNoEntriesInHashMap() {
    HashMap<Character, Integer> hm = new HashMap<>();

    assertEquals(0, hm.entrySet().length);
  }

  @Test
  public void entrySetMethodReturnsAllThEntriesInTheHashMap() {
    HashMap<Character, Integer> hm = new HashMap<>();

    hm.put('a', 1);
    hm.put('b', 2);
    hm.put('c', 3);

    Entry<Character, Integer>[] entry = hm.entrySet();
    assertEquals(3, entry.length);

    ArrayList<Character> keys = new ArrayList<>();
    ArrayList<Integer> values = new ArrayList<>();

    for (int i = 0; i < entry.length; i++) {
      keys.add(entry[i].getKey());
      values.add(entry[i].getValue());
    }
    
    assertTrue(keys.contains('a'));
    assertTrue(keys.contains('b'));
    assertTrue(keys.contains('c'));
    assertTrue(values.contains(1));
    assertTrue(values.contains(2));
    assertTrue(values.contains(3));
  }

  @Test
  public void containsKeyReturnsTrueIfTheKeyIsInHashMapOtherwiseFalse() {
    HashMap<Character, Integer> hm = new HashMap<>();

    hm.put('a', 1);
    hm.put('b', 2);
    hm.put('c', 3);

    assertEquals(true, hm.containsKey('b'));
    assertEquals(false, hm.containsKey('o'));
  }

  @Test
  public void equalsMethodWorksBetweenTwoHashMaps() {
    HashMap<Character, Integer> hm = new HashMap<>();
    hm.put('a', 1);
    hm.put('g', 56);
    hm.put('z', 99);

    HashMap<Character, Integer> hm2 = new HashMap<>();
    hm2.put('g', 56);
    hm2.put('a', 1);
    hm2.put('z', 99);

    assertTrue(hm.equals(hm2));

    // Also with collisions
    HashMap<HashMapObject, Integer> hm3 = new HashMap<>();

    hm3.put(new HashMapObject('a'), 4);
    hm3.put(new HashMapObject('b'), 3);
    hm3.put(new HashMapObject('c'), 5);

    HashMap<HashMapObject, Integer> hm4 = new HashMap<>();

    hm4.put(new HashMapObject('a'), 4);
    hm4.put(new HashMapObject('c'), 5);
    hm4.put(new HashMapObject('b'), 3);

    assertTrue(hm3.equals(hm4));

    HashMap<Character, Integer> hm5 = new HashMap<>();
    hm5.put('a', 1);
    hm5.put('g', 55);
    hm5.put('z', 99);

    HashMap<Character, Integer> hm6 = new HashMap<>();
    hm6.put('g', 56);
    hm6.put('a', 1);
    hm6.put('z', 99);

    assertTrue(!hm5.equals(hm6));

    HashMap<HashMapObject, Integer> hm7 = new HashMap<>();

    hm7.put(new HashMapObject('a'), 4);
    hm7.put(new HashMapObject('b'), 6);
    hm7.put(new HashMapObject('c'), 5);

    HashMap<HashMapObject, Integer> hm8 = new HashMap<>();

    hm8.put(new HashMapObject('a'), 4);
    hm8.put(new HashMapObject('c'), 5);
    hm8.put(new HashMapObject('b'), 3);

    assertTrue(!hm7.equals(hm8));
  }
}