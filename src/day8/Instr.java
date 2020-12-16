package day8;

import java.util.ArrayList;

public abstract class Instr {
  public static class JMP extends Instr{
    public JMP(int param) {
      super(param);
    }

    @Override
    public int execute(int line) {
      return line + param;
    }
  };
  public static  class ACC  extends Instr{
    public ACC(int param) {
      super(param);
    }

    @Override
    public int execute(int line) {
      val += param;
      return line + 1;
    }
  }
  public  static class NOP  extends Instr{
    public NOP(int param) {
      super(param);
    }

    @Override
    public int execute(int line) {
      return line + 1;
    }
  };

  public static int val = 0;
  protected int param;

  public Instr(int param) {
    this.param = param;
  }
  public abstract int execute(int line);
}
