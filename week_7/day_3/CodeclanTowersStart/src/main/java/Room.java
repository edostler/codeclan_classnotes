import java.util.ArrayList;

public abstract class Room {

    private int capacity;
    private ArrayList<Guest> guests;

    public Room(int capacity) {
        this.capacity = capacity;
        this.guests = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public void checkIn(ArrayList<Guest> guestList) {
        if (guestList.size() <= this.capacity) {
            for (Guest guest : guestList) {
                this.guests.add(guest);
            }
        }
    }

    public void checkOut() {
        guests.clear();
    }

    public int getCheckedInGuestCount() {
        return this.guests.size();
    }

}
