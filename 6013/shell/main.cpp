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
        
        
        //        operator<<(std::cout, commands[0]);
        //        std::cout << "\n";
        
        std::vector<pid_t> childIDs;
        for (int i = 0; i < commands.size(); i++) {
            pid_t id = fork();
            if (id < 0) {
                perror(strerror(errno));
            }
            
            if (id == 0) {
                //child
                dup2(commands[i].fdStdin, 0);
                dup2(commands[i].fdStdout, 1);
                
                for (int j = 0; j < commands.size(); j++) {
                    if (j != i && commands[j].fdStdout != 1) {
                        close(commands[j].fdStdout);
                    }
                }
                
                const char** argumentsPointer = commands[i].argv.data();
                const char* namePointer = commands[i].exec.c_str();
                
                execvp(namePointer, const_cast<char* const*> (argumentsPointer));
            } else {
                //parent
                childIDs.push_back(id);
                
            }
        }
        
        for (Command command: commands) {
            if (command.fdStdout != 1) {
                close(command.fdStdout);
            }
        }
        
        int result;
        //        std::cout << childIDs.size() << std::endl;
        for (pid_t id : childIDs) {
            do {
                result = waitpid(id, NULL, 0);
                if(result < 0 && errno != EINTR) {
                    perror(strerror(errno));
                }
            } while (result < 0 && errno != EINTR);
            
            //            std::cout << id << "\n";
        }
        
        
    }
}
