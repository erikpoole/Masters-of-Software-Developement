class IPNode:
    latencies = None
    latencyAverage = None
    address = None
    complete = False

    def __init__(self, input_string):
        self.latencies = []
        input_string = input_string.split()
        self.address = input_string[2][1:-1]

        self.add_latency_values(input_string)

        if len(self.latencies) == 3:
            self.latencyAverage = (self.latencies[0] + self.latencies[1] + self.latencies[2])/3
            self.complete = True

    def add_line(self, input_string):
        self.address = "Multiple IP Addresses"

        input_string = input_string.split()
        self.add_latency_values(input_string)

        if len(self.latencies) == 3:
            self.latencyAverage = (self.latencies[0] + self.latencies[1] + self.latencies[2])/3
            self.complete = True

    def add_latency_values(self, input_string):
        if len(input_string) % 2 == 1:
            input_string = input_string[3:]
        else:
            input_string = input_string[2:]

        for i in range(0, len(input_string), 2):
            self.latencies.append(float(input_string[i]))


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
