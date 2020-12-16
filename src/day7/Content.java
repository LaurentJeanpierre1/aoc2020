package day7;

public class Content {
  String color;
  int nb;

  @Override
  public String toString() {
    return String.format("{color='%s', nb=%d}", color, nb);
  }

  public Content(String color, int nb) {
    this.color = color;
    this.nb = nb;
  }
}
