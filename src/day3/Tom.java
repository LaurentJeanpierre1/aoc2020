package day3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Tom {
  public static void main(String[] args) {

    ArrayList<String> lines = new ArrayList<String>();
    //String fichier = "C:\\Users\\tomle\\Desktop\\AOC\\day_3\\input_1.txt";
    try {
      InputStream ips = Tom.class.getResourceAsStream("data.txt");
      InputStreamReader isr = new InputStreamReader(ips);
      BufferedReader br = new BufferedReader(isr);
      String ligne = "";
      while((ligne = br.readLine()) != null) {
        lines.add(ligne);
      }
      br.close();
    }
    catch(Exception e){
      System.err.println("ERREUR");
    }

    int nbCharPerLine = lines.get(0).length();
    int i = 0;
    int j = 0;
    long nb[] = {0, 0, 0, 0, 0};

    while(i < lines.size()) {
      if(lines.get(i).charAt(j%nbCharPerLine) == '#') nb[0]++;
      i++;
      j++;
    }

    i = 0;
    j = 0;
    while(i < lines.size()) {
      if(lines.get(i).charAt(j%nbCharPerLine) == '#') nb[1]++;
      i++;
      j += 3;
    }

    i = 0;
    j = 0;
    while(i < lines.size()) {
      if(lines.get(i).charAt(j%nbCharPerLine) == '#') nb[2]++;
      i++;
      j += 5;
    }

    i = 0;
    j = 0;
    while(i < lines.size()) {
      if(lines.get(i).charAt(j%nbCharPerLine) == '#') nb[3]++;
      i++;
      j += 7;
    }

    i = 0;
    j = 0;
    while(i < lines.size()) {
      if(lines.get(i).charAt(j%nbCharPerLine) == '#') nb[4]++;
      i += 2;
      j++;
    }

    System.out.println(Arrays.toString(nb));
    System.out.println(nb[0]*nb[1]*nb[2]*nb[3]*nb[4]);
  }
}
