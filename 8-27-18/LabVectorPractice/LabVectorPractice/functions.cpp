//
//  functions.cpp
//  LabVectorPractice
//
//  Created by Erik Poole on 8/27/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>
#include <vector>
#include "functions.hpp"

double sum(std::vector<double> inputValues) {
    double total = 0;
    for(double specificValue : inputValues) {
        total += specificValue;
    }
    return total;
}

std::vector<char> stringToVec(std::string inputString) {
    std::vector<char> charVector;
    for (char specificChar : inputString) {
        charVector.push_back(specificChar);
    }
    return charVector;
}

std::vector<int> reverse(std::vector<int> inputVector) {
    std::vector<int> reverseVector;
    for (int i = inputVector.size()-1; i >= 0; i--) {
        reverseVector.push_back(inputVector[i]);
    }
    return reverseVector;
}
