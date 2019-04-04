default:
	clang++ -c -O3 -std=c++11 petHotelTests.cpp fairHotelTests.cpp petHotel.cpp fairHotel.cpp
	clang++ -o petHotel petHotelTests.o petHotel.o
	clang++ -o fairHotel fairHotelTests.o fairHotel.o

testPet:
	./petHotel 100 100 100

testFair:
	./fairHotel 100 100 100

clean:
	rm *.o
	rm petHotel fairHotel