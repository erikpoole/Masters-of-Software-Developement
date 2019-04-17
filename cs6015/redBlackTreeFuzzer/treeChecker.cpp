//
//  main.cpp
//  redBlackTreeFuzzer
//
//  Created by Erik Poole on 4/15/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#include "red_black_tree.h"
#include <iostream>
#include <random>
#include <utility>
#include <vector>



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


int verifyTreeRecursive(rb_red_blk_tree *tree, rb_red_blk_node *node, int count) {
    if (node != tree->nil) {
        
        int leftCount = verifyTreeRecursive(tree, node->left, count);
        int rightCount = verifyTreeRecursive(tree, node->right, count);
        assert(leftCount == rightCount);
        count = leftCount;
        
        //root is black
        if (node->red == 0) {
            count++;
        //root is red
        } else {
            if (node->right) {
                assert(node->right->red == 0);
            }
            if (node->left) {
                assert(node->left->red == 0);
            }
        }
    } else {
        //need leaf null nodes to be black
        count++;
    }
    return count;
}

void verifyTree(rb_red_blk_tree *tree) {
    verifyTreeRecursive(tree, tree->root->left, 0);
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
    
    int getRandomValue() {
        return vec[rand() % vec.size()].second;
    }
    
    long getSize() {
        return vec.size();
    }
};


int main(int argc, const char * argv[]) {
    rb_red_blk_tree *tree = RBTreeCreate(IntComp, IntDest, InfoDest, IntPrint, InfoPrint);
    comparisonVector compVec;
    
    //arbitrary seed
    srand((unsigned int) time(NULL));
    
    std::cerr << argv[1] << " Cycles to be Run\n";
    std::cerr << "Fuzzing Tree...\n";
    for (int i = 0; i < std::stoi(argv[1]); i++) {
        int randNum = rand() % 201;
        int caseType = 0;
        if (randNum < 50) {
            caseType = 1;
        } else if (randNum < 100) {
            caseType = 2;
        } else if (randNum < 150) {
            caseType = 3;
        } else if (randNum < 200) {
            caseType = 4;
        }
        
        switch (caseType) {
            case 0: {
//                std::cerr << "Tree Destroyed\n";
                RBTreeDestroy(tree);
                tree = RBTreeCreate(IntComp, IntDest, InfoDest, IntPrint, InfoPrint);
                compVec.reset();
                break;
            }
            case 1: {
                int* key = new int(rand() % 50);
                compVec.add(key);
                RBTreeInsert(tree, key, key);
                verifyTree(tree);
                break;
            }
            case 2: {
                int valueToRemove = rand() % 50;
                rb_red_blk_node* foundNode = RBExactQuery(tree, &valueToRemove);
                
                if (foundNode) {
                    compVec.remove(valueToRemove);
                    RBDelete(tree, foundNode);
                }
                    
                verifyTree(tree);
                break;
            }
                //also fuzzes stack operations, not sure if necessary...
            case 3: {
//                std::cerr << "Enumerate Called\n";
                if (compVec.getSize() > 0) {
                    int value1 = compVec.getRandomValue();
                    int value2 = compVec.getRandomValue();
                    stk_stack* stack1 = RBEnumerate(tree, &value1, &value2);
                    stk_stack* stack2 = RBEnumerate(tree, &value2, &value1);
                    int* poppedValue = (int*) StackPop(stack1);
                    StackPush(stack1, poppedValue);
                    stk_stack* combinedStack = StackJoin(stack1, stack2);
                    delete combinedStack;
                break;
                }
            }
            case 4: {
                RBTreePrint(tree);
                break;
            }
            //Should be impossible to reach...
            default: {
                assert(false);
            }
        }
        
//        std::cerr << compVec.getSize() << "\n";
    }
    
    RBTreeDestroy(tree);
    std::cerr << "Fuzzing Complete!\n";
}
