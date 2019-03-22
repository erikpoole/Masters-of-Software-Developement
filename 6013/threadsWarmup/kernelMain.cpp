//
//  main.cpp
//  threadsWarmup
//
//  Created by Erik Poole on 3/18/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//


#include <atomic>
#include <iostream>
#include <thread>
#include <vector>

void starter(int iterations, std::atomic<int>* shared) {
    for (int i = 0; i < iterations; i++) {
        while (*shared % 2 == 1) {
            std::this_thread::yield();
        }
        *shared += 1;
    }
}

void finisher(int iterations, std::atomic<int>* shared) {
    for (int i = 0; i < iterations; i++) {
        while (*shared % 2 == 0) {
            std::this_thread::yield();
        }
        *shared += 1;
    }
}

int main(int argc, const char * argv[]) {
    int threadPairCount = std::stoi(argv[1]);
    int iterationsPerPair = std::stoi(argv[2]);
    
    std::vector<std::thread> threads;
    std::vector<std::atomic<int>*> ints;
    
    for (int i = 0; i < threadPairCount; i++) {
        ints.push_back(new std::atomic<int>(0));
    }
    
    for (int i = 0; i < threadPairCount; i++) {
        threads.push_back(std::thread(starter, iterationsPerPair, ints[i]));
        threads.push_back(std::thread(finisher, iterationsPerPair, ints[i]));
    }
    
    for (std::thread &thread : threads) {
        thread.join();
    }
    
//    for (int i = 0; i < threadPairCount; i++) {
//        std::cout << *ints[i] << "\n";
//    }
    
    for (std::atomic<int>* ptr : ints) {
        delete ptr;
        ptr = nullptr;
    }

}
