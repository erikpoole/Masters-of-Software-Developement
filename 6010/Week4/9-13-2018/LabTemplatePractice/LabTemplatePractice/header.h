//
//  header.h
//  LabTemplatePractice
//
//  Created by Erik Poole on 9/13/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#ifndef header_h
#define header_h


template<typename T>
struct Triple {
    T first;
    T second;
    T third;
};

template<typename T>
void printEach(T t){
    for (int i = 0; i < t.size(); i++){
        std::cout << t[i];
    }
    std::cout << std::endl;
}




#endif /* header_h */
