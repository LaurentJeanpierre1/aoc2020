package day22;

import gnu.trove.list.linked.TIntLinkedList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Game {
  static int nextGame = 1;

  Player p1, p2;
  int gameNo;
  int round;
  List<TIntLinkedList> hands1,hands2;

  public Game(Player p1, Player p2) {
    this.p1 = p1;
    this.p2 = p2;
    gameNo = nextGame++;
    round = 1;
    hands1 = new ArrayList<>();
    hands2 = new ArrayList<>();
  }

  int winner() {
    while (p1.hasCards() && p2.hasCards()) {
      System.out.printf("-- Round %d (Game %d) --\n", round, gameNo);
      System.out.println(p1.toString());
      System.out.println(p2.toString());
      if (hands1.contains(p1.cards) && hands2.contains(p2.cards)) {
        System.out.println("Hands already played!");
        return 1;
      } else {
        hands1.add(new TIntLinkedList(p1.cards));
        hands2.add(new TIntLinkedList(p2.cards));
      }

      int c1 = p1.draw();
      int c2 = p2.draw();
      System.out.printf("%s plays: %d\n", p1.name, c1);
      System.out.printf("%s plays: %d\n", p2.name, c2);
      int win;
      if (c1<=p1.cards.size() && c2<=p2.cards.size())
        win = new Game(new Player(p1,c1), new Player(p2,c2)).winner();
      else if (c1>c2) win = 1;
      else win = 2;
      if (win == 1) {
        p1.add(c1);
        p1.add(c2);
      } else {
        p2.add(c2);
        p2.add(c1);
      }
      round++;
    } // while not won
    if (p1.hasCards()) return 1;
    return 2;
  }

  long getScore() {
    return p1.getScore() + p2.getScore();
  }

  public static void main(String[] args) throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(Player.class.getResourceAsStream("data.txt")));

    Player p1 = new Player(read);
    Player p2 = new Player(read);

    Game g = new Game(p1, p2);
    int win = g.winner();
    System.out.printf("Final score = %d\n", g.getScore());
  }
}
