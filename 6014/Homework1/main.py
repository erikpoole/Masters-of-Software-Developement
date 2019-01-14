class IPLocation:
    address = None
    latency = None

    def __init__(self, input_string):
        split_string = input_string.split()

        self.address = split_string[2][1:-1]

        self.latency = (float(split_string[3]) + float(split_string[5]) + float(split_string[7]))/3


ipList = []

inputFile = open("rawData.txt")

for line in inputFile:
    ipList.append(IPLocation(line))

inputFile.close()
outputFile = open("output.txt", "w")

for ipLocation in ipList:
    outputFile.write("{} {}\n".format(ipLocation.address, ipLocation.latency))
    print("{} {}".format(ipLocation.address, ipLocation.latency))