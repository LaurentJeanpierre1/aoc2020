package day12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class Exo2 {
  static int north = 0;
  static int east = 0;
  static int dx = 10, dy = 1;


  char direction;
  int distance;

  public Exo2(char direction, int distance) {
    this.direction = direction;
    this.distance = distance;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(Exo2.class.getResourceAsStream("data.txt")));
//    var parts = read.lines().mapToLong(Long::parseLong).toArray();
    var actions = read.lines().map(ligne -> {
      char dir = ligne.charAt(0);
      int dist = Integer.parseInt(ligne.substring(1));
      return new Exo2(dir, dist);
    }).peek(Exo2::execute).collect(Collectors.toList());

    System.out.printf("N=%d, E=%d, dist=%d\n", north, east, Math.abs(north)+Math.abs(east));

  } // main

  private void execute() {
    int tmp;
    switch (direction) {
      case 'N' : dy += distance; break;
      case 'S' : dy -= distance; break;
      case 'E' : dx += distance; break;
      case 'W' : dx -= distance; break;
      case  'F' :
        north += dy * distance;
        east += dx * distance;
        break;
      case 'R' :
        switch (distance % 360) {
          case 0 : break;
          case 90 :
            tmp = -dx;
            dx = dy;
            dy = tmp;
            break;
          case 180 :
            dx = -dx;
            dy = -dy;
            break;
          case 270 :
            tmp = dx;
            dx = -dy;
            dy = tmp;
            break;
          default: throw new IllegalArgumentException();
        }
        break;
      case 'L' :
        switch (distance % 360) {
          case 0 : break;
          case 270 :
            tmp = -dx;
            dx = dy;
            dy = tmp;
            break;
          case 180 :
            dx = -dx;
            dy = -dy;
            break;
          case 90 :
            tmp = dx;
            dx = -dy;
            dy = tmp;
            break;
          default: throw new IllegalArgumentException();
        }
        break;
    }
  }
} // Exo
