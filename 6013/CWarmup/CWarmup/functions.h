//
//  functions.h
//  CWarmup
//
//  Created by Erik Poole on 1/7/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#ifndef functions_h
#define functions_h

#include <stdio.h>

#endif /* functions_h */

void swapByte(uint8_t* location1, uint8_t* location2);
void swapNibble(unsigned long* arg, int location1, int location2);

unsigned long mask(unsigned long arg, int location);
int compare(unsigned long arg, int location1, int location2);

unsigned long byte_sort (unsigned long arg);
unsigned long nibble_sort (unsigned long arg);


struct elt {
    char val;
    struct elt *link;
};

struct elt *name_list (void);
void print_list(struct elt* head);
void free_list(struct elt* head);

