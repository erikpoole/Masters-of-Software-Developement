package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class Grid {

 public int height;
 public int width;
 public Node[][] nodeGrid;
 public Node startNode = null;
 public Node endNode = null;



 // **************************************************
 // **************************************************



 /**
  * Sets up grid based on gridString input and specified static width and height height & width will
  * have been set by Grid.readFileToString()
  * 
  * @param gridString - linear representation of the input file (maze)
  */
 public void setUp(String gridString) {
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



 /**
  * modifies grid node elements to represent fastest path path found via breadth-first search
  */
 public void findPath() {
  if (startNode == null || endNode == null) {
   return;
  }

  LinkedList<Node> list = new LinkedList<>();
  list.addLast(startNode);

  while (!list.isEmpty() && endNode.isUnvisited()) {
   Node currentNode = list.removeFirst();

   // TODO fix me
   Node upNode = nodeGrid[currentNode.getRow()][currentNode.getCol() - 1];
   Node rightNode = nodeGrid[currentNode.getRow() + 1][currentNode.getCol()];
   Node downNode = nodeGrid[currentNode.getRow()][currentNode.getCol() + 1];
   Node leftNode = nodeGrid[currentNode.getRow() - 1][currentNode.getCol()];

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



 /**
  * reads in file based on inputFile pathname
  * 
  * @param inputFile - file pathname to be read
  * @return - linear string representation of file
  * @throws FileNotFoundException
  */
 public String readFileToString(String inputFile) throws FileNotFoundException {
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



 /**
  * prints out visual representation of the grid to the chosen path
  * 
  * @param outputFile - pathname to print to
  * @throws FileNotFoundException
  */
 public void printToFile(String outputFile) throws FileNotFoundException {
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



 /**
  * @return character array representation of the grid
  */
 public char[] getGridArray() {
  char[] output = new char[height * width];
  for (int i = 0; i < height; i++) {
   for (int j = 0; j < width; j++) {
    output[i * width + j] = nodeGrid[i][j].getElement();
   }
  }
  return output;
 }
}
