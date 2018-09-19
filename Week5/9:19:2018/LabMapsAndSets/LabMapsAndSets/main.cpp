//
//  main.cpp
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
#include <set>
#include <map>



int main(int argc, const char * argv[]) {

    if (argc != 2) {
        std::cout << "Invalid Command Line Input!" << std::endl << std::endl;
        std::cout << "Please try again and enter only the file you would like to analyze. " << std::endl;
        return 0;
    }
    
    std::string requestedFile = argv[1];
    std::ifstream book(requestedFile);
    std::vector<std::string> words;
    std::string currentWord;
    
    while (book >> currentWord) {
        words.push_back(currentWord);
    }
    
    std::set<std::string> uniqueWords;
    std::map<std::string, int> wordNum;
    
    for (std::string specificWord : words) {
        uniqueWords.insert(specificWord);
        if (wordNum.find(specificWord) == wordNum.end()) {
            wordNum.insert(std::pair<std::string, int>(specificWord, 1));
        } else {
            wordNum[specificWord]++;
        }
    }
    
    std::cout << "All Words: " << words.size() << std::endl;
    std::cout << "Unique Words: " << uniqueWords.size() << std::endl;
    std::cout << std::endl;
    std::cout << "Occurances of \"Fang\": " << wordNum["Fang"] << std::endl;
    std::cout << "Occurances of \"the\": " << wordNum["the"] << std::endl;
 
}





