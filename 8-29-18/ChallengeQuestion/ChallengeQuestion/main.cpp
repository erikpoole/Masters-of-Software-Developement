//
//  main.cpp
//  ChallengeQuestion
//
//  Created by Erik Poole on 8/29/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>

void swapInts(int& number1, int& number2) {
    int temp = number1;
    number1 = number2;
    number2 = temp;
}

int main(int argc, const char * argv[]) {

    int a = 1, b = 2;
    
    swapInts(a,b);
    
    std::cout << a << b << std::endl;
    
    
    
    
    std::string h = "hello";
    for (char& c : h) {
        c = c - 'a' + 'A';
    }
    
    std::cout << h << std::endl;
}
