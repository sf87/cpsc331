package cpsc331.collections;

/**
*
* Provides an interface for a hash function that can be used by a hash
* table with chaining.
*
*/

public interface ChainHashFunction<E> {

  /**
  *
  * Reports the hash value for a given value
  *
  * @param e the element whose has value is to be provided
  * @return the hash value for this element
  * <br><br>
  *
  * Precondition:<br><br>
  * <ol style="list-style-type: lower-alpha">
  * <li> An element e  of type&nbsp;E has been given as input. </li>
  * </ol>
  * Postcondition: <br><br>
  * <ol style="list-style-type: lower-alpha">
  * <li> The has value of&nbsp;e &mdash; an integer between zero and one less
  *      than the size of the supported has table &mdash; is returned as output.
  * </li>
  * </ol>
  * 
  */
  
  public int hashValue (E e);

}