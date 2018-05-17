import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConferenceRoomTest {

    ConferenceRoom conferenceRoom;

    @Before
    public void setUp() throws Exception {
        conferenceRoom = new ConferenceRoom(100, "Jura", 500.0);
    }

    @Test
    public void hasName(){
        assertEquals("Jura", conferenceRoom.getName());
    }

    @Test
    public void hasCapacity(){
        assertEquals(100, conferenceRoom.getCapacity());
    }

    @Test
    public void hasDailyRate(){
        assertEquals(500.00, conferenceRoom.getRate(), 0.01);
    }

    @Test
    public void canGetTotal(){
        assertEquals(500.00, conferenceRoom.getTotal(), 0.01);
    }
}
