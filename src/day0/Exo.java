package day0;

import day8.Instr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Exo {
  public static void main(String[] args) throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(Exo.class.getResourceAsStream("data.txt")));
//    var parts = read.lines().mapToLong(Long::parseLong).toArray();
//    var parts = read.lines().map(ligne -> ligne.split(" ")).collect(Collectors.toList());

    while (true) {
      String line = read.readLine();
      if (line == null) break;

      //TODO

    } // while more lines
  } // main
} // Exo
