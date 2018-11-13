package lab05;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SearchSetTests<E> {

 BinarySearchSet<Integer> intSet;

 @BeforeEach
 void setUp() throws Exception {
  intSet = new BinarySearchSet<>();


 }

 @Test
 void test() {
  assertTrue(intSet.isEmpty());
  assertTrue(intSet.size() == 0);

  // descending loop demonstrates add searches and places correctly
  for (int i = 7; i >= 0; i--) {
   intSet.add(i);
  }


  assertTrue(intSet.first() == 0);
  assertTrue(intSet.last() == 7);
  assertTrue(intSet.size() == 8);
  assertFalse(intSet.isEmpty());

  assertTrue(intSet.contains(0));
  assertTrue(intSet.contains(7));
  assertFalse(intSet.contains(8));

  ArrayList<Integer> comparisonList = new ArrayList<>();
  comparisonList.add(0);
  comparisonList.add(1);
  comparisonList.add(7);

  assertTrue(intSet.containsAll(comparisonList));

  comparisonList.add(10);

  assertFalse(intSet.containsAll(comparisonList));

  intSet.addAll(comparisonList);

  assertTrue(intSet.containsAll(comparisonList));
  assertTrue(intSet.size() == 9);



  // TODO remove may not be working as expected
  // testing iterator.next() & iterator.hasNext() & iterator.remove();
//  for (int number : comparisonList) {
//
//   System.out.println(number);
//   intSet.iterator().remove();
//  }
//
//  System.out.println();
//  System.out.println("Size: " + intSet.size());
//
//  for (int number : intSet) {
//   System.out.println(number);
//  }



 }



}
