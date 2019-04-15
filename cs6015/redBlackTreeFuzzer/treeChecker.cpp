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
};


int main(int argc, const char * argv[]) {
    std::cout << "Hello, World!\n";
    rb_red_blk_tree *tree = RBTreeCreate(IntComp, IntDest, InfoDest, IntPrint, InfoPrint);
}
