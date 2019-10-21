package cpsc331.collections;

import cpsc331.collections.SimpleChainHashFunction;
import java.util.NoSuchElementException;
import java.util.ArrayList;

/**
*
* Implements a hash table with chaining that can be used to implement either
* a Set or a Mapping: Table entries store ordered pairs of keys and values.
* <br><br>
*
* ChainHashTable Invarianb:<br>
* <ol style="list-style-type: lower-alpha">
* <li> TABLESIZE is a positive integer. </li>
* <li> H is a well-defined total function from K to the set of integers
*      between 0 and T&minus;1 </li>
* <li> T is a hash table with chaining with size TABLESIZE, that is being used
*      to represent a partial function f from&nbsp;K to&nbsp;V:  For each integer
*      i from&nbsp;0 to TABLESIZE&minus;1, T[i] is a linked list storing ordered
*      pairs (k,&nbsp;v) such that f(k) is defined and equal to&nbsp;v and such
*      that hashValue(k)=i. </li>
* </ol>
*
*/

public class ChainHashTable<K, V> {

  //
  // Provides a node for a linked list used by this hash table
  //
  // ListNode Invariant:
  // a) This node stores a key with type K and value V.
  // b) next is a reference to another ListNode, which may be null.
  
  private class ListNode {
  
    // Data Fields:
    
    private K key;
    private V value;
    private ListNode next;
    
    //
    // Constructor
    //
    // Precondition: A key k with type K anv value v with type V are given as input.
    // Postcondition: A ListNode with key k, value v, and such that next is null,
    //  is returned as output.
    //
    
    public ListNode (K k, V v) {
    
      key = k;
      value = v;
      next = null;
    
    }
  
  }


  // 
  // Data Fields
  //
  
  private final int TABLESIZE;                 // Size of this hash table
  private final SimpleChainHashFunction<K> H;  // Hash function to be used
  private ArrayList<ListNode> T;               // Hash table with size TABLESIZE
  
  /**
  *
  * Creates an empty hash table.
  * <br><br>
  *
  * @param t the table size to be used
  * @throws IllegalArgumentException if the input table size is not positive
  * <br><br>
  *
  * Precondition: An integer t is given as input.<br>
  * Postcondition: If t is positive then an empty hash table with chaining, with
  *  size&nbsp;t, is created. An IllegalArgumentException is thrown otherwise.
  *
  */
  
  public ChainHashTable (int t) throws IllegalArgumentException {
  
    if (t > 0) {
    
      TABLESIZE = t;
      H = new SimpleChainHashFunction<K>(TABLESIZE);
      T = new ArrayList<ListNode>(TABLESIZE);
      int i = 0;
      while (i < TABLESIZE) {
        T.add(null);
        i = i+1;
      }
    
    } else {
    
      throw new IllegalArgumentException("Table size must be positive.");
    
    };
  
  }
  
  /**
  *
  * Searches for the value associated with a given key, throwing an exception
  * if no such value is defined.<br><br>
  *
  * @param k the key whose value is being checked for
  * @return the value associated with this key
  * @throws NoSuchElementException if no value for this key is defined
  * <br><br>
  *
  * Precondition: A non-null key k with type&nbsp;K is received as input.<br>
  * Postcondition: If a value is associated with&nbsp;k then this is returned.
  *  An IllegalArgumentException is thrown otherwise.
  *
  */
  
  public V search (K k) throws NoSuchElementException {
  
    int indx = H.hashValue(k);
    ListNode node = T.get(indx);
    while (node != null) {
      if (k.equals(node.key)) {
        return node.value;
      } else {
        node = node.next;
      };
    };
    throw new NoSuchElementException("There is no value for this key.");
  }
  
  /**
  *
  * Reports whether a value for a given key k is defined.<br><br>
  *
  * @param k a key of type&nbsp;k
  * @return true if a value is associated with k; false otherwise
  * <br><br>
  *
  * Precondition: A key&nbsp;k of type&nbsp;k is given as input.<br>
  * Postcondition: If a value is defined for this key then &ldquo;true&rdquo;
  *   is returned; &ldquo;false&rdquo; is returned otherwise.
  *
  */
  
  public boolean defined (K k) {
  
   try {
   
     V v = this.search(k);
     return true;
   
   } catch (NoSuchElementException e) {
     return false;
   }
  
  }
  
  /**
  *
  * Sets the value associated with an input key k to be an input value v.<br><br>
  *
  * @param k the value for which a new value is to be defined
  * @param v the value to be associated with this key
  * <br><br>
  *
  * Precondition: A key k with type&nbsp;K and value v with type&nbsp;V are
  *  given as input.<br>
  * Postcondition: The partial function f being represented is changed so that
  *  f(k) = v.
  *
  */
  
  public void set (K k, V v) {
  
    int indx = H.hashValue(k);
    boolean found = false;
    ListNode node = T.get(indx);
    while ((node != null) && !found) {
      if (k.equals(node.key)) {
        node.value = v;
        found = true;
      } else {
        node = node.next;
      };
    };
    if (!found) {
      node = new ListNode(k, v);
      node.next = T.get(indx);
      T.set(indx, node);
    };
  
  }
  
  /**
  *
  * Sets the value for a given key to be undefined.<br><br>
  *
  * @param k the key for which no value should be defined
  * @return the value previously associated with this key
  * @throws NoSuchElementException if no value was defined for this key
  * <br><br>
  *
  * Precondition: A key k of type&nbsp;K is given as input.<br>
  * Postcondition: If a value v was associated with this key then this value
  *  is returned and no value is associated with the key after that.
  *  A NoSuchElementException is thrown otherwise.
  *
  */
  
  public V remove (K k) throws NoSuchElementException {
  
    int indx = H.hashValue(k);
    if (T.get(indx) == null) {
      throw new NoSuchElementException("No value is defined for this key.");
    } else if (k.equals((T.get(indx)).key)) {
      ListNode node = T.get(indx);
      T.set(indx, node.next);
      node.next = null;                // Probably not a necessary operation
      return node.value;
    } else {
      ListNode previous = T.get(indx);
      ListNode current = previous.next;
      while (current != null) {
        if (k.equals(current.key)) {
          previous.next = current.next;
          current.next = null;        // Probably not a necessary operation
          return current.value;
        } else {
          previous = current;
          current = current.next;
        };
      };
      throw new NoSuchElementException("No value is defined for this key.");
    }
  
  }
  
  // Returns hash function being used - for testing
  
  SimpleChainHashFunction<K> hashFunction() {
    return H;
  }

  // Returns the table size - used for teting
  
  int tableSize(){
    return TABLESIZE;
  }

}