package lab07;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements List<E>, Iterable<E> {

 private Node<E> head;
 private Node<E> tail;
 private int size;

 /*
  * Default Constructor
  */
 public DoublyLinkedList() {
  head = null;
  tail = null;
  size = 0;
 }

 // ********************************************************************************
 // ********************************************************************************

 /*
  * adds passed element to the beginning of the list
  */
 @Override
 public void addFirst(E element) {
  if (size == 0) {
   Node<E> newNode = new Node<E>(element, null, null);
   head = newNode;
   tail = newNode;
  } else {
   Node<E> newNode = new Node<E>(element, null, head);
   head.setPrevious(newNode);
   head = newNode;
  }
  size++;
 }

 /*
  * adds passed element to the end of the list
  */
 @Override
 public void addLast(E o) {
  if (size == 0) {
   Node<E> newNode = new Node<E>(o, null, null);
   head = newNode;
   tail = newNode;
  } else {
   Node<E> newNode = new Node<E>(o, tail, null);
   tail.setNext(newNode);
   tail = newNode;
  }
  size++;
 }

 /*
  * add passed element to the passed index in the list will throw exception if passed index is
  * outside range of list
  */
 @Override
 public void add(int index, E element) throws IndexOutOfBoundsException {
  if (index < 0 || index > size) {
   throw new IndexOutOfBoundsException();
  }

  if (index == 0) {
   addFirst(element);
  } else if (index == size) {
   addLast(element);
  } else {
   Node<E> nodeAfter = findIndex(index);
   Node<E> nodeBefore = nodeAfter.getPrevious();

   Node<E> addedNode = new Node<>(element, nodeBefore, nodeAfter);

   nodeAfter.setPrevious(addedNode);
   nodeBefore.setNext(nodeAfter);

   size++;
  }
 }

 // ********************************************************************************
 // ********************************************************************************

 /*
  * returns first element in the linked list
  */
 @Override
 public E getFirst() throws NoSuchElementException {
  if (size == 0) {
   throw new NoSuchElementException();
  }
  return head.getElement();
 }

 /*
  * returns last element in the linked list
  */
 @Override
 public E getLast() throws NoSuchElementException {
  if (size == 0) {
   throw new NoSuchElementException();
  }
  return tail.getElement();
 }

 /*
  * returns element at passed index in the linked list will throw exception if the passed index is
  * outside of range of the list
  */
 @Override
 public E get(int index) throws IndexOutOfBoundsException {
  if (index < 0 || index >= size) {
   throw new IndexOutOfBoundsException();
  }
  return findIndex(index).getElement();
 }

 // ********************************************************************************
 // ********************************************************************************

 /*
  * removes and returns the first element of the list
  */
 @Override
 public E removeFirst() throws NoSuchElementException {
  if (size == 0) {
   throw new NoSuchElementException();
  }

  E output = head.getElement();

  if (size == 1) {
   head = null;
   tail = null;
  } else {
   Node<E> nextNode = head.getNext();
   nextNode.setPrevious(null);
   head.setNext(null);
   head = nextNode;
  }

  size--;
  return output;
 }

 /*
  * removes and returns the last element of the list
  */
 @Override
 public E removeLast() throws NoSuchElementException {
  if (size == 0) {
   throw new NoSuchElementException();
  }

  E output = tail.getElement();

  Node<E> previousNode = tail.getPrevious();
  previousNode.setNext(null);
  tail.setPrevious(null);
  tail = previousNode;

  size--;
  return output;
 }

 /*
  * removes and returns the element at the passed index will throw exception if the passed index is
  * outside of range of the list
  */
 @Override
 public E remove(int index) throws IndexOutOfBoundsException {
  if (index < 0 || index >= size) {
   throw new IndexOutOfBoundsException();
  }

  E output;

  if (index == 0) {
   output = head.getElement();
   removeFirst();
  } else if (index == size) {
   output = tail.getElement();
   removeLast();
  } else {
   Node<E> doomedNode = findIndex(index);
   Node<E> nodeAfter = doomedNode.getNext();
   Node<E> nodeBefore = doomedNode.getPrevious();

   output = doomedNode.getElement();

   doomedNode.setNext(null);
   doomedNode.setPrevious(null);
   nodeAfter.setPrevious(nodeBefore);
   nodeBefore.setNext(nodeAfter);

   size--;
  }

  return output;
 }

 // ********************************************************************************
 // ********************************************************************************


 /*
  * returns the index of the first occurrence of the passed element in the list
  */
 @Override
 public int indexOf(E element) {
  Node<E> currentNode = head;
  for (int i = 0; i < size; i++) {
   if (currentNode.getElement().equals(element)) {
    return i;
   }
   currentNode = currentNode.getNext();
  }
  return -1;
 }

 /*
  * returns the index of the last occurrence of the passed element in the list
  */
 @Override
 public int lastIndexOf(E element) {
  Node<E> currentNode = tail;
  for (int i = size - 1; i >= 0; i--) {
   if (currentNode.getElement().equals(element)) {
    return i;
   }
   currentNode = currentNode.getPrevious();
  }
  return -1;
 }

 // ********************************************************************************
 // ********************************************************************************

 /*
  * Returns the number of elements in the list
  */
 @Override
 public int size() {
  return size;
 }

 /*
  * Returns true if the list contains no elements, false otherwise
  */
 @Override
 public boolean isEmpty() {
  if (size == 0) {
   return true;
  }
  return false;
 }

 /*
  * Removes all of the elements from the list
  */
 @Override
 public void clear() {
  head = null;
  tail = null;
  size = 0;
 }

 /*
  * Returns an array containing all of the elements in this list
  */
 @Override
 public Object[] toArray() {
  Object[] outputArr = new Object[size];
  Node<E> currentNode = head;
  for (int i = 0; i < size; i++) {
   outputArr[i] = currentNode.getElement();
   currentNode = currentNode.getNext();
  }

  return outputArr;
 }

 // ********************************************************************************
 // ********************************************************************************

 /*
  * returns specific LinkedListIterator for iterating through loops
  */
 @Override
 public Iterator<E> iterator() {
  return new LinkedListIterator<>(this);
 }

 /*
  * internal class for defining LinkedListIterator
  */
 public class LinkedListIterator<F> implements Iterator<F> {
  private int location = 0;
  private Node<E> currentNode = head;
  private DoublyLinkedList<E> list;

  /*
   * default constructor
   * passes list into Iterator
   */
  public LinkedListIterator(DoublyLinkedList<E> input) {
   list = input;
  }

  /*
   * returns false if iterator has reached end of list, true otherwise
   */
  @Override
  public boolean hasNext() {
   if (location >= size) {
    return false;
   }
   return true;
  }

  /*
   * moves to the next location in the list
   */
  @SuppressWarnings("unchecked")
  @Override
  public F next() {
   F output = (F) currentNode.getElement();
   currentNode = currentNode.getNext();
   location++;
   return output;
  }

  /*
   * removes element at current location
   */
  @Override
  public void remove() {
   location--;
   list.remove(location);
  }
 }

 // ********************************************************************************
 // ********************************************************************************

 /*
  * finds node at the passed index in the list
  */
 private Node<E> findIndex(int index) {
  Node<E> currentNode;
  if (index < size / 2) {
   currentNode = head;
   for (int i = 0; i <= index; i++) {
    currentNode = currentNode.getNext();
   }
  } else {
   currentNode = tail;
   for (int i = size - 1; i > index; i--) {
    currentNode = currentNode.getPrevious();
   }
  }
  return currentNode;
 }

}
