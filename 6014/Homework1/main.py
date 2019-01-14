class IPNode:
    latencies = None
    latencyAverage = None
    address = None
    complete = False

    def __init__(self, split_string):
        self.latencies = []

        if len(split_string) == 9:
            self.handleSingleAddress(split_string)
        else:
            self.handleMultiAddress(split_string)

    def handleSingleAddress(self, split_string):
        self.latencies.append(float(split_string[3]))
        self.latencies.append(float(split_string[5]))
        self.latencies.append(float(split_string[7]))

        self.latencyAverage = (self.latencies[0] + self.latencies[1] + self.latencies[2])/3
        self.address = split_string[2][1:-1]
        self.complete = True

    def handleMultiAddress(self, split_string):
        if len(split_string) == 7:
            self.latencies.append(float(split_string[3]))
            self.latencies.append(float(split_string[5]))
        elif len(split_string) == 6:
            self.latencies.append(float(split_string[2]))
            self.latencies.append(float(split_string[4]))
        elif len(split_string) == 5:
            self.latencies.append(float(split_string[3]))
        elif len(split_string) == 4:
            self.latencies.append(float(split_string[2]))
        else:
            print("Invalid Input! Closing...")
            assert(False)

        if len(self.latencies) == 3:
            self.latencyAverage = (self.latencies[0] + self.latencies[1] + self.latencies[2])/3
            self.address = "Multiple IP Addresses"
            self.complete = True





ipList = []

inputFile = open("rawData.txt")

inputFile.readline()
line = inputFile.readline()
while line:
    split_string = line.split()
    currentIPNode = IPNode(split_string)

    while currentIPNode.complete != True:
        line = inputFile.readline()
        split_string = line.split()
        currentIPNode.handleMultiAddress(split_string)

    ipList.append(currentIPNode)
    line = inputFile.readline()

inputFile.close()
outputFile = open("output.txt", "w")

for ipLocation in ipList:
    outputFile.write("{} {}\n".format(ipLocation.address, ipLocation.latencyAverage))
    print("{} {}".format(ipLocation.address, ipLocation.latencyAverage))