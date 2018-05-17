import java.util.ArrayList;

public class Hotel {

    private ArrayList<Room> rooms;

    public Hotel(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public void checkIntoBedRoom(ArrayList<Guest> guestList, int roomNumber, int numberOfNights) {
                    BedRoom bedroom = getBedRoom(roomNumber);
                    bedroom.checkIn(guestList, numberOfNights);

    }

    public BedRoom getBedRoom(int roomNumber) {
        BedRoom bedroom = null;
        for (Room room : this.rooms) {
            if (room instanceof BedRoom) {
                 bedroom = (BedRoom) room;
                if (bedroom.getRoomNumber() == roomNumber) {
                    return bedroom;
                }
            }
        }
        return bedroom;
    }

    public ConferenceRoom getConferenceRoom() {
        for (Room room : this.rooms) {
            if (room instanceof ConferenceRoom) {
                ConferenceRoom conferenceRoom = (ConferenceRoom) room;
                return conferenceRoom;
            }
        }
        return null;
    }

    public void checkIntoConferenceRoom(ArrayList<Guest> guestList){
        ConferenceRoom room = getConferenceRoom();
        room.checkIn(guestList);
    }

    public DiningRoom getDiningRoom() {
        for (Room room : this.rooms) {
            if (room instanceof DiningRoom) {
                DiningRoom diningRoom = (DiningRoom) room;
                return diningRoom;
            }
        }
        return null;
    }

    public void checkIntoDiningRoom(ArrayList<Guest> guestList){
        DiningRoom room = getDiningRoom();
        room.checkIn(guestList);
    }

}
