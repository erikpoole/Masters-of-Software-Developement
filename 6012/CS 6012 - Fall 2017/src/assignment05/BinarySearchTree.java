package assignment05;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {

 private Node<T> root;
 private int size;

/**
 * constructor - produces empty tree 
 */
 public BinarySearchTree() {
  root = null;
  size = 0;
 }

 /**
  * Ensures that this set contains the specified item.
  * 
  * @param item - the item whose presence is ensured in this set
  * @return true if this set changed as a result of this method call (that is, if the input item was
  *         actually inserted); otherwise, returns false
  * @throws NullPointerException if the item is null
  */
 @Override
 public boolean add(T item) {
  if (item == null) {
   throw new NullPointerException();
  }
  if (root == null) {
   root = new Node<T>(item);
   size++;
   return true;
  }

  return addRecursion(item, root);
 }

 /**
  * recursive inner class associated with the "add" method
  * navigates through BinarySearchTree and places new element correctly
  * 
  * @param inputElement element to be added
  * @param inputNode current working node at specific level of the recursion - starts with "root"
  * @returns true if element was added (not previously present in the tree)
  */
 private boolean addRecursion(T inputElement, Node<T> inputNode) {
  if (inputElement.compareTo(inputNode.getElement()) < 0) {
   if (inputNode.getLeft() == null) {
    inputNode.setLeft(new Node<T>(inputElement));
    size++;
    return true;
   } else {
    return addRecursion(inputElement, inputNode.getLeft());
   }

  } else if (inputElement.compareTo(inputNode.getElement()) > 0) {
   if (inputNode.getRight() == null) {
    inputNode.setRight(new Node<T>(inputElement));
    size++;
    return true;
   } else {
    return addRecursion(inputElement, inputNode.getRight());
   }
  }

  return false;
 }

 /**
  * ensures that this set contains all items in the specified collection.
  * utilizes .add() method
  * 
  * @param items - the collection of items whose presence is ensured in this set
  * @return true if this set changed as a result of this method call (that is, if any item in the
  *         input collection was actually inserted); otherwise, returns false
  * @throws NullPointerException if any of the items is null
  */
 @Override
 public boolean addAll(Collection<? extends T> items) {
  boolean itemAdded = false;
  for (T item : items) {
   if (add(item) == true) {
    itemAdded = true;
   }
  }
  return itemAdded;
 }

 /**
  * Removes all items from this set. The set will be empty after this method call.
  */
 @Override
 public void clear() {
  root = null;
  size = 0;
 }

 /**
  * Determines if there is an item in this set that is equal to the specified item.
  * 
  * @param item - the item sought in this set
  * @return true if there is an item in this set that is equal to the input item; otherwise, returns
  *         false
  * @throws NullPointerException if the item is null
  */
 @Override
 public boolean contains(T item) {
  if (item == null) {
   throw new NullPointerException();
  }
  return containsRecursion(item, root);
 }

 /**
  * recursive inner class associated with the "contains" method
  * navigates through BinarySearchTree and determines if element is present
  * 
  * @param inputElement element to be found
  * @param inputNode node at this point in the recursion, starts with "root"
  * @return true if element is present, false otherwise
  */
 private boolean containsRecursion(T inputElement, Node<T> inputNode) {
  if (inputNode == null) {
   return false;
  }

  if (inputElement.compareTo(inputNode.getElement()) < 0) {
   return containsRecursion(inputElement, inputNode.getLeft());
  } else if (inputElement.compareTo(inputNode.getElement()) > 0) {
   return containsRecursion(inputElement, inputNode.getRight());
  }

  return true;
 }


 /**
  * Determines if for each item in the specified collection, there is an item in this set that is
  * equal to it.
  * utilizes .contains() method
  * 
  * @param items - the collection of items sought in this set
  * @return true if for each item in the specified collection, there is an item in this set that is
  *         equal to it; otherwise, returns false
  * @throws NullPointerException if any of the items is null
  */
 @Override
 public boolean containsAll(Collection<? extends T> items) {
  boolean itemsFound = true;
  for (T item : items) {
   if (contains(item) == false) {
    itemsFound = false;
   }
  }
  return itemsFound;
 }

 /**
  * Returns the first (i.e., smallest) item in this set.
  * 
  * @throws NoSuchElementException if the set is empty
  */
 @Override
 public T first() throws NoSuchElementException {
  if (root == null) {
   throw new NoSuchElementException();
  }
  Node<T> workingNode = root;
  while (workingNode.getLeft() != null) {
   workingNode = workingNode.getLeft();
  }
  return workingNode.getElement();
 }

 /**
  * Returns true if this set contains no items.
  */
 @Override
 public boolean isEmpty() {
  if (size == 0) {
   return true;
  }
  return false;
 }

 /**
  * Returns the last (i.e., largest) item in this set.
  * 
  * @throws NoSuchElementException if the set is empty
  */
 @Override
 public T last() throws NoSuchElementException {
  if (root == null) {
   throw new NoSuchElementException();
  }
  Node<T> workingNode = root;
  while (workingNode.getRight() != null) {
   workingNode = workingNode.getRight();
  }
  return workingNode.getElement();
 }

 /**
  * Ensures that this set does not contain the specified item.
  * 
  * @param item - the item whose absence is ensured in this set
  * @return true if this set changed as a result of this method call (that is, if the input item was
  *         actually removed); otherwise, returns false
  * @throws NullPointerException if the item is null
  */
 @Override
 public boolean remove(T item) {
  if (item == null) {
   throw new NullPointerException();
  }
  return removeRecursion(item, root, root);
 }

 /**
  * recursive inner method associated with .remove() method
  * navigates through BinarySearchTree and removes input element correctly
  * 
  * @param inputElement element to be removed
  * @param childNode current node at level in recursion
  * @param parentNode previous nede at level in recursion
  * @return true if element was found and removed, else return false
  */
 private boolean removeRecursion(T inputElement, Node<T> childNode, Node<T> parentNode ) {
  if (childNode == null) {
   return false;
  }

  //searches to find element
  if (inputElement.compareTo(childNode.getElement()) < 0) {
   return removeRecursion(inputElement, childNode.getLeft(), childNode);
  } else if (inputElement.compareTo(childNode.getElement()) > 0) {
   return removeRecursion(inputElement, childNode.getRight(), childNode);
  } else {
   
   //handles no children removal
   if (childNode.getLeft() == null && childNode.getRight() == null) {
    if (parentNode.getLeft() == childNode) {
     parentNode.setLeft(null);
    } else {
     parentNode.setRight(null);
    }
    
    //handles two children removal
   } else if (childNode.getLeft() != null && childNode.getRight() != null) {
    Node<T> workingParent = childNode;
    Node<T> workingChild = childNode.getRight();
    while (workingChild.getLeft() != null) {
     workingParent = workingChild;
     workingChild = workingChild.getLeft();
    }
    
    childNode.setElement(workingChild.getElement());
    if (workingParent.getRight() == null) {
    workingParent.setLeft(null);
    } else {
     if (workingParent.getRight() == workingChild) {
      workingParent.setRight(workingChild.getRight());
     }
     else {
      workingParent.setLeft(workingChild.getRight());
     }
    }
    
    //handles one child removal
   } else {
    if (parentNode.getLeft() == childNode) {
     if (childNode.getLeft() != null) {
      parentNode.setLeft(childNode.getLeft());
     } else if (childNode.getRight() != null) {
      parentNode.setLeft(childNode.getRight());
     }
    } else {     
     if (childNode.getLeft() != null) {
     parentNode.setRight(childNode.getLeft());
    } else if (childNode.getRight() != null) {
     parentNode.setRight(childNode.getRight());
    }
    }
   }
  }
  
  size--;
  return true;
 }

 /**
  * Ensures that this set does not contain any of the items in the specified collection.
  * Utilizes .remove() method
  * Commented code will allow for easy viewing of tree after each element is removed via Graphviz
  * 
  * @param items - the collection of items whose absence is ensured in this set
  * @return true if this set changed as a result of this method call (that is, if any item in the
  *         input collection was actually removed); otherwise, returns false
  * @throws NullPointerException if any of the items is null
  */
 @Override
 public boolean removeAll(Collection<? extends T> items) {
  boolean itemRemoved = false;
//  int counter = 0;
  for (T item : items) {
//   counter++;
//   this.writeDot(counter + "expressionTree.dot");
   if (remove(item) == true) {
    itemRemoved = true;
   }
  }
  return itemRemoved;
 }

 /**
  * Returns the number of items in this set.
  */
 @Override
 public int size() {
  return size;
 }

 /**
  * Returns an ArrayList containing all of the items in this set, in sorted order.
  */
 @Override
 public ArrayList<T> toArrayList() {
  ArrayList<T> output = new ArrayList<>();
  toArrayListRecursion(root, output);
  
  return output;
 }
 
 /**
  * recursive inner method associated with "toArrayList" 
  * Navigates through BinarySearchTree and returns elements in order via in-order depth-first traversal
  * 
  * @param inputNode node at this level of the recursion, starts with "root"
  * @param inputList list to add elements to
  * @return inputList with added element
  */
 private ArrayList<T> toArrayListRecursion(Node<T> inputNode, ArrayList<T> inputList) {
  if (inputNode == null) {
   return inputList;
  }
  
  toArrayListRecursion(inputNode.getLeft(), inputList);
  inputList.add(inputNode.getElement());
  toArrayListRecursion(inputNode.getRight(), inputList);
  
  return inputList;
 }

 /**
  * Driver method
  * generates a .dot file representing this tree
  * uses each node's hashcode to uniquely identify it
  * @param filename output filename
  */
 public void writeDot(String filename) {
   try {
     PrintWriter output = new PrintWriter(new FileWriter(filename));
     output.println("graph g {");
     if (root != null)
       output.println(root.hashCode() + "[label=\"" + root.getElement() + "\"]");
     writeDotRecursive(root, output);
     output.println("}");
     output.close();
   } catch (Exception e) {
     e.printStackTrace();
   }
 }
  
 /**
  * Recursively traverse the tree, outputting each node to the .dot file
  * 
  * @param n node at this point in the recursion
  * @param output printwriter with output information
  * @throws Exception
  */
 private void writeDotRecursive(Node<T> n, PrintWriter output) throws Exception {
   if (n == null)
     return;
   if (n.getLeft() != null) {
     output.println(n.getLeft().hashCode() + "[label=\"" + n.getLeft().getElement() + "\"]");
     output.println(n.hashCode() + " -- " + n.getLeft().hashCode());
   }
   if (n.getRight() != null) {
     output.println(n.getRight().hashCode() + "[label=\"" + n.getRight().getElement() + "\"]");
     output.println(n.hashCode() + " -- " + n.getRight().hashCode());
   }

   writeDotRecursive(n.getLeft(), output);
   writeDotRecursive(n.getRight(), output);
 }
 

}
