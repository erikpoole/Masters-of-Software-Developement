//
//  fuctions.hpp
//  HW6ADeckOfCards
//
//  Created by Erik Poole on 8/28/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#ifndef fuctions_hpp
#define fuctions_hpp

#include <stdio.h>

#include <iostream>
#include <vector>
#include <string>

struct card {
    std::string suit;
    int rank;
};

std::vector<card> deck();
void printdeck(std::vector<card> inputDeck);

#endif /* fuctions_hpp */
