package assignment06;

public class MediocreHashFunctor implements HashFunctor {

 /**
  * returns a reasonable but not excellent hash functor based on the sum of all
  * character ASCII values in the string 
  */
 @Override
 public int hash(String item) {
  int count = 0;
  char[] chars = item.toCharArray();
  for (char c : chars) {
   count+= c;
  }
  return count;
 }

}
