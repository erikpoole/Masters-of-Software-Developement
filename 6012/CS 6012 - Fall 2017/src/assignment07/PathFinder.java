package assignment07;

import java.io.FileNotFoundException;


public class PathFinder {

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
