package assignment02;

import java.util.GregorianCalendar;

public class LibraryBook extends Book {

 private String holder;
 private GregorianCalendar dueDate;

 public LibraryBook(long isbn, String author, String title) {
  super(isbn, author, title);

  holder = null;
  dueDate = null;
 }

 public String getHolder() {
  return holder;
 }

 public GregorianCalendar getDueDate() {
  return dueDate;
 }

 // currently dueDate = checkoutDate TODO
 public void checkOut(String newHolder, GregorianCalendar checkoutDate) {
  holder = newHolder;
  dueDate = checkoutDate;
 }

 public void checkIn() {
  holder = null;
  dueDate = null;
 }

}
