package cpsc331.collections;

import cpsc331.collections.Mapping;
import cpsc331.collections.ChainHashTable;
import java.util.NoSuchElementException;

/**
*
* Implements a Mapping using a hash table with chaining..
*
*/

public class ChainHashMap<K, V> implements Mapping<K, V> {

 // Data Fields
 
 private ChainHashTable<K, V> T;

  /**
  *
  * Default constructor uses a hash table with size 23.
  *
  */
  
  public ChainHashMap() {
  
    T = new ChainHashTable<K, V>(23);
  
  }  
  
  /**
  *
  * Constructor receives table size as input.<br><br>
  *
  * @param s size of hash table to be used
  * @throws IllegalArgumentException if s is not positive
  *
  */
  
  public ChainHashMap(int s) {
  
    if (s > 0) {
      T = new ChainHashTable<K, V>(s);
    } else {
      throw new IllegalArgumentException("Table size must be positive.");
    };
    
  }
    
  // Implements Mapping's "get" method
    
  public V get (K k) throws NoSuchElementException {
    return T.search(k);
  }
  
    
  // Implements Mapping's "set" method
    
  public void set (K k, V v) {
    T.set(k, v);
  }
    
  // Implements Mapping's "remove" method
    
  public V remove (K k) throws NoSuchElementException {
    return T.remove(k);
  }
  
  // Returns hash function being used - for testing
  
  SimpleChainHashFunction<K> hashFunction() {
    return T.hashFunction();
  }
  
  // Returns the table size - used for testing
  
  int tableSize() {
    return T.tableSize();
  }

}
