//
//  main.cpp
//  redBlackTreeFuzzer
//
//  Created by Erik Poole on 4/15/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#include "red_black_tree.h"
#include <iostream>
#include <vector>
#include <utility>
#include <random>

//shamelessly stolen from test_red_black_tree.c
void IntDest(void *a) { free((int *)a); }

int IntComp(const void *a, const void *b) {
    if (*(int *)a > *(int *)b)
        return (1);
    if (*(int *)a < *(int *)b)
        return (-1);
    return (0);
}

void IntPrint(const void *a) { printf("%i", *(int *)a); }

void InfoPrint(void *a) { ; }

void InfoDest(void *a) { ; }


class comparisonVector {
public:
    std::vector<std::pair<int, int>> vec;
    
    void add(int key, int value) {
        vec.push_back(std::pair<int, int>(key, value));
    }
    
    bool remove(int key) {
        long originalSize = vec.size();
        for (int i = 0; i < vec.size(); i++) {
            if (vec[i].first == key) {
                vec.erase(vec.begin() + i);
                assert(originalSize - 1 == vec.size());
                return true;
            }
        }
        return false;
    }
    
    int contains(int key) {
        int count = 0;
        for (int i = 0; i < vec.size(); i++) {
            if (vec[i].first == key) {
                count++;
            }
        }
        return count;
    }
    
    long getSize() {
        return vec.size();
    }
};


int main(int argc, const char * argv[]) {
    rb_red_blk_tree *tree = RBTreeCreate(IntComp, IntDest, InfoDest, IntPrint, InfoPrint);
    comparisonVector compVec;
    
    srand((unsigned int) time(NULL));
    std::vector<int*> randVec;
    
    for (int i = 0; i < 100; i++) {
        randVec.push_back(new int(rand()));
    }
    
    for (int* randInt : randVec) {
        compVec.add(*randInt, *randInt);
        RBTreeInsert(tree, randInt, randInt);
    }
    
//    std::cout << compVec.getSize() << "\n";
//    RBTreePrint(tree);
    
    for (int* randInt : randVec) {
        compVec.remove(*randInt);
        rb_red_blk_node* foundNode = RBExactQuery(tree, randInt);
        if (foundNode) {
            RBDelete(tree, foundNode);
        }

    }
    
    std::cout << compVec.getSize() << "\n";
    RBTreePrint(tree);
    
}
