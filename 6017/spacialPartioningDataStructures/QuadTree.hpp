//
//  QuadTree.h
//  spacialPartioningDataStructures
//
//  Created by Erik Poole on 6/18/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#pragma once

#include "Point.hpp"
#include <memory>
#include <queue>
#include <vector>



class QuadTree{
public:
    
    QuadTree(std::vector<Point<2>>& points){
        int threshold = 2;
        AABB<2> boundingbox = getBounds(points);
        
        root = std::make_unique<Node>(points, boundingbox, threshold);
    }
    
    std::vector<Point<2>> rangeQuery(const Point<2>& inputPoint, float radius) {
        AABB<2> boundingBox;
        std::vector<Point<2>> retPoints;
        root->RangeQueryRecurse(retPoints, inputPoint, radius);
        
        return retPoints;
    }
    
    std::vector<Point<2>> KNN(const Point<2>& inputPoint, int k) {
        std::vector<Point<2>> retPoints;
        DistanceComparator<2> comparator(inputPoint);
        std::make_heap(begin(retPoints), end(retPoints), comparator);
        
        root->KNNRecurse(retPoints, inputPoint, k);
        
        return retPoints;
    }
    
private:
    
    struct Node{
        std::unique_ptr<Node> nw, ne, sw, se;
        std::vector<Point<2>> points;
        AABB<2> boundingBox, nwBoundingBox, neBoundingBox, swBoundingBox, seBoundingBox;
        bool isLeaf;
        
        Node(std::vector<Point<2>> inputPoints, AABB<2> inputBounds, int threshold) {
            if (inputPoints.size() > threshold) {
                isLeaf = false;
                std::vector<Point<2>> nwPoints, nePoints, swPoints, sePoints;

                std::array<float, 2> centerVal{ {
                    (inputBounds.maxs[0]-inputBounds.mins[0])/2 + inputBounds.mins[0],
                    (inputBounds.maxs[1]-inputBounds.mins[1])/2 + inputBounds.mins[1]} };
                
                nwBoundingBox = inputBounds;
                nwBoundingBox.maxs[0] = centerVal[0];
                nwBoundingBox.mins[1] = centerVal[1];
                
                neBoundingBox = inputBounds;
                neBoundingBox.mins[0] = centerVal[0];
                neBoundingBox.mins[1] = centerVal[1];
                
                seBoundingBox = inputBounds;
                seBoundingBox.mins[0] = centerVal[0];
                seBoundingBox.maxs[1] = centerVal[1];
                
                swBoundingBox = inputBounds;
                swBoundingBox.maxs[0] = centerVal[0];
                swBoundingBox.maxs[1] = centerVal[1];
                
                for (Point<2> point : inputPoints) {
                    if (point[0] > centerVal[0]) {
                        if (point[1] > centerVal[1]) {
                            nePoints.push_back(point);
                        } else {
                            sePoints.push_back(point);
                        }
                    } else {
                        if (point[1] > centerVal[1]) {
                            nwPoints.push_back(point);
                        } else {
                            swPoints.push_back(point);
                        }
                    }
                }
                
                nw = std::make_unique<Node>(nwPoints, nwBoundingBox, threshold);
                ne = std::make_unique<Node>(nePoints, neBoundingBox, threshold);
                sw = std::make_unique<Node>(swPoints, swBoundingBox, threshold);
                se = std::make_unique<Node>(sePoints, seBoundingBox, threshold);
                
            } else {
                isLeaf = true;
                points = inputPoints;
            }
        }
        
        void RangeQueryRecurse(std::vector<Point<2>>& retPoints, const Point<2>& targetPoint, const float radius) {
            
            if (isLeaf) {
                for (Point<2> point: points) {
                    if (distance(point, targetPoint) <= radius) {
                        retPoints.push_back(point);
                    }
                }
            } else {
                if (distance(targetPoint, nwBoundingBox.closestInBox(targetPoint)) <= radius) {
                    nw->RangeQueryRecurse(retPoints, targetPoint, radius);
                }
                if (distance(targetPoint, neBoundingBox.closestInBox(targetPoint)) <= radius) {
                    ne->RangeQueryRecurse(retPoints, targetPoint, radius);
                }
                if (distance(targetPoint, swBoundingBox.closestInBox(targetPoint)) <= radius) {
                    sw->RangeQueryRecurse(retPoints, targetPoint, radius);
                }
                if (distance(targetPoint, seBoundingBox.closestInBox(targetPoint)) <= radius) {
                    se->RangeQueryRecurse(retPoints, targetPoint, radius);
                }
            }
        }
        
        
        void KNNRecurse(std::vector<Point<2>>& retPoints, const Point<2>& targetPoint, const int numNeighbors) {

            if (isLeaf) {
                for (Point<2> point: points) {
                    DistanceComparator<2> comparator(targetPoint);
                    if (retPoints.size() < numNeighbors) {
                        retPoints.push_back(point);
                        std::push_heap(begin(retPoints), end(retPoints), comparator);
                    } else if (distance(point, targetPoint) < distance(retPoints[0], targetPoint)) {
                        std::pop_heap(begin(retPoints), end(retPoints), comparator);
                        retPoints.pop_back();
                        retPoints.push_back(point);
                        std::push_heap(begin(retPoints), end(retPoints), comparator);
                    }
                }
            } else {
                if (retPoints.size() < numNeighbors || distance(targetPoint, nwBoundingBox.closestInBox(targetPoint)) < distance(targetPoint, retPoints[0])) {
                    nw->KNNRecurse(retPoints, targetPoint, numNeighbors);
                }
                if (retPoints.size() < numNeighbors || distance(targetPoint, neBoundingBox.closestInBox(targetPoint)) < distance(targetPoint, retPoints[0])) {
                    ne->KNNRecurse(retPoints, targetPoint, numNeighbors);
                }
                if (retPoints.size() < numNeighbors || distance(targetPoint, swBoundingBox.closestInBox(targetPoint)) < distance(targetPoint, retPoints[0])) {
                    sw->KNNRecurse(retPoints, targetPoint, numNeighbors);
                }
                if (retPoints.size() < numNeighbors || distance(targetPoint, seBoundingBox.closestInBox(targetPoint)) < distance(targetPoint, retPoints[0])) {
                    se->KNNRecurse(retPoints, targetPoint, numNeighbors);
                }
            }
        }
        
    };
    
    std::unique_ptr<Node> root;
    
};
