package day23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class Cups {
  public static void main(String[] args) throws IOException {
    String input = "389125467";
//    String input = "739862541";
    LinkedList<Integer> cups = new LinkedList<>();
    for (char c : input.toCharArray())
      cups.add(c-'0');

    ArrayList<Integer> moving = new ArrayList<>(3);

    for (int moves=0; moves < 100; moves++) {
      System.out.println("cups: "+cups);
      int current = cups.removeFirst();
      int next = current-1;
      if (next==0) next=9;
      System.out.print("pick up: ");
      for (int i=0; i<3; ++i) {
        Integer cup = cups.removeFirst();
        moving.add(cup);
        System.out.printf("%d ",cup);
      }
      System.out.println();
      while (moving.contains(next)) {
        next--;
        if (next==0) next=9;
      }
      System.out.printf("destination: %d\n", next);
      ListIterator<Integer> ite = cups.listIterator(cups.indexOf(next));
      ite.next();
      for (int cup : moving)
        ite.add(cup);
      moving.clear();
      cups.addLast(current);
    }
    System.out.println(cups);

    System.out.print("Answer is: ");
    ListIterator<Integer> ite = cups.listIterator(cups.indexOf(1));
    ite.next();
    ite.remove(); // cup 1
    while (ite.hasNext()) {
      System.out.print(ite.next());
      ite.remove();
    }
    while (! cups.isEmpty()) System.out.print(cups.removeFirst());
    System.out.println();
  } // main
} // Exo
