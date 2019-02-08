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
        

        
//        dup2(fds[0], <#int#>)
        
        /*
         int main()
         {
         int file_desc = open("tricky.txt",O_WRONLY | O_APPEND);
         
         // here the newfd is the file descriptor of stdout (i.e. 1)
         dup2(file_desc, 1) ;
         
         // All the printf statements will be written in the file
         // "tricky.txt"
         printf("I will be printed in the file tricky.txt\n");
         
         return 0;
         }
         */
        
        /*
         read = 0
         write = 1
         */
        
//        int fds[2];
//        fds[0] = commands[0].fdStdin;
//        fds[1] = commands[0].fdStdout;
        
        pid_t id = fork();
        if (id < 0) {
            perror(strerror(errno));
            exit(1);
        } else if (id == 0) {
            //child
            dup2(commands[0].fdStdin, 0);
            dup2(commands[0].fdStdout, 1);
            
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
                    perror(strerror(errno));
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
