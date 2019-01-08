//
//  main.cpp
//  assignment1
//
//  Created by Erik Poole on 1/8/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#include <stdio.h>
#include <iostream>



int countOccurances(std::string inputWord, char inputChar) {
    int count = 0;
    for (char c : inputWord) {
        if (c == inputChar) {
            count++;
        }
    }
    return count;
}



bool isAutobiographical(int input) {
    std::string stringInput = std::to_string(input);
    
    for (int i = 0; i < stringInput.length(); i++) {
        int expectedCount = stringInput[i] - '0';
        int actualCount = countOccurances(stringInput, '0' + i);
        if (expectedCount != actualCount) {
            return false;
        }
    }
    return true;
}



int main(int argc, const char * argv[]) {
    for (int i = 0; i < 1e8; i++) {
        if (isAutobiographical(i)) {
            std::cout << i << std::endl;
        }
    }
}





