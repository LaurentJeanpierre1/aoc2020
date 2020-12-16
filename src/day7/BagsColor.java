package day7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.List.of;

public class BagsColor {
  public static void main(String[] args) throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(BagsColor.class.getResourceAsStream("data.txt")));
    Map<String, List<Content>> rules = new HashMap<>();
    Map<String, List<String>> contained = new HashMap<>();
    while (true) {
      String line = read.readLine();
      if (line == null) break;

      String[] predicate = line.split("bags contain");
      List<Content> res = new LinkedList<>();
      String key = predicate[0].trim();
      if (predicate[1].trim().equals("no other bags.")) {
        // nothing to do
      } else {
        String[] bags = predicate[1].split("bags?[,.]");
        for (String bag : bags) {
          String s = bag.trim();
          if (s.isEmpty()) continue;
          int no = Integer.parseInt(s.substring(0, 1));
          String color = s.substring(2);
          Content aBag = new Content(color, no);
          res.add(aBag);
          if (contained.containsKey(color)) {
            contained.get(color).add(key);
          } else {
            contained.put(color, new LinkedList<>(of(key)));
          }
        }
      }
      rules.put(key, res);
      System.out.println(key + res.toString());
    } // while more lines

    rules = read.lines().map(ligne -> ligne.split(" bags contain ")).collect(Collectors.toMap(tab->tab[0], tab-> {
      if (tab[1].equals("no other bags.")) return List.of();
      else return (List<Content>) Arrays.stream(tab[1].split("bags?[,.]")).map(
          elt -> new Content(elt.substring(2), Integer.parseInt(elt.substring(0,1)))
      ).peek(elt->contained.compute(elt.color, (coul, liste)->{liste.add(tab[0]); return liste;}))
       .collect(Collectors.toList());
    }
    ));
    String searched = "shiny gold";
    contained.computeIfAbsent(searched, k -> of());
    List<String> containers = new LinkedList<>();
    List<String> toAdd = new LinkedList<>(contained.get(searched));
    while (!toAdd.isEmpty()) {
      List<String> tmp = toAdd;
      toAdd = new LinkedList<>();
      for (String bag : tmp) {
        containers.add(bag);
        if (contained.get(bag) == null)
          continue;
        for (String into : contained.get(bag)) {
          if (!containers.contains(into) && !toAdd.contains(into) && !tmp.contains(into)) {
            toAdd.add(into);
          }
        } // for into in containers(bag)
      } // for bag in tmp
    } // while toAdd.isNotEmpty
    System.out.printf("%d containers : %s\n", containers.size(), containers.toString());


    ///

    int nbBags = addBags(searched, rules);
    System.out.printf("Total bags to carry in %s : %d\n", searched, nbBags-1);
    nbBags = addBags2(searched, rules);
    System.out.printf("Total bags to carry in %s : %d\n", searched, nbBags-1);
  } // main

  private static int addBags(String bag, Map<String, List<Content>> rules) {
    int sum = 1; // this bag
    for (Content content : rules.get(bag)) {
      int bags = addBags(content.color, rules);
      sum += content.nb * bags;
      System.out.printf("%s contains %d %s (%d*%d bags)\n", bag, content.nb, content.color, content.nb, bags);
    }
    return sum;
  }

  private static int addBags2(String bag, Map<String, List<Content>> rules) {
    return rules.get(bag).stream().mapToInt(contenu->contenu.nb*addBags2(contenu.color, rules)).sum()+1;
  }
} // Exo
