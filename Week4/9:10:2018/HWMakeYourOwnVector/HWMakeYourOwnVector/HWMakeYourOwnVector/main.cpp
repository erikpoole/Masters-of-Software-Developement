//
//  main.cpp
//  HWMakeYourOwnVector
//
//  Created by Erik Poole on 9/10/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>

#include "functions.hpp"

using namespace std;

int main(int argc, const char * argv[]) {

    homemadeVector testVector = makeVector(10);
    
    pushBack(testVector, 3);
    cout << get(testVector, 0) << endl;
    
    pushBack(testVector, 15);
    cout << get(testVector, 1) << endl;
    cout << get(testVector, 1) + 10 << endl;
    
    popBack(testVector);
    
    set(testVector, 0, 8);
    cout << get(testVector, 0) << endl;
    
    
}
