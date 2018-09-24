//
//  MyString.hpp
//  MyString-ClassWork
//
//  Created by Erik Poole on 9/13/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#ifndef MyString_hpp
#define MyString_hpp

#include <iostream>


using namespace std;

#include <stdio.h>

class MyString{
public:
    MyString();
    MyString(const char* cstr);
    MyString(const MyString& rhs);

    MyString& operator=(const MyString& rhs);
    
    //valid as long as MyString isn't modified
    const char* cstring() const {return data;}
    
    char operator [] (int index) const {return data[index];}
    char& operator [] (int index) {return data[index];}
    
    MyString& operator += (MyString& rhs);

    
    ~MyString();
    
private:
    char* data;
    int capacity;
};

inline MyString operator + (MyString lhs, const MyString& rhs) {
    lhs += rhs;
    return lhs;
}

ostream& operator << (ostream& outs, const MyString& ms);           //Always the same, don't worry about specifics

int StringLength(const char* cstr);
void copyString(const char* src, char* dest);



#endif /* MyString_hpp */
