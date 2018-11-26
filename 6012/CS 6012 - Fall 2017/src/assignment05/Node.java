package assignment05;

/*
 * Wrapper class around each element in the DoublyLinkedList
 * Stores next and previous nodes and the element itself
 */
public class Node<T> {

 private Node<T> left;
 private Node<T> right;
 private T element;

 /*
  * Constructor
  */
 public Node(T inputElement) {
  element = inputElement;
  left = null;
  right = null;
 }

 /*
  * returns Node before this one in Linked List
  */
 public Node<T> getLeft() {
  return left;
 }

 /*
  * returns Node after this one in Linked List
  */
 public Node<T> getRight() {
  return right;
 }

 /*
  * returns stored element
  */
 public T getElement() {
  return element;
 }
 
 public void setElement(T input) {
  element = input;
 }

 /*
  * changes node previous to this one to another node
  */
 public void setLeft(Node<T> input) {
  left = input;
 }

 /*
  * changes node after this one to another node
  */
 public void setRight(Node<T> input) {
  right = input;
 }

}
