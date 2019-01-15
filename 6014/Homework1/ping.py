def parseLine(input_line):
    split_line = line.split()
    time_string = split_line[6]
    time_string = time_string[5:]
    return time_string

def isCorrectLength(input_split_string):
    if len(input_split_string) == 8:
        return True
    return False


timeList = []
inputFile = open("pingData.txt")

inputFile.readline()
for line in inputFile:
    splitLine = line.split()
    if (isCorrectLength(splitLine)):
        time = parseLine(line)
        timeList.append(float(time))

minTime = min(timeList)

sum = 0
for time in timeList:
    sum = sum + time - minTime

average = sum / len(timeList)

outputFile = open("pingOutput.txt", "w")
outputFile.write("Average Round-Trip Queuing Delay: " + str(average))
