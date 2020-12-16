package day5;

public class Seat {
  int row;
  int col;

  int getID() {
    return row*8 + col;
  }

  public Seat(String bsp) {
    int rowBlock = 64;
    row = 0;
    for (int i=0; i<7; ++i) {
      if (bsp.charAt(i) == 'B')
        row += rowBlock;
      rowBlock /= 2;
    }
    int colBlock = 4;
    col = 0;
    for (int i=7; i<10; ++i) {
      if (bsp.charAt(i) == 'R')
        col += colBlock;
      colBlock /= 2;
    }
  }

  @Override
  public String toString() {
    return String.format("Seat{row=%d, col=%d, id=%d}", row, col, getID());
  }

  public static void main(String[] args) {
    System.out.println(new Seat("BFFFBBFRRR")); // row 70, column 7, seat ID 567
    System.out.println(new Seat("FFFBBBFRRR")); // row 14, column 7, seat ID 119
    System.out.println(new Seat("BBFFBBFRLL")); // row 102, column 4, seat ID 820
  }
}
