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
  assertTrue(intSet.isEmpty());
  assertTrue(intSet.size() == 0);
  
  for (int i = 0; i < 8; i++) {
   intSet.add(i);
  }
  
  assertTrue(intSet.first() == 0);
  assertTrue(intSet.last() == 7);
  assertTrue(intSet.size() == 8);
  assertFalse(intSet.isEmpty());

  assertTrue(intSet.contains(0));
  assertTrue(intSet.contains(7));
  assertFalse(intSet.contains(8));
 }
 
 

}
