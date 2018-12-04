package assignment07;

import java.io.FileNotFoundException;

public class PathFinder {

 /**
  * runs a series of static methods from the Grid class in a clean format
  * to read in file, setup the grid, find the path, and output an updated file
  * @param inputFile - pathname to input
  * @param outputFile - pathname to output
  */
 public static void solveMaze(String inputFile, String outputFile) {
  try {
   Grid grid = new Grid();
   String gridString = grid.readFileToString(inputFile);
   grid.setUp(gridString);
   grid.findPath();
   grid.printToFile(outputFile);

  } catch (FileNotFoundException e) {
   e.printStackTrace();
  }

 }
}
