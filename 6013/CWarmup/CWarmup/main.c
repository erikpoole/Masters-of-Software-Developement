//
//  main.c
//  CWarmup
//
//  Created by Erik Poole on 1/7/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#include <stdio.h>

#include "functions.h"

int main(int argc, const char * argv[]) {
    
    //0xefdebead04030201
    printf("%lx\n", byte_sort(0x0403deadbeef0201));
    printf("\n");
    
    //0xfeeeddba43210000
    printf("%lx\n", nibble_sort(0x0403deadbeef0201));
    printf("\n");
}
