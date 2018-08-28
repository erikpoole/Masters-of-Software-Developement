//
//  main.cpp
//  LabStructPractice
//
//  Created by Erik Poole on 8/28/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>
#include <vector>

#include "functions.hpp"

int main(int argc, const char * argv[]) {

    std::vector<Politician> listOfPoliticians = {{"Greg",true,true},{"Susie",false,true},{"Freddy",true,true},{"Lowly Jim",false,false}};
    
    democratFinder(listOfPoliticians);
    fedRepublicanFinder(listOfPoliticians);
    
    int billVote = whipVotes(listOfPoliticians, false);
    
    if (billVote == -1) {
        std::cout << "The Republicans win!" << std::endl;
    } else if (billVote == 0) {
        std::cout << "It's a tie!" << std::endl;
    } else {
        std::cout << "The Democrats win!" << std::endl;
    }
    
}
