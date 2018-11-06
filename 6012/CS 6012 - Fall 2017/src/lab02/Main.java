package lab02;

public class Main {

 public static void main(String[] args) {
  
  long startTime = System.nanoTime();
  
  for (int i = 1; i <= 10; i++) {
   Math.sqrt(i);
  }
  
  long totalTime = System.nanoTime() - startTime;
  System.out.println("Total Nanoseconds Taken: " + totalTime);


 }
}
