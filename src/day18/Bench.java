package day18;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 2,
    jvmArgsAppend = {"-server", "-disablesystemassertions"})
@Warmup(iterations = 1, time = 5)
@Measurement(iterations = 3, time = 3)

public class Bench {
}
