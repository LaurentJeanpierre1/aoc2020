package day19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Exo {
  private char match;
  List<List<Exo>> alternatives = new ArrayList<>();

  public Exo(Map<Integer, String[]> rules, int i) {
    String[] rule = rules.get(i);
    if (rule.length == 1 && rule[0].startsWith("\"") && rule[0].endsWith("\"")) {
      alternatives = null;
      match = rule[0].charAt(1);
    } else {
      for (String s : rule) {
        ArrayList<Exo> sequence = new ArrayList<>();
        String[] parts = s.split(" +");
        for (String part : parts) {
          try {
            int j = Integer.parseInt(part);
            if (j==i)
              sequence.add(this);
            else
              sequence.add(new Exo(rules, j));
          } catch (NumberFormatException e) {
            e.printStackTrace();
          }
        } // for part in parts
        alternatives.add(sequence);
      } // for s in rules
    } // if ! terminal
  } // Exo(...)

  public int matches(String chaine, int from) {
    if (from>=chaine.length())
      return -1;
    else if (alternatives == null)
      if (chaine.charAt(from) == match)
        return from+1;
      else
        return -1;
    else {
      for (var seq : alternatives) {
        int idx = from;
        for (Exo next : seq) {
          idx = next.matches(chaine, idx);
          if (idx == -1) break;
        }
        if (idx != -1) return idx;
      }
      return -1;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(Exo.class.getResourceAsStream("ex2b.txt")));

    var lines = read.lines().collect(Collectors.toList());
    ListIterator<String> ite = lines.listIterator();
    Map<Integer, String[]> rules = new HashMap<>();
    while (ite.hasNext()) {
      String line = ite.next();
      if (line.isEmpty()) break;
      String[] parts = line.split(" *: *");
      rules.put(Integer.parseInt(parts[0]), parts[1].split(" *\\| *"));
    }
    List<String> messages = new ArrayList<>();
    ite.forEachRemaining(messages::add);

    Exo recog = new Exo(rules, 0);

    long nb = messages.stream().filter(s->recog.matches(s, 0)==s.length()).peek(System.out::println).count();

    System.out.printf("Nb=%d\n",nb);

    int test = recog.matches("babbbbaabbbbbabbbbbbaabaaabaaa", 0);
    System.out.println(test);
  } // main
} // Exo
