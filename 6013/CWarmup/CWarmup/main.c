//
//  main.c
//  CWarmup
//
//  Created by Erik Poole on 1/7/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#include <stdio.h>
#include <assert.h>

#include "functions.h"

int main(int argc, const char * argv[]) {
    
    //byte_sort testing
    assert(byte_sort(0x0403deadbeef0201ul) == 0xefdebead04030201ul);
    assert(byte_sort(0x0000000000000000ul) == 0x0000000000000000ul);
    assert(byte_sort(0xFFFFFFFFFFFFFFFFul) == 0xFFFFFFFFFFFFFFFFul);
    printf("byte_sort Testing Complete\n");
    
    //nibble_sort testing
    assert(nibble_sort(0x0403deadbeef0201ul) == 0xfeeeddba43210000ul);
    assert(nibble_sort(0x0000000000000000ul) == 0x0000000000000000ul);
    assert(nibble_sort(0xFFFFFFFFFFFFFFFFul) == 0xFFFFFFFFFFFFFFFFul);
    printf("nibble_sort Testing Complete\n");
    
    //name_list testing
    struct elt* testElt = name_list();
    
    assert(testElt->val == 'E');
    assert(testElt->link->link->link->val == 'K');
    assert(testElt->link->link->link->link != NULL);
    assert(testElt->link->link->link->link->link == NULL);

    //print_list
    print_list(testElt);
    
    //free_list
    free_list(testElt);
    assert(testElt->link == NULL);
    
    printf("name_list Testing Complete\n");
}
