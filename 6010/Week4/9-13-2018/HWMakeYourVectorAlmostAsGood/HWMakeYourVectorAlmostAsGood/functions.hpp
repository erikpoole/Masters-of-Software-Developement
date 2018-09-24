//
//  functions.hpp
//  HWMakeYourOwnVector
//
//  Created by Erik Poole on 9/10/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#ifndef functions_hpp
#define functions_hpp

#include <stdio.h>


template<typename T>
class homemadeVector {
public:
    //CONSTRUCTOR                           //constructor from input e.g. homemadeVector name(3);
    homemadeVector(int inputCapacity) {
        vectorPointer = new T[inputCapacity];
        capacity = inputCapacity;
        size = 0;
    }

    //COPY CONSTRUCTOR                      //constructor from copy e.g. homemadeVector vector1(vector2)
    homemadeVector(const homemadeVector& original) {
        vectorPointer = new T[original.capacity];
        capacity = original.capacity;
        size = original.size;
        
        for (int i = 0; i < original.size; i++) {
            vectorPointer[i] = original.vectorPointer[i];
        }
    }
    
    //DESTRUCTOR
    ~homemadeVector() {
        delete [] vectorPointer;
    }
    
    //Overloading '='
    homemadeVector& operator = (const homemadeVector& rhs) {
        if (rhs.size > capacity) {
            delete [] vectorPointer;
            vectorPointer = new T[rhs.size];
        }
        size = rhs.size;
        
        for (int i = 0; i < size; i++) {
            vectorPointer[i] = rhs.vectorPointer[i];
        }
        return *this;
    }
    
    
///////////////////////////////////////////////////////////////////////////
    
    T operator [] (int index) const {return vectorPointer[index];}
    T& operator [] (int index) {return vectorPointer[index];}             
    
    void pushBack(T addedValue) {
        if (capacity <= size) {
            growVector();
        }
        //assigns according to index "0" first - size will always be one larger than filled indices
        vectorPointer[size] = addedValue;
        size++;
    }
    
    void popBack() {
        size--;
    }
    
    T get(int index) {
        if (index >= size || index < 0) {
            std::cout << "Invalid Index!!! Error: ";
            return 0;
        }
        return vectorPointer[index];
    }
    
    void set(int index, T newValue) {
        if (index >= size || index < 0) {
            std::cout << "Invalid Index!!!" << std::endl;
        } else {
            vectorPointer[index] = newValue;
        }
    }
    
    int getSize() {
        return size;
    }
    
    int getCapacity() {
        return capacity;
    }
    
    //Doesn't seem to work when I use *this instead of VectorPointer, unsure of why since [ ] is overloaded to take homemadeVectors
    //- FIXED: ORDER OF OPERATIONS!
    void sortVector() {
        for(int i = 0; i < size; i++) {
            T lowestValue = (*this)[i];
            for(int j = i; j < size; j++) {
                if (vectorPointer[j] < lowestValue) {
                    lowestValue = vectorPointer[j];
                    vectorPointer[j] = vectorPointer[i];
                    vectorPointer[i] = lowestValue;
                }
            }
        }
    }
    
    
/////////////////////////Equivalency Operator Overloads/////////////////////////
    
    bool operator == (const homemadeVector& rhs) const {
        if (size != rhs.size) {
            return false;
        } else {
            for (int i = 0; i < size; i++) {
                if (vectorPointer[i] != rhs.vectorPointer[i]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    bool operator < (const homemadeVector& rhs) const {
        //Compares values up until the size of the smaller array
        int countTo;
        if (size < rhs.size) {
            countTo = size;
        } else {countTo = rhs.size;}
            
        for (int i = 0; i < countTo; i++) {
            if (vectorPointer[i] < rhs.vectorPointer[i]) {
                return true;
            } else if (vectorPointer[i] > rhs.vectorPointer[i]) {
                return false;
            }
        }
        if (size < rhs.size) {
            return true;
        } else {
            return false;
        }
    }
    
    bool operator > (const homemadeVector& rhs) const {
        //Compares values up until the size of the smaller array
        int countTo;
        if (size < rhs.size) {
            countTo = size;
        } else {countTo = rhs.size;}
        
        for (int i = 0; i < countTo; i++) {
            if (vectorPointer[i] > rhs.vectorPointer[i]) {
                return true;
            } else if (vectorPointer[i] < rhs.vectorPointer[i]) {
                return false;
            }
        }
        if (size > rhs.size) {
            return true;
        } else {
            return false;
        }
    }
    
    
    
///////////////////////////////////////////////////////////////////////////
    
private:
    T* vectorPointer;
    int capacity;
    int size;
    
    void growVector() {
        T* newPointer = new T[capacity*2];
        
        for (int i = 0; i < capacity; i++) {
            newPointer[i] = vectorPointer[i];
        }
        capacity *= 2;
        
        vectorPointer = newPointer;
    }
};


/////////////////////////Equivalency Operator Overloads/////////////////////////

//!=
template<typename T>
bool operator != (homemadeVector<T> lhs, homemadeVector<T> rhs) {
    if (lhs == rhs) {
        return false;
    }
    return true;
}

//>=
template<typename T>
bool operator >= (homemadeVector<T> lhs, homemadeVector<T> rhs) {
    if (lhs < rhs) {
        return false;
    }
    return true;
}

///<=
template<typename T>
bool operator <= (homemadeVector<T> lhs, homemadeVector<T> rhs) {
    if (lhs > rhs) {
        return false;
    }
    return true;
}



/////////////////////////Stack Change Testers for Int/////////////////////////

template<typename T>
 //Copy Constructor Test
 void testStackChangeConstructor(homemadeVector<int> inputVector) {
 homemadeVector<int> newVector(inputVector);
 }

template<typename T>
 //Overloading '=' Test
 void testStackChangeEquals(homemadeVector<int> inputVector) {
 homemadeVector<int> newVector(0);
 newVector = inputVector;
 }



#endif /* functions_hpp */
