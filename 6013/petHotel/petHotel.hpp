//
//  petHotel.hpp
//  petHotel
//
//  Created by Erik Poole on 4/3/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#ifndef petHotel_hpp
#define petHotel_hpp

#include <stdio.h>

#include <thread>
#include <chrono>
#include <mutex>
#include <condition_variable>
#include <atomic>

#endif /* petHotel_hpp */

class PetHotel {
public:
    std::mutex myMutex;
    std::condition_variable birdDogCV;
    std::condition_variable catCV;
    
    int numBirds;
    int numDogs;
    int numCats;

    PetHotel();

    void play() const;
    
    void bird();
    void dog();
    void cat();
};
