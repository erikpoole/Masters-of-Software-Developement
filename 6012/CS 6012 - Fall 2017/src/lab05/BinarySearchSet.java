package lab05;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchSet<E extends Comparable<E>> implements SortedSet<E>, Iterable<E> {

 private E[] baseArray;
 private int size; // always one past end of accessible values e.g if five included values, size
                   // will be 5, but indices will be 0-4
 private Iterator<E> searchSetIterator;

 // ********************************************************************************
 // ********************************************************************************

 public Comparator<? super E> comparator;

 // use compareTo for primitive types
 @SuppressWarnings("unchecked")
 public BinarySearchSet() {
  baseArray = (E[]) new Comparable[16];
  size = 0;
  comparator = null;
  searchSetIterator = new SearchSetIterator<>();
 }

 // use passed comparator for complex types
 @SuppressWarnings("unchecked")
 public BinarySearchSet(Comparator<? super E> inputComparator) {
  baseArray = (E[]) new Comparable[16];
  size = 0;
  comparator = inputComparator;
  searchSetIterator = new SearchSetIterator<>();
 }

 // ********************************************************************************
 // ********************************************************************************

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

 @Override
 public E first() throws NoSuchElementException {
  return baseArray[0];
 }

 @Override
 public E last() throws NoSuchElementException {
  return baseArray[size - 1];
 }

 @Override
 public int size() {
  return size;
 }

 @Override
 public boolean isEmpty() {
  if (size == 0) {
   return true;
  }
  return false;
 }

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

 @Override
 public Comparator<? super E> comparator() {
  return comparator;
 }

 @Override
 public Iterator<E> iterator() {
  return searchSetIterator;
 }

 // ********************************************************************************
 // ********************************************************************************

 @SuppressWarnings("hiding")
 private class SearchSetIterator<E> implements Iterator<E> {

  public int location = 0; // always one past location of interest

  @Override
  public boolean hasNext() {
   if (location < size) {
    return true;
   }
   location = 0;
   return false;
  }

  @SuppressWarnings("unchecked")
  @Override
  public E next() {
   if (this.hasNext()) {
    location++;
    return (E) baseArray[location - 1];
   }
   return null;
  }

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
