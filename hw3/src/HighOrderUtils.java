import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;

public class HighOrderUtils {

    static NamedBiFunction<Double, Double, Double> add = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String name() {
            return "add";
        }

        @Override
        public Double apply(Double aDouble, Double aDouble2) {
            return aDouble + aDouble2;
        }
    };

    static NamedBiFunction<Double, Double, Double> subtract = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String name() {
            return "diff";
        }

        @Override
        public Double apply(Double aDouble, Double aDouble2) {
            return aDouble - aDouble2;
        }
    };

    static NamedBiFunction<Double, Double, Double> multiply = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String name() {
            return "mult";
        }

        @Override
        public Double apply(Double aDouble, Double aDouble2) {
            return aDouble * aDouble2;
        }
    };

    static NamedBiFunction<Double, Double, Double> divide = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String name() {
            return "div";
        }

        @Override
        public Double apply(Double aDouble, Double aDouble2) throws ArithmeticException {
            return Math.floor(aDouble / aDouble2);
        }
    };

    public static void main(String[] args) throws Exception {

//        List<NamedBiFunction<Double, Double, Double>> functions = Arrays.asList(add, subtract, multiply, divide);
//        List<Double> nums = Arrays.asList(1d, 3d, 2d, 5d, 6d);
//        zip(nums, functions);
//        nums.stream().forEach(System.out::println);

    }

    public static interface NamedBiFunction<T, U, R> extends java.util.function.BiFunction<T, U, R> {
        String name();
    }



    public static <T> T zip(List<T> args, List<NamedBiFunction<T, T, T>> bifunctions) throws Exception {
        if (args.size() < bifunctions.size()) {
            throw new Exception();
        }

        AtomicInteger i = new AtomicInteger();
        bifunctions.stream().forEachOrdered(bi -> args.set(i.get()+1, bi.apply(args.get(i.get()), args.get(i.incrementAndGet()))));

        return args.get(args.size()-1);
    }

    public static class FunctionComposition<T, F, G>{
        BiFunction<Function<T, F>, Function<F,G>, Function<T, G>> composition = Function::andThen;
    }
}
