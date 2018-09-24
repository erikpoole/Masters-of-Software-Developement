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

//CONSTRUCTOR
homemadeVector::homemadeVector(int inputCapacity) {
        vectorPointer = new int[inputCapacity];
        capacity = inputCapacity;
        size = 0;
}

void homemadeVector::freeVector() {
    delete vectorPointer;
    vectorPointer = nullptr;
}


///////////////////////////////////////////////////////////////////////////


void homemadeVector::pushBack(int addedValue) {
    if (capacity <= size) {
        growVector();
    }
    //assigns according to index "0" first - size will always be one larger than filled indices
    vectorPointer[size] = addedValue;
    size++;
}

void homemadeVector::popBack() {
    size--;
}


///////////////////////////////////////////////////////////////////////////


int homemadeVector::get(int index) {
    if (index >= size || index < 0) {
        cout << "Invalid Index!!! Error: ";
        return 0;
    }
    return vectorPointer[index];
}

void homemadeVector::set(int index, int newValue) {
    if (index >= size || index < 0) {
        cout << "Invalid Index!!!" << endl;
    } else {
        vectorPointer[index] = newValue;
    }
}

int homemadeVector::getSize() {
    return size;
}

int homemadeVector::getCapacity() {
    return capacity;
}

///////////////////////////////////////////////////////////////////////////


void homemadeVector::growVector() {
    int* newPointer = new int[capacity*2];

    for (int i = 0; i < capacity; i++) {
        newPointer[i] = vectorPointer[i];
    }
    capacity *= 2;
    
    delete vectorPointer;
    vectorPointer = newPointer;
}
