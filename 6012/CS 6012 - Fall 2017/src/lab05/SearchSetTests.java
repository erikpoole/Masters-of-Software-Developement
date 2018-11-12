package lab05;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SearchSetTests {
 
 BinarySearchSet<Integer> intSet;

 @BeforeEach
 void setUp() throws Exception {
  intSet = new BinarySearchSet<>();
 }

 @Test
 void test() {
  for (int i = 0; i < 8; i++) {
   intSet.add(i);
  }
  
  assertTrue(intSet.first() == 0);
  assertTrue(intSet.last() == 7);
//  assertTrue(intSet.baseArray.length == 8);

//  System.out.println(intSet.baseArray.length);
 }
 
 

}
