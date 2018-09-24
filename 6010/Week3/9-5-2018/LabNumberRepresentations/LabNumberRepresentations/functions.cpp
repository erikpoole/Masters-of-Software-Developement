//
//  functions.cpp
//  LabNumberRepresentations
//
//  Created by Erik Poole on 9/5/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>
#include <cstdint>
#include <iomanip>
#include <cmath>
#include <fstream>

#include "functions.hpp"


bool approxEquals(double input1, double input2, double tolerance) {
    return (std::abs(input1 - input2) < tolerance);
}

void printFile(std::string fileName) {
    
    std::ifstream file(fileName);
    char c;
    while (file >> c) {
        std::cout << c << std::endl;
    }
}
