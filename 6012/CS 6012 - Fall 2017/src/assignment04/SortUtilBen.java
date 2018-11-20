package assignment04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortUtilBen {
  private static int threshold = 100;

  /**
   * This method performs a mergesort on the generic ArrayList given as input.
   * For the mergesort algorithm, see the class notes and/or the textbook. Your mergesort implementation must switch over to insertion sort when the size of the sublist to be sorted meets a certain threshold (i.e., becomes small enough). Make this threshold value a private static variable that you can easily change. You will perform experiments to determine which threshold value works best (see the Analysis Document).
   * Don't forget to include the insertion sort in the program files you submit.
   * @param arrayList
   * @param comparator
   * @param <T>
   */
  //Ben
  public static <T> void mergesort(ArrayList<T> arrayList, Comparator<? super T> comparator) {

    // arrays of size 1 are already sorted
    if (arrayList.size() == 1) {
      return;
    }

    //pre-allocate a temporary list for merge space once in the driver method
    ArrayList<T> tempList = new ArrayList<>();
    for(int i = 0; i < arrayList.size(); i++){
      tempList.add(null);
    }

    split(arrayList, tempList, comparator, 0, arrayList.size() - 1);
  }

  private static <T> void split(ArrayList<T> list, ArrayList<T> temp, Comparator<? super T> comparator, int start, int end) {
    if (start < end) {
      int mid = start + (end - start) / 2;

      if (end - start < threshold) {
        SortUtilBen.insertionSortMerge(list, start, mid, comparator);
        SortUtilBen.insertionSortMerge(list, mid + 1, end, comparator);
      } else {
        split(list, temp, comparator, start, mid);
        split(list, temp, comparator, mid + 1, end);
      }
      merge(list, temp, comparator, start, mid, end);
    }
  }

  private static <T> void insertionSortMerge(ArrayList<T> toBeSorted, int L, int R, Comparator<? super T> comparator) {

    for (int i = R; i > L; i--) {
      if (comparator.compare(toBeSorted.get(i), toBeSorted.get(i - 1)) < 0) {
        swapGeneric(toBeSorted, i, i - 1);
      }
    }

    for (int i = L + 2; i <= R; i++) {
      T temp = toBeSorted.get(i);
      int j = i;
      while (comparator.compare(temp, toBeSorted.get(j - 1)) < 0) {
        toBeSorted.set(j, toBeSorted.get(j - 1));
        j--;
      }
      toBeSorted.set(j, temp);
    }
  }

  private static <T> void swapGeneric(ArrayList<T> arrayList, int i, int j){
    T temp = arrayList.get(i);
    arrayList.set(i,arrayList.get(j));
    arrayList.set(j,temp);
  }


  private static <T> void merge(ArrayList<T> list, ArrayList<T> temp, Comparator<? super T> comparator, int start, int mid, int end) {

    for(int i = start; i <= end; i++) {
      temp.set(i,list.get(i));
    }

    int i = start;
    int j = mid + 1;
    int k = start;
      while(i <= mid && j <= end) {
      if(comparator.compare(temp.get(i),temp.get(j)) < 0){
        list.set(k, temp.get(i));
        i++;
      } else {
        list.set(k, temp.get(j));
        j++;
      }
      k++;
    }

    while(i <= mid){
      list.set(k, temp.get(i));
      i++;
      k++;
    }

    while(j <= end){
      list.set(k, temp.get(j));
      j++;
      k++;
    }
  }

  /**
   * This method performs a quicksort on the generic ArrayList given as input.
   * For the quicksort algorithm, see the class notes and/or the textbook. You must implement three different strategies for determining the pivot. Your quicksort implementation should be able to easily switch among these strategies. (Consider using a few private helper methods for your different pivot selection strategies.) You will perform experiments to determine which pivot strategy works best (see the Analysis Document). Your quicksort may also switch to insertion sort on some small threshold if you wish.
   * In designing a strategy for choosing a pivot, keep in mind that its running time affects the overall running time of the quicksort.
   * @param arrayList
   * @param comparator
   * @param <T>
   */
  //Erik
  public static <T> void quicksort(ArrayList<T> arrayList, Comparator<? super T> comparator){

  }

  /**
   * This method generates and returns an ArrayList of integers 1 to size in ascending order.
   * @param size
   * @return
   */
  public static ArrayList<Integer> generateBestCase(int size){
    ArrayList<Integer> outputList = new ArrayList<>();
    for (int i = 1; i <= size; i++) {
      outputList.add(i);
    }
    return outputList;  }

  /**
   * This method generates and returns an ArrayList of integers 1 to size in permuted order (i,e., randomly ordered). I will show you in class how to permute a list.
   * @param size
   * @return
   */
  public static ArrayList<Integer> generateAverageCase(int size){
    ArrayList<Integer> outputList = new ArrayList<>();
    for (int i = 1; i <= size; i++) {
      outputList.add(i);
    }
    Collections.shuffle(outputList);
    return outputList;
  }

  /**
   * This method generates and returns an ArrayList of integers 1 to size in descending order.
   * @param size
   * @return
   */
   public static ArrayList<Integer> generateWorstCase(int size){
     ArrayList<Integer> outputList = new ArrayList<>();
     for (int i = size; i >= 1; i--) {
       outputList.add(i);
     }
     return outputList;
  }


}
