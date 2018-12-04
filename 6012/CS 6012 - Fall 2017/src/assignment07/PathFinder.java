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
   String gridString = Grid.readFileToString(inputFile);
   Grid.setUp(gridString);
   Grid.findPath();
   Grid.printToFile(outputFile);

  } catch (FileNotFoundException e) {
   e.printStackTrace();
  }

 }
}
