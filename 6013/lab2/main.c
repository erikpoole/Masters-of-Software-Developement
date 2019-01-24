//
//  main.c
//  lab2
//
//  Created by Erik Poole on 1/23/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/wait.h>
#include<string.h>


int main(int argc, const char * argv[]) {
    /*
     int fds[2]
     
     Pipe(fds)
     
     int secret = 0xBEEF
     
     Write(fds[1], &secret, sizeof(secret))
     
     int received;
     
     Read(fds[0], &received, sizeof(received));
     
     assert(received == 0xBeef);
*/
    
    int fds[2];
    
    if (pipe(fds) == -1) {
        perror("Piping Error!");
        exit(1);
    }
    
    pid_t id = fork();
    if (id < 0) {
        perror("Forking Error!");
        exit(1);
    } else if (id == 0) {
        printf("Child\n");
        long size;
        read(fds[0], &size, sizeof(size));
        char output[sizeof(size)];
        read(fds[0], output, size);
        printf("%s\n", output);
        exit(0);
    } else {
        long size = strlen(argv[1]);
        write(fds[1], &size, sizeof(size));
        write(fds[1], argv[1], size);
        wait(NULL);
        //wait is returning an interrupted system call error - unsure why...?
//        if (wait(NULL) == -1) {
//            perror("Wait Error!");
//        }
    printf("Parent\n");
    }
}
