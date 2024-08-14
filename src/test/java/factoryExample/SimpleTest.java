package factoryExample;

import org.testng.annotations.Test;

public class SimpleTest {
    String message;

    public SimpleTest(String message) {
        this.message = message;
    }

    @Test
    public void testMethod1() {
        System.out.println("Message1: " + message);
    }

    @Test
    public void testMethod2() {
        System.out.println("Message2: " + message);
    }

    @Test
    public void testMethod3() {
        System.out.println("Message3: " + message);
    }
}
