//
//  main.cpp
//  IfStatementPractice
//
//  Created by Erik Poole on 8/22/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>

int main(int argc, const char * argv[]) {

    std::cout << "Welcome to If Statement Practice!" << std::endl;
    
    std::cout << "What is your age (in years)?" << std::endl;
    int age;
    std::cin >> age;
    std::cout << std::endl;
    
    bool VoteAge = age >= 18;
    bool SenateAge = age >=30;
    if (SenateAge) {
        std::cout << "Congratulations!  You can both vote AND be elected to the Senate!" << std::endl;
    } else if (VoteAge) {
        std::cout << "You are old enough to vote, but you can't be elected to the Senate." << std::endl;
    } else {
        std::cout << "Sorry, you aren't even old enough to vote, let alone be elected to the Senate." << std::endl;
    }
    
    bool GreatestAge = age > 80;
    bool BoomerAge = age > 60;
    bool XAge = age > 40;
    bool MillenialAge = age > 20;
    if (GreatestAge) {
        std::cout << "You are a member of the Greatest Generation." << std::endl;
    } else if (BoomerAge) {
        std::cout << "You are a Baby Boomer." << std::endl;
    } else if (XAge) {
        std::cout << "You are in Generation X." <<std::endl;
    } else if (MillenialAge) {
        std::cout << "You are a Millennial." << std::endl;
    } else {
        std::cout << "You are an iKid." << std::endl;
    }
    std::cout << std::endl;
    
    std::cout << "Is it a weekday today (Y/N)?: " << std::endl;
    char IsWeekday;
    std::cin >> IsWeekday;
    std::cout << std::endl;
    
    std::cout << "Is it a holiday today (Y/N)?: " << std::endl;
    char IsHoliday;
    std::cin >> IsHoliday;
    std::cout << std::endl;
    
    std::cout << "Do you have young children (Y/N)?: " << std::endl;
    char ChildPresent;
    std::cin >> ChildPresent;
    std::cout << std::endl;
    
    bool WeekdayTrue = IsWeekday == 'y' || IsWeekday == 'Y';
    bool HolidayTrue = IsHoliday == 'y' || IsHoliday == 'Y';
    bool ChildTrue = ChildPresent == 'y' || ChildPresent == 'Y';

    if (ChildTrue || (WeekdayTrue && !HolidayTrue)) {
        std::cout << "Unfortunately, you cannot sleep in." << std::endl;
    } else {
        std::cout << "You get to sleep in!" << std::endl;
    }
    
}
