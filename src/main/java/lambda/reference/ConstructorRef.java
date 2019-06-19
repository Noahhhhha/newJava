package lambda.reference;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 构造器引用
 *
 * ClassName::new;
 */
public class ConstructorRef {

    /**
     * 无参构造器的引用
     */
    @Test
    public void test1(){
        Supplier<Employer> giveEmployer = Employer::new;
    }

    /**
     * 有参构造器的引用
     * 有几个参数取决于接口中的方法有几个参数
     */
    @Test
    public void test2(){
        //Function<T, R>接口是将T转换为R
        Function<String, Employer> oneConstructor = Employer::new;

        //BiFunction<T, S, R> 接口方法是将T，S转换为R
        BiFunction<String, Integer, Employer> twoConstructor = Employer::new;

        System.out.println(oneConstructor.apply("Tom"));
        System.out.println(twoConstructor.apply("peter", 18));
    }
}
