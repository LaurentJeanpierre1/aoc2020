package day13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

// marche pas en temps raisonnable
public class Exo2 {
  public static void main(String[] args) throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(Exo2.class.getResourceAsStream("data.txt")));
//    var parts = read.lines().mapToLong(Long::parseLong).toArray();
//    var parts = read.lines().map(ligne -> ligne.split(" ")).collect(Collectors.toList());
    long time = Long.parseLong(read.readLine());
    String buses = read.readLine();
    var ids = Arrays.stream(buses.split(",")).map(s->{
      if (s.equals("x")) return null;
      long id = Long.parseLong(s);
      return id;
    }).collect(Collectors.toList());

    long max=0, maxidx=0;
    for (int i = 0, idsSize = ids.size(); i < idsSize; i++) {
      Long id = ids.get(i);
      if (id != null && id > max) {
        max = id;
        maxidx = i;
      }
    }

    long t = 100000000000000L-100000000000000L%max - max - maxidx;
    //long t = 1000000 - 1000000%max - max - maxidx;
    System.out.println((t+maxidx) % max);
    boolean ok;
    do {
      t += max;
      ok = true;
      for (int i = 0, idsSize = ids.size(); ok && i < idsSize; i++) {
        Long id = ids.get(i);
        if (id != null)
          ok = ((t+i) % id) == 0;
      }
    }while (!ok);
    System.out.println(t);
    for (int i = 0, idsSize = ids.size(); i < idsSize; i++) {
      Long id = ids.get(i);
      if (id != null)
        System.out.printf("Bus %d starts in %d\n", i, (t + i) % id);
    }
  } // main
} // Exo
