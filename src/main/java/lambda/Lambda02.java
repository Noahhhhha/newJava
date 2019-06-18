package lambda;

import org.junit.Test;

public class Lambda02 {
    @Test
    public void test1(){
        MyFunction myFunction = () -> System.out.println("叼你妈");
        myFunction.diaonima();
    }
}
