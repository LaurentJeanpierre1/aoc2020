package day16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Exo {
  private static class Range{
    int min,max;

    public Range(int min, int max) {
      this.min = min;
      this.max = max;
    }

    boolean isValid(int value) {
      return value>=min && value<=max;
    }

    @Override
    public String toString() {
      return MessageFormat.format("{0}->{1}", min, max);
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(Exo.class.getResourceAsStream("data.txt")));
    String line = read.readLine();
    HashMap<String, List<Range>> rules = new HashMap<>();
    while (! line.isEmpty()) {
      String[] parts = line.split(" *: *");
      String[] ranges = parts[1].split(" *or *");
      ArrayList<Range> theRanges = new ArrayList<>();
      for (String r : ranges) {
        String[] numbers = r.split(" *- *");
        theRanges.add(new Range(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1])));
      }
      rules.put(parts[0], theRanges);
      System.out.printf("%s : %s\n",parts[0],theRanges.toString());
      line = read.readLine();
    } // while more lines

    ArrayList<Integer> myTicket = new ArrayList<>();
    line = read.readLine(); //"Your ticket"
    line = read.readLine();
    String[] parts = line.split(" *, *");
    for (String r : parts) {
      myTicket.add(Integer.parseInt(r));
    }

    ArrayList<ArrayList<Integer>> nearbyTickets = new ArrayList<>();
    line = read.readLine();
    line = read.readLine(); //"nearby tickets"
    line = read.readLine();
    long sumInvalid = 0;
    while (line != null) {
      ArrayList<Integer> aTicket = new ArrayList<>();
      parts = line.split(" *, *");
      for (String r : parts) {
        int value = Integer.parseInt(r);
        aTicket.add(value);
        boolean invalid = true;
        for (var rule : rules.entrySet()) {
          if (rule.getValue().stream().anyMatch(rg -> rg.isValid(value))) {
            invalid = false;
            break;
          }
        } // for rule in rules
        if (invalid) {
          System.out.printf("Invalid value: %d\n", value);
          sumInvalid += value;
        }
      } // for r in parts
      nearbyTickets.add(aTicket);
      line = read.readLine();
    } // while more lines

    System.out.printf("Sum of invalids : %d\n", sumInvalid);
  } // main
} // Exo
