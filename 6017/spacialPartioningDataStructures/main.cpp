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
#include "QuadTree.hpp"

int main(int argc, const char * argv[]) {
    
    UniformGenerator<2> uniGen(0, 10);
    TrialData<2> data = getTrialData<2>(10, 10, uniGen);
    
    std::array<float, 2> pointValue{ {5, 5} };
    Point<2> searchPoint;
    searchPoint.point = pointValue;
    
    DumbSolver<2> dumbSolver(data.testing);
    BucketKNN<2> bucketModel(data.testing, 2);
    KDTree<2> kdModel(data.testing);
    QuadTree quadModel(data.testing);
    
    std::vector<Point<2>> dumbRangePoints = dumbSolver.rangeQuery(searchPoint, 3);
    std::vector<Point<2>> bucketRangePoints = bucketModel.rangeQuery(searchPoint, 3);
    std::vector<Point<2>> kdRangePoints = kdModel.rangeQuery(searchPoint, 3);
    std::vector<Point<2>> quadRangePoints = quadModel.rangeQuery(searchPoint, 3);
    
    std::vector<Point<2>> dumbKNNPoints = dumbSolver.KNN(searchPoint, 3);
    std::vector<Point<2>> bucketKNNPoints = bucketModel.KNN(searchPoint, 3);
    std::vector<Point<2>> kdKNNPoints = kdModel.KNN(searchPoint, 3);
    std::vector<Point<2>> quadKNNPoints = quadModel.KNN(searchPoint, 3);


    std::cout << "\nDumb RangeQuery Points:\n";
    for (Point<2> point : dumbRangePoints) {
        std::cout << point << "\n";
    }
    
    std::cout << "\nBucket RangeQuery Points:\n";
    for (Point<2> point : bucketRangePoints) {
        std::cout << point << "\n";
    }
    
    std::cout << "\nKD RangeQuery Points:\n";
    for (Point<2> point : kdRangePoints) {
        std::cout << point << "\n";
    }
    
    std::cout << "\nQuad RangeQuery Points:\n";
    for (Point<2> point : quadRangePoints) {
        std::cout << point << "\n";
    }
    
    
    std::cout << "\nDumb KNN Points:\n";
    for (Point<2> point : dumbKNNPoints) {
        std::cout << point << "\n";
    }

    std::cout << "\nBucket KNN Points:\n";
    for (Point<2> point : bucketKNNPoints) {
        std::cout << point << "\n";
    }

    std::cout << "\nKD KNN Points:\n";
    for (Point<2> point : kdKNNPoints) {
        std::cout << point << "\n";
    }
    
    std::cout << "\nQuad KNN Points:\n";
    for (Point<2> point : quadKNNPoints) {
        std::cout << point << "\n";
    }
    
}
