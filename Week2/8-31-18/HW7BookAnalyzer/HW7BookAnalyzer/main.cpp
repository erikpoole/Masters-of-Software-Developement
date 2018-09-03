//
//  main.cpp
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

int main(int argc, const char * argv[]) {
    
    if (argc != 3) {
        std::cout << "Invalid Command Line Input!" << std::endl << std::endl;
        std::cout << "Please try again and enter the file you would like to analyze followed by the word you'd like to look for." << std::endl;
        return 0;
    }
    
    std::string requestedFile = argv[1];
    std::string requestedWord = argv[2];
    
    std::ifstream book(requestedFile);
    std::vector<std::string> words;
    std::string currentWord;
    
    //two extra "words" added at the beginning and end of the vector to effectively capture the first and last words of the book
    words.push_back("");
    while (book >> currentWord) {
        words.push_back(currentWord);
    }
    words.push_back("");
    
    if (words.size() <= 2) {
        std::cout << "No file with that tile found in the present working directory!" << std::endl << std::endl;
        std::cout << "Please try agin and select a different file name." << std::endl;
        return 0;
    }
    
    
    std::cout << "Title: " << findTitle(words) << std::endl;
    std::cout << "Author: " << findAuthor(words) << std::endl;
    //words.size() - 2 because two empty strings were added to the front and back of the vector
    std::cout << "Total Number of Words: " << words.size()-2 << std::endl;
    std::cout << "Total Number of Characters (non-space): " << characterCount(words) << std::endl;
    
    std::cout << "Shortest Word(s): ";
    for (std::string smallword : shortestWord(words)) {
        std::cout << smallword << " ";
    }
    std::cout << std::endl;
    std::cout << "Longest Word(s): ";
    for (std::string bigword : largestWord(words)) {
        std::cout << bigword << " ";
    }
    std::cout << std::endl;

    keywordAndNumber analyzedOutput = keywordAnalyzer(words, requestedWord);
    int keywordCount = analyzedOutput.numberKeywords;
    std::vector<keyword> vectorKeywords = analyzedOutput.allKeywords;
    
    std::cout << "Your word " << requestedWord << " appears " << keywordCount << " times: " << std::endl;
    for (keyword eachKeyword : vectorKeywords) {
        std::cout << "\t at " << eachKeyword.locationPercentage << "%: \"" << eachKeyword.threeWords << "\"" << std::endl;
    }
    
}
