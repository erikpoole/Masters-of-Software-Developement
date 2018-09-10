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
    outputVector.vectorPointer = new int[inputCapacity];
    outputVector.capacity = inputCapacity;
    outputVector.size = 0;
    
    return outputVector;
}

void freeVector(homemadeVector& inputVector) {
    delete inputVector.vectorPointer;
    inputVector.vectorPointer = nullptr;
}


///////////////////////////////////////////////////////////////////////////


void pushBack(homemadeVector& inputVector, const int& addedValue) {
    //assigns according to index "0" first - size will always be one larger than filled index
    inputVector.vectorPointer[inputVector.size] = addedValue;
    inputVector.size++;
}

void popBack(homemadeVector& inputVector) {
    inputVector.size--;
}


///////////////////////////////////////////////////////////////////////////


int get(const homemadeVector& inputVector, int index) {
    if (index > inputVector.size-1 || index < 0) {
        cout << "Invalid Index!!!" << endl;
        return 0;
    }
    return inputVector.vectorPointer[index];
}

void set(homemadeVector& inputVector, const int& index, const int& newValue) {
    if (index > inputVector.size-1 || index < 0) {
        cout << "Invalid Index!!!" << endl;
    } else {
        inputVector.vectorPointer[index] = newValue;
    }
}

