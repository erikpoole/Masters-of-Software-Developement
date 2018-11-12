package assignment02;

import java.util.GregorianCalendar;


/**
 * Builds off of the book class and adds functionality
 * for tracking holders and dueDates
 */
public class LibraryBook extends Book {

 private String holder;
 private GregorianCalendar dueDate;

//********************************************************************************
//********************************************************************************
  
/**
 * Constructor for library book - sets initial holder/dueDate values to null;
 */
 public LibraryBook(long isbn, String author, String title) {
  super(isbn, author, title);

  holder = null;
  dueDate = null;
 }

 //********************************************************************************
 //********************************************************************************

/**
 *  sets holder and dueDate to the values of the associated arguments
 */
 public void checkOut(String newHolder, GregorianCalendar checkoutDate) {
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
  * Returns holder value
  */
 public String getHolder() {
  return holder;
 }

 /**
  * returns dueDate value (year, month, day)
  */
 public GregorianCalendar getDueDate() {
  return dueDate;
 }
 
}
