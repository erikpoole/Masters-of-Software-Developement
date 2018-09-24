//
//  functions.hpp
//  LabNumberRepresentations
//
//  Created by Erik Poole on 9/5/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#ifndef functions_hpp
#define functions_hpp

#include <iostream>
#include <cstdint>
#include <iomanip>
#include <cmath>
#include <fstream>

#include <stdio.h>

bool approxEquals(double input1, double input2, double tolerance);

void printFile(std::string fileName);

#endif /* functions_hpp */
