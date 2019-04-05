//
//  fairHotel.hpp
//  petHotel
//
//  Created by Erik Poole on 4/4/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#ifndef fairHotel_hpp
#define fairHotel_hpp

#include <stdio.h>
#include <stdlib.h>

#include <thread>
#include <chrono>
#include <mutex>
#include <condition_variable>
#include <atomic>
#include <vector>

#endif /* fairHotel_hpp */

class FairHotel {
public:
    std::mutex myMutex;
    std::condition_variable birdDogsCV;
    std::condition_variable catCV;
    std::atomic<bool> *donePtr;
    
    int numBirds;
    int numDogs;
    int numCats;
    
    int maxBirds;
    int maxDogs;
    int maxCats;

    bool birdsFull;
    bool dogsFull;
    bool catsFull;
    
    FairHotel(char** argv, std::atomic<bool> *done);
    
    void play() const;
    
    void bird();
    void dog();
    void cat();
};


std::vector<int> findDivisors(int num);
int findClosestDivisor(std::vector<int> divisors, int average);
