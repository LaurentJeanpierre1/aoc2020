package day6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class Exo {
  public static void main(String[] args) throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(Exo.class.getResourceAsStream("data.txt")));
    Set<Character> reponses = new TreeSet<>();
    int somme = 0;
    while (true) {
      String line = read.readLine();
      if (line == null) break;
      if (line.isEmpty()) {
        System.out.println(reponses.toString() + "->" + reponses.size());
        somme += reponses.size();
        reponses.clear();
      } else {
        for (Character c : line.trim().toCharArray())
          reponses.add(c);
      }
    } // while more lines
    if (! reponses.isEmpty()){
      System.out.println(reponses.toString() + "->" + reponses.size());
      somme += reponses.size();
      reponses.clear();
    }
    System.out.printf("Somme = %d%n", somme);
  } // main
} // Exo
