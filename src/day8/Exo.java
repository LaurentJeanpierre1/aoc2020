package day8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Exo {
  public static void main(String[] args) throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(Exo.class.getResourceAsStream("data.txt")));
    List<Instr> programme = read.lines().map(ligne -> ligne.split(" "))
                                .map(ligne-> {
                                  int v = Integer.parseInt(ligne[1].trim());
                                  return switch (ligne[0].trim().toUpperCase(Locale.ROOT)) {
                                    case "NOP" -> new Instr.NOP(v);
                                    case "ACC" -> new Instr.ACC(v);
                                    case "JMP" -> new Instr.JMP(v);
                                    default -> throw new IllegalArgumentException(ligne[0]);
                                  };
    }).collect(Collectors.toList());
    boolean executed[] = new boolean[programme.size()];
    int pc = 0;
    while (! executed[pc]) {
      executed[pc] = true;
      pc = programme.get(pc).execute(pc);
    }
    System.out.printf("Accumulator = %d\n",Instr.val);
  } // main
} // Exo
