//
//  main.cpp
//  HW6Rainfall
//
//  Created by Erik Poole on 8/30/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <iomanip>

#include "functions.hpp"

// fixed << setprecision

int main(int argc, const char * argv[]) {

    dataPointsAndTitle dataAndTitle = fileReader();
    
    std::vector<averageMonthRainfall> monthAndRainfall = perMonthRainfall(dataAndTitle.AllDataPoints);
    
    std::vector<double> lowAndHigh = wetAndDryMonths(dataAndTitle.AllDataPoints);
    
    std::vector<dataPoint> medianDataPoint = findMedian(dataAndTitle.AllDataPoints);
    
    
    std::ofstream outputFile("rainfall_results.txt");
    outputFile << std::fixed << std::setprecision(2);
    outputFile << "Homework 6" << std::endl;
    outputFile << "CS 6010" << std::endl;
    outputFile << "Erik Poole" << std::endl;
    outputFile << "Rainfall data for ";
    outputFile << dataAndTitle.title << std::endl;
    outputFile << std::endl;
    outputFile << "The overall average rainfall amount is ";
    outputFile << overallAverageRainfall(dataAndTitle.AllDataPoints);
    outputFile << " inches." << std::endl;
    
    for (averageMonthRainfall oneMonth : monthAndRainfall) {
        outputFile << "The average rainfall amount for ";
        outputFile << oneMonth.month;
        outputFile << " is ";
        outputFile << oneMonth.rainfall;
        outputFile << " inches." << std::endl;
    }

    outputFile << "The rain amounts (in inches) of the four wettest months are: ";
    for (int i = 0; i < 4; i++) {
        outputFile << lowAndHigh[i] << " ";
    }
    outputFile << std::endl;
    outputFile << "The rain amounts (in inches) of the four driest months are: ";
    for (int i = 4; i < 8; i++) {
        outputFile << lowAndHigh[i] << " ";
    }
    outputFile << std::endl;
    
    outputFile << "The median month(es) is(are):" << std::endl;
    for (dataPoint specificDataPoint : medianDataPoint) {
        outputFile << specificDataPoint.month << " ";
        outputFile << specificDataPoint.year << " ";
        outputFile << specificDataPoint.rainfall << std::endl;
    }

    
}
