//
//  main.cpp
//  msdAlloc
//
//  Created by Erik Poole on 3/1/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

/*
 In this assignment, we'll practice using the system calls for allocating/deallocating virtual memory from the OS to implement the library functions malloc() and free()
 
 Our allocator will be pretty "dumb" and will probably be about 10x slower than the standard library malloc, and waste lots of memory... but it should work as a drop in replacement!
 
 When a user calls malloc, we'll allocate memory using the mmap system call, which deals in units of pages. We'll forward calls to free to munmap. Unfortunately, free doesn't take the allocation size as a parameter, so we'll store allocation sizes in a hash table that fits into some memory that we'll mmap.
 
 Overview
 For this assignment you'll write a class that has 2 public methods: void* allocate(size_t bytesToAllocate) and void deallocate(void* ptr). that are near drop-in replacements for malloc and free.
 
 In addition, my implementation had the following other methods:
 
 Constructor/Destructor
 hash table insert, delete, grow
 The allocate function should essentially
 
 round up the allocation size to the next multiple of the page size
 allocate memory with mmap
 insert the returned pointer and the allocation size in the hash table
 The deallocate function should do the inverse
 
 I recommend using a probing based hash table rather than a bucketed one so that you only need 1 memory allocation to store the table. This requires deciding on "never used" and "tombstone" (deleted entry) values so that you can tell when to stop probing.
 
 A remakably good hash function for pointers is x >> VirtualAddressOffsetSizeInBits. Think about why this is reasonable.
 
 Requirements
 Include unit testing showing that your can correctly allocate and deallocate memory (allocations shouldn't overlap, your allocator shouldn't crash)
 
 Include some simple microbenchmarks to compare your allocator vs the builtin malloc. Mine was about 10x slower :(
 
 Include instructions for how to run your tests + benchmarks
 
 Bonus
 Our allocator has a ton of internal fragmentation since very few calls to allocate will ask for multiples of the page size. This leads to lots of memory waste (if we allocate 4096 1 byte bits of memory, we'll use 4096 times as much memory as we really need!).
 
 For a bonus, develop an improved allocator that reduces this fragmentation. Test and benchmark this allocator to make sure it works correctly and is actually faster than the naive version described above.
 */


#include <iostream>
#include <sys/mman.h>
#include <vector>

class Allocater {
private:
    
    std::pair<void*, size_t>* hashMapPointer;
    size_t internalSize;
    size_t filledSlots;
    
    
    size_t calculateHash(void* ptr) {
        return (size_t) ptr >> 12;
    }
    
    void hashInsert(void* ptr, size_t size) {
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
    
    size_t hashDelete(void* ptr) {
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
    
    void hashGrow() {
        
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
    
    
public:
    
    Allocater() {
        hashMapPointer = (std::pair<void*, size_t>*) mmap(NULL, 4096, PROT_READ | PROT_WRITE | PROT_EXEC, MAP_ANON | MAP_PRIVATE, 0, 0);
        internalSize = 4096 / sizeof(hashMapPointer[0]);;
        filledSlots = 0;
        
        //        std::cout << internalSize << "\n";
        //        for (int i = 0; i < internalSize; i++) {
        //            std::cout << hashMapPointer[i].first << "\n";
        //            std::cout << hashMapPointer[i].second << "\n";
        //        }
    }
    
    ~Allocater() {
        for (int i = 0; i < internalSize; i++) {
            if (hashMapPointer[i].first != nullptr) {
                munmap(hashMapPointer[i].first, 4096);
            }
        }
        
        munmap(hashMapPointer, internalSize);
    }
    
    void* allocate(size_t bytesToAllocate) {
        size_t pagesToAllocate = bytesToAllocate / 4096;
        if (bytesToAllocate % 4096 != 0) {
            pagesToAllocate++;
        }
        size_t bytesSize = pagesToAllocate * 4096;
        
        void* memory = mmap(NULL, bytesSize, PROT_READ | PROT_WRITE | PROT_EXEC, MAP_ANON | MAP_PRIVATE, 0, 0);
        hashInsert(memory, bytesSize);
        
        return memory;
    }
    
    void deallocate(void* ptr) {
        size_t bytesSize = hashDelete(ptr);
        munmap(ptr, bytesSize);
    }
    
};


int main(int argc, const char * argv[]) {
    
    Allocater allocater = Allocater();
    
    int number = 500000;
    void* pointers[number];
    for (int i = 0; i < number; i++) {
        pointers[i] = allocater.allocate(1);
    }
    for (int i = 0; i < number; i++) {
        allocater.deallocate(pointers[i]);
    }
    
    
}

