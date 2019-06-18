package lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * java8 四大核心接口
 *
 * Consumer<T> :消费型接口
 *  void accept(T t);
 *
 * Supplier<T> :供给型接口
 *  T get();
 *
 * Function<T, R> :函数型接口
 *  R apply(T t);
 *
 * Predicate<T> :断言型接口
 *  boolean test(T t);
 */
public class Lambda03 {
    /**
     * Predicate<T> :断言型接口，过滤字符串
     *  boolean test(T t);
     */
    @Test
    public void test6(){
        List<String> list = Arrays.asList("cao","shabi","erbi","尼玛的为什么");
        List<String> stringList = new ArrayList<>(list);
        stringList.add("caonimamamammamamma");
        List<String> newStringList = choose(stringList,(x -> x.length() > 3));
        for (String s :
                stringList) {
            System.out.println(s);
        }
    }

    public List<String> choose(List<String> list, Predicate<String> predicate){
                //list.remove(s);
                //java.util.ConcurrentModificationException --用foreach循环删除的时候会抛出此异常
                //需要用迭代器删
                Iterator<String> iterator = list.iterator();
                if (iterator.hasNext()){
                    predicate.test(iterator.next());
                    iterator.remove();
                }
        return list;
    }


    /**
     * Function<T, R> :函数型接口
     *  apply(T t);
     *  T 转换为 R
     */
    @Test
    public void test5(){
        String str = "我屌你吗的   艹";
        System.out.println(getNewString(str, (x) -> x.substring(1, 6)));
        System.out.println(getNewString(str, x -> x.trim()));
    }

    public String getNewString(String string, Function<String, String> function){
        return function.apply(string);
    }

    /**
     * Supplier<T> :供给型接口
     *  T get();
     */
    @Test
    public void test3(){
        Supplier<Integer> supplier = () -> (int)(Math.random() * 100); //不加括号会先强转成int再*100，即0。 100以内的数
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) list.add(supplier.get());
        for (int i : list) System.out.println(i);
    }

    /**
     * 加一个方法修饰
     */
    @Test
    public void test4(){
        List<Integer> list = give(10,() -> (int)(Math.random() * 100));
        for (Integer i :
                list) {
            System.out.println(i);
        }
    }

    public List<Integer> give(int num, Supplier<Integer> supplier){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++){
            list.add(supplier.get());
        }
        return list;
    }

    /**
     * Consumer<T> :消费型接口
     * void accept(T t);
     */
    @Test
    public void test1(){
        Consumer consumer = (m) -> System.out.println("叼你妈" + m);
        consumer.accept(10000);
    }

    /**
     * 消费型，外面套一个方法，传递实例
     */
    @Test
    public void test2(){
        happy(5000,(x) -> System.out.println(x));
    }

    public void happy(double money,Consumer<Double> consumer){
        consumer.accept(money);
    }

}
