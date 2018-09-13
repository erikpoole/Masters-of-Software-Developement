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
    
    //performs arithmatic on class fraction based on passed argument - includes operator overloads
    fraction plus(fraction rhs) const;
    fraction& operator += (const fraction& rhs);
    fraction minus(fraction rhs) const;
    fraction& operator -= (const fraction& rhs);
    fraction times(fraction rhs) const;
    fraction& operator *= (const fraction& rhs);
    fraction dividedBy(fraction rhs) const;
    fraction& operator /= (const fraction& rhs);
    fraction reciprocal() const;
    //converts class fraction into appropriate string (N/D) output or as a double decimal value
    string toString() const;
    double toDouble() const;
    
    bool operator == (const fraction& rhs) const;
    bool operator < (fraction rhs);
    bool operator > (fraction rhs);
    
    fraction operator - ();
    
private:
    //fields representing numerator & demoninator for class fraction;
    long _n;
    long _d;
    
    //calculates greatest common denominator and utilizes that in reduce method. Reduce is called during class construction and anytime arithmatic is done
    long calculateGCD() const;
    void reduce() ;
};

fraction operator + (fraction lhs, fraction rhs);
fraction operator - (fraction lhs, fraction rhs);
fraction operator * (fraction lhs, fraction rhs);
fraction operator / (fraction lhs, fraction rhs);

bool operator != (const fraction& lhs, const fraction& rhs);
bool operator <= (fraction lhs, fraction rhs);
bool operator >= (fraction lhs, fraction rhs);

#endif /* fraction_hpp */
