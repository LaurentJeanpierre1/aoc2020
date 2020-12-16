package day1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.Arrays;

public class ReportRepair {
  public static void main(String[] args) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(ReportRepair.class.getResourceAsStream("data1.txt")));
    var lignes = reader.lines().mapToInt(Integer::parseInt).sorted().toArray();
    for (int i=lignes.length-1; i>=0; --i) {
      int first = lignes[i];
      for (int j = 0; j<lignes.length && first+lignes[j]<=2020; ++j) {
        int second = lignes[j];
        if (first + second == 2020) {
          System.out.printf("%d + %d = %d ; mult = %d\n", first, second, first+second, first*second);
          return;
        }
      }
    }
  }
}
