package lab07;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestDoublyLinkedList {

 DoublyLinkedList<Integer> testList;

 /*
  * prepares for testing
  */
 @BeforeEach
 void setUp() throws Exception {
  testList = new DoublyLinkedList<>();
 }

/*
 * Tests all methods of DoublyLinkedList
 * Includes testing on iterator (bottom)
 */
 @Test
 void test() {
  assertTrue(testList.size() == 0);
  assertTrue(testList.isEmpty());

  Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
   testList.add(1, 99);
  });

  testList.add(0, 99);

  assertTrue(testList.size() == 1);
  assertTrue(!testList.isEmpty());
  assertTrue((int) testList.toArray()[0] == 99);

  testList.addLast(999);
  assertTrue(testList.size() == 2);
  assertTrue((int) testList.toArray()[1] == 999);

  assertTrue(testList.getFirst() == 99);
  assertTrue(testList.getLast() == 999);

  testList.addFirst(9);
  assertTrue(testList.getFirst() == 9);
  assertTrue(testList.get(2) == 999);

  assertTrue(testList.removeFirst() == 9);
  assertTrue(testList.getFirst() == 99);

  assertTrue(testList.removeLast() == 999);
  assertTrue(testList.getLast() == 99);

  testList.addFirst(9);
  testList.addLast(999);
  assertTrue(testList.remove(1) == 99);
  testList.addFirst(9);
  testList.addFirst(9);

  assertTrue(testList.indexOf(999) == 3);
  assertTrue(testList.indexOf(9) == 0);
  assertTrue(testList.lastIndexOf(9) == 2);
  assertTrue(testList.indexOf(100) == -1);

  assertTrue(testList.size() == 4);
  testList.clear();
  assertTrue(testList.size() == 0);
  Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
   testList.get(0);
  });

  
  for (int i = 0; i < 5; i++) {
   testList.add(i, i);
  }
  int expected = 0;
  for (int value : testList) {
   assertTrue(value == expected);
   expected++;
  }

  Iterator<Integer> testIterator = testList.iterator();
  while (testIterator.hasNext()) {
   testIterator.next();
   testIterator.remove();
  }
  assertTrue(testList.isEmpty());
  

 }

}
