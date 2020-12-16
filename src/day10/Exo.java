package day10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Exo {
  public static void main(String[] args) throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(Exo.class.getResourceAsStream("data.txt")));
    var parts = read.lines().mapToLong(Long::parseLong).sorted().toArray();
//    var parts = read.lines().map(ligne -> ligne.split(" ")).collect(Collectors.toList());

    long prev = 0;
    int nb1 = 0;
    int nb3 = 1; // mine

    for (long jolt : parts) {
      if (jolt == prev + 1) nb1 ++;
      else if (jolt == prev + 2) throw new IllegalStateException("2 jolts ?");
      else if (jolt == prev + 3) nb3++;
      else throw new IllegalStateException("more jolts ?");
      prev = jolt;
    }
    System.out.printf("Chain of adapters : %d 1-jolt and %d 3-jolts gaps => mult = %d.\n", nb1, nb3, nb1*nb3);
  } // main
} // Exo
