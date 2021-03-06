
# prompt> make
# builds everything and links in test program test_rb
#
# prompt> make mem_check
# Rebuilds everything using dmalloc and does memory testing.
# Only works if you have dmalloc installed (see http://dmalloc.com).

SRCS = test_red_black_tree.c red_black_tree.c stack.c misc.c

HDRS = red_black_tree.h stack.h misc.h

OBJS = red_black_tree.o stack.o test_red_black_tree.o misc.o

CC = gcc
#CC = clang -fsanitize=address,undefined

#CFLAGS = -g -O0 -coverage -fprofile-arcs -Wall -pedantic
CFLAGS = -O3 -Wall -pedantic

PROGRAM = test_rb

.PHONY:	mem_check clean

all: $(PROGRAM) $(PROGRAM2)

$(PROGRAM): 	$(OBJS)
		$(CC) $(CFLAGS) $(OBJS) -o $(PROGRAM) $(DMALLOC_LIB)

$(PROGRAM2): 	$(OBJS2)
		$(CC) $(CFLAGS) $(OBJS2) -o $(PROGRAM2) $(DMALLOC_LIB)

mem_check:
		@if [ -e makefile.txt ] ; then \
			echo "Using makefile.txt" ; \
			$(MAKE) clean -f makefile.txt ; \
			$(MAKE) $(PROGRAM) "CFLAGS=$(CFLAGS) -DDMALLOC" "DMALLOC_LIB=-ldmalloc" -f makefile.txt ; \
		else \
			echo "Using default makefile (i.e. no -f flag)." ; \
			$(MAKE) clean ; \
			$(MAKE) $(PROGRAM) "CFLAGS=$(CFLAGS) -DDMALLOC" "DMALLOC_LIB=-ldmalloc" ; \
		fi
		./simple_test.sh
		@if [ -s unfreed.txt ] ; then \
			echo " " ; \
			echo "Leaked some memory.  See logfile for details." ;\
		else \
			echo " " ; \
			echo "No memory leaks detected. " ;\
			echo " " ; \
			echo "Test passed. " ; \
			echo " " ; \
		fi


test_red_black_tree.o:	test_red_black_tree.c red_black_tree.c stack.c stack.h red_black_tree.h misc.h

red_black_tree.o:	red_black_tree.h stack.h red_black_tree.c stack.c misc.h misc.c

stack.o:		stack.c stack.h misc.h misc.c

clean:
	rm -f -r *.o *~ $(PROGRAM) $(PROGRAM2) *.gcda *.gcno *.gcov unfreed.txt *fuzzer *treeChecker *.profdata *.profraw *.dSYM *stdOutput.txt

cover:
	clang++ -g -fsanitize=address -O3 -std=c++11 -fprofile-instr-generate -fcoverage-mapping treeChecker.cpp stack.c misc.c red_black_tree.c -o treeChecker
	LLVM_PROFILE_FILE="treeChecker.profraw" ./treeChecker 1000000 > stdOutput.txt
	llvm-profdata merge -sparse treeChecker.profraw -o treeChecker.profdata
	llvm-cov show ./treeChecker -instr-profile=treeChecker.profdata > coverageReport.txt

