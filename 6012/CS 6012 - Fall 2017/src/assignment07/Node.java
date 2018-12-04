package assignment07;

public class Node {

 private char element;
 private int row;
 private int col;
 private Node parent;

 public boolean wasVisited;

 public Node(char inputType, int inputRow, int inputCol) {
  element = inputType;
  row = inputRow;
  col = inputCol;
  wasVisited = false;
 }



 // **************************************************
 // **************************************************



 public char getElement() {
  return element;
 }
 
 public void setElement(char input) {
  element = input;
 }

 public Node getParent() {
  return parent;
 }
 
 public void setParent(Node parentNode) {
  parent = parentNode;
 }
 
 public boolean isUnvisited() {
  return (!wasVisited && element != 'X');
 }

 public void visitNode(Node receivingNode) {
  receivingNode.wasVisited = true;
  receivingNode.setParent(this);
 }
 




 // **************************************************
 // **************************************************



 public Node getUp() {
  return Grid.nodeGrid[row][col - 1];
 }

 public Node getDown() {
  return Grid.nodeGrid[row][col + 1];
 }

 public Node getRight() {
  return Grid.nodeGrid[row + 1][col];
 }

 public Node getLeft() {
  return Grid.nodeGrid[row - 1][col];
 }

}
