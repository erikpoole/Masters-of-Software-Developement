//
//  Functions.hpp
//  GA4:HexDecBinConverter
//
//  Created by Erik Poole on 9/4/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#ifndef Functions_hpp
#define Functions_hpp

#include <iostream>
#include <string>
#include <vector>
#include <cmath>

#include <stdio.h>


std::vector<char> hexConvert(std::string inputString);
int stringToInt(std::string inputString, int base);

std::string intToDecimalString(int input);
std::string intToBinaryString(int input);
std::string intToHexString(int input);


#endif /* Functions_hpp */
