package lambda.reference;

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

    @Override
    public String toString() {
        return "Employer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
