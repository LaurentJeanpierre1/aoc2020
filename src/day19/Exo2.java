package day19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Exo2 {
  private char match;
  List<List<Exo2>> alternatives = new ArrayList<>();

  public Exo2(Map<Integer, String[]> rules, int i) {
    String[] rule = rules.get(i);
    if (rule.length == 1 && rule[0].startsWith("\"") && rule[0].endsWith("\"")) {
      alternatives = null;
      match = rule[0].charAt(1);
    } else {
      for (String s : rule) {
        ArrayList<Exo2> sequence = new ArrayList<>();
        String[] parts = s.split(" +");
        for (String part : parts) {
          try {
            int j = Integer.parseInt(part);
            if (j==i)
              sequence.add(this);
            else
              sequence.add(new Exo2(rules, j));
          } catch (NumberFormatException e) {
            e.printStackTrace();
          }
        } // for part in parts
        alternatives.add(sequence);
      } // for s in rules
    } // if ! terminal
  } // Exo(...)

  public List<Integer> matches(String chaine, int from) {
    if (from>=chaine.length())
      return List.of();
    else if (alternatives == null)
      if (chaine.charAt(from) == match)
        return List.of(from+1);
      else
        return List.of();
    else {
      ArrayList<Integer> res = new ArrayList<>();
      for (var seq : alternatives) {
        List<Integer> idx = matchSeq(seq,0,from, chaine);
        res.addAll(idx);
      }
      return res;
    }
  }

  private List<Integer> matchSeq(List<Exo2> seq, int index, int from, String chaine) {
    if (index >= seq.size())
      return List.of(from);
    List<Integer> next = seq.get(index).matches(chaine, from);
    if (index == seq.size()-1)
      return next;
    List<Integer> res = new ArrayList<>();
    for (int nextFrom : next) {
      res.addAll(matchSeq(seq, index+1, nextFrom, chaine));
    }
    return res;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(Exo2.class.getResourceAsStream("data2.txt")));

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

    Exo2 recog = new Exo2(rules, 0);

    long nb = messages.stream().filter(s->recog.matches(s, 0).contains(s.length())).peek(System.out::println).count();

    System.out.printf("Nb=%d\n",nb);

    var test = recog.matches("babbbbaabbbbbabbbbbbaabaaabaaa", 0);
    System.out.println(test);
  } // main
} // Exo
