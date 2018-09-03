//
//  functions.hpp
//  HW7BookAnalyzer
//
//  Created by Erik Poole on 8/31/18.
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

struct keyword {
    int locationPercentage;
    std::string threeWords;
};

struct keywordAndNumber {
    std::vector<keyword> allKeywords;
    int numberKeywords;
};

std::string findTitle (const std::vector<std::string>& inputWords);
std::string findAuthor (const std::vector<std::string>& inputWords);

int characterCount(const std::vector<std::string>& inputWords);

std::vector<std::string> shortestWord(const std::vector<std::string>& inputWords);
std::vector<std::string> largestWord(const std::vector<std::string>& inputWords);

keywordAndNumber keywordAnalyzer(const std::vector<std::string>& inputWords, const std::string& userWord);

#endif /* functions_hpp */
