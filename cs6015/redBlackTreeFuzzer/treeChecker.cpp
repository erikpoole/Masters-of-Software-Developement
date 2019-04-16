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
        } else
        
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
                //no need to free pointers, should always be freed by red_black_tree
                vec.erase(vec.begin() + i);
                assert(originalSize - 1 == vec.size());
                return true;
            }
        }
        return false;
    }
    
    void reset() {
        vec.clear();
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
    
    for (int i = 0; i < std::stoi(argv[1]); i++) {
        int randNum = rand() % 101;
        int caseType = 0;
        if (randNum < 50) {
            caseType = 1;
        } else if (randNum < 100) {
            caseType = 2;
        }
        
        switch (caseType) {
            case 0: {
//                std::cout << "Tree Destroyed\n";
                RBTreeDestroy(tree);
                tree = RBTreeCreate(IntComp, IntDest, InfoDest, IntPrint, InfoPrint);
                compVec.reset();
            }
            case 1: {
                int* key = new int(rand() % 50);
                compVec.add(key);
                RBTreeInsert(tree, key, key);
                verifyTree(tree);
                break;
            }
            case 2: {
                if (compVec.getSize() > 0) {
                    int valueToRemove = compVec.peek();
                    rb_red_blk_node* foundNode = RBExactQuery(tree, &valueToRemove);
                    
                    assert(foundNode);
                    compVec.remove(valueToRemove);
                    RBDelete(tree, foundNode);
                    
                    verifyTree(tree);
                }
                break;

            }
            default: {
                assert(false);
            }
        }
        
//        std::cout << compVec.getSize() << "\n";
    }
}
