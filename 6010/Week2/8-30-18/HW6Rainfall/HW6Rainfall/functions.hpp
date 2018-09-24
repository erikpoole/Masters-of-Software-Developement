//
//  functions.hpp
//  HW6Rainfall
//
//  Created by Erik Poole on 8/30/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#ifndef functions_hpp
#define functions_hpp

#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <iomanip>

#include <stdio.h>

struct dataPoint {
    std::string month;
    int year;
    double rainfall;
};

struct dataPointsAndTitle {
    std::vector<dataPoint> AllDataPoints;
    std::string title;
};

struct averageMonthRainfall {
    std::string month;
    double rainfall;
};

dataPointsAndTitle fileReader();

double overallAverageRainfall(const std::vector<dataPoint>& inputDataPoints);

std::vector<averageMonthRainfall> perMonthRainfall(std::vector<dataPoint> inputDataPoints);

std::vector<double> wetAndDryMonths(std::vector<dataPoint>& inputDataPoints);

std::vector<dataPoint> findMedian(const std::vector<dataPoint>& inputDataPoints);

#endif /* functions_hpp */
