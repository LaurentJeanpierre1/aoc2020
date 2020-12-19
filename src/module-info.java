module adventOfCode2020 {
//  requires antlr;
  requires trove;
  requires javafx.base;
  requires javafx.graphics;
  requires jmh.generator.annprocess;
  requires jmh.core;
  opens day17;
  exports day17;
  exports day18.generated to  jmh.core;
}