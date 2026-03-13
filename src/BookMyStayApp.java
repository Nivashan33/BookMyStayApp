import java.util.HashMap;
abstract class Room {
    private int beds;
    private int size;
    private double price;
    public Room(int beds, int size, double price) {
        this.beds = beds;
        this.size = size;
        this.price = price;
    }
    public int getBeds() {
        return beds;
    }
    public int getSize() {
        return size;
    }
    public double getPrice() {
        return price;
    }
}
class SingleRoom extends Room {
    public SingleRoom() {
        super(1, 250, 1500.0);
    }
}
class DoubleRoom extends Room {
    public DoubleRoom() {
        super(2, 400, 2500.0);
    }
}
class SuiteRoom extends Room {
    public SuiteRoom() {
        super(3, 750, 5000.0);
    }
}
class RoomInventory {
    private HashMap<String, Integer> inventory;
    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
    public void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }
    public void displayInventory(Room single, Room dbl, Room suite) {
        System.out.println("Hotel Room Inventory Status\n");
        System.out.println("Single Room:");
        System.out.println("Beds: " + single.getBeds());
        System.out.println("Size: " + single.getSize() + " sqft");
        System.out.println("Price per night: " + single.getPrice());
        System.out.println("Available Rooms: " + getAvailability("Single"));
        System.out.println();
        System.out.println("Double Room:");
        System.out.println("Beds: " + dbl.getBeds());
        System.out.println("Size: " + dbl.getSize() + " sqft");
        System.out.println("Price per night: " + dbl.getPrice());
        System.out.println("Available Rooms: " + getAvailability("Double"));
        System.out.println();
        System.out.println("Suite Room:");
        System.out.println("Beds: " + suite.getBeds());
        System.out.println("Size: " + suite.getSize() + " sqft");
        System.out.println("Price per night: " + suite.getPrice());
        System.out.println("Available Rooms: " + getAvailability("Suite"));
    }
}
public class BookMyStayApp {
    public static void main(String[] args) {
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();
        RoomInventory inventory = new RoomInventory();
        inventory.displayInventory(single, doubleRoom, suite);
    }
}