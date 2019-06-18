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
#include "DumbSolver.hpp"
#include "BucketKNN.hpp"
#include "KDTree.hpp"

int main(int argc, const char * argv[]) {
    
    UniformGenerator<3> uniGen(0, 10);
    
    TrialData<3> data = getTrialData<3>(10, 10, uniGen);
    
//    DumbSolver<1> dumbSolver(data.testing);
//
//    for (int i = 0; i < data.testing.size(); i++) {
//        std::cout << data.testing[i] << "\n";
//    }
//
//    std::cout << "\n";
//
//    Point<1> chosenPoint = getTrialData<1>(1, 1, uniGen).testing[0];
//    std::cout << chosenPoint << "\n";
//
//    std::cout << "\n";
    
//    std::vector<Point<1>> nearestPoints = dumbSolver.KNN(chosenPoint, 3);
//    for (int i = 0; i < nearestPoints.size(); i++) {
//        std::cout << nearestPoints[i] << "\n";
//    }
    
    
    BucketKNN<3> bucketModel(data.testing, 3);


    std::array<float, 3> pointValue{ {5, 5, 5} };
    Point<3> searchPoint;
    searchPoint.point = pointValue;

    std::vector<Point<3>> bucketPoints = bucketModel.rangeQuery(searchPoint, 3);

    std::cout << "\nBucket RangeQuery Points:\n";
    for (Point<3> point : bucketPoints) {
        std::cout << point << "\n";
    }
//
//    points = bucketModel.KNN(searchPoint, 3);
//
//    std::cout << "\nBucket KNN Points:\n";
//    for (Point<3> point : points) {
//        std::cout << point << "\n";
//    }
    
    KDTree<3> kdTree(data.testing);
    
//    std::array<float, 3> pointValue{ {5, 5, 5} };
//    Point<3> searchPoint;
//    searchPoint.point = pointValue;
    
    std::vector<Point<3>> kdPoints = kdTree.rangeQuery(searchPoint, 3);
    
    std::cout << "\nKD RangeQuery Points:\n";
    for (Point<3> point : kdPoints) {
        std::cout << point << "\n";
    }
    
//        points = kdTree.KNN(searchPoint, 3);
//    
//        std::cout << "\nKD KNN Points:\n";
//        for (Point<3> point : points) {
//            std::cout << point << "\n";
//        }
    
    
    
    
    
}
