package assignment05;

/**
 *  @author epoole
 * 
 * wrapper class around each element in the BinarySearchTree
 * stores left and right child nodes and the housed element
 * @param <T> generic element to be stored
 */
public class Node<T> {

 private Node<T> left;
 private Node<T> right;
 private T element;

 /**
  * constructor
  * @param inputElement to be stored
  */
 public Node(T inputElement) {
  element = inputElement;
  left = null;
  right = null;
 }


 /**
  * @return left child node
  */
 public Node<T> getLeft() {
  return left;
 }

/**
 * @return right child node
 */
 public Node<T> getRight() {
  return right;
 }

 /**
  * @return stored element
  */
 public T getElement() {
  return element;
 }
 
 /**
  * changes element associated with node
  * @param input new element to be stored
  */
 public void setElement(T input) {
  element = input;
 }

 /**
  * Changes left child node
  * @param input new node
  */
 public void setLeft(Node<T> input) {
  left = input;
 }

/**
 * changes right child node 
 * @param input new node
 */
 public void setRight(Node<T> input) {
  right = input;
 }

}
