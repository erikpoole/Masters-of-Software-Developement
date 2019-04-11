#used to test locking

import os

os.chdir("/dev")

file = open("myRand", "rb+", buffering=0)

for i in range(10000000):
    output = file.read(1)

print("Last byte: " + bytes(file.read(1)).hex())

file.close()

