package cn.think.in.java.learing;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 *
 *
 * @author 玄灭
 * @date 2018/10/11-下午4:16
 */
 class Test {

    public int say() {
        int a = 0;
        for (int i = 0; i < 100; i++) {
            a += i;
        }
        return a;
    }

}

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
 public class Test2{

    static Method method;
    static Test test = new Test();
    static {
        try {
            method = Test.class.getDeclaredMethod("say");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Benchmark
    public void test1() {
        test.say();
    }

    @Benchmark
    public void test2() throws InvocationTargetException, IllegalAccessException {
        method.invoke(test, null);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(Test2.class.getSimpleName()).build();

        new Runner(opt).run();
    }
}