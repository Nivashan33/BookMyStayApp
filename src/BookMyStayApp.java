abstract class Room {
    private String roomType;
    private int beds;
    private int size;
    private double price;
    public Room(String roomType, int beds, int size, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }
    public void displayRoomDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sq.ft");
        System.out.println("Price per night: ₹" + price);
    }
}
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 200, 2500);
    }
}
class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 350, 4000);
    }
}
class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 600, 8000);
    }
}
public class BookMyStayApp {
    public static void main(String[] args) {
        int singleRoomAvailable = 5;
        int doubleRoomAvailable = 3;
        int suiteRoomAvailable = 2;
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();
        System.out.println("===== HOTEL ROOM DETAILS =====\n");
        System.out.println("Single Room Information:");
        single.displayRoomDetails();
        System.out.println("Available: " + singleRoomAvailable + "\n");
        System.out.println("Double Room Information:");
        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + doubleRoomAvailable + "\n");
        System.out.println("Suite Room Information:");
        suite.displayRoomDetails();
        System.out.println("Available: " + suiteRoomAvailable + "\n");
        System.out.println("Application terminated.");
    }
}