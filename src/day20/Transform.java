package day20;

public enum Transform {
  R0(){
    @Override
    char getChar(int l, int c, char[][] pixels) {
      return pixels[l][c];
    }
  }, R90(){
    @Override
    char getChar(int l, int c, char[][] pixels) {
      return pixels[c][pixels.length-1-l];
    }
  }, R180(){
    @Override
    char getChar(int l, int c, char[][] pixels) {
      return pixels[pixels.length-1-l][pixels.length-1-c];
    }
  }, R270(){
    @Override
    char getChar(int l, int c, char[][] pixels) {
      return pixels[pixels.length-1-c][l];
    }
  },
  FR0(){
    @Override
    char getChar(int l, int c, char[][] pixels) {
      return pixels[pixels.length-1-l][c];
    }
  }, FR90(){
    @Override
    char getChar(int l, int c, char[][] pixels) {
      return pixels[pixels.length-1-c][pixels.length-1-l];
    }
  }, FR180(){
    @Override
    char getChar(int l, int c, char[][] pixels) {
      return pixels[l][pixels.length-1-c];
    }
  }, FR270(){
    @Override
    char getChar(int l, int c, char[][] pixels) {
      return pixels[c][l];
    }
  };

  abstract char getChar(int l, int c, char[][] pixels);
}
