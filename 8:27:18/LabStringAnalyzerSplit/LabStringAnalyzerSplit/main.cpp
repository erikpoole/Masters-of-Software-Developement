//
//  main.cpp
//  LabStringAnalyzerSplit
//
//  Created by Erik Poole on 8/27/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>
#include <string>
#include "charFunctions.hpp"
#include "stringFunctions.hpp"

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
