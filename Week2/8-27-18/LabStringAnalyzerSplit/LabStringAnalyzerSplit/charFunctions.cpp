//
//  charFunctions.cpp
//  LabStringAnalyzerSplit
//
//  Created by Erik Poole on 8/27/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include "charFunctions.hpp"

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
