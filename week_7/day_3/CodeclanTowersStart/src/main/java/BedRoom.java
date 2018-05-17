import java.util.ArrayList;

public class BedRoom extends Room implements Chargable {

    private RoomType type;
    private int roomNumber;
    private double rate;
    private int numberOfNights;


    public BedRoom(RoomType type, double rate, int roomNumber) {
        super(type.getCapacity());
        this.type = type;
        this.rate = rate;
        this.roomNumber = roomNumber;
        this.numberOfNights = 0;
    }

    public RoomType getType() {
        return type;
    }

    public double getRate() {
        return rate;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void checkIn(ArrayList<Guest> guestList, int numberOfNights) {
        this.numberOfNights = numberOfNights;
        super.checkIn(guestList);
    }

    public double getTotal(){
        return this.rate * numberOfNights;
    }
}

