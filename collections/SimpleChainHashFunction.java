package cpsc331.collections;

import cpsc331.collections.ChainHashFunction;

/**
*
* Provides an implementation of a ChainHashFunction &mdash; that is, a class
* providing a hash function for a hash table with chaining &mdash; by making
* a straightforward use of Java&rsquo;s hashCode function
*
*/

public class SimpleChainHashFunction<E> implements ChainHashFunction<E> {

  // Data Fields
  
  private final int TABLESIZE;
  
  /**
  *
  * Provides a hash function for a hash table with chaining, for a given table
  * size
  * <br><br>
  *
  * @param T table size
  * @throws IllegalArgumentException if the input table size is not positive
  * <br><br>
  *
  * Precondition: An integer T is given as input.<br>
  * Postcondition: If T is positive then a ChainHasFunction that can be used
  *   with a hash table of size&nbsp;T is created. An IllegalArgumentException
  *   is thrown otherwise.
  *
  */
  
  public SimpleChainHashFunction ( int T ) throws IllegalArgumentException {
  
    if (T > 0) {
    
      TABLESIZE = T;
    
    } else {
    
      throw new IllegalArgumentException("Table size must be positive.");
    
    };
  
  }
  
  public int hashValue (E e) {
  
    return e.hashCode() % TABLESIZE;
  
  }
  

}
