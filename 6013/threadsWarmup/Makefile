#CFLAGS=-O3 -std=gnu11
CFLAGS=-O0 -g -std=gnu11
CC=clang

all: ctxt-switch

ctxt-switch: swtch.o thread.o ctxt-switch.o
	$(CC) $(CFLAGS) -o $@ $^

threads:
	clang++ -c -std=c++11 main.cpp
	clang++ -o kernalThreading main.o

sanThread:
	clang++ -fsanitize=thread -g main.cpp
	./a.out 10 10

.PHONY: clean
clean:
	-rm -f *.o *a.out ctxt-switch kernalThreading
	-rm -r *a.out*
