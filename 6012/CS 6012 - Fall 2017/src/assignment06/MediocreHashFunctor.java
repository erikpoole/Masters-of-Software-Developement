package assignment06;

public class MediocreHashFunctor implements HashFunctor {

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
