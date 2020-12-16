package day16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.*;

public class Exo2 {
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
    BufferedReader read = new BufferedReader(new InputStreamReader(Exo2.class.getResourceAsStream("data.txt")));
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
    ArrayList<Set<String>> validRulesPerField = new ArrayList<>();
    for (String r : parts) {
      int value = Integer.parseInt(r);
      myTicket.add(value);
      Set<String> validRules = new HashSet<>();
      for (var rule : rules.entrySet()) {
        if (rule.getValue().stream().anyMatch(rg -> rg.isValid(value)))
          validRules.add(rule.getKey());
      } // for rule in rules
      validRulesPerField.add(validRules);
    } // for r in parts

    ArrayList<ArrayList<Integer>> nearbyTickets = new ArrayList<>();
    line = read.readLine();
    line = read.readLine(); //"nearby tickets"
    line = read.readLine();
    long sumInvalid = 0;
    while (line != null) {
      ArrayList<Integer> aTicket = new ArrayList<>();
      parts = line.split(" *, *");
      int idx = 0;

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
        } else {
          for (var rule : rules.entrySet()) {
            if (rule.getValue().stream().noneMatch(rg -> rg.isValid(value))) {
              if (validRulesPerField.get(idx).remove(rule.getKey()))
                System.out.printf("value %d of field %d violates rule %s!\n", value, idx, rule.getKey());
            }
          } // for rule in rules
        } // if valid
        idx++;
      } // for r in parts
      nearbyTickets.add(aTicket);
      line = read.readLine();
    } // while more lines

    System.out.printf("Sum of invalids : %d\n", sumInvalid);

    int idx = 0;
    for (var fieldRules : validRulesPerField) {
      System.out.printf("Candidate rules for fiels %d are %s\n", idx, fieldRules.toString());
      idx++;
    }
    System.out.println("----------------------");
    System.out.println("--     Deducing     --");
    System.out.println("----------------------");

    boolean changed;
    do {
      changed = false;
      for (var fieldRules : validRulesPerField) {
        if (fieldRules.size() == 1) {
          for (var otherFieldRules : validRulesPerField) {
            if (otherFieldRules != fieldRules)
              changed |= otherFieldRules.removeAll(fieldRules);
          }// for otherFieldRules in validRulesPerField
        }
      } // for fieldRules in validRulesPerField
    } while (changed);
    long puzzle = 1;
    for (int i = 0, validRulesPerFieldSize = validRulesPerField.size(); i < validRulesPerFieldSize; i++) {
      Set<String> fieldRules = validRulesPerField.get(i);
      System.out.printf("Candidate rules for fiels %d are %s\n", idx, fieldRules.toString());
      for (String rule : fieldRules) {
        if (rule.startsWith("departure"))
          puzzle *= myTicket.get(i);
      }
    }
    System.out.printf("Puzzle = %d\n",puzzle);
  } // main
} // Exo
