//
//  fuctions.hpp
//  HW6ADeckOfCards
//
//  Created by Erik Poole on 8/28/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//
//Worked with Alex Moyle

#ifndef fuctions_hpp
#define fuctions_hpp

#include <stdio.h>

#include <iostream>
#include <vector>
#include <string>
#include <cstdlib>

struct card {
    std::string suit;
    int rank;
};


//input: none
//output: vector of 52 "card" structs with "card"s defined in 4 suits of 13 cards
std::vector<card> deck();

//input: vector of cards (designed for a deck)
//output: prints each card's characteristics to the console, exchanging appropriate ranks for face card names
void printDeck(const std::vector<card>& inputDeck);

//input: vector of cards (designed for a deck)
//output: none
//shuffles the location of each card const in the vector
void shuffleDeck(std::vector<card>& inputdeck);

//input: vector of cards (designed for a deck)
//output: vector of five sorted cards
std::vector<card> sortHand(const std::vector<card>& inputDeck);

//check to see if the first five cards of an input are all the same suit
bool isFlush(const std::vector<card>& inputDeck);

//check to see if the first five cards can be arranged in ascending order
bool isStraight(const std::vector<card>& inputDeck);

//check to see if the first five cards are the same suit and in ascending order
bool isStraightFlush(const std::vector<card>& inputDeck);

//check to see if the first five cards are the same suit, ascending order, and all greater than 9
bool isRoyalFlush(const std::vector<card>& inputDeck);

//check to see if exactly three of five cards are the same rank and two of the three cards are a different rank
bool isFullHouse(const std::vector<card>& inputDeck);


#endif /* fuctions_hpp */
