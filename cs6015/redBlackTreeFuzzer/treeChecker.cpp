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
void verifyTreeRecursive(rb_red_blk_tree *tree, rb_red_blk_node *node) {
//    rb_red_blk_node *nil = tree->nil;
//    rb_red_blk_node *root = tree->root;
    if (node != tree->nil) {
        verifyTreeRecursive(tree, node->left);
        
        //parent is red
        if (node->red == 0) {
            if (node->right) {
                assert(node->right != 0);
            }
            if (node->left) {
                assert(node->left != 0);
            }
        }
        
        verifyTreeRecursive(tree, node->right);
    }
}

//adapted from RBTreePrint in red_black_tree.c
void verifyTree(rb_red_blk_tree *tree) {
    verifyTreeRecursive(tree, tree->root->left);
}


class comparisonVector {
public:
    std::vector<std::pair<int*, int>> vec;
    
    void add(int* key) {
        vec.push_back(std::pair<int*, int>(key, *key));
    }
    
    bool remove(int value) {
        long originalSize = vec.size();
        for (int i = 0; i < vec.size(); i++) {
            if (vec[i].second == value) {
                vec.erase(vec.begin() + i);
                assert(originalSize - 1 == vec.size());
                return true;
            }
        }
        return false;
    }
    
    int contains(int* key) {
        int count = 0;
        for (int i = 0; i < vec.size(); i++) {
            if (vec[i].first == key) {
                count++;
            }
        }
        return count;
    }
    
    int peek() {
        return vec[0].second;
    }
    
    long getSize() {
        return vec.size();
    }
};


int main(int argc, const char * argv[]) {
    rb_red_blk_tree *tree = RBTreeCreate(IntComp, IntDest, InfoDest, IntPrint, InfoPrint);
    comparisonVector compVec;
    
    //arbitrary seed
    srand(100);
    std::vector<int*> keyVec;
    
    for (int i = 0; i < 10000; i++) {
        keyVec.push_back(new int(rand() % 50));
    }
    
    for (int* key : keyVec) {
        compVec.add(key);
        RBTreeInsert(tree, key, key);
        verifyTree(tree);
    }
    
//    std::cout << compVec.getSize() << "\n";
//    RBTreePrint(tree);
    
    while (compVec.getSize() != 0) {
        int valueToRemove = compVec.peek();
        rb_red_blk_node* foundNode = RBExactQuery(tree, &valueToRemove);
        
        assert(foundNode);
        compVec.remove(valueToRemove);
        RBDelete(tree, foundNode);
        
        verifyTree(tree);
        
    }
    
    std::cout << compVec.getSize() << "\n";
    RBTreePrint(tree);
    
}
