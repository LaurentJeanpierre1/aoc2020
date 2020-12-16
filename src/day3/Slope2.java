package day3;

import java.util.Scanner;

public class Slope2 {
  public static void main(String[] args) {
    long tree1 = getTrees(1);
    long tree3 = getTrees(3);
    long tree5 = getTrees(5);
    long tree7 = getTrees(7);
    long tree2 = getTrees2(1);
    System.out.println(tree1);
    System.out.println(tree3);
    System.out.println(tree5);
    System.out.println(tree7);
    System.out.println(tree2);
    System.out.println(tree1*tree2*tree3*tree5*tree7);
  }

  private static int getTrees(int slope) {
    Scanner s = new Scanner(Slope2.class.getResourceAsStream("data.txt"));
    int tree = 0;
    int c = 0;
    int l = 0;
    while (s.hasNextLine()) {
      String line = s.nextLine();
      System.out.print(line.substring(0, c));
      if (line.charAt(c) == '#') {
        ++tree;
        System.out.print('X');
      } else
        System.out.print('O');
      System.out.println(line.substring(c+1));
      l += 1;
      c = (c + slope) % line.length();
    }
    return tree;
  }
  private static int getTrees2(int slope) {
    Scanner s = new Scanner(Slope2.class.getResourceAsStream("data.txt"));
    int tree = 0;
    int c = 0;
    int l = 0;
    while (s.hasNextLine()) {
      String line = s.nextLine();
      System.out.print(line.substring(0, c));
      if (l % 2 == 0) {
        if (line.charAt(c) == '#') {
          ++tree;
          System.out.print('X');
        } else
          System.out.print('O');
          c = (c + slope) % line.length();
      } else {
        System.out.print(line.charAt(c));
      }
      System.out.println(line.substring(c+1));
      l += 1;
    }
    return tree;
  }
}
