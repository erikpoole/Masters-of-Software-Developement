//
//  main.cpp
//  GiveChange
//
//  Created by Erik Poole on 8/21/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>

int main(int argc, const char * argv[]) {
    // insert code here...

    std::cout << "Welcome to Give Change!" << std::endl;
    
    std::cout << "Enter the cost of your item (in cents): " << std::endl;
    int ItemCost;
    std::cin >> ItemCost;
    
    std::cout << "Enter the amount paid (in cents): " << std::endl;
    int AmountPaid;
    std::cin >> AmountPaid;
    
    int Change = AmountPaid - ItemCost;
    std::cout << "Your change is: " << std::endl;
    
    int Quarters = Change / 25;
    //removes value of the quarters caluclated from change value
    Change -= Quarters * 25;
    std::cout << "\t Quarters: " << Quarters << std::endl;
    
    int Dimes = Change / 10;
    Change -= Dimes * 10;
    std::cout << "\t Dimes: " << Dimes << std::endl;
    
    int Nickels = Change / 5;
    Change -= Nickels * 5;
    std::cout << "\t Nickels: " << Nickels << std::endl;
    
    int Pennies = Change;
    std::cout << "\t Pennies: " << Pennies << std::endl;
    
}
