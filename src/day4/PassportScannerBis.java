package day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static java.util.List.of;

public class PassportScannerBis {
  public static void main(String[] args) throws IOException {
    int nbValid = 0;
    int nbInvalid = 0;
    BufferedReader read = new BufferedReader(new InputStreamReader(PassportScannerBis.class.getResourceAsStream("data.txt")));
    while (true) {
      String line = read.readLine();
      StringBuilder sb = new StringBuilder();
      while (line != null && ! line.isBlank()) {
        sb.append(line);
        sb.append(' ');
        line = read.readLine();
      }
      String passport = sb.toString().trim();
      if (passport.isEmpty()) break;
      /*byr (Birth Year)
        iyr (Issue Year)
        eyr (Expiration Year)
        hgt (Height)
        hcl (Hair Color)
        ecl (Eye Color)
        pid (Passport ID)
        cid (Country ID)
       */
      String[] fields={"cid", "byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
      String[] passFields = passport.split("[ ]+");
      LinkedList<String> fieldNames = new LinkedList<>();
      LinkedList<String> fieldValues = new LinkedList<>();
      for(String s : passFields) {
        String[] tmp = s.split(":");
        fieldNames.add(tmp[0]);
        fieldValues.add(tmp[1]);
      }

      boolean valid = true;
      for (int i = 1, fieldsLength = fields.length; i < fieldsLength; i++) {
        String f = fields[i];
        valid &= fieldNames.contains(f);
      }
      if (valid) {
        Iterator<String> keys = fieldNames.iterator();
        Iterator<String> values = fieldValues.iterator();
        while(keys.hasNext()) {
          String value = values.next();
          valid &= switch (keys.next()) {
            case "cid"-> true;
            case "byr"->
              checkByr(value);
            case "iyr"->
              checkIyr(value);
            case "eyr"->
              checkEyr(value);
            case "hgt"->
              checkHgt(value);
            case "hcl"->
              checkHcl(value);
            case "ecl"->
              checkEcl(value);
            case "pid"->
              checkPid(value);
            default ->  true;
          };
        }
      }
      if (valid)
        ++ nbValid;
      else
        ++ nbInvalid;
    } // while more passports
    System.out.printf("%d valides et %d invalides\n",nbValid, nbInvalid);
  } // main

  private static boolean checkByr(String value) {
    try {
      int v = Integer.parseInt(value);
      boolean r = value.length()==4 && v>=1920  && v<=2002;
      if (!r) {
        System.out.printf("Byr not valid : value=%s\n",value);
      }
      return r;
    } catch (NumberFormatException e) {
      System.out.printf("Byr not valid : value=%s\n",value);
      return false;
    }
  }
  private static boolean checkIyr(String value) {
    try {
      int v = Integer.parseInt(value);
      boolean r = value.length()==4 && v>=2010  && v<=2020;
      if (!r) {
        System.out.printf("Iyr not valid : value=%s\n",value);
      }
      return r;
    } catch (NumberFormatException e) {
      System.out.printf("Iyr not valid : value=%s\n",value);
      return false;
    }
  }
  private static boolean checkEyr(String value) {
    try {
      int v = Integer.parseInt(value);
      boolean r = value.length()==4 && v>=2020  && v<=2030;
      if (!r) {
        System.out.printf("Eyr not valid : value=%s\n",value);
      }
      return r;
    } catch (NumberFormatException e) {
      System.out.printf("Eyr not valid : value=%s\n",value);
      return false;
    }
  }

  private static boolean checkHgt(String value) {
    String unit = value.substring(value.length()-2);
    String val = value.substring(0, value.length()-2);
    try {
      int v = Integer.parseInt(val);
      boolean r = false;
      if (unit.equals("cm"))
        r = v>=150  && v<=193;
      if (unit.equals("in"))
        r = v>=59  && v<=76;

      if (!r) {
        System.out.printf("Hgt not valid : value=%s\n",value);
      }
      return r;
    } catch (NumberFormatException e) {
      System.out.printf("Hgt not valid : value=%s\n",value);
      return false;
    }
  }

  private static boolean checkHcl(String value) {
    boolean r = value.matches("#[0-9a-f]{6}");
    if (!r) {
      System.out.printf("Hcl not valid : value=%s\n",value);
    }
    return r;
  }

  private static boolean checkEcl(String value) {
    boolean r = List.of("amb","blu","brn","gry","grn","hzl","oth").contains(value);
    if (!r) {
      System.out.printf("Ecl not valid : value=%s\n",value);
    }
    return r;
  }

  private static boolean checkPid(String value) {
    boolean r = value.matches("[0-9]{9}");
    if (!r) {
      System.out.printf("Pid not valid : value=%s\n",value);
    }
    return r;
  }

}
