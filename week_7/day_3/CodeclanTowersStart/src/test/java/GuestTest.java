import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GuestTest {

    private Guest guest;

    @Before
    public void setUp() throws Exception {
        guest = new Guest("Basil Fawlty");
    }

    @Test
    public void hasName() {
        assertEquals("Basil Fawlty", guest.getName());
    }
}
