package lab05;

import java.util.Comparator;

public class BinarySearch<E> {

 public static <E> void binarySearch(BinarySearchSet<E> set, E goal) {

  int currentLoc = set.baseArray.length / 2;
  int changeValue = set.baseArray.length / 2;

  while (set.baseArray[currentLoc] != goal) {
   changeValue /= 2;
   if (changeValue < 1) {
    changeValue = 1;
   }
   
//   set.comparator.compare(currentLoc, goal);
//
//   if (set.comparator().compare(currentLoc, goal) == 0) {
   }
//   // if (set.baseArray[currentLoc] < goal) {
//    currentLoc += changeValue;
//    } else {
//    currentLoc -= changeValue;
//    }
   
   
  }
 }
//}
