package stream;

import lambda.reference.Employer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream的三个操作步骤：
 *  1、创建Stream
 *  2、中间操作
 *  3、终止操作（终端操作）
 */
public class CreateStream {

    /**
     * 创建Stream
     */
    @Test
    public void test1(){
        //1、集合->Stream
        //通过collection提供的stream()方法或parallelStream()创建
        List<String> list = new ArrayList<>();
        Stream stream1 = list.stream();

        //2、数组->Stream
        //通过Arrays的stream()静态方法
        Employer[] employers = new Employer[10];
        Stream stream2 = Arrays.stream(employers);

        //3、通过Stream中的of()静态方法
        Stream stream3 = Stream.of("01","02","03");

        //创建无限流 迭代
        //iterate方法中第二个参数  UnaryOperator<T> extends Function<T, T>
        //iterate会调用f.apply来实现方法引用
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x+2);
        stream4.limit(5).forEach(System.out::println);

        //生成
        //generate()中的参数supplier()接口
        Stream.generate(Math::random).limit(5).forEach(System.out::println);


    }
}
