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
#include "BucketKNN.hpp"

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


TEST_CASE( "BucketKNN", "BucketKNN" ) {
    std::vector<Point<1>> data;
    for (float i = 0; i < 10; i++) {
        std::array<float, 1> pointValue{ {i} };
        Point<1> workingPoint;
        workingPoint.point = pointValue;
        data.push_back(workingPoint);
    }
    
    DumbSolver<1> dumbSolver(data);
    BucketKNN<1> bucketModel(data, 3);
    
    std::vector<Point<1>> dumbRange = dumbSolver.rangeQuery(data[0], 1);
    std::vector<Point<1>> bucketRange = bucketModel.rangeQuery(data[0], 1);
    for (float i = 0; i < dumbRange.size(); i++) {
        REQUIRE(dumbRange[i].point == bucketRange[i].point);
    }
    
    std::vector<Point<1>> dumbKNN = dumbSolver.KNN(data[3], 1);
    std::vector<Point<1>> bucketKNN = bucketModel.KNN(data[3], 1);
    for (float i = 0; i < dumbRange.size(); i++) {
        REQUIRE(dumbRange[i].point == bucketRange[i].point);
    }
    
    
}

