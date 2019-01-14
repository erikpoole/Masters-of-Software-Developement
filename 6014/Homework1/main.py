class IPNode:
    string_list = None

    latencies = None
    latencyAverage = None
    address = None
    complete = False

    def __init__(self, input_string):
        self.latencies = []
        self.string_list = input_string.split()
        self.address = self.string_list[2][1:-1]

        self.shorten_string_list()
        self.add_latency_values()

        if len(self.latencies) == 3:
            self.latencyAverage = (self.latencies[0] + self.latencies[1] + self.latencies[2])/3
            self.complete = True

    def add_line(self, input_string):
        self.shorten_string_list()
        self.add_latency_values()

        if len(self.latencies) == 3:
            self.latencyAverage = (self.latencies[0] + self.latencies[1] + self.latencies[2])/3
            self.address = "Multiple IP Addresses"
            self.complete = True

    def shorten_string_list(self):
        if len(self.string_list) % 2 == 1:
            self.string_list = self.string_list[3:]
        else:
            self.string_list = self.string_list[2:]

    def add_latency_values(self):
        for i in range(0, len(self.string_list), 2):
            self.latencies.append(float(self.string_list[i]))


ipList = []

inputFile = open("rawData.txt")

inputFile.readline()
line = inputFile.readline()
while line:
    currentIPNode = IPNode(line)

    while not currentIPNode.complete:
        line = inputFile.readline()
        split_string = line.split()
        currentIPNode.string_list = split_string;
        currentIPNode.add_line(line)

    ipList.append(currentIPNode)
    line = inputFile.readline()

inputFile.close()
outputFile = open("output.txt", "w")

for ipLocation in ipList:
    outputFile.write("{} {}\n".format(ipLocation.address, ipLocation.latencyAverage))
    print("{} {}".format(ipLocation.address, ipLocation.latencyAverage))
