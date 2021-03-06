package lab9;

import edu.princeton.cs.algs4.BST;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Joe Chou
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        } else if (p.key.equals(key)) {
            return p.value;
        } else if (p.key.compareTo(key) > 0) {
            return getHelper(key, p.left);
        } else {
            return getHelper(key, p.right);
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            size += 1;
            return new Node(key, value);
        } else if (p.key.equals(key)) {
            p.value = value;
        } else if (p.key.compareTo(key) > 0) {
            p.left = putHelper(key, value, p.left);
        } else {
            p.right = putHelper(key, value, p.right);
        }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
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
        traverse(root, set);
        return set;
    }

    private void traverse(Node p, Set<K> keySet ) {
        if (p == null) {
            return;
        }
        traverse(p.left, keySet);
        keySet.add(p.key);
        traverse(p.right, keySet);
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        Node parent = findParent(key, root);
        Boolean parentIsRoot = parent == root && parent.key == key;
        V toReturn = parent.value;

        if (!parentIsRoot) {
            Node toRemove = parent.key.compareTo(key) > 0 ? parent.left : parent.right;
            toReturn  = toRemove.value;


            if (toRemove.left == null && toRemove.right == null) {
                if (parent.key.compareTo(key) > 0 ) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            } else if (toRemove.left == null || toRemove.right == null) {

                if (parent.key.compareTo(key) > 0 ) {
                    parent.left = toRemove.left == null ? toRemove.right : toRemove.left;
                } else {
                    parent.right = toRemove.left == null ? toRemove.right : toRemove.left;;
                }
            } else {
                Node newChild = new Node(toRemove.left.key, toRemove.left.value);
                Node temp = toRemove.left;
                while (temp.right != null) {
                    temp = temp.right;
                }
                newChild.key = temp.key;
                newChild.value = temp.value;

                Node tempLeftMostNode = temp.left;
                while (tempLeftMostNode != null) {
                    tempLeftMostNode = tempLeftMostNode.left;
                }

                newChild.right = toRemove.right;
                tempLeftMostNode = toRemove.left;

                if (parent.key.compareTo(key) > 0) {
                    parent.left = newChild;
                } else {
                    parent.right = newChild;
                }

            }

        } else {
            if (parent.left == null && parent.right == null) {
                root = null;
            } else if (parent.left == null || parent.right == null) {
                root = parent.left == null ? parent.right : parent.left;
            } else {
                Node temp = parent.left;
                while (temp.right != null) {
                    temp = temp.right;
                }
                Node tempLeftMostNode = temp;
                while (tempLeftMostNode.left != null) {
                    tempLeftMostNode = tempLeftMostNode.left;
                }
                temp.right = parent.right;
                tempLeftMostNode.left = parent.left;
                root = temp;
            }
        }




        return toReturn;

    }

    private Node findParent (K key, Node p) {
        if (p.key == key) {
            return p;
        } else if (p.left != null && p.left.key == key) {
            return p;
        } else if (p.right != null && p.right.key == key) {
            return p;
        } else if (p.key.compareTo(key) > 0) {
            p = findParent(key, p.left);
        } else if (p.key.compareTo(key) < 0) {
            p = findParent(key, p.right);
        }
        return p;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if (get(key) == value) {
            return null;
        }
        return remove(key);
    }

    @Override
    public Iterator<K> iterator() {
        Set<K> set = keySet();
        return set.iterator();
    }

    public static void main(String[] args) {
        BSTMap<String, Integer> bstmap = new BSTMap<>();
        bstmap.put("hello", 5);
        bstmap.put("cat", 10);
        bstmap.put("fish", 22);
        bstmap.put("zebra", 90);
        bstmap.iterator();
        for (String str : bstmap) {
            System.out.println(str);
        }
    }
}
