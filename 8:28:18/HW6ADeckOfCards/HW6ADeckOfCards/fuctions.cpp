//
//  fuctions.cpp
//  HW6ADeckOfCards
//
//  Created by Erik Poole on 8/28/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>
#include <vector>
#include <string>

#include "fuctions.hpp"

std::vector<card> deck() {
    card currentCard = {};
    std::vector<card> listOfCards;
    for (int i = 1; i <= 4; i++) {
        if (i == 1) {
            currentCard.suit = "Clubs";
        } else if (i == 2) {
            currentCard.suit = "Diamonds";
        } else if (i == 3) {
            currentCard.suit = "Spades";
        } else if (i == 4) {
            currentCard.suit = "Hearts";
        }
        for (int j = 1; j <= 13; j++) {
            currentCard.rank = j;
            listOfCards.push_back(currentCard);
        }
    }
    return listOfCards;
}

void printdeck(std::vector<card> inputDeck) {
    for(int i = 0; i < inputDeck.size(); i++) {
        std::cout << inputDeck[i].suit << " ";
        std::string specialtyCard;
        if (i % 13 == 0) {
            specialtyCard = "Ace";
        } else if (i % 13 == 10) {
            specialtyCard = "Jack";
        } else if (i % 13 == 11) {
            specialtyCard = "Queen";
        } else if (i % 13 == 12) {
            specialtyCard = "King";
        } else {
            specialtyCard = "Ordinary";
        }
        if (specialtyCard == "Ordinary") {
            std::cout << inputDeck[i].rank << std::endl;
        } else {
            std::cout << specialtyCard << std::endl;
        }
    }
}

//std::vector<card> decklist = deck();
//for(int i = 0; i < decklist.size(); i++) {
//    std::cout << decklist[i].suit << " " << decklist[i].rank << std::endl;
