import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class HotelReservationSystem {
    public static void main(String[] args) {
        // Create hotel and rooms
        Hotel hotel = new Hotel();
        Room room1 = new Room(101, "Single");
        Room room2 = new Room(102, "Double");
        Room room3 = new Room(103, "Suite");
        hotel.addRoom(room1);
        hotel.addRoom(room2);
        hotel.addRoom(room3);

        // Initialize scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Main menu
        int choice;
        do {
            System.out.println("\nHotel Reservation System Menu:");
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                switch (choice) {
                    case 1:
                        // Search for available rooms
                        System.out.print("\nEnter check-in date (YYYY-MM-DD): ");
                        String startDate = scanner.nextLine();
                        System.out.print("Enter check-out date (YYYY-MM-DD): ");
                        String endDate = scanner.nextLine();
                        System.out.print("Enter room type (Single/Double/Suite): ");
                        String roomType = scanner.nextLine();
                        List<Room> availableRooms = hotel.searchAvailableRooms(startDate, endDate, roomType);
                        if (!availableRooms.isEmpty()) {
                            System.out.println("\nAvailable " + roomType + " Rooms:");
                            for (Room room : availableRooms) {
                                System.out.println("Room Number: " + room.getRoomNumber());
                                System.out.println("Room Type: " + room.getType());
                            }
                        } else {
                            System.out.println("\nNo available rooms matching the criteria.");
                        }
                        break;
                    case 2:
                        // Make reservation
                        System.out.print("\nEnter your name: ");
                        String customerName = scanner.nextLine();
                        System.out.print("Enter room number to reserve: ");
                        int roomNumber = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        Room selectedRoom = null;
                        for (Room room : hotel.getRooms()) {
                            if (room.getRoomNumber() == roomNumber) {
                                selectedRoom = room;
                                break;
                            }
                        }
                        if (selectedRoom != null && selectedRoom.isAvailable()) {
                            System.out.print("Enter check-in date (YYYY-MM-DD): ");
                            startDate = scanner.nextLine();
                            System.out.print("Enter check-out date (YYYY-MM-DD): ");
                            endDate = scanner.nextLine();
                            Reservation reservation = hotel.makeReservation(customerName, selectedRoom, startDate, endDate);
                            System.out.println("\nReservation successful. Reservation ID: " + reservation.getReservationId());
                        } else {
                            System.out.println("\nRoom not available or does not exist.");
                        }
                        break;
                    case 3:
                        // View booking details
                        System.out.print("\nEnter reservation ID: ");
                        int reservationId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        Reservation reservationDetails = hotel.findReservationById(reservationId);
                        if (reservationDetails != null) {
                            System.out.println("\nBooking Details:");
                            System.out.println("Reservation ID: " + reservationDetails.getReservationId());
                            System.out.println("Customer Name: " + reservationDetails.getCustomerName());
                            System.out.println("Room Number: " + reservationDetails.getRoom().getRoomNumber());
                            System.out.println("Room Type: " + reservationDetails.getRoom().getType());
                            System.out.println("Check-in Date: " + reservationDetails.getStartDate());
                            System.out.println("Check-out Date: " + reservationDetails.getEndDate());
                            System.out.println("Total Amount: " + reservationDetails.calculateTotalAmount() + " USD");
                        } else {
                            System.out.println("\nReservation not found.");
                        }
                        break;
                    case 4:
                        System.out.println("\nThank you for using the Hotel Reservation System.");
                        break;
                    default:
                        System.out.println("\nInvalid choice. Please enter a number from 1 to 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter a number.");
                scanner.nextLine(); // Consume newline
                choice = 0; // Reset choice to continue loop
            }
        } while (choice != 4);

        // Close scanner
        scanner.close();
    }
}