package day11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Exo2 {
  private static final char EMPTY = 'L';
  private static final char FULL = '#';
  private static final char FLOOR = '.';

  List<char[]> old;
  List<char[]> seats;
  long occupied = 0;

  public Exo2(List<char[]> seats) {
    this.seats = seats;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(Exo2.class.getResourceAsStream("data.txt")));
//    var seats = read.lines().mapToLong(Long::parseLong).toArray();
    var seats = read.lines().map(String::toCharArray).collect(Collectors.toList());
    Exo2 waiting = new Exo2(seats);

    boolean updated = true;
    while (updated) {
      System.out.println(waiting.toString());
      updated = waiting.evolve();
    }

    System.out.printf("Occupied = %d\n", waiting.countOccupied());
  } // main

  private long countOccupied() {
    return occupied;
  }

  private boolean evolve() {
    old = new ArrayList<>(seats.size());
    for (char[] lg : seats)
      old.add(Arrays.copyOf(lg, lg.length));

    boolean changed = false;
    for (int l = 0; l<seats.size(); ++l) {
      var line = old.get(l);
      for (int c = 0; c<line.length; ++c) {
        if (line[c] == EMPTY)
          changed |= evolveEmpty(c, l);
        if (line[c] == FULL)
          changed |= evolveFull(c, l);
      }
    }
    return changed;
  }

  private boolean evolveEmpty(int col, int lg) {
    int cols = old.get(0).length;
    int rows = old.size();
    if (countDirection(cols, rows, 0, lg, col, -1, -1) > 0) return false;
    if (countDirection(cols, rows, 0, lg, col, -1, 0) > 0) return false;
    if (countDirection(cols, rows, 0, lg, col, -1, +1) > 0) return false;
    if (countDirection(cols, rows, 0, lg, col, 0, -1) > 0) return false;
    if (countDirection(cols, rows, 0, lg, col, 0, +1) > 0) return false;
    if (countDirection(cols, rows, 0, lg, col, +1, -1) > 0) return false;
    if (countDirection(cols, rows, 0, lg, col, +1, 0) > 0) return false;
    if (countDirection(cols, rows, 0, lg, col, +1, +1) > 0) return false;
    seats.get(lg)[col] = FULL;
    occupied++;
    return true;
  }

  private boolean evolveFull(int col, int lg) {
    int cols = old.get(0).length;
    int rows = old.size();
    int nb = 0;
    nb = countDirection(cols, rows, nb, lg, col, -1, -1);
    nb = countDirection(cols, rows, nb, lg, col, -1, 0);
    nb = countDirection(cols, rows, nb, lg, col, -1, +1);
    nb = countDirection(cols, rows, nb, lg, col, 0, -1);
    nb = countDirection(cols, rows, nb, lg, col, 0, +1);
    nb = countDirection(cols, rows, nb, lg, col, +1, -1);
    nb = countDirection(cols, rows, nb, lg, col, +1, 0);
    nb = countDirection(cols, rows, nb, lg, col, +1, +1);
    if (nb >= 5) {
      seats.get(lg)[col] = EMPTY;
      occupied--;
      return true;
    }
    return false;
  }

  private int countDirection(int cols, int rows, int nb, int l, int c, int dirL, int dirC) {
    while ((l += dirL)>=0 && (c += dirC)>=0 && l < rows && c < cols && nb <5) {
      switch (old.get(l)[c]) {
        case FULL:
          return nb +1;
        case EMPTY:
          return nb;
        default:
          //continue;
      }
    }
    return nb;
  }

  @Override
  public String toString() {
    return seats.stream().map(String::new).collect(Collectors.joining("\n"))+"\n";
  }
} // Exo
