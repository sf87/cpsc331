
package cpsc331.collections;

import java.util.NoSuchElementException;
import cpsc331.collections.ElementFoundException;

/**
*
* Provides an interface for a set whose elements have
* type&nbsp;E.<br><br>
*
* Set Invariant: A finite set of elements of type E is maintained.
*
* @author Wayne Eberly
*
*/

public interface Set<E> {

  /**
  *
  * Reports whether a given element belongs to this set.<br><br>
  *
  * @param e the element to be searched for
  * @return true if this element is found; false otherwise
  * <br><br>
  *
  * Precondition:<br><br>
  * <ol style="list-style-type: lower-alpha">
  * <li> The Set Invariant is satisfied. </li>
  * <li> A value e with type E has been given as input. </li>
  * </ol>
  * Postcondition:<br><br>
  * <ol style="list-style-type: lower-alpha">
  * <li> The Set Invariant is satisfied. </li>
  * <li> This set has not been changed. </li>
  * <li> If the given element e belongs to this set then
  *      &ldquo;true&rdquo; is returned; &ldquo;false&rdquo;
  *      is returned otherwise. </li>
  * </ol>
  *
  */
  
  public boolean contains (E e);
  
  /**
  *
  * Adds an input element to this set, throwing an
  * ElementFoundException if this element already belongs
  * to it.<br><br>
  *
  * @param e the element to be added to this set
  * @throws ElementFoundException if this element is already in the set
  * <br><br>
  *
  * Precondition:<br><br>
  * <ol style="list-style-type: lower-alpha">
  * <li> The Set Invariant is satisfied. </li>
  * <li> A value e with type E has been given as input. </li>
  * </ol>
  * Postcondition:<br><br>
  * <ol style="list-style-type: lower-alpha">
  * <li> The Set Invariant is satisfied. </li>
  * <li> If the input element e was not already included in
  *      this set then e has been added to it, and no other
  *      changes have been made. An ElementFoundException is
  *      thrown, otherwise, and this set has not been changed. </li>
  * </ol>
  *
  */
  
  public void include (E e) throws ElementFoundException;
  
  /**
  *
  * Removes an input element to this set, throwing a
  * NoSuchElementException if this element does not belong to it.
  * <br><br>
  *
  * @param e the element to be removed from this set
  * @throws NoSuchElementException if this element was not in this set
  * <br><br>
  *
  * Precondition:<br><br>
  * <ol style="list-style-type: lower-alpha">
  * <li> The Set Invariant is satisfied. </li>
  * <li> A value e with type E has been given as input. </li>
  * </ol>
  * Postcondition:<br><br>
  * <ol style="list-style-type: lower-alpha">
  * <li> The Set Invariant is satisfied. </li>
  * <li> If the input element e was already included in this set
  *      then this element is removed from it. A
  *      NoSuchElementException is thrown otherwise, and this set
  *      is not changed. </li>
  * </ol>
  * 
  */
  
  public void remove (E e) throws NoSuchElementException;

}

