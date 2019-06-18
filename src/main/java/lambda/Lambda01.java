package lambda;

import org.junit.Test;

import java.util.function.Consumer;

/**
 * (参数) -> 逻辑
 * JVM会做参数的类型推断
 * 类似匿名内部类，对接口方法的重写
 *
 * Lambda表达式需要函数式接口的支持
 * 函数式接口：接口中只有一个抽象方法，称为函数式接口，如果有 同样的 参数数量 的抽象方法，Lambda区分不出来
 *  可以使用注解@FunctionalInterface检查是否是函数式接口
 */

public class Lambda01 {
    /**
     * 语法格式一：无参数无返回值
     */
    @Test
    public void test1(){
        Runnable runnable = () -> System.out.println("草泥马");
        runnable.run();
    }

    /**
     * 有一个参数，无返回值
     * 若只有一个参数，小括号可以不写
     */
    @Test
    public void test2(){
        Consumer consumer = (x) -> System.out.println(x);
        consumer.accept("我屌尼玛的");
    }

    /**
     * 如果多条语句，要用大括号括起来
     */
    public void test3(){
//        Comparable<Integer> comparable = (x,y) -> {
//            System.out.println("真棒");
//            return Integer.compare(x,y);
//        };
    }

    /**
     * 如果只有一条语句，返回值和大括号都可以省略
     */
    public void test4(){
//        Comparable<Integer> comparable = (x,y) -> Integer.compare(x,y);
    }
}
