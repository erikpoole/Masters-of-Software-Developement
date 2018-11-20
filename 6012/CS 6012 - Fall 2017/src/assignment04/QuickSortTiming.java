package assignment04;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class QuickSortTiming<E> {

 private static int numDoubles = 11;
 private static int sampleSize = 100;
 private static int size = 512;

 private static ArrayList<Integer> sampleSet;
// private static HashMap<Integer, Long> firstIndexMap = new HashMap<>();
// private static HashMap<Integer, Long> middleIndexMap = new HashMap<>();
// private static HashMap<Integer, Long> samplingMap = new HashMap<>();

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

   /*
    * Best/Average/Worst Case comparison
    */
   for (int j = 0; j < 3; j++) {
    if (j == 0) {
     sampleSet = SortUtil.generateBestCase(size);
     System.out.print("Best Case (QuickSort):\t");
     timeSort(sampleSet, "sampling");
     System.out.print(("Best Case (MergeSort):\t"));
     timeMerge(sampleSet);
    }
    if (j == 1) {
     sampleSet = SortUtil.generateAverageCase(size);
     System.out.print("Ave. Case (Quicksort):\t");
     timeSort(sampleSet, "sampling");
     System.out.print(("Ave. Case (MergeSort):\t"));
     timeMerge(sampleSet);
    }
    if (j == 2) {
     sampleSet = SortUtil.generateWorstCase(size);
     System.out.print("Worst Case (Quicksort):\t");
     timeSort(sampleSet, "sampling");
     System.out.print(("Worst Case (MergeSort):\t"));
     timeMerge(sampleSet);
    }
   }

   /*
    * Pivot Selection
    */
   // sampleSet = SortUtil.generateAverageCase(size);

   // for (int j = 0; j < 3; j++) {
   // if (j == 0) {
   // System.out.print("Pivot based on First Index:\t");
   // timeSort(sampleSet, "firstIndex");
   // }
   // if (j == 1) {
   // System.out.print("Pivot based on Middle Index:\t");
   // timeSort(sampleSet, "middleIndex");
   // }
   // if (j == 2) {
   // System.out.print("Pivot based on Sampling:\t");
   // timeSort(sampleSet, "sampling");
   // }
   // }
  }


//  System.out.println();
//  System.out.println();
//  System.out.println("Pivot based on First Index:");
//  printMap(firstIndexMap);
//  System.out.println();
//  System.out.println("Pivot based on Middle Index:");
//  printMap(middleIndexMap);
//  System.out.println();
//  System.out.println("Pivot based on Sampling:");
//  printMap(samplingMap);
//  System.out.println();

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

//  if (pivotMethod == "firstIndex") {
//   firstIndexMap.put(size, totalTime);
//  } else if (pivotMethod == "middleIndex") {
//   middleIndexMap.put(size, totalTime);
//  } else {
//   samplingMap.put(size, totalTime);
//  }

  System.out.println(
    size + "\t" + totalTime / sampleSize / 10e6 + "\t" + new Timestamp(System.currentTimeMillis()));
 }
 
 private static void timeMerge(ArrayList<Integer> inputSet) {

  long totalTime = 0;
  for (int i = 0; i < sampleSize; i++) {

   ArrayList<Integer> tempSet = null;
   tempSet = new ArrayList<>();
   for (Integer value : inputSet) {
    tempSet.add(value);
   }

   long startTime = System.nanoTime();
   SortUtilBen.mergesort(inputSet, comparator);
   long endTime = System.nanoTime();
   totalTime += (endTime - startTime);
  }

//  if (pivotMethod == "firstIndex") {
//   firstIndexMap.put(size, totalTime);
//  } else if (pivotMethod == "middleIndex") {
//   middleIndexMap.put(size, totalTime);
//  } else {
//   samplingMap.put(size, totalTime);
//  }

  System.out.println(
    size + "\t" + totalTime / sampleSize / 10e6 + "\t" + new Timestamp(System.currentTimeMillis()));
 }

 // ********************************************************************************
 // ********************************************************************************

 private static void printMap(HashMap<Integer, Long> inputMap) {
  for (Map.Entry<Integer, Long> entry : inputMap.entrySet()) {
   System.out.println(entry.getKey() + "\t" + entry.getValue() / sampleSize / 10e6);

  }

 }

}
