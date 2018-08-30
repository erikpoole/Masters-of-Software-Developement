/*
  Author: Daniel Kopta and ??
  July 2017
  
  CS 6010 Fall 2017
  Vector util library tests

  Compile this file together with your VectorUtil library with the following command:
  clang++ -std=c++11 VectorTest.cpp VectorUtil.cpp

  Most of the provided tests will fail until you have provided correct 
  implementations for the VectorUtil library functions.

  You will need to provide more thorough tests.
*/

#include <iostream>
#include <string>

// Include the VectorUtil library
#include "VectorUtil.h"

/*
 * Helper function for failing a test.
 * Prints a message and exits the program.
 */
void ErrorExit(std::string message)
{
  std::cerr << "Failed test: " << message << std::endl;
  exit(1);
}


int main()
{
  
  // Set up some input vectors for testing purposes.

  // We can use list initialization syntax:
  vector<int> v1 = {3, 1, 0, -1, 5};

  // Or we can repeatedly push_back items
  vector<int> v2;
  v2.push_back(1);
  v2.push_back(2);
  v2.push_back(3);

  // When testing, be sure to check boundary conditions, such as an empty vector
  vector<int> empty;
  
  
  /* 
   * Contains tests 
   */

  // v1 doesn't contain 4, so this should return false
  if(Contains(v1, 4) != false)
    ErrorExit("Contains test 1");

  // v1 does contain -1, so this should return true
  if(Contains(v1, -1) != true)
    ErrorExit("Contains test 2");

    
  /* 
   * The vector 'empty' doesn't contain anything, so this should return false
   * The specific value we're looking for here (99) is not important in this test. 
   * This test is designed to find any general errors caused by the array being empty. 
   * That type of error is unlikely to depend on the value we are looking for.
  */
  if(Contains(empty, 99) != false)
    ErrorExit("Contains empty");
  
  // TODO: Add your own tests that thoroughly exercise your VectorUtil library.

    //v2 does contain 3, so this should return true
    if(Contains(v2, 3) != true) {
        ErrorExit("Contains test 3");
    }
    
    // v1's smallest value is '-1' so this should return true
    if(FindMin(v1) != -1) {
        ErrorExit("Contains test 4");
    }
    
    // v2's largest value is '3' so this should return true
    if(FindMax(v2) != 3) {
        ErrorExit("Contains test 5");
    }
    
    //v2's average is '2' so this should return false
    if (Average(v2) == 3) {
        ErrorExit("Contains test 6");
    }
    
    //the custom array's int average is 1 so this should return true
    std::vector<int> customArray1 = {1, -5, 9};
    if (Average(customArray1) != 1) {
        ErrorExit("Contains test 7");
    }
    
    //custom array should return true
    std::vector<int> customArray2 = {-1, -1, -1};
    if (Average(customArray2) != -1) {
        ErrorExit("Contains test 8");
    }
    
    //v2 is sorted so should return true
    if (IsSorted(v2) == false) {
        ErrorExit("contains test 9");
    }
    
    //custom array is sorted so should return true
    std::vector<int> customArray3 = {-5,-5,-5,10};
    if (IsSorted(customArray3) == false) {
        ErrorExit("contains test 10");
    }
    
    //custom array is blank so should return true
    std::vector<int> customArray4 = {};
    if (IsSorted(customArray4) == false) {
        ErrorExit("contains test 11");
    }
    
    
  // Since any failed test exits the program, if we made it this far, we passed all tests.
  std::cout << "All tests passed!" << std::endl; 

}
