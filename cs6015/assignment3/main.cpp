//
//  main.cpp
//  assignment3
//
//  Created by Erik Poole on 1/15/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#include <iostream>
#include <sstream>

#include <cmath>

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
    
    int getX() const {return x;}
    int getY() const {return y;}
};

class Line{
private:
    int xOffset;
    int yOffset;
    
public:
    Line() {
        xOffset = NULL;
        yOffset = NULL;
    }
    
    Line(const Point& point1, const Point& point2) {
        xOffset = point1.getX() - point2.getX();
        yOffset = point1.getY() - point2.getY();
    }
    
    int getXOffset() const {return xOffset;}
    int getYOffset() const {return yOffset;}
    
    double getLength() {
        return sqrt((double) xOffset*xOffset + (double) yOffset*yOffset);
    }
};


class Shape{
private:
    Point pointArr[4];
    Line sideArr[4];
    Line diagonalArr[2];
    
public:
    Shape(const Point& inputPoint1, const Point& inputPoint2, const Point& inputPoint3) {
        //points
        pointArr[0] = Point(0, 0);
        pointArr[1] = Point(inputPoint1);
        pointArr[2] = Point(inputPoint2);
        pointArr[3] = Point(inputPoint3);
        
        //sides
        sideArr[0] = Line(pointArr[0], pointArr[1]);
        sideArr[1] = Line(pointArr[1], pointArr[2]);
        sideArr[2] = Line(pointArr[2], pointArr[3]);
        sideArr[3] = Line(pointArr[3], pointArr[0]);
        
        //diagonals
        diagonalArr[0] = Line(pointArr[0], pointArr[2]);
        diagonalArr[1] = Line(pointArr[1], pointArr[3]);
    }
    
    Point getPoint(const int& pointNumber) const {return pointArr[pointNumber];}
    Line getSide(const int& sideNumber) const {return sideArr[sideNumber];}
    
    Line getDiagonal(int diagonalNumber) const {return diagonalArr[diagonalNumber];}
    Point getDiagonalCenter(int diagonalNumber) const {
        float middleX = (float) (pointArr[diagonalNumber].getX() + pointArr[diagonalNumber+2].getX()) / 2;
        float middleY = (float) (pointArr[diagonalNumber].getY() + pointArr[diagonalNumber+2].getY()) / 2;
        return Point(middleX, middleY);
    }
    int getDiagonalManhattanDistance(int diagonalNumber) const {
        return abs(diagonalArr[diagonalNumber].getXOffset()) + abs(diagonalArr[diagonalNumber].getYOffset());
    }
    int getSideManhattanDistance(int sideNumber) const {
        return abs(sideArr[sideNumber].getXOffset()) + abs(sideArr[sideNumber].getYOffset());
    }
};


//****************************************************************************************************
//****************************************************************************************************

bool isParallelogram(const Shape& inputShape) {
    //overload '==' to make more readable
    if (inputShape.getDiagonalCenter(0).getX() == inputShape.getDiagonalCenter(1).getX()) {
        if (inputShape.getDiagonalCenter(0).getY() == inputShape.getDiagonalCenter(1).getY()) {
            return true;
        }
    }
    return false;
}

//assumes isParallelogram == true
bool isRectangle(const Shape& inputShape) {
    if (inputShape.getDiagonalManhattanDistance(0) == inputShape.getDiagonalManhattanDistance(1)) {
        return true;
    }
    return false;
}

//forced to use floats..?
bool isRhombus(const Shape& inputShape) {
    for (int i = 0; i < 3; i++) {
        if (inputShape.getSide(i).getLength() - inputShape.getSide(i+1).getLength() > .0001) {
            return false;
        }
    }
    return true;
}

//assumes isParallelogram == ture
bool isSquare(const Shape& inputShape) {
    for (int i = 0; i < 3; i++) {
        if (inputShape.getSideManhattanDistance(i) != inputShape.getSideManhattanDistance(i+1)) {
            return false;
        }
    }
    return true;
}



//****************************************************************************************************
//****************************************************************************************************

int main(int argc, const char * argv[]) {
    std::cout << "Hello!" << std::endl;
    
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
        
        //        std::cout << "Points: " << std::endl;
        //        std::cout << shape.getPoint(0).getX() << std::endl;
        //        std::cout << shape.getPoint(0).getY() << std::endl;
        //        std::cout << shape.getPoint(1).getX() << std::endl;
        //        std::cout << shape.getPoint(1).getY() << std::endl;
        //        std::cout << shape.getPoint(2).getX() << std::endl;
        //        std::cout << shape.getPoint(2).getY() << std::endl;
        //        std::cout << shape.getPoint(3).getX() << std::endl;
        //        std::cout << shape.getPoint(3).getY() << std::endl;
        //
        //        std::cout << "Sides :" << std::endl;
        
        std::string outputString = "something else ...";
        if (isParallelogram(shape)) {
            outputString = "parallelogram";
            if (isRhombus(shape)) {
                outputString = "rhombus";
            }
            if (isRectangle(shape)) {
                outputString = "rectangle";
                if (isSquare(shape)) {
                    outputString = "square";
                }
            }

        }
        std::cout << outputString << std::endl;
    }
}


