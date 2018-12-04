package assignment07;

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Tests {
 
 Grid grid;
 
 @BeforeEach
 void setUp() throws Exception {
  grid = new Grid();
 }

 @Test
 void test() throws FileNotFoundException {
  Assertions.assertThrows(FileNotFoundException.class, () -> {
   grid.readFileToString("poop");
  });
  
  String classicString = grid.readFileToString("pacman/classic.txt");
  assertTrue(grid.height == 11);
  assertTrue(grid.width == 20);
  
  grid.setUp(classicString);
  
  assertTrue(grid.nodeGrid.length == 11);
  assertTrue(grid.nodeGrid[0].length == 20);
  assertTrue(grid.endNode.getCol() == 8);
  assertTrue(grid.endNode.getRow() == 5);
  assertTrue(grid.startNode.getCol() == 9);
  assertTrue(grid.startNode.getRow() == 9);
  
  assertTrue(grid.startNode.getElement() == 'S');
  assertTrue(grid.endNode.getElement() == 'G');
  assertTrue(grid.startNode.getParent() == null);
  assertTrue(grid.endNode.getParent() == null);
  assertTrue(grid.startNode.isUnvisited() == true);
  assertTrue(grid.endNode.isUnvisited() == true);
  assertTrue(grid.nodeGrid[0][0].isUnvisited() == false);
  
  grid.startNode.visitNode(grid.endNode);
  
  assertTrue(grid.endNode.isUnvisited() == false);
  assertTrue(grid.endNode.getParent() == grid.startNode);
  
 }

}
