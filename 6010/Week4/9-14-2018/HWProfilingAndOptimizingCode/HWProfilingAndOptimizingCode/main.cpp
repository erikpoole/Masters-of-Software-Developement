#include "Image.hpp"
#include <iostream>

///////////////////////////////////////////////////////////////////////////
//EDIT 1:
//Was looping through three times (once for each color value), now looping just once and getting all three colors at onceC
//EDIT 3:
//Changed to constant reference rather than copy
///////////////////////////////////////////////////////////////////////////
uint64_t getBrightness(const Image& im){
  
  uint64_t count = 0;
  for(uint64_t col = 0; col < im.getCols(); ++col){
	for(uint64_t row = 0; row < im.getRows(); ++row){

	  RGB color = im.getColor(row, col);
	  count += color.r;
        count += color.g;
        count += color.b;
	}
  }

  
  return static_cast<uint8_t>(static_cast<double>(count)/(3*im.getRows()*im.getCols()));
}


void halfDomeTests(){
 Image image("/Users/epoole/Box Sync/erikpoole/Week4/9:14:2018/HWProfilingAndOptimizingCode/HWProfilingAndOptimizingCode/HalfDomeBinary.ppm");
 //repeat 20x so that it's easier to profile
 double brightness = 0;
 for(int i = 0; i < 20; ++i){
   brightness += getBrightness(image);
 }
 
 std::cout << "avg brightness: " << brightness/20 << std::endl;

}

void POMTest(){

  Image image("/Users/epoole/Box Sync/erikpoole/Week4/9:14:2018/HWProfilingAndOptimizingCode/HWProfilingAndOptimizingCode/POM.ppm");
    
  for(int i = 0; i < 60; ++i){
	std::cout << "blur pass " << i << std::endl;
	image.blur();
  }
  image.saveImage("POMOut.ppm");
    
    //Test that Blurred Pixel is functioning properly:
    //std::cout << double(image.getColor(400, 400).b) << std::endl;
    //std::cout << double(image.BlurredPixel(400, 400).b) << std::endl;
}


int main(){

  //halfDomeTests();
  POMTest();

  return 0;
}
