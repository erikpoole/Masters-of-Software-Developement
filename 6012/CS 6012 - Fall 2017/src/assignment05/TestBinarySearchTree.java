package assignment05;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestBinarySearchTree {

 BinarySearchTree<String> testTree;
 ArrayList<String> sampleList;

 /**
  * creates sample collection and BinarySearchTree testTree for use in testing
  */
 @BeforeEach
 void setUp() throws Exception {
  testTree = new BinarySearchTree<String>();
  sampleList = new ArrayList<>();
  sampleList.add("testing");
  sampleList.add("a");
  sampleList.add("collection");
 }

 /**
  * tests all methods of BinarySerachTree
  */
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
 
 /**
  * used to test remove method with Graphviz by producing graphs on each level with commented code in .removeAll() method
  */
 @Test
 void testRemoveAll() {
  sampleList.add("these");
  sampleList.add("are");
  sampleList.add("extra");
  sampleList.add("words");
  
  assertTrue(testTree.addAll(sampleList));
  assertTrue(testTree.removeAll(sampleList));
 }

 /**
  * verifies that testSpeller implementation was correct 
  */
 @Test
 void testSpellChecker() {
  SpellChecker testSpeller = new SpellChecker(sampleList);
  testSpeller.addToDictionary("words");
  testSpeller.removeFromDictionary("collection");
  List<String> wrongWords = testSpeller.spellCheck(new File("sampleText.txt"));
  System.out.println("Mispelled Words: " + wrongWords);
 }
 
 /**
  * Additional Testing for specific cases where root is removed with one or zero children
  */
 @Test
 void testRootRemoval() {
  assertTrue(testTree.add("test"));
  assertTrue(testTree.remove("test"));
  assertTrue(testTree.getRoot() == null);
  
  assertTrue(testTree.add("test"));
  assertTrue(testTree.add("test2"));
  assertTrue(testTree.remove("test"));
  assertTrue(testTree.getRoot().getElement() == "test2");
 }
 
 
}
