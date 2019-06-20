package lambda.reference;

import java.util.Objects;

public class Employer {

    private String name;
    private Integer age;

    public Employer() {
    }

    public Employer(String name) {
        this.name = name;
    }

    public Employer(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employer employer = (Employer) o;
        return Objects.equals(name, employer.name) &&
                Objects.equals(age, employer.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
