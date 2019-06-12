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
#include <iostream>

#include "Point.hpp"

#endif /* DumbSolver_hpp */


template<int Dimension> //The dimension of the points.  This can be used like any other constant within.
class DumbSolver{
public:
    
    std::vector<Point<Dimension>> points;
    
    DumbSolver(const std::vector<Point<Dimension>>& points);
    
    std::vector<Point<Dimension>> rangeQuery(const Point<Dimension>& p, float radius) const;
    std::vector<Point<Dimension>> KNN(const Point<Dimension>& p, int k) const;
    

private:

};
