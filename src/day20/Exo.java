package day20;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

public class Exo {

  Tile [][] puzzle;

  public Exo(ArrayList<Tile> tiles, int rows, int cols) {
    puzzle = new Tile[rows][cols];
    System.out.println(place(tiles, 0, 0, new boolean[tiles.size()]));
    for (int l=0; l<rows; ++l) {
      for (int c = 0; c < cols; ++c)
        System.out.printf(" %d ",puzzle[l][c].id);
      System.out.println();
    }
    long res = puzzle[0][0].id * puzzle[rows-1][0].id * puzzle[0][cols-1].id * puzzle[rows-1][cols-1].id;
    System.out.println(res);

    /*BigInteger res2 = BigInteger.valueOf(puzzle[0][0].id);
    res2 = res2.multiply(BigInteger.valueOf(puzzle[rows-1][0].id))*/
  }

  private boolean place(ArrayList<Tile> tiles, int row, int col, boolean[] booleans) {
    if (col>= puzzle[row].length) {
      col = 0;
      ++row;
    }
    if (row >= puzzle.length)
      return true;
    for (int i = 0; i < tiles.size(); i++) {
      if (booleans[i]) continue;
      Tile tile = tiles.get(i);
      for (Transform tr : Transform.values()) {
        if (row>0 && !Arrays.equals(tile.getTop(tr), puzzle[row - 1][col].getBottom(Transform.R0))) continue;
        if (col>0 && !Arrays.equals(tile.getLeft(tr), puzzle[row][col - 1].getRight(Transform.R0))) continue;
        puzzle[row][col] = new Tile(tile, tr);
        booleans[i] = true;
        if (place(tiles, row, col+1, booleans)) return true;
        booleans[i] = false;
      }
    }
    return false;
  }

  public static void main(String[] args) throws IOException {
    var file = Files.readAllLines(Paths.get("src/day20/data.txt"));
    ListIterator<String> ite = file.listIterator();
    ArrayList<Tile> tiles = new ArrayList<>();
    while (ite.hasNext()) {
      tiles.add(new Tile(ite,10));
    } // while more lines

    long time = System.nanoTime();
    Exo exo = new Exo(tiles, 12, 12);
    time -= System.nanoTime();
    Exo2 exo2 = new Exo2(exo);
    System.out.printf("time to puzzle : %f ms", time/-1000000.);
  } // main
} // Exo
