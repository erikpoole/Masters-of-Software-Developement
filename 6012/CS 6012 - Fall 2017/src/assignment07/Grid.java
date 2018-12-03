package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Grid {

 public static int height;
 public static int width;
 public static Node[][] nodeGrid;
 
 
 
 // **************************************************
 // **************************************************



 /*
  * assumes input format is width, then height e.g.3x2 means three columns, 2 rows
  */
 public static void setUp(String gridString) {
  char[] gridArr = gridString.toCharArray();

  nodeGrid = new Node[width][height];
  for (int i = 0; i < gridArr.length; i++) {
   int row = i / width;
   int col = i % width;
   nodeGrid[row][col] = new Node(gridArr[i], row, col);
  }
 }

 
 
 // **************************************************
 // **************************************************



 public static String readFileToString(String inputFile) throws FileNotFoundException {
  File input = new File(inputFile);
  Scanner scanner = new Scanner(input);

  width = Integer.parseInt(scanner.next());
  height = Integer.parseInt(scanner.next());

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
  
  writer.println(width + " " + height);
  for (int i = 0; i < height; i++) {
   String line = new String(gridArr, i*width, width);
   writer.println(line);
  }

  writer.close();
 }
 
 
 
 public static char[] getGridArray() {
  char[] output = new char[height*width];
  for (int i = 0; i < height; i++) {
   for (int j = 0; j < width; j++) {
    output[i*width+j] = nodeGrid[i][j].getNodeType();
   }
  }
  return output;
 }


}
