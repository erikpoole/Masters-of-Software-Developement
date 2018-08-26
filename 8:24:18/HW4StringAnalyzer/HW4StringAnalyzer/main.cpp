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
    
void testFunction(char input) {
    std::cout << isTerminator(input) << std::endl;
    std::cout << isPunctuation(input) << std::endl;
    std::cout << isVowel(input) << std::endl;
    std::cout << isConsonant(input) << std::endl;
    std::cout << isLetter(input) << std::endl;

}


int main(int argc, const char * argv[]) {

    std::cout << "Hello and welcome to the String Analzyer!" << std::endl << "Please enter a couple of sentences: " << std::endl;
    std::string userString;
    getline(std::cin, userString);
    std::cout << std::endl;
    
    std::cout << userString << std::endl;
    std::cout << "Number of words: " << numWords(userString) << std::endl;
    std::cout << "Number of sentences: " << numSentences(userString) << std::endl;
    
    testFunction('Y');
}
