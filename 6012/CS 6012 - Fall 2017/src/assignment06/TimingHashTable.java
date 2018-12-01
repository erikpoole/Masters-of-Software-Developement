package assignment06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class TimingHashTable<E> {

 public static ChainingHashTable badHashTable;
 public static ChainingHashTable mediocreHashTable;
 public static ChainingHashTable goodHashTable;

/**
 * Tests two different metrics depending on how code is commented -
 * Tests time taken to run .contains() after adding words with three hash functors 
 * OR
 * Returns proportion of linked Lists left unfilled after adding words
 */
 public static void main(String[] args) {

  int numDoubles = 15;
  int sampleSize = 1000000;

  int hashTableSize = 1;
  int inputValuesSize = 10000;

  String[] wordArr = generateWordList(inputValuesSize, 15);
  ArrayList<String> wordList = new ArrayList<>(Arrays.asList(wordArr));

  badHashTable = new ChainingHashTable(hashTableSize, new BadHashFunctor());
  mediocreHashTable = new ChainingHashTable(hashTableSize, new MediocreHashFunctor());
  goodHashTable = new ChainingHashTable(hashTableSize, new GoodHashFunctor());

  long bufferTime = System.nanoTime();
  while (bufferTime + 1e9 > System.nanoTime()) {
  }

  for (int i = 0; i < numDoubles; i++) {
   hashTableSize *= 2;

   badHashTable = new ChainingHashTable(hashTableSize, new BadHashFunctor());
   mediocreHashTable = new ChainingHashTable(hashTableSize, new MediocreHashFunctor());
   goodHashTable = new ChainingHashTable(hashTableSize, new GoodHashFunctor());

   badHashTable.addAll(wordList);
   mediocreHashTable.addAll(wordList);
   goodHashTable.addAll(wordList);

   // System.out.println("badHashTable\t" + hashTableSize + "\t" + badHashTable.countFilledLists());
   // System.out.println("mediocreHashTable\t" + hashTableSize + "\t" +
   // mediocreHashTable.countFilledLists());
   // System.out.println("goodHashTable\t" + hashTableSize + "\t" +
   // goodHashTable.countFilledLists());


   long totalTime = 0;
   for (int j = 0; j < sampleSize; j++) {
    long startTime = System.nanoTime();
//    badHashTable.contains("test");
//    mediocreHashTable.contains("test");
    goodHashTable.contains("test");
    long endTime = System.nanoTime();
    totalTime += (endTime - startTime);
   }

   System.out.println(hashTableSize + "\t" + totalTime / sampleSize);

  }

 }

/**
 * Used for generating a list of randomized words 
 * @param size - size of list to be generated
 * @param length - maximum length of word to be generated
 * @return - the filled randomized word list
 */
 public static String[] generateWordList(int size, int length) {
  String[] outputArr = new String[size];
  Random random = new Random();

  for (int i = 0; i < size; i++) {
   int wordLength = random.nextInt(length) + 1;
   String word = "";
   for (int j = 0; j < wordLength; j++) {
    word += (char) (random.nextInt(24) + 'a');
   }
   outputArr[i] = word;
  }

  return outputArr;
 }

}
