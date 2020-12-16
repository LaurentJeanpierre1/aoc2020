package day13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Arrays;

public class Exo {
  static class Pair implements Comparable<Pair>{
    long v, modulo;

    @Override
    public String toString() {
      return MessageFormat.format("'{'v={0}, modulo={1}'}'", v, modulo);
    }

    public Pair(long v, long modulo) {
      this.v = v;
      this.modulo = v-modulo;
    }

    @Override
    public int compareTo(Pair o) {
      return Long.compare(modulo, o.modulo);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(Exo.class.getResourceAsStream("data.txt")));
//    var parts = read.lines().mapToLong(Long::parseLong).toArray();
//    var parts = read.lines().map(ligne -> ligne.split(" ")).collect(Collectors.toList());
    long time = Long.parseLong(read.readLine());
    String buses = read.readLine();
    Object[] ids = Arrays.stream(buses.split(",")).filter(s->!s.equals("x")).mapToInt(Integer::parseInt).mapToObj(v->new Pair(v, time % v)).sorted().toArray();
    System.out.println(Arrays.toString(ids));
    Pair first = (Pair)ids[0];
    System.out.println(first.v * first.modulo);
  } // main
} // Exo
