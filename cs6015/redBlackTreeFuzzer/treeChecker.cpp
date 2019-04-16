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
//end of shameless stealing

//adapted from InorderTreePrint int red_black_tree.c
void verifyTreeRecursive(rb_red_blk_tree *tree, rb_red_blk_node *x) {
    rb_red_blk_node *nil = tree->nil;
    rb_red_blk_node *root = tree->root;
    if (x != tree->nil) {
        verifyTreeRecursive(tree, x->left);
//        tree->PrintInfo(x->info);
//        tree->PrintKey(x->key);
        if (x->left == nil) {
            
        } else {
//            tree->PrintKey(x->left->key);
        }
        if (x->right == nil) {
            
        } else {
//            tree->PrintKey(x->right->key);
        }
        if (x->parent == root) {
            
        } else {
//            tree->PrintKey(x->parent->key);
        }
        verifyTreeRecursive(tree, x->right);
    }
}

//adapted from RBTreePrint in red_black_tree.c
void verifyTree(rb_red_blk_tree *tree) {
    verifyTreeRecursive(tree, tree->root->left);
}


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
        verifyTree(tree);
    }
    
//    std::cout << compVec.getSize() << "\n";
//    RBTreePrint(tree);
    
    for (int* randInt : randVec) {
        compVec.remove(*randInt);
        rb_red_blk_node* foundNode = RBExactQuery(tree, randInt);
        if (foundNode) {
            RBDelete(tree, foundNode);
        }
        verifyTree(tree);

    }
    
    std::cout << compVec.getSize() << "\n";
    RBTreePrint(tree);
    
}
