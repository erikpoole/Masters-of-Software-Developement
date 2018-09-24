//
//  functions.cpp
//  HWBookAnalyzer2
//
//  Created by Erik Poole on 9/5/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>
#include <string>
#include <vector>
#include <fstream>
#include <cmath>

#include "functions.hpp"


std::string findAuthorOrTitle (const std::vector<std::string>& inputWords, std::string startWord, std::string endWord) {
    std::string author;
    for (int i = 0; i < inputWords.size(); i++) {
        if (inputWords[i] == startWord) {
            for (int j = i+1; j < i + 100; j++) {
                if (inputWords[j] == endWord) {
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


///////////////////////////////////////////////////////////////////////////


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


///////////////////////////////////////////////////////////////////////////

 
std::vector<importantWord> keywordAnalyzer(const std::vector<std::string>& inputWords, const std::string& userWord) {
    double currentCharacters = 0;
    double totalCharacters = characterCount(inputWords);
    std::vector<importantWord> outputKeywords;
    importantWord oneKeyword;
    for(int i = 1; i < inputWords.size(); i++) {
        if (inputWords[i] == userWord) {
            oneKeyword.threeWords = (inputWords[i-1] + " " + inputWords[i] + " " + inputWords[i+1]);
            //round() found at www.cplusplus.com/reference/cmath/round/
            oneKeyword.locationPercentage = round((currentCharacters / totalCharacters) * 100);
            outputKeywords.push_back(oneKeyword);
        }
        currentCharacters += inputWords[i].size();
    }
    return outputKeywords;
}

std::vector<importantWord> properNameFinder(const std::vector<std::string>& inputWords) {
    double currentCharacters = 0;
    double totalCharacters = characterCount(inputWords);
    std::vector<importantWord> outputProperNames;
    importantWord oneName;
    for(int i = 1; i < inputWords.size(); i++) {
        
        bool isCapitalized = inputWords[i][0] >= 'A' && inputWords[i][0] <= 'Z';
        bool isAfterPunctuation = inputWords[i-1].back() == '.' || inputWords[i-1].back() == '?' || inputWords[i-1].back() == '!';
        
        if (isCapitalized && !isAfterPunctuation) {
            oneName.threeWords = (inputWords[i-1] + " " + inputWords[i] + " " + inputWords[i+1]);
            //round() found at www.cplusplus.com/reference/cmath/round/
            oneName.locationPercentage = round((currentCharacters / totalCharacters) * 100);
            outputProperNames.push_back(oneName);
        }
        currentCharacters += inputWords[i].size();
    }
    return outputProperNames;
}


///////////////////////////////////////////////////////////////////////////


bool isDecimalWord(const std::string& inputWord) {
    std::string negFixWord = inputWord;
    if (inputWord[0] == '-') {
        negFixWord = inputWord.substr(1);
    }
    
    bool areAllNumbers = true;
    for (char eachChar : negFixWord) {
        if (!isdigit(eachChar)) {
            areAllNumbers = false;
        }
    }
    return areAllNumbers;
}

std::string decimalToBinary(std::string inputString){
    bool isNegative = false;
    std::string fixNegString = inputString;
    if (inputString[0] == '-') {
        isNegative = true;
        fixNegString = inputString.substr(1);
    }
    
    bool isBinary = true;
        for (char c : fixNegString) {
            if (c != '0' && c != '1') {
                isBinary = false;
            }
        }
    if (isBinary) {
        return fixNegString;
    }
    
    std::vector<char> charVector;
    for (int i = 0; i < fixNegString.size(); i++) {
        char specificChar = fixNegString[i] - '0';
        charVector.push_back(specificChar);
    }
    
    int outputInt = 0;
    for (int i = 0 ; i < charVector.size(); i++){
        int convertedInt = charVector[i] * pow(10, charVector.size() - (i+1));
        outputInt += convertedInt;
    }
    
    int charCount = 0;
    int dividingInput = outputInt;
    if (dividingInput == 0) {
        charCount = 1;
    } else {
        while (dividingInput >= 1) {
            dividingInput /= 2;
            charCount++;
        }
    }
    
    std::string outputString;
    if (isNegative) {
        outputString.push_back('-');
    }
    
    for (int i = charCount-1; i >= 0; i--) {
        int intValue;
        char charValue;
        intValue = outputInt / (pow(2, i));
        charValue = intValue + '0';
        outputInt -= (intValue * pow(2,i));
        outputString.push_back(charValue);
    }
    
    return outputString;
}

std::string encodeWord(const std::string& inputword, int cipher) {
    std::string changedWord;
    for (char individualChar : inputword) {
        if (isupper(individualChar)) {
            char encodedChar = ((individualChar + cipher - 'A') % 26);
            while (encodedChar < 0) {
                encodedChar += 26;
            }
            encodedChar += 'A';
            changedWord.push_back(encodedChar);
        } else if (islower(individualChar)) {
            char encodedChar = ((individualChar + cipher - 'a') % 26);
            while (encodedChar < 0) {
                encodedChar += 26;
            }
            encodedChar += 'a';
            changedWord.push_back(encodedChar);
        } else {
            changedWord.push_back(individualChar);
        }
    }
    //std::cout << changedWord << std::endl;
    return changedWord;
}


///////////////////////////////////////////////////////////////////////////


void cipher(const std::string& fileName, const std::vector<std::string>& inputWords, const std::string& cipherString) {
    
    std::string fixedNegCipher = cipherString;
    bool isNegative = false;
    if (cipherString[0] == '-') {
        isNegative = true;
        fixedNegCipher = cipherString.substr(1);
    }
    int cipherInt = 0;
    for (char individualChar : fixedNegCipher) {
        individualChar = individualChar - '0';
        cipherInt += individualChar;
    }
    if (isNegative) {
        cipherInt *= -1;
    }
    
    
    std::ofstream outputFile(fileName + " -encode " + cipherString);
    for (std::string individualWord : inputWords) {
        if (isDecimalWord(individualWord)) {
            outputFile << decimalToBinary(individualWord);
        } else {
            //std::cout << encodeWord(individualWord, cipherInt) << std::endl;
            outputFile << encodeWord(individualWord, cipherInt);
        }
        outputFile << " ";
    }
}






