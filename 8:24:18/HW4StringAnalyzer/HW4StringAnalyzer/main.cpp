//
//  main.cpp
//  HW4StringAnalyzer
//
//  Created by Erik Poole on 8/24/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>
#include <string>


bool isTerminator(char c) {
    return (c == '.' || c == '?' || c == '!');
}

bool isPunctuation(char c) {
    return (c == '.' || c == '?' || c == '!' || c == ',');
}

bool isVowel(char c) {
    //checks for uppercase letter and if so converts to lowercase via ASCII trick
    if (c >= 'A' && c <= 'Z') {
        c = c - 'A' + 'a';
    }
    return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y');
}

bool isConsonant(char c) {
    return (!(isTerminator(c) || isPunctuation(c) || isVowel(c) || c == ' '));
}

bool isLetter(char c) {
    return (isVowel(c) || isConsonant(c));
}

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
    for (int i = 0; i <= inputString.size()-1; i++) {
        if (isVowel(inputString[i])) {
            vowelCount++;
        }
    }
    return vowelCount;
}

int numConsonants(std::string inputString) {
    int consonantCount = 0;
    for (int i = 0; i <= inputString.size()-1; i++) {
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

int main(int argc, const char * argv[]) {

    std::cout << "Hello and welcome to the String Analzyer!" << std::endl;
    
    for (int i = 0; i < 1; i = i) {
        std::cout << "Please enter a couple of sentences: " << std::endl;
        std::string userString;
        getline(std::cin, userString);
        std::cout << std::endl;
    
        std::cout << "Number of words: " << numWords(userString) << std::endl;
        std::cout << "Number of sentences: " << numSentences(userString) << std::endl;
        std::cout << "Number of vowels: " << numVowels(userString) << std::endl;
        std::cout << "Number of consonants: " << numConsonants(userString) << std::endl;
        std::cout << "Average word length: " << averageWordLength(userString) << std::endl;
        std::cout << "Average vowels per word: " << averageVowelsPerWord(userString) << std::endl;
        std::cout << std::endl;
    }
}
