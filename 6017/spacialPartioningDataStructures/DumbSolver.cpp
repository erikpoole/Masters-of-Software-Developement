//
//  DumbSolver.cpp
//  spacialPartioningDataStructures
//
//  Created by Erik Poole on 6/11/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#include "DumbSolver.hpp"


//customer::customer(long inputArrivalTime, long inputTimeNeeded) {
//    arrivalTime = inputArrivalTime;
//    timeNeeded = inputTimeNeeded;
//}
//
//
//long customer::getArrivalTime(){
//    return arrivalTime;
//}
//long customer::getTimeNeeded(){
//    return timeNeeded;
//}

template<int Dimension>
DumbSolver<Dimension>::DumbSolver(const std::vector<Point<Dimension>>& points) {
    this->points = points;
}

template<int Dimension>
std::vector<Point<Dimension>> DumbSolver<Dimension>::rangeQuery(const Point<Dimension>& p, float radius) const{
    std::vector<Point<Dimension>> ret;
        
    for (Point<Dimension> workingPoint : points) {
        if (distance(workingPoint, p) < radius) {
            ret.push_back(workingPoint);
        }
    }
    return ret;
}

template<int Dimension>
std::vector<Point<Dimension>> DumbSolver<Dimension>::KNN(const Point<Dimension>& p, int k) const{
    assert(points.size() >= k);
    
    std::vector<Point<Dimension>> ret;
    int count = 0;
    DistanceComparator<Dimension> comparator(p);
    std::make_heap(begin(ret), end(ret), comparator);
        
    for (Point<Dimension> workingPoint : points) {
        if (count < k) {
            count++;
            ret.push_back(workingPoint);
            std::push_heap(begin(ret), end(ret), comparator);
                
        } else if (distance(workingPoint, p) < (distance(ret[0], p))) {
            std::pop_heap(begin(ret), end(ret), comparator);
            ret.pop_back();
            ret.push_back(workingPoint);
            std::push_heap(begin(ret), end(ret), comparator);
        }
        
//        for (int i = 0; i < ret.size(); i++) {
//            std::cout << ret[i] << "\n";
//        }
//        std::cout << "\n";
    }
    return ret;
}
    
    

    

