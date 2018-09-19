//
//  main.cpp
//  HWMakeYourOwnVector
//
//  Created by Erik Poole on 9/10/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>
#include <vector>
#include <numeric>

#include "functions.hpp"

using namespace std;


bool isEven(int value) {return value % 2 == 0;}

int main(int argc, const char * argv[]) {
    
    
/////////////////////////Original int tests/////////////////////////
    
    homemadeVector<int> testVector(1);
    
    testVector.pushBack(10);
    cout << testVector.get(0) << endl;
    testVector.pushBack(33);
    cout << testVector.get(2) << endl;
    testVector.pushBack(-13);
    cout << testVector.get(2) << endl;
    cout << testVector.getSize() << endl;
    cout << testVector.getCapacity() << endl;
    cout << endl;
    
    testVector.popBack();
    cout << testVector.get(2) << endl;
    cout << testVector.getSize() << endl;
    cout << testVector.getCapacity() << endl;
    cout << endl;
    
    testVector.set(0, 15);
    cout << testVector.get(0) << endl;
    cout << endl;
    
    cout << testVector.getCapacity() << endl;
    testStackChangeConstructor<int> (testVector);   //Will cause sanitzier error w/o Copy Constructor method
    cout << testVector.getCapacity() << endl;
    
    cout << testVector.getSize() << endl;
    testStackChangeEquals<int> (testVector);        //Will cause sanitizer error w/o '=' overload method
    cout << testVector.getSize() << endl;
    
    cout << testVector.get(0) << endl;
    cout << testVector[0] << endl;                  //Testing [] overload
    
    testVector.pushBack(5);
    testVector.pushBack(10);
    homemadeVector <int>secondVector(1);
    secondVector = testVector;
    secondVector[0] = 100;                          //Testing [0] change in secondVector which had been based on first - proves no reference between them
    cout << testVector[0] << endl;
    cout << secondVector[0] << endl;
    
    
/////////////////////////New Template tests/////////////////////////
    
    homemadeVector<string> testString(1);
    
    testString.pushBack("Hello!");
    cout << testString.get(0) << endl;
    testString.pushBack("World!");
    cout << testString[1] << endl;
    testString[1] = "Squirrel!";
    cout << testString[1] << endl;
    
    homemadeVector<double> testDouble(1);
    
    testDouble.pushBack(1.5);
    cout << testDouble[0] << endl;
    testDouble.pushBack(testDouble[0]-1);
    cout << testDouble[1] << endl;
    testDouble.sortVector();                        //Testing .sortVector()
    cout << testDouble[1] << endl;
    
    /*
    homemadeVector<homemadeVector<int>> testVectorVectors(1);                       //errant hopes and dreams
    testVectorVectors[0].pushBack(3);
    cout << testVectorVectors[0][0] << endl;
    */
    
    cout << endl;
    
    
    /////////////////////////Equvialency tests/////////////////////////
    
    homemadeVector<string> testString1(1);
    testString1.pushBack("Hello");
    testString1.pushBack("My");
    testString1.pushBack("Friend");
    homemadeVector<string> testString2 = testString1;
    if (testString1 == testString2) {
        cout << "True" << endl;
    } else {cout << "False" << endl;}
    
    testString1[2] = "Enemy";
    if (testString1 != testString2) {
        cout << "True" << endl;
    } else {cout << "False" << endl;}
    
    cout << endl;
    
    homemadeVector<float> testFloat1(1);
    testFloat1.pushBack(3.9);
    //testFloat1.pushBack(5.4);
    homemadeVector<float> testFloat2 = testFloat1;
    if (testFloat1 < testFloat2) {
        cout << "True" << endl;
    } else {cout << "False" << endl;}
    
    testFloat1[0] = 1.3;
    if (testFloat1 < testFloat2) {
        cout << "True" << endl;
    } else {cout << "False" << endl;}
    
    testFloat2 = testFloat1;
    testFloat2.pushBack(0);
    if (testFloat1 < testFloat2) {
        cout << "True" << endl;
    } else {cout << "False" << endl;}
    
    cout << endl;
    
    assert(testFloat1 <= testFloat2);
    testFloat2.popBack();
    assert(testFloat1 >= testFloat2);
    testFloat1[0] = 50;
    assert(testFloat1 > testFloat2);
    testFloat1[0] = 4;
    testFloat1.pushBack(10);
    testFloat1.pushBack(3);
    testFloat1.pushBack(-3);
    testFloat1.pushBack(0);
    testFloat1.sortVector();
    assert(testFloat1[0] == -3 && testFloat1[1] == 0 && testFloat1[2] == 3 && testFloat1[3] == 4 && testFloat1[4] == 10);
    
    
    homemadeVector<char> testChar1(1), testChar2(1);
    testChar1.pushBack('a');
    testChar2.pushBack('b');
    assert(testChar1 <= testChar2);
    testChar1.pushBack('b');
    testChar2.pushBack('a');
    testChar1.sortVector();
    testChar2.sortVector();
    assert(testChar1 == testChar2);
    
    

    
    homemadeVector<std::string> sortTestString(2);
    sortTestString.pushBack("Hello My Darling");
    sortTestString.pushBack("Hello My Baby");
    sortTestString.sortVector();
    cout << sortTestString[1] << endl;
    
    
    
    
    
////////////////////New Tests based on on .begin() & .end()////////////////////
    
    homemadeVector<long> newestTestVector(3);
    newestTestVector.pushBack(3);
    newestTestVector.pushBack(4);
    newestTestVector.pushBack(10);
    
    for (long value : newestTestVector) {
        cout << value << endl;
    }
    cout << endl;

    assert(newestTestVector[0] == 3);
    assert(newestTestVector[2] == 10);
    newestTestVector[0] = 11;
    std::sort(newestTestVector.begin(), newestTestVector.end());
    assert(newestTestVector[2] == 11);
    long* min = std::min_element(newestTestVector.begin(), newestTestVector.end());
    assert(*min == 4);

    
    long total = std::accumulate(newestTestVector.begin(), newestTestVector.end(), 0);
    assert(total == 25);

    std::string totalString = std::accumulate(sortTestString.begin(), sortTestString.end(), std::string());
    cout << sortTestString[0] << endl;
    cout << sortTestString[1] << endl;
    cout << totalString << endl;
    cout << endl;
    
    
    
    
    
    total = std::count_if(newestTestVector.begin(), newestTestVector.end(), isEven);
    assert(total == 2);
    total = std::count_if(newestTestVector.begin(), newestTestVector.end(), [] (int x) { return x % 2 == 0;});
    assert(total == 2);
    isEvenS:isEvenS predicate;
    total = std::count_if(newestTestVector.begin(), newestTestVector.end(), predicate);
    assert(total == 2);
    

    newestTestVector.pushBack(15);
    total = std::count_if(newestTestVector.begin(), newestTestVector.end(), isEven);
    std::remove_if(newestTestVector.begin(), newestTestVector.end(), isEven);
    long actualSize = newestTestVector.getSize() - total;
    while (newestTestVector.getSize() > actualSize) {
        newestTestVector.popBack();
    }
    assert(newestTestVector.getSize() == 2);
    
    newestTestVector.pushBack(101);
    newestTestVector.pushBack(4);
    total = std::count_if(newestTestVector.begin(), newestTestVector.end(), [] (int x) { return x % 2 == 0;});
    std::remove_if(newestTestVector.begin(), newestTestVector.end(), [] (int x) {return x % 2 == 0;});
    actualSize = newestTestVector.getSize() - total;
    while (newestTestVector.getSize() > actualSize) {
        newestTestVector.popBack();
    }
    assert(newestTestVector.getSize() == 3);
    

    total = std::count_if(newestTestVector.begin(), newestTestVector.end(), predicate);
    std::remove_if(newestTestVector.begin(), newestTestVector.end(), predicate);
    actualSize = newestTestVector.getSize() - total;
    while (newestTestVector.getSize() > actualSize) {
        newestTestVector.popBack();
    }
    assert(newestTestVector.getSize() == 3);

    
     
    cout << endl;
    cout << "All assert tests passed" << endl;

    
}
