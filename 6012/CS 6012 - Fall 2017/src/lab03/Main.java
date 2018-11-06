package lab03;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Main {

 public static void main(String[] args) {

  HashMap<Integer, Integer> sampleHash;
  int numDoubles = 11;
  int sampleSize = 10000;
  
  long initialWait = System.nanoTime();
  while (System.nanoTime() < initialWait + 1e9)
  
  try {
   FileWriter writer = new FileWriter(new File("HashMap Results"));
   //writer.write("Size\tTime\n");

   int size = 512;
   for (int i = 0; i < numDoubles; i++) {
    sampleHash = new HashMap<>();
    size *= 2;

    for (int j = 0; j < size; j++) {
     sampleHash.put(j, j);
    }
    
    long totalTime = 0;
    for (int j = 0; j < sampleSize; j++) {
     long startTime = System.nanoTime();
     sampleHash.get(500); // arbitrary number inside set
     long endTime = System.nanoTime();
     totalTime += (endTime - startTime);
    }
    
    writer.write(size + "\t" + totalTime/sampleSize + "\n");
   }
   
   writer.close();
   
  } catch (IOException e) {
   e.printStackTrace();
  }

  Charter charter = new Charter();
  charter.createChart("HashMap Results", "chart.png");
 }
}
