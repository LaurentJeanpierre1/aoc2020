package day17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Exo {
  private static class Coords{
    @Override
    public String toString() {
      return "{x=%d, y=%d, z=%d}".formatted(x, y, z);
    }

    public Coords(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Coords coords = (Coords) o;

      if (x != coords.x) return false;
      if (y != coords.y) return false;
      return z == coords.z;
    }

    @Override
    public int hashCode() {
      int result = x;
      result = 31 * result + y;
      result = 31 * result + z;
      return result;
    }

    int x,y,z;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(Exo.class.getResourceAsStream("data.txt")));
    var parts = read.lines().map(ligne -> ligne.toCharArray()).collect(Collectors.toList());

    Set<Coords> active = new HashSet<>();

    int minX=0,maxX=0,minY=0,maxY=0,minZ=0,maxZ=0;
    for (int i = 0, partsSize = parts.size(); i < partsSize; i++) {
      char[] l = parts.get(i);
      for (int j = 0, lLength = l.length; j < lLength; j++) {
        char c = l[j];
        if (c == '#') {
          active.add(new Coords(j, i, 0));
          if (maxX<j) maxX=j;
          if (maxY<i) maxY=i;
        }
      }
    }

    System.out.println(countNeighbors(active, new Coords(0,1,0)));
    print(active,minX,maxX, minY, maxY, minZ, maxZ);

    for (int cycles=0; cycles<6; cycles++) {
      Set<Coords> next = new HashSet<>();
      int nminX=1000,nmaxX=-1000,nminY=1000,nmaxY=-1000,nminZ=1000,nmaxZ=-1000;

      for (int z=minZ-1; z<=maxZ+1; ++z) {
        for (int y=minY-1; y<=maxY+1; ++y) {
          for (int x=minX-1; x<=maxX+1; ++x) {
            Coords c = new Coords(x, y, z);
            if (active.contains(c))
              switch (countNeighbors(active,c)) {
                case 0,1:continue;
                case 2,3:
                  next.add(c);
                  if (x<nminX) nminX = x;
                  if (y<nminY) nminY = y;
                  if (z<nminZ) nminZ = z;
                  if (x>nmaxX) nmaxX = x;
                  if (y>nmaxY) nmaxY = y;
                  if (z>nmaxZ) nmaxZ = z;
                default:
              }
            else if (countNeighbors(active,c) == 3) {
              next.add(c);
              if (x<nminX) nminX = x;
              if (y<nminY) nminY = y;
              if (z<nminZ) nminZ = z;
              if (x>nmaxX) nmaxX = x;
              if (y>nmaxY) nmaxY = y;
              if (z>nmaxZ) nmaxZ = z;
            }
          }
        }
      }
      active = next;
      print(active,minX=nminX,maxX=nmaxX, minY=nminY, maxY=nmaxY, minZ=nminZ, maxZ=nmaxZ);

    } // for cycles
    System.out.println(active.size());
  } // main

  private static void print(Set<Coords> active, int minX, int maxX, int minY, int maxY,int minZ, int maxZ) {
    for (int z=minZ; z<=maxZ; ++z) {
      System.out.printf("z=%d\n",z);
      for (int y=minY; y<=maxY; ++y) {
        for (int x=minX; x<=maxX; ++x) {
          if (active.contains(new Coords(x,y,z)))
            System.out.print('#');
          else
            System.out.print('.');
        }
        System.out.println();
      }
    }
  }

  private static int countNeighbors(Set<Coords> active, Coords c) {
    int count = 0;
    for (int x = -1; x<=1; ++x)
      for (int y = -1; y<=1; ++y)
        for (int z = -1; z<=1; ++z)
          if (x!= 0 || y!=0 || z!=0)
            if (active.contains(new Coords(c.x+x, c.y+y, c.z+z)))
              if (++count > 3) return count;
    return count;
  }
} // Exo
