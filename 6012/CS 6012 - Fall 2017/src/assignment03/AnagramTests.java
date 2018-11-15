package assignment03;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AnagramTests<T> {

 Character[] charArr;
 String[] stringArr1;
 String[] stringArr2;
 Comparator<Character> comparator;

 @BeforeEach
 void setUp() throws Exception {
  
  charArr = new Character[] {'t', 'e', 's', 't'};
  comparator = new Comparator<Character>() {
   @Override
   public int compare(Character o1, Character o2) {
    return (-1 * (o1.compareTo(o2)));
   }
  };

  stringArr1 = new String[] {"Base", "Bun", "Nub"};
  stringArr2 = new String[] {"Race", "Care", "Acre", "Bun", "Nub"};
 }


 @Test
 void test() {
  
  AnagramUtil.insertionSort(charArr, comparator);
  assertTrue(charArr[0] == 't');
  assertTrue(charArr[2] == 's');
  assertTrue(charArr[3] == 'e');
  
  
  assertTrue(AnagramUtil.sort("Race").equals("aceR"));
  assertTrue(AnagramUtil.areAnagrams("Race", "Care"));
  assertFalse(AnagramUtil.areAnagrams("Race", "Bare"));
  
  
  assertTrue(AnagramUtil.getLargestAnagramGroup(stringArr1).length == 2);
  assertTrue(Arrays.asList(stringArr1).contains("Bun"));
  assertTrue(AnagramUtil.getLargestAnagramGroup(stringArr2).length == 3);
  assertTrue(Arrays.asList(stringArr2).contains("Acre"));

 
 }

}
