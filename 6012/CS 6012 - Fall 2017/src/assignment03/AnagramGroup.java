package assignment03;

import java.util.ArrayList;

/*
 * Not necessary for assignment
 */
public class AnagramGroup {
 
 public ArrayList<String> words;
 public String sortedWord;
 public int size;
 
 public AnagramGroup(String firstWord) {
  words = new ArrayList<>();
  words.add(firstWord);
  sortedWord = AnagramUtil.sort(firstWord);
  size = 1;
 }
 
 public void addWord(String inputWord) {
  words.add(inputWord);
  size++;
 }
 
 public String[] getArray() {
  return words.toArray(new String[words.size()]);
 }

}
