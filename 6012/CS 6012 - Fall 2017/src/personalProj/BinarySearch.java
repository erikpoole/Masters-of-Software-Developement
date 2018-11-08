package personalProj;

public class BinarySearch {

 public static void main(String[] args) {
  
  int[] bigArr = createArray(1000000);
  
  long bufferTime = System.nanoTime();
  while (bufferTime + 1e9 > System.nanoTime()) {
  }

  System.out.println("Worst Case:");
  linearSearch(bigArr, bigArr.length -1);
  binarySearch(bigArr, bigArr.length -1);
  
  System.out.println("Best Case:");
  linearSearch(bigArr, 0);
  binarySearch(bigArr, bigArr.length/2);


 }

 
 //****************************************************************************************************
 //****************************************************************************************************
 
 private static int[] createArray(int inputSize) {
  int[] arr = new int[inputSize];
  for (int i = 0; i < arr.length; i++) {
   arr[i] = i;
  }
  return arr;
 }

 
 //****************************************************************************************************
 //****************************************************************************************************
 
 private static void linearSearch(int[] inputArray, int goal) {
  long startTime = System.nanoTime();
  
  for (int i = 0; i < inputArray.length; i++) {
   if (inputArray[i] == goal) {
    break;
   }
  }
  
  long endtime = System.nanoTime();
  System.out.println("Linear search time with input size " + inputArray.length + ": " + (endtime-startTime));
 }
 
 
 //will fail if goal doesn't exist in inputArray
 private static void binarySearch(int[] inputArray, int goal) {
  long startTime = System.nanoTime();
  
  int currentLoc = inputArray.length / 2;
  int changeValue = inputArray.length / 2;

  while (inputArray[currentLoc] != goal) {
   changeValue /= 2;
   if (changeValue < 1) {
    changeValue = 1;
   }

   if (inputArray[currentLoc] < goal) {
    currentLoc += changeValue;
   } else {
    currentLoc -= changeValue;
   }
  }
  
  long endtime = System.nanoTime();
  System.out.println("Binary search time with input size " + inputArray.length + ": " + (endtime-startTime));
 }

}
