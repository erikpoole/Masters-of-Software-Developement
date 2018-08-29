//
//  fuctions.cpp
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
        for (int j = 2; j <= 14; j++) {
            currentCard.rank = j;
            listOfCards.push_back(currentCard);
        }
    }
    return listOfCards;
}

void printDeck(const std::vector<card>& inputDeck) {
    for(int i = 0; i < inputDeck.size(); i++) {
        std::cout << inputDeck[i].suit << " ";
        std::string specialtyCard;
        if (inputDeck[i].rank == 11) {
            specialtyCard = "Jack";
        } else if (inputDeck[i].rank == 12) {
            specialtyCard = "Queen";
        } else if (inputDeck[i].rank == 13) {
            specialtyCard = "King";
        } else if (inputDeck[i].rank == 14) {
            specialtyCard = "Ace";
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

void shuffleDeck(std::vector<card>& inputDeck) {
    int currentLocation = 0;
    for(int i = 0; i < inputDeck.size(); i++) {
        int numberVerifier = -1;
        while (numberVerifier == -1) {
            int randomNumber = rand();
            if (randomNumber <= 51) {
                numberVerifier++;
                card oldCard = inputDeck[currentLocation];
                card nextCard = inputDeck[randomNumber];
                inputDeck[currentLocation] = nextCard;
                inputDeck[randomNumber] = oldCard;
                currentLocation = randomNumber;
                std::cout << currentLocation << std::endl;
            }
        }
    }
}


bool isFlush(const std::vector<card>& inputDeck) {
    bool flush = true;
    for(int i = 0; i < 4; i++) {
        if (inputDeck[i].suit != inputDeck[i+1].suit) {
            flush = false;
        }
    }
    return flush;
}

/*
bool isStraight(const std::vector<card>& inputDeck) {
    std::vector<int> sortedHand;
    int highestSortedHand = 0;
    int lowValue = inputDeck[0].rank;
    for(int i = 0; i<5; i++) {
        if (inputDeck[i].rank < lowValue) {
            lowValue = inputDeck[i].rank;
        }
        sortedHand.push_back(lowValue);
        highestSortedHand = sortedHand.back();
        std::cout << sortedHand.back() << std::endl;
    }

    
    for(int i = 0; i<5; i++) {
        if (sortedHand[i] + 1 != sortedHand[i+1]) {
            return false;
        }
    }
    return true;
}
*/

/*
bool isStraight(const std::vector<card>& inputDeck) {
    bool tester1 = false;
    bool tester2 = false;
    
    int lowValue
    
    for(int i = 0; i<5; i++) {
        for(int j = 0; j<5; j++) {
            if (inputDeck[i].rank + 1 == inputDeck[j].rank) {
                tester2 = true;
            }
        }
    }
}
*/

bool isStraight(const std::vector<card>& inputDeck) {
    std::vector<int> unsortedHand;
    for(int i = 0; i < 5; i++) {
        unsortedHand.push_back(inputDeck[i].rank);
    }
    std::vector<int> sortedHand;
    int smallestValue = 15;
    int smallestLocation;
    for (int i = 0; i < unsortedHand.size(); i++) {
        for (int i = 0; i < unsortedHand.size(); i++) {
            if (unsortedHand[i] < smallestValue) {
                smallestValue = unsortedHand[i];
                smallestLocation = i;
            }
        }
        unsortedHand[smallestLocation] = 99;
        sortedHand.push_back(smallestValue);
    }
    for (int i = 0; i < sortedHand.size(); i++) {
            std::cout << sortedHand[i] << std::endl;
    }
    return true;
}
