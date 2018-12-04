package assignment07;

public class Node {

 private int row;
 private int col;
 private char element;
 private Node parent;
 private boolean wasVisited;

 
 
 /**
  * Constructor - sets default parent to null and wasVisited flag to false 
  * @param inputType - character to be stored, represents location in grid
  *   possible values: 'X' | ' ' | 'S' | 'G' | '.' 
  * @param inputRow - row location of node in grid
  * @param inputCol - col location of node in grid
  */
 public Node(char inputType, int inputRow, int inputCol) {
  element = inputType;
  row = inputRow;
  col = inputCol;
  parent = null;
  wasVisited = false;
 }



 // **************************************************
 // **************************************************

 
 
 /**
  * @return row
  */
public int getRow() {
 return row;
}

 /**
  * @return col (column)
  */
 public int getCol() {
  return col;
 }

/**
 * @returns stored element
 */
 public char getElement() {
  return element;
 }
 
 /**
  * used when redefining ' ' to '.' to indicate path in Grid.findPath()
  * @param sets element based on input
  */
 public void setElement(char input) {
  element = input;
 }

 /**
  * @returns parent node
  */
 public Node getParent() {
  return parent;
 }
 
 /**
  * used to track path in Grid.findPath()
  * @param parentNode parent node to be set
  */
 public void setParent(Node parentNode) {
  parent = parentNode;
 }
 
 
 
 // **************************************************
 // **************************************************
 
 
 
 /**
  * @return true if element hasn't been visited and isn't a wall ('X')
  */
 public boolean isUnvisited() {
  return (!wasVisited && element != 'X');
 }

 /**
  * flag receivingNode as having been visited and set receiving node's parent to this node
  * @param receivingNode - node to have values changed
  */
 public void visitNode(Node receivingNode) {
  receivingNode.wasVisited = true;
  receivingNode.setParent(this);
 }
 
}
