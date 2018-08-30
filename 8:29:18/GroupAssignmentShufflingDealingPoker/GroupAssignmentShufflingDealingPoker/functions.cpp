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
            int randomNumber = rand() % 52;
            numberVerifier++;
            card oldCard = inputDeck[currentLocation];
            card nextCard = inputDeck[randomNumber];
            inputDeck[currentLocation] = nextCard;
            inputDeck[randomNumber] = oldCard;
            currentLocation = randomNumber;
            //std::cout << currentLocation << std::endl;
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


bool isStraight(const std::vector<card>& inputDeck) {
    //creates "unsortedHand" vector based off of input rank values of inputDeck
    std::vector<int> unsortedHand;
    for(int i = 0; i < 5; i++) {
        unsortedHand.push_back(inputDeck[i].rank);
    }

    //nested loop to compare each value in "unsortedHand" against all others, then take the lowest value and place it into a new vector "sortedHand", then replace lowest value with '9999', an arbitrarily high value.  this sorts the vector from lowest value to highest
    std::vector<int> sortedHand;
    int smallestValue = unsortedHand[0];
    int smallestLocation = 0;
    for (int i = 0; i < unsortedHand.size(); i++) {
        for (int j = 0; j < unsortedHand.size(); j++) {
            if (unsortedHand[j] < smallestValue) {
                smallestValue = unsortedHand[j];
                smallestLocation = j;
            }
        }
        sortedHand.push_back(smallestValue);
        unsortedHand[smallestLocation] = 9999;
        smallestValue = 9999;
    }
    
    //checks each value in "sortedHand" against the next in order and will return true if every value is exactly one higher than the next
    for (int i = 0; i < sortedHand.size() - 1; i++) {
        if (sortedHand[i] + 1 != sortedHand[i+1]) {
            return false;
        }
    }
    return true;
}


bool isStraightFlush(const std::vector<card>& inputDeck) {
    return (isFlush(inputDeck) && isStraight(inputDeck));
}


bool isRoyalFlush(const std::vector<card>& inputDeck) {
    for (int i = 0; i < 5; i++) {
        if (inputDeck[i].rank < 10) {
            return false;
        }
    }
    return (isStraightFlush(inputDeck));
}


bool isFullHouse(const std::vector<card>& inputDeck) {
    int firstCardRank = inputDeck[0].rank;
    int firstCardCount = 0;
    for (int i = 0; i < 5; i++) {
        if (inputDeck[i].rank == firstCardRank) {
            firstCardCount++;
        }
    }
    
    int secondCardRank;
    for (int i = 0; i < 5; i++) {
        if (inputDeck[i].rank != firstCardRank) {
            secondCardRank = inputDeck[i].rank;
            break;
        }
    }
    int secondCardCount = 0;
    for (int i = 0; i < 5; i++) {
        if (inputDeck[i].rank == secondCardRank) {
            secondCardCount++;
        }
    }
    return (firstCardCount + secondCardCount == 5);
}
