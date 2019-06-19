#pragma once

#include "Point.hpp"
#include <vector>
#include <math.h>

template<int Dimension> //The dimension of the points.  This can be used like any other constant within.
class BucketKNN{
    using Bucket = std::vector<Point<Dimension>>;
    
public:
    
    BucketKNN(const std::vector<Point<Dimension>>& inputPoints, int numDivisions) {
        numPoints = inputPoints.size();
        boundingBox = getBounds(inputPoints);
        divisions = numDivisions;

        for (int i = 0; i < Dimension; i++) {
            bucketLengths[i] = (boundingBox.maxs[i] - boundingBox.mins[i]) / divisions;
        }

        buckets.resize(std::pow(divisions, Dimension));
        
        for (Point<Dimension> point : inputPoints) {
            buckets[BucketCoordinatesToIndex(PointToBucketCoordinates(point))].push_back(point);
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
                if (distance(point, inputPoint) <= radius) {
                    retPoints.push_back(point);
                }
            }
            workingBucket = nextBucket(current, minCoords, maxCoords);
        }
        
        //this is lazy and needs to be fixed
        int workingBucketIndex = BucketCoordinatesToIndex(current);
        Bucket workingBucket = buckets[workingBucketIndex];
        
        for (Point<Dimension> point : workingBucket) {
            if (distance(point, inputPoint) <= radius) {
                retPoints.push_back(point);
            }
        }
        
        
        return retPoints;
    }
    
    std::vector<Point<Dimension>> KNN(const Point<Dimension>& inputPoint, int k) {
//        KNN - use range query in loop until size is appropriate, narrow down based on distance to point
//        Picking starting value -
        
        assert(numPoints >= k);
        
        double smallestLength = bucketLengths[0];
        for (double length : bucketLengths) {
            if (length < smallestLength) {
                smallestLength = length;
            }
        }
        
        std::vector<Point<Dimension>> workingPoints;
        double scaler = .9;
        while (workingPoints.size() < k) {
            workingPoints = rangeQuery(inputPoint, smallestLength*scaler);
            
            scaler *= 2;
        }
        
        DistanceComparator<Dimension> comparator(inputPoint);
        std::sort(begin(workingPoints), end(workingPoints), comparator);
        
        std::vector<Point<Dimension>> retPoints;
        for (int i = 0; i < k; i++) {
            retPoints.push_back(workingPoints[i]);
        }
        
        return retPoints;
    }
    
    
    
    
private:
    long numPoints;
    AABB<Dimension> boundingBox;
    int divisions;
    std::array<double, Dimension> bucketLengths;
    std::vector<Bucket> buckets;
    
    std::array<int, Dimension> PointToBucketCoordinates(const Point<Dimension>& inputPoint) {
        std::array<int, Dimension> coords;
        
        for (int i = 0; i < Dimension; i++) {
            coords[i] = std::clamp((int) ((inputPoint[i] - boundingBox.mins[i]) / bucketLengths[i]), 0, divisions-1);
        }
        
        return coords;
    }
    
    
    int BucketCoordinatesToIndex(const std::array<int, Dimension>& inputCoords) {
        int index = 0;
        for (int i = 0; i < Dimension; i++) {
            index += inputCoords[i] * std::pow(divisions, i);
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
        return buckets[index];
    }
};

