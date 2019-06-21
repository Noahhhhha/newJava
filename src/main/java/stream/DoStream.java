package stream;

import lambda.reference.Employer;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Stream的中间操作
 */
public class DoStream {
    List<Employer> employerList = Arrays.asList(new Employer("张三",18,Employer.Status.BUSY),
            new Employer("李三",19,Employer.Status.BUSY),
            new Employer("王五",20,Employer.Status.BUSY),
            new Employer("王五",20,Employer.Status.FREE),
            new Employer("王五",20,Employer.Status.FREE),
            new Employer("赵六",21,Employer.Status.VOCATION));
    @Test
    public void test1(){
        //filter（排除） 中的参数是Predicate断言式接口，返回布尔型，是true的会被保留下来
        //limit（截断）
        employerList.stream()
                    .filter((e) -> e.getAge()>19)
                    .limit(1)
                    .forEach(System.out::println);
    }

    @Test
    public void test2(){
        //skip（跳过） 跳过流中的前几个
        employerList.stream()
                .filter(e -> e.getAge()>19)
                .skip(1)
                .forEach(System.out::println);

    }

    @Test
    public void test3(){
        //distinct （筛选）通过流生成元素的hascode()和equals()去除重复值
        //只有一个王五
        //需要在employer类中加上hascode方法，不然不生效
        employerList.stream()
                    .filter(e -> e.getAge() > 19)
                    .distinct()
                    .forEach(System.out::println);
    }


    @Test
    public void test4(){
        //映射 map 和 flatMap 会将一种类型 T 转换为 R ，从一个流到另一个流
        //flatMap 规避了 Stream<Stream<Employer>> 的情况， 会将多个流合并成一个流
        employerList.stream()
                    .map(x -> x.getName())
                    .forEach(System.out::println);
    }

    @Test
    public void test5(){
        //排序 sorted() 自然排序 - javabean 要实现 comparable 接口  ， 自动实现排序方法
        //sorted(comparator com) -定制排序 比较器类要实现 comparator 接口 传递给sorted
        employerList.stream()
                .sorted((a,b) -> -a.getAge().compareTo(b.getAge()))
                .forEach(System.out::println);
    }

    /**
     * 终止操作
     */
    @Test
    public void test6(){
     /*
		allMatch——是不是都这样？
		anyMatch——至少有一个？
		noneMatch——一个都没有？
		findFirst——返回第一个元素
		findAny——返回当前流中的任意元素
		count——返回流中元素的总个数
		max——返回流中最大值
		min——返回流中最小值
	 */
        boolean bl = employerList.stream()
                .allMatch((e) -> e.getStatus().equals(Employer.Status.BUSY));

        System.out.println(bl);

        boolean bl1 = employerList.stream()
                .anyMatch((e) -> e.getStatus().equals(Employer.Status.BUSY));

        System.out.println(bl1);

        boolean bl2 = employerList.stream()
                .noneMatch((e) -> e.getStatus().equals(Employer.Status.BUSY));

        System.out.println(bl2);
    }

    @Test
    public void test7(){
        //当可能出现 null 值的时候，用Optional包装起来，避免null

        Optional<Employer> op = employerList.stream()
                .sorted((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()))
                .findFirst();

        System.out.println(op.get());

        System.out.println("--------------------------------");

        Optional<Employer> op2 = employerList.parallelStream()
                .filter((e) -> e.getStatus().equals(Employer.Status.FREE))
                .findAny();

        System.out.println(op2.get());
    }

    @Test
    public void test8(){
        long count = employerList.stream()
                .filter((e) -> e.getStatus().equals(Employer.Status.FREE))
                .count();

        System.out.println(count);

        Optional<Integer> op = employerList.stream()
                .map(Employer::getAge)
                .max(Integer::compare);

        System.out.println(op.get());

        Optional<Employer> op2 = employerList.stream()
                .min((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()));

        System.out.println(op2.get());
    }

    //注意：流进行了终止操作后，不能再次使用
    @Test
    public void test9(){
        Stream<Employer> stream = employerList.stream()
                .filter((e) -> e.getStatus().equals(Employer.Status.FREE));

        long count = stream.count();

        stream.map(Employer::getAge)
                .max(Integer::compare);
    }
}
