#CFLAGS=-O3 -std=gnu11
CFLAGS=-O0 -g -std=gnu11
CC=clang

all: ctxt-switch

ctxt-switch: swtch.o thread.o ctxt-switch.o
	$(CC) $(CFLAGS) -o $@ $^

kThreads:
	clang++ -c -std=c++11 main.cpp
	clang++ -o kernalThreading main.o


.PHONY: clean

clean:
	-rm -f *.o ctxt-switch kernalThreading
