//
//  fraction.cpp
//  GAFractions
//
//  Created by Erik Poole on 9/11/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include "fraction.hpp"

#include <iostream>

using namespace std;


///////////////////////////////////////////////////////////////////////////


fraction::fraction() {
    _n = 0;
    _d = 1;
}

fraction::fraction(long inputNumerator, long inputDenominator) {
    _n = inputNumerator;
    _d = inputDenominator;
    
    reduce();
    
    //will make fraction positive if both _n & _d are negative, or will move minus sign to _n from _d if only _d was negative
    if (_d < 0) {
        _n *= -1;
        _d *= -1;
    }
}


///////////////////////////////////////////////////////////////////////////


//arcane wizardry provided by assignment description
long fraction::calculateGCD() {
    long gcd = _n;
    long remainder = _d;
    while(remainder != 0) {
        long temp = remainder;
        remainder = gcd % remainder;
        gcd = temp;
    }
    return gcd;
}

void fraction::reduce() {
    long gcd = calculateGCD();
    _n /= gcd;
    _d /= gcd;
}


///////////////////////////////////////////////////////////////////////////


fraction fraction::plus(fraction inputFraction) {
    long newNumerator = (_n * inputFraction._d) + (inputFraction._n * _d);
    long newDenominator = _d * inputFraction._d;
    
    return fraction(newNumerator, newDenominator);
}

fraction fraction::minus(fraction inputFraction) {
    long newNumerator = (_n * inputFraction._d) - (inputFraction._n * _d);
    long newDenominator = _d * inputFraction._d;
    
    return fraction(newNumerator, newDenominator);
}

fraction fraction::times(fraction inputFraction) {
    long newNumerator = _n * inputFraction._n;
    long newDenominator = _d * inputFraction._d;

    return fraction(newNumerator, newDenominator);
}

fraction fraction::dividedBy(fraction inputFraction) {
    //reciprocal of divisor then multiplication of fractions
    fraction reciprocalFraction = inputFraction.reciprocal();
    long newNumerator = _n * reciprocalFraction._n;
    long newDenominator = _d * reciprocalFraction._d;
    
    return fraction(newNumerator, newDenominator);
}

fraction fraction::reciprocal() {
    long newNumerator = _d;
    long newDumerator = _n;
    
    return fraction(newNumerator, newDumerator);
}


///////////////////////////////////////////////////////////////////////////


string fraction::toString() {
    string string_n = to_string(_n);
    string string_d = to_string(_d);
    
    return string_n + "/" + string_d;
}

double fraction::toDouble() {
    return double(_n)/double(_d);
}

