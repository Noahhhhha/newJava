package lambda.reference;

import org.junit.Test;

import java.util.function.Function;

/**
 * 数组引用
 */
public class ArrayRef {
    @Test
    public void test1(){
        Function<Integer, String[]> function = (x) -> new String[x];
        System.out.println(function.apply(10).length);

        Function<Integer, String[]> integerFunction = String[]::new;
        System.out.println(integerFunction.apply(20).length);
    }
}
