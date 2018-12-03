package assignment07;

public class Grid {
 
 private static int height;
 private static int width;
 private static Node[][] grid;
 
 
 
 //**************************************************
 //**************************************************
 
 
 
 /*
  * assumes input format is width, then height 
  * e.g.3x2 means three columns, 2 rows
  */
 public static void setUpGraph(int inputWidth, int inputHeight, char[] inputArr) {
  width = inputWidth;
  height = inputHeight;
  
  grid = new Node[width][height];
  for (int i = 0; i < inputArr.length; i++) {
   int row = i / width;
   int col = i % width;
   grid[row][col] = new Node(inputArr[i], row, col);
  }
 }
 
 
 
 //**************************************************
 //**************************************************
 
 
 
 public static Node[][] getGrid() {
  return grid;
 }
 
 public static int getHeight() {
  return height;
 }
 
 public static int getWidth() {
  return width;
 }
 
 
}
