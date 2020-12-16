package day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;

public class PassportScanner {
  public static void main(String[] args) throws IOException {
    int nbValid = 0;
    int nbInvalid = 0;
    BufferedReader read = new BufferedReader(new InputStreamReader(PassportScanner.class.getResourceAsStream("data.txt")));
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
      if (valid)
        ++ nbValid;
      else
        ++ nbInvalid;
    } // while more passports
    System.out.printf("%d valides et %d invalides\n",nbValid, nbInvalid);
  } // main
}
