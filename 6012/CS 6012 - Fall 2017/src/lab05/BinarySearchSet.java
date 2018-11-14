package lab05;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchSet<E extends Comparable<E>> implements SortedSet<E>, Iterable<E> {

 private E[] baseArray;
 private int size; // always one past end of accessible values e.g if five included values, size
                   // will be 5, but indices will be 0-4
 
 public Comparator<? super E> comparator;
 private Iterator<E> searchSetIterator;

 // ********************************************************************************
 // ********************************************************************************

 /*
  * Constructor used if no comparator is passed
  * Will use natural ordering via compareTo for searching
  */
 @SuppressWarnings("unchecked")
 public BinarySearchSet() {
  baseArray = (E[]) new Comparable[16];
  size = 0;
  comparator = null;
  searchSetIterator = new SearchSetIterator<>();
 }

/*
 * Constructor used if comparator is passed
 * Will use custom comparator for searching
 */
 @SuppressWarnings("unchecked")
 public BinarySearchSet(Comparator<? super E> inputComparator) {
  baseArray = (E[]) new Comparable[16];
  size = 0;
  comparator = inputComparator;
  searchSetIterator = new SearchSetIterator<>();
 }

 // ********************************************************************************
 // ********************************************************************************

 /*
  * Checks to see if an object is contained by the generic-typed baseArray
  * Utilizes a custom binary sort pattern to find the element
  * Returns true if found or false otherwise
  */
 @SuppressWarnings("unchecked")
 @Override
 public boolean contains(Object element) {
  // can't add instanceOf for a generic
  if (element.equals(null)) {
   return false;
  }
  E typedElement = (E) element;

  int low = 0;
  int high = size;
  int changeValue = size / 2;

  while (high > low) {
   int center = (low + high) / 2;
   if (changeValue / 2 == 0) {
    changeValue = 1;
   } else {
    changeValue /= 2;
   }

   if (comparator == null) {
    if (typedElement.compareTo(baseArray[center]) == 0) {
     return true;
    } else if (typedElement.compareTo(baseArray[center]) < 0) {
     high -= changeValue;
    } else {
     low += changeValue;
    }
   } else {
    if (comparator.compare(typedElement, baseArray[center]) == 0) {
     return true;
    } else if (comparator.compare(typedElement, baseArray[center]) < 0) {
     high -= changeValue;
    } else {
     low += changeValue;
    }
   }
  }
  return false;
 }
 /*
  * Loops through all elements in the passed collection
  * Checks baseArray for each of the elements using the .contains() method
  * If all elements are found return true, false otherwise
  */
 @SuppressWarnings("unchecked")
 @Override
 public boolean containsAll(Collection<?> elements) {
  for (Object element : elements) {
   if (!this.contains((E) element)) {
    return false;
   }
  }
  return true;
 }

 // ********************************************************************************
 // ********************************************************************************

 /*
  * Adds passed element to the set if the element is not already present
  * Places it in correct order using a binary search to find the correct index
  * Returns true if element is added, false if the elment was already present
  */
 @Override
 public boolean add(E element) {
  if (element.equals(null) || this.contains(element)) {
   return false;
  }
  E typedElement = (E) element;

  int low = 0;
  int high = size;
  int changeValue = size / 2;

  while (high > low) {
   int center = (low + high) / 2;
   if (changeValue / 2 == 0) {
    changeValue = 1;
   } else {
    changeValue /= 2;
   }

   if (comparator == null) {
    if (typedElement.compareTo(baseArray[center]) < 0) {
     high -= changeValue;
    } else {
     low += changeValue;
    }
   } else {
    if (comparator.compare(typedElement, baseArray[center]) < 0) {
     high -= changeValue;
    } else {
     low += changeValue;
    }
   }
  }

  if (size >= baseArray.length) {
   doubleSize();
  }

  for (int i = size; i > low; i--) {
   baseArray[i] = baseArray[i - 1];
  }
  baseArray[low] = typedElement;
  size++;
  return true;
 }

 /*
  * Loops through all elements in the passed collection
  * Will add the element according to the rules in the .add() method
  * If at least one element is added will return true, otherwise false.
  */
 @SuppressWarnings("unchecked")
 @Override
 public boolean addAll(Collection<? extends E> elements) {
  boolean wasAdded = false;
  for (Object element : elements) {
   if (!this.contains((E) element)) {
    this.add((E) element);
    wasAdded = true;
   }
  }
  return wasAdded;
 }

 @SuppressWarnings("unchecked")
 private void doubleSize() {
  E[] tempArr = baseArray;
  baseArray = (E[]) new Comparable[size * 2];

  for (int i = 0; i < tempArr.length; i++) {
   baseArray[i] = tempArr[i];
  }
 }

 // ********************************************************************************
 // ********************************************************************************

 /*
  * Removes passed element from baseArray if present
  * Finds the element in baseArray using a binary search
  * Returns true if element the element was removed, false if the elment was already present
  */
 @SuppressWarnings("unchecked")
 @Override
 public boolean remove(Object element) {
  if (element.equals(null) || !this.contains(element)) {
   return false;
  }
  E typedElement = (E) element;

  int low = 0;
  int high = size;
  int center = 0;
  int changeValue = size / 2;

  while (high > low) {
   center = (low + high) / 2;
   if (changeValue / 2 == 0) {
    changeValue = 1;
   } else {
    changeValue /= 2;
   }

   if (comparator == null) {
    if (typedElement.compareTo(baseArray[center]) == 0) {
     break;
    } else if (typedElement.compareTo(baseArray[center]) < 0) {
     high -= changeValue;
    } else {
     low += changeValue;
    }
   } else {
    if (typedElement.compareTo(baseArray[center]) == 0) {
     break;
    } else if (comparator.compare(typedElement, baseArray[center]) < 0) {
     high -= changeValue;
    } else {
     low += changeValue;
    }
   }
  }
  for (int i = center; i < size; i++) {
   baseArray[i] = baseArray[i + 1];
  }
  size--;
  return true;
 }

 /*
  * Loops through all elements in the passed collection
  * Will remove the elements according to the rules in the .remove() method
  * If at least one element is removed will return true, otherwise false.
  */
 @SuppressWarnings("unchecked")
 @Override
 public boolean removeAll(Collection<?> elements) {
  boolean wasRemoved = false;
  for (Object element : elements) {
   if (this.contains((E) element)) {
    this.remove((E) element);
    wasRemoved = true;
   }
  }
  return wasRemoved;
 }
/*
 * Removes all elements from the baseArray and resets size to 0
 */
 @SuppressWarnings("unused")
 @Override
 public void clear() {
  for (E value : baseArray) {
   value = null;
  }
  size = 0;
 }

 // ********************************************************************************
 // ********************************************************************************

 /*
  * Returns the first/smallest element in baseArray
  */
 @Override
 public E first() throws NoSuchElementException {
  return baseArray[0];
 }

 /*
  * returns the last/largest element in baseArray
  */
 @Override
 public E last() throws NoSuchElementException {
  return baseArray[size - 1];
 }

 /*
  * returns the size of the array
  */
 @Override
 public int size() {
  return size;
 }

 /*
  * if size == 0 returns true, otherwise return false
  */
 @Override
 public boolean isEmpty() {
  if (size == 0) {
   return true;
  }
  return false;
 }
 
/*
 * Returns array based on elements from 0 to size in baseArray
 */
 @SuppressWarnings("unchecked")
 @Override
 public Object[] toArray() {
  E[] returnArr = (E[]) new Comparable[size];
  for (int i = 0; i < size; i++) {
   returnArr[i] = baseArray[i];
  }
  return returnArr;
 }

 // ********************************************************************************
 // ********************************************************************************

 /*
  * Returns the comparator created in the constructor
  */
 @Override
 public Comparator<? super E> comparator() {
  return comparator;
 }
 
/*
 * Returns the iterator created in the constructor
 */
 @Override
 public Iterator<E> iterator() {
  return searchSetIterator;
 }

 // ********************************************************************************
 // ********************************************************************************

 @SuppressWarnings("hiding")
 private class SearchSetIterator<E> implements Iterator<E> {

  public int location = 0; // always one past location of interest

  /*
   * Checks the baseArray if 'location' is at the upper bound of the array
   * Returns true if location isn't, otherwise returns false;
   */
  @Override
  public boolean hasNext() {
   if (location < size) {
    return true;
   }
   location = 0;
   return false;
  }
  
/*
 * Advances to the next index in baseArray if hasNext() returns true
 */
  @SuppressWarnings("unchecked")
  @Override
  public E next() {
   if (this.hasNext()) {
    location++;
    return (E) baseArray[location - 1];
   }
   return null;
  }

  /*
   * Removes the value at the current index and shifts all other elements down
   * deincrements size and location appropriately
   */
  @Override
  public void remove() {
   size--;
   for (int i = location; i < size; i++) {
    baseArray[i] = baseArray[i + 1];
   }
   baseArray[size]= null; 
   location--;
  }
 }

}
