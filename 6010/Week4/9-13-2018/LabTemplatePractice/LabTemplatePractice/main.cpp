//
//  main.cpp
//  LabTemplatePractice
//
//  Created by Erik Poole on 9/13/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>
#include <string>
#include <vector>

#include "header.h"

using namespace std;

int main(int argc, const char * argv[]) {

    string tester1 = "Hello";
    vector<int> tester2 = {3, 4, 5};
    
    Triple<string> testTriple1{tester1, tester1, tester1};
    cout << testTriple1.first << endl;
    
    Triple<vector<int>> testTriple2{tester2, tester2, tester2};
    cout << testTriple2.third[0] << endl;
    
    printEach(tester1);
    
    
    //cout << testStruct.first[0] << endl;
    
    
}
