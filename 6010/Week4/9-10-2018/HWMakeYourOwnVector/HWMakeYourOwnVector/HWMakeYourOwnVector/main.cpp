//
//  main.cpp
//  HWMakeYourOwnVector
//
//  Created by Erik Poole on 9/10/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>

#include "functions.hpp"

using namespace std;

int main(int argc, const char * argv[]) {

    homemadeVector testVector = makeVector(1);
    
    //demonstrates effective pushBack() & growVector()
    pushBack(testVector, 10);
    cout << "Value: " << testVector.vectorPointer[0] << endl;
    cout << "Size: " << testVector.size << endl;
    cout << "Capacity: " << testVector.capacity << endl;
    pushBack(testVector, 33);
    cout << "Value: " << testVector.vectorPointer[1] << endl;
    cout << "Size: " << testVector.size << endl;
    cout << "Capacity: " << testVector.capacity << endl;
    pushBack(testVector, -13);
    cout << "Value: " << testVector.vectorPointer[2] << endl;
    cout << "Size: " << testVector.size << endl;
    cout << "Capacity: " << testVector.capacity << endl;
    cout << endl;
    
    //demonstrates effective popBack()
    popBack(testVector);
    cout << "Size: " << testVector.size << endl;
    cout << "Capacity: " << testVector.capacity << endl;
    cout << endl;
    
    //demonstrates effective get()
    cout << "Value at index 0: " << get(testVector, 0) << endl;
    cout << "Value at index 2: " << get(testVector, 2) << endl;;
    cout << "Addition (+1) at index 0: " << get(testVector, 0) + 1 << endl;
    cout << endl;
    
    //demonstrates effective set()
    cout << "Value at index 0 before change: " << get(testVector,0) << endl;
    set(testVector, 0, -20);
    cout << "Value at index 0 after change: " << get(testVector,0) << endl;
    cout << "Changing value at index 2: ";
    set(testVector, 2, 100);
    cout << endl;
    
    delete testVector.vectorPointer;
    testVector.vectorPointer = nullptr;
    
}
