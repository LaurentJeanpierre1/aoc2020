package day8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Exo2 {
  public static void main(String[] args) throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(Exo2.class.getResourceAsStream("data.txt")));
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
    for (int line=0; line < programme.size(); ++line) {
      Instr old = programme.get(line);
      if (old instanceof Instr.NOP && old.param != 0)
        programme.set(line, new Instr.JMP(old.param));
      else if (old instanceof Instr.JMP)
        programme.set(line, new Instr.NOP(old.param));
      else continue; // nothing changed, no need to run
      System.out.printf("Changed line %d...", line);
      if (runProgram(programme)) break;
      System.out.print("reverted!\n");
      programme.set(line, old);
    }
    System.out.printf("Accumulator = %d\n",Instr.val);
  } // main

  private static boolean runProgram(List<Instr> programme) {
    Instr.val = 0;
    boolean executed[] = new boolean[programme.size()];
    int pc = 0;
    int oldPC = 0;
    while (pc != programme.size() && ! executed[pc]) {
      oldPC = pc;
      executed[pc] = true;
      pc = programme.get(pc).execute(pc);
    }
    return pc == programme.size() && oldPC == pc-1;
  }
} // Exo
