//
//  main.cpp
//  shell
//
//  Created by Erik Poole on 2/5/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

#include <iostream>
#include <sstream>
#include "shelpers.hpp"

int main(int argc, const char * argv[]) {
    std::cout << "Shell Running\n";

    std::string inputString;
    while (std::getline(std::cin, inputString)) {
        std::stringstream stringStream(inputString);
        std::string singleInput;
        std::vector<std::string> inputs;
        while (std::getline(stringStream, singleInput, ' ')) {
            inputs.push_back(singleInput);
        }
        std::vector<Command> commands = getCommands(inputs);

        
        operator<<(std::cout, commands[0]);
        std::cout << "\n";
        
        /*
         //You'll need to fork/exec for each one of these!,
         //Initially, assume the user tries to only execute 1 command.
         struct Command{
         std::string exec; //the name of the executable
         //remember argv[0] should be the name of the program (same as exec)
         //Also, argv should end with a nullptr!
         std::vector<const char*> argv;
         int fdStdin, fdStdout;
         bool background;
         };
         */
        
        pid_t id = fork();
        if (id < 0) {
            perror("Forking Error!\n");
            exit(1);
        } else if (id == 0) {
            //child
            Command workingCommand = commands[0];
            const char** argumentsPointer = workingCommand.argv.data();
            
            const char* namePointer = workingCommand.exec.c_str();
            
            execvp(namePointer, const_cast<char* const*> (argumentsPointer));
            exit(1);
        } else {
            //parent
            int result;
            do {
                result = waitpid(id, NULL, 0);
                if(result < 0 && errno != EINTR) {
                    perror("Wait Error!\n");
                }
            } while (result < 0);
            
//            std::cout << id << "\n";
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
