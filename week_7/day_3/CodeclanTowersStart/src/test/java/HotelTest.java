import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class HotelTest {
    Hotel hotel;
    BedRoom singleRoom;
    BedRoom doubleRoom;
    BedRoom suite;
    ConferenceRoom conferenceRoom;
    DiningRoom diningRoom;
    Guest guest;
    ArrayList<Guest> testGuests;

    @Before
    public void before(){
        singleRoom = new BedRoom(RoomType.SINGLE, 25.00, 101);
        doubleRoom  = new BedRoom(RoomType.DOUBLE, 50.00, 201);
        suite  = new BedRoom(RoomType.SUITE, 100.00, 301);
        guest = new Guest("Sybil Fawlty");
        conferenceRoom = new ConferenceRoom(100, "Conference Room B", 200);
        diningRoom = new DiningRoom(50, "The Ritz");
        ArrayList<Room> testRooms = new ArrayList<>();
        testRooms.add(singleRoom);
        testRooms.add(doubleRoom);
        testRooms.add(suite);
        testRooms.add(conferenceRoom);
        testRooms.add(diningRoom);
        hotel = new Hotel(testRooms);
        testGuests = new ArrayList<>();

    }

    @Test
    public void canCheckIntoSingleRoom(){
        testGuests.add(guest);
        BedRoom room = hotel.getBedRoom(101);
        hotel.checkIntoBedRoom(testGuests, 101, 3);
        assertEquals(1, room.getCheckedInGuestCount());
    }

    @Test
    public void canCheckIntoConference(){
        testGuests.add(guest);
        hotel.checkIntoConferenceRoom(testGuests);
        ConferenceRoom room = hotel.getConferenceRoom();
        assertEquals(1, room.getCheckedInGuestCount());
    }

    @Test
    public void canCheckIntoDiningRoom(){
        testGuests.add(guest);
        hotel.checkIntoDiningRoom(testGuests);
        DiningRoom room = hotel.getDiningRoom();
        assertEquals(1, room.getCheckedInGuestCount());
    }
}
