package assignment07;

public class Main {

 /**
  * Test main() for producing output files to use with the 'pacman' visualization provided
  */
 public static void main(String[] args) {

  String inputFile = "pacman/randomMaze.txt";
  String outputFile = "pacman/00mySolution.txt";

  PathFinder.solveMaze(inputFile, outputFile);
 }

}
