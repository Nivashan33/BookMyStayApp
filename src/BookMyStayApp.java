import java.util.*;
public class BookMyStayApp {
    public static void main(String[] args) {
        BookingService bookingService = new BookingService();
        try {
            bookingService.bookRoom("RES201", "Alice", "DELUXE", 2);
            bookingService.bookRoom("RES202", "Bob", "PENTHOUSE", 1);
            bookingService.bookRoom("RES203", "Charlie", "STANDARD", 10);
        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }
        System.out.println("\nSystem still running...");
    }
}
class BookingService {
    private Map<String, Integer> inventory = new HashMap<>();
    public BookingService() {
        inventory.put("STANDARD", 5);
        inventory.put("DELUXE", 3);
        inventory.put("SUITE", 2);
    }
    public void bookRoom(String reservationId, String guestName,
                         String roomType, int quantity)
            throws InvalidBookingException {
        validateRoomType(roomType);
        validateQuantity(quantity);
        validateAvailability(roomType, quantity);
        inventory.put(roomType, inventory.get(roomType) - quantity);
        System.out.println("Booking Confirmed: " + reservationId +
                " | Guest: " + guestName +
                " | Room: " + roomType +
                " | Qty: " + quantity);
    }
    private void validateRoomType(String roomType) throws InvalidBookingException {
        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }
    }
    private void validateQuantity(int quantity) throws InvalidBookingException {
        if (quantity <= 0) {
            throw new InvalidBookingException("Quantity must be greater than zero.");
        }
    }
    private void validateAvailability(String roomType, int quantity)
            throws InvalidBookingException {
        int available = inventory.get(roomType);
        if (available < quantity) {
            throw new InvalidBookingException(
                    "Not enough rooms available. Requested: " + quantity +
                            ", Available: " + available
            );
        }
    }
}
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}
