package day23;

import java.io.IOException;
import java.util.ArrayList;

public class Cups3 {
  static Cups3[] tableau = new Cups3[1000001];
  int idx;
  Cups3 next;
  Cups3 prev;

  public Cups3(int idx, Cups3 prev) {
    this.idx = idx;
    this.prev = prev;
    tableau[idx] = this;
    if (prev != null)
      prev.next = this;
  }

  int size() {
    int nb = 1;
    Cups3 suite = next;
    while (suite != this) {
      if (++nb>tableau.length) throw new IllegalStateException();
      suite = suite.next;
    }
    return nb;
  }
  @Override
  public String toString() {
    return String.format("%d <- %d -> %d ", prev==null?-1:prev.idx, idx, next==null?-1:next.idx);
  }

  public static void main(String[] args) throws IOException {
    //String input = "389125467";
    String input = "739862541";
    Cups3 liste = new Cups3(0, null);
    Cups3 suite = liste;
    for (char c : input.toCharArray())
      suite = new Cups3(c-'0', suite);
    for (int i=10; i<=1000000; ++i)
      suite = new Cups3(i, suite);
    liste = liste.next;

    suite.next = liste; // circulaire
    liste.prev = suite;
    suite = null;

    System.out.println(liste.size());

    ArrayList<Integer> movedCups = new ArrayList<>();
    for (int moves=0; moves < 10000000; moves++) {
      //System.out.println("cups: "+liste);
      int current = liste.idx;
      liste = liste.next;
      int next = current-1;
      if (next==0) next=tableau.length-1;
      Cups3 moving = liste;
      //System.out.print("pick up: ");
      for (int i=0; i<3; ++i) {
        int cup = liste.idx;
        liste = liste.next;
        //System.out.printf("%d ",cup);
        movedCups.add(cup);
      }
      moving.next.next.next = null;
      moving.prev.next = liste;
      liste.prev = moving.prev;
      //System.out.println();
      while (movedCups.contains(next)) {
        next--;
        if (next==0) next=tableau.length-1;
      }
      movedCups.clear();
      //System.out.printf("destination: %d\n", next);

      Cups3 insert = tableau[next];
      moving.prev = insert;
      insert.next.prev = moving.next.next;
      moving.next.next.next = insert.next;
      insert.next = moving;
    }
    //System.out.println(liste);

    System.out.println(liste.size());

    System.out.print("Answer is: ");
    Cups3 ite = tableau[1].next;
    System.out.printf("1 -> %d * %d\n",ite.idx, ite.next.idx);
  } // main
} // Exo
