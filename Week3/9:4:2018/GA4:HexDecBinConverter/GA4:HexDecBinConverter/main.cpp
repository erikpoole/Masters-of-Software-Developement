//
//  main.cpp
//  GA4:HexDecBinConverter
//
//  Created by Erik Poole on 9/4/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>
#include <string>
#include <vector>
#include <cmath>

#include "Functions.hpp"

int main(int argc, const char * argv[]) {

    
    std::cout << stringToInt("-99", 10) << std::endl;
    std::cout << stringToInt("1100", 2) << std::endl;
    std::cout << stringToInt("-FF", 16) << std::endl;
    
    std::cout << std::endl;
    
    std::cout << intToDecimalString(-617) << std::endl;
    std::cout << intToBinaryString(-617) << std::endl;
    std::cout << intToHexString(-15) << std::endl;
    
    std::cout << std::endl;
    
    std::cout << stringToInt(intToHexString(1333),16) << std::endl;
    std::cout << intToBinaryString(stringToInt("11101001", 2)) << std::endl;
    std::cout << intToHexString(stringToInt("-FAD", 16)) << std::endl;
    std::cout << intToBinaryString(stringToInt("0", 2)) << std::endl;

    
}
