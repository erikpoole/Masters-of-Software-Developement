#CFLAGS=-O3 -std=gnu11
CFLAGS=-O0 -g -std=gnu11
CC=clang

all: ctxt-switch

ctxt-switch: swtch.o thread.o ctxt-switch.o
	$(CC) $(CFLAGS) -o $@ $^

.PHONY: clean

clean:
	-rm *.o ctxt-switch
