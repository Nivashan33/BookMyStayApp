import java.util.Queue;
import java.util.LinkedList;
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
class BookingRequestQueue {
    private Queue<Reservation> queue;
    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }
    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
    }
    public void processRequests() {
        System.out.println("Booking Request Queue");
        while (!queue.isEmpty()) {
            Reservation r = queue.poll();
            System.out.println(
                    "Processing booking for Guest: "
                            + r.getGuestName()
                            + ", Room Type: "
                            + r.getRoomType()
            );
        }
    }
}
public class BookMyStayApp {
    public static void main(String[] args) {
        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        bookingQueue.addRequest(new Reservation("Abhi", "Single"));
        bookingQueue.addRequest(new Reservation("Subha", "Double"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Suite"));
        bookingQueue.processRequests();
    }
}