//
//  main.cpp
//  homework1
//
//  Created by Erik Poole on 1/15/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#include <iostream>
#include <sys/time.h>

extern "C" {
    void sayHello();
    void myPuts(const char* inputString, int inputLength);
    timeval myGTOD();
}

int main(int argc, const char * argv[]) {
    sayHello();
    myPuts("", 4);
    
    timeval myTime;
    timeval theirTime;
    
    myTime = myGTOD();
    gettimeofday(&theirTime, NULL);
    
    std::cout << myTime.tv_sec - theirTime.tv_sec << std::endl;
    std::cout <<myTime.tv_sec << "\n";
    std::cout <<theirTime.tv_sec << "\n";
}
