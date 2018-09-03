//
//  main.cpp
//  FunctionPractice
//
//  Created by Erik Poole on 8/24/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>
#include <cmath>
#include <ctime>

//input 2 numbers as sides of right angle triangle
//outpus hypotenuse of those numbers
double hypotenuseFinder(double side1, double side2){
    double hypotenuse = std::sqrt(side1*side1+side2*side2);
    return hypotenuse;
}

//input velocity and angle in radians
//outpus x-velocity component
double xVelFinder(double speed, double radAngle) {
    double xVel = speed * std::cos(radAngle);
    return xVel;
}

//input velocity and angle in radians
//outpus y-velocity component
double yVelFinder(double speed, double radAngle) {
    double yVel = speed * std::sin(radAngle);
    return yVel;
}

//input number
//outputs boolean 'true' if number is even, boolean 'false' otherwise
bool isEven(int inputNumber) {
    return inputNumber % 2 == 0;
}

//input number
//outputs boolean 'true' if number is prime, boolean 'false' otherwise
bool isPrime(int inputnumber) {
    for(int i = 2; i < inputnumber; i++) {
        if (inputnumber % i == 0 || inputnumber <= 1) {
            return false;
        }
    }
    return true;
}


int main(int argc, const char * argv[]) {

    std::cout << "Welcome to the Function Practice Lab!" << std::endl;
    
    std::cout << "Please enter two right-angle side lengths of a triangle: " << std::endl;
    double side1;
    double side2;
    std::cin >> side1 >> side2;
    
    double hypotenuse = std::sqrt(side1*side1+side2*side2);
    std::cout << "Hypotenuse: " << hypotenuse << std::endl << std::endl;
 

    std::cout << "Please enter your speed, followed by your angle of travel in radians:" << std::endl;
    double speed;
    double radAngle;
    std::cin >> speed >> radAngle;
    
    double xVel = speed * std::cos(radAngle);
    double yVel = speed * std::sin(radAngle);
    std::cout << "Your x velocity: " << xVel << std::endl;
    std::cout << "Your y velocity: " << yVel << std::endl;
    std::cout << std::endl;

    //copied from https://en.cppreference.com/w/cpp/chrono/c/time
    //3 functions called - time(), asctime(), localtime()
    std::time_t result = std::time(nullptr);
    std::cout << std::asctime(std::localtime(&result)) << result << " seconds since the Epoch\n";
    std::cout << std::endl;
    
    //testing user functions below -
    std::cout << "Hypotenuse of sides with length 3 & 4: " << hypotenuseFinder(3, 4) << std::endl;
    
    std::cout << "X Velocity of speed 10 & angle 3: " <<xVelFinder(10, 3) << std::endl;
    std::cout << "Y Velocity of speed 10 & angle 3: " <<yVelFinder(10, 3) << std::endl;
    
    std::cout << "Is 5 even? " << isEven(5) << std::endl;
    std::cout << "Is 12 even? " << isEven(12) << std::endl;
    
    std::cout << "Enter a positive integer to check for prime status: " << std::endl;
    int primeinput;
    std::cin >> primeinput;
    if (isPrime(primeinput) == true) {
        std::cout << primeinput << " is prime!" << std::endl;
    } else {
        std::cout << primeinput << " isn't prime." << std::endl;
    }
}
