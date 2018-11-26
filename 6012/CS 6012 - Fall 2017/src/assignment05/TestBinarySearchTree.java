package assignment05;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestBinarySearchTree {

 BinarySearchTree<String> testTree;
 ArrayList<String> sampleList;

 @BeforeEach
 void setUp() throws Exception {
  testTree = new BinarySearchTree<String>();
  sampleList = new ArrayList<>();
  sampleList.add("testing");
  sampleList.add("a");
  sampleList.add("collection");
 }

 @Test
 void test() {
  Assertions.assertThrows(NullPointerException.class, () -> {
   testTree.add(null);
  });
  Assertions.assertThrows(NoSuchElementException.class, () -> {
   testTree.first();
  });
  Assertions.assertThrows(NoSuchElementException.class, () -> {
   testTree.last();
  });

  assertTrue(testTree.add("hiya"));
  assertTrue(testTree.add("how"));
  assertTrue(testTree.add("are"));
  assertTrue(testTree.add("you"));
  assertTrue(testTree.size() == 4);

  assertTrue(testTree.addAll(sampleList));
  assertFalse(testTree.addAll(sampleList));
  assertTrue(testTree.size() == 7);

  assertTrue(testTree.contains("hiya"));
  assertTrue(testTree.contains("how"));
  assertTrue(testTree.contains("are"));
  assertTrue(testTree.contains("you"));
  assertFalse(testTree.contains("chicken"));

  assertTrue(testTree.containsAll(sampleList));
  sampleList.add("break");
  assertFalse(testTree.containsAll(sampleList));
  
  assertTrue(testTree.first() == "a");
  assertTrue(testTree.last() == "you");
  
  //testing removal on leaf node
  assertTrue(testTree.remove("a"));
  assertFalse(testTree.contains("a"));
  
  //testing removal on inner node with two children (Right Case)
  assertTrue(testTree.remove("hiya"));
  assertFalse(testTree.contains("hiya"));
  
  assertTrue(testTree.add("vile"));
  assertTrue(testTree.contains("vile"));
  assertTrue(testTree.size() == 6);
  
  //testing removal on inner node with two children (Left Case)
  assertTrue(testTree.remove("how"));
  assertFalse(testTree.contains("how"));
  
  //testing removal on inner node with one child
  assertTrue(testTree.remove("are"));
  assertFalse(testTree.contains("are"));
  
  assertTrue(testTree.removeAll(sampleList));
  assertFalse(testTree.removeAll(sampleList));
  assertTrue(testTree.size() == 2);
  
  testTree.clear();
  assertTrue(testTree.size() == 0);
  assertFalse(testTree.contains("vile"));
  
  assertTrue(testTree.addAll(sampleList));
  assertTrue(testTree.toArrayList().get(0) == "a");
  assertTrue(testTree.toArrayList().get(testTree.size()-1) == "testing");
  
 }

}
