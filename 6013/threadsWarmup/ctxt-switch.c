#include <stdio.h>
#include <assert.h>
#include <inttypes.h>
#include <string.h>
#include <stdatomic.h>
#include <stdlib.h>

#include "thread.h"

struct argumentContainer{
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
    
    struct argumentContainer arguments[threadPairCount];
    for (int i = 0; i < threadPairCount; i++) {
        arguments[i].shared = 0;
        arguments[i].iterations = iterationsPerPair;
    }

    thread_t threads[threadPairCount * 2];
    for (int i = 0; i < threadPairCount * 2; i += 2) {
        memset(&threads[i], 0, sizeof(threads[i]));
        memset(&threads[i+1], 0, sizeof(threads[i+1]));

        thread_create(&threads[i], starter, &arguments[i/2]);
        thread_create(&threads[i+1], finisher, &arguments[i/2]);
    }

    for (int i = 0; i < threadPairCount * 2; i++) {
        thread_join(&threads[i]);
    }

//    for (int i = 0; i < threadPairCount; i++) {
//        printf("%d\n", arguments[i].shared);
//    }
}
