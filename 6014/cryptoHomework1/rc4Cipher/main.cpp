//
//  main.cpp
//  rc4Cipher
//
//  Created by Erik Poole on 2/28/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

/*
 Implement the RC4 cypher as described in class (it's not very much code! Probably a struct/class with a constructor and a single method. Pseudocode in the class slides or many other places).
 
 Verify that decrypting a message with a different key than the encryption key does not reveal the plaintext.
 
 Verify that encrypting 2 messages using the same keystream is insecure (what do you expect to see if you xor the two encrypted messages?)
 
 Modify part of a message using a bit-flipping attack. For example, try sending the message "Your salary is $1000" encrypted with RC4. Modify the cyphertext so that when decrypted is says that your salary is 9999 instead. Hint: this should just require using xor.
 */


#include <iostream>
#include <vector>

class rc4Cipher {
public:
    std::vector<uint8_t> table;
    int index1, index2;
    
    rc4Cipher(const std::string &key) {
        for (int i = 0; i < 256; i++) {
            table.push_back(i);
        }
        
        int j = 0;
        for (int i = 0; i < table.size(); i++) {
            j = (j + table[i] + key[i % key.size()]) % 256;
            std::swap(table[i], table[j]);
        }
        
        index1 = 0;
        index2 = 0;
    }
    
    std::string encode(const std::string &plaintext) {
        std::string ciphertext = "";
        for (int i = 0; i < plaintext.size(); i++) {
            index1 = (index1 + 1) % table.size();
            index2 = (index2 + table[index1]) % table.size();
            std::swap(table[index1], table[index2]);
            uint8_t cypterByte = table[(table[index1] + table[index2]) % table.size()];
            cypterByte ^= plaintext[i];
            
            ciphertext += cypterByte;
        }
        
        return ciphertext;
    }
};


int main(int argc, const char * argv[]) {
    std::string password = "Password";
    rc4Cipher encoder = rc4Cipher(password);
    rc4Cipher decoder = rc4Cipher(password);
    
    std::string plainText = "My Message";
    std::string cipherText = encoder.encode(plainText);
    std::string decryptedText = decoder.encode(cipherText);
    
    std::cout << "plaintext: " << plainText << "\n";
    std::cout << "ciphertext: " << cipherText << "\n";
    std::cout << "decrpytedtext: " << decryptedText << "\n";
    
    
    
}
