package day14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Exo {
  public static void main(String[] args) throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(Exo.class.getResourceAsStream("data.txt")));
//    var parts = read.lines().mapToLong(Long::parseLong).toArray();
    var parts = read.lines().map(ligne -> ligne.split(" *= *")).collect(Collectors.toList());

    HashMap<Integer,Long> memory = new HashMap<>();
    long maskAnd = 0xFFFFFFFFFL;
    long maskOr = 0;
    for (String[] instr : parts) {
      if (instr[0].equals("mask")) {
        maskAnd = maskOr = 0;
        for (char c : instr[1].strip().toCharArray()) {
          maskAnd <<= 1;
          maskOr <<= 1;
          switch (c) {
            case '0' :
              break;
            case '1' :
              maskAnd |= 1;
              maskOr |= 1;
              break;
            default:
              maskAnd |= 1;
          }
        } // for c
      } else {
        int addr = Integer.parseInt(instr[0].substring(4, instr[0].length()-1));
        long val = Long.parseLong(instr[1]);
        val &= maskAnd;
        val |= maskOr;
        memory.put(addr, val);
      }
    } // for instr in parts
    long sum = memory.values().stream().mapToLong(Long::longValue).sum();
    System.out.printf("Sum = %d\n",sum);
  } // main
} // Exo
