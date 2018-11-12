package personalProj;

public class ONotation {

 public static void main(String[] args) {

  for (int n = 0; n < 100; n++) {
   int count = 0;

   for (int i = 0; i < (n / 2); i++) {
    for (int j = n; j > (i + 1); j--) {
     count++;
    }
   }
   
   //WRONG!!!
   double formOut = (Math.pow(n, 2) + n)/4;
   
   System.out.println("N: " + n + " Output: " + count + " Function Out: " + formOut);
  }
 }

 
 
 
}
