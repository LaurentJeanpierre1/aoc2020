package day23;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class Cups2b {
  static Cups2b[] tableau = new Cups2b[1000001];
  int idx;
  Cups2b next;
  Cups2b prev;

  public Cups2b(int idx, Cups2b prev) {
    this.idx = idx;
    this.prev = prev;
    tableau[idx] = this;
    if (prev != null)
      prev.next = this;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(Integer.toString(idx));
    Cups2b suite = next;
    while (suite != this) {
      if (suite == null) {
        sb.append("-");
        break;
      }
      sb.append(suite.idx);
      suite = suite.next;
    }
    return sb.toString();
  }

  public static void main(String[] args) throws IOException {
//    String input = "389125467";
    String input = "739862541";
    Cups2b liste = new Cups2b(0, null);
    Cups2b suite = liste;
    for (char c : input.toCharArray())
      suite = new Cups2b(c-'0', suite);
    liste = liste.next;
    suite.next = liste; // circulaire
    liste.prev = suite;
    suite = null;

    ArrayList<Integer> movedCups = new ArrayList<>();
    for (int moves=0; moves < 100; moves++) {
      System.out.println("cups: "+liste);
      int current = liste.idx;
      liste = liste.next;
      int next = current-1;
      if (next==0) next=9;
      Cups2b moving = liste;
      System.out.print("pick up: ");
      for (int i=0; i<3; ++i) {
        int cup = liste.idx;
        liste = liste.next;
        System.out.printf("%d ",cup);
        movedCups.add(cup);
      }
      moving.next.next.next = null;
      moving.prev.next = liste;
      liste.prev = moving.prev;
      System.out.println();
      while (movedCups.contains(next)) {
        next--;
        if (next==0) next=9;
      }
      movedCups.clear();
      System.out.printf("destination: %d\n", next);

      Cups2b insert = tableau[next];
      moving.prev = insert;
      insert.next.prev = moving.next.next;
      moving.next.next.next = insert.next;
      insert.next = moving;
    }
    System.out.println(liste);

    System.out.print("Answer is: ");
    Cups2b ite = tableau[1].next;
    while (ite.idx != 1) {
      System.out.print(ite.idx);
      ite = ite.next;
    }
    System.out.println();
  } // main
} // Exo
