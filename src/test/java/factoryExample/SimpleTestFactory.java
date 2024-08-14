package factoryExample;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import static tests.ITestConstants.*;
import static tests.ITestConstants.T_SHIRT_RED;

public class SimpleTestFactory {

    @Factory
    public Object[] factoryMethod() {
        return new Object[] {
               new SimpleTest("Test 1"),
               new SimpleTest("Test 2"),
               new SimpleTest("Test 3")
        };
    }

    @Test
    public void runAllTests() {
        Object[] tests = factoryMethod();
        System.out.println("This is method 3 again");
        for (Object test : tests) {
            ((SimpleTest) test).testMethod3();
        }
    }

}

