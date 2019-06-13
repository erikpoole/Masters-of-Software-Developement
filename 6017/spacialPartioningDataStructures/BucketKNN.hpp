#pragma once

#include "Point.hpp"
#include <vector>
#include <math.h>
#include <iostream>

template<int Dimension> //The dimension of the points.  This can be used like any other constant within.
class BucketKNN{
    using Bucket = std::vector<Point<Dimension>>;
    
    /*
     
     BucketCoords - nextBucket(current, min, max)
     
        ret = current
        ret.y ++
        if (ret.y > max.y) {
        ret.y = min.y
        ret.x ++
     }
     
     while dimension value doesn't surpass max increment next dimension and continue
     
     return ret
     
     
     Test that looping through all buckets this way hits every element of the array
     
     USE bounding box method given /divided by divisions
     */
    
    
    
    
public:
    
    BucketKNN(const std::vector<Point<Dimension>>& inputPoints, int numDivisions) {
        boundingBox = getBounds(inputPoints);
        divisions = numDivisions;

        for (int i = 0; i < Dimension; i++) {
            bucketLengths[i] = (boundingBox.maxs[i] - boundingBox.mins[i]) / divisions;
        }

        buckets.resize(std::pow(divisions, Dimension));
        
//        std::cout << bucketLengths[0] << ", " << bucketLengths[1] << "\n";
        
        for (Point<Dimension> point : inputPoints) {
//            std::cout << BucketCoordinatesToIndex(PointToBucketCoordinates(point)) << "\n\n";
            buckets[BucketCoordinatesToIndex(PointToBucketCoordinates(point))].push_back(point);
        }
        
        for (int i = 0; i < buckets.size(); i++) {
            std::cout << i << "\n";
            for (Point<Dimension> point : buckets[i]) {
                std::cout << point << "\n";
            }
        }
    }
    
    
    std::vector<Point<Dimension>> rangeQuery(const Point<Dimension>& inputPoint, float radius) {
        
        Point<Dimension> minPoint;
        Point<Dimension> maxPoint;
        
        for (int i = 0; i < Dimension; i++) {
            minPoint.point[i] = inputPoint.point[i] - radius;
            maxPoint.point[i] = inputPoint.point[i] + radius;
        }
        
        //current = minCoords?
        std::array<int, Dimension> minCoords = PointToBucketCoordinates(minPoint);
        std::array<int, Dimension> maxCoords = PointToBucketCoordinates(maxPoint);
        std::array<int, Dimension> current = PointToBucketCoordinates(minPoint);
        
        std::vector<Point<Dimension>> retPoints;
        
        int maxIndex = BucketCoordinatesToIndex(maxCoords);
        while (BucketCoordinatesToIndex(current) < maxIndex) {
            int workingBucketIndex = BucketCoordinatesToIndex(current);
            Bucket workingBucket = buckets[workingBucketIndex];
            
            for (Point<Dimension> point : workingBucket) {
                bool inside = true;
                for (int i = 0; i < Dimension; i++) {
                    if (std::abs(inputPoint[i] - point[i]) > radius) {
                        inside = false;
                        break;
                    }
                }
                if (inside) {
                    retPoints.push_back(point);
                }
            }
            workingBucket = nextBucket(current, minCoords, maxCoords);
        }
        
        //this is lazy and needs to be fixed
        int workingBucketIndex = BucketCoordinatesToIndex(current);
        Bucket workingBucket = buckets[workingBucketIndex];
        
        for (Point<Dimension> point : workingBucket) {
            bool inside = true;
            for (int i = 0; i < Dimension; i++) {
                if (std::abs(inputPoint[i] - point[i]) > radius) {
                    inside = false;
                    break;
                }
            }
            if (inside) {
                retPoints.push_back(point);
            }
        }
        
        
        
        return retPoints;
    }

        
        
        
//
//        //We want to do this
//        for each bucket from minCoords to maxCoords:
//            process(bucket)
//
//
//            //We can do it with something like this
//            for( coords = minBucket; //start at the beginning
//                coords != nextBucket(maxCoords, minCoords, maxCoords); //stop once we go past the end
//                coords = nextBucket(coords, minCoords, maxCoords)
//                //advance to the next set of coordinates
//                ){
//                process(bucket(coords))
//            }
//
//        //pseudocode for next bucket

//                }
//            }
//        }
    

        
    
    //        int[] nextBucket(int[] current, int[] minCoords, int[] maxCoords){
    //            current[lastDimension] ++; //increment the last dimension
    //            for( i = lastDimension; i > 0; —i){
    //                //if we need to "carry"
    //                if(current[i] > maxCoords[i]){
    //                    //reset this dimension
    //                    current[i] = minCoords[i];
    //                    //and add to the next "digit"
    //                    current[i -1]++;
    //                } else {
    //                    //no more carries... we're done here
    //                    break;
    
    
    std::vector<Point<Dimension> > KNN(const Point<Dimension>& p, int k) const{
//        assert(numPoints >= k);
        return {};
        
    }
    
    
    
    
private:
    AABB<Dimension> boundingBox;
    int divisions;
    std::array<float, Dimension> bucketLengths;
    std::vector<Bucket> buckets;
    
    std::array<int, Dimension> PointToBucketCoordinates(const Point<Dimension>& inputPoint) {
//        std::cout << inputPoint << "\n";
        std::array<int, Dimension> coords;
        
        for (int i = 0; i < Dimension; i++) {
            coords[i] = std::clamp((int) ((inputPoint[i] - boundingBox.mins[i]) / bucketLengths[i]), 0, divisions-1);
        }
        
        return coords;
    }
    
    
    int BucketCoordinatesToIndex(const std::array<int, Dimension>& inputCoords) {
        int index = 0;
        for (int i = 0; i < Dimension; i++) {
//            std::cout << inputCoords[i] * std::pow(divisions, i) << "\n";
            index += inputCoords[i] * std::pow(divisions, i);
//            index += bucketLengths[i] * std::pow(inputCoords[i], i);

        }
        
        return index;
    }
    
    Bucket nextBucket(std::array<int, Dimension>& current, std::array<int, Dimension> minCoords, std::array<int, Dimension> maxCoords) {
        current[0]++;
        for (int i = 0; i < Dimension-1; i++) {
            if (current[i] > maxCoords[i]) {
                current[i] = minCoords[i];
                current[i+1]++;
            } else {
                break;
            }
        }
        int index = BucketCoordinatesToIndex(current);
//        std::cout << "Index: " << index << "\n";
        return buckets[index];
    }
    

};

