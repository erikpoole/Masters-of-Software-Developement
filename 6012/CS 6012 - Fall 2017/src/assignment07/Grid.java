package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class Grid {

 public static int height;
 public static int width;
 public static Node[][] nodeGrid;
 public static Node startNode;
 public static Node endNode;



 // **************************************************
 // **************************************************



 /*
  * assumes input format is width, then height e.g.3x2 means three columns, 2 rows
  */
 public static void setUp(String gridString) {
  char[] gridArr = gridString.toCharArray();

  nodeGrid = new Node[height][width];
  for (int i = 0; i < gridArr.length; i++) {
   int row = i / width;
   int col = i % width;

   Node newNode = new Node(gridArr[i], row, col);
   nodeGrid[row][col] = newNode;
   if (newNode.getElement() == 'S') {
    startNode = newNode;
   }
   if (newNode.getElement() == 'G') {
    endNode = newNode;
   }

  }
 }



 public static void findPath() {
  LinkedList<Node> list = new LinkedList<>();
  list.addLast(startNode);

  while (!list.isEmpty() && !endNode.wasVisited) {
   Node currentNode = list.removeFirst();

   Node upNode = currentNode.getUp();
   Node rightNode = currentNode.getRight();
   Node downNode = currentNode.getDown();
   Node leftNode = currentNode.getLeft();

   if (upNode.isUnvisited()) {
    list.addLast(upNode);
    currentNode.visitNode(upNode);
   }
   if (rightNode.isUnvisited()) {
    list.addLast(rightNode);
    currentNode.visitNode(rightNode);
   }
   if (downNode.isUnvisited()) {
    list.addLast(downNode);
    currentNode.visitNode(downNode);
   }
   if (leftNode.isUnvisited()) {
    list.addLast(leftNode);
    currentNode.visitNode(leftNode);
   }
  }

  if (endNode.getParent() != null) {
   Node nodeInPath = endNode.getParent();
   while (nodeInPath != startNode) {
    nodeInPath.setElement('.');
    nodeInPath = nodeInPath.getParent();
   }
  }
 }



 // **************************************************
 // **************************************************



 public static String readFileToString(String inputFile) throws FileNotFoundException {
  File input = new File(inputFile);
  Scanner scanner = new Scanner(input);

  height = Integer.parseInt(scanner.next());
  width = Integer.parseInt(scanner.next());

  String gridString = "";
  while (scanner.hasNextLine()) {
   gridString += scanner.nextLine();
  }

  scanner.close();
  return gridString;
 }



 public static void printToFile(String outputFile) throws FileNotFoundException {
  char[] gridArr = getGridArray();

  File output = new File(outputFile);
  PrintWriter writer = new PrintWriter(output);

  writer.println(height + " " + width);
  for (int i = 0; i < height; i++) {
   String line = new String(gridArr, i * width, width);
   writer.println(line);
  }

  writer.close();
 }



 public static char[] getGridArray() {
  char[] output = new char[height * width];
  for (int i = 0; i < height; i++) {
   for (int j = 0; j < width; j++) {
    output[i * width + j] = nodeGrid[i][j].getElement();
   }
  }
  return output;
 }


}
