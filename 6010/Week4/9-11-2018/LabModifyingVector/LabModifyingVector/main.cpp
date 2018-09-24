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

    homemadeVector testVector(1);
    
    testVector.pushBack(10);
    cout << testVector.get(0) << endl;
    testVector.pushBack(33);
    cout << testVector.get(2) << endl;
    testVector.pushBack(-13);
    cout << testVector.get(2) << endl;
    cout << testVector.getSize() << endl;
    cout << testVector.getCapacity() << endl;
    cout << endl;
    
    testVector.popBack();
    cout << testVector.get(2) << endl;
    cout << testVector.getSize() << endl;
    cout << testVector.getCapacity() << endl;
    cout << endl;
    
    testVector.set(0, 15);
    cout << testVector.get(0) << endl;
    cout << endl;
    
    testVector.freeVector();
    
}
