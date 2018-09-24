//
//  main.cpp
//  RoadTripCalculator
//
//  Created by Erik Poole on 8/21/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>

int main(int argc, const char * argv[]) {
    // insert code here...
    
    std::cout << "Hello and Welcome to Road Trip Calculator" << std::endl;
    
    std::cout << "Enter your driving distance, vehicle miles per gallon, and cost of gas in dollars per gallon separated by spaces: " << std::endl;
    float DriveDist, VehicleEff, CostGas;
    std::cin >> DriveDist >> VehicleEff >> CostGas;
    
    float GallonsUsed = DriveDist / VehicleEff;
    float Tripcost = GallonsUsed * CostGas;
    
    std:: cout << "Your road trip should cost: $" << Tripcost << std::endl;
    
}
