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
    homemadeVector(int inputCapacity);
    
    void freeVector();
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

#endif /* functions_hpp */
