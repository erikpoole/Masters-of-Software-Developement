default: 
	clang++ -DNDEBUG -std=c++17 -c -O3 main.cpp
	clang++ -o working main.o

test:
	clang++ -std=c++17 -c test.cpp
	clang++ -o tester test.o
	./tester

clean:
	rm -rf working tester