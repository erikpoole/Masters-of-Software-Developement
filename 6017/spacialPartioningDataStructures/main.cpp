//
//  main.cpp
//  spacialPartioningDataStructures
//
//  Created by Erik Poole on 6/11/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#include <iostream>
#include <fstream>
#include <unistd.h>

#include "Generators.hpp"
#include "Stopwatch.hpp"

#include "DumbSolver.hpp"
#include "BucketKNN.hpp"
#include "KDTree.hpp"
#include "QuadTree.hpp"

template<int Dimension>
static void GenerateBucketData(std::ofstream &file, int numNeighbors, int numTrials, int structurePoints, Stopwatch &timer) {
    GaussianGenerator<Dimension> gauGen(0, 100);
    TrialData<Dimension> gauData = getTrialData<Dimension>(structurePoints, numTrials, gauGen);
    BucketKNN<Dimension> bucketModel(gauData.testing, 10);
    
    double totalTime = 0;
    
    usleep(100000);
    
    for (int i = 0; i < numTrials; i++) {
        timer.start();
        bucketModel.KNN(gauData.testing[i], numNeighbors);
        totalTime += timer.stop();
    }
    double timeTaken = totalTime / numTrials * 1000000;
    
    file << "Bucket," << numNeighbors << "," << structurePoints << "," << Dimension << "," << timeTaken << "\n";
//    std::cout << Dimension << ", " << timeTaken << "\n";
}

template<int Dimension>
static void GenerateKDData(std::ofstream &file, int numNeighbors, int numTrials, int structurePoints, Stopwatch &timer) {
    GaussianGenerator<Dimension> gauGen(0, 100);
    TrialData<Dimension> gauData = getTrialData<Dimension>(structurePoints, numTrials, gauGen);
    KDTree<Dimension> kdModel(gauData.testing);
    
    double totalTime = 0;
    
    usleep(100000);
    
    for (int i = 0; i < numTrials; i++) {
        timer.start();
        kdModel.KNN(gauData.testing[i], numNeighbors);
        totalTime += timer.stop();
    }
    double timeTaken = totalTime / numTrials * 1000000;
    
    file << "KD," << numNeighbors << "," << structurePoints << "," << Dimension << "," << timeTaken << "\n";
//    std::cout << Dimension << ", " << timeTaken << "\n";
}

template<int Dimension>
static void GenerateQuadData(std::ofstream &file, int numNeighbors, int numTrials, int structurePoints, Stopwatch &timer) {
    GaussianGenerator<Dimension> gauGen(0, 100);
    TrialData<Dimension> gauData = getTrialData<Dimension>(structurePoints, numTrials, gauGen);
    QuadTree quadModel(gauData.testing);
    
    double totalTime = 0;
    
    usleep(100000);
    
    for (int i = 0; i < numTrials; i++) {
        timer.start();
        quadModel.KNN(gauData.testing[i], numNeighbors);
        totalTime += timer.stop();
    }
    double timeTaken = totalTime / numTrials * 1000000;
    
    file << "Quad," << numNeighbors << "," << structurePoints << "," << Dimension << "," << timeTaken << "\n";
//    std::cout << Dimension << ", " << timeTaken << "\n";
}


int main(int argc, const char * argv[]) {
    
    std::string argument = "";
    if (argc > 1) {
        argument = argv[1];
    }
    
    if (argument == "-test") {
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
    } else if (argument == "-generate"){
        Stopwatch timer;
        int numTrials = 100;
        
        std::ofstream file;
        file.open("data/k&n.csv");
        file << "AnalysisType,NumNeighbors,NumPoints,NumDimensions,TimeMicroSeconds\n";
        std::cout << "Generating Data\n";
        
        for (int numNeighbors = 10; numNeighbors < 110; numNeighbors+=10) {
            for (int structurePoints = 1000; structurePoints < 11000; structurePoints+=1000) {
                std::cout << numNeighbors << ", " << structurePoints << "\n";
//                GenerateBucketData<1>(file, numNeighbors, numTrials, structurePoints, timer);
//                GenerateBucketData<2>(file, numNeighbors, numTrials, structurePoints, timer);
                GenerateBucketData<2>(file, numNeighbors, numTrials, structurePoints, timer);
//                GenerateBucketData<4>(file, numNeighbors, numTrials, structurePoints, timer);
//                GenerateBucketData<5>(file, numNeighbors, numTrials, structurePoints, timer);
//                GenerateBucketData<6>(file, numNeighbors, numTrials, structurePoints, timer);
//                GenerateBucketData<7>(file, numNeighbors, numTrials, structurePoints, timer);
//                GenerateBucketData<8>(file, numNeighbors, numTrials, structurePoints, timer);
//                GenerateBucketData<9>(file, numNeighbors, numTrials, structurePoints, timer);
//                GenerateBucketData<10>(file, numNeighbors, numTrials, structurePoints, timer);
//                std::cout << "Buckets Done\n";

//                GenerateKDData<1>(file, numNeighbors, numTrials, structurePoints, timer);
//                GenerateKDData<2>(file, numNeighbors, numTrials, structurePoints, timer);
                GenerateKDData<2>(file, numNeighbors, numTrials, structurePoints, timer);
//                GenerateKDData<4>(file, numNeighbors, numTrials, structurePoints, timer);
//                GenerateKDData<5>(file, numNeighbors, numTrials, structurePoints, timer);
//                GenerateKDData<6>(file, numNeighbors, numTrials, structurePoints, timer);
//                GenerateKDData<7>(file, numNeighbors, numTrials, structurePoints, timer);
//                GenerateKDData<8>(file, numNeighbors, numTrials, structurePoints, timer);
//                GenerateKDData<9>(file, numNeighbors, numTrials, structurePoints, timer);
//                GenerateKDData<10>(file, numNeighbors, numTrials, structurePoints, timer);
//                std::cout << "KD Done\n";

                GenerateQuadData<2>(file, numNeighbors, numTrials, structurePoints, timer);
//                std::cout << "Quad Done\n";
            }
        }
        file.close();

        
    } else {
        std::cout << "Provide an argument, either '-generate' or '-test'\n";
    }
    
    
}
