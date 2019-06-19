# Notes On Implementation

## Iterator stuff
```
Iter start, stop; //If I have 2 iterators

//I can subtract them to find how many elements are in between
int size = stop - start; 

//I can get add a number to get an iterator to some other element
Iter middle = start + size/2;

//I can derefence it to get the value
Point middleValue = *middle;

//find first quartile and partition
CompareBy<0> compareByX; //See the Points.hpp file

//Find the first quartile and partition accordingly
std::nth_element(start, start + size/4, end, compareByX);
Point quartileValue = *(start + size/4);
```

## Mapping a point to a bucket

To figure out which bucket a point is in, I recommend using a 2-step process.

First, figure out the "row" and "column" of the bucket that the point is in.  Basically, this requires you to compute a "Point" whose values are ints.  This can be computed with essentially:  (p - min)/bucketSize.  All of those are arrays, and everything is computed element-wise.

Next, you'll need to convert that point into an array index.  I recommend storing your buckets like this:

```C++
using Bucket = vector<Point>; //just lets us write Bucket in our code
vector<Bucket> points(pow(numDivisions, Dimension));
```

so we have a flattened array.  To convert a "row and column" into an index into the array, we can use something like the following pseudocode:

```C++
int bucketCoordsToIndex(Point p){
int index = 0;
for i = 0 .. dimension:
index += clamp((int)p[i], 0 .. dim -1)* pow(numDivisions, Dimensions - i - 1);
return index 
}
```

## Looping over Buckets

Pseudocode for looking at all the buckets when we don't know the number of dimensions in advance (so we can't just use nested for loops)

```
int[] minCoords = findBucket(point - radius)
int[] maxCoords = findBucket(point + radius)

//We want to do this
for each bucket from minCoords to maxCoords:
process(bucket)


//We can do it with something like this
for( coords = minBucket; //start at the beginning
coords != nextBucket(maxCoords, minCoords, maxCoords); //stop once we go past the end
coords = nextBucket(coords, minCoords, maxCoords)  
//advance to the next set of coordinates
){
process(bucket(coords))
}

//pseudocode for next bucket
int[] nextBucket(int[] current, int[] minCoords, int[] maxCoords){
current[lastDimension] ++; //increment the last dimension
for( i = lastDimension; i > 0; —i){
//if we need to "carry"
if(current[i] > maxCoords[i]){
//reset this dimension
current[i] = minCoords[i];
//and add to the next "digit"
current[i -1]++;
} else {
//no more carries... we're done here
break;
}
}
}
```

Notes from class -

How to pick number of division?    
Points per bucket - assuming even distrubution - x = points/buckets
    = Points/(#divisions)^dimensions
    #divisions = N^Ydimensions

BucketSize = (max-min)/#divisions
BucketIndex = int( (p-min) / divisions)
    
    
How to place points in buckets -
    Bucket size = length in dimension you're looking in
        Point class for each dimension you're looking at
        
Corner value = minX and minY


Start by building dumb data structure, loop through all points
    Start with ten points
    
Range Query first then KNN then trees






Translation of 3D object to 1D array of buckets -
Cut into strips along axes

Think of entries in array as being skipped for each dimension
Powers of numbers of divisions (x, y, z) - x^0 + y^1 + z^2

where x, y, z are lengths

'stride' how far you must skip to go from one entry to the next


pointtobucketcoords - use clamp, cares about length of buckets
bucketcoordstoindex 


Range query - use bounding box (add and subtract radius from each dimension)

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


KNN - use range query in loop until size is appropriate, narrow down based on distance to point
Picking starting value - 





For Trees -

std::nth_element for splits

Use CompareBy<DIM> structs
    x = 0,
    etc.


Private KNNRecurse
Private RangeQueryRecurse


//output data -

//scaling for data structure (n number of points)
//different values of k relative to dimension

//Do 2-3 dimensions for an "n" and "k" works

buckets vs dimension
