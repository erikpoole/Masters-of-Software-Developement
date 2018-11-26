package assignment05;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {

 private Node<T> root;
 private int size;

 // create empty
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
  * My method
  * 
  * @param inputElement
  * @param inputNode
  * @return
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
  * Ensures that this set contains all items in the specified collection.
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

 private boolean removeRecursion(T inputElement, Node<T> childNode, Node<T> parentNode ) {
  if (childNode == null) {
   return false;
  }

  //searches to find element - Working
  if (inputElement.compareTo(childNode.getElement()) < 0) {
   return removeRecursion(inputElement, childNode.getLeft(), childNode);
  } else if (inputElement.compareTo(childNode.getElement()) > 0) {
   return removeRecursion(inputElement, childNode.getRight(), childNode);
  } else {
   
   //handles no children removal - Working
   if (childNode.getLeft() == null && childNode.getRight() == null) {
    if (parentNode.getLeft() == childNode) {
     parentNode.setLeft(null);
    } else {
     parentNode.setRight(null);
    }
    
    //handles two children removal - Working (?)
   } else if (childNode.getLeft() != null && childNode.getRight() != null) {
    Node<T> workingParent = childNode;
    Node<T> workingChild = childNode.getRight();
    while (workingChild.getLeft() != null) {
     workingParent = workingChild;
     workingChild = workingChild.getLeft();
    }

    childNode.setElement(workingChild.getElement());
    if (workingChild.getRight() == null) {
    workingParent.setLeft(null);
    } else {
     //setRight/setLeft is problem (Believed to be working)
     if (workingParent.getRight() == workingChild) {
      workingParent.setRight(workingChild.getRight());
     }
     else {
      workingParent.setLeft(workingChild.getRight());
     }
    }
    
    //handles one child removal - Working
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
  * 
  * @param items - the collection of items whose absence is ensured in this set
  * @return true if this set changed as a result of this method call (that is, if any item in the
  *         input collection was actually removed); otherwise, returns false
  * @throws NullPointerException if any of the items is null
  */
 @Override
 public boolean removeAll(Collection<? extends T> items) {
  boolean itemRemoved = false;
  for (T item : items) {
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
 
 private ArrayList<T> toArrayListRecursion(Node<T> inputNode, ArrayList<T> inputList) {
  if (inputNode == null) {
   return inputList;
  }
  
  toArrayListRecursion(inputNode.getLeft(), inputList);
  inputList.add(inputNode.getElement());
  toArrayListRecursion(inputNode.getRight(), inputList);
  
  return inputList;
 }

}
