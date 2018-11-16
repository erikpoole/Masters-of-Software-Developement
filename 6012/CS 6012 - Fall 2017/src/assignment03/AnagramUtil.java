package assignment03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

// 2.5 + 1.5 + 1.0 + 2.0 + 1.0 + 3.0
public class AnagramUtil {

 /*
  * Sorts inputString based on character using an insertion sort
  * takes into account character case during sorting but will retain original case in returned string
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
  * Sorts a generic inputArray using insertion sort based on the passed comparator
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
  * Compares two words and returns true if are anagrams, false otherwise
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
  * Sorts an array of strings based on .areAnagrams()
  * Will return an array containing all strings in the largest group of anagrams
  * If no anagrams are found will return an empty array
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
    currentIndex = i + 1;
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
  * Same functionality as .getLargestAnagramGroup()
  * Takes a file as input rather than an array of strings
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


 /*
  * Generates an array of Strings with randomized size and characters for testing
  */
 public static String[] generateWordList(int size, int length) {
  String[] outputArr = new String[size];
  Random random = new Random();

  for (int i = 0; i < size; i++) {
   int wordLength = random.nextInt(length)+1;
   String word = "";
   for (int j = 0; j < wordLength; j++) {
    word += (char) (random.nextInt(24) + 'a');
   }
   outputArr[i]= word; 
  }

  return outputArr;
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
