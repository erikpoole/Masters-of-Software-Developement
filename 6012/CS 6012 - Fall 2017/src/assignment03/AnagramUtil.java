package assignment03;

import java.util.Comparator;

// time spent 11:00
public class AnagramUtil {

 /*
  * This method returns the sorted version of the input string. The sorting must be accomplished
  * using an insertion sort.
  */
 public static String sort(String inputString) {
  char[] charArray = inputString.toCharArray();

  // loops through remaining unsorted elements
  // assumes charArray[0] to be the first sorted element
  for (int i = 1; i < charArray.length; i++) {
   char changingChar = charArray[i];

   // finds first location with a value larger than changingChar
   int location = 0;
   while (Character.toLowerCase(changingChar) > Character.toLowerCase(charArray[location])) {
    location++;
   }

   // shifts all values in the sorted portion up one location
   for (int j = i; j > location; j--) {
    charArray[i] = charArray[i - 1];
   }

   charArray[location] = changingChar;
  }

  return new String(charArray);
 }


 /*
  * This generic method sorts the input array using an insertion sort and the input Comparator
  * object.
  */
 public static <T> void insertionSort(T[] inputArray, Comparator<? super T> comparator) {

  // loops through remaining unsorted elements
  // assumes charArray[0] to be the first sorted element
  for (int i = 1; i < inputArray.length; i++) {
   T changingValue = inputArray[i];

   // finds first location with a value larger than changingChar
   int location = 0;
   while (comparator.compare(changingValue, inputArray[location]) > 0) {
    location++;
   }

   // shifts all values in the sorted portion up one location
   for (int j = i; j > location; j--) {
    inputArray[i] = inputArray[i - 1];
   }

   inputArray[location] = changingValue;
  }

 }


 /*
  * This method returns true if the two input strings are anagrams of each other, otherwise returns
  * false.
  */
 public static boolean areAnagrams(String input1, String input2) {  
  
  String sorted1 = AnagramUtil.sort(input1).toLowerCase();
  String sorted2 = AnagramUtil.sort(input2).toLowerCase();
  
  if (sorted1.equals(sorted2)) {
   return true;
  }
  return false;
 }


 /*
  * This method returns the largest group of anagrams in the input array of words, in no particular
  * order. It returns an empty array if there are no anagrams in the input array.
  * 
  * Will not contain duplicate words
  */
 public static String[] getLargestAnagramGroup(String[] sortedArray) {
  // TODO
  return null;
 }


 /*
  * Behaves the same as the previous method, but reads the list of words from the input filename. It
  * is assumed that the file contains one word per line. If the file does not exist or is empty, the
  * method returns an empty array because there are no anagrams.
  * 
  * Will not contain duplicate words
  */
 public static String[] getLargestAnagramGroup(String filename) {
  // TODO
  return null;
 }


}
