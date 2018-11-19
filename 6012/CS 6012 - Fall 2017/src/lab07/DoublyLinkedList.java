package lab07;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements List<E>, Iterable<E> {
 
 Node<E> head;
 Node<E> tail;
 int size;
 
public DoublyLinkedList() {
  head = null;
  tail = null;
  size = 0;
 }


/**
 * Inserts the specified element at the beginning of the list. O(1) for a
 * doubly-linked list.
 */
 @Override
 public void addFirst(E element) {
  if (size == 0) {
   Node<E> newNode = new Node<E>(element, null, null);
   head = newNode;
   tail = newNode;
  } else {
   Node<E> newNode = new Node<E>(element, null, head);
   head = newNode;
  }
  size++;
 }

 /**
  * Inserts the specified element at the end of the list. O(1) for a
  * doubly-linked list.
  */
 @Override
 public void addLast(E o) {
  if (size == 0) {
   Node<E> newNode = new Node<E>(o, null, null);
   head = newNode;
   tail = newNode;
  } else {
   Node<E> newNode = new Node<E>(o, tail, null);
   tail = newNode;
  }
  size++;
 }

 /**
  * Inserts the specified element at the specified position in the list. Throws
  * IndexOutOfBoundsException if index is out of range (index < 0 || index >
  * size()) O(N) for a doubly-linked list.
  */
 @Override
 public void add(int index, E element) throws IndexOutOfBoundsException {
  // TODO Auto-generated method stub
  
 }

 /**
  * Returns the first element in the list. Throws NoSuchElementException if the
  * list is empty. O(1) for a doubly-linked list.
  */
 @Override
 public E getFirst() throws NoSuchElementException {
  // TODO Auto-generated method stub
  return null;
 }

 /**
  * Returns the last element in the list. Throws NoSuchElementException if the
  * list is empty. O(1) for a doubly-linked list.
  */
 @Override
 public E getLast() throws NoSuchElementException {
  // TODO Auto-generated method stub
  return null;
 }

 /**
  * Returns the element at the specified position in the list. Throws
  * IndexOutOfBoundsException if index is out of range (index < 0 || index >=
  * size()) O(N) for a doubly-linked list.
  */
 @Override
 public E get(int index) throws IndexOutOfBoundsException {
  // TODO Auto-generated method stub
  return null;
 }

 /**
  * Removes and returns the first element from the list. Throws
  * NoSuchElementException if the list is empty. O(1) for a doubly-linked list.
  */
 @Override
 public E removeFirst() throws NoSuchElementException {
  // TODO Auto-generated method stub
  return null;
 }

 /**
  * Removes and returns the last element from the list. Throws
  * NoSuchElementException if the list is empty. O(1) for a doubly-linked list.
  */
 @Override
 public E removeLast() throws NoSuchElementException {
  // TODO Auto-generated method stub
  return null;
 }

 /**
  * Removes and returns the element at the specified position in the list. Throws
  * IndexOutOfBoundsException if index is out of range (index < 0 || index >=
  * size()) O(N) for a doubly-linked list.
  */
 @Override
 public E remove(int index) throws IndexOutOfBoundsException {
  // TODO Auto-generated method stub
  return null;
 }

 /**
  * Returns the index of the first occurrence of the specified element in the
  * list, or -1 if this list does not contain the element. O(N) for a
  * doubly-linked list.
  */
 @Override
 public int indexOf(E element) {
  // TODO Auto-generated method stub
  return 0;
 }

 /**
  * Returns the index of the last occurrence of the specified element in this
  * list, or -1 if this list does not contain the element. O(N) for a
  * doubly-linked list.
  */
 @Override
 public int lastIndexOf(E element) {
  // TODO Auto-generated method stub
  return 0;
 }

 /**
  * Returns the number of elements in this list. O(1) for a doubly-linked list.
  */
 @Override
 public int size() {
  // TODO Auto-generated method stub
  return 0;
 }

 /**
  * Returns true if this collection contains no elements. O(1) for a
  * doubly-linked list.
  */
 @Override
 public boolean isEmpty() {
  // TODO Auto-generated method stub
  return false;
 }

 /**
  * Removes all of the elements from this list. O(1) for a doubly-linked list.
  */
 @Override
 public void clear() {
  // TODO Auto-generated method stub
  
 }

 /**
  * Returns an array containing all of the elements in this list in proper
  * sequence (from first to last element). O(N) for a doubly-linked list.
  */
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
