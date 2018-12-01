package assignment06;

public class GoodHashFunctor implements HashFunctor {

 /*
  * Returns relatively good hash functor via a mathmatical operation based on
  * string length & character ASCII values.
  * 
  * More or less stolen from:
  * https://stackoverflow.com/questions/2624192/good-hash-function-for-strings
  */
 @Override
 public int hash(String item) {
  char[] chars = item.toCharArray();
  int output = 3;
  
  for (char c : chars) {
   output = output*7 + c;
  }
  if (output < 0) {
   output*=-1;
  }
  return output;
 }
}
