package tests.example;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;

@Log4j2
public class CarTest {

    @Test
    public void carComparison() {
//        Car car1 = new Car("bmw", "210");
//        Car car2 = new Car("bmw", "250");
        Car car3 = new Car();
        car3.setMark("tesla");
        car3.setSpeed("300");
        Car car5 = new Car("bmw", "210", "23");

        Car car4 = Car.builder()
                .mark("byd")
                .speed("40")
                .build();
        System.out.println(car4.getColour());
        car4.setColour("orange");
        System.out.println(car4.getColour());

//        Assert.assertEquals(car1, car2);
    }

    @Test
    public void loggerTest() {
        log.fatal("fatal");
        log.error("error");
        log.warn("warn");
        log.info("info");
        log.debug("debug");
        log.trace("trace");
    }
}
