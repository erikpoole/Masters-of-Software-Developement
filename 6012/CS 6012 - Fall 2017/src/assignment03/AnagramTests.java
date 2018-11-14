package assignment03;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Comparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AnagramTests<T> {

 Character[] charArr;
 ArrayList<String> stringList;
 Comparator<Character> comparator;

 @BeforeEach
 void setUp() throws Exception {
  
  charArr = new Character[] {'t', 'e', 'S', 't'};
  comparator = new Comparator<Character>() {
   @Override
   public int compare(Character o1, Character o2) {
    return (-1 * (o1.compareTo(o2)));
   }
  };

  stringList = new ArrayList<>();

  stringList.add("Race");
  stringList.add("Care");
  stringList.add("Acre");
 }


 @Test
 void test() {
  
  AnagramUtil.insertionSort(charArr, comparator);
  assertTrue(charArr[0] == 't');
  assertTrue(charArr[2] == 'S');
  
  
  assertTrue(AnagramUtil.sort("Race").equals("aceR"));
  assertTrue(AnagramUtil.areAnagrams("Race", "Care"));
  assertFalse(AnagramUtil.areAnagrams("Race", "Bare"));


 }

}
