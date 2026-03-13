import java.util.*;
class Room {
    int roomNumber;
    String type;
    boolean isAvailable;

    Room(int roomNumber, String type) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.isAvailable = true;
    }
}
class Booking {
    String bookingId;
    String customerName;
    String roomType;

    Booking(String bookingId, String customerName, String roomType) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.roomType = roomType;
    }
}
class BookingService {
    Queue<Booking> bookingQueue = new LinkedList<>();
    HashMap<Integer, Room> rooms = new HashMap<>();
    HashSet<String> bookingIds = new HashSet<>();
    public BookingService() {
        initializeRooms();
    }
    void initializeRooms() {
        rooms.put(101, new Room(101, "Single"));
        rooms.put(102, new Room(102, "Single"));
        rooms.put(201, new Room(201, "Double"));
        rooms.put(202, new Room(202, "Double"));
    }
    void requestBooking(String bookingId, String name, String roomType) {
        if (bookingIds.contains(bookingId)) {
            System.out.println("Duplicate Booking ID not allowed!");
            return;
        }
        Booking booking = new Booking(bookingId, name, roomType);
        bookingQueue.add(booking);
        bookingIds.add(bookingId);
        System.out.println("Booking request added for " + name);
    }
    void processBookings() {
        while (!bookingQueue.isEmpty()) {
            Booking booking = bookingQueue.poll();
            boolean roomAllocated = false;
            for (Room room : rooms.values()) {
                if (room.type.equalsIgnoreCase(booking.roomType) && room.isAvailable) {
                    room.isAvailable = false;
                    System.out.println(
                            "Booking Confirmed!\nCustomer: " + booking.customerName +
                                    "\nRoom Number: " + room.roomNumber +
                                    "\nRoom Type: " + room.type +
                                    "\nBooking ID: " + booking.bookingId
                    );
                    roomAllocated = true;
                    break;
                }
            }
            if (!roomAllocated) {
                System.out.println("No rooms available for " + booking.customerName);
            }
            System.out.println("----------------------------------");
        }
    }
    void showAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms.values()) {
            if (room.isAvailable) {
                System.out.println("Room " + room.roomNumber + " - " + room.type);
            }
        }
    }
}
public class BookMyStayApp {
    public static void main(String[] args) {
        BookingService service = new BookingService();
        service.showAvailableRooms();
        System.out.println("\n--- Booking Requests ---");
        service.requestBooking("B001", "Rahul", "Single");
        service.requestBooking("B002", "Anita", "Double");
        service.requestBooking("B003", "Vikram", "Single");
        System.out.println("\n--- Processing Bookings ---");
        service.processBookings();
        System.out.println("\n--- Remaining Rooms ---");
        service.showAvailableRooms();
    }
}