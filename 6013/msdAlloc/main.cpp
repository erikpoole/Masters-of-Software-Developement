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
#include <vector>

class allocater {
private:
    std::vector<std::pair<void*, int>> hashTable;
    int filledSlots;
    
    
    long calculateHash(void* ptr) {
        return (long) ptr >> 12;
    }
    
    void hashInsert(void* ptr, int size) {
        if (filledSlots > hashTable.size() / 2) {
            hashGrow();
        }
        
        long location = calculateHash(ptr) % hashTable.size();
        int collisions = 0;
        while (hashTable[location].first != nullptr) {
            collisions++;
            location += (collisions + collisions*collisions)/2 % hashTable.size();
        }
        
        hashTable[location] = std::pair<void*, int> (ptr, size);
        filledSlots++;
    }
    
    int hashDelete(int* ptr) {
        long location = calculateHash(ptr) % hashTable.size();
        int collisions = 0;
        while (hashTable[location].first != ptr && hashTable[location].second != 0) {
            collisions++;
            location += (collisions + collisions*collisions)/2 % hashTable.size();
        }
        int size = hashTable[location].second;
        hashTable[location] = std::pair<void*, int> (nullptr, -1);
        filledSlots--;
        
        return size;
    }
    
    void hashGrow() {

        std::vector<std::pair<void*, int>> tempTable;
        for (int i = 0; i < hashTable.size(); i++) {
            if (hashTable[i].first != nullptr) {
                tempTable.push_back(hashTable[i]);
            }
        }
        int newSize = (int) hashTable.size() * 2;
        hashTable = std::vector<std::pair<void*, int>>(newSize, std::pair<void*, int>(nullptr, 0));
        
        for (int i = 0; i < tempTable.size(); i++) {
            hashInsert(tempTable[i].first, tempTable[i].second);
        }
    }
    
public:

    allocater() {
        hashTable = std::vector<std::pair<void*, int>>(8, std::pair<void*, int>(nullptr, 0));
        filledSlots = 0;
    }
    
    ~allocater() {
        //for allocation in hash table call munmap
    }
    
    void* allocate(size_t bytesToAllocate) {
        
        return nullptr;
    }
    
    void deallocate(void* ptr) {
        
    }
    
    
    
    
};


int main(int argc, const char * argv[]) {
    
}
