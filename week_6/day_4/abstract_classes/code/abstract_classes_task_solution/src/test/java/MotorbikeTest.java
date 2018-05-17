import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MotorbikeTest {
    Motorbike motorbike;

    @Before
    public void before() {
        motorbike = new Motorbike();
    }

    @Test
    public void motorbikeHasNumberOfWheels() {
        assertEquals(2, motorbike.getNumberOfWheels());
    }

    @Test
    public void canStartEngine() {
        assertEquals("Vrrmmm", motorbike.startEngine());
    }

    @Test
    public void canDrive() {
        assertEquals("use handlebars", motorbike.drive());
    }

}
