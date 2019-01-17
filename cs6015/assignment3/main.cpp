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
    float x;
    float y;
    
public:
    Point() {
    }
    
    Point(const float& xInput, const float& yInput) {
        x = xInput;
        y = yInput;
    }
    
    float getX() const {return x;}
    float getY() const {return y;}
};

class Line{
private:
    int xOffset;
    int yOffset;
    
public:
    Line() {
    }
    
    Line(const Point& point1, const Point& point2) {
        xOffset = abs(point1.getX() - point2.getX());
        yOffset = abs(point1.getY() - point2.getY());
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
        return diagonalArr[diagonalNumber].getXOffset() + diagonalArr[diagonalNumber].getYOffset();
    }
    int getSideManhattanDistance(int sideNumber) const {
        return sideArr[sideNumber].getXOffset() + sideArr[sideNumber].getYOffset();
    }
    
    double getSlopeofSide(int sideNumber) const {
        //avoids division by zero error because doubles aren't precise, but check just to make sure
        if (sideArr[sideNumber].getXOffset() == 0 || sideArr[sideNumber].getYOffset() == 0) {
            return 0;
        }
        
        double slope = (double) sideArr[sideNumber].getYOffset() / (double) sideArr[sideNumber].getXOffset();
        
        Point point1;
        Point point2;
        if (sideNumber == 3) {
            point1 = pointArr[3];
            point2 = pointArr[0];
        } else {
            point1 = pointArr[sideNumber];
            point2 = pointArr[sideNumber+1];
        }
        
        if (point1.getX() > point2.getX() && point1.getY() < point2.getY()) {
            slope *= -1;
        }
        
        if (point1.getX() < point2.getX() && point1.getY() > point2.getY()) {
            slope *= -1;
        }
        
        return slope;
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

//forced to use doubles..?
bool isRhombus(const Shape& inputShape) {
    for (int i = 0; i < 3; i++) {
        if (inputShape.getSide(i).getLength() - inputShape.getSide(i+1).getLength() > .0001) {
            return false;
        }
    }
    return true;
}

//assumes isParallelogram == true
bool isSquare(const Shape& inputShape) {
    for (int i = 0; i < 3; i++) {
        if (inputShape.getSideManhattanDistance(i) != inputShape.getSideManhattanDistance(i+1)) {
            return false;
        }
    }
    return true;
}

bool isKite(const Shape& inputShape) {
    if (abs(inputShape.getSide(0).getLength() -  inputShape.getSide(1).getLength()) < .0001) {
        if (abs(inputShape.getSide(2).getLength() - inputShape.getSide(3).getLength()) < .0001) {
            return true;
        }
    }
    if (abs(inputShape.getSide(1).getLength() - inputShape.getSide(2).getLength()) < .0001) {
        if (abs(inputShape.getSide(3).getLength() - inputShape.getSide(0).getLength()) < .0001) {
            return true;
        }
    }
    return false;
}

//is defining Kites, and other generic quadrilaterals too...
bool isTrapezoid(const Shape& inputShape) {
//    if (abs(inputShape.getSlopeofSide(0) - inputShape.getSlopeofSide(2)) < .0001) {
//        if (abs(inputShape.getSlopeofSide(1) - inputShape.getSlopeofSide(3)) < .0001) {
//            std::cout << "Rea" << std::endl;
//            return false;
//        }
//    }
//    if (abs(inputShape.getSlopeofSide(1) - inputShape.getSlopeofSide(3)) < .0001) {
//        if (abs(inputShape.getSlopeofSide(0) - inputShape.getSlopeofSide(2)) < .0001) {
//            std::cout << "Rea" << std::endl;
//            return false;
//        }
//    }
    
    if (abs(inputShape.getSlopeofSide(0) - inputShape.getSlopeofSide(2)) < .0001) {
        if (abs(inputShape.getSlopeofSide(1) - inputShape.getSlopeofSide(3)) > .0001) {
            return true;
        }
    }
    if (abs(inputShape.getSlopeofSide(1) - inputShape.getSlopeofSide(3)) < .0001) {
        if (abs(inputShape.getSlopeofSide(0) - inputShape.getSlopeofSide(2)) > .0001) {
            return true;
        }
    }

    
    return false;
}


//****************************************************************************************************
//****************************************************************************************************

int main(int argc, const char * argv[]) {    
    std::string inputString;
    while(std::getline(std::cin, inputString)) {
        std::stringstream stringStream(inputString);
        
        std::string singleInput;
        int inputValueArray[6];
        int *inputValuePointer = inputValueArray;
        while (std::getline(stringStream, singleInput, ' ')){
            *inputValuePointer++ = std::stoi(singleInput);
        }
        
        Point point1(inputValueArray[0], inputValueArray[1]);
        Point point2(inputValueArray[2], inputValueArray[3]);
        Point point3(inputValueArray[4], inputValueArray[5]);
        
        Shape shape(point1, point2, point3);
        
        std::string outputString = "error...";
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
        } else if (isKite(shape)) {
            outputString = "kite";
        } else if (isTrapezoid(shape)) {
            outputString = "trapezoid";
        } else {
            outputString = "quadrilateral";
        }
        std::cout << outputString << std::endl;
    }
}



