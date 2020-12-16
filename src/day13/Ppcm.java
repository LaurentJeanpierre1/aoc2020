package day13;

public class Ppcm {
  static long pgcd(long a, long b) {
    if (a == 0) return b;
    if (a > b) return pgcd(a-b, b);
    return pgcd(b-a, a);
  }

  static long pgcd2(long a, long b) {
    while (a != 0) {
      if (a>b) a = a % b;
      else {
        long tmp = b % a;
        b = a;
        a = tmp;
      }
    }
    return b;
  }

  static long ppcm (long a, long b) {
    return a*b / pgcd2(a, b);
  }

  public static void main(String[] args) {
    //System.out.println(pgcd2(12, 18));
    //System.out.println(ppcm(12, 18));

    //7,13,x,x,59,x,31,19
    long v = ppcm(7, 13);
    System.out.println(v);
    //v = ppcm(v, 59);
    //v = ppcm(v, 31);
    v = ppcm(v, 19);
    System.out.println(v);
    // 1068781
    // 3162341
  }
}
