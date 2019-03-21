#include <stdio.h>
#include <assert.h>
#include <inttypes.h>
#include <string.h>
#include <stdatomic.h>
#include <stdlib.h>

#include "thread.h"

struct argumentContainer{
    //unassigned members are initialized as zero
    atomic_int shared;
    int iterations;
};


void* starter(void* arguments) {
    struct argumentContainer* argumentsPointer = (struct argumentContainer*) arguments;
    for (int i = 0; i < argumentsPointer->iterations; i++) {
        while (argumentsPointer->shared % 2 == 1) {
            thread_yield();
        }
        argumentsPointer->shared += 1;
    }
    return arguments;
}

void* finisher(void* arguments) {
    struct argumentContainer* argumentsPointer = (struct argumentContainer*) arguments;
    for (int i = 0; i < argumentsPointer->iterations; i++) {
        while (argumentsPointer->shared % 2 == 0) {
            thread_yield();
        }
        argumentsPointer->shared += 1;
    }
    return arguments;
}


int main(int argc, char* argv[]) {
    thread_init();
    
    int threadPairCount = atoi(argv[1]);
    int iterationsPerPair = atoi(argv[2]);
    
    printf("%d", threadPairCount);
    printf("\n");
    printf("%d", iterationsPerPair);
    printf("\n");
    
//    struct argumentContainer arguments[threadPairCount];
//    for (int i = 0; i < threadPairCount; i++) {
//
//    }
    
    
    struct argumentContainer temp;
    temp.iterations = iterationsPerPair;
    
//    printf("%d\n", temp.shared);
//    temp.shared += 1;
//    printf("%d\n", temp.shared);

    thread_t t1;
    thread_t t2;

    memset(&t1, 0, sizeof(t1));
    memset(&t2, 0, sizeof(t1));

    thread_create(&t1, starter, &temp);
    thread_create(&t2, finisher, &temp);

    thread_join(&t1);
    thread_join(&t2);


    printf("%d\n", temp.shared);


    
    
//    int threadPairCount = std::stoi(argv[1]);
//    int iterationsPerPair = std::stoi(argv[2]);
//
//    std::vector<std::thread> threads;
//    std::vector<std::atomic<int>*> ints;
//
//    for (int i = 0; i < threadPairCount; i++) {
//        ints.push_back(new std::atomic<int>(0));
//    }
//
//    for (int i = 0; i < threadPairCount; i++) {
//        threads.push_back(std::thread(starter, iterationsPerPair, ints[i]));
//        threads.push_back(std::thread(finisher, iterationsPerPair, ints[i]));
//    }
//
//    for (std::thread &thread : threads) {
//        thread.join();
//    }
//
//    for (int i = 0; i < threadPairCount; i++) {
//        std::cout << *ints[i] << "\n";
//    }
//
//    for (std::atomic<int>* ptr : ints) {
//        free(ptr);
//    }
}
