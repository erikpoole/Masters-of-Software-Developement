//
//  functions.hpp
//  HWMakeYourOwnVector
//
//  Created by Erik Poole on 9/10/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#ifndef functions_hpp
#define functions_hpp

#include <stdio.h>

struct homemadeVector {
    int* vectorPointer;
    int capacity;
    int size;
};


homemadeVector makeVector(const int& inputCapacity);
void freeVector(homemadeVector& inputVector);

void pushBack(homemadeVector& inputVector, const int& addedValue);
void popBack(homemadeVector& inputVector);

int get(const homemadeVector& inputVector, int index);
void set(homemadeVector& inputVector, const int& index, const int& newValue);

#endif /* functions_hpp */
