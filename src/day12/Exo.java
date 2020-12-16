package day12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class Exo {
  static int north = 0;
  static int east = 0;
  static int dx = 1, dy = 0;


  char direction;
  int distance;

  public Exo(char direction, int distance) {
    this.direction = direction;
    this.distance = distance;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(Exo.class.getResourceAsStream("ex.txt")));
//    var parts = read.lines().mapToLong(Long::parseLong).toArray();
    var actions = read.lines().map(ligne -> {
      char dir = ligne.charAt(0);
      int dist = Integer.parseInt(ligne.substring(1));
      return new Exo(dir, dist);
    }).peek(Exo::execute).collect(Collectors.toList());

    System.out.printf("N=%d, E=%d, dist=%d\n", north, east, Math.abs(north)+Math.abs(east));

  } // main

  private void execute() {
    int tmp;
    switch (direction) {
      case 'N' : north += distance; break;
      case 'S' : north -= distance; break;
      case 'E' : east += distance; break;
      case 'W' : east -= distance; break;
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
