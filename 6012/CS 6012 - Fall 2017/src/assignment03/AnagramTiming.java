package assignment03;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Comparator;

public class AnagramTiming<E> {

 public static void main(String[] args) {

  int numDoubles = 15;
  int sampleSize = 100;

  String[] sampleSet;


  long bufferTime = System.nanoTime();
  while (bufferTime + 1e9 > System.nanoTime()) {
  }

  int size = 512;
  for (int i = 0; i < numDoubles; i++) {
   size *= 2;
   // first argument is numWords, second is size of words
   // sampleSet = AnagramUtil.generateWordList(2, size);
   sampleSet = AnagramUtil.generateWordList(size, 10);

   long totalTime = 0;
   for (int j = 0; j < sampleSize; j++) {
    long startTime = System.nanoTime();
    // AnagramUtil.areAnagrams(sampleSet[0], sampleSet[1]);
    // AnagramUtil.getLargestAnagramGroup(sampleSet);
    Arrays.sort(sampleSet, new Comparator<String>() {
     @Override
     public int compare(String o1, String o2) {
      if (AnagramUtil.areAnagrams(o1, o2)) {
       return 0;
      }
      return 1;
     }
    });
    long endTime = System.nanoTime();
    totalTime += (endTime - startTime);
   }

   System.out.println(size + "\t" + totalTime / sampleSize / 10e6 + "\t"
     + new Timestamp(System.currentTimeMillis()));

  }

 }
}
