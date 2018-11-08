package assignment02;

import java.util.GregorianCalendar;

public class LibraryBookGeneric<Type> extends Book {

 private Type holder;
 private GregorianCalendar dueDate;

 public LibraryBookGeneric(long isbn, String author, String title) {
  super(isbn, author, title);

  holder = null;
  dueDate = null;
 }

 public Type getHolder() {
  return holder;
 }

 public GregorianCalendar getDueDate() {
  return dueDate;
 }

 // currently dueDate = checkoutDate 
 public void checkOut(Type newHolder, GregorianCalendar checkoutDate) {
  holder = newHolder;
  dueDate = checkoutDate;
 }

 public void checkIn() {
  holder = null;
  dueDate = null;
 }

}
