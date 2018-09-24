#include "Image.hpp"
#include <fstream>
#include <cassert>

Image::Image(const std::string& filename){

  std::ifstream ins(filename, std::ios::binary);
  assert(ins.good());

  std::string magicNumber;
  ins >> magicNumber;
  assert(magicNumber == "P6" && "File must start with P6");

  ins >> width >> height;

  int maxColorVal;
  ins >> maxColorVal;
  assert(maxColorVal <= 255 && "We only work with 1-byte colors");

  ins.get(); //throw away the whitespace
  //need this many pixels
  data.resize(width*height);

  for(uint64_t row = 0; row < height; ++row){
	for(uint64_t col = 0; col < width; ++col){
	  RGB color;
	  color.r = ins.get();
	  color.g = ins.get();
	  color.b = ins.get();
	  setColor(row, col, color);
	}
  }
  
}

//changed to getColor2
void Image::saveImage(const std::string& filename) const{
  std::ofstream outs(filename, std::ios::binary);

  outs << "P6\n" << width << ' ' << height << '\n' << 255 << '\n';
  for(uint64_t row = 0; row < height; ++row){
	for(uint64_t col = 0; col < width; ++col){
	  RGB color = getColor2(row, col);
	  outs.put(color.r);
	  outs.put(color.g);
	  outs.put(color.b);
	}
  }
}


///////////////////////////////////////////////////////////////////////////
//EDIT 2:
//Switched from row*width + col to match up with getBrightness - running through rows quickly and columns slowly (e.g. we go from {row 0, col 0} -> {row 1, col 0})
///////////////////////////////////////////////////////////////////////////
RGB Image::getColor(uint64_t row, uint64_t col) const{
  return data[col*height + row];
}

RGB Image::getColor2(uint64_t row, uint64_t col) const{
    return data[row*width + col];;
}

void Image::setColor(uint64_t row, uint64_t col, RGB color){
  data[row*width + col] = color;
}

RGB Image::BlurredPixel(int64_t row, int64_t col) const{
  //TODO fill in
    
    DRGB middlePixel = getColor2(row, col);
    middlePixel *= .5;
    
    for (int i = -1; i < 2; i++) {
        for (int j = -1; j <2; j++) {
            if (i == 1 && j == 1) {
                continue;
            }
            int64_t rShift = row + j;
            int64_t cShift = col + i;
            if (rShift < 0 || rShift >= height) {rShift = row;}
            if (cShift < 0 || cShift >= width) {cShift = col;}
            DRGB adjacentPixel = getColor2(rShift, cShift);
            middlePixel += (adjacentPixel * .0625);
        }
    }
return RGB(middlePixel);
}

void Image::blur(){
  //TODO fill in
    std::vector<RGB> blurredVector;
    
    for(int row = 0; row < height; row++){
        for(int col = 0; col < width; col++){
            blurredVector.push_back(BlurredPixel(row, col));
        }
    }
    (*this).data = blurredVector;
}
                



