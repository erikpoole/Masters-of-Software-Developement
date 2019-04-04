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

#include <thread>
#include <chrono>
#include <mutex>
#include <condition_variable>
#include <atomic>

#endif /* fairHotel_hpp */

class FairHotel {
public:
    std::mutex myMutex;
    std::condition_variable birdDogsCV;
    std::condition_variable catCV;
    
    int numBirds;
    int numDogs;
    int numCats;
    
    int maxBirds;
    int maxDogs;
    int maxCats;

    bool birdsFull;
    bool dogsFull;
    bool catsFull;
    
    FairHotel(char** argv);
    
    void play() const;
    
    void bird();
    void dog();
    void cat();
};
