//
//  functions.hpp
//  msdAlloc
//
//  Created by Erik Poole on 3/6/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#ifndef functions_h
#define functions_h

#include <stdio.h>
#include <iostream>

#endif /* functions_h */

class Allocater {
    //private:
    
    //made everything public for unit testing
public:
    std::pair<void*, size_t>* hashMapPointer;
    size_t internalSize;
    size_t filledSlots;
    
    size_t calculateHash(void* ptr);
    
    void hashInsert(void* ptr, size_t size);
    size_t hashDelete(void* ptr);
    void hashGrow();
    
    
    //public:
    
    Allocater();
    ~Allocater();
    
    void* allocate(size_t bytesToAllocate);
    void deallocate(void* ptr);
    
};

void compareMallocs(int entries, int entrySize);
