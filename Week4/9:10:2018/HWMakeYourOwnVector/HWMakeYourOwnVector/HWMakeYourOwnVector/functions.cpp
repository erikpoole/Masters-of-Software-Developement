//
//  functions.cpp
//  HWMakeYourOwnVector
//
//  Created by Erik Poole on 9/10/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>

#include "functions.hpp"

using namespace std;


homemadeVector makeVector(const int& inputCapacity) {
    homemadeVector outputVector;
    if (inputCapacity <= 0) {
        cout << "Invalid input in \"makeVector\" function call" << endl;
        //from: www.cplusplus.com/reference/cstdlib/exit/
        exit(-1);
    } else {
        outputVector.vectorPointer = new int[inputCapacity];
        outputVector.capacity = inputCapacity;
        outputVector.size = 0;
    }
    return outputVector;
}

void freeVector(homemadeVector& inputVector) {
    delete inputVector.vectorPointer;
    inputVector.vectorPointer = nullptr;
}


///////////////////////////////////////////////////////////////////////////


void pushBack(homemadeVector& inputVector, const int& addedValue) {
    if (inputVector.capacity <= inputVector.size) {
        inputVector = growVector(inputVector);
    }
    //assigns according to index "0" first - size will always be one larger than filled indices
    inputVector.vectorPointer[inputVector.size] = addedValue;
    inputVector.size++;
}

void popBack(homemadeVector& inputVector) {
    inputVector.size--;
}


///////////////////////////////////////////////////////////////////////////


int get(const homemadeVector& inputVector, int index) {
    if (index >= inputVector.size || index < 0) {
        cout << "Invalid Index!!! Error: ";
        return 0;
    }
    return inputVector.vectorPointer[index];
}

void set(homemadeVector& inputVector, const int& index, const int& newValue) {
    if (index >= inputVector.size || index < 0) {
        cout << "Invalid Index!!!" << endl;
    } else {
        inputVector.vectorPointer[index] = newValue;
    }
}


///////////////////////////////////////////////////////////////////////////


homemadeVector growVector(homemadeVector& inputVector) {
    homemadeVector newVector;
    newVector.vectorPointer = new int[inputVector.size*2];
    newVector.capacity = inputVector.size*2;
    newVector.size = inputVector.size;
    
    for (int i = 0; i < inputVector.size; i++) {
        newVector.vectorPointer[i] = inputVector.vectorPointer[i];
    }
    delete inputVector.vectorPointer;
    inputVector.vectorPointer = nullptr;
    
    return newVector;
}
