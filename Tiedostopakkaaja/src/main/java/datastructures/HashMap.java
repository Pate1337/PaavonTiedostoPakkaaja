package datastructures;

import datastructures.Entry;

public class HashMap<K, V> {
  private Entry<K, V>[] buckets;
  private static final int INITIAL_CAPACITY = 701;
  private int size = 0;

  public HashMap() {
    this(INITIAL_CAPACITY);
  }
  public HashMap(int capacity) {
    this.buckets = new Entry[capacity];
  }
  public void put(K key, V value) {
    Entry<K, V> entry = new Entry<>(key, value, null);
    // Hajautusfunktio: Jakojäännösmenetelmä
    int bucket = key.hashCode() % this.buckets.length;
    Entry<K, V> existing = buckets[bucket];
    if (existing == null) {
      buckets[bucket] = entry;
      size++;
    } else {
      // compare the keys see if key already exists
      while (existing.next != null) {
        if (existing.key.equals(key)) {
          existing.value = value;
          return;
        }
        existing = existing.next;
      }
      if (existing.key.equals(key)) {
        existing.value = value;
      } else {
        existing.next = entry;
        size++;
      }
    }
  }
  public V get(K key) {
    Entry<K, V> bucket = buckets[key.hashCode() % buckets.length];
    while (bucket != null) {
      if (bucket.key.equals(key)) {
        return bucket.value;
      }
      bucket = bucket.next;
    }
    return null;
  }
  public int getArraySize() {
    return this.buckets.length;
  }
  public int size() {
    return this.size;
  }
  public Entry<K, V>[] entrySet() {
    Entry<K, V>[] set = new Entry[this.size];
    if (this.size == 0) return set;

    int indexOfSet = 0;
    for (int i = 0; i < this.buckets.length; i++) {
      if (this.buckets[i] != null) {
        Entry<K, V> bucket = this.buckets[i];
        set[indexOfSet] = bucket;
        indexOfSet++;
        while (bucket.next != null) {
          bucket = bucket.next;
          set[indexOfSet] = bucket;
          indexOfSet++;
        }
      }
    }
    return set;
  }
  public boolean containsKey(K key) {
    V value = get(key);
    if (value == null) return false;
    return true;
  }

  @Override
  public boolean equals(Object o) {
    // Jos parametrina saatu objekti on tämä, palauta true 
    if (o == this) {
        return true;
    }
    // Tämä tarkastaa myös jos o on null
    if (!(o instanceof HashMap)) {
        return false;
    }
    // Muuta o HashMap-olioksi 
    HashMap hm = (HashMap) o;
    if (hm.size() != this.size) return false;
    if (hm.getArraySize() != this.buckets.length) return false;

    // Käy läpi kaikki arvot
    for (int i = 0; i < this.buckets.length; i++) {
      if (this.buckets[i] != null) {
        Entry<K, V> bucket = this.buckets[i];
        // Tarkista, että myös o:ssa on tällainen key ja sitä vastaava value
        if (!hm.get(bucket.key).equals(bucket.value)) return false;
        while (bucket.next != null) {
          bucket = bucket.next;
          if (!hm.get(bucket.key).equals(bucket.value)) return false;
        }
      }
    }
    return true;
  }
}