package lab05;

public class BinarySearch<E> {

// public static <E> boolean binarySearch(BinarySearchSet<E> set, E goal) {
//
//  int currentLoc = set.baseArray.length / 2;
//  int changeValue = set.baseArray.length / 2;
//
//  while (true) {
//   if (currentLoc <= 0 || currentLoc >= set.baseArray.length) {
//    return false;
//   }
//   
//   if (changeValue / 2 == 0) {
//    changeValue = 1;
//   } else {
//    changeValue /= 2;
//   }
//
//   if (set.comparator.compare(set.baseArray[currentLoc], goal) == 0) {
//    break;
//   }
//   else if (set.comparator.compare(set.baseArray[currentLoc], goal) < 0) {
//    currentLoc -= changeValue;
//   } else {
//    currentLoc += changeValue;
//   }
//  }
//  return true;
// }
}

