//
//  catch.cpp
//  spacialPartioningDataStructures
//
//  Created by Erik Poole on 6/11/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#define CATCH_CONFIG_MAIN

#include "catch.hpp"
#include "Generators.hpp"
#include "DumbSolver.cpp"

//    UniformGenerator<1> uniGen(0, 10);
//
//    TrialData<1> data = getTrialData<1>(10, 10, uniGen);
//


TEST_CASE( "DumbSolver", "DumbSolver" ) {
    std::vector<Point<1>> data;
    for (float i = 0; i < 10; i++) {
        std::array<float, 1> pointValue{ {i} };
        Point<1> workingPoint;
        workingPoint.point = pointValue;
        data.push_back(workingPoint);
    }
    
    DumbSolver<1> dumbSolver(data);

    std::vector<Point<1>> rangeOuput = dumbSolver.rangeQuery(data[0], 3);
    for (float i = 0; i < rangeOuput.size(); i++) {
        REQUIRE(rangeOuput[i].point == std::array<float, 1>{ {i} });
    }
    
    std::vector<Point<1>> knnOuput = dumbSolver.KNN(data[3], 3);
    for (float i = 0; i < knnOuput.size(); i++) {
        REQUIRE(knnOuput[i].point == std::array<float, 1>{ {i+2} });
    }

    
    
}
