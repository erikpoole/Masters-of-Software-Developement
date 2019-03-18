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
 */

#include <iostream>

int main(int argc, const char * argv[]) {
    int threadPairCount = std::stoi(argv[1]);
    int iterationsPerPair = std::stoi(argv[2]);
    
    std::cout << threadPairCount << " " << iterationsPerPair << "\n";
    
}
