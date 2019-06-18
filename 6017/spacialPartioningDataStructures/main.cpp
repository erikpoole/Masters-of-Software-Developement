//
//  main.cpp
//  spacialPartioningDataStructures
//
//  Created by Erik Poole on 6/11/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#include <iostream>
#include "Generators.hpp"
#include "DumbSolver.hpp"
#include "BucketKNN.hpp"
#include "KDTree.hpp"

int main(int argc, const char * argv[]) {
    
    UniformGenerator<3> uniGen(0, 10);
    TrialData<3> data = getTrialData<3>(10, 10, uniGen);
    
    std::array<float, 3> pointValue{ {5, 5, 5} };
    Point<3> searchPoint;
    searchPoint.point = pointValue;
    
    DumbSolver<3> dumbSolver(data.testing);
    BucketKNN<3> bucketModel(data.testing, 3);
    KDTree<3> kdModel(data.testing);
    
    std::vector<Point<3>> dumbRangePoints = dumbSolver.rangeQuery(searchPoint, 4);
    std::vector<Point<3>> bucketRangePoints = bucketModel.rangeQuery(searchPoint, 4);
    std::vector<Point<3>> kdRangePoints = kdModel.rangeQuery(searchPoint, 4);
    
    std::vector<Point<3>> dumbKNNPoints = dumbSolver.KNN(searchPoint, 3);
    std::vector<Point<3>> bucketKNNPoints = bucketModel.KNN(searchPoint, 3);
    std::vector<Point<3>> kdKNNPoints = kdModel.KNN(searchPoint, 3);


    std::cout << "\nDumb RangeQuery Points:\n";
    for (Point<3> point : dumbRangePoints) {
        std::cout << point << "\n";
    }
    
    std::cout << "\nBucket RangeQuery Points:\n";
    for (Point<3> point : bucketRangePoints) {
        std::cout << point << "\n";
    }
    
    std::cout << "\nKD RangeQuery Points:\n";
    for (Point<3> point : kdRangePoints) {
        std::cout << point << "\n";
    }
    
    
    std::cout << "\nDumb KNN Points:\n";
    for (Point<3> point : dumbKNNPoints) {
        std::cout << point << "\n";
    }

    std::cout << "\nBucket KNN Points:\n";
    for (Point<3> point : bucketKNNPoints) {
        std::cout << point << "\n";
    }
    
    std::cout << "\nKD KNN Points:\n";
    for (Point<3> point : kdKNNPoints) {
        std::cout << point << "\n";
    }
    
}
