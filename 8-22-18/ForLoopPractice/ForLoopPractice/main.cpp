//
//  main.cpp
//  ForLoopPractice
//
//  Created by Erik Poole on 8/22/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>

int main(int argc, const char * argv[]) {

    std::cout << "Welcome to Loop Practice!" << std::endl;
    
    
    //For Loop counting 1-10
    for (int ForCount = 1; ForCount <= 10; ForCount++){
        std::cout << ForCount << std::endl;
    }
    
    //While Loop counting 1-10
    int WhileCount = 1;
    while (WhileCount <= 10) {
        std::cout << WhileCount << std::endl;
        WhileCount++;
    }
    
    /* The "For" Loop seems more appropriate here.
     You can add all of your loop controls easily in one line and it's easier to read and type.
     */
    

    //Takes two input numbers and counts between them
    std::cout << std::endl << "Please Enter Two Numbers: " << std::endl;
    double number1;
    double number2;
    std::cin >> number1 >> number2;
    std::cout << std::endl;
  
    if (number1 > number2) {
        for (number2; number2 <= number1; number2++){
            std::cout << number2 << std::endl;
        }
    } else {
        for (number1; number1 <= number2; number1++){
            std::cout << number1 << std::endl;
        }
    }
    std::cout << std::endl;
    

    //Printing Odd Numbers 1-20 with if statement
    for (int OddCount = 1; OddCount <= 20; OddCount++) {
        if (OddCount % 2 == 1) {
            std::cout << OddCount << std::endl;
        }
    }
    std::cout << std::endl;
    
    
    //Printing Odd Numbers 1-20 without an if statement
    for (int OddCount =1; OddCount <= 20; OddCount += 2) {
        std::cout << OddCount << std::endl;
    }
    
    
    //Totals any number of numbers entered
    double UserNumber = 0;
    double TotalNumber = 0;
    std::cout << std::endl << "Enter any number of positive numbers to add followed by a negative number to end:" << std::endl;
    while (UserNumber >= 0) {
        std::cin >> UserNumber;
        TotalNumber += UserNumber;
    }
    std::cout << "Your total is: " << TotalNumber - UserNumber << std::endl;
    

    //Prints muliplication tables up to five from 1-5
    std::cout << std::endl;
    for (int tableLoop = 1; tableLoop <= 5; tableLoop++){
        for (int multiplyLoop = 1; multiplyLoop <= 5; multiplyLoop++){
            std::cout << (tableLoop * multiplyLoop) << " ";
        }
        std::cout << std::endl;
    }
    
    
}

