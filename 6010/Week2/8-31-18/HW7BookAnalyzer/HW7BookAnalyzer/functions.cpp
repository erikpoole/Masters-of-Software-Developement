//
//  functions.cpp
//  HW7BookAnalyzer
//
//  Created by Erik Poole on 8/31/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>
#include <string>
#include <vector>
#include <fstream>
#include <cmath>

#include "functions.hpp"

std::string findTitle (const std::vector<std::string>& inputWords) {
    std::string title;
    for (int i = 0; i < inputWords.size(); i++) {
        if (inputWords[i] == "Title:") {
            for (int j = i+1; j < i + 100; j++) {
                if (inputWords[j] == "Author:") {
                    return title;
                }
                title += inputWords[j];
                title += " ";
            }
            return "Unknown";
        }
    }
    return "Unknown";
}

std::string findAuthor (const std::vector<std::string>& inputWords) {
    std::string author;
    for (int i = 0; i < inputWords.size(); i++) {
        if (inputWords[i] == "Author:") {
            for (int j = i+1; j < i + 100; j++) {
                if (inputWords[j] == "Release") {
                    return author;
                }
                author += inputWords[j];
                author += " ";
            }
            return "Unknown";
        }
    }
    return "Unknown";
}


int characterCount(const std::vector<std::string>& inputWords) {
    int count = 0;
    for (std::string word : inputWords) {
        count += word.size();
    }
    return count;
}


std::vector<std::string> shortestWord(const std::vector<std::string>& inputWords) {
    int smallestWordCount = int(inputWords[1].size());
    std::vector<std::string> smallestWordList;
    
    for (int i = 1; i < inputWords.size()-1; i++) {
        //if new word's size is less than the previous low than vector is started over with the new smallest word
        if (inputWords[i].size() < smallestWordCount) {
            smallestWordCount = int(inputWords[i].size());
            //clear() found at www.cplusplus.com/reference/vector/vector/clear/
            smallestWordList.clear();
            smallestWordList.push_back(inputWords[i]);
        // if new word is of equal size to the previous low, check to see if the new word has already been added and, if not, add new equally small word
        } else if (inputWords[i].size() == smallestWordCount) {
            bool notInList = true;
            for (std::string wordInList : smallestWordList) {
                if (inputWords[i] == wordInList) {
                    notInList = false;
                }
            }
            if (notInList == true) {
                smallestWordList.push_back(inputWords[i]);
            }
        }
    }
    return smallestWordList;
}

std::vector<std::string> largestWord(const std::vector<std::string>& inputWords) {
    int biggestWordCount = int(inputWords[1].size());
    std::vector<std::string> biggestWordList;
    
    for (int i = 1; i < inputWords.size()-1; i++) {
        //if new word's size is less than the previous low than vector is started over with the new smallest word
        if (inputWords[i].size() > biggestWordCount) {
            biggestWordCount = int(inputWords[i].size());
            //clear() found at www.cplusplus.com/reference/vector/vector/clear/
            biggestWordList.clear();
            biggestWordList.push_back(inputWords[i]);
            // if new word is of equal size to the previous low, check to see if the new word has already been added and, if not, add new equally small word
        } else if (inputWords[i].size() == biggestWordCount) {
            bool notInList = true;
            for (std::string wordInList : biggestWordList) {
                if (inputWords[i] == wordInList) {
                    notInList = false;
                }
            }
            if (notInList == true) {
                biggestWordList.push_back(inputWords[i]);
            }
        }
    }
    return biggestWordList;
}

keywordAndNumber keywordAnalyzer(const std::vector<std::string>& inputWords, const std::string& userWord) {
    double currentCharacters = 0;
    double totalCharacters = characterCount(inputWords);
    std::vector<keyword> outputKeywords;
    keyword oneKeyword;
    int keywordCount = 0;
    for(int i = 1; i < inputWords.size(); i++) {
        if (inputWords[i] == userWord) {
            oneKeyword.threeWords = (inputWords[i-1] + " " + inputWords[i] + " " + inputWords[i+1]);
            //round() found at www.cplusplus.com/reference/cmath/round/
            oneKeyword.locationPercentage = round((currentCharacters / totalCharacters) * 100);
            outputKeywords.push_back(oneKeyword);
            keywordCount++;
        }
        currentCharacters += inputWords[i].size();
    }
    return {outputKeywords, keywordCount};
}

