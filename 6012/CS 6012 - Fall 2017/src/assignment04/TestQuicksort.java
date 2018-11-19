package assignment04;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Comparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestQuicksort<T> {

 ArrayList<Integer> bestList;
 ArrayList<Integer> averageList;
 ArrayList<Integer> worstList;
 
 Comparator<Integer> comparator; 
 
 @BeforeEach
 void setUp() throws Exception {
  bestList = SortUtil.generateBestCase(8);
  averageList = SortUtil.generateAverageCase(8);
  worstList = SortUtil.generateWorstCase(8);
  
  comparator = new Comparator<Integer>() {

   @Override
   public int compare(Integer o1, Integer o2) {
    return (o1.compareTo(o2));
   }};
  
 }

 @Test
 void test() {
    
  assertTrue(bestList.get(0) == 1);
  assertTrue(worstList.get(0) == worstList.size());
  
  SortUtil.quicksort(bestList, comparator);
  SortUtil.quicksort(averageList, comparator);
  SortUtil.quicksort(worstList, comparator);
  
  assertTrue(bestList.get(0) == 1);
  assertTrue(averageList.get(0) == 1);
  assertTrue(worstList.get(0) == 1);
  
 }

}
