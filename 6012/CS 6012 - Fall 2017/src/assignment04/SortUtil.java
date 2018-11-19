package assignment04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortUtil {

 public static <T> void quicksort(ArrayList<T> inputArr, Comparator<? super T> comparator) {

  quicksortInner(inputArr, 0, inputArr.size() - 1, comparator);
 }

 private static <T> void quicksortInner(ArrayList<T> inputArr, int leftOriginal, int rightOriginal,
   Comparator<? super T> comparator) {

  if (leftOriginal >= rightOriginal) {
   return;
  }

  int leftWorking = leftOriginal;
  int rightWorking = rightOriginal;

  // *** Pivot at first index ***
  // T pivotValue = inputArr.get(leftOriginal);

  // *** Pivot at center index ***
  // T pivotValue = inputArr.get((leftOriginal + rightOriginal) / 2);

  // *** Pivot at "best" index after sampling ***
  T pivot1 = inputArr.get(leftOriginal);
  T pivot2 = inputArr.get(rightOriginal);
  T pivot3 = inputArr.get((leftOriginal + rightOriginal) / 2);
  T pivotValue;
  if (comparator.compare(pivot1, pivot2) < 0) {
   // pivot2 > pivot1
   if (comparator.compare(pivot2, pivot3) < 0) {
    pivotValue = pivot2;
   } else if (comparator.compare(pivot1, pivot3) < 0) {
    pivotValue = pivot3;
   } else {
    pivotValue = pivot1;
   }
   // pivot1 > pivot2
  } else {
   if (comparator.compare(pivot1, pivot3) < 0) {
    pivotValue = pivot1;
   } else if (comparator.compare(pivot2, pivot3) < 0) {
    pivotValue = pivot3;
   } else {
    pivotValue = pivot2;
   }
  }

  // *** proof that "best" pivot logic is correct ***
  // System.out.println("Possible: " + pivot1 + ", " + pivot2 + ", " + pivot3);
  // System.out.println(pivotValue);


  while (leftWorking <= rightWorking) {
   if (comparator.compare(inputArr.get(leftWorking), pivotValue) < 0) {
    leftWorking++;
    continue;
   }

   if (comparator.compare(inputArr.get(rightWorking), pivotValue) > 0) {
    rightWorking--;
    continue;
   }

   Collections.swap(inputArr, leftWorking, rightWorking);
   leftWorking++;
   rightWorking--;
  }

  quicksortInner(inputArr, leftOriginal, rightWorking, comparator);
  quicksortInner(inputArr, leftWorking, rightOriginal, comparator);


 }

 // ********************************************************************************
 // ********************************************************************************

 public static ArrayList<Integer> generateBestCase(int size) {
  ArrayList<Integer> outputList = new ArrayList<>();
  for (int i = 1; i <= size; i++) {
   outputList.add(i);
  }
  return outputList;
 }

 public static ArrayList<Integer> generateAverageCase(int size) {
  ArrayList<Integer> outputList = new ArrayList<>();
  for (int i = 1; i <= size; i++) {
   outputList.add(i);
  }
  Collections.shuffle(outputList);
  return outputList;
 }

 public static ArrayList<Integer> generateWorstCase(int size) {
  ArrayList<Integer> outputList = new ArrayList<>();
  for (int i = size; i >= 1; i--) {
   outputList.add(i);
  }
  return outputList;
 }



}

