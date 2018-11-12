package lab05;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

//extend comparable?
public class BinarySearchSet<E> implements SortedSet<E>, Iterable<E> {

 public E[] baseArray;
 private int index;

public Comparator<? super E> comparator;

 //use compareTo for primitive types
 @SuppressWarnings("unchecked")
 public BinarySearchSet() {
  baseArray = (E[]) new Object[16];
  index = 0;
  
  comparator = null;
 }

 //use passed comparator for complex types
 @SuppressWarnings("unchecked")
 public BinarySearchSet(Comparator<? super E> inputComparator) {
  baseArray = (E[]) new Object[16];
  index = 0;

  comparator = inputComparator;

 }

 @Override
 public Comparator<? super E> comparator() {
//  comparator.compare(1, 1);
  return comparator;
 }

 @Override
 public E first() throws NoSuchElementException {
  return baseArray[0];
 }

 @Override
 public E last() throws NoSuchElementException {
  System.out.println("index: " + index);
  return baseArray[index-1];
 }

 //Alex's Code !!!!
////binary search function if the user passed in a comparator
// public boolean binarySearch(E[] arr, E goal) {
//       int low = 0, high = arr.length - 1, mid = 0;
//       while (low <= high) {
//         mid = (low + high) / 2;
//         if (comparator.compare(goal,(arr[mid]))==0) {
//           return true;
//         } else if (comparator.compare(goal,arr[mid])<0) {
//           high = mid - 1;
//         } else {
//           low = mid + 1;
//         }
//       }
//       return false;
//     }
 
 //implement binary search
 @SuppressWarnings("unchecked")
 @Override
 public boolean add(E element) {
  if (element.equals(null) || Arrays.asList(baseArray).contains(element)) {
   return false;
  }

  if (index >= baseArray.length) {
   E[] tempArr = baseArray;
   baseArray = (E[]) new Object[index * 2];

   for (int i = 0; i < tempArr.length; i++) {
    baseArray[i] = tempArr[i];
   }

  }
  baseArray[index] = element;
  index++;
  return true;
 }

 @Override
 public boolean addAll(Collection<? extends E> elements) {
  return false;
 }

 @Override
 public void clear() {
  // TODO Auto-generated method stub

 }

 @Override
 public boolean contains(Object element) {
  // TODO Auto-generated method stub
  return false;
 }

 @Override
 public boolean containsAll(Collection<?> elements) {
  // TODO Auto-generated method stub
  return false;
 }

 @Override
 public boolean isEmpty() {
  // TODO Auto-generated method stub
  return false;
 }

 @Override
 public boolean remove(Object element) {
  // TODO Auto-generated method stub
  return false;
 }

 @Override
 public boolean removeAll(Collection<?> elements) {
  // TODO Auto-generated method stub
  return false;
 }

 @Override
 public int size() {
  // TODO Auto-generated method stub
  return 0;
 }

 @Override
 public Object[] toArray() {
  // TODO Auto-generated method stub
  return null;
 }

 @Override
 public Iterator<E> iterator() {
  // TODO Auto-generated method stub
  return null;
 }



}
