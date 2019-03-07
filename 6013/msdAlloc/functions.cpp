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
    size_t collisions = 0;
    while (hashMapPointer[location].first != nullptr) {
        collisions++;
        location = (location + (collisions + collisions*collisions)/2) % internalSize;
    }
    
    hashMapPointer[location] = std::pair<void*, size_t> (ptr, size);
    filledSlots++;
}

size_t Allocater::hashDelete(void* ptr) {
    size_t location = calculateHash(ptr) % internalSize;
    size_t collisions = 0;
    size_t numHops = 0;
    while (hashMapPointer[location].first != ptr || hashMapPointer[location].second == -1) {
        collisions++;
        location = (location + (collisions + collisions*collisions)/2) % internalSize;
        
        numHops++;
        if (numHops > internalSize) {
            break;
        }
    }
    
    if (numHops > internalSize) {
        location = calculateHash(ptr) % internalSize;
        std:: cout << location << "\n";
        std::cout << "Wrapped\n";
        return -1;
    }
    
    size_t size = hashMapPointer[location].second;
    hashMapPointer[location] = std::pair<void*, size_t> (nullptr, -1);
    filledSlots--;
    
    return size;
}

void Allocater::hashGrow() {
    std::pair<void*, size_t>* tempPointer = hashMapPointer;
    size_t tempSize = internalSize;
    size_t newSize = internalSize*sizeof(hashMapPointer[0])*2;
    
    hashMapPointer = (std::pair<void*, size_t>*) mmap(NULL, newSize, PROT_READ | PROT_WRITE | PROT_EXEC, MAP_ANON | MAP_PRIVATE, 0, 0);
    
    filledSlots = 0;
    internalSize *= 2;
    for (int i = 0; i < tempSize; i++) {
        if (tempPointer[i].first != nullptr) {
            hashInsert(tempPointer[i].first, tempPointer[i].second);
        }
    }
    
    munmap(tempPointer, tempSize);

}


Allocater::Allocater() {
    hashMapPointer = (std::pair<void*, size_t>*) mmap(NULL, 4096, PROT_READ | PROT_WRITE | PROT_EXEC, MAP_ANON | MAP_PRIVATE, 0, 0);
    internalSize = 4096 / sizeof(hashMapPointer[0]);;
    filledSlots = 0;
}

Allocater::~Allocater() {
    for (int i = 0; i < internalSize; i++) {
        if (hashMapPointer[i].first != nullptr) {
            munmap(hashMapPointer[i].first, hashMapPointer[i].second);
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
    if (bytesSize >= 0) {
        munmap(ptr, bytesSize);
    }
    
}


//***************************************************************************
//***************************************************************************


void compareMallocs(int entries, int entrySize) {
    void* pointers[entries];
    Allocater allocater = Allocater();
    
    std::cout.precision(1);
    std::cout << std::scientific;
    std::cout << "Number of Entries: " << (float) entries << " // Size of Entries: " << (float) entrySize << ":\n";
    
    //timing MSDalloc
    auto timeStart = std::chrono::high_resolution_clock::now();
    for (int i = 0; i < entries; i++) {
        pointers[i] = allocater.allocate(entrySize);
    }
    for (int i = 0; i < entries; i++) {
        allocater.deallocate(pointers[i]);
    }
    auto timeEnd = std::chrono::high_resolution_clock::now();

    std::cout << "MSDalloc Allocate/Deallocate time: " << std::chrono::duration_cast<std::chrono::milliseconds>(timeEnd - timeStart).count() << "ms \n";
    
    
    //timing Malloc
    timeStart = std::chrono::high_resolution_clock::now();
    for (int i = 0; i < entries; i++) {
        pointers[i] = malloc(entrySize);
    }
    for (int i = 0; i < entries; i++) {
        free(pointers[i]);
    }
    timeEnd = std::chrono::high_resolution_clock::now();
    
    std::cout << "Malloc/Free time: " << std::chrono::duration_cast<std::chrono::milliseconds>(timeEnd - timeStart).count() << "ms \n";
    std::cout << "\n";
}
