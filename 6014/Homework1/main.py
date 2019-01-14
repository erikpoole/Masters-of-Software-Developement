class IPNode:
    latencies = None
    latencyAverage = None
    address = None
    complete = False

    def __init__(self, input_string):
        self.latencies = []
        self.add_line(input_string)

    def add_line(self, input_string):
        split_string = input_string.split()

        if self.address is None:
            self.address = split_string[2][1:-1]
        else:
            self.address = "Multiple IP Addresses"

        self.add_latency_values(split_string)
        if len(self.latencies) == 3:
            self.latencyAverage = (self.latencies[0] + self.latencies[1] + self.latencies[2])/3
            self.complete = True

    def add_latency_values(self, input_list):
        if len(input_list) % 2 == 1:
            input_list = input_list[3:]
        else:
            input_list = input_list[2:]

        for i in range(0, len(input_list), 2):
            self.latencies.append(float(input_list[i]))


ipList = []

inputFile = open("rawData.txt")

inputFile.readline()
line = inputFile.readline()
while line:
    currentIPNode = IPNode(line)

    while not currentIPNode.complete:
        line = inputFile.readline()
        currentIPNode.add_line(line)

    ipList.append(currentIPNode)
    line = inputFile.readline()

inputFile.close()
outputFile = open("output.txt", "w")

for ipLocation in ipList:
    outputFile.write("{} {}\n".format(ipLocation.address, ipLocation.latencyAverage))
    print("{} {}".format(ipLocation.address, ipLocation.latencyAverage))
