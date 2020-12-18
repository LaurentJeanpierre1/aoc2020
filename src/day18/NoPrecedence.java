package day18;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class NoPrecedence {
  private static final long NOVAL = -123456;

  public static void main(String[] args) throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(NoPrecedence.class.getResourceAsStream("data.txt")));
    long sum = 0;
    while (true) {
      String line = read.readLine();

      if (line == null) break;

      long val = analyse(line);
      System.out.printf("%s = %d\n",line, val);
      sum += val;
    } // while more lines
    System.out.printf("\nSum = %d\n", sum);
  } // main

  private static long analyse(String line) {
    Reader read = new InputStreamReader(new ByteArrayInputStream(line.getBytes(StandardCharsets.UTF_8)));

    return analyse(read);
  }

  private static long analyse(Reader read) {
    long res = 0;
    Character op = null;
    try {
      while (true) {
        long val = NOVAL;
        int r = read.read();
        if (r == -1) return res;
        char c = (char) r;
        if (c == ' ') continue;
        else if (c == ')')
          return res;
        else if (Character.isDigit(c))
          val = c - '0';
        else if (c == '(')
          val = analyse(read);

        if (op != null) {
          switch (op) {
            case '+' -> res += val;
            case '*' -> res *= val;
            default -> {
            }
          }
          op = null;
        } else  if (val != NOVAL)
          res = val;
        else if (c == '+')
          op = c;
        else if (c == '*')
          op = c;
      } // while(true)
    } catch (IOException e) {
      return 0;
    }
  }

} // Exo
