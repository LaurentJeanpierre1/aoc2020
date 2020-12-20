package day20;

import java.util.ListIterator;

public class Tile {
  int size;
  char[][] pixels;
  long id;

  public Tile(ListIterator<String> file, int size) {
    this.size = size;
    pixels = new char[size][];
    String entete = file.next().trim();
    id = Integer.parseInt(entete.substring(5, entete.length()-1));
    for (int l=0; l<size; ++l) {
      pixels[l] = file.next().toCharArray();
    }
    if (file.hasNext())
      file.next(); // blank line
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(String.format("Tile %d:\n", id));
    for (var line : pixels) {
      sb.append(new String(line));
      sb.append('\n');
    }
    return sb.toString();
  }

  public char[] getTop(Transform t) {
    char[] res = new char[size];
    for (int i=0; i<size; ++i)
      res[i] = t.getChar(0, i, pixels);
    return res;
  }
  public char[] getBottom(Transform t) {
    char[] res = new char[size];
    for (int i=0; i<size; ++i)
      res[i] = t.getChar(size-1, i, pixels);
    return res;
  }
  public char[] getLeft(Transform t) {
    char[] res = new char[10];
    for (int i=0; i<10; ++i)
      res[i] = t.getChar(i, 0, pixels);
    return res;
  }
  public char[] getRight(Transform t) {
    char[] res = new char[10];
    for (int i=0; i<10; ++i)
      res[i] = t.getChar(i, size-1, pixels);
    return res;
  }

  public Tile(Tile orig, Transform t) {
    pixels = new char[orig.size][];
    this.size = orig.size;
    for (int l=0; l<size; ++l) {
      pixels[l] = new char[size];
      for (int c=0; c<size; ++c) {
        pixels[l][c] = t.getChar(l, c, orig.pixels);
      }
    }
    id = orig.id;
  }

  public Tile(Tile[][] puzzle) {
    size = puzzle.length * (puzzle[0][0].pixels.length - 2);
    pixels = new char[size][size];
    int row = 0;
    for (int l=0; l< puzzle.length; ++l) {
      int col = 0;
      for (int c=0; c< puzzle[l].length; ++c) {
        Tile tile = puzzle[l][c];
        for (int ll=1; ll < tile.size-1; ++ll) {
          System.arraycopy(tile.pixels[ll], 1, pixels[l * (tile.size - 2) + ll - 1], c * (tile.size - 2) + 1 - 1, tile.size - 1 - 1);
        }
      }
    }
    id = 0;
  }
} // class Tile
