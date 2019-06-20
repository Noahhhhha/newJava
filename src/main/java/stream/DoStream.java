package stream;

import lambda.reference.Employer;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream的中间操作
 */
public class DoStream {
    List<Employer> employerList = Arrays.asList(new Employer("张三",18),
            new Employer("李三",19),
            new Employer("王五",20),
            new Employer("王五",20),
            new Employer("赵六",21));
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
        //映射 map 和 flatMap 会将一种类型 T 转换为 R
        //flatMap 规避了 Stream<Stream<Employer>> 的情况， 会将多个流合并成一个流
        employerList.stream()
                    .map(x -> x.getName())
                    .forEach(System.out::println);
    }


}
