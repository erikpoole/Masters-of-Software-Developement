//
//  main.cpp
//  MyString-ClassWork
//
//  Created by Erik Poole on 9/13/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>


#include "MyString.hpp"

int main(int argc, const char * argv[]) {

    MyString ms1;
    MyString ms2("hello");
    
    MyString ms3 = ms2;
    MyString ms4;
    ms4 = ms2;
    
    cout << ms2 << endl;
    cout << ms2.cstring() << endl;
    cout << ms2[0] << endl;
    ms2[0] = 'j';
    cout << ms2[0] << endl;
    cout << ms2 << endl;
    
    ms2 += MyString(" world");
    cout << ms2 << endl;
    
    cout << ms2 + myString(" tour") << endl;
    
}
