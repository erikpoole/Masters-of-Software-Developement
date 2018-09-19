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

/*

//CONSTRUCTOR
template<typename T>
homemadeVector::homemadeVector(int inputCapacity) {
    vectorPointer = new T[inputCapacity];
    capacity = inputCapacity;
    size = 0;
}

//COPY CONSTRUCTOR
template<typename T>
homemadeVector::homemadeVector(const homemadeVector& original) {
    vectorPointer = new T[original.capacity];
    capacity = original.capacity;
    size = original.size;
    
    for (int i = 0; i < original.size; i++) {
        vectorPointer[i] = original.vectorPointer[i];
    }
}


////Copy Constructor Test
//void testStackChangeConstructor(homemadeVector inputVector) {
//    homemadeVector newVector(inputVector);
//}


//DESTRUCTOR
template<typename T>
homemadeVector::~homemadeVector() {
    delete [] vectorPointer;
}


///////////////////////////////////////////////////////////////////////////


//Overloading '='
template<typename T>
homemadeVector& homemadeVector::operator = (const homemadeVector& rhs) {
    if (rhs.size > capacity) {
        delete [] vectorPointer;
        vectorPointer = new T[rhs.size];
    } else {
        size = rhs.size;
    }
    
    for (int i = 0; i < size; i++) {
        vectorPointer[i] = rhs.vectorPointer[i];
    }
    return *this;
}


////Overloading '=' Test
//void testStackChangeEquals(homemadeVector inputVector) {
//    homemadeVector newVector(0);
//    newVector = inputVector;
//}


///////////////////////////////////////////////////////////////////////////


template<typename T>
void homemadeVector::pushBack(T addedValue) {
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


template<typename T>
int homemadeVector::get(int index) {
    if (index >= size || index < 0) {
        cout << "Invalid Index!!! Error: ";
        return 0;
    }
    return vectorPointer[index];
}

template<typename T>
void homemadeVector::set(int index, T newValue) {
    if (index >= size || index < 0) {
        cout << "Invalid Index!!!" << endl;
    } else {
        vectorPointer[index] = newValue;
    }
}

template<typename T>
int homemadeVector::getSize() {
    return size;
}

template<typename T>
int homemadeVector::getCapacity() {
    return capacity;
}

///////////////////////////////////////////////////////////////////////////


template<typename T>
void homemadeVector::growVector() {
    int* newPointer = new int[capacity*2];

    for (int i = 0; i < capacity; i++) {
        newPointer[i] = vectorPointer[i];
    }
    capacity *= 2;
    
    delete vectorPointer;
    vectorPointer = newPointer;
}

*/
