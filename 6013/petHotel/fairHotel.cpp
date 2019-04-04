//
//  fairHotel.cpp
//  petHotel
//
//  Created by Erik Poole on 4/4/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#include "fairHotel.hpp"
 
 void FairHotel::play() const {
     for(volatile int i = 0; i < 10000; ++i) {} //use the CPU for a while
     std::this_thread::sleep_for(std::chrono::milliseconds(1)); //take a nap
 
 }


FairHotel::FairHotel() {
    numBirds = 0;
    numDogs = 0;
    numCats = 0;
    
    maxBirdDogs = 4;
    maxCats = 1;
    birdDogsFull = false;
    catsFull = false;
}

void FairHotel::bird() {
    std::unique_lock<std::mutex> lock(myMutex);
    while (numCats != 0 || birdDogsFull) {
        birdDogsCV.wait(lock);
    }
    if (numDogs + numBirds > maxBirdDogs) {
        birdDogsFull = true;
    }
    
    ++numBirds;
    lock.unlock();
    
    assert(numCats == 0);
    play();
    lock.lock();
    --numBirds;
    if (numBirds + numDogs == 0) {
        birdDogsFull = false;
        catCV.notify_all();
    }
    lock.unlock();
    
}

void FairHotel::dog() {
    std::unique_lock<std::mutex> lock(myMutex);
    while (numCats != 0 || birdDogsFull) {
        birdDogsCV.wait(lock);
    }
    if (numDogs + numBirds > maxBirdDogs) {
        birdDogsFull = true;
    }
    
    ++numDogs;
    lock.unlock();
    
    assert(numCats == 0);
    play();
    
    lock.lock();
    --numDogs;
    if (numBirds + numDogs == 0) {
        birdDogsFull = false;
        catCV.notify_all();
    }
    lock.unlock();
}

void FairHotel::cat() {
    std::unique_lock<std::mutex> lock(myMutex);
    while (numDogs + numBirds != 0 || catsFull) {
        catCV.wait(lock);
    }
    if (numCats > maxCats) {
        catsFull = true;
    }
    
    ++numCats;
    lock.unlock();
    
    assert(numDogs + numBirds == 0);
    play();
    
    lock.lock();
    --numCats;
    if (numCats == 0) {
        catsFull = false;
        birdDogsCV.notify_all();
    }
    lock.unlock();
}

