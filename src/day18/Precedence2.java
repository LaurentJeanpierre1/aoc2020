package day18;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 2,
    jvmArgsAppend = {})
@Warmup(iterations = 1, time = 5)
@Measurement(iterations = 3, time = 3)
public class Precedence2 {
  private static final long NOVAL = -123456;
  private List<String> file;

  public static void main(String[] args) {
    Options opt = new OptionsBuilder().include(Precedence2.class.getSimpleName()).build();

    try {
      new Runner(opt).run();
    } catch (RunnerException e) {
      e.printStackTrace();
    }
  } // main

  @Benchmark
  public void measurePrecedence2() {
    new Precedence2();
  }

  public Precedence2() {
    try {
      file = Files.readAllLines(Paths.get("src/day18/input.txt"));
    }catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Benchmark
  public long compute() {
    long sum = 0;
    //long time = System.nanoTime();
    for (String line : file) {
      long val = analyse(line);
      sum += val;
    } // while more lines
    //time = System.nanoTime() - time;
    //System.out.printf("\nSum = %d in %f ms\n", sum, time / 1000000.);
    return sum;
  }

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
