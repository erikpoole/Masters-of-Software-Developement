//
//  main.cpp
//  spacialPartioningDataStructures
//
//  Created by Erik Poole on 6/11/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

//Range Query first then KNN

#include <iostream>
#include "Generators.hpp"
#include "DumbSolver.cpp"

int main(int argc, const char * argv[]) {
    
    UniformGenerator<1> uniGen(0, 10);
    
    TrialData<1> data = getTrialData<1>(10, 10, uniGen);
    
    DumbSolver<1> dumbSolver(data.testing);
    

    
    for (int i = 0; i < data.testing.size(); i++) {
        std::cout << data.testing[i] << "\n";
    }
    
    std::cout << "\n";
    
    Point<1> chosenPoint = getTrialData<1>(1, 1, uniGen).testing[0];
    std::cout << chosenPoint << "\n";
    
    std::cout << "\n";
    
    std::vector<Point<1>> nearestPoints = dumbSolver.KNN(chosenPoint, 3);
    for (int i = 0; i < nearestPoints.size(); i++) {
        std::cout << nearestPoints[i] << "\n";
    }
}
