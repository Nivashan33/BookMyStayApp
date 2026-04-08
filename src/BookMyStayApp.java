import java.util.*;
public class BookMyStayApp {
    public static void main(String[] args) {
        ConcurrentBookingProcessor processor = new ConcurrentBookingProcessor();
        Thread t1 = new Thread(() -> processor.processBooking("RES401", "Alice", "DELUXE"));
        Thread t2 = new Thread(() -> processor.processBooking("RES402", "Bob", "DELUXE"));
        Thread t3 = new Thread(() -> processor.processBooking("RES403", "Charlie", "DELUXE"));
        Thread t4 = new Thread(() -> processor.processBooking("RES404", "David", "DELUXE"));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
class ConcurrentBookingProcessor {
    private Map<String, Integer> inventory = new HashMap<>();
    private Queue<BookingRequest> bookingQueue = new LinkedList<>();
    public ConcurrentBookingProcessor() {
        inventory.put("DELUXE", 2);
    }
    public void processBooking(String reservationId, String guestName, String roomType) {
        BookingRequest request = new BookingRequest(reservationId, guestName, roomType);
        synchronized (bookingQueue) {
            bookingQueue.add(request);
        }
        handleRequest();
    }
    private void handleRequest() {
        BookingRequest request;
        synchronized (bookingQueue) {
            if (bookingQueue.isEmpty()) return;
            request = bookingQueue.poll();
        }
        synchronized (inventory) {
            int available = inventory.getOrDefault(request.roomType, 0);
            if (available > 0) {
                inventory.put(request.roomType, available - 1);
                System.out.println(Thread.currentThread().getName() +
                        " SUCCESS: " + request.reservationId +
                        " | Remaining: " + (available - 1));
            } else {
                System.out.println(Thread.currentThread().getName() +
                        " FAILED: No rooms available for " + request.reservationId);
            }
        }
    }
}
class BookingRequest {
    String reservationId;
    String guestName;
    String roomType;
    public BookingRequest(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }
}
