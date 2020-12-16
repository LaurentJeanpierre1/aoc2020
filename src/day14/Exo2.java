package day14;

import gnu.trove.list.TCharList;
import gnu.trove.list.linked.TCharLinkedList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Exo2 {

  private static HashMap<Long, Long> memory;

  public static void main(String[] args) throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(Exo2.class.getResourceAsStream("data.txt")));
//    var parts = read.lines().mapToLong(Long::parseLong).toArray();
    var parts = read.lines().map(ligne -> ligne.split(" *= *")).collect(Collectors.toList());

    memory = new HashMap<>();
    String mask = "000000000000000000000000000000000000";
    for (String[] instr : parts) {
      if (instr[0].equals("mask")) {
        mask = instr[1];
      } else {
        int addr = Integer.parseInt(instr[0].substring(4, instr[0].length()-1));
        long val = Long.parseLong(instr[1]);
        TCharList bitList = new TCharLinkedList();
        bitList.add(mask.toCharArray());
        memoryPut(addr, bitList, val);
      }
    } // for instr in parts
    long sum = memory.values().stream().mapToLong(Long::longValue).sum();
    System.out.printf("Sum = %d\n",sum);
  } // main

  private static void memoryPut(long addr, TCharList bitList, long val) {
    if (bitList.isEmpty())
      memory.put(addr, val);
    else {
      char c;
      do {
        c = bitList.get(0);
        bitList.remove(0,1);
        if (c == '1')
          addr |= (1L << bitList.size());
      } while ((c == '0' || c=='1') && !bitList.isEmpty());
      if (c != 'X')
        memory.put(addr, val);
      else {
        memoryPut(addr | (1L<<bitList.size()), new TCharLinkedList(bitList),val );
        memoryPut(addr & (-1L - (1L<<bitList.size())), new TCharLinkedList(bitList),val );
      }
    }
  }
} // Exo
