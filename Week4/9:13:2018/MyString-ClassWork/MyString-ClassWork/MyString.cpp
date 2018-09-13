//
//  MyString.cpp
//  MyString-ClassWork
//
//  Created by Erik Poole on 9/13/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include "MyString.hpp"
#include <algorithm>

//CONSTRUCTOR
MyString::MyString()
:data(new char[1]), capacity(0)
{
    //data = new char[1];                       Moved into initialization List
    data[0] = 0;
}

//CONSTRUCTOR
MyString::MyString(const char* cstr) {
    int length = StringLength(cstr);
    data = new char[length+1];                  //+1 because length counted until we reached 0, but didn't include it
    capacity = length;
    for(int i = 0; i < length; i++) {
        data[i] = cstr[i];
    }
    data[length] = 0;
}

//COPY CONSTRUCTOR
MyString::MyString(const MyString& rhs)
:MyString(rhs.data)
{}

MyString& MyString::operator=(const MyString& rhs) {
    
    /*                                          //Quick And Dirty
    MyString copy(rhs);                         //Calling Copy Constructor
    swap(data, copy.data);
    return *this;
    */
    
    int rhsLength = StringLength((rhs.data));
    if (capacity < rhsLength){
        delete [] data;
        data = new char[rhsLength + 1];
        capacity = rhsLength;
    }
    copyString(rhs.data, data);

    return *this;
}

MyString& MyString::operator += (MyString& rhs) {
    int lLen = StringLength(data);
    int rLen = StringLength(rhs.data);
    
    char* newArray = new char[lLen + rLen + 1];
    
    copyString(data, newArray);
    copyString(rhs.data, newArray + lLen);
    
    capacity = lLen + rLen;
    delete [] data;
    data = newArray;
    
    return *this;
}



//DESTRUCTOR
MyString::~MyString(){
    delete [] data;
}



ostream& operator << (ostream& outs, const MyString& ms) {
    outs << ms.cstring();
    return outs;
}

int StringLength(const char* cstr) {
    int length = 0;
    while(cstr[length]) {
        ++length;
    }
    return length;
}

void copyString(const char* src, char* dest) {
    while (*src != 0) {
        *dest = *src;       //dest[i] = src[i]
        src++;
        dest++;
    }
    *dest = 0;
}



