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
 void testIntSet() {
  assertTrue(intSet.isEmpty());
  assertTrue(intSet.size() == 0);

  // descending loop demonstrates add searches and places correctly
  for (int i = 7; i >= 0; i--) {
   assertTrue(intSet.add(i));
  }
  assertFalse(intSet.add(0));
      
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

  assertTrue(intSet.addAll(comparisonList));
  assertTrue(intSet.last() == 10);

  assertTrue(intSet.containsAll(comparisonList));
  assertTrue(intSet.size() == 9);
  
  assertTrue(intSet.remove(0));
  assertTrue(intSet.remove(2));
  assertFalse(intSet.remove(2));
  
  assertTrue(intSet.size() == 7);
  assertFalse(intSet.contains(0));
  
  assertTrue(intSet.removeAll(comparisonList));
  assertFalse(intSet.removeAll(comparisonList));
  
  assertTrue(intSet.size() == 4);
  assertFalse(intSet.contains(1));
  assertTrue(intSet.contains(4));
  
  intSet.clear();
  
  assertTrue(intSet.size() == 0);
  assertFalse(intSet.contains(4));
  
  
  for (int i = 0; i < 24; i++) {
   intSet.add(i);
  }
  
  // For each loops demonstrate correctly implemented iterator
  int i = 0;
  for (int number : intSet) {
   assertTrue(i == number);
   i++;
  }
  
  for (int number: intSet) {
   intSet.iterator().remove();
  }
  assertTrue(intSet.size() == 0);

 }
 
 @Test
 void testObjectSet() {


  
//TODO Object
  
//TODO Analysis
 }

}
