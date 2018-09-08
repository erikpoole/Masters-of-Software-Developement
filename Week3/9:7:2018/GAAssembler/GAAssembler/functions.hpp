//
//  functions.hpp
//  GAAssembler
//
//  Created by Erik Poole on 9/7/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#ifndef functions_hpp
#define functions_hpp

#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <cmath>
#include <sstream>

using namespace std;

#include <stdio.h>

struct argument {
    string opcode;
    string remainingLine;
};

vector<argument> getArguments(const string& filename);

string decimalToBinary(string input);

void opcodeTranslator(const argument& inputArgument, uint16_t& twoByte);
void lineTranslator(const argument& inputArgument, uint16_t& twoByte);
string toBinaryString(uint16_t input);




#endif /* functions_hpp */
