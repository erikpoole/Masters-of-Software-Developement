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



class homemadeVector {
public:
    homemadeVector(int inputCapacity);                          //constructor from input e.g. homemadeVector name(3);
    homemadeVector(const homemadeVector& original);             //constructor from copy e.g. homemadeVector vector1(vector2)
    ~homemadeVector();                                          //destructor
    
    homemadeVector& operator = (const homemadeVector& rhs);
    int operator [] (int index) const {return vectorPointer[index];}        //short and not defined in .cpp
    int& operator [] (int index) {return vectorPointer[index];}             //short and not defined in .cpp
    
    void pushBack(int addedValue);
    void popBack();
    int get(int index);
    void set(int index, int newValue);
    int getSize();
    int getCapacity();
    
private:
    int* vectorPointer;
    int capacity;
    int size;
    
    
    
    void growVector();
};

void testStackChangeConstructor(homemadeVector inputVector);
void testStackChangeEquals(homemadeVector inputVector);

#endif /* functions_hpp */
