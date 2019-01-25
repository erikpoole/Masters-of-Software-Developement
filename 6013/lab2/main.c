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
#include<errno.h>
#include<string.h>


int main(int argc, const char * argv[]) {
    int fds[2];
    
    if (pipe(fds) == -1) {
        perror("Piping Error!\n");
        exit(1);
    }
    
    pid_t id = fork();
    if (id < 0) {
        perror("Forking Error!\n");
        exit(1);
    } else if (id == 0) {
        printf("Child %d\n", getpid());
        long size;
        read(fds[0], &size, sizeof(size));
        char output[size+1];
        read(fds[0], output, size);
        printf("%s\n", output);
        exit(1);
    } else {
        long size = strlen(argv[1]);
        write(fds[1], &size, sizeof(size));
        write(fds[1], argv[1], size);
        int result;
        do {
            result = wait(NULL);
            if(result < 0 && errno != EINTR) {
                printf("Wait Error!\n");
            }
        } while (result < 0);
        
    printf("Parent %d\n", getpid());
    }
}
