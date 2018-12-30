package personalProj;

public class InterviewQuestions {

 public static void main(String[] args) {

  long waitTime = System.nanoTime();
  while (waitTime + 1 > System.nanoTime()) {
  }
  
//   FizzBuzz();
//  
//  System.out.println(Fibonacci(35));
//  System.out.println(betterFibonnaci(35));
//  System.out.println(time(() -> Fibonacci(35)));
//  System.out.println(time(() -> betterFibonnaci(35)));

 }

 //************************************************************
 //************************************************************

 /*
  * write a program that prints the number from 1 -> 100 for multiples of 3 - print "fizz" instead
  * for multiples of 5 - print "buzz" instead for multiples of 3 & 5 - print "fizzbuzz" instead
  */
 private static void FizzBuzz() {
  for (int i = 1; i <= 100; i++) {
   if (i % 3 == 0 && i % 5 == 0) {
    System.out.println("FizzBuzz");
   } else if (i % 3 == 0) {
    System.out.println("Fizz");
   } else if (i % 5 == 0) {
    System.out.println("Buzz");
   } else {
    System.out.println(i);
   }
  }
 }

 //************************************************************
 //************************************************************
 
 /*
  * Find the nth fibonacci number
  * zero is the zeroth fibonacci number Should be able to prove that complexity is O( 2^N )
  */
 private static long Fibonacci(int input) {
  if (input == 0) {
   return 0;
  }
  if (input == 1) {
   return 1;
  }

  return Fibonacci(input - 1) + Fibonacci(input - 2);
 }

 /*
  * make fibonnaci more efficient
  */
 private static long[] fibonnaciArr;

 private static long betterFibonnaci(int input) {
  fibonnaciArr = new long[input + 1];
  return fibonnaciInner(input, fibonnaciArr);
 }

 private static long fibonnaciInner(int input, long[] inputArr) {
  if (fibonnaciArr[input] != 0) {
   return fibonnaciArr[input];
  }

  if (input == 0) {
   return 0;
  }
  if (input == 1) {
   return 1;
  }

  fibonnaciArr[input] = fibonnaciInner(input - 1, inputArr) + fibonnaciInner(input - 2, inputArr);
  return fibonnaciArr[input];
 }

 //************************************************************
 //************************************************************
 
/*
 * Remove duplicates from linked list (Psudocode) - 
 * 
 * W/ temporary buffer -
 * Space Complexity - N
 * Time Complexity - N (Navigation Cost)
 * 
 * arrList = {}                       - Must use hash table instead of arraylist
 * for (item : originalList) {
 * if (arrList.contains(item) {
 *  remove(item)
 *  }
 * else {
 *  add(item)
 *  }
 * 
 * W/O temporary buffer - 
 * Space Complexity - 1
 * Time Complexity - N^2 (Navigation Cost)
 * 
 * for (int i = 0; i < originalList.size; i++) {
 *  for (int j = 0; j < originalList.size; j++) {
 *    if (originalList[i] == originalList[j]) {
 *      originalList[i].remove
 *      break
 *      
 * OR
 * 
 * originalList.sort()
 * for (int i = 0; i < originalList.size; i++) {
 *  if (originalList[i] == originalList[i+1] {
 *    originalList[i].remove
 * 
 */
 
 //************************************************************
 //************************************************************
 
 /*
  * Sort a large file (40GB) with one string per line
  * 
  *  Too large to hold in ram
  *  
  *  Read portion in
  *  Quicksort
  *  Read portion out
  *  
  *  externally k(merge)
  */
 
 
 
 //************************************************************
 //************************************************************
 
 private static long time(Runnable inputMethod) {
  long startTime = System.nanoTime();
  inputMethod.run();
  long endTime = System.nanoTime();
  return endTime - startTime;
 }
 

}
