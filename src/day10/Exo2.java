package day10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Exo2 {
  public static void main(String[] args) throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(Exo2.class.getResourceAsStream("data.txt")));
    var parts = read.lines().mapToInt(Integer::parseInt).sorted().toArray();
//    var parts = read.lines().map(ligne -> ligne.split(" ")).collect(Collectors.toList());

    long[] ways = new long[300];
    int prev = 0;
    int nb1 = 0;
    int nb3 = 1; // mine
    ways[0] = 1; // the outlet

    for (int jolt : parts) {
      ways[jolt] = ways[jolt-1];
      if (jolt>1 && ways[jolt-2]>0) ways[jolt] += ways[jolt-2];
      if (jolt>2 && ways[jolt-3]>0) ways[jolt] += ways[jolt-3];
      prev = jolt;
    }

    System.out.printf("Ways of connecting : %d\n", ways[prev]);
  } // main
} // Exo
