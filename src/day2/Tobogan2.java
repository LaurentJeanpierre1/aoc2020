package day2;

import java.util.Arrays;
import java.util.Scanner;

public class Tobogan2 {
  public static void main(String[] args) {
    Scanner s = new Scanner(Tobogan2.class.getResourceAsStream("pass.txt"));
    int ok = 0;
    while (s.hasNextLine()) {
      String l = s.nextLine();
      String[] t = l.split("[ \\-:]+");
      System.out.println(Arrays.toString(t));
      int cpt = 0;
      char ref = t[2].charAt(0);
      for (char c : t[3].toCharArray()) {
        if (c == ref) cpt ++;
      }
      char c1 = t[3].charAt(Integer.parseInt(t[0])-1);
      char c2 = t[3].charAt(Integer.parseInt(t[1])-1);
      if ((c1 == ref) ^ (c2 == ref)) {
        System.out.println("Ok");
        ok++;
      } else
        System.out.println("Error");
    }
    System.out.printf("Compte = %d\n", ok);
  }
}
