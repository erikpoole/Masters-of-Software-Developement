package lab05;

import java.util.Random;

public class BinarySearchTiming<E> {

 public static void main(String[] args) {

  int numDoubles = 11;
  int sampleSize = 10000;
  
  BinarySearchSet<Integer> sampleSet;
  Random random;


  long bufferTime = System.nanoTime();
  while (bufferTime + 1e9 > System.nanoTime()) {
  }

  int size = 512;
  for (int i = 0; i < numDoubles; i++) {
   sampleSet = new BinarySearchSet<>();
   random = new Random();
   size*=2;
   
   for (int j = 0; j < size; j++) {
    sampleSet.add(j);
   }
   
   long totalTime = 0;
   for (int j = 0; j < sampleSize; j++) {
    int randInt = random.nextInt(size);
    sampleSet.remove(randInt);
    long startTime = System.nanoTime();
    sampleSet.add(randInt);
//    sampleSet.contains(random.nextInt(size));
    long endTime = System.nanoTime();
    totalTime += (endTime - startTime);
   }
   
   System.out.println(size + "\t" + totalTime/sampleSize);
   
  }
  
 }
}
