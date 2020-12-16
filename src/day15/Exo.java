package day15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Exo {

  private static ArrayList<Integer> numbers;

  public static void main(String[] args) throws IOException {
    numbers = new ArrayList<>(30000000);
    numbers.addAll(List.of(15,12,0,14,3,1));
    int next=0;
    while (numbers.size()<30000000){
      next = getNext();
    }
    System.out.println(next);
  } // main

  private static int getNext() {
    ListIterator<Integer> ite = numbers.listIterator(numbers.size());
    int dist = 1;
    int nb = ite.previous();
    int cur = -1;
    while (ite.hasPrevious() && (cur=ite.previous()) != nb) dist++;
    if (cur != nb)
      dist = 0;
    numbers.add(dist);
    return dist;
  }
} // Exo
