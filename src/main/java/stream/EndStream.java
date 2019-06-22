package stream;

import lambda.reference.Employer;
import org.junit.Test;
import sun.awt.dnd.SunDragSourceContextPeer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EndStream {
    List<Employer> employerList = Arrays.asList(new Employer("张三",18,Employer.Status.BUSY),
            new Employer("李三",19,Employer.Status.BUSY),
            new Employer("王五",20,Employer.Status.BUSY),
            new Employer("王五",20,Employer.Status.FREE),
            new Employer("王五",20,Employer.Status.FREE),
            new Employer("赵六",21,Employer.Status.VOCATION));

    /**
     * 终止操作
     */
    @Test
    public void testCollect(){
        //收集 将一种形式 -> 另一种 如 List -> Set
        List<Employer> list = employerList.stream()
                .collect(Collectors.toList());

        HashSet<String> collect = employerList.stream().map(e -> e.getName()).collect(Collectors.toCollection(HashSet::new));
        collect.forEach(System.out::println);
        list.forEach(System.out::println);
    }

    @Test
    public void testCollect2(){
        Optional<Integer> max = employerList.stream()
                .map(Employer::getAge)
                .collect(Collectors.maxBy(Integer::compare));

        System.out.println(max.get());

        Optional<Employer> op = employerList.stream()
                .collect(Collectors.minBy((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge())));

        System.out.println(op.get());

        Integer sum = employerList.stream()
                .collect(Collectors.summingInt(Employer::getAge));

        System.out.println(sum);

        Double collect = employerList.stream()
                .collect(Collectors.averagingInt(Employer::getAge));

        System.out.println(collect);

        Long count = employerList.stream()
                .collect(Collectors.counting());

        System.out.println(count);

        System.out.println("--------------------------------------------");

        IntSummaryStatistics intSummaryStatistics = employerList.stream()
                .collect(Collectors.summarizingInt(Employer::getAge));

        System.out.println(intSummaryStatistics.getMax());
    }

    //分组
    @Test
    public void testCollect3(){
        Map<Employer.Status, List<Employer>> map = employerList.stream()
                .collect(Collectors.groupingBy(Employer::getStatus));

        System.out.println(map);
    }

    //多级分组  groupby
    @Test
    public void test12(){
        Map<Employer.Status, Map<String, List<Employer>>> map = employerList.stream()
                .collect(Collectors.groupingBy(Employer::getStatus, Collectors.groupingBy((e) -> {
                    if(e.getAge() >= 60)
                        return "老年";
                    else if(e.getAge() >= 35)
                        return "中年";
                    else
                        return "成年";
                })));

        System.out.println(map);
    }

    //分区
    @Test
    public void test13(){
        Map<Boolean, List<Employer>> map = employerList.stream()
                .collect(Collectors.partitioningBy((e) -> e.getAge() >= 5000));

        System.out.println(map);
    }

    //拼接
    @Test
    public void test11(){
        String str = employerList.stream()
                .map(Employer::getName)
                .collect(Collectors.joining("," , "----", "----"));

        System.out.println(str);
    }

    @Test
    public void test10(){
        Optional<Integer> sum = employerList.stream()
                .map(Employer::getAge)
                .collect(Collectors.reducing(Integer::sum));

        System.out.println(sum.get());
    }

    @Test
    public void testReduce(){
        //规约 map / reduce(计算)
        //有起始值
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);
        Integer sum = list.stream()
                .reduce(100, Integer::sum); //提供一个起始值100，避免了null
        System.out.println(sum);

        //无起始值
        Optional<Integer> age = employerList.stream() //没有起始值，可能为null
                .map((e) -> e.getAge()) //map将employer转化为int类
                .reduce(Integer::sum);
        System.out.println(age.get());
    }


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
