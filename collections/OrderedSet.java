package cpsc331.collections;

import java.util.NoSuchElementException;
import cpsc331.collections.ElementFoundException;
import cpsc331.collections.Set;

/**
*
* Provides an interface for a whose elements have type&nbsp;E,
* where E has a total order.<br><br>
*
* OrderedSet Invariant: A finite set of elements of type E (which
* has a total order) is maintained.
*
* @author Wayne Eberly
*
*/

public interface OrderedSet<E extends Comparable<E>>
  extends Set<E> {
  
  }
