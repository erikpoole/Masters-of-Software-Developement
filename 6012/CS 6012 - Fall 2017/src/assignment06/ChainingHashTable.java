package assignment06;

import java.util.Collection;
import java.util.LinkedList;

public class ChainingHashTable implements Set<String> {

 private LinkedList<String>[] storage;
 private HashFunctor hasher;

 @SuppressWarnings("unchecked")
 public ChainingHashTable(int capacity, HashFunctor functor) {
  storage = (LinkedList<String>[]) new LinkedList[capacity];
  for (int i = 0; i < storage.length; i++) {
   storage[i]= new LinkedList<>(); 
  }
  hasher = functor;
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
  if (contains(item)) {
   return false;
  }
  
  int hashValue = hasher.hash(item) % storage.length;
  storage[hashValue].add(item);
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
  boolean valueAdded = false;
  for (String item: items) {
   if (add(item) == true) {
    valueAdded = true;
   }
  }
  return valueAdded;
 }

 /**
  * Removes all items from this set. The set will be empty after this method call.
  */
 @Override
 public void clear() {
  // TODO Auto-generated method stub

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
  int hashValue = hasher.hash(item) % storage.length;
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
  // TODO Auto-generated method stub
  return false;
 }

 /**
  * Returns true if this set contains no items.
  */
 @Override
 public boolean isEmpty() {
  // TODO Auto-generated method stub
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
  // TODO Auto-generated method stub
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
  // TODO Auto-generated method stub
  return false;
 }

 /**
  * Returns the number of items in this set.
  */
 @Override
 public int size() {
  // TODO Auto-generated method stub
  return 0;
 }

}
