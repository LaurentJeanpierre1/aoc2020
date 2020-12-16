package day2;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Tobogan {
  public static void main(String[] args) {
    Scanner s = new Scanner(Tobogan.class.getResourceAsStream("pass.txt"));
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
      if (cpt >= Integer.parseInt(t[0]) && cpt <= Integer.parseInt(t[1])) {
        System.out.println("Ok");
        ok++;
      } else
        System.out.println("Error");
    }
    System.out.printf("Compte = %d\n", ok);
  }
}
