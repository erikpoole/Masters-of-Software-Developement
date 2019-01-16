//
//  main.cpp
//  assignment3
//
//  Created by Erik Poole on 1/15/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#include <iostream>
#include <sstream>

class Point{
private:
    int x;
    int y;
    
public:
    Point() {
        x = NULL;
        y = NULL;
    }
    
    Point(const int& xInput, const int& yInput) {
        x = xInput;
        y = yInput;
    }
    
    int getX() {return x;}
    int getY() {return y;}
};

class Shape{
private:
    Point pointArr[4];
    
public:
    Shape(const Point& inputPoint1, const Point& inputPoint2, const Point& inputPoint3) {
        pointArr[0] = Point(0, 0);
        pointArr[1] = Point(inputPoint1);
        pointArr[2] = Point(inputPoint2);
        pointArr[3] = Point(inputPoint3);
    }
    
    Point getPoint(int pointNumber) {return pointArr[pointNumber];}

};

//****************************************************************************************************
//****************************************************************************************************

int main(int argc, const char * argv[]) {
    while(true) {
        std::string inputString;
        std::getline(std::cin, inputString);
        std::stringstream stringStream(inputString);
        
        std::string singleInput;
        int inputValueArray[6];
        int *inputValuePointer = inputValueArray;
        while (std::getline(stringStream, singleInput, ' ')){
            *inputValuePointer++ = std::stof(singleInput);
        }
        
        Point point1(inputValueArray[0], inputValueArray[1]);
        Point point2(inputValueArray[2], inputValueArray[3]);
        Point point3(inputValueArray[4], inputValueArray[5]);
        
        Shape shape(point1, point2, point3);
        
        std::cout << shape.getPoint(0).getX() << std::endl;
        std::cout << shape.getPoint(0).getY() << std::endl;
        std::cout << shape.getPoint(1).getX() << std::endl;
        std::cout << shape.getPoint(1).getY() << std::endl;
        std::cout << shape.getPoint(2).getX() << std::endl;
        std::cout << shape.getPoint(2).getY() << std::endl;
        std::cout << shape.getPoint(3).getX() << std::endl;
        std::cout << shape.getPoint(3).getY() << std::endl;
    }
}

