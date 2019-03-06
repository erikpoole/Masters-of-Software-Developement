//
//  main.cpp
//  msdAlloc
//
//  Created by Erik Poole on 3/6/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#include <chrono>
#include "functions.hpp"
#include <stdio.h>
#include <thread>
#include <iostream>


int main(int argc, const char * argv[]) {
    
    int number = 50000;
    void* pointers[number];
    
    Allocater allocater = Allocater();
    
    std::this_thread::sleep_for(std::chrono::milliseconds(1000));
    
    //timing MSDalloc
    auto timeStart = std::chrono::high_resolution_clock::now();
    for (int i = 0; i < number; i++) {
        pointers[i] = allocater.allocate(1);
    }
    for (int i = 0; i < number; i++) {
        allocater.deallocate(pointers[i]);
    }
    auto timeEnd = std::chrono::high_resolution_clock::now();
    
    std::cout << "MSDalloc Time: " << std::chrono::duration_cast<std::chrono::milliseconds>(timeEnd - timeStart).count() << "\n";
    
    
    //timing Malloc
    timeStart = std::chrono::high_resolution_clock::now();
    for (int i = 0; i < number; i++) {
        pointers[i] = malloc(1);
    }
    for (int i = 0; i < number; i++) {
        free(pointers[i]);
    }
    timeEnd = std::chrono::high_resolution_clock::now();
    
    std::cout << "Malloc Time: " << std::chrono::duration_cast<std::chrono::milliseconds>(timeEnd - timeStart).count() << "\n";
}
