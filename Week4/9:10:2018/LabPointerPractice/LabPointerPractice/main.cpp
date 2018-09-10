//
//  main.cpp
//  LabPointerPractice
//
//  Created by Erik Poole on 9/10/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>

using namespace std;

double* copyArray(double* inputpointer, int size) {
    double* newArrayPointer = new double[size];
    
    for (int i = 0; i < size; i++) {
        //newArrayPointer[i] = inputpointer[i];
        *(newArrayPointer+i) = *(inputpointer+i);
    }
    
    return newArrayPointer;
}


int main(int argc, const char * argv[]) {

    int size = 5;
    double arr[size];
    for (int i = 0; i < size; i++) {
        arr[i] = i+5;
    }
    
    cout << "Original Array Sample Values: ";
    for (int i = 0; i < size; i++) {
        cout << arr[i] << " ";
    }
    cout << endl;
    
    double* copiedArrayPointer = copyArray(arr, size);
    
    cout << "Copied Array Sample Values: ";
    for (int i = 0; i < size; i++) {
        cout << copiedArrayPointer[i] << " ";
    }
    cout << endl;
    
    delete copiedArrayPointer;
    copiedArrayPointer = nullptr;
    
}
