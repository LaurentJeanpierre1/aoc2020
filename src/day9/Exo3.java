package day9;

import gnu.trove.list.array.TLongArrayList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Exo3 {
  public static void main(String[] args) throws IOException {
    long start = System.nanoTime();
    BufferedReader read = new BufferedReader(new InputStreamReader(Exo3.class.getResourceAsStream("data.txt")));
    //var parts = read.lines().mapToLong(Long::parseLong).toArray();
    TLongArrayList liste = new TLongArrayList(1000);
    String l;
    while ((l = read.readLine()) != null) {
      liste.add(Long.parseLong(l));
    }
    long parts[] = liste.toArray();
    long readTime = System.nanoTime();
    long fault = checkFault(parts, 25);
    long faultTime = System.nanoTime();
    //System.out.printf("First fault = %d\n", fault);

    long weakness = findWeakness(parts, fault);
    long weakTime = System.nanoTime();

    //System.out.printf("weakness = %d\n", weakness);

    System.out.printf("""
        Durées :
         - lecture : %.3f ms
         - fault : %.3f ms
         - weakness : %.3f ms
        """, (readTime-start) / 1000000., (faultTime-readTime) / 1000000., (weakTime-faultTime)/1000000.);
  } // main

  private static long findWeakness(long[] parts, long fault) {
    int first = 0;
    int last = 1;
    long sum = parts[0] + parts[1];
    while (sum != fault) {
      if (sum < fault) {
        ++last;
        sum += parts[last];
      } else {
        sum -= parts[first];
        ++first;
      }
    }
    long[] slice = Arrays.copyOfRange(parts, first, last+1);
    Arrays.sort(slice);
    //System.out.printf("Found sequence : %s\n", Arrays.toString(slice));
    return slice[0] + slice[slice.length-1];
  }

  private static long checkFault(long[] parts, int win) {
    for (int i = win; i<parts.length; ++i) {
      boolean found = false;
      long partI = parts[i];
      for (int j=i-win; j<i-1 && !found; ++j) {
        long partJ = parts[j];
        for (int k=j+1; k<i && !found; ++k) {
          found = (partI == partJ + parts[k]);
        } // for k
      } // for j
      if (!found) return partI;
    } // for i
    return -1;
  }
} // Exo
