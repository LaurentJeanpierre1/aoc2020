package day20;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ListIterator;
import java.util.regex.Pattern;

public class Exo2 {
  Tile image;

  String[] serpent = {"..................#.",
                      "#....##....##....###",
                      ".#..#..#..#..#..#..."};
  private Tile curTile;

  public Exo2(Exo q1) {
    /*
    for (int l = 0; l<q1.puzzle.length; ++l) {
      for (int row = 0; row < q1.puzzle[l][0].size; ++row) {
        for (int c = 0; c < q1.puzzle[l].length; ++c)
          System.out.printf("%s ", new String(q1.puzzle[l][c].pixels[row]));
        System.out.println();
      }
      System.out.println();
    }
    */
    image = new Tile(q1.puzzle);
    System.out.println(image);
    chercheSerpent();
  }

  public Exo2(Tile t) {
    image = t;
    System.out.println(image);
    chercheSerpent();
  }
  private void chercheSerpent() {
    for (Transform t : Transform.values()) {
      int nb = cherche(new Tile(image, t));
      System.out.println(t.toString()+ ":"+nb);
      if (nb>0) {
        System.out.println(curTile.toString());
        long rough = 0;
        for (var l : curTile.pixels)
          for (char c : l)
            if (c=='#')
              ++rough;
        System.out.printf("Rough water : %d\n",rough);
      }
    }
  }


  private int cherche(Tile tile) {
    //System.out.println(tile.toString());
    curTile = tile;
    final int[] nb = {0};
    Pattern l0 = Pattern.compile(serpent[0]);
    Pattern l1 = Pattern.compile(serpent[1]);
    Pattern l2 = Pattern.compile(serpent[2]);
    for (int l = 1; l < tile.size-1; ++l) {
      String line = new String(tile.pixels[l]);
      int finalL = l;
      l1.matcher(line).results().forEach(mr->{
        int col = mr.start();
        int end = mr.end();
        if (l2.matcher(new String(tile.pixels[finalL + 1]).substring(col, end)).matches() &&
            l0.matcher(new String(tile.pixels[finalL - 1]).substring(col, end)).matches()
            ) {
          nb[0]++;
          for (int c = col; c<end; ++c) {
            if (serpent[0].charAt(c-col) == '#')
              curTile.pixels[finalL-1][c] = 'O';
            if (serpent[1].charAt(c-col) == '#')
              curTile.pixels[finalL][c] = 'O';
            if (serpent[2].charAt(c-col) == '#')
              curTile.pixels[finalL+1][c] = 'O';
          }
        }
      });
    }
    return nb[0];
  }

  public static void main(String[] args) throws IOException {
    var file = Files.readAllLines(Paths.get("src/day20/ex3.txt"));
    ListIterator<String> ite = file.listIterator();
    Tile tile = new Tile(ite,24);
    new Exo2(tile);
  }
}
