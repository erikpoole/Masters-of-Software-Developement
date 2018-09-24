//
//  functions.hpp
//  HWBookAnalyzer2
//
//  Created by Erik Poole on 9/5/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#ifndef functions_hpp
#define functions_hpp

#include <stdio.h>

#include <iostream>
#include <string>
#include <vector>
#include <fstream>
#include <cmath>

struct importantWord {
    int locationPercentage;
    std::string threeWords;
};

std::string findAuthorOrTitle (const std::vector<std::string>& inputWords, std::string startWord, std::string endWord);

int characterCount(const std::vector<std::string>& inputWords);

std::vector<std::string> shortestWord(const std::vector<std::string>& inputWords);
std::vector<std::string> largestWord(const std::vector<std::string>& inputWords);

std::vector<importantWord> keywordAnalyzer(const std::vector<std::string>& inputWords, const std::string& userWord);
std::vector<importantWord> properNameFinder(const std::vector<std::string>& inputWords);


bool isDecimalWord(const std::string& inputWord);
void cipher(const std::string& fileName, const std::vector<std::string>& inputWords, const std::string& cipherString);


#endif /* functions_hpp */
