/*
 * Practical Issues of Timing Your Programs During this class you will analyze algorithms to
 * determine their performance. As part of the analysis process, you will be required to determine
 * how long it takes an algorithm to execute. Unfortunately, timing is not an exact science; there
 * are practical issues with timing that reduce its accuracy.
 * 
 * In this lab you will learn various timing techniques, as well as adequate was of expressing your
 * timing data.
 * 
 * Timing Experiments Many, many factors affect timing, including the CPU, OS, memory, and cache
 * performance, so answers will vary from machine to machine. When running your own timing code, try
 * to have as clean an environment as possible. Shut down other programs and keep the Eclipse window
 * active. We will discuss ways that you can write programs that you start and forget about so you
 * don't feel like you're wasting time with the many timing experiments we will ask of you this
 * semester.
 * 
 * Create a lab02 package for this lab. All of the example code uses this package. Also, down the
 * worksheet.rtfPreview the document file for this lab, which you will submit through canvas after
 * you are done.
 * 
 * Using time in a Java program.
 * 
 * There are two common ways to access time in Java:
 * 
 * System.currentTimeMillis(); System.nanoTime(); The millisecond call returns the number of
 * milliseconds that have elapsed since 1970 (The Unix EPOCH). For those nerds out there who are
 * worried about overflow, keep this in mind: Both of these values are longs, which means they are
 * 64-bit twos-complement values. It will take another 292 277 230 years before we have to worry
 * about running out of bits. This is useful both in timing and for time stamps. Remember, the
 * return values is an actual date, and can be used to compare against another program's time
 * stamps.
 * 
 * The nanoTime system call, however, is internal to a JVM's specific instance, and shouldn't be
 * used outside of a program's lifetime. Now, a program would have to run have to run for 292 years
 * straight before we get undefined behavior so...watch out for that. However, this is much more
 * precise a time measurement than currentTimeMillis(), and what you should use for your timing
 * experiments for this course. In theory, the nanoTime time measurements would be 1,000,000x more
 * precise than the currentMillis measurements. In practice, however, there are limitations to the
 * gain in precision. Consider that most computers can run at most three or four CPU instructions in
 * one nanosecond. Making the system call to get the nanosecond time will take hundreds of CPU
 * instructions. This means that when you get the result back from nanoTime, time has already
 * advanced hundreds of nanoseconds further.
 * 
 * The No-no's of Timing
 * 
 * As you might notice, repeatedly using print statements kills performance at a laughable rate.
 * Between starting the clock and stopping it, make sure absolutely zero print statements are found.
 * 
 * The nature of how we're timing is very general and big picture-esque. Because of that, we can't
 * just take one value and expect it to be an accurate representation of the actual time of whatever
 * algorithm / data structure is in question. Make sure that you average out your timings over tens
 * of thousands of iterations for small input sizes, and 1000 times for larger input sizes.
 * 
 * Using the millisecond system timer
 * 
 * If a program keeps track of a start time and a stop time in milliseconds, it can easily compute
 * how much time a program takes. In this part you are to determine the granularity of the
 * millisecond timer.
 * 
 * Download TimingExperiment01.javaPreview the document. The code in TimingExperiment01.java loops
 * 100 times looking for changes in the current time. If time advances in one millisecond
 * increments, then this code should complete in 100 milliseconds. Study this code until you
 * understand how it works.
 * 
 * Run the program. Did the millisecond timer advance in one millisecond steps? Is the continue
 * statement ever executed? Unfortunately, the output from the above experiment does not give this
 * information.
 * 
 * Download TimingExperiment02.javaPreview the document. This code counts how many times the
 * millisecond timer changes in one second. Again study the code and run the experiment. Is one
 * iteration of the loop more or less than one millisecond?
 * 
 * Is one second long enough to get an accurate answer? Try running the experiment again, except
 * this time change it so that it runs for ten seconds.
 * 
 * Answer the first question in the lab worksheet before continuing.
 * 
 * Using the nanosecond system timer
 * 
 * Experiments 3, 4, 5, and 6 repeat experiments 1 and 2 above, using the nanosecond timer and some
 * other variations. Download and run TimingExperiment03.javaPreview the document. Notice that it
 * appears to take a very, very long time (tens of thousands of nanoseconds) for the nanosecond
 * timer to advance. This is incorrect. There is a System.out.println statement within the loop,
 * which is where most of the time is being spent -- outputting information to the console.
 * 
 * As an improvement, consider TimingExperiment04.javaPreview the document. Instead of printing out
 * the results in the loop, the results are saved in an array and then printed after the loop.
 * Download and run this code to get much better results. Is the continue statement ever executed?
 * 
 * Download TimingExperiment05.javaPreview the document, which is similar experiment 2. The loop
 * runs for 10 seconds, counting how many times the nanosecond timer appears to change. In addition,
 * it counts how many times the loop loops. What is the difference between the nanosecond timer
 * granularity, and the loop run time granularity? It appears that the nanosecond timer is fairly
 * accurate, but it is still unclear how much time the loop takes vs. how much time the call to
 * System.nanoTime() takes.
 * 
 * Run experiment 5 several times (at least five) and then answer questions 2, 3, and 4 on the lab
 * text file.
 * 
 * Download TimingExperiment06.javaPreview the document and examine the code. It is the same timing
 * algorithm as in experiment 5, except coded to be as efficient as possible. Computations are
 * rearranged to minimize how many times they would be repeated.
 * 
 * Do the code changes pay off? Compare the results of experiment 6 with the results of experiment
 * 5. (You will need to run it several times to get consistent results.) From these results, you
 * should understand why it is good to write clear, easy to understand code. Optimizing your
 * statements does not usually save time, but choosing low complexity algorithms does save time!
 * 
 * Timing an algorithm
 * 
 * Try writing a small piece of code (less than 10 lines) to determine how long it takes to compute
 * the square root of each number from 1..10 (using a loop). Test your code. Then download, examine,
 * and run TimingExperiment07.javaPreview the document.
 * 
 * You should get huge, unrealistic numbers. This is a bad example of how to time code. From the
 * above experiments you should have seen that simply calling the nanoTime method takes a
 * significant amount of time. In addition, there are timing instabilities that appear when you
 * first run an application that can throw off the results.
 * 
 * For this kind of small experiment, it is better to repeat the experiment millions of times and
 * average the costs across the runs. In addition, it is always wise to pause briefly before
 * starting the timing. Finally, if you write a loop that loops millions of times, this will also
 * add time to your experiment that should be removed.
 * 
 * Download and examine the final experiment in TimingExperiment08.javaPreview the document, which
 * uses these techniques to get a much better time estimate for the square root code.
 * 
 * Run this experiment at least five times, and then answer the remainder of the questions in the
 * lab worksheet.
 * 
 * You are encouraged to run these experiments again on your personal machine(s) at home. Different
 * platforms have different timers with different granularities. Regardless, you should begin
 * applying the good timing techniques of experiment 8 to your work.
 * 
 * Submit your lab worksheet through canvas after filling in your answers.
 */

package lab02;

public class TimingExperiment01 {

 public static void main(String[] args) {
  long lastTime = System.currentTimeMillis();
  int advanceCount = 0;
  while (advanceCount < 100) {
   long currentTime = System.currentTimeMillis();
   if (currentTime == lastTime)
    continue;
   System.out.println("Time advanced " + (currentTime - lastTime) + " milliseconds.");
   lastTime = currentTime;
   advanceCount++;
  }
 }
}
