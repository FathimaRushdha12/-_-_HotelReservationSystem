import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;
    private int nextReservationId;

    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        nextReservationId = 1;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Room> searchAvailableRooms(String startDate, String endDate, String type) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable() && room.getType().equalsIgnoreCase(type)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Reservation makeReservation(String customerName, Room room, String startDate, String endDate) {
        Reservation reservation = new Reservation(nextReservationId++, customerName, room, startDate, endDate);
        room.setAvailable(false);
        reservations.add(reservation);
        return reservation;
    }

    public Reservation findReservationById(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId() == reservationId) {
                return reservation;
            }
        }
        return null;
    }
}
