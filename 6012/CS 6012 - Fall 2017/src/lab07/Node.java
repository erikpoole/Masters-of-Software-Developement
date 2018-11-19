package lab07;

public class Node<E> {

 private Node<E> previous;
 private Node<E> next;
 private E element;
 
 public Node(E inputElement, Node<E> nodeBefore, Node<E> nodeAfter) {
  element = inputElement;
  previous = nodeBefore;
  next = nodeAfter;
 }
 
 public Node<E> getPrevious() {
  return previous;
 }
 
 public Node<E> getNext() {
  return next;
 }
 
 public E getElement() {
  return element;
 }
 
}
