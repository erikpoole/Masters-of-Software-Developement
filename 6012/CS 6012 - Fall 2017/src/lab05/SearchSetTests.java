package lab05;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Comparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SearchSetTests<E> {

 BinarySearchSet<Integer> intSet;
 BinarySearchSet<String> reverseStringSet;

 
 /*
  * sets up sets for testing
  */
 @BeforeEach
 void setUp() throws Exception {
  intSet = new BinarySearchSet<>();
  
  /*
   * custom Comparator will sort in reverse
   */
  reverseStringSet = new BinarySearchSet<>(new Comparator<String>() {

   @Override
   public int compare(String o1, String o2) {
    return (-1*o1.compareTo(o2));
   }});
 }

 /*
  * testing all methods with an object that implements comparable (int)
  */
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
 
 /*
  * testing object with custom comparator for string
  */
 @Test
 void testReverseStringSet() {
  
  assertTrue(reverseStringSet.isEmpty());
  
  assertTrue(reverseStringSet.add("AA"));
  assertTrue(reverseStringSet.add("ZZ"));
  assertTrue(reverseStringSet.add("KK"));
  assertFalse(reverseStringSet.add("KK"));
  
  assertTrue(reverseStringSet.size() == 3);
  assertTrue(reverseStringSet.last() == "AA");
  assertTrue(reverseStringSet.first() == "ZZ");
 }

}
