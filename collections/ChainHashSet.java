package cpsc331.collections;

import cpsc331.collections.Set;
import cpsc331.collections.ChainHashTable;
import cpsc331.collections.SimpleChainHashFunction;
import cpsc331.collections.ElementFoundException;
import java.util.NoSuchElementException;

/**
*
* Implements a Set using a hash table with chaining..
*
*/

public class ChainHashSet<E> implements Set<E> {

  // Data Fields
  
  private ChainHashTable<E, Integer> T;
  
  /**
  *
  * Default constructor uses a hash table with size 23.
  *
  */
  
  public ChainHashSet() {
  
    T = new ChainHashTable<E, Integer>(23);
  
  }  
  
  /**
  *
  * Constructor receives table size as input.<br><br>
  *
  * @param s size of hash table to be used
  * @throws IllegalArgumentException if s is not positive
  *
  */
  
  public ChainHashSet(int s) {
  
    if (s > 0) {
      T = new ChainHashTable<E, Integer>(s);
    } else {
      throw new IllegalArgumentException("Table size must be positive.");
    };
  
  }
  
  // Implements Set's "contains" method
  
  public boolean contains(E e) {
    return T.defined(e);
  }
  
  // Implements Set's "include" method
  
  public void include (E e) throws ElementFoundException {
    if (T.defined(e)) {
      throw new ElementFoundException("Element " + e.toString()
        + " is already in this set.");
    };
    T.set(e, null);
  }
  
  // Implements Set's "remove" method
  
  public void remove (E e) throws NoSuchElementException {
    Integer i = T.remove(e);
  }
  
  // Returns hash function used - for testing
  
  SimpleChainHashFunction<E> hashFunction() {
    return T.hashFunction();
  }
  
  // Returns the table size - used for testing
  
  int tableSize() {
    return T.tableSize();
  }
  

}
