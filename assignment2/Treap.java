

package cpsc331.assignment2;
import java.util.NoSuchElementException;
import cpsc331.collections.ElementFoundException;
import cpsc331.assignment2.STreap;

/**
 *
 * Provides a Treap Storing Values from an Ordered Type E Using Priorities
 * from an Ordered Type&nbsp;P
 *
 */


// Treap Invariant: A binary tree T is represented tha satisfies the
// Treap Properties given below.
//
// a) Every node in T stores an element e of E and a priority p in P.
// b) For each node x in T, each element (in E) stored in a node in the left
//    subtree of the tree with root x is less than the element stored at x,
//    and every element stored in a node in the right subtree of the subtree
//    with root x is greater than the element stored at x.
// c) For each node x in T, he priority stored at x is greater than or equal
//    to each priority stored at a child of x.
// d) Every subtree of T is also a treap, that is, every subtree satisifes
//    the above properties (a)-(c).

public class Treap<E extends Comparable<E>, P extends Comparable<P>> {

    // Provides a node in this Treap
  
    class TreapNode {
  
	// Data Fields
    
	E element;          // Element of E stored at this TreapNode
	P priority;         // Priority of element stored at this TreapNode
    
	TreapNode left;     // Left child of this TreapNode
	TreapNode right;    // Right child of this TreapNode
	TreapNode parent;   // Parent of this TreapNode;
    
    
	// Constructor; constructs a TreepNode storing a given element e of E
	// with a given priority p of P whose left child, right child and parent
	// are null
    
	TreapNode(E e, P p) {
    
	    element = e;
	    priority = p;
	    left = null;
	    right = null;
	    parent = null;
    
	}
    
	// Returns the element stored at this node
    
	E element() {
    
	    return element;
    
	}
    
	// Returns the priority of the data at this node
    
	P priority() {
    
	    return priority;
    
	}
    
	// Returns the left child of this node
    
	TreapNode left() {
    
	    return left;
    
	}
    
	// Returns the right child of this node
    
	TreapNode right() {
    
	    return right;
    
	}
    
	// Returns the parent of this node
    
	TreapNode parent() {
    
	    return parent;
    
	}

    }

    // Data Fields
  
    private TreapNode root;


    /**
     *
     * Constructs an empty Treap<br><br>
     *
     * Precondition: None<br>
     * Postcondition: An empty Treap (satisfying the above Treap Invariant)
     *                has been created.
     *
     */
  
    public Treap() {
  
	root = null;
    
    }

    // Used for testing: produces a Treap from a STreap
  
    public Treap(STreap<E, P> s) {
  
	root = makeSubTreap(s.root());
  
    }

    // Used for Testing: Converts a subtree into a STreap with a given
    // STreapNode as root into a corresponding tree with TreapNodes as
    // nodes
  
    TreapNode makeSubTreap(STreap<E, P>.STreapNode s) {
  
	if (s != null) {
    
	    TreapNode t = new TreapNode(s.element(), s.priority());
      
	    t.left = makeSubTreap(s.left());
	    if (t.left != null) {
		(t.left).parent = t;
	    };
      
	    t.right = makeSubTreap(s.right());
	    if (t.right != null) {
		(t.right).parent = t;
	    };

	    System.out.println();
	    return t;
    
	} else {
    
	    return null;
    
	}
  
    }
    
    // *******************************************************************
    //
    // Searches
    //
    // *******************************************************************

    public void printTreap (TreapNode x){
	System.out.println(" ");
	if(x == null){
	    System.out.println("NULL");
	}else{
	    if(x == root){
		System.out.print("ROOT ");
	    }
	    System.out.print("(" + x.element() + ", " + x.priority() + ")");
	    
	    if(x.parent != null) System.out.print(" PARENT " + "(" + x.parent.element() + ", " + x.parent.priority() + ")");
	    else System.out.print(" PARENT NULL ");

	    if(x.left() != null) System.out.print(" LEFT  " + "(" + x.left.element() + ", " + x.left.priority() + ")");
	    else System.out.print(" LEFT NULL ");

	    if(x.right() != null) System.out.print(" RIGHT  " + "(" + x.right.element() + ", " + x.right.priority() + ")");
	    else System.out.print(" RIGHT NULL ");
	    
	    

	    if(x.left !=null){
		printTreap(x.left);
		
	    }
	    if (x.right != null){
		printTreap(x.right);
	    }

	    
	}
    }
    /**
     * Returns a TreapNode storing a given key<br>
     *
     * @param key the element to be searched for
     * @return the TreapNode in this Treap storing the input key
     * @throws NoSuchElementException if the key is not stored in this treap
     *
     */
  
    // Precondition:
    // a) This Treap satisfies the above Treap properties.
    // b) A non-null key with type E is given as input.
    // 
    // Postcondition:
    // a) If the key is stored in this Treap then the node storing it is
    //    returned as output. A NoSuchElementException is thrown otberwise.
    // b) This Treap has not been changed, so it still satisfies the
    //    Treap properties.
  
    public TreapNode search(E key) throws NoSuchElementException {
  
	if (root == null) {
    
	    throw new NoSuchElementException(key.toString() + " not found.");
    
	} else {

    
	    return get(key, root);
    
	}
  
    }
  
    //
    // Searches for a given key in the subtree with a given node as root
    //
    // Precondition:
    // a) This Treap satisfies the above Treap properties.
    // b) key is a non-null input with type E.
    // c) x is a non-null TreapNode in this Treap, that is also given
    //    as input.
    //
    // Postcondition:
    // a) If the key is stored in the subtree with root x, then the node
    //    storing the key is returned as output. A NoSuchElementExeption
    //    is thrown otherwise.
    // b) This Treap has not been changed, so it still satisfies the
    //    Treap properties.
  
    private TreapNode get(E key, TreapNode x) throws NoSuchElementException {
       
       
	if(key.compareTo(x.element()) == 1){

	    if (x.right() != null)  return get(key, x.right());
	    else throw new NoSuchElementException("This line should be replaced.");


	}else if(key.compareTo(x.element()) == -1){
	    if (x.left() != null) return get(key, x.left()) ;
	    else throw new NoSuchElementException("This line should be replaced.");

	}else{
	    return x;
	}
                       
       
    }
    
	
  
    private TreapNode copyNode(TreapNode x){
	TreapNode newNode = null;
	if(x != null){
	     newNode =  new TreapNode(x.element(), x.priority());
	     
	     newNode.parent = x.parent;
	     //newNode.right = x.right;
	     //newNode.left = x.left;
	}
	return newNode;
    
    }

    private void  printNode(TreapNode x, String name){
	System.out.println(name+" = (" + x.element() + ", " +x.priority()+")");
	
	if(x != null)System.out.println(name+"= "+x.toString());
	else System.out.println(name + "  is null");

	if(x.parent != null)System.out.println(name + " .parent = "+x.parent().toString());
	else System.out.println(name + " .parent is null");

	if(x.left != null)System.out.println(name + " .left = "+x.left().toString());
	else System.out.println(name + " .left is null");

	if(x.right != null)System.out.println(name + " .right = "+x.right().toString());
	else System.out.println(name + " .right is null");
    }
      
    // *******************************************************************
    //
    // Utility Methods Needed for Insertions and Deletions
    //
    // *******************************************************************
  
    //
    // Implements a Left Rotation at a Node
    //
    // Precondition:
    // a) T is a binary tree whose nodes store non-null ordered pairs of
    //    elements of E and priorities in P.
    // b) A non-null node z in T, which has a non-null right child, is given
    //    as input.
    //
    // Postcondition:
    // a) A left rotation is carried out at z - and T is otherwise unchanged.

   
    
    private void leftRotate (TreapNode z) {
	TreapNode zCopy = copyNode(z);
	TreapNode xCopy = copyNode(z.right);
	TreapNode t1 = copyNode(z.left);
	TreapNode t2 = copyNode(z.right.left);
	TreapNode t3 = copyNode(z.right.right);

		//Some thing prevents z = xCopy from working IDK
	printNode(z, "z");
	printNode(zCopy, "z.copy");
	z.element = xCopy.element();
	z.priority = xCopy.priority();


	
	
	z.left = zCopy;
	z.left.parent = z;

	z.left.left = t1;
	if(t1 != null) z.left.left.parent = z.left;

	z.left.right = t2;
	if(t2 != null) z.left.right.parent = z.left;

	z.right = t3;
	if(t3 != null) z.right.parent = z;

	

    }

  
    //
    // Implements a Right Rotation at a Node
    //
    // Precondition:
    // a) T is a binary tree whose nodes store non-null ordered pairs of
    //    elements of E and priorities in P.
    // b) A non-null node z in T, which has a non-null left child, is given
    //    as input.
    //
    // Postcondition;
    // a) A right rotation is carried out at z - and T is otherwise unchanged.
  
    private void rightRotate (TreapNode z) {
  
	TreapNode zCopy = copyNode(z);
	
	TreapNode xCopy = copyNode(z.left);

	TreapNode t1 = copyNode(z.left.left);
	TreapNode t2 = copyNode(z.left.right);
	TreapNode t3 = copyNode(z.right);

	printNode(z, "z");
	printNode(zCopy, "zCopy");
	
	//Some thing prevents z = xCopy from working IDK
	z.element = xCopy.element();
	z.priority = xCopy.priority();


	
	
	z.right = zCopy;
	z.right.parent = z;

	z.left = t1;
	if(t1 != null) z.left.parent = z;
	z.right.left = t2;
	if(t2 != null) z.right.left.parent = z.right;

	z.right.right = t3;
	if(t3 != null) z.right.right.parent = z.right;
  
    }
  
  
    // *******************************************************************
    //
    // Insertions
    //
    // *******************************************************************
  
    /**
     *
     * Inserts a given element into a treap with a given priority<br>
     * @param key the element to be inserted
     * @param priority the priority to be used
     * @throws ElementFoundException if the element is already stored
     *         in this treap
     *
     */
  
    // Precondition:
    // a) This Treap satisfies the above Treap properties.
    // b) key is a non-null input with type E and priority is a
    //    non-null input with type p
    //
    // Postcondition:
    // a) If the key does not already belong to the subset of E stored
    //    at the nodes in this Treap then it is added to this set - with
    //    the input priority used as the priority for the node storing8 1

    //    this key. No other changes have been made to the subset of E
    //    stored at nodes in this Treap, and the priorities of the
    //    elements of E already in this subset have not been changed.
    //    An ElementFound Exception is thrown and this Treap is not
    //    changed, if the input key already belongs to this subset
    //    of E.
    // b) This Treap satisfies the Treap properties given above.
  
    public void insert (E key, P priority) throws ElementFoundException {
  
	if (root == null) {
	    root = new TreapNode(key, priority);
    
	} else {
    
	    TreapNode x = add(key, priority, root);
	    restoreAfterInsertion(x);
    
	}
  
    }

  
    // Inserts a given element, with a given priority, into the subtree
    // of this Treap with a given node as root
    //
    // Precondition:
    // a) This Treap satisfies the above Treap properties.
    // b) key is a non-null input with type E and priority is a non-null
    //    input with type P.
    // c) x is a non-null node in this Treap such that, if the input key
    //    was stored at a node in this Treap then it would necessarily be
    //    stored at a node in the subtree of this Treap with root x.
    // 
    // Postcondition:
    // a) If the key does not already belong to the subset of E stored
    //    at the nodes in this tree then it is added to this set - with
    //    the input priority used as the priority for the node storing
    //    this key. No other changes have been made to the subset of E
    //    stored at nodes in this tree, and the priorities of the
    //    elements of E already in this subset have not been changed.
    //    The new node, storing the input key and priority, is returned
    //    as output in this case. On the other hand, an
    //    ElementfoundException is thrown and this tree is not changed,
    //    if the input key already belongs to this subset of E.
    // b) This tree satisfies the Treap prpoperties given above, except
    //    that if they key was not already stored in this Treap, then
    //    the priority of the new leaf, storing the key, and this node
    //    is not the root, then the priority of this new leaf may be
    //    greater than the priority of its parent.
  
    private TreapNode add (E key, P priority, TreapNode x) throws ElementFoundException {

	TreapNode next = new TreapNode(key, priority);
	int flag = 1;

	while(flag == 1){

	    //x's element < key
	    if(x.element().compareTo(key) == -1){
		if(x.right() == null){
		    x.right = next;
		    x.right.parent = x;
		    flag = 0;
		    System.out.println("( "+ x.right.element + ", "+ x.right.priority + ")" +" inserted as right child of " + "( "+ x.element + ", "+ x.priority + ")" );
		}

		//x.right is not null
		// Continue seaching down the tree
		else{
		    x = x.right();
		}
		
	    }
	    
	    // x's element > key
	    else if(x.element().compareTo(key) == 1){
		if(x.left == null){
		    x.left = next;
		    x.left.parent = x;
		    flag = 0;
		    System.out.println("( "+ x.left.element + ", "+ x.left.priority + ")" +" inserted as left child of " + "( "+ x.element + ", "+ x.priority + ")" );
		}

		else{
		    x = x.left();
		}
	    }
	    // x's element == key
	    else{
		throw new ElementFoundException("This lins should be replaced.");
	    }	    
	}
	return next;
    }
  
    // Restores Treap properties after insertion of a new node
    //
    // Precondition:
    // a) This tree satisfies Treap properties (a) and (b).
    // b) A non-null node x of this tree is given as input.
    // b) For every node y in this tree except for x, if y is
    //    not the root of this tree (so that y has a parent)
    //    then the priority stored at y is less than or equal
    //    to the priority stored at the parent of y. Furthermore,
    //    if the parent of y also has a parent (the "grandparent"
    //    of y) then the priority stored at y is also less than
    //    or equal to the priority stored at the grandparent of y.
    //
    // Postcondition:
    // a) The subset of elements of E stored at nodes of this tree
    //    has not been changed.
    // b) The priority associated with each element of E that is
    //    stored in this tree has not been changed. That is, if an
    //    element e of E and a priority p in P were stored were stored
    //    in the same node of this tree before this computation began,
    //    then e and p are also both stored in the same node of this
    //    tree after the computation ends.
    // c) This tree is a Treap, that is, it satisfies all of the
    //    Treap properties given above.
  
    private void restoreAfterInsertion (TreapNode x) {
	printTreap(root);
	System.out.println("");
	System.out.println("x = (" + x.element() + ", " +x.priority()+")");
	System.out.println("x.parent" + "(" + x.parent.element() + ", " +x.parent.priority()+")"); 
	//if x's parents priority < x's priority
	if(x.parent().priority().compareTo(x.priority()) == -1){
	    if(x.parent() == root){
		//System.out.println("x.parent = root");
		//if parent element is greater than x's element
		//if true x is on the left
		if(x.parent().element().compareTo(x.element()) == 1){
	
		      System.out.println("ROOT Right rotate at " + "(" + x.parent.element() + ", " +x.parent.priority()+")"); 
		      rightRotate(x.parent);

		}
		else {

		    System.out.println(" Root Left rotate at " + "(" + x.parent.element() + ", " +x.parent.priority()+")"); 
		    leftRotate(x.parent);
		}
		
		root = x;
		root.parent = null;

	    }else{
	    
	    

		//if parent element is greater than x's element
		//if true x is on the left
		if(x.parent().element().compareTo(x.element()) == 1){
		    System.out.println("Right rotate at " + "(" + x.parent.element() + ", " +x.parent.priority()+")"); 
		    rightRotate(x.parent);
	
		    
		}
		else {
		    System.out.println("left rotate at " + "(" + x.parent.element() + ", " +x.parent.priority()+")"); 
		    leftRotate(x.parent);
	
		
	
		}
		
		
		restoreAfterInsertion(x.parent());
	    }
	}
    }
    // *******************************************************************
    //
    // Deletions
    //
    // *******************************************************************
  
    /**
     *
     * Removes an input element from the subset of E stored at the
     * nodes of this Treap
     *
     * @param key the element of&nbsp;E to be removed
     * @throws NoSuchElementException if the key is not stored
     *
     */
  
    // Precondition:
    // a) This Treap satisfies the above Treap properties.
    // b) key is a non-nyll input with type E.<br>
    // Postcondition:
    // a) If the input key is included of the subset of E stored at
    //    the nodes of this Treap then it is removed from this
    //    subset (with this subset being otherwise unchanged); the
    //    priorities associated with other elements of E in this subset
    //    are also unchanged. A NoSuchElementException is thrown, and
    //    this Treap is not changed, if the input key does not already
    //    belong to this susbset of E.
    // b) This Treap satisfies the Treap properties given above.
  
    public void delete (E key) throws NoSuchElementException {
  
	if (root != null) {
    
	    TreapNode z = deleteFromSubtree(key, root);
	    if (z != null) {
		restoreAfterDeletion(z);
	    };
    
	} else {
    
	    throw new NoSuchElementException(key.toString() + " not found.");
    
	}
  
    }
  
    // Removes an input element from the subset of E stored in the
    // subtree of this Treap with a given node as root.
    //
    // Precondition:
    // a) This Treap satisfies the above Treap properties.
    // b) key is a non-null input with type E.
    // c) x is a non-null node in this Treap.
    // d) Either key is not an element of the subset of E
    //    stored at the nodes of this Treap, or key is stored
    //    at a node in the subtree of this Treap with x as root.
    //
    // Postcondition:
    // a) If the input key was originally stored a node in the
    //    subtree of this binary tree  with root x then
    //    - every node in this binary tree stores an element
    //      of E and element of P (called a "priority")
    //    - the key is removed from the subset of E stored at
    //      the nodes of this binary tree, and this subset is
    //      otherwise unchanged; the priorities associated with
    //      other elements of this subset are not changed.
    //    - For every non-null node y in this binary tree, every
    //      element of E stored in the left subtree of the
    //      subtree with root y is less than the element of E
    //      stored at y, and every element of E stored in the
    //      right subtree of the subtree with root y is
    //      greater than the element of E stored at y.
    //    - A (possibly null) node z in this tree is returned as
    //      output. For every non-null node y in this binary
    //      tree except for x, if y has a parent then the
    //      the priority stored at the parent of y is greater
    //      than or equal to the priority stored at y, and if
    //      y has a grandparent then the priority stored at the
    //      grandparent of y is greater than or equal to the
    //      priority stored at y.
    // b) - If the input key was not originally stored in the
    //      subtree of this binary tree with root x then
    //      a NoSuchElementException is thrown and this binary
    //      tree is not changed - so that it still satisfies
    //      the Treap properties and is, therefore, a Treap.
  
    private TreapNode deleteFromSubtree (E key, TreapNode x)
	throws NoSuchElementException {
                            
	// For You To Complete

	throw new NoSuchElementException("This line should be replaced.");
    
    }
  
    // Restores Treap properties after a node has been deleted.
    //
    // Ppstcondition:
    // a) Each node in this binary tree stores an element of E
    //    and an element of P (called a "priority").
    // b) A non-null node x in this binary tree is given as input.
    // c) For every non-null node y in this binary tree, every
    //    element of E stored in the left subtree of the subtree
    //    with root y is less than the element of E stored at y,
    //    and every element of E stored in the right subtree of
    //    the subtree with root y is greater than the element
    //    of E stored at y.
    // d) For every non-null node y in this binary tree except
    //    for x, if y has any children then the priority stored
    //    at each child of y is less than or equal to the priority
    //    stored at y, and if y has any grandchildren then the
    //    priority stored at each grandchild of y is less than or
    //    equal to the priority stored at y.
    //
    // Postcondition:
    // a) The binary tree satisfies the above Treap properties, that
    //    is, it is a Treap.
    // b) The subset of E stored at the nodes of this binary tree has
    //    not been changed.
    // c) The priority associated with each element of E, stored in
    //    this binary tree, has not been changed.
    //
    // Bound Function: Two plus the sum of the depths of the left
    //                 and right subtrees of x
  
    private void restoreAfterDeletion (TreapNode x) {
  
	// For You To Complete

    }
  
  
    // *******************************************************************
    //
    // Additional Code Needed for Testing
    //
    // *******************************************************************
  
    // Returns a reference to the root of this Treap
  
    TreapNode root() {
  
	return root;
    
    }
}
