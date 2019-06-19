package lambda.reference;

import org.junit.Test;

import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 方法引用，原先lambda表达式的地方直接写入别人写好的方法 ::
 *  注意：
 *      方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
 */
public class FunctionRef {

    /**
     * 类名 :: 实例方法名
     * 注意：
     *      若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName
     */
    @Test
    public void test4(){
        BiPredicate<String,String> predicate = (x,y) -> x.equals(y);
        BiPredicate<String,String> biPredicate = String::equals;
    }
    /**
     * 类名::静态方法名
     */
    @Test
    public void test02(){
        // 将int类型转换string类型
        // Function<Integer,String> function = (x) -> String.valueOf(x);
        Function<Integer,String> function = String::valueOf;
        System.out.println(function.apply(10));
    }



    /**
     * 实例::方法名 System.out 返回一个PrintStream
     */
    @Test
    public void test01(){
        Consumer consumer = System.out::println;
        consumer.accept("傻逼");
    }
}
