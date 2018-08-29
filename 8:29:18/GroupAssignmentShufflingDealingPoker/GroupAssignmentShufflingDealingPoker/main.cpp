//
//  main.cpp
//  HW6ADeckOfCards
//
//  Created by Erik Poole on 8/28/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//
//Worked with Alex Moyle

#include <iostream>
#include <vector>
#include <string>
#include <cstdlib>

#include "functions.hpp"

int main(int argc, const char * argv[]) {
    
    std::vector<card> flush = {{"Diamonds", 3}, {"Diamonds", 4}, {"Diamonds", 13}, {"Diamonds", 9}, {"Diamonds", 6}};
    
    std::vector<card> straight = {{"Diamonds", 5}, {"Hearts", 4}, {"Clubs", 3}, {"Clubs", 6}, {"Diamonds", 7}};
    
    std::vector<card> straightFlush = {{"Diamonds", 5},{"Diamonds", 6}, {"Diamonds", 7}, {"Diamonds", 8}, {"Diamonds", 9}};
    
    std::vector<card> royalFlush = {{"Diamonds", 10},{"Diamonds", 11}, {"Diamonds", 12}, {"Diamonds", 13}, {"Diamonds", 14}};
    
    std::vector<card> FullHouse = {{"Diamonds", 5},{"Diamonds", 5}, {"Diamonds", 5}, {"Spades", 13}, {"Spades", 13}};
    
//    printDeck(deck());
    
//    std::vector<card> currentDeck = deck();
//    shuffleDeck(currentDeck);
//    printDeck(currentDeck);
    
    std::cout << isFlush(flush) << std::endl;
    std::cout << isFlush(straight) << std::endl;
    
    std::cout << isStraight(flush) << std::endl;
    std::cout << isStraight(straight) << std::endl;
    
    std::cout << isStraightFlush(straight) << std::endl;
    std::cout << isStraightFlush(straightFlush) << std::endl;
   
    std::cout << isRoyalFlush(straightFlush) << std::endl;
    std::cout << isRoyalFlush(royalFlush) << std::endl;
    
}

