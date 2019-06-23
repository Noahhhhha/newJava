package lambda.reference;


import lombok.*;

import java.util.Objects;

/**
 * lambod 能帮忙写javabean
 * 需要装一个lambod插件
 * 再在maven中引用即可
 *         <dependency>
 *             <groupId>org.projectlombok</groupId>
 *             <artifactId>lombok</artifactId>
 *             <version>1.18.8</version>
 *         </dependency>
 *
 */

@Getter
@Setter
@ToString(exclude ={"name","age","status"}) //toString()
@EqualsAndHashCode   //生成equals()和hashcode()
@AllArgsConstructor  //生成带有所有参数的构造方法
@NoArgsConstructor //无参构造方法
//@RequiredArgsConstructor // 会生成一个包含常量，和标识了NotNull的变量的构造方法。生成的构造方法是私有的private    会有冲突，次处注释掉
public class Employer {

    private String name;
    private Integer age;
    private Status status;

//    public Employer() {
//    }

    public Employer(String name) {
        this.name = name;
    }

    public Employer(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

//    public Employer(String name, Integer age, Status status) {
//        this.name = name;
//        this.age = age;
//        this.status = status;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }



//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Employer employer = (Employer) o;
//        return Objects.equals(name, employer.name) &&
//                Objects.equals(age, employer.age);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name, age);
//    }

//    @Override
//    public String toString() {
//        return "Employer{" +
//                "name='" + name + '\'' +
//                ", age=" + age +
//                ", status=" + status +
//                '}';
//    }

//    public Status getStatus() {
//        return status;
//    }
//
//    public void setStatus(Status status) {
//        this.status = status;
//    }

    //有点像内部类
    public enum Status{
        BUSY,
        FREE,
        VOCATION
    }
}
