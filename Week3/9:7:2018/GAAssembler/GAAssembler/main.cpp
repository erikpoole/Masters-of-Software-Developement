//
//  main.cpp
//  GAAssembler
//
//  Created by Erik Poole on 9/7/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>
#include <fstream>
#include <sstream>

#include <string>
#include <vector>

#include <cmath>
#include <cstdint>


#include "functions.hpp"

using namespace std;

int main(int argc, const char * argv[]) {

    vector<argument> allArguments = getArguments(argv[1]);

    string fileName = argv[1];
    fileName = fileName.substr(0, fileName.rfind('.'));
    fileName = fileName + "_test.bin";
    ofstream outputFile(fileName);
    for (argument oneArgument : allArguments) {
        uint16_t twoByteOutput = 0;
        
        opcodeTranslator(oneArgument, twoByteOutput);
        lineTranslator(oneArgument, twoByteOutput);
        
        /* used solution from www.avrfreaks.net/forum/converting-uint16t-uint8t to cut my 16 bit variable into two 8 bit variables */
        uint8_t firstByte = twoByteOutput >> 8;
        uint8_t secondByte = twoByteOutput;
        //string twoByteBinary = toBinaryString(twoByteOutput);
        //cout << twoByteBinary << endl;
        
        outputFile.put(firstByte);
        outputFile.put(secondByte);
    }
}
