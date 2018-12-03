package assignment07;

public class Node {
 
 private char type;
 private int row;
 private int col;
 private Node parent;
 
 public boolean wasVisited;
 
 public Node(char inputType, int inputRow, int inputCol) {
  type = inputType;
  row = inputRow;
  col = inputCol;
  wasVisited = false;
 }
 
 
 
 //**************************************************
 //**************************************************
 
 
 
 public char getNodeType() {
  return type;
 }
 
 public Node getParent() {
  return parent;
 }
 
 public void setParent(Node parent) {
  this.parent = parent;
 }
 
 
 
 //**************************************************
 //**************************************************
 
 
 
 public Node getUp() {
  return Grid.getGrid()[row][col-1];
 }
 
 public Node getDown() {
  return Grid.getGrid()[row][col+1];
 }
 
 public Node getRight() {
  return Grid.getGrid()[row+1][col];
 }
 
 public Node getLeft() {
  return Grid.getGrid()[row-1][col];
 }
 
}
