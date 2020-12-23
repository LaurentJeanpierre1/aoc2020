package day23;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

//oops, 10 000 000 * indexof sur une liste de 1 000 000 :(
public class Cups2 {
  public static void main(String[] args) throws IOException {
    String input = "389125467";
//    String input = "739862541";
    LinkedList<Integer> cups = new LinkedList<>();
    for (char c : input.toCharArray())
      cups.add(c-'0');
    for (int i=10; i<1000000; ++i)
      cups.add(i);

    ArrayList<Integer> moving = new ArrayList<>(3);

    for (int moves=0; moves < 10000000; moves++) {
      //System.out.println("cups: "+cups);
      int current = cups.removeFirst();
      int next = current-1;
      if (next==0) next=9;
      //System.out.print("pick up: ");
      for (int i=0; i<3; ++i) {
        Integer cup = cups.removeFirst();
        moving.add(cup);
        //System.out.printf("%d ",cup);
      }
      //System.out.println();
      while (moving.contains(next)) {
        next--;
        if (next==0) next=9;
      }
      //System.out.printf("destination: %d\n", next);
      ListIterator<Integer> ite = cups.listIterator(cups.indexOf(next));
      ite.next();
      for (int cup : moving)
        ite.add(cup);
      moving.clear();
      cups.addLast(current);
    }
    //System.out.println(cups);

    System.out.print("Answer is: ");
    ListIterator<Integer> ite = cups.listIterator(cups.indexOf(1));
    ite.next();
    ite.remove(); // cup 1
    System.out.println(ite.next());
    System.out.println(ite.next());
  } // main
} // Exo
