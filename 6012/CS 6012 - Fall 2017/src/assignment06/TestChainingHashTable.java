package assignment06;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestChainingHashTable {
 
 BadHashFunctor badHash;
 MediocreHashFunctor mediocreHash;
 GoodHashFunctor goodHash;
 
 ChainingHashTable testHashTable;
 ArrayList<String> testArrayList;

 @BeforeEach
 void setUp() throws Exception {
  badHash = new BadHashFunctor();
  mediocreHash = new MediocreHashFunctor();
  goodHash = new GoodHashFunctor();
  
  testHashTable = new ChainingHashTable(1000, goodHash);
  testArrayList = new ArrayList<>();
  testArrayList.add("these");
  testArrayList.add("are");
  testArrayList.add("included");
  
 }

 @Test
 void test() {
  assertTrue(badHash.hash("hi") == 2);
  assertTrue(mediocreHash.hash("hi") == 209);
  assertTrue(goodHash.hash("hi") == 980);
  
  assertFalse(testHashTable.contains("hi"));
  assertTrue(testHashTable.add("hi"));
  assertFalse(testHashTable.add("hi"));
  assertTrue(testHashTable.contains("hi"));
  
  assertTrue(testHashTable.addAll(testArrayList));
  assertFalse(testHashTable.addAll(testArrayList));
 }
  

}
