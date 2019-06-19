#pragma once

#include "Point.hpp"
#include <memory>
#include <queue>
#include <vector>



template<int Dimension>
class KDTree{
public:
    
    KDTree(std::vector<Point<Dimension>>& points){
        root = std::make_unique<Node<0>> (Node<0>(begin(points), end(points)));
    }
    
    std::vector<Point<Dimension>> rangeQuery(const Point<Dimension>& inputPoint, float radius) {
        AABB<Dimension> boundingBox;
        std::vector<Point<Dimension>> retPoints;
        root->RangeQueryRecurse(boundingBox, retPoints, inputPoint, radius);
        
        return retPoints;
    }
    
    std::vector<Point<Dimension>> KNN(const Point<Dimension>& inputPoint, int k) {
        std::vector<Point<Dimension>> points;
        DistanceComparator<Dimension> comparator(inputPoint);
        std::make_heap(begin(points), end(points), comparator);
        
        AABB<Dimension> boundingBox;
        
        root->KNNRecurse(boundingBox, points, inputPoint, k);
        
        return points;
    }
    
private:

    template<int SplitDimension> //Don't store the split dimension explicitly
    struct Node{
        Point<Dimension> splittingPoint;
        //The children will have the "next" splitting dimension
        //Since this is part of the type, we can't "forget" to set it properly... nice!
        std::unique_ptr<Node< (SplitDimension + 1)%Dimension>>  left, right;
        
        /*
         We'll use iterators to describe the chunk of our points array that belong to this node/subtree
         */
        template<typename Iter>
        Node(Iter begin, Iter end)
        {
            //Our children (if we have any) will use the "next" splitting dimension
            using ChildType = Node<(SplitDimension +1)%Dimension>;
            
            CompareBy<SplitDimension> comparator;
            Iter median = begin + (end-begin)/2;
            std::nth_element(begin, median, end, comparator);
            //            std::cout << "Median Index: " << median << "\n";
            splittingPoint = *median;
            
            if (median-begin != 0) {
                left = std::make_unique<ChildType>(begin, median);
            }
            if (end-(median+1) != 0) {
                right = std::make_unique<ChildType>(median+1, end);
            }
        }
        
        void RangeQueryRecurse(const AABB<Dimension>& boundingBox, std::vector<Point<Dimension>>& points, const Point<Dimension>& targetPoint, const float radius) {
            
            if (distance(splittingPoint, targetPoint) <= radius) {
                points.push_back(splittingPoint);
            }
            
            AABB<Dimension> leftBoundingBox = boundingBox;
            leftBoundingBox.maxs[SplitDimension] = splittingPoint[SplitDimension];
            if (left != nullptr && distance(targetPoint, leftBoundingBox.closestInBox(targetPoint)) <= radius) {
                left->RangeQueryRecurse(leftBoundingBox, points, targetPoint, radius);
            }
            
            AABB<Dimension> rightBoundingBox = boundingBox;
            rightBoundingBox.mins[SplitDimension] = splittingPoint[SplitDimension];
            if (right != nullptr && distance(targetPoint, rightBoundingBox.closestInBox(targetPoint)) <= radius) {
                right->RangeQueryRecurse(rightBoundingBox, points, targetPoint, radius);
            }
        }
        
        
        void KNNRecurse(const AABB<Dimension>& boundingBox, std::vector<Point<Dimension>>& points, const Point<Dimension>& targetPoint, const int numNeighbors) {
            
            DistanceComparator<Dimension> comparator(targetPoint);
            if (points.size() < numNeighbors) {
                points.push_back(splittingPoint);
                std::push_heap(begin(points), end(points), comparator);
            } else if (distance(splittingPoint, targetPoint) < distance(points[0], targetPoint)) {
                std::pop_heap(begin(points), end(points), comparator);
                points.pop_back();
                points.push_back(splittingPoint);
                std::push_heap(begin(points), end(points), comparator);
            }
            
            AABB<Dimension> leftBoundingBox = boundingBox;
            leftBoundingBox.maxs[SplitDimension] = splittingPoint[SplitDimension];
            if (left != nullptr && distance(targetPoint, leftBoundingBox.closestInBox(targetPoint)) <= distance (targetPoint, points[0])) {
                left->KNNRecurse(leftBoundingBox, points, targetPoint, numNeighbors);
            }
            
            AABB<Dimension> rightBoundingBox = boundingBox;
            rightBoundingBox.mins[SplitDimension] = splittingPoint[SplitDimension];
            if (right != nullptr && distance(targetPoint, rightBoundingBox.closestInBox(targetPoint)) <= distance (targetPoint, points[0])) {
                right->KNNRecurse(rightBoundingBox, points, targetPoint, numNeighbors);
            }
        }
        
    };
    
    std::unique_ptr<Node<0>> root;
    
};

