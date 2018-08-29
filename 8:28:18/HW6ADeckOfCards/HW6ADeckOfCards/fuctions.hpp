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


//input: none
//output: vector of 52 "card" structs with "card"s defined in 4 suits of 13 cards
std::vector<card> deck();

//input: vector of cards (designed for a deck)
//output: prints each card's characteristics to the console, exchanging appropriate ranks for face card names
void printdeck(std::vector<card> inputDeck);

#endif /* fuctions_hpp */
