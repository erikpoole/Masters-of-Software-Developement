/*
 * Author: Daniel Kopta and Erik Poole
 * July 2017 * September 2018

 * Testing program for your fraction class.
 * These tests are not very thorough, and you will need to add your own.
*/

// Include the student's fraction class
#include "fraction.hpp"

// Standard includes
#include <iostream>
#include <string>
// Also include math so we can use the pow and abs functions (see below)
#include <cmath>

using std::cout;
using std::cin;
using std::endl;

bool CompareDoubles(double d1, double d);


/* Helper function for writing tests that compare strings.
 * Compares expected to result, and prints an error if they don't match. 
 */
void Test(std::string message, std::string expected, std::string result)
{
  cout << "Test: " << message << endl;
  if(expected != result)
    cout << "\tFAILED, expected: \"" << expected << "\", got: \"" << result << "\"" << endl;
  else
    cout << "\tPASSED" << endl;
}

/* Helper function for writing tests that compare doubles (overloaded version of the above)
 * Compares expected to result, and prints an error if they don't match. 
 */
void Test(std::string message, double expected, double result)
{
  cout << "Test: " << message << endl;
  if(!CompareDoubles(expected, result))
    cout << "\tFAILED, expected: " << expected << ", got: " << result << endl;
  else
    cout << "\tPASSED" << endl;
}


/*
 * Helper function
 * Returns whether or not two doubles are "equivalent",
 * within a certain error bound. 
 *
 * As we know, floating point numbers are incapable of 
 * precisely representing certain values, so to compare
 * equality, we must tolerate some absolute difference.
 */
bool CompareDoubles(double d1, double d2)
{
  return std::abs(d1 - d2) < 1e-6;
}

/* 
 * Basic constructor and toString tests
 */
void TestConstructors()
{
  std::string result = "";
  fraction f1;
  result = f1.toString();
  Test("Default constructor", "0/1", result);
  
  fraction f2(1, 2);
  result = f2.toString();
  Test("Basic constructor", "1/2", result); 

  //TODO: Add your own additional tests here

    fraction f3(4800, 3);
    result = f3.toString();
    Test("Large constructor", "1600/1", result);
    
    fraction f4(7, 4900);
    result = f4.toString();
    Test("Small constructor", "1/700", result);
    
    fraction f5(999, 1000);
    result = f5.toString();
    Test("Non-Divisible constructor", "999/1000", result);
}


/*
 * Negative fraction tests
 */
void TestNegative()
{
  std::string result = "";
  fraction f1(-1, 2);
  result = f1.toString();
  Test("Negative numerator", "-1/2", result); 

  fraction f2(1, -2);
  result = f2.toString();
  Test("Negative denominator", "-1/2", result); 

  fraction f3(-1, -2);
  result = f3.toString();
  Test("Negative numerator and denominator", "1/2", result); 

  fraction f4(4, -16);
  result = f4.toString();
  Test("Negative denominator and reduce", "-1/4", result); 

  //TODO: Add your own additional tests here
    
    fraction f5(-10, -10);
    result = f5.toString();
    Test("Equal values, both negative", "1/1", result);
    
    fraction f6(0, -10);
    result = f6.toString();
    Test("Zero numerator, negative denominator", "0/1", result);
}


/*
 * Reduced fraction tests
 */
void TestReduced()
{
  std::string result = "";
  fraction f1(2, 4);
  result = f1.toString();
  Test("Reduce fraction (2/4)", "1/2", result); 

  //TODO: Add your own additional tests here
    
    fraction f2(47, -94);
    result = f2.toString();
    Test("Reduce fraction (47/-94)", "-1/2", result);
    
    fraction f3(114, 15);
    result = f3.toString();
    Test("Reduce fraction (114/15)", "38/5", result);
}


/*
 * Reciprocal tests
 */
void TestReciprocal()
{
  std::string result = "";
  fraction f1(1, 3);
  f1 = f1.reciprocal();
  result = f1.toString();
  Test("Reciprocal of simple", "3/1", result); 

  fraction f2(-1, 2);
  f2 = f2.reciprocal();
  result = f2.toString();
  Test("Reciprocal of negative", "-2/1", result); 

  fraction f3(6, 2);
  f3 = f3.reciprocal();
  result = f3.toString();
  Test("Reciprocal of non-reduced", "1/3", result); 

  //TODO: Add your own additional tests here
    
    fraction f4(1000, 999);
    f4 = f4.reciprocal();
    result = f4.toString();
    Test("Reciprocal of large", "999/1000", result);
    
    fraction f5(-6, -1);
    f5 = f5.reciprocal();
    result = f5.toString();
    Test("Reciprocal of double negative", "1/6", result);
}

/* 
 * fraction addition tests
 */
void TestPlus()
{
  std::string result = "";
  fraction f1(4, 6);
  fraction f2(3, 4);
  
  // Should result in 17/12
  fraction f3 = f1.plus(f2);
  result = f3.toString();
  Test("Addition of non-reduced", "17/12", result); 

  //TODO: Add your own additional tests here
    
    fraction f4(-1, 5);
    fraction f5(1, 5);
    
    fraction f6 = f4.plus(f5);
    result = f6.toString();
    Test("Addition of negative", "0/1", result);
    
    fraction f7(-1, 5);
    fraction f8(-1, 4);
    
    fraction f9 = f7.plus(f8);
    result = f9.toString();
    Test("Addition of double negative", "-9/20", result);
}

void TestMinus() {
    std::string result = "";
    fraction f1(4, 6);
    fraction f2(3, 4);
    fraction f3 = f1.minus(f2);
    result = f3.toString();
    Test("Subtraction of reduced", "-1/12", result);
    
    fraction f4(-1, 5);
    fraction f5(1, 5);
    fraction f6 = f4.minus(f5);
    result = f6.toString();
    Test("Subtraction of negative", "-2/5", result);
    
    fraction f7(-1, 5);
    fraction f8(-1, 4);
    fraction f9 = f7.minus(f8);
    result = f9.toString();
    Test("Subtraction of double negative", "1/20", result);
}

void TestTimes() {
    std::string result = "";
    fraction f1(4, 6);
    fraction f2(3, 4);
    fraction f3 = f1.times(f2);
    result = f3.toString();
    Test("Muliplication of reduced", "1/2", result);
    
    fraction f4(-1, 5);
    fraction f5(1, 5);
    fraction f6 = f4.times(f5);
    result = f6.toString();
    Test("Muliplication of negative", "-1/25", result);
    
    fraction f7(-1, 5);
    fraction f8(-1, 4);
    fraction f9 = f7.times(f8);
    result = f9.toString();
    Test("Muliplication of double negative", "1/20", result);
}

void TestDividedBy() {
    std::string result = "";
    fraction f1(4, 6);
    fraction f2(3, 4);
    fraction f3 = f1.dividedBy(f2);
    result = f3.toString();
    Test("Division of reduced", "8/9", result);
    
    fraction f4(-1, 5);
    fraction f5(1, 5);
    fraction f6 = f4.dividedBy(f5);
    result = f6.toString();
    Test("Division of negative", "-1/1", result);
    
    fraction f7(-1, 5);
    fraction f8(-1, 4);
    fraction f9 = f7.dividedBy(f8);
    result = f9.toString();
    Test("Division of double negative", "4/5", result);
}

void TestToDouble()
{
  fraction f1(1, 2);
  double result = f1.toDouble();
  Test("toDouble (1/2)", 0.5, result);

  fraction f2(1, 3);
  result = f2.toDouble();
  Test("toDouble (1/3)", 1.0/3.0, result);
}

/*
 * Approximates pi using a summation of fractions.
 */
double ComputePi()
{
  fraction sum;

  // We will only compute the first 4 terms
  // Note that even with long (64-bit) numbers, 
  // the intermediate numbers required for fraction addition 
  // become too large to represent if we go above k=4.
  for(long k = 0; k < 4; k++)
  {
    fraction multiplier(1, pow(16, k));
    fraction firstTerm(4, 8*k + 1);
    fraction secondTerm(2, 8*k + 4);
    fraction thirdTerm(1, 8*k + 5);
    fraction fourthTerm(1, 8*k + 6);

    fraction temp = firstTerm.minus(secondTerm);
    temp = temp.minus(thirdTerm);
    temp = temp.minus(fourthTerm);
 
    fraction product = multiplier.times(temp);

    sum = sum.plus(product);
  }
  
  return sum.toDouble();
}

int main()
{
  // Break up the tests into categories for better readability.
  TestConstructors();
  TestNegative();
  TestReduced();
  TestReciprocal();
  TestToDouble();
  TestPlus();


  Test("Approximating pi", 3.141592, ComputePi());

  // TODO: Add your own additional tests

    TestMinus();
    TestTimes();
    TestDividedBy();
    
}

