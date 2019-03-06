default: 
	clang++ -DNDEBUG -std=c++17 -c -O3 main.cpp functions.cpp
	clang++ -o timer main.o functions.o
	./timer

test:
	clang++ -c -std=c++17 tester.cpp functions.cpp
	clang++ -o tester tester.o functions.o
	./tester

clean:
	rm -f *.o
	rm -f tester
	rm -f timer
