class IPNode:
    latencies = None
    latencyAverage = None
    address = None
    complete = False

    def __init__(self, input_list):
        self.latencies = []

        if len(input_list) == 9:
            self.handle_single_address(input_list)
        else:
            self.handle_multiple_address(input_list)

    def handle_single_address(self, input_list):
        self.latencies.append(float(input_list[3]))
        self.latencies.append(float(input_list[5]))
        self.latencies.append(float(input_list[7]))

        self.latencyAverage = (self.latencies[0] + self.latencies[1] + self.latencies[2])/3
        self.address = input_list[2][1:-1]
        self.complete = True

    def handle_multiple_address(self, input_list):
        if len(input_list) > 7 or len(input_list) < 4:
            print("Invalid Input of length " + str(len(input_list)) + "! Closing...")
            assert False

        if len(input_list) % 2 == 1:
            input_list = input_list[3:]
        else:
            input_list = input_list[2:]

        for i in range(0, len(input_list), 2):
            self.latencies.append(float(input_list[i]))

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

    while not currentIPNode.complete:
        line = inputFile.readline()
        split_string = line.split()
        currentIPNode.handle_multiple_address(split_string)

    ipList.append(currentIPNode)
    line = inputFile.readline()

inputFile.close()
outputFile = open("output.txt", "w")

for ipLocation in ipList:
    outputFile.write("{} {}\n".format(ipLocation.address, ipLocation.latencyAverage))
    print("{} {}".format(ipLocation.address, ipLocation.latencyAverage))
