package assignment07;

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Tests {

 @Test
 void test() throws FileNotFoundException {
  Assertions.assertThrows(FileNotFoundException.class, () -> {
   Grid.readFileToString("poop");
  });
  
  Grid.readFileToString("pacman/testMaze8.txt");
  
  Grid.readFileToString("pacman/classic.txt");
 }

}
