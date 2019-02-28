//
//  main.cpp
//  rc4Cipher
//
//  Created by Erik Poole on 2/28/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//


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


std::string xOR(std::string inputText1, std::string inputText2) {
    std::string output = "";
    for (int i = 0; i < inputText1.size(); i++) {
        output += inputText1[i]^inputText2[i];
    }
    
    return output;
}


int main(int argc, const char * argv[]) {
    std::string password = "Password";
    std::string badPassword = "Badword";
    
    rc4Cipher encoder = rc4Cipher(password);
    rc4Cipher encoder2 = rc4Cipher(password);
    rc4Cipher decoder = rc4Cipher(password);
    rc4Cipher badDecoder = rc4Cipher(badPassword);
    
    std::string plainText = "My Message";
    std::string cipherText = encoder.encode(plainText);
    std::string decryptedText = decoder.encode(cipherText);
    std::string badDecryptedText = badDecoder.encode(cipherText);
    
    std::cout << "plaintext: " << plainText << "\n";
    std::cout << "ciphertext: " << cipherText << "\n";
    std::cout << "decryptedText: " << decryptedText << "\n";
    std::cout << "badDecryptedText: " << badDecryptedText << "\n";
    std::cout << "\n";
    
    
    //reused key attack demonstration
    std::string plainText2 = "10 Letters";
    std::string cipherText2 = encoder2.encode(plainText2);
    std::cout << "ciphertext: " << cipherText << "\n";
    std::cout << "ciphertext2: " << cipherText2 << "\n";
    
    std::string combinedPlainText = xOR(cipherText, cipherText2);
    std::cout << "combined plaintext: " << combinedPlainText << "\n";
    
    std::string hackedText = xOR(plainText2, combinedPlainText);
    std::cout << "Hacked text given that plaintext2 is known: " << hackedText << "\n";
    std::cout << "\n";
    
    
    //bit flipping attack demonstration
    std::string salaryText = "Your salary is $1000";
    std::string attackerText = "Your salary is $9999";
    std::string salaryCipher = encoder.encode(salaryText);
    std::string attackerCipher = xOR(salaryCipher, xOR(attackerText, salaryText));
    std::string decryptedAttack = encoder2.encode(attackerCipher);
    
    std::cout << "salaryText: " << salaryText << "\n";
    std::cout << "attackerText: " << attackerText << "\n";
    std::cout << "salaryCipher: " << salaryCipher << "\n";
    std::cout << "attackerCipher: " << attackerCipher << "\n";
    std::cout << "decryptedAttack: " << decryptedAttack << "\n";
    
    
    
    
}


