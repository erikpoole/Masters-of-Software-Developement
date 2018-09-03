//
//  main.cpp
//  LabVectorPractice
//
//  Created by Erik Poole on 8/27/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>
#include <vector>
#include "functions.hpp"

int main(int argc, const char * argv[]) {

    std::vector<double> testDouble = {1.1, 1.3, 1.6};
    std::string testString = "Hello!";
    std::vector<int> testInt = {1, 2, 3, 4, 5};
    
    std::cout << sum(testDouble) << std::endl;
    
    for (int i = 0; i < stringToVec(testString).size(); i++) {
        std::cout << stringToVec(testString)[i] << std::endl;
    }
    
    for (int i = 0; i < reverse(testInt).size(); i++) {
        std::cout << reverse(testInt)[i] << std::endl;
    }
    
}
