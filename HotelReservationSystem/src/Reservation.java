public class Reservation {
    private int reservationId;
    private String customerName;
    private Room room;
    private String startDate;
    private String endDate;

    public Reservation(int reservationId, String customerName, Room room, String startDate, String endDate) {
        this.reservationId = reservationId;
        this.customerName = customerName;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getReservationId() {
        return reservationId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Room getRoom() {
        return room;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
    public double calculateTotalAmount() {
        // Dummy implementation: just a fixed amount for demonstration purposes
        // You should replace this with your own logic to calculate the total amount
        double roomRate = 100; // Assuming room rate is $100 per night
        int numOfNights = calculateNumberOfNights();
        return roomRate * numOfNights;
    }

    // Helper method to calculate the number of nights for the reservation
    private int calculateNumberOfNights() {
        // Dummy implementation: just returning a fixed value of 3 for demonstration purposes
        // You should replace this with your own logic to calculate the number of nights
        return 3;
}}
