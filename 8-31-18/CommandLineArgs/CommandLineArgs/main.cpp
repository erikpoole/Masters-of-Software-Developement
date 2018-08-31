//
//  main.cpp
//  CommandLineArgs
//
//  Created by Erik Poole on 8/31/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>
#include <vector>
#include <string>

int main(int argc, const char * argv[]) {

    std::cout << "This is your input:\n" << std::endl;
    for (int i = 1; i < argc; i++) {
        std::cout << argv[i] << std::endl;
    }
}
