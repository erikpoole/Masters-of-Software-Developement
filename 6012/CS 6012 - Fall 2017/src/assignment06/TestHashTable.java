package assignment06;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestHashTable {
 
 BadHashFunctor badHash;
 MediocreHashFunctor mediocreHash;
 GoodHashFunctor goodHash;
 
 ChainingHashTable chainingTable;
 QuadProbeHashTable quadTable;
 ArrayList<String> testList;

 
 /**
  * Sets up empty hash tables and a list for use with .addAll() & .removeAll()
  */
 @BeforeEach
 void setUp() throws Exception {
  
  badHash = new BadHashFunctor();
  mediocreHash = new MediocreHashFunctor();
  goodHash = new GoodHashFunctor();
  
  chainingTable = new ChainingHashTable(6, goodHash);
  quadTable = new QuadProbeHashTable(6, goodHash);
  
  testList = new ArrayList<>();
  testList.add("these");
  testList.add("are");
  testList.add("included");
  
 }

 
 /**
  * Tests for appropriate output from hash functors
  */
 @Test
 void testHashFunctors() {
  
  assertTrue(badHash.hash("hi") == 2);
  //ASCII Values: h = 104, i = 105
  assertTrue(mediocreHash.hash("hi") == 209);
  //Hash arithmetic - ((21+104)*7)+105
  assertTrue(goodHash.hash("hi") == 980);
  
 }
 
 
 /**
  * Tests Chaining Hash Table functionality
  */
 @Test
 void testChainingHash() { 
  
  assertFalse(chainingTable.contains("hi"));
  assertTrue(chainingTable.add("hi"));
  assertFalse(chainingTable.add("hi"));
  assertTrue(chainingTable.contains("hi"));
  
  assertFalse(chainingTable.containsAll(testList));
  assertTrue(chainingTable.add("these"));
  assertFalse(chainingTable.containsAll(testList));
  assertTrue(chainingTable.addAll(testList));
  assertFalse(chainingTable.addAll(testList));
  assertTrue(chainingTable.containsAll(testList));
  
  assertTrue(chainingTable.size() == 4);
  assertFalse(chainingTable.isEmpty());
  chainingTable.clear();
  assertTrue(chainingTable.size() == 0);
  assertTrue(chainingTable.isEmpty());
  assertFalse(chainingTable.contains("hi"));
  
  assertTrue(chainingTable.addAll(testList));
  assertTrue(chainingTable.remove("included"));
  assertFalse(chainingTable.remove("included"));
  assertTrue(chainingTable.size() == 2);
  
  assertTrue(chainingTable.add("toast"));
  assertTrue(chainingTable.removeAll(testList));
  assertFalse(chainingTable.removeAll(testList));
  assertTrue(chainingTable.size() == 1);
  
 }
  
 
 /**
  * Tests Quadratic Probing Hash Table functionality
  */
 @Test
 void testProbing() {

  assertFalse(quadTable.contains("hi"));
  assertTrue(quadTable.add("hi"));
  assertFalse(quadTable.add("hi"));
  assertTrue(quadTable.contains("hi"));
  
  assertFalse(quadTable.containsAll(testList));
  assertTrue(quadTable.add("these"));
  assertFalse(quadTable.containsAll(testList));
  assertTrue(quadTable.addAll(testList));
  assertFalse(quadTable.addAll(testList));
  assertTrue(quadTable.containsAll(testList));
  
  assertTrue(quadTable.size() == 4);
  assertFalse(quadTable.isEmpty());
  quadTable.clear();
  assertTrue(quadTable.size() == 0);
  assertTrue(quadTable.isEmpty());
  assertFalse(quadTable.contains("hi"));
  
  assertTrue(quadTable.addAll(testList));
  assertTrue(quadTable.remove("included"));
  assertFalse(quadTable.remove("included"));
  assertTrue(quadTable.size() == 2);
  
  assertTrue(quadTable.add("toast"));
  assertTrue(quadTable.removeAll(testList));
  assertFalse(quadTable.removeAll(testList));
  assertTrue(quadTable.size() == 1);
  
 }

}
