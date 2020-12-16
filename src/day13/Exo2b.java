package day13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Exo2b {
  public static void main(String[] args) throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(Exo2b.class.getResourceAsStream("data.txt")));
//    var parts = read.lines().mapToLong(Long::parseLong).toArray();
//    var parts = read.lines().map(ligne -> ligne.split(" ")).collect(Collectors.toList());
    Long.parseLong(read.readLine()); // unused
    String buses = read.readLine();
    var ids = Arrays.stream(buses.split(",")).map(s->{
      if (s.equals("x")) return null;
      long id = Long.parseLong(s);
      return id;
    }).collect(Collectors.toList());

    long time = 1;
    long increment = 1;
    int busIdx = 0;
    while (busIdx<ids.size()) {
      Long id = ids.get(busIdx);
      if (id != null) {
        long lid = id;
        while ((time + busIdx) % lid != 0)
          time += increment;
        System.out.printf("Bus %d (%d/%d) ok at t=%d\n", lid, busIdx, ids.size(), time);
        increment = Ppcm.ppcm(increment, lid);
      }
      ++busIdx;
    }
    System.out.println(time);
  } // main
} // Exo
