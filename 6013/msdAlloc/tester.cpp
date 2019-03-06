////
////  tester.cpp
////  msdAlloc
////
////  Created by Erik Poole on 3/6/19.
////  Copyright © 2019 ErikPoole. All rights reserved.
////
//
//#define CATCH_CONFIG_MAIN
//
//#include "catch.hpp"
//#include "functions.hpp"
//#include <sys/mman.h>
//
//TEST_CASE("calculateHash") {
//    size_t size = 10000;
//    Allocater allocater = Allocater();
//    void* testPointer = (std::pair<void*, size_t>*) mmap(NULL, size, PROT_READ | PROT_WRITE | PROT_EXEC, MAP_ANON | MAP_PRIVATE, 0, 0);
//    
//    REQUIRE(allocater.calculateHash(testPointer) == (size_t) testPointer >> 12);
//    munmap(testPointer, size);
//    
//    size = 0;
//    testPointer = (std::pair<void*, size_t>*) mmap(NULL, size, PROT_READ | PROT_WRITE | PROT_EXEC, MAP_ANON | MAP_PRIVATE, 0, 0);
//    
//    REQUIRE(allocater.calculateHash(testPointer) == (size_t) testPointer >> 12);
//    munmap(testPointer, size);
//}
//
//TEST_CASE("hashInsert & hashDelete") {
//    size_t size = 10000;
//    Allocater allocater = Allocater();
//    void* testPointer = (std::pair<void*, size_t>*) mmap(NULL, size, PROT_READ | PROT_WRITE | PROT_EXEC, MAP_ANON | MAP_PRIVATE, 0, 0);
//    int count = 0;
//    
//    REQUIRE(allocater.hashMapPointer != nullptr);
//    REQUIRE(allocater.internalSize == 256);
//    REQUIRE(allocater.filledSlots == 0);
//    
//    allocater.hashInsert(testPointer, size);
//    SECTION("hashInsert") {
//        REQUIRE(allocater.hashMapPointer != nullptr);
//        REQUIRE(allocater.internalSize == 256);
//        REQUIRE(allocater.filledSlots == 1);
//        
//        count = 0;
//        for (int i = 0; i < allocater.internalSize; i++) {
//            if (allocater.hashMapPointer[i].first != nullptr) {
//                count++;
//            }
//        }
//        REQUIRE(count == allocater.filledSlots);
//    }
//
//    size_t outputSize = allocater.hashDelete(testPointer);
//    SECTION("hashDelete") {
//
//        REQUIRE(outputSize == size);
//    
//        REQUIRE(allocater.internalSize == 256);
//        REQUIRE(allocater.filledSlots == 0);
//        
//        count = 0;
//        for (int i = 0; i < allocater.internalSize; i++) {
//            if (allocater.hashMapPointer[i].first != nullptr) {
//                count++;
//            }
//        }
//        REQUIRE(count == allocater.filledSlots);
//    }
//    
//    SECTION("many hashInsert & hashDelete") {
//        void* testPointerArr[10000];
//        for (int i = 0; i < 10000; i++) {
//            void* testPointer = (std::pair<void*, size_t>*) mmap(NULL, i+1, PROT_READ | PROT_WRITE | PROT_EXEC, MAP_ANON | MAP_PRIVATE, 0, 0);
//            testPointerArr[i] = testPointer;
//            allocater.hashInsert(testPointerArr[i], i);
//        }
//        REQUIRE(allocater.internalSize == 32768);
//        REQUIRE(allocater.filledSlots == 10000);
//        
//        count = 0;
//        for (int i = 0; i < allocater.internalSize; i++) {
//            if (allocater.hashMapPointer[i].first != nullptr) {
//                count++;
//            }
//        }
//        REQUIRE(count == allocater.filledSlots);
//        
//        for (int i = 0; i < 10000; i++) {
//            allocater.hashDelete(testPointerArr[i]);
//        }
//        REQUIRE(allocater.internalSize == 32768);
//        REQUIRE(allocater.filledSlots == 0);
//        
//        count = 0;
//        for (int i = 0; i < allocater.internalSize; i++) {
//            if (allocater.hashMapPointer[i].first != nullptr) {
//                count++;
//                std::cout << allocater.hashMapPointer[i].first << "\n";
//            }
//        }
//        REQUIRE(count == allocater.filledSlots);
//    }
//}
//
//TEST_CASE() {
//    
//}
//
///*
// 
// void hashInsert(void* ptr, size_t size);
// size_t hashDelete(void* ptr);
// void hashGrow();
// 
// Allocater();
// ~Allocater();
// 
// void* allocate(size_t bytesToAllocate);
// void deallocate(void* ptr);
// */
//
//
