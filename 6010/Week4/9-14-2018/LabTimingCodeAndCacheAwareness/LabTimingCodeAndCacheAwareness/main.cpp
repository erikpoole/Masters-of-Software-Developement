//
//  main.cpp
//  LabTimingCodeAndCacheAwareness
//
//  Created by Erik Poole on 9/14/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>
#include <vector>

#include "functions.hpp"
#include "Stopwatch.hpp"

using namespace std;

int main(int argc, const char * argv[]) {

    double rows = 20000, cols = 20000;
    Stopwatch time;
    
    vector<double> myGrid = makeVector(rows, cols);
    
    time.start();
    cout << sum1(myGrid, rows, cols) << endl;
    cout << "Time Counting Across: " << time.stop() << endl << endl;
    
    time.start();
    cout << sum2(myGrid, rows, cols) << endl;
    cout << "Time Counting Down: " << time.stop() << endl << endl;

}
