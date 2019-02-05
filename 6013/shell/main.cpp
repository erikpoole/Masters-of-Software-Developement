//
//  main.cpp
//  shell
//
//  Created by Erik Poole on 2/5/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#include <iostream>
#include "shelpers.hpp"

int main(int argc, const char * argv[]) {
    std::cout << "Shell Running\n";

    std::string inputString;
    while (std::getline(std::cin, inputString)) {
        std::vector<std::string> inputs;
        inputs.push_back(inputString);
//        std::vector<Command> commands = getCommands(inputs);
        
        pid_t id = fork();
        if (id < 0) {
            perror("Forking Error!\n");
            exit(1);
        } else if (id == 0) {
            //child
            char *cmd = "ls";
            char *argv[2];
            argv[0] = "ls";
            argv[1] = NULL;
            
            execvp(cmd, argv);
            exit(1);
        } else {
            //parent
            int result;
            do {
                result = waitpid(id, NULL, 0);
                if(result < 0 && errno != EINTR) {
                    printf("Wait Error!\n");
                }
            } while (result < 0);
            
            std::cout << id << "\n";
        }
        
    }
}

/*
 char *cmd = "ls";
 char *argv[3];
 argv[0] = "ls";
 argv[1] = "-la";
 argv[2] = NULL;
 
 execvp(cmd, argv); //This will run "ls -la" as if it were a command
 */

    
    /*
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
    */
