//
//  main.cpp
//  VendingMachinePt2
//
//  Created by Erik Poole on 8/22/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>

int main(int argc, const char * argv[]) {

    std::cout << "Welcome to Vending Machine MkII!" << std::endl;
    
    std::cout << "Enter the cost of your item (in cents): " << std::endl;
    int itemCost;
    std::cin >> itemCost;
    if (itemCost < 0){
        std::cout << "We don't sell anything for negative cents! Please restart the program and input a positive number!" << std::endl;
        return 1;
    }
    
    std::cout << "Enter the amount paid (in cents): " << std::endl;
    int amountPaid;
    std::cin >> amountPaid;
    if (amountPaid < 0){
        std::cout << "You can't insert negative cents! Please restart the program and input a positive number!" << std::endl;
        return 1;
    }
    
    int change = amountPaid - itemCost;
    if (change < 0){
        std::cout << "Sorry you didn't provide enough money! Please restart the program and put in more money next time!" << std::endl;
        return 1;
        // assumes 2 of each coin is the maximum that vending machine can deliver
    } else if (change > 82 || change % 5 >= 3) {
        std::cout << "Sorry, machine is out of appropriate change!" << std::endl;
        return 1;
    } else {
        std::cout << "Your change is: " << std::endl;
    }
    
    //integer division - quarters will be a whole number
    int quarters = change / 25;
    if (quarters > 2) {
        quarters = 2;
    }
    //removes value of the quarters caluclated from change value
    change -= quarters * 25;
    std::cout << "\t Quarters: " << quarters << std::endl;
    
    int dimes = change / 10;
    if (dimes > 2) {
        dimes = 2;
    }
    change -= dimes * 10;
    std::cout << "\t Dimes: " << dimes << std::endl;
    
    int nickels = change / 5;
    if (nickels > 2) {
        nickels = 2;
    }
    change -= nickels * 5;
    std::cout << "\t Nickels: " << nickels << std::endl;
    
    //change values that require more than 2 pennies should have already been accounted for on line 36
    int pennies = change;
    std::cout << "\t Pennies: " << pennies << std::endl;
    
}
