//
//  main.cpp
//  assignment3
//
//  Created by Erik Poole on 1/15/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#include <iostream>
#include <sstream>

class point{
private:
    int x;
    int y;
    
public:
    point() {
        x = NULL;
        y = NULL;
    }
    
    point(const int& xInput, const int& yInput) {
        x = xInput;
        y = yInput;
    }
    
    int getX() {return x;}
    int getY() {return y;}
};

class shape{
private:
    point pointArr[4];
    
public:
    shape(const point& inputPoint1, const point& inputPoint2, const point& inputPoint3) {
        pointArr[0] = point(0, 0);
        pointArr[1] = point(inputPoint1);
        pointArr[2] = point(inputPoint2);
        pointArr[3] = point(inputPoint3);
    }
};

//****************************************************************************************************
//****************************************************************************************************

int main(int argc, const char * argv[]) {
    while(true) {
        std::string inputString;
        std::getline(std::cin, inputString);
        std::stringstream stringStream(inputString);
        
        std::string singleInput;
        while (std::getline(stringStream, singleInput, ' ')){
            std::cout << singleInput << std::endl;
        }
    }
}

