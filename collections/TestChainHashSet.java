package cpsc331.collections;

import cpsc331.collections.SimpleChainHashFunction;
import cpsc331.collections.ChainHashSet;
import cpsc331.collections.ElementFoundException;
import java.util.NoSuchElementException;

// Provides simple tests of ChainHashMap

public class TestChainHashSet {

  // Data Fields
  
  private static SimpleChainHashFunction<Integer> H;
  private static ChainHashSet<Integer> T;
  private static int tableSize;

  public static void main (String[] args) {
  
    makeTable(args);
    testContains(5, false);
    testInclude(5, true);
    testInclude(3, true);
    testContains(3, true);
    testInclude(3, false);
    testContains(3, true);
    testInclude(7, true);
    testContains(7, true);
    testContains(4, false);
    testInclude(1, true);
    testInclude(4, true);
    testInclude(6, true);
    testInclude(9, true);
    testInclude(2, true);
    testInclude(8, true);
    
    System.out.println("Checking values in order.");
    testContains(1, true);
    testContains(2, true);
    testContains(3, true);
    testContains(4, true);
    testContains(5, true);
    testContains(6, true);
    testContains(7, true);
    testContains(8, true);
    testContains(9, true);
    testContains(10, false);
    
    testRemove(1, true);
    testRemove(5, true);
    testRemove(6, true);
    testRemove(9, true);
    
    System.out.println("Checking values in order.");
    testContains(1, false);
    testContains(2, true);
    testContains(3, true);
    testContains(4, true);
    testContains(5, false);
    testContains(6, false);
    testContains(7, true);
    testContains(8, true);
    testContains(9, false);
    testContains(10, false);
    
    testInclude(5, true);
    
    System.out.println("Checking values in order.");
    testContains(1, false);
    testContains(2, true);
    testContains(3, true);
    testContains(4, true);
    testContains(5, true);
    testContains(6, false);
    testContains(7, true);
    testContains(8, true);
    testContains(9, false);
    testContains(10, false);
    
    testRemove(6, false);
    
    testRemove(2, true);
    testRemove(3, true);
    testRemove(4, true);
    testRemove(5, true);
    testRemove(7, true);
    
    System.out.println("Checking values in order.");
    testContains(1, false);
    testContains(2, false);
    testContains(3, false);
    testContains(4, false);
    testContains(5, false);
    testContains(6, false);
    testContains(7, false);
    testContains(8, true);
    testContains(9, false);
    testContains(10, false);
    
    testRemove(8, true);
    testInclude(2, true);
    
    System.out.println("Checking values in order.");
    testContains(1, false);
    testContains(2, true);
    testContains(3, false);
    testContains(4, false);
    testContains(5, false);
    testContains(6, false);
    testContains(7, false);
    testContains(8, false);
    testContains(9, false);
    testContains(10, false);
    
    testRemove(2, true);
    
    System.out.println("Checking values in order.");
    testContains(1, false);
    testContains(2, false);
    testContains(3, false);
    testContains(4, false);
    testContains(5, false);
    testContains(6, false);
    testContains(7, false);
    testContains(8, false);
    testContains(9, false);
    testContains(10, false);
  
  }
  
  // Constructs the desired hash table
  
  private static void makeTable(String[] args) {
  
    System.out.println();
    System.out.println("Trying to create a hash table with non-positive size.");
    try {
      ChainHashSet<Integer> S = new ChainHashSet<Integer>(-1);
      System.out.println("An IllegalArgumentException was, incorrectly, not thrown.");
    } catch (IllegalArgumentException ex) {
      System.out.println("An IllegalArgumentException was correctly thrown.");
    };
    System.out.println("");
    
    tableSize = 1;
    if (args.length == 1) {
      try {
        int inputSize = Integer.parseInt(args[0]);
        tableSize = Math.max(tableSize, inputSize);
      } catch (NumberFormatException ex) {
        System.out.println("Invalid input.");
      };
    } else {
      System.out.print("Incorrect number of inputs.");
    };
  
    System.out.println("Creating hash table with size " + tableSize + ".");
    T = new ChainHashSet<Integer>(tableSize);
    H = T.hashFunction();
    System.out.println("");
    
  }
  
  private static void testContains(Integer k, boolean success) {
  
    System.out.print("Checking whether ");
    System.out.println(k.toString() + " belongs to this set.");
    System.out.println("Hash Value: " + H.hashValue(k));
    boolean result = T.contains(k);
    if (success && result) {
      System.out.print("defined correctly reported that ");
      System.out.println(k.toString() + " belongs to this set.");
    } else if (success) {
      System.out.print("defined incorrectly reported that ");
      System.out.println(k.toString() + " belongs to this set.");
    } else if (!result) {
      System.out.print("defined correctly reported that ");
      System.out.println(k.toString() + " does not belong to this set.");
    } else {
      System.out.print("defined incorrectly reported that ");
      System.out.println(k.toString() + " belongs to this set.");
    };
    System.out.println("");
  
  }
  
  
  private static void testInclude(Integer k, boolean success) {
  
    System.out.print("Adding " + k.toString());
    System.out.println(" to be this set.");
    System.out.println("Hash Value: " + H.hashValue(k));
    try {
      T.include(k);
      if (success) {
        System.out.print("include correctly added " + k.toString());
        System.out.println(" to this set.");
      } else {
        System.out.print("include failed to report that " + k.toString());
        System.out.println(" already belongs to this set.");
      };
    }
    catch (ElementFoundException e) {
      if (!success) {
        System.out.print("include correctly reported that " + k.toString());
        System.out.println(" already belongs to this set.");
      } else {
        System.out.print("include incorrectly reported that " + k.toString());
        System.out.println(" already belongs to this set.");
      };
    };
    System.out.println();
  
  }
  
  public static void testRemove(Integer k, boolean success) {
  
    System.out.print("Attempting to remove the element ");
    System.out.println(k.toString() + " from this set.");
    System.out.println("Hash Value: " + H.hashValue(k));
    try {
      T.remove(k);
      if (success) {
        System.out.print("remove correctly reported that " + k.toString());
        System.out.println(" was removed from this set.");
      } else {
        System.out.print("remove incorrectly failed to throw a ");
        System.out.println("NoSuchElementException.");
      };
    } catch (NoSuchElementException e) {
      if (!success) {
        System.out.println("remove correctly threw a NoSuchElementException.");
      } else {
        System.out.println("remove incorrectly threw a NoSuchElementException.");
      };
    };
    System.out.println("");
  
  }

}