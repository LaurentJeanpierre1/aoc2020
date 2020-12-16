package day3;

import day2.Tobogan;

import java.util.Scanner;

public class Slope {
  public static void main(String[] args) {
    Scanner s = new Scanner(Slope.class.getResourceAsStream("data.txt"));
    int tree = 0;
    int c = 0;
    int l = 0;
    while (s.hasNextLine()) {
      String line = s.nextLine();
      if (line.charAt(c) == '#') ++tree;
      l += 1;
      c = (c+3) % line.length();
    }
    System.out.println(tree);
  }
}
