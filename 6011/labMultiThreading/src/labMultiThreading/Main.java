package labMultiThreading;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws InterruptedException {

//		In this lab we'll do some basic multithreading using java.
//		
//
//		To start with we'll just write "hello" many, many times from various threads.
//		Create a static function in your main class called sayHello.
//
//		Within that method:
//		create an array of threads (start with 10 or so)
//		Set each thread to run a function that counts to 100, printing ("hello number ___ from thread number ___") 
//		for each number (you can use Thread.currentThread().getID() to get the thread's number).
//		Start all the threads in your array
//		Join all the threads in your array
//		What happens?  Do all the threads run in order?
//		Modify your thread function slightly to use print rather than println.  Print a newline every 10th hello.  
//		This should make the interaction between threads more obvious.
//
//
//		Now we'll write a program that shows the dangers of multithreading.  
//		We have to jump through a few hoops for Java to even allow us to do this (for this particularly small example.  
//		Real code wouldn't appear quite as ridiculous).
//
//
//		We're going to use many threads to help us add up the numbers from 1 to 40000 quickly.  Use the following steps:
//		First, create a static int variable in your class named answer.  All the threads will add numbers to this variable.
//		Second, create a static function badSum.
//
//		In that function:
//		set answer to 0
//		set a variable maxValue to 40000
//		create an array of threads (start with 1 thread in your array)
//		For each thread, i 
//		that thread should add the numbers from i*maxValue/numThreads to Math.min((i+1)*maxValue/numThreads, maxValue).  
//	
//		
//		The minimum is necessary in case maxNum isn't evenly divisible by the number of threads.  
//		Note, java gets skittish here and won't let you write that code exactly.  
//		You'll have to store i in a final int variable and use that variable inside your thread's run() method.
//		start the threads
//		join the threads
//		print the computed answer along with the correct answer, 
//		computed using a clever formula that the mathemetician Euler gave us:  (maxNum*(maxNum -1)/2).  
//		If you get different answers, try using only 1 thread to verify that your algorithm is "correct".  
//		Once you get the same answer using 1 thread + Euler's formula, use multiple threads.  
//		What's happening?  Why do the results seem random?

		
		sayHello();
//		System.out.println("Main Finished");
		badSum();

	}

	public static void sayHello() throws InterruptedException {
		ArrayList<Thread> threadList = new ArrayList<Thread>();

		for (int i = 0; i < 10; i++) {
			threadList.add(new Thread(new Runnable() {

				@Override
				public void run() {
					for (int i = 1; i <= 100; i++) {
						System.out
								.println("Hello number " + i + " from thread number " + Thread.currentThread().getId());
						if (i % 10 == 0) {
							System.out.println();
						}
					}
				}
			}));
		}

		for (Thread thread : threadList) {
			thread.start();
		}

		for (Thread thread : threadList) {
			thread.join();
		}
	}



	public static int answer;

	public static void badSum() throws InterruptedException {
		answer = 0;
		int maxValue = 40000;

		ArrayList<Thread> threadList = new ArrayList<Thread>();
		for (int i = 0; i < 3; i++) {
			int finalI = i;
			threadList.add(new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = finalI * maxValue / threadList.size(); j < Math
							.min((finalI + 1) * maxValue / threadList.size(), maxValue); j++) {
						//+= involves a reference step and then an addition step then an update step, different threads
						// might interract with different references
						answer += j;
					}

				}
			}));
		}
		
		for (Thread thread : threadList) {
			thread.start();
		}
		
		for (Thread thread : threadList) {
			thread.join();
		}

		System.out.println("Calculated Value: " + answer);
		System.out.println("Actual Value: " + maxValue*(maxValue-1)/2);
	}

}
