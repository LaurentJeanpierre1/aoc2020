package day18;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Precedence {
  private static final long NOVAL = -123456;

  public static void main(String[] args) throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(Precedence.class.getResourceAsStream("data.txt")));
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

    return analyse(read, false);
  }

  static Character pushBack = null;

  private static long analyse(Reader read, boolean inParentheses) {
    long res = 0;
    Character op = null;
    try {
      while (true) {
        long val = NOVAL;
        char c;
        if (pushBack != null) {
          c = pushBack;
          pushBack = null;
        } else {
          int r = read.read();
          if (r == -1) return res;
          c = (char) r;
          if (c == ' ') continue;
        }
        if (c == ')') {
          if (! inParentheses)
            pushBack = c;
          return res;

        } else if (Character.isDigit(c))
          val = c - '0';
        else if (c == '(') {
          val = analyse(read, true);
          if (pushBack != null)
            pushBack = null;
        }
        if (op != null) {
          res += val;
          op = null;
        } else  if (val != NOVAL)
          res = val;
        else if (c == '+')
          op = c;
        else if (c == '*')
          res *= analyse(read, false); // in case a ')' would return right val, do not consume ')'
      } // while(true)
    } catch (IOException e) {
      return 0;
    }
  }

} // Exo
