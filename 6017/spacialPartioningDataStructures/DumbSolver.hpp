//
//  DumbSolver.hpp
//  spacialPartioningDataStructures
//
//  Created by Erik Poole on 6/11/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#ifndef DumbSolver_hpp
#define DumbSolver_hpp

#include <stdio.h>
#include <algorithm>
#include <vector>

#include "Point.hpp"


template<int Dimension> //The dimension of the points.  This can be used like any other constant within.
class DumbSolver{
public:

    std::vector<Point<Dimension>> points;

    
    DumbSolver(const std::vector<Point<Dimension>>& points) {
        this->points = points;
    }
    
    std::vector<Point<Dimension>> rangeQuery(const Point<Dimension>& p, float radius) const{
        std::vector<Point<Dimension>> ret;
        
        for (Point<Dimension> workingPoint : points) {
            if (distance(workingPoint, p) < radius) {
                ret.push_back(workingPoint);
            }
        }
        return ret;
    }
    
    std::vector<Point<Dimension>> KNN(const Point<Dimension>& p, int k) const{
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
        }
        return ret;
    }

private:

};



#endif /* DumbSolver_hpp */
