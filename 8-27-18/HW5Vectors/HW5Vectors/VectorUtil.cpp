/*
  Author: Daniel Kopta and ??
  July 2017
  
  CS 6010 Fall 2017
  Vector util library definitions

  Complete the functions defined in this file.

  Some basic tests for your library are provided in VectorTest.cpp.
  Compile the tests together with your library using the following command:
  clang++ -std=c++11 VectorTest.cpp VectorUtil.cpp

  Most of the provided tests will fail until you have provided correct 
  implementations for the VectorUtil library functions.

  You will need to provide more thorough tests.
*/

#include "VectorUtil.h"
#include <iostream>

/*
 * Determines whether or not a vector contains a certain item.
 *
 * Parameters:
 *   input -- The vector to search
 *   lookFor -- The item to look for
 *
 * Returns:
 *   Whether or not the item is contained in the vector
 */
bool Contains(vector<int> input, int lookFor)
{
  // TODO: Fill in implementation. Do not always return false.
    for (int vectorValueInt : input) {
        if (lookFor == vectorValueInt) {
        return true;
        }
    }
  return false;
}

/*
 * Determines the minimum value in a vector.
 *
 * Assumes the vector is non-empty
 *
 * Parameters:
 *   input -- A vector of integers
 *
 * Returns:
 *   The smallest item in the vector
 */
int FindMin(vector<int> input)
{
  // TODO: Fill in implementation. Do not always return 0.
    int lowestVectorValue = input[0];
    for (int vectorValueInt : input) {
        if (vectorValueInt < lowestVectorValue) {
            lowestVectorValue = vectorValueInt;
        }
    }
    return lowestVectorValue;
}

/*
 * Determines the maximum value in a vector.
 *
 * Assumes the vector is non-empty
 *
 * Parameters:
 *   input -- A vector of integers
 *
 * Returns:
 *   The largest item in the vector
 */
int FindMax(vector<int> input)
{
  // TODO: Fill in implementation. Do not always return 0.
    int highestVectorValue = input[0];
    for (int vectorValueInt : input) {
        if (vectorValueInt > highestVectorValue) {
            highestVectorValue = vectorValueInt;
        }
    }
    return highestVectorValue;
}

/*
 * Determines the average of all values in a vector
 *
 * Assumes the vector is non-empty
 *
 * Parameters:
 *   input -- A vector of integers
 *
 * Returns:
 *   The (integer) average of all values in the vector
 */
int Average(vector<int> input)
{
  // TODO: Fill in implementation. Do not always return 0.
    int averageVectorValue = 0;
    for (int vectorValueInt : input) {
        averageVectorValue += vectorValueInt;
    }
    int denominator = input.size();
    return (averageVectorValue/denominator);
}


/*
 * Determines whether or not the items in a vector are in non-descending order
 *
 * Non-descending order is similar to ascending order, except that it allows for 
 * duplicate items to appear next to each other.
 * i.e., no item appearing at a lower index than another item is greater than that
 * other item.
 * 
 * Examples:
 *  {1, 2, 2, 15, 70} is sorted
 *  {2, 3, 0} is not sorted
 *
 * Parameters:
 *   input -- A vector of integers
 *
 * Returns:
 *   True if the vector is sorted in non-descending order, false otherwise
 *   An empty vector is considered sorted, since there are no items out of order
 *   A single-item vector is considered sorted, since there are no items out of order
 */
bool IsSorted(vector<int> input)
{
  // TODO: Fill in implementation. Do not always return false.
    if (input.size() == 1 || input.size() == 0) {
        return true;
    }
    for (int i = 1; i < input.size(); i++) {
        if (input[i] < input[i-1]) {
            return false;
        }
    }
    return true;
}
