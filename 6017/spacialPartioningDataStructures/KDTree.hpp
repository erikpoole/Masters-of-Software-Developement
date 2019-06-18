#pragma once

#include "Point.hpp"
#include <memory>
#include <queue>
#include <iostream>


template<int Dimension>
class KDTree{
public:
    
    KDTree(std::vector<Point<Dimension>>& points){
        root = std::make_unique<Node<0>> (Node<0>(begin(points), end(points)));
    }
    
    //    For Trees -
    //
    //    std::nth_element for splits
    //
    //        Use CompareBy<DIM> structs
    //        x = 0, y = 1
    //        etc.
    //
    //
    //        Private KNNRecurse
    //        Private RangeQueryRecurse
    
    std::vector<Point<Dimension>> rangeQuery(const Point<Dimension>& inputPoint, float radius) {
        AABB<Dimension> boundingBox;
        for (int i = 0; i < Dimension; i++) {
            boundingBox.maxs[i] = INT_MAX;
            boundingBox.mins[i] = INT_MIN;
        }
        std::vector<Point<Dimension>> retPoints;
        root->RangeQueryRecurse(boundingBox, retPoints, inputPoint, radius);
        
        return retPoints;
    }
    
    std::vector<Point<Dimension>> KNN(const Point<Dimension>& inputPoint, int k) {
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
            
            return;
            
            
            //check point
            //add point if in range
            //find left AABB
            //if distance(targetPoint, closestinbox(node.point)) <= r)
            // if children: recurse(left)
            //find right AABB
            //if distance(targetPoint, closestinbox(node.point)) <= r)
            //if children: recurse right
        }
        
        
        //fix signature
        void KNNRecurse(Node<Dimension> workingNode, AABB<Dimension>, Point<Dimension> targetPoint, float radius) {
            //start at root, add node to list of nearest neighbors
            //keep adding to list until full
            //recurse to children and either add to list or discount all children of that node based on current worst neighbor in list
        }
        
    };
    
    std::unique_ptr<Node<0>> root;
    
};

