//
//  functions.cpp
//  GAAssembler
//
//  Created by Erik Poole on 9/7/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <cmath>
#include <sstream>

#include "functions.hpp"

using namespace std;

vector<argument> getArguments(const string& filename) {
    fstream fileStream(filename);
    vector<argument> allArguments;
    string line;
    
    while (getline(fileStream, line)) {
        stringstream lineStream(line);
        string oneInstruction;
        string combinedInstruction;
        argument completeArgument;
        
        lineStream >> completeArgument.opcode;
        
        
        while (lineStream >> oneInstruction) {
            combinedInstruction += oneInstruction;
        }
        completeArgument.remainingLine = combinedInstruction;
        
        allArguments.push_back(completeArgument);
    }
    return allArguments;
}


///////////////////////////////////////////////////////////////////////////


//pass by reference, twoByteValue changed in main stack
//magic number 12 is positioning the opcode at correct location (the first nibble)
void opcodeTranslator(const argument& inputArgument, uint16_t& twoByte){
    uint16_t mask = 0;
    if (inputArgument.opcode == "addi") {
        mask = 1 << 12;
    } else if (inputArgument.opcode == "blt") {
        mask = 2 << 12;
    } else if (inputArgument.opcode == "bne") {
        mask = 3 << 12;
    } else if (inputArgument.opcode == "j") {
        mask = 4 << 12;
    } else if (inputArgument.opcode == "mul") {
        mask = 5 << 12;
    } else if (inputArgument.opcode == "sub") {
        mask = 6 << 12;
    } else if (inputArgument.opcode == "read") {
        mask = 7 << 12;
    } else if (inputArgument.opcode == "print") {
        mask = 8 << 12;
    } else {
        cout << "Invalid Input" << endl;
    }
    twoByte |= mask;
}

//pass by reference, twoByteValue changed in main stack
//magic number 10 is positioning the opcode at correct location
void lineTranslator(const argument& inputArgument, uint16_t& twoByte){
    for (int i = 0; i < inputArgument.remainingLine.size(); i++) {
        if (inputArgument.remainingLine[i] == '$') {
            int mask = inputArgument.remainingLine[i+1] - '0';
            mask <<= (10 - i);
            twoByte |= mask;
            i++;
        } else {
            int mask = inputArgument.remainingLine[i] - '0';
            twoByte |= mask;
        }
    }
}


///////////////////////////////////////////////////////////////////////////

//Used to verify binary value was correct for 16bit sequences, not called in final program
string toBinaryString(uint16_t input) {
    int digitCount = 0;
    int inputRemaining = input;
    if (inputRemaining == 0) {
        digitCount = 1;
    } else {
        while (inputRemaining >= 1) {
            inputRemaining /= 2;
            digitCount++;
        }
    }
    
    string outputString;
    for (int i = digitCount-1; i >= 0; i--) {
        int binaryValue;
        char charValue;
        binaryValue = input / (pow(2, i));
        charValue = binaryValue + '0';
        input -= binaryValue * pow(2 , i);
        outputString.push_back(charValue);
        
    }
    while (outputString.size() < 16) {
        outputString = "0" + outputString;
    }
    
    return outputString;
}
