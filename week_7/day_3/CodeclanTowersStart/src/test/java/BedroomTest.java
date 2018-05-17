import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BedroomTest {

    BedRoom singleRoom;
    BedRoom doubleRoom;
    BedRoom suite;
    Guest guest;

    @Before
    public void before(){
        singleRoom = new BedRoom(RoomType.SINGLE, 25.00, 101);
        doubleRoom  = new BedRoom(RoomType.DOUBLE, 50.00, 201);
        suite  = new BedRoom(RoomType.SUITE, 100.00, 301);
        guest = new Guest("Sybil Fawlty");
    }

    @Test
    public void hasType(){
        assertEquals(RoomType.SINGLE, singleRoom.getType());
        assertEquals(RoomType.DOUBLE, doubleRoom.getType());
        assertEquals(RoomType.SUITE, suite.getType());
    }

    @Test
    public void roomHasCapacity(){
        assertEquals(1, singleRoom.getCapacity());
        assertEquals(2, doubleRoom.getCapacity());
        assertEquals(4, suite.getCapacity());
    }

    @Test
    public void roomHasRate(){
        assertEquals(25.00, singleRoom.getRate(), 0.01);
        assertEquals(50.00, doubleRoom.getRate(), 0.01);
        assertEquals(100.00, suite.getRate(), 0.01);
    }

    @Test
    public void roomHasNumber(){
        assertEquals(101, singleRoom.getRoomNumber());
        assertEquals(201, doubleRoom.getRoomNumber());
        assertEquals(301, suite.getRoomNumber());
    }

    @Test
    public void cannotCheckInMoreThanCapacity(){
        ArrayList<Guest> testList = new ArrayList<>();
        testList.add(guest);
        testList.add(guest);
        testList.add(guest);
        singleRoom.checkIn(testList);
        assertEquals(0, singleRoom.getCheckedInGuestCount());
    }

    @Test
    public void canCheckI(){
        ArrayList<Guest> testList = new ArrayList<>();
        testList.add(guest);
        singleRoom.checkIn(testList, 3);
        assertEquals(1, singleRoom.getCheckedInGuestCount());
    }

    @Test
    public void canCharge(){
        ArrayList<Guest> testList = new ArrayList<>();
        testList.add(guest);
        singleRoom.checkIn(testList, 3);
        assertEquals(75.0, singleRoom.getTotal(), 0.01);
    }

}
