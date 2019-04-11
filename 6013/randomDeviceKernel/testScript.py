import os

os.chdir("/dev")

file = open("myRand", "rb+", buffering=0)

output = file.read(1)
print("One Character Read: " + bytes(output).hex())

output = file.read(1)
print("One Character Read: " + bytes(output).hex())

output = file.read(10)
print("Ten Character Read: " + bytes(output).hex())

file.write("stuff".encode())
output = file.read(5)
print("Output when RC4 Buffer initialized to 'stuff': " + bytes(output).hex())

file.write("stuff".encode())
output = file.read(5)
print("Output after RC4 Buffer initialized to 'stuff' again: " + bytes(output).hex())

file.close()

