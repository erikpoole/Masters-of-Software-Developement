//
//  main.cpp
//  blockCipher
//
//  Created by Erik Poole on 2/25/19.
//  Copyright © 2019 ErikPoole. All rights reserved.
//

/*
 We're going to implement a block cypher similar to AES (but simplified somewhat).
 The cypher will use a 64 bit blocksize, and a 64 bit key
 (it's probably convenient to store your data as an array of 8 unsigned bytes).
 
 The algorithm has 3 main parts: Computing the secret key based on a password (of any length);
 computing a set of substitution tables that will be used during encryption/decryption;
 the actual encryption algorithm which makes use of the first 2 parts.
 
 To generate the encryption key, we're going to use a very insecure "hash" of an arbitrary length password.
 The key start as an array of 8 zeros. To generate the key do the following:
 for i = 0 to password length: key[i mod 8] = key[i mod 8] xor password[i]
 Think about why this particular scheme is such a bad choice.
 
 To get the next substitution table, apply a Fisher-Yates shuffle to the previous table.
 To get the first table, start with the identity permutation.
 Note that this is not a good way to generate these for a production implementation!
 Also note that in a production cypher, these tables would probably be specified in advance
 (the AES substitution tables are part of the algorithm specification).
 
 Since "Alice" and "Bob" are part of the same program for us,
 we can just compute the substitution tables once and share them between the encryption and decryption functions
 
 
 
 Now that we have the substitution tables and the key, the encryption algorith is as follows:
 
 The state begins as the input message
 
 For each of 16 rounds:
 
 xor the current state with the key
 
 for each byte in the state, substitute that byte using the appropriate substitution table
 (byte 0 should use table 0, byte 1 should use table 1, etc)
 
 rotate the entire state 1 bit to the left (after this byte 0 will contain contributions from bytes 0 and 1.
 Byte 7 will contain contributions from byte 7 and byte 0).
 
 The output is the state after these 16 rounds.
 
 Once you've implmented this encryption algorithm, implement the decryption algorithm
 (the "tricky" part is implementing the reverse substitution tables)
 
 Verify that you can encrypt and decrypt messages using your program.
 Demonstate that trying to decrypt a message using the wrong password
 (and therefore the wrong key) does not recover the plaintext message.
 
 I suggest using C++ since you have greater control over integer types (specifically, you'll mostly be working with uint8_t types).
 */



#include <iostream>

uint8_t* generateKey(std::string password) {
    uint8_t* key = new uint8_t[8]();
    for (int i = 0; i < password.size(); i++) {
        key[i % 8] = key[i % 8] ^ password[i];
    }
    
    return key;
}

uint8_t* generateTable() {
    uint8_t* table = new uint8_t[256]();
    for (int i = 0; i < 256; i++) {
        table[i] = i;
    }
    return table;
}

uint8_t* generateTable(uint8_t* inputTable) {
    uint8_t* table = new uint8_t[256]();
    
    for (int i = 0; i < 256; i++) {
        table[i] = inputTable[i];
    }
    
    for (int i = 0; i < 256; i++) {
        uint8_t random = (rand() % (256 - i)) + i;
        
        uint8_t temp = table[i];
        table[i] = table[random];
        table[random] = temp;
    }
    
    return table;
}

uint8_t* encrypt(uint8_t* key, uint8_t* message, uint8_t** tables) {
    uint8_t* current = new uint8_t[8]();
    for (int i = 0; i < 8; i++) {
        current[i] = message[i];
    }
    
    for (int i = 0; i < 16; i++) {
    
//        for (int j = 0; j < 8; j++) {
//            current[j] ^= key[j];
//        }
//        for (int j = 0; j < 8; j++) {
//            uint8_t byteValue = current[j];
//            current[j] = tables[j][byteValue];
//        }
        uint8_t lastBit = 0;
        lastBit |= current[0] >> 7;
        for (int j = 0; j < 7; j++) {
            uint8_t hangingBit = current[j+1];
            hangingBit >>= 7;
            current[j] <<= 1;
            current[j] ^= hangingBit;
        }
        current[7] <<= 1;
        current[7] ^= lastBit;
    }

    return current;
}

uint8_t* decrypt(uint8_t* key, uint8_t* cipherText, uint8_t** tables) {
    uint8_t* current = new uint8_t[8]();
    for (int i = 0; i < 8; i++) {
        current[i] = cipherText[i];
    }
    
    for (int i = 0; i < 16; i++) {
    
        uint8_t lastBit = 0;
        lastBit |= current[7] << 7;
        for (int j = 7; j > 0; j--) {
            uint8_t hangingBit = current[j-1];
            hangingBit <<= 7;
            current[j] >>= 1;
            current[j] ^= hangingBit;
        }
        current[0] >>= 1;
        current[0] ^= lastBit;
//        
//        for (int j = 0; j < 8; j++) {
//            int count = 0;
//            int value = current[j];
//            while (value != tables[j][count]) {
//                count++;
//            }
//            current[j] = tables[j][count];
//        }
        
//        for (int j = 0; j < 8; j++) {
//            current[j] ^= key[j];
        }

//    }
    
    return current;
}

void printText(uint8_t* cipherText, std::string name) {
    std::cout << name << ": ";
    for (int i = 0; i < 8; i++) {
        std::cout << (uint16_t) cipherText[i] << ", ";
    }
    std::cout << "\n";
}

int main(int argc, const char*  argv[]) {
    
    uint8_t message[8];
    for (int i = 0; i < 8; i++) {
        message[i] = i;
    }
    
    printText(message, "plaintext");
    
    uint8_t* key = generateKey("password");
    //    for (int i = 0; i < 8; i++) {
    //        std::cout << (uint16_t) key[i] << "\n";
    //    }
    
    uint8_t* tables[8];
    
    tables[0] = generateTable();
    for (int i = 1; i < 8; i++) {
        tables[i] = generateTable(tables[i-1]);
    }
    
    //    for (int i = 0; i < 256; i++) {
    //        std::cout << (uint16_t) table[1][i] << "\n";
    //    }
    
    
    uint8_t* cipherText = encrypt(key, message, tables);
    printText(cipherText, "ciphertext");
    uint8_t* decryptedText = decrypt(key, cipherText, tables);
    printText(decryptedText, "decryptedtext");
}


