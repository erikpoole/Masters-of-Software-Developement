//
//  main.cpp
//  threadsWarmup
//
//  Created by Erik Poole on 3/18/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

/*
 Write equivalent programs using C++ + std::thread, and C + user level threads that:
 
 take as commandline parameters the number of thread pairs, and the number of iterations per thread
 start up the specified number of thread pairs (one starter + one finisher in each pair)
 join all the threads
 Benchmark the two implementations. Which is faster? Use a profiler (Instrument's time profiler is good) to explore what's happening when each program runs. Include a short description of your findings.
 
 
 void starter( start ,  iters, int* shared):
 assert(start is even)
 *shared = start;
 for(i = 0 .. iters){
 while( *shared is odd){ yield(); }
 assert(shared == start + 2*i)
 ++(*shared)
 }
 
 void finisher( start, iters, int* shared):
 
 for(int i = 0 .. iters){
 while( *shared is even) { yield(); }
 assert( shared == start + 2*i + 1)
 ++(*shared)
 }
 */

#include <atomic>
#include <iostream>
#include <thread>
#include <vector>

void starter(int iterations, std::atomic<int> *shared) {
    for (int i = 0; i < iterations; i++) {
        while (*shared % 2 == 1) {
        }
        *shared += 1;
    }
}

void finisher(int iterations, std::atomic<int> *shared) {
    for (int i = 0; i < iterations; i++) {
        while (*shared % 2 == 0) {
        }
        *shared += 1;
    }
}

int main(int argc, const char * argv[]) {
    int threadPairCount = std::stoi(argv[1]);
    int iterationsPerPair = std::stoi(argv[2]);
    
    std::atomic<int> sharedNum(0);
    
    std::vector<std::thread> threads;
    
    for (int i = 0; i < threadPairCount; i++) {
        threads.push_back(std::thread(starter, iterationsPerPair, &sharedNum));
        threads.push_back(std::thread(finisher, iterationsPerPair, &sharedNum));
    }
    
    for (std::thread &thread : threads) {
        thread.join();
    }
    
    std::cout << sharedNum << "\n";
}
