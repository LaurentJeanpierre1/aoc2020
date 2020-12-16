package day15;

import java.util.HashMap;

public class Exo2 {
  static HashMap<Integer, Integer> numbers;

  public static void main(String[] args) {
    numbers = new HashMap<>(10000);
    numbers.put(15, 1);
    numbers.put(12, 2);
    numbers.put(0, 3);
    numbers.put(14, 4);
    numbers.put(3, 5);
    int last = 1;
    for (int i = 6; i<30000000; ++i) {
      last = getNext(last, i);
    }
    System.out.println(last);
  }

  private static int getNext(int last, int i) {
    int rank = numbers.getOrDefault(last, 0);
    numbers.put(last, i);
    if (rank == 0)
      last = 0;
    else
      last = i -rank;
    return last;
  }
}
