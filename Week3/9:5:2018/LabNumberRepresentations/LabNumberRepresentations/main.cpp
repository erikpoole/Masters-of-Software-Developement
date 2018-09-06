//
//  main.cpp
//  LabNumberRepresentations
//
//  Created by Erik Poole on 9/5/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>
#include <cstdint>
#include <iomanip>
#include <cmath>
#include <fstream>

#include "functions.hpp"

int main(int argc, const char * argv[]) {

    std::cout << "Size of Bool: " << sizeof(bool) << std::endl;
    std::cout << "Size of Char: " << sizeof(char) << std::endl;
    std::cout << "Size of Int: " << sizeof(int) << std::endl;
    std::cout << "Size of Long: " << sizeof(long) << std::endl;
    std::cout << "Size of Float: " << sizeof(float) << std::endl;
    std::cout << "Size of Double: " << sizeof(double) << std::endl;
    
    std::cout << std::endl;
    
    std::cout << "Size of int8_t: " << sizeof(int8_t) << std::endl;
    std::cout << "Size of int16_t: " << sizeof(int16_t) << std::endl;
    std::cout << "Size of uint8_t: " << sizeof(uint8_t) << std::endl;
    std::cout << "Size of uint16_t: " << sizeof(uint16_t) << std::endl;

    std::cout << std::endl;
    
    
    uint8_t hexUpperUInt8 = 0xFF;
    uint8_t hexLowerUInt8 = 0x0;
    uint16_t hexUpperUInt16 = 0xFFFF;
    uint16_t hexLowerUInt16 = 0x0;
    uint64_t hexUpperUInt64 = 0xFFFFFFFFFFFFFFFF;
    uint64_t hexLowerUInt64 = 0x0;
    
    std::cout << +hexUpperUInt8 << std::endl;
    std::cout << +hexLowerUInt8 << std::endl;
    std::cout << hexUpperUInt16 << std::endl;
    std::cout << hexLowerUInt16 << std::endl;
    std::cout << hexUpperUInt64 << std::endl;
    std::cout << hexLowerUInt64 << std::endl;
    
    std::cout << std::endl;
    
    
    int8_t hexUpperSInt8 = 0x7F;
    int8_t hexLowerSInt8 = 0x80;
    int16_t hexUpperSInt16 = 0x7FFF;
    int16_t hexLowerSInt16 = 0x8000;
    int64_t hexUpperSInt64 = 0x7FFFFFFFFFFFFFFF;
    int64_t hexLowerSInt64 = 0X8000000000000000;
    
    std::cout << +hexUpperSInt8 << std::endl;
    std::cout << +hexLowerSInt8 << std::endl;
    std::cout << hexUpperSInt16 << std::endl;
    std::cout << hexLowerSInt16 << std::endl;
    std::cout << hexUpperSInt64 << std::endl;
    std::cout << hexLowerSInt64 << std::endl;
    
    std::cout << std::endl;
    
    std::cout << hexLowerUInt64-- << std::endl;
    //adding to upper bound with Signed will cause failure
    //std::cout << hexUpperSInt64++ << std::endl;
    
    std::cout << std::endl;
    
    float floatVariable = .1 + .2;
    std::cout << floatVariable << std::endl;
    //assert(floatVariable == .3);
    std::cout << std::setprecision(18);
    std::cout << floatVariable << std::endl;
    
    std::cout << approxEquals(floatVariable, .3, .00001) << std::endl;
    std::cout << approxEquals(floatVariable, .3, .0000000000001) << std::endl;
    
    std::cout << std::endl;
    
    printFile("UTF-8-demo.txt");
    
    
}
