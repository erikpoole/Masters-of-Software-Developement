//
//  functions.cpp
//  msdAlloc
//
//  Created by Erik Poole on 3/6/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//


#include "functions.hpp"
#include <sys/mman.h>



size_t Allocater::calculateHash(void* ptr) {
        return (size_t) ptr >> 12;
    }
    
void Allocater::hashInsert(void* ptr, size_t size) {
        if (filledSlots > internalSize / 2) {
            hashGrow();
        }

        size_t location = calculateHash(ptr) % internalSize;
        int collisions = 0;
//        std::cout << location << "\n";
        while (hashMapPointer[location].first != nullptr) {
            collisions++;
            location += (collisions + collisions*collisions)/2 % internalSize;
        }

        hashMapPointer[location] = std::pair<void*, int> (ptr, size);
        filledSlots++;
    }

size_t Allocater::hashDelete(void* ptr) {
        long location = calculateHash(ptr) % internalSize;
        int collisions = 0;
        while (hashMapPointer[location].first != ptr && hashMapPointer[location].second != 0) {
            collisions++;
            location += (collisions + collisions*collisions)/2 % internalSize;
        }
        size_t size = hashMapPointer[location].second;
        hashMapPointer[location] = std::pair<void*, int> (nullptr, -1);
        filledSlots--;
        
        return size;
    }
    
void Allocater::hashGrow() {
        
        std::pair<void*, size_t>* tempPointer = hashMapPointer;
        size_t newSize = internalSize*sizeof(hashMapPointer[0])*2;
        hashMapPointer = (std::pair<void*, size_t>*) mmap(NULL, newSize, PROT_READ | PROT_WRITE | PROT_EXEC, MAP_ANON | MAP_PRIVATE, 0, 0);
        
        filledSlots = 0;
        for (int i = 0; i < internalSize; i++) {
            if (tempPointer[i].first != nullptr) {
                hashInsert(tempPointer[i].first, tempPointer[i].second);
                munmap(tempPointer[i].first, tempPointer[i].second);
            }
        }
        
        munmap(tempPointer, internalSize);
        
        internalSize *= 2;
    }

    
Allocater::Allocater() {
        hashMapPointer = (std::pair<void*, size_t>*) mmap(NULL, 4096, PROT_READ | PROT_WRITE | PROT_EXEC, MAP_ANON | MAP_PRIVATE, 0, 0);
        internalSize = 4096 / sizeof(hashMapPointer[0]);;
        filledSlots = 0;
    }
    
Allocater::~Allocater() {
        for (int i = 0; i < internalSize; i++) {
            if (hashMapPointer[i].first != nullptr) {
                munmap(hashMapPointer[i].first, 4096);
            }
        }
        
        munmap(hashMapPointer, internalSize);
    }
    
void* Allocater::allocate(size_t bytesToAllocate) {
        size_t pagesToAllocate = bytesToAllocate / 4096;
        if (bytesToAllocate % 4096 != 0) {
            pagesToAllocate++;
        }
        size_t bytesSize = pagesToAllocate * 4096;
        
        void* memory = mmap(NULL, bytesSize, PROT_READ | PROT_WRITE | PROT_EXEC, MAP_ANON | MAP_PRIVATE, 0, 0);
        hashInsert(memory, bytesSize);
        
        return memory;
    }
    
void Allocater::deallocate(void* ptr) {
        size_t bytesSize = hashDelete(ptr);
        munmap(ptr, bytesSize);
    }
