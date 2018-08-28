//
//  stringFunctions.cpp
//  LabStringAnalyzerSplit
//
//  Created by Erik Poole on 8/27/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <string>
#include "stringFunctions.hpp"
#include "charFunctions.hpp"

int numWords(std::string inputString) {
    int wordCount = 0;
    int i = 0;
    while (i <= inputString.size()-1) {
        //if character at position 'i' is a letter then loops through all following characters until a ' ' chacter is reached or the string ends
        if (isLetter(inputString[i])) {
            while (inputString[i] != ' ' && i <= inputString.size()-1) {
                i++;
            }
            wordCount++;
        }
        i++;
    }
    return wordCount;
}

int numSentences(std::string inputString) {
    int sentenceCount = 0;
    int i = 0;
    while (i <= inputString.size()-1) {
        if (isLetter(inputString[i])) {
            while (isTerminator(inputString[i]) == false && i <= inputString.size()-1) {
                i++;
            }
            sentenceCount++;
        }
        i++;
    }
    return sentenceCount;
}

int numVowels(std::string inputString) {
    int vowelCount = 0;
    for (int i = 0; i < inputString.size(); i++) {
        if (isVowel(inputString[i])) {
            vowelCount++;
        }
    }
    return vowelCount;
}

int numConsonants(std::string inputString) {
    int consonantCount = 0;
    for (int i = 0; i < inputString.size(); i++) {
        if (isConsonant(inputString[i])) {
            consonantCount++;
        }
    }
    return consonantCount;
}

double averageWordLength(std::string inputString) {
    return double (numVowels(inputString) + numConsonants(inputString)) / numWords(inputString);
}

double averageVowelsPerWord(std::string inputString) {
    return double (numVowels(inputString))/numWords(inputString);
}
