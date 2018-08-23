//
//  main.cpp
//  HW3RomanNumerals
//
//  Created by Erik Poole on 8/23/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>
#include <string>

int main(int argc, const char * argv[]) {

    const int M = 1000;
    const int CM = 900;
    const int D = 500;
    const int CD = 400;
    const int C = 100;
    const int XC = 90;
    const int L = 50;
    const int XL = 40;
    const int X = 10;
    const int IX = 9;
    const int V = 5;
    const int IV = 4;
    const int I = 1;
    
    std::cout << "Hello and welcome to the Roman Numeral Calculator!" << std::endl << "Please enter a number: " << std::endl;
    int input;
    std::cin >> input;
    
    if (input < 0) {
        std::cout << "Sorry, please try again and enter a positive number!" <<std::endl;
        return 1;
    }
    
    std::string finalString;
    while (input > 0) {
        if (input >= M) {
            input -= M;
            finalString += "M";
            
        } else if (input >= CM) {
            input -= CM;
            finalString += "CM";
            
        } else if (input >= D) {
            input -= D;
            finalString += "D";
            
        } else if (input >= CD) {
            input -= CD;
            finalString += "CD";
            
        } else if (input >= C) {
            input -= C;
            finalString += "C";
            
        } else if (input >= XC) {
            input -= XC;
            finalString += "XC";
            
        } else if (input >= L) {
            input -= L;
            finalString += "L";
            
        } else if (input >= XL) {
            input -= XL;
            finalString += "XL";
            
        } else if (input >= X) {
            input -= X;
            finalString += "X";
            
        } else if (input >= IX) {
            input -= IX;
            finalString += "IX";
            
        } else if (input >= V) {
            input -= V;
            finalString += "V";
            
        } else if (input >= IV) {
            input -= IV;
            finalString += "IV";
            
        } else if (input >= I) {
            input -= I;
            finalString += "I";
        }
    }
    
    std::cout << "Your number, in roman numerals:" << std::endl << finalString <<std::endl;
    
}

