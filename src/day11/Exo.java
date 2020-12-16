package day11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Exo {
  private static final char EMPTY = 'L';
  private static final char FULL = '#';
  private static final char FLOOR = '.';

  List<char[]> old;
  List<char[]> seats;
  long occupied = 0;

  public Exo(List<char[]> seats) {
    this.seats = seats;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(Exo.class.getResourceAsStream("data.txt")));
//    var seats = read.lines().mapToLong(Long::parseLong).toArray();
    var seats = read.lines().map(String::toCharArray).collect(Collectors.toList());
    Exo waiting = new Exo(seats);

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
    char[] prev = null;
    char[] next = old.get(0);
    char[] line = null;
    for (int l = 1; l<seats.size(); ++l) {
      prev = line;
      line = next;
      next = old.get(l);
      for (int c = 0; c<line.length; ++c) {
        if (line[c] == EMPTY)
          changed |= evolveEmpty(prev, line, next, c, l-1);
        if (line[c] == FULL)
          changed |= evolveFull(prev, line, next, c, l-1);
      }
    }
    for (int c = 0; c<line.length; ++c) {
      if (next[c] == EMPTY)
        changed |= evolveEmpty(line, next, null, c, seats.size()-1);
      if (next[c] == FULL)
        changed |= evolveFull(line, next, null, c, seats.size()-1);
    }
    return changed;
  }

  private boolean evolveEmpty(char[] prev, char[] line, char[] next, int col, int lg) {
    if (prev != null) {
      if (col > 0 && prev[col - 1] == FULL) return false;
      if (prev[col] == FULL) return false;
      if (col < line.length-1 && prev[col + 1] == FULL) return false;
    }
    if (next != null) {
      if (col > 0 && next[col - 1] == FULL) return false;
      if (next[col] == FULL) return false;
      if (col < line.length-1 && next[col + 1] == FULL) return false;
    }
    if (col > 0 && line[col-1] == FULL) return false;
    if (col < line.length-1 && line[col+1] == FULL) return false;
    seats.get(lg)[col] = FULL;
    occupied++;
    return true;
  }

  private boolean evolveFull(char[] prev, char[] line, char[] next, int col, int lg) {
    int nb = 0;
    if (prev != null) {
      if (col > 0 && prev[col - 1] == FULL) nb++;
      if (prev[col] == FULL) nb++;
      if (col < line.length-1 && prev[col + 1] == FULL) nb++;
    }
    if (next != null) {
      if (col > 0 && next[col - 1] == FULL) nb++;
      if (next[col] == FULL) nb++;
      if (col < line.length-1 && next[col + 1] == FULL) nb++;
    }
    if (col>0 && line[col-1] == FULL) nb++;
    if (col<line.length-1 && line[col+1] == FULL) nb++;
    if (nb >= 4) {
      seats.get(lg)[col] = EMPTY;
      occupied--;
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return seats.stream().map(String::new).collect(Collectors.joining("\n"))+"\n";
  }
} // Exo
