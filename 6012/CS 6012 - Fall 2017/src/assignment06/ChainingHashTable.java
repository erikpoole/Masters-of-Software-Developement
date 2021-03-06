package assignment06;

import java.util.Collection;
import java.util.LinkedList;

public class ChainingHashTable implements Set<String> {

 private LinkedList<String>[] storage;
 private HashFunctor hasher;
 private int size;

 
 /**
  * Constructor for an empty Chaining Hash Table 
  * @param capacity - number of linked list "buckets" in the hash table
  * @param functor - hash functor to be used for sorting inputs
  */
 @SuppressWarnings("unchecked")
 public ChainingHashTable(int capacity, HashFunctor functor) {
  storage = (LinkedList<String>[]) new LinkedList[capacity];
  for (int i = 0; i < storage.length; i++) {
   storage[i] = new LinkedList<>();
  }

  hasher = functor;
  size = 0;
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
  if (item == null || contains(item)) {
   return false;
  }

  int hashValue = Math.abs(hasher.hash(item)) % storage.length;
  storage[hashValue].add(item);
  size++;
  return true;
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
  for (LinkedList<String> list : storage) {
   list.clear();
  }
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
  
  int hashValue = Math.abs(hasher.hash(item)) % storage.length;
  if (storage[hashValue].contains(item)) {
   return true;
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
  
  int hashValue = Math.abs(hasher.hash(item)) % storage.length;
  storage[hashValue].remove(item);
  size--;
  return true;
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
 
 
 /**
  * returns number of linked lists that have at least one element stored in them
  * (used for collision comparison between hash functors)
  */
 public int countFilledLists() {
  int output = 0;
  for (int i = 0; i < storage.length; i++) {
   if (!storage[i].isEmpty()) {
    output++;
   }
  }
  return output;
 }

}
