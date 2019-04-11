import os

os.chdir("/dev")

file = open("myRand", "r+")

output = file.read(1)
print("One Character Read: " + output);

output = file.read(10)
print("Ten Character Read: " + output);

file.write("stuff");
output = file.read(5);
print("Output when RC4 Buffer initialized to 'stuff': " + output);

file.write("stuff");
output = file.read(5);
print("Output after RC4 Buffer initialized to 'stuff' again: " + output);

file.close();

