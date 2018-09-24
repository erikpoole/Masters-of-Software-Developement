//
//  functions.hpp
//  LabTimingCodeAndCacheAwareness
//
//  Created by Erik Poole on 9/14/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#ifndef functions_hpp
#define functions_hpp

#include <stdio.h>

std::vector<double> makeVector(double rows, double cols);

double sum1(const std::vector<double>& inputVector, double inputRows, double inputCols);
double sum2(const std::vector<double>& inputVector, double inputRows, double inputCols);

#endif /* functions_hpp */
