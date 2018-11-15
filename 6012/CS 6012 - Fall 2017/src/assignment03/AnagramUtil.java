package assignment03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

// 2.5 + 1.5 + 1.0
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
    charArray[j] = charArray[j - 1];
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
    inputArray[j] = inputArray[j - 1];
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
  insertionSort(sortedArray, new Comparator<String>() {
   @Override
   public int compare(String o1, String o2) {
    if (AnagramUtil.areAnagrams(o1, o2)) {
     return 0;
    }
    return 1;
   }
  });
  
  int largestSize = 1;
  int largestIndex = 0;
  int currentSize = 1;
  int currentIndex = 0;
  for (int i = 0; i < sortedArray.length - 1; i++) {
   if (AnagramUtil.areAnagrams(sortedArray[i], sortedArray[i + 1])) {
    currentSize++;
   } else {
    currentSize = 1;
    currentIndex = i+1;
   }
   if (currentSize > largestSize) {
    largestSize = currentSize;
    largestIndex = currentIndex;
   }
   
  }
  if (largestSize == 1) {
   return new String[0];
  }
  String[] outputArr = Arrays.copyOfRange(sortedArray, largestIndex, largestIndex + largestSize);
  return outputArr;
 }



 /*
  * Behaves the same as the previous method, but reads the list of words from the input filename. It
  * is assumed that the file contains one word per line. If the file does not exist or is empty, the
  * method returns an empty array because there are no anagrams.
  * 
  * Will not contain duplicate words
  */
 public static String[] getLargestAnagramGroup(String filename) throws FileNotFoundException {
  Scanner scanner = new Scanner(new File(filename));
  ArrayList<String> words = new ArrayList<>();
  
  while (scanner.hasNext()) {
   words.add(scanner.next());
  }
  scanner.close();
  
  String[] wordsArr = words.toArray(new String[words.size()]);  
  return getLargestAnagramGroup(wordsArr);
 }


 // ****************************************************************************************
 // ****************************************************************************************

 // Was a reasonable implementation, but after reading the requirements of the analysis document
 // it's clear that this was not how we were intended to solve the sorting problem
 public static String[] customGetLargestAnagramGroup(String[] sortedArray) {
  ArrayList<AnagramGroup> groupList = new ArrayList<>();
  for (String word : sortedArray) {
   boolean wordAdded = false;
   for (AnagramGroup group : groupList) {
    if (areAnagrams(AnagramUtil.sort(word), group.sortedWord)) {
     group.addWord(word);
     wordAdded = true;
     break;
    }
   }
   if (!wordAdded) {
    groupList.add(new AnagramGroup(word));
   }
  }
  AnagramGroup biggestGroup = groupList.get(0);
  for (AnagramGroup group : groupList) {
   if (group.size > biggestGroup.size) {
    biggestGroup = group;
   }
  }
  return biggestGroup.getArray();
 }


}
