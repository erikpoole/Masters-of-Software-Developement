//
//  main.cpp
//  Unit Conversion
//
//  Created by Erik Poole on 8/21/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//


// Worked with Qunicy Copeland

#include <iostream>

int main(int argc, const char * argv[]) {
    // insert code here...

    std::cout << "Welcome to Unit Conversion!" << std::endl;
    std::cout << "Input volume in Ounces: "  << std::endl;
    
    float ounce;
    std::cin >> ounce;
    std::cout << std::endl;
    
    std::cout << "Ounces: " << ounce << std::endl;
    std::cout << "Cups: " << ounce / 8 << std::endl;
    std::cout << "Pints: " << ounce / 16 << std::endl;
    std::cout << "Gallons: " << ounce / 128 << std::endl;
    std::cout << "Liters: " << ounce * .0296 << std::endl;
    std::cout << "Cubic Inches: " << ounce * 1.8 << std::endl;
    
    
}
