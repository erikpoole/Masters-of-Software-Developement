//
//  Functions.cpp
//  GA4:HexDecBinConverter
//
//  Created by Erik Poole on 9/4/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//
// Worked with Quincy Coopland

#include <iostream>
#include <string>
#include <vector>
#include <cmath>

#include "Functions.hpp"


std::vector<char> hexConvert(std::string inputString) {
    std::vector<char> outputVector;
    for(int i = 0; i < inputString.size(); i++) {
        if (inputString[i] >= 'a' && inputString[i] <= 'z') {
            inputString[i] = inputString[i] + 'A' - 'a';
        }
        
        if (inputString[i] >= 'A' && inputString[i] <= 'F') {
            char specificChar = inputString[i] - 'A' + 10;
            outputVector.push_back(specificChar);
        } else {
            char specificChar = inputString[i] - '0';
            outputVector.push_back(specificChar);
        }
    }
    return outputVector;
}


///////////////////////////////////////////////////////////////////////////


int stringToInt(std::string inputString, int base){
    bool isNegative = false;
    std::string negFixString;
    if (inputString[0] == '-') {
        isNegative = true;
        for (int i = 1; i < inputString.size(); i++) {
            negFixString.push_back(inputString[i]);
        }
    } else {
        negFixString = inputString;
    }
    
    std::vector<char> inputVector = hexConvert(negFixString);

    int outputInt= 0;
    for (int i = 0 ; i < inputVector.size(); i++){
        int convertedInt = inputVector[i] * pow(base, inputVector.size() - (i+1));
        outputInt += convertedInt;
    }
    
    if (isNegative) {
        outputInt *= -1;
    }
    
    return outputInt;
}


///////////////////////////////////////////////////////////////////////////


std::string intToDecimalString(int input) {
    bool isNegative = false;
    int negFixInput = input;
    if (input < 0) {
        isNegative = true;
        negFixInput *= -1;
    }

    int charCount = 0;
    int dividingInput = negFixInput;
    if (dividingInput == 0) {
        charCount = 1;
    } else {
        while (dividingInput >= 1) {
            dividingInput /= 10;
            charCount++;
        }
    }
    
    std::string outputString;
    if (isNegative) {
        outputString.push_back('-');
    }
    
    for (int i = charCount-1; i >= 0; i--) {
        int intValue;
        char charValue;
        intValue = negFixInput / (pow(10, i));
        charValue = intValue + '0';
        negFixInput -= (intValue * pow(10,i));
        outputString.push_back(charValue);
    }
    
    return outputString;
}

std::string intToBinaryString(int input) {
    bool isNegative = false;
    int negFixInput = input;
    if (input < 0) {
        isNegative = true;
        negFixInput *= -1;
    }
    
    int charCount = 0;
    int dividingInput = negFixInput;
    if (dividingInput == 0) {
        charCount = 1;
    } else {
        while (dividingInput >= 1) {
            dividingInput /= 2;
            charCount++;
        }
    }
    
    std::string outputString;
    if (isNegative) {
        outputString.push_back('-');
    }
    
    for (int i = charCount-1; i >= 0; i--) {
        int intValue;
        char charValue;
        intValue = negFixInput / (pow(2, i));
        charValue = intValue + '0';
        negFixInput -= (intValue * pow(2,i));
        outputString.push_back(charValue);
    }
    
    return outputString;
}

std::string intToHexString(int input) {
    bool isNegative = false;
    int negFixInput = input;
    if (input < 0) {
        isNegative = true;
        negFixInput *= -1;
    }
    
    int charCount = 0;
    int dividingInput = negFixInput;
    if (dividingInput == 0) {
        charCount = 1;
    } else {
        while (dividingInput >= 1) {
            dividingInput /= 16;
            charCount++;
        }
    }
    
    std::string outputString;
    if (isNegative) {
        outputString.push_back('-');
    }
    
    for (int i = charCount-1; i >= 0; i--) {
        int intValue;
        char charValue;
        intValue = negFixInput / (pow(16, i));
        if (intValue > 9) {
            charValue = intValue - 10 + 'A';
        } else {
            charValue = intValue + '0';
        }
        negFixInput -= (intValue * pow(16, i));
        outputString.push_back(charValue);
    }
    
    return outputString;
}
