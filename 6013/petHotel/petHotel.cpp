//
//  petHotel.cpp
//  petHotel
//
//  Created by Erik Poole on 4/3/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//x

#include "petHotel.hpp"


PetHotel::PetHotel() {
    numBirds = 0;
    numDogs = 0;
    numCats = 0;
}

void PetHotel::play() const {
    for(volatile int i = 0; i < 10000; ++i) {} //use the CPU for a while
    std::this_thread::sleep_for(std::chrono::milliseconds(1)); //take a nap
    
}

void PetHotel::bird() {
    std::unique_lock<std::mutex> lock(myMutex);
    while (numCats != 0) {
        birdDogCV.wait(lock);
    }
    
    ++numBirds;
    lock.unlock();
    
    assert(numCats == 0);
    play();
    lock.lock();
    --numBirds;
    if (numBirds + numDogs == 0) {
        catCV.notify_all();
    }
    lock.unlock();

}

void PetHotel::dog() {
    std::unique_lock<std::mutex> lock(myMutex);
    while (numCats != 0) {
        birdDogCV.wait(lock);
    }
    
    ++numDogs;
    lock.unlock();
    
    assert(numCats == 0);
    play();
    
    lock.lock();
    --numDogs;
    if (numBirds + numDogs == 0) {
        catCV.notify_all();
    }
    lock.unlock();
}

void PetHotel::cat() {
    std::unique_lock<std::mutex> lock(myMutex);
    while (numDogs + numBirds != 0) {
        catCV.wait(lock);
    }
    
    ++numCats;
    lock.unlock();
    
    assert(numDogs + numBirds == 0);
    play();
    
    lock.lock();
    --numCats;
    if (numCats == 0) {
        birdDogCV.notify_all();
    }
    lock.unlock();
}

