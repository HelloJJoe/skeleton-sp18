package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  @author Your Joe Chou
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;

    private ArrayMap<K, V>[] buckets;
    private int size;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        int hash = hash(key);
        return buckets[hash].containsKey(key) ? buckets[hash].get(key) : null;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        int hash = hash(key);
        if (!buckets[hash].containsKey(key)) {
            size += 1;
        }
        buckets[hash].put(key,value);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (int i = 0; i < buckets.length; i += 1) {
            Set<K> temp = buckets[i].keySet();
            set.addAll(temp);
        }
        return set;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        int hash = hash(key);
        V toReturn = buckets[hash].remove(key);
        return toReturn;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        if (get(key) != value) {
            return null;
        }
        return remove(key);
    }

    @Override
    public Iterator<K> iterator() {
        Set<K> set = keySet();
        return keySet().iterator();
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> mhm = new MyHashMap<>();
        mhm.put("hello", 5);
        mhm.put("cat", 10);
        mhm.put("fish", 22);
        mhm.put("zebra", 90);
//        Set<String> set = mhm.keySet();
        for (String str : mhm) {
            System.out.println(str);
        }

    }
}
