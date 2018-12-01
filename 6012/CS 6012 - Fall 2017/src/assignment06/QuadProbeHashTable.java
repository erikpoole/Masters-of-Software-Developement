package assignment06;

import java.util.Arrays;
import java.util.Collection;

public class QuadProbeHashTable implements Set<String> {

 private String[] storage;
 private boolean[] storageArtifacts;
 private HashFunctor hasher;
 private int size;

 

/**
 * Constructor that produces a hash table of the size of the nearest prime
 * number greater than the input capacity 
 * @param capacity - input size requested (actual size will often be slightly larger)
 * @param functor - hash functor for sorting inputs
 */
 public QuadProbeHashTable(int capacity, HashFunctor functor) {

  // resize table to nearest prime number above capacity
  if (capacity <= 1) {
   capacity = 2;
  }
  while (!isPrime(capacity)) {
   capacity++;
  }

  storage = new String[capacity];
  storageArtifacts = new boolean[capacity];
  hasher = functor;
  size = 0;
 }


 
/**
 * If the input number is prime returns true, else returns false 
 * @param input - number to be tested
 */
 public static boolean isPrime(int input) {

  boolean isPrime = true;
  for (int i = 2; i <= input / 2; i++) {
   if (input % i == 0) {
    isPrime = false;
    break;
   }
  }
  return isPrime;
 }



 /**
  * Ensures that this set contains the specified item.
  * 
  * @param item - the item whose presence is ensured in this set
  * @return true if this set changed as a result of this method call (that is, if the input item was
  *         actually inserted); otherwise, returns false
  */
 @Override
 public boolean add(String item) {
  // check if item is present
  if (item == null || contains(item)) {
   return false;
  }
  
  //resize and rehash if size is too large
  if (size >= storage.length / 2) {
   rehash();
  }

  // compute hash
  int hashValue = Math.abs(hasher.hash(item)) % storage.length;

  // if hash position is filled - check next position
  int quadraticIncrementor = 0;
  while (storage[(int) (hashValue + Math.pow(quadraticIncrementor, 2))] != null) {
   quadraticIncrementor++;
  }

  // add value at appropriate position
  storage[(int) (hashValue + Math.pow(quadraticIncrementor, 2))] = item;
  storageArtifacts[(int) (hashValue + Math.pow(quadraticIncrementor, 2))] = true;

  size++;
  return true;
 }

 
 
 /**
  * resizes current hash table to one of at least twice the size
  * while ensuring that the resulting size is still prime 
  */
 private void rehash() {
  int newCapacity = storage.length*2;
  while (!isPrime(newCapacity)) {
   newCapacity++;
  }
  
  String[] storageTemp = storage;
  storage  = new String[newCapacity];
  storageArtifacts = new boolean[newCapacity];
  size = 0;
  
  for (int i = 0; i < storageTemp.length; i++) {
   add(storageTemp[i]); 
  }
  
 }
 


 /**
  * Ensures that this set contains all items in the specified collection.
  * 
  * @param items - the collection of items whose presence is ensured in this set
  * @return true if this set changed as a result of this method call (that is, if any item in the
  *         input collection was actually inserted); otherwise, returns false
  */
 @Override
 public boolean addAll(Collection<? extends String> items) {
  boolean itemAdded = false;
  for (String item : items) {
   if (add(item) == true) {
    itemAdded = true;
   }
  }
  return itemAdded;
 }



 /**
  * Removes all items from this set. The set will be empty after this method call.
  */
 @Override
 public void clear() {
  for (int i = 0; i < storage.length; i++) {
   storage[i] = null;
  }
  Arrays.fill(storageArtifacts, false);
  size = 0;
 }



 /**
  * Determines if there is an item in this set that is equal to the specified item.
  * 
  * @param item - the item sought in this set
  * @return true if there is an item in this set that is equal to the input item; otherwise, returns
  *         false
  */
 @Override
 public boolean contains(String item) {
  if (item == null) {
   return false;
  }

  // compute hash
  int hashValue = Math.abs(hasher.hash(item)) % storage.length;

  // if hash position has a storage artifact - compare to item
  int quadraticIncrementor = 0;
  while (storageArtifacts[(int) (hashValue + Math.pow(quadraticIncrementor, 2))] == true) {
   if (storage[(int) (hashValue + Math.pow(quadraticIncrementor, 2))] == item) {
    return true;
   }
   quadraticIncrementor++;
  }

  return false;
 }



 /**
  * Determines if for each item in the specified collection, there is an item in this set that is
  * equal to it.
  * 
  * @param items - the collection of items sought in this set
  * @return true if for each item in the specified collection, there is an item in this set that is
  *         equal to it; otherwise, returns false
  */
 @Override
 public boolean containsAll(Collection<? extends String> items) {
  boolean allPresent = true;
  for (String item : items) {
   if (contains(item) != true) {
    allPresent = false;
   }
  }
  return allPresent;
 }



 /**
  * Returns true if this set contains no items.
  */
 @Override
 public boolean isEmpty() {
  if (size == 0) {
   return true;
  }
  return false;
 }



 /**
  * Ensures that this set does not contain the specified item.
  * 
  * @param item - the item whose absence is ensured in this set
  * @return true if this set changed as a result of this method call (that is, if the input item was
  *         actually removed); otherwise, returns false
  */
 @Override
 public boolean remove(String item) {
  if (item == null || !contains(item)) {
   return false;
  }
  // compute hash
  int hashValue = Math.abs(hasher.hash(item)) % storage.length;

  // if hash position has a storage artifact - compare to item
  int quadraticIncrementor = 0;
  while (storageArtifacts[(int) (hashValue + Math.pow(quadraticIncrementor, 2))] == true) {
   if (storage[(int) (hashValue + Math.pow(quadraticIncrementor, 2))] == item) {
    storage[(int) (hashValue + Math.pow(quadraticIncrementor, 2))] = null;
    size--;
    return true;
   }
   quadraticIncrementor++;
  }

  return false;
 }



 /**
  * Ensures that this set does not contain any of the items in the specified collection.
  * 
  * @param items - the collection of items whose absence is ensured in this set
  * @return true if this set changed as a result of this method call (that is, if any item in the
  *         input collection was actually removed); otherwise, returns false
  */
 @Override
 public boolean removeAll(Collection<? extends String> items) {
  boolean itemRemoved = false;
  for (String item : items) {
   if (remove(item) == true) {
    itemRemoved = true;
   }
  }
  return itemRemoved;
 }



 /**
  * Returns the number of items in this set.
  */
 @Override
 public int size() {
  return size;
 }

}
