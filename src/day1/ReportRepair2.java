package day1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ReportRepair2 {
  public static void main(String[] args) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(ReportRepair2.class.getResourceAsStream("data1.txt")));
    var lignes = reader.lines().mapToInt(Integer::parseInt).sorted().toArray();
    for (int i=lignes.length-1; i>=0; --i) {
      int first = lignes[i];
      for (int j = 0; j<lignes.length && first+lignes[j]<=2020; ++j) {
        int second = lignes[j];
        int sum = first+ second;
        for (int k = 0; k<lignes.length && sum+lignes[k]<=2020; ++k) {
          int third = lignes[k];
          if (sum + third == 2020) {
            System.out.printf("%d + %d + %d = %d ; mult = %d\n", first, second, third, third + sum, first * second * third);
            return;
          }
        }
      }
    }
  }
}
