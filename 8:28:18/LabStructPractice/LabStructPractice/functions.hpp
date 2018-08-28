//
//  functions.hpp
//  LabStructPractice
//
//  Created by Erik Poole on 8/28/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#ifndef functions_hpp
#define functions_hpp

#include <iostream>
#include <stdio.h>

struct Politician {
    std::string name;
    bool isDemocrat;
    bool isFederal;
};

//input: vector of struct Politician
//output: vector of struct Politician
//returns a list of democrats from a list of politicians
std::vector<Politician> democratFinder(std::vector<Politician> input);

//input: vector of struct Politician
//output: vector of struct Politician
//returns a list of federal repulicans from a list of politicians
std::vector<Politician> fedRepublicanFinder(std::vector<Politician> input);


//input: vector of struct Politician & bool - true for Federal, false for State
//output: integer between -1 & 1
//returns -1 for Republican Victory, 0 for Tie, 1 for Democrat Victory
int whipVotes(std::vector<Politician> inputList, bool isFederalBill);

#endif /* functions_hpp */
