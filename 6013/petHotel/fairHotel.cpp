//
//  fairHotel.cpp
//  petHotel
//
//  Created by Erik Poole on 4/4/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#include "fairHotel.hpp"
#include <iostream>
 
 void FairHotel::play() const {
     for(volatile int i = 0; i < 10000; ++i) {} //use the CPU for a while
     std::this_thread::sleep_for(std::chrono::milliseconds(1)); //take a nap
 
 }


FairHotel::FairHotel(char** argv, std::atomic<bool>* done) {
    numBirds = 0;
    numDogs = 0;
    numCats = 0;
    donePtr = done;
    
    birdsFull = false;
    dogsFull = false;
    catsFull = false;
    
    int birdThreadCount = std::atoi(argv[1]);
    int dogThreadCount = std::atoi(argv[3]);
    int catThreadCount = std::atoi(argv[2]);
    
    double averageThreadCount = ((double) birdThreadCount + dogThreadCount + catThreadCount) / 3;
    std::vector<int> birdDivisors = findDivisors(birdThreadCount);
    std::vector<int> dogDivisors = findDivisors(dogThreadCount);
    std::vector<int> catDivisors = findDivisors(catThreadCount);
    
    maxBirds = findClosestDivisor(birdDivisors, averageThreadCount);
    maxDogs = findClosestDivisor(dogDivisors, averageThreadCount);
    maxCats = findClosestDivisor(catDivisors, averageThreadCount);
    
    /* equal limits
    maxBirds = std::atoi(argv[1]);
    maxDogs = std::atoi(argv[3]);
    maxCats = std::atoi(argv[2]);
     */
    
    // cat limit
//    maxBirds = std::atoi(argv[2]);
//    maxDogs = std::atoi(argv[2]);
//    maxCats = std::atoi(argv[2]);

    /* minimum limit
    if (birdThreadCount < dogThreadCount && birdThreadCount < catThreadCount) {
        //birds smallest
        maxBirds = birdThreadCount;
        maxDogs = birdThreadCount;
        maxCats = birdThreadCount;
    } else if (dogThreadCount < catThreadCount) {
        //dogs smallest
        maxBirds = dogThreadCount;
        maxDogs = dogThreadCount;
        maxCats = dogThreadCount;
    } else {
        //cats smallest
        maxBirds = catThreadCount;
        maxDogs = catThreadCount;
        maxCats = catThreadCount;
    }
    */
    
    /* debugging output
    std::cout << "Average: " << averageThreadCount << "\n";
    
    std::cout << "Bird Divisors:";
    for (int i : birdDivisors) {
        std::cout << " " << i;
    }
    std::cout << "\n";

    std::cout << "Dog Divisors:";
    for (int i : dogDivisors) {
        std::cout << " " << i;
    }
    std::cout << "\n";

    std::cout << "Cat Divisors:";
    for (int i : catDivisors) {
        std::cout << " " << i;
    }
    std::cout << "\n";

    std::cout << "Closest Bird: " << maxBirds << "\n";
    std::cout << "Closest Dog: " << maxDogs << "\n";
    std::cout << "Closest Cat: " << maxCats << "\n";
     */
}



void FairHotel::bird() {
    std::unique_lock<std::mutex> lock(myMutex);
    while (numCats != 0 || (birdsFull && !*donePtr)) {
        birdDogsCV.wait(lock);
    }
    
    ++numBirds;
    if (numBirds >= maxBirds) {
        birdsFull = true;
    }
    
    lock.unlock();
    
    assert(numCats == 0);
    play();
    lock.lock();
    --numBirds;
    if (numBirds + numDogs == 0) {
        birdsFull = false;
        dogsFull = false;
        catCV.notify_all();
    }
    lock.unlock();
    
}

void FairHotel::dog() {
    std::unique_lock<std::mutex> lock(myMutex);
    while (numCats != 0 || (dogsFull && !*donePtr)) {
        birdDogsCV.wait(lock);
    }

    ++numDogs;
    if (numDogs >= maxDogs) {
        dogsFull = true;
    }
    
    lock.unlock();
    
    assert(numCats == 0);
    play();
    
    lock.lock();
    --numDogs;

    if (numBirds + numDogs == 0) {
        birdsFull = false;
        dogsFull = false;
        catCV.notify_all();
    }
    lock.unlock();
}

void FairHotel::cat() {
    std::unique_lock<std::mutex> lock(myMutex);
    while (numDogs + numBirds != 0 || (catsFull && !*donePtr)) {
        catCV.wait(lock);
    }
    
    ++numCats;
    if (numCats >= maxCats) {
        catsFull = true;
    }
    
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



std::vector<int> findDivisors(int num) {
    std::vector<int> output;
    for (int i = 1; i <= num; i++) {
        if (num % i == 0) {
            output.push_back(i);
        }
    }
    return output;
}

int findClosestDivisor(std::vector<int> divisors, int average) {
    long closestIndex = divisors.size()-1;
    int difference = std::abs(divisors[closestIndex] - average);
    for (int i = 0; i < divisors.size(); i++) {
        if (std::abs(divisors[i] - average) < difference) {
            closestIndex = i;
            difference = std::abs(divisors[i] - average);
        }
    }
    return divisors[closestIndex];
}
