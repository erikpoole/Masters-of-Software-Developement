package personalProj;

public class Recursion {
 
 public static void main(String[] args) {
  System.out.println(modulo(100, 99));
  System.out.println(modulo(10, 4));
  System.out.println(modulo(100, 1));
  
  System.out.println();
  
  System.out.println(fibonnaci(4));
 }

 private static int modulo(int numerator, int denominator) {
  if (numerator < denominator) {
   return numerator;
  }
  return modulo(numerator - denominator, denominator);
 }
 
 private static int fibonnaci(int input) {
  if (input == 1) {
   return 0;
  }
  if (input == 2) {
   return 1;
  }

  //why 1+ x + y?
return 1+fibonnaci(input-1) + fibonnaci(input-2);
 }
}
