package assignment06;

public class BadHashFunctor implements HashFunctor {

 /**
  * returns relatively poor hash functor based on string length 
  */
 @Override
 public int hash(String item) {
  return item.length();
 }

}
