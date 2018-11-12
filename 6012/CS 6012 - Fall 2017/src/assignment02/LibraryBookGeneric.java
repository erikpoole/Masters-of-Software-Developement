package assignment02;

import java.util.GregorianCalendar;

/**
 * Builds off of the book class and adds functionality
 * for tracking holders and dueDates.
 * Generic class, allows holder to be any consistent input
 */
public class LibraryBookGeneric<Type> extends Book {

 private Type holder;
 private GregorianCalendar dueDate;

//********************************************************************************
//********************************************************************************
 
 /**
  * Constructor for library book - sets initial holder/dueDate values to null;
  */
 public LibraryBookGeneric(long isbn, String author, String title) {
  super(isbn, author, title);

  holder = null;
  dueDate = null;
 }

//********************************************************************************
//********************************************************************************

 /**
  *  sets generic holder and dueDate to the values of the associated arguments
  */
 public void checkOut(Type newHolder, GregorianCalendar checkoutDate) {
  holder = newHolder;
  dueDate = checkoutDate;
 }

 /**
  * returns holder and dueDate values to null
  */
 public void checkIn() {
  holder = null;
  dueDate = null;
 }
 
//********************************************************************************
//********************************************************************************
 
 /**
  * Returns generic holder value
  */
 public Type getHolder() {
  return holder;
 }

 /**
  * returns dueDate value (year, month, day)
  */
 public GregorianCalendar getDueDate() {
  return dueDate;
 }
 
}
