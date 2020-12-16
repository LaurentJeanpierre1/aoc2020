package day6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Exo2 {
  public static void main(String[] args) throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(Exo2.class.getResourceAsStream("data.txt")));
    Set<Character> common = null;
    Set<Character> reponses = new TreeSet<>();
    int somme = 0;
    while (true) {
      String line = read.readLine();
      if (line == null) break;
      if (line.isEmpty()) {
        System.out.println(common.toString() + "->" + common.size());
        somme += common.size();
        common = null;
      } else {
        for (Character c : line.trim().toCharArray())
          reponses.add(c);
        if (common == null)
          common = new HashSet<>(reponses); // copy
        else {
          common.removeIf(c -> ! reponses.contains(c));
        }
        reponses.clear();
      }
    } // while more lines
    if (common != null){
      System.out.println(common.toString() + "->" + common.size());
      somme += common.size();
      common = null;
    }
    System.out.printf("Somme = %d%n", somme);
  } // main
} // Exo
