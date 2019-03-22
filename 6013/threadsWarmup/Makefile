#CFLAGS=-O3 -std=gnu11
CFLAGS=-O0 -g -std=gnu11
CC=clang

all: ctxt-switch

ctxt-switch: swtch.o thread.o ctxt-switch.o
	$(CC) $(CFLAGS) -o $@ $^

kernel:
	clang++ -c -std=c++11 kernelMain.cpp
	clang++ -o kernelThreading kernelMain.o

sanKernel:
	clang++ -fsanitize=thread -g kernelMain.cpp
	./a.out 10 10

.PHONY: clean
clean:
	-rm -f *.o *a.out ctxt-switch kernelThreading userThreading
	-rm -r *a.out*
