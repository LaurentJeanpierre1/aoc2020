package day22;

import gnu.trove.list.linked.TIntLinkedList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Player {
  String name;
  TIntLinkedList cards;

  public Player(Player old, int nbCards) {
    name = old.name;
    cards = new TIntLinkedList();
    var ite = old.cards.iterator();
    for (int i=0; i<nbCards; ++i)
      cards.add(ite.next());
  }

  @Override
  public String toString() {
    return "Player %s's deck: %s}".formatted(name, cards.toString());
  }

  public Player(BufferedReader read) throws IOException {
    name = read.readLine();
    cards = new TIntLinkedList();
    String line = read.readLine();
    while (line != null && ! line.isBlank()) {
      cards.add(Integer.parseInt(line));
      line = read.readLine();
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(Player.class.getResourceAsStream("data.txt")));

    Player p1 = new Player(read);
    Player p2 = new Player(read);

    while (p1.hasCards() && p2.hasCards()) {
      System.out.println(p1.toString());
      System.out.println(p2.toString());
      int c1 = p1.draw();
      int c2 = p2.draw();
      if (c1>c2) {
        System.out.println("Player 1 wins the round!");
        p1.add(c1);
        p1.add(c2);
      } else {
        System.out.println("Player 2 wins the round!");
        p2.add(c2);
        p2.add(c1);
      }
      System.out.println();
    } // while not won

    long score = p1.getScore() + p2.getScore();
    System.out.printf("Final score = %d\n", score);
  } // main

  long getScore() {
    AtomicLong mult = new AtomicLong(cards.size());
    AtomicLong sum = new AtomicLong();
    cards.forEach(i-> sum.addAndGet(i * (mult.getAndDecrement()))>0);
    return sum.get();
  }

  void add(int card) {
    cards.add(card);
  }

  int draw() {
    return cards.removeAt(0);
  }

  boolean hasCards() {
    return ! cards.isEmpty();
  }


} // Exo
