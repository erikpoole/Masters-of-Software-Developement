//
//  QuadTree.h
//  spacialPartioningDataStructures
//
//  Created by Erik Poole on 6/18/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#ifndef QuadTree_h
#define QuadTree_h


//pseduocode for quadtree -

/*
 2D data structure
 
 leaf nodes hold points
 branching nodes hold four children
 
 AABB
 bool isLeaf
 store information for both possibilities
 
 leaf node child values - null
 leaf node points - vector
 
 branching nodes point values - null
 unique pointers for children (SE, SW, NE, NW)
 
 
 Node(iter begin, iter end) {
 if (end - begin) < maxLeafSize {
    isLeaf = true
    points = vector(begin, end)
 } else {
    std::partition() or make four vectors and loop through points
 }
 
 */


#endif /* QuadTree_h */
