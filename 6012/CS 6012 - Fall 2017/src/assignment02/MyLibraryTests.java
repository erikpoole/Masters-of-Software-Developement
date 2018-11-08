package assignment02;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyLibraryTests {

 LibraryGeneric<String> testLibrary; 
 LibraryGeneric<PhoneNumber> phoneLibrary;
 
 @BeforeEach
 public void setup() throws Exception {
  testLibrary = new LibraryGeneric<>();
  testLibrary.addAll("Mushroom_Publishing.txt");
  phoneLibrary = new LibraryGeneric<>();
  phoneLibrary.addAll("Mushroom_Publishing.txt");
 }

 @Test
 void testGetOrderedByAuthor() {
  ArrayList<LibraryBookGeneric<PhoneNumber>> sortedLibrary = phoneLibrary.getOrderedByAuthor();
     
  assertTrue("Alan Burt Akers".equals(sortedLibrary.get(0).getAuthor()));
  assertFalse("Alan Burt".equals(sortedLibrary.get(0).getAuthor()));
  
  assertTrue("Whistler".equals(sortedLibrary.get(sortedLibrary.size()-2).getTitle()));
  assertTrue("The Call of the Sword".equals(sortedLibrary.get(sortedLibrary.size()-3).getTitle()));
  assertFalse("The Call".equals(sortedLibrary.get(sortedLibrary.size()-3).getTitle()));
 }
 
 @Test
 void testGetOverdueList() {
  ArrayList<LibraryBookGeneric<String>> overdueList = testLibrary.getOverdueList(1, 1, 2010);

  assertTrue(overdueList.size() == 0);
  
  
  testLibrary.checkout(9781843192701L, "me", 1, 1, 2000);
  testLibrary.checkout(9781843190349L, "me", 1, 1, 2001);
  
  overdueList = testLibrary.getOverdueList(1, 1, 2010);
  assertTrue(overdueList.size() == 2);
  assertTrue(overdueList.get(1).getIsbn() == 9781843190349L);
  
  
  testLibrary.checkin(9781843190349L);
  
  overdueList = testLibrary.getOverdueList(1, 1, 2010);
  assertTrue(overdueList.size() == 1);
  assertTrue(overdueList.get(0).getIsbn() == 9781843192701L);
  assertFalse(overdueList.get(0).getAuthor() == "NotTheTitle");
  
  
 }

}
