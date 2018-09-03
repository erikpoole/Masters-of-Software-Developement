//
//  functions.cpp
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


dataPointsAndTitle fileReader() {
    
    std::ifstream inputDocument("rainfall_data.txt");
    
    std::string title;
    dataPoint transferDataPoint;
    std::vector<dataPoint> allDataPoints;

    inputDocument >> title;
    std::cout << title;
    while (!inputDocument.eof()) {
        inputDocument >> transferDataPoint.month;
        inputDocument >> transferDataPoint.year;
        inputDocument >> transferDataPoint.rainfall;
        allDataPoints.push_back(transferDataPoint);
    }
    return {allDataPoints, title};
}


double overallAverageRainfall(const std::vector<dataPoint>& inputDataPoints) {
    double totalRainfall = 0;
    int totalDataPoints = 0;
    for (dataPoint eachDataPoint : inputDataPoints) {
        totalRainfall += eachDataPoint.rainfall;
        totalDataPoints++;
    }
    return double (totalRainfall/totalDataPoints);
}


std::vector<averageMonthRainfall> perMonthRainfall(std::vector<dataPoint> inputDataPoints) {
    
    std::vector<std::string> months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    
    std::vector<averageMonthRainfall> outputStructure;
    averageMonthRainfall oneMonth;
    for (std::string certainMonth : months) {
        double certainMonthRainfall = 0;
        int certainMonthCount = 0;
        for (dataPoint specificDataPoint : inputDataPoints) {
            if (specificDataPoint.month == certainMonth) {
                certainMonthRainfall += specificDataPoint.rainfall;
                certainMonthCount++;
            }
        }
        oneMonth = {certainMonth, certainMonthRainfall/certainMonthCount};
        outputStructure.push_back(oneMonth);
    }
    return outputStructure;
}


//no "const" indicator means that original values will be updated, not a new copy
std::vector<double> wetAndDryMonths(std::vector<dataPoint>& inputDataPoints) {
    for (int i = 0; i < inputDataPoints.size(); i++) {
        int lowIndex = i;
        double lowValue = inputDataPoints[i].rainfall;
        for (int j = i; j < inputDataPoints.size(); j++) {
            if (inputDataPoints[j].rainfall < lowValue) {
                lowIndex = j;
                lowValue = inputDataPoints[j].rainfall;
            }
        }
        dataPoint temp = inputDataPoints[i];
        inputDataPoints[i] = inputDataPoints[lowIndex];
        inputDataPoints[lowIndex] = temp;
    }
    
    std::vector<double> wetThenDryOutput;
    for (int i = int(inputDataPoints.size())-1; i > inputDataPoints.size() - 5; i--) {
        wetThenDryOutput.push_back(inputDataPoints[i].rainfall);
    }
    for (int i = 0; i < 4; i++) {
        wetThenDryOutput.push_back(inputDataPoints[i].rainfall);
    }
    return wetThenDryOutput;
}


//assumes data is already sorted by rainfall values
std::vector<dataPoint> findMedian(const std::vector<dataPoint>& inputDataPoints) {
    int numberValues = int(inputDataPoints.size());
    std::vector<dataPoint> medianOutput;
    if (numberValues % 2 == 0) {
        medianOutput.push_back(inputDataPoints[(numberValues/2)-1]);
        medianOutput.push_back(inputDataPoints[numberValues/2]);
    } else {
        medianOutput.push_back(inputDataPoints[(numberValues/2)]);
    }
    return medianOutput;
}











