package assignment04;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;

public class QuickSortTiming<E> {

 private static int numDoubles = 11;
 private static int sampleSize = 1000;
 private static int size = 512;

 private static ArrayList<Integer> sampleSet;
 private static Comparator<Integer> comparator = new Comparator<Integer>() {

  @Override
  public int compare(Integer o1, Integer o2) {
   return (o1.compareTo(o2));
  }
 };

 // ********************************************************************************
 // ********************************************************************************

 public static void main(String[] args) {

  long bufferTime = System.nanoTime();
  while (bufferTime + 1e9 > System.nanoTime()) {
  }

  for (int i = 0; i < numDoubles; i++) {
   size *= 2;

   // sampleSet = SortUtil.generateBestCase(size);
   sampleSet = SortUtil.generateAverageCase(size);
   // sampleSet = SortUtil.generateWorstCase(size);

   for (int j = 0; j < 3; j++) {
    if (j == 0) {
     System.out.print("Pivot based on First Index:\t");
     timeSort(sampleSet, "firstIndex");
    }
    if (j == 1) {
     System.out.print("Pivot based on Middle Index:\t");
     timeSort(sampleSet, "middleIndex");
    }
    if (j == 2) {
     System.out.print("Pivot based on Sampling:\t");
     timeSort(sampleSet, "sampling");
    }
   }
  }
 }

 // ********************************************************************************
 // ********************************************************************************

 private static void timeSort(ArrayList<Integer> inputSet, String pivotMethod) {

  long totalTime = 0;
  for (int i = 0; i < sampleSize; i++) {

   ArrayList<Integer> tempSet = null;
   tempSet = new ArrayList<>();
   for (Integer value : inputSet) {
    tempSet.add(value);
   }

   long startTime = System.nanoTime();
   SortUtil.quicksortSpecifyPivot(tempSet, comparator, pivotMethod);
   long endTime = System.nanoTime();
   totalTime += (endTime - startTime);
  }
  
  System.out.println(size + "\t" + totalTime / sampleSize / 10e6 + "\t"
    + new Timestamp(System.currentTimeMillis()));
 }
}
