//
//  fraction.hpp
//  GAFractions
//
//  Created by Erik Poole on 9/11/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#ifndef fraction_hpp
#define fraction_hpp

#include <stdio.h>

#include <iostream>

using namespace std;

//class with two fields - _n (numerator) & _d (denominator) that represents a fraction
class fraction {
public:
    //defines fraction of 0/1 if no argument given, otherwise defines fraction based on given numerator and denominator
    fraction();
    fraction(long numerator, long denominator);
    
    //performs arithmatic on class fraction based on passed argument
    fraction plus(fraction rhs);
    fraction minus(fraction rhs);
    fraction times(fraction rhs);
    fraction dividedBy(fraction rhs);
    fraction reciprocal();
    //converts class fraction into appropriate string (N/D) output or as a double decimal value
    string toString();
    double toDouble();
    
private:
    //fiels representing numerator & demoninator for class fraction;
    long _n;
    long _d;
    
    //calculates greatest common denominator and utilizes that in reduce method. Reduce is called during class construction and anytime arithmatic is done
    long calculateGCD();
    void reduce();
};




#endif /* fraction_hpp */
