//
//  functions.cpp
//  LabStructPractice
//
//  Created by Erik Poole on 8/28/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include "functions.hpp"

#include <vector>

std::vector<Politician> democratFinder(std::vector<Politician> input) {
    std::vector<Politician> democratList;
    for (Politician p : input) {
        if (p.isDemocrat == true) {
            democratList.push_back(p);
            //testing std::cout << p.name << std::endl;
        }
    }
    return democratList;
}

std::vector<Politician> fedRepublicanFinder(std::vector<Politician> input) {
    std::vector<Politician> fedRepublicanList;
    for (Politician p : input) {
        if (p.isDemocrat == false && p.isFederal == true) {
            fedRepublicanList.push_back(p);
            //testing std::cout << p.name << std::endl;
        }
    }
    return fedRepublicanList;
}

int whipVotes(std::vector<Politician> inputList, bool isFederalBill) {
    int democratPoints = 0;
    if (isFederalBill == true) {
        for (Politician p : inputList) {
            if (p.isFederal == true && p.isDemocrat == true) {
                democratPoints++;
            } else if (p.isFederal == true && p.isDemocrat == false) {
                democratPoints--;
            }
        }
    } else {
        for (Politician p: inputList) {
            if (p.isFederal == false && p.isDemocrat == true) {
                democratPoints++;
            } else if (p.isFederal == false && p.isDemocrat == false) {
                democratPoints--;
            }
        }
    }
    if (democratPoints > 0) {
        return 1;
    } else if (democratPoints < 0) {
        return -1;
    } else {
        return 0;
    }
}
