//
//  functions.cpp
//  LabTimingCodeAndCacheAwareness
//
//  Created by Erik Poole on 9/14/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <vector>

#include "functions.hpp"


std::vector<double> makeVector(double rows, double cols) {
    return std::vector<double> (rows*cols, 1);
}

//moving across
double sum1(const std::vector<double>& inputVector, double inputRows, double inputCols) {
    double total = 0;
    for (int i = 0; i < inputRows; i++) {
        for (int j = 0; j <inputCols; j++) {
            total += inputVector[i*inputCols+j];
        }
    }
    return total;
}

//moving down
double sum2(const std::vector<double>& inputVector, double inputRows, double inputCols) {
    double total = 0;
    for (int j = 0; j < inputRows; j++) {
        for (int i = 0; i <inputCols; i++) {
            total += inputVector[i*inputCols+j];
        }
    }
    return total;
}
