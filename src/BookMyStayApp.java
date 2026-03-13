import java.util.*;
class Reservation {
    private String guestName;
    private String roomType;
    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
    public String getGuestName() {
        return guestName;
    }
    public String getRoomType() {
        return roomType;
    }
}
class InventoryService {
    private HashMap<String, Integer> inventory;
    public InventoryService() {
        inventory = new HashMap<>();
        inventory.put("Single", 2);
        inventory.put("Double", 1);
        inventory.put("Suite", 1);
    }
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
    public void decrementRoom(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }
}
class BookingService {
    private Queue<Reservation> bookingQueue;
    private InventoryService inventory;
    private HashMap<String, Set<String>> allocatedRooms;
    private HashMap<String, Integer> roomCounters;
    public BookingService(InventoryService inventory) {
        this.inventory = inventory;
        bookingQueue = new LinkedList<>();
        allocatedRooms = new HashMap<>();
        roomCounters = new HashMap<>();
        allocatedRooms.put("Single", new HashSet<>());
        allocatedRooms.put("Double", new HashSet<>());
        allocatedRooms.put("Suite", new HashSet<>());
        roomCounters.put("Single", 1);
        roomCounters.put("Double", 1);
        roomCounters.put("Suite", 1);
    }
    public void addRequest(Reservation r) {
        bookingQueue.offer(r);
    }
    public void processBookings() {
        System.out.println("Room Allocation Processing:");
        while (!bookingQueue.isEmpty()) {
            Reservation r = bookingQueue.poll();
            String type = r.getRoomType();
            if (inventory.getAvailability(type) > 0) {
                int idNumber = roomCounters.get(type);
                String roomId = type + "-" + idNumber;
                Set<String> rooms = allocatedRooms.get(type);
                rooms.add(roomId);
                roomCounters.put(type, idNumber + 1);
                inventory.decrementRoom(type);
                System.out.println("Booking confirmed for Guest: "
                        + r.getGuestName()
                        + ", Room ID: "
                        + roomId);
            }
        }
    }
}
public class BookMyStayApp {
    public static void main(String[] args) {
        InventoryService inventory = new InventoryService();
        BookingService bookingService = new BookingService(inventory);
        bookingService.addRequest(new Reservation("Abhi", "Single"));
        bookingService.addRequest(new Reservation("Subha", "Single"));
        bookingService.addRequest(new Reservation("Vanmathi", "Suite"));
        bookingService.processBookings();
    }
}