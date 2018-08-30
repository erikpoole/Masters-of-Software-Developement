//
//  main.cpp
//  GroupAssignment2Loops&Strings
//
//  Created by Erik Poole on 8/23/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

//Worked with Qi Liu and Chung Choi
#include <iostream>
#include <string>

int main(int argc, const char * argv[]) {
    
    std::cout << "Welcome to Group Assignment 2 Loops & Strings!" << std::endl;

    //Question 1
    std::string input;
    std::cout << "Please insert a word:" << std::endl;
    std::cin >> input;
    std::cout << std::endl;
    
    //Reverses input string and compares it to original
    std::string reverseInput;
    for (int i = 0; i < input.size(); i++) {
        reverseInput = input[i] + reverseInput;
        }
    if (reverseInput == input) {
        std::cout << input << " Is a palindrome." << std::endl;
    } else {
        std::cout << input << " is NOT a palindrome" << std::endl;
    }
    
    
    //Question2
    std::string date;
    std::cout<<"Enter a date"<<std::endl;
    std::cin>>date;
    
    std::string month;
    std::string day;
    std::string year;
    
    //divides user input into smaller strings based on the location of '/' Will throw error if there are less than two '/'
    int index = 0;
    while(date[index]!='/'){
        month = month + date[index];
        index++;
        if(index>=date.size()){
            std::cout<<"Invalid Input"<<std::endl;
            return 1;
        }
    }
    index++;
    if(index>=date.size()){
        std::cout<<"Invalid Input";
        return 1;
    }
    while(date[index]!='/'){
        day = day + date[index];
        index++;
        if(index>=date.size()){
            std::cout<<"Invalid Input"<<std::endl;
            return 1;
        }
    }
    index++;
    if(index>=date.size()){
        std::cout<<"Invalid Input"<<std::endl;
        return 1;
    }
    year = date.substr(index);
    
    int monthInt = std::stoi(month);
    int dayInt = std::stoi(day);
    int yearInt = std::stoi(year);
    
    //throws error if inappropriate date is input
    if(monthInt>12 || monthInt <=0 || dayInt<=0 || dayInt>31 || yearInt<1000 || yearInt>=10000){
        std::cout<<"Invalid Date!"<<std::endl;
        return 1;
    }
    
    if(monthInt == 1)month = "January";
    if(monthInt == 2) month = "February";
    if(monthInt == 3) month = "March";
    if(monthInt == 4) month = "April";
    if(monthInt == 5) month = "May";
    if(monthInt == 6) month = "June";
    if(monthInt == 7) month = "July";
    if(monthInt == 8) month = "August";
    if(monthInt == 9) month = "September";
    if(monthInt == 10) month = "October";
    if(monthInt == 11) month = "November";
    if(monthInt == 12) month = "December";
    
    
    std::cout<<month<<" "<<day<<", "<<year<<std::endl;
    
    
    //Question 3
    int first = monthInt * dayInt;
    int second = yearInt%100;
    if (first == second) {
        std::cout<<date<<" IS a magic date"<<std::endl;
    
    }
    else {
        std::cout<<date<<" is NOT a magic date"<<std::endl;
    }
    
    
    
}
