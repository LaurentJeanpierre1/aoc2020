package day5;

import day4.PassportScanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HighestSeatId {
  public static void main(String[] args) throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(HighestSeatId.class.getResourceAsStream("data.txt")));
    List<Seat> seats = new LinkedList<>();
    while (true) {
      String line = read.readLine();
      if (line == null) break;
      seats.add(new Seat(line.trim()));
    } // while more lines
    int max = seats.stream().mapToInt(Seat::getID).max().orElse(-1);
    System.out.println(max);

    seats.sort((s1,s2)->Integer.compare(s1.getID(), s2.getID()));

    Iterator<Seat> ite = seats.iterator();
    int precID = ite.next().getID();
    while (ite.hasNext()) {
      int nextID = ite.next().getID();
      System.out.printf("%d - %d \n",precID, nextID);
      if (nextID == precID+2) System.out.println("Vacant seat = "+ (nextID-1));
      precID = nextID;
    }
  } // main
}
