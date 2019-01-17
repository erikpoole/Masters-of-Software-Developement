//
//  main.cpp
//  homework1
//
//  Created by Erik Poole on 1/15/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#include <iostream>

extern "C" {
    void sayHello();
    void myPuts(const char* inputString, int inputLength);
}

int main(int argc, const char * argv[]) {
    sayHello();
    myPuts("poop", 4);



}
