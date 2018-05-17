import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CarTest {
    Car car;

    @Before
    public void before() {
        car = new Car("Ford Escort");
    }

    @Test
    public void carHasNumberOfWheels() {
        assertEquals(4, car.getNumberOfWheels());
    }

    @Test
    public void carHasModel() {
        assertEquals("Ford Escort", car.getModel());
    }

    @Test
    public void canStartEngine() {
        assertEquals("Vrrmmm", car.startEngine());
    }

    @Test
    public void canDrive() {
        assertEquals("use steering wheel", car.drive());
    }

}
