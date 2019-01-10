//
//  main.cpp
//  assignment1
//
//  Created by Erik Poole on 1/8/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#include <stdio.h>
#include <iostream>


/*
 * takes argument character and counts number of occurances in argument string
 */
int countOccurances(std::string const &inputWord, char const &inputChar) {
    int count = 0;
    for (char c : inputWord) {
        if (c == inputChar) {
            count++;
        }
    }
    return count;
}


/*
 * takes integer argument and checks to see if the number is autobiographical
 * e.g. 1210
 */
bool isAutobiographical(int const &input) {
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
    for (int i = 0; i < 1e9; i++) {
        if (isAutobiographical(i)) {
            std::cout << i << std::endl;
        }
    }
}





