package assignment07;

public class Node {

 private char element;
 private int row;
 private int col;
 private Node parent;
 public boolean wasVisited;

 
 
 /**
  * Constructor - sets default parent to null and wasVisited flag to false 
  * @param inputType - character to be stored, represents location in grid
  *   possible values: 'X', ' ', 'S', 'G', '.' 
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
 


 // **************************************************
 // **************************************************


 
/**
 * @return the node above this one based on position in the grid
 */
 public Node getUp() {
  return Grid.nodeGrid[row][col - 1];
 }

 /**
  * @return the node below this one based on position in the grid
  */
 public Node getDown() {
  return Grid.nodeGrid[row][col + 1];
 }

 /**
  * @return the node right of this one based on position in the grid
  */
 public Node getRight() {
  return Grid.nodeGrid[row + 1][col];
 }

 /**
  * @return the node left of this one based on position in the grid
  */
 public Node getLeft() {
  return Grid.nodeGrid[row - 1][col];
 }

}
