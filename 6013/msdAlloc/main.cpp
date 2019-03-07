//
//  main.cpp
//  msdAlloc
//
//  Created by Erik Poole on 3/6/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#include <chrono>
#include "functions.hpp"
#include <stdio.h>
#include <thread>
#include <iostream>

int main(int argc, const char * argv[]) {
    
    std::this_thread::sleep_for(std::chrono::milliseconds(1000));
    
    compareMallocs(10000, 1000);
    compareMallocs(100000, 1000);
    compareMallocs(1000000, 1000);
    std::cout << "\n";
    
    compareMallocs(10000, 10000);
    compareMallocs(100000, 10000);
    compareMallocs(1000000, 10000);
    std::cout << "\n";
    
    compareMallocs(10000, 100000);
    compareMallocs(100000, 100000);
    compareMallocs(1000000, 100000);
    std::cout << "\n";

}

