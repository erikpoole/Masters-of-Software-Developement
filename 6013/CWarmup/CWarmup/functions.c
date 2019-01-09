//
//  functions.c
//  CWarmup
//
//  Created by Erik Poole on 1/7/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#include "functions.h"

#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>

/*********************************************************************
 *
 * These C functions use patterns and functionality often found in
 * operating system code. Your job is to implement them. Write some test
 * cases for each function ( stick them in a funciton called it xyzTests() or similar)
 * call your abcTests(), etc functions in main().
 *
 * Write your own tests for each function you are testing, then share/combine
 * tests with a classmate.  Try to come up with tests that will break people's code!
 * Easy tests don't catch many bugs!
 *
 * You may not use any global variables in your solution
 *
 *
 * You must compile in C mode (not C++).  If you compile from the commandline
 * run clang, not clang++.
 *
 * Check your solution into github.
 *
 *********************************************************************/

/*********************************************************************
 * Helper functions -
 *********************************************************************/

void swapByte(uint8_t* location1, uint8_t* location2) {
    uint8_t temp = *location1;
    *location1 = *location2;
    *location2 = temp;
}

void swapNibble(unsigned long* arg, int location1, int location2) {
    unsigned long value1 = mask(*arg, location1);
    unsigned long value2 = mask(*arg, location2);
    
    unsigned long mask = 0xf;
    mask = mask << (location1 * 4);
    value2 = value2 << (location1 * 4);
    mask = ~mask;
    *arg = *arg & mask;
    *arg = *arg | value2;
    
    mask = 0xf;
    mask = mask << (location2 * 4);
    value1 = value1 << (location2 * 4);
    mask = ~mask;
    *arg = *arg & mask;
    *arg = *arg | value1;
}

unsigned long mask(unsigned long arg, int location) {
    unsigned long value = arg << ((sizeof(arg) * 8) - (location * 4) - 4);
    value = value >> ((sizeof(arg) * 8) - 4);
    return value;
}

int compare(unsigned long arg, int location1, int location2) {
    unsigned long value1 = mask(arg, location1);
    unsigned long value2 = mask(arg, location2);
    if (value1 > value2) {
        return 1;
    }
    
    return 0;
}


/*********************************************************************
 *
 * byte_sort()
 *
 * specification: byte_sort() treats its argument as a sequence of
 * 8 bytes, and returns a new unsigned long integer containing the
 * same bytes, sorted numerically, with the smaller-valued bytes in
 * the lower-order byte positions of the return value
 *
 * EXAMPLE: byte_sort (0x0403deadbeef0201) returns 0xefdebead04030201
 * Ah, the joys of using bitwise operators!
 *
 * Hint: you may want to write helper functions for these two functions
 *
 *********************************************************************/


/*
 &int - location of: creates pointer to object
 int* - declares pointer to int, or dereferences a currently declared pointer
 */

unsigned long byte_sort (unsigned long arg)
{
    //create one byte pointer pointing to beginning of arg
    uint8_t* argPointer = (uint8_t*) &arg;
    
    //insertion sort by comparing one byte sections
    for (int i = 0; i < sizeof(arg); i++) {
        int smallestLocation = i;
        for (int j = i; j < sizeof(arg); j++) {
            if (argPointer[j] < argPointer[smallestLocation]) {
                smallestLocation = j;
            }
        }
        swapByte(&argPointer[i], &argPointer[smallestLocation]);
    }
    
    return arg;
}


/*********************************************************************
 *
 * nibble_sort()
 *
 * specification: nibble_sort() treats its argument as a sequence of 16 4-bit
 * numbers, and returns a new unsigned long integer containing the same nibbles,
 * sorted numerically, with smaller-valued nibbles towards the "small end" of
 * the unsigned long value that you return
 *
 * the fact that nibbles and hex digits correspond should make it easy to
 * verify that your code is working correctly
 *
 * EXAMPLE: nibble_sort (0x0403deadbeef0201) returns 0xfeeeddba43210000
 *
 *********************************************************************/


// first mask specific nibble locations and then compare
// swap if necessary into original arg (original arg must first be cleared)
// compare and swap always needed for sort
unsigned long nibble_sort (unsigned long arg)
{
    for (int i = 0; i < sizeof(arg)*2; i++) {
        for (int j = i; j < sizeof(arg)*2; j++) {
            if (compare(arg, i, j)) {
                swapNibble(&arg, i, j);
            }
        }
    }
    
    return arg;
}

/*********************************************************************
 *
 * name_list()
 *
 * specification: allocate and return a pointer to a linked list of
 * struct elts
 *
 * - the first element in the list should contain in its "val" field the first
 *   letter of your first name; the second element the second letter, etc.;
 *
 * - the last element of the linked list should contain in its "val" field
 *   the last letter of your first name and its "link" field should be a null
 *   pointer
 *
 * - each element must be dynamically allocated using a malloc() call
 * Note, that since we're using C, not C++ you can't use new/delete!
 * The analog to delete is the free() function
 *
 * - if any call to malloc() fails, your function must return NULL and must also
 *   free any heap memory that has been allocated so far; that is, it must not
 *   leak memory when allocation fails
 *
 *
 * Implement print_list and free_list which should do what you expect.
 * Printing or freeing a nullptr should do nothing
 * Note: free_list might be useful for error handling for name_list...
 *********************************************************************/

struct elt *name_list (void) {
    char name[4] = {'E', 'R', 'I', 'K'};
    int arrSize = sizeof(name)/sizeof(name[0]);
    
    struct elt *headElt = malloc(sizeof(struct elt));
    struct elt *workingElt = headElt;
    for (int i = 0; i < arrSize; i++) {
        struct elt *nextElt = malloc(sizeof(struct elt));
        if (nextElt == NULL) {
            free_list(headElt);
        }
        
        workingElt->val = name[i];
        workingElt->link = nextElt;
        workingElt = nextElt;
    }
    
    workingElt->link = NULL;
    workingElt = NULL;
    
    return headElt;
}

void print_list(struct elt* head) {
    while(head->link != NULL) {
        printf("%c", head->val);
        head = head->link;
    }
    printf("\n");
    
}

void free_list(struct elt* head) {
    struct elt* currentElt = head;
    struct elt* nextElt;
    while (currentElt->link != NULL) {
        nextElt = currentElt->link;
        free(currentElt);
        currentElt = nextElt;
    }
    
    currentElt = NULL;
    nextElt = NULL;
}


/*********************************************************************
 *
 * draw_me()
 *
 * this function creates a file called me.txt which contains an ASCII-art
 * picture of you (it does not need to be very big).
 *
 * Use the C stdlib functions: fopen, fclose, fprintf, etc which live in stdio.h
 * don't use C++ iostreams
 *
 *
 *********************************************************************/

void draw_me (void) {
    FILE *file = fopen("drawing.txt", "w+");
    fprintf(file, "     // // //\n");
    fprintf(file, "   /\n");
    fprintf(file, "  [    *  *\n");
    fprintf(file, "   \\     >\n");
    fprintf(file, "    \\_____\n");
    fclose(file);
}





