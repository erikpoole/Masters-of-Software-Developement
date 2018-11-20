package lab07;

/*
 * Wrapper class around each element in the DoublyLinkedList
 * Stores next and previous nodes and the element itself
 */
public class Node<E> {

 private Node<E> previous;
 private Node<E> next;
 private E element;

 /*
  * Constructor
  */
 public Node(E inputElement, Node<E> nodeBefore, Node<E> nodeAfter) {
  element = inputElement;
  previous = nodeBefore;
  next = nodeAfter;
 }

 /*
  * returns Node before this one in Linked List
  */
 public Node<E> getPrevious() {
  return previous;
 }

 /*
  * returns Node after this one in Linked List
  */
 public Node<E> getNext() {
  return next;
 }

 /*
  * returns stored element
  */
 public E getElement() {
  return element;
 }

 /*
  * changes node previous to this one to another node
  */
 public void setPrevious(Node<E> input) {
  previous = input;
 }

 /*
  * changes node after this one to another node
  */
 public void setNext(Node<E> input) {
  next = input;
 }

}
