import java.util.concurrent.locks.ReentrantLock;

class TicketBooking {
    private static int availableSeats = 10;  // Total number of available seats
    private static final ReentrantLock lock = new ReentrantLock();  // Lock for synchronized access

    // Method to book a seat
    public void bookTicket(String customerName, boolean isVIP) {
        lock.lock();  // Acquire lock to ensure synchronized access to availableSeats
        try {
            if (availableSeats > 0) {
                // Simulate VIP booking having priority
                if (isVIP) {
                    System.out.println(customerName + " (VIP) is booking a seat.");
                } else {
                    System.out.println(customerName + " is booking a seat.");
                }
                // Simulate booking processing time
                try {
                    Thread.sleep(500);  // Simulate time taken for processing the booking
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                // Book the seat
                availableSeats--;
                System.out.println("Booking successful for " + customerName + ". Seats remaining: " + availableSeats);
            } else {
                System.out.println("No available seats for " + customerName);
            }
        } finally {
            lock.unlock();  // Release the lock
        }
    }

    // Method to get the number of available seats
    public int getAvailableSeats() {
        return availableSeats;
    }
}

class CustomerThread extends Thread {
    private TicketBooking ticketBooking;
    private String customerName;
    private boolean isVIP;

    public CustomerThread(TicketBooking ticketBooking, String customerName, boolean isVIP) {
        this.ticketBooking = ticketBooking;
        this.customerName = customerName;
        this.isVIP = isVIP;
    }

    @Override
    public void run() {
        ticketBooking.bookTicket(customerName, isVIP);
    }
}

public class TicketBookingSystem {

    public static void main(String[] args) {
        TicketBooking ticketBooking = new TicketBooking();

        // Creating threads for customers (VIP and regular)
        CustomerThread customer1 = new CustomerThread(ticketBooking, "Alice", true);  // VIP customer
        CustomerThread customer2 = new CustomerThread(ticketBooking, "Bob", false);   // Regular customer
        CustomerThread customer3 = new CustomerThread(ticketBooking, "Charlie", false);  // Regular customer
        CustomerThread customer4 = new CustomerThread(ticketBooking, "David", true);   // VIP customer
        CustomerThread customer5 = new CustomerThread(ticketBooking, "Eve", false);   // Regular customer
        CustomerThread customer6 = new CustomerThread(ticketBooking, "Frank", true);  // VIP customer

        // Setting thread priorities to simulate VIP booking processing first
        customer1.setPriority(Thread.MAX_PRIORITY);  // VIP customer has high priority
        customer2.setPriority(Thread.NORM_PRIORITY); // Regular customer
        customer3.setPriority(Thread.NORM_PRIORITY); // Regular customer
        customer4.setPriority(Thread.MAX_PRIORITY);  // VIP customer
        customer5.setPriority(Thread.NORM_PRIORITY); // Regular customer
        customer6.setPriority(Thread.MAX_PRIORITY);  // VIP customer

        // Starting the threads
        customer1.start();
        customer2.start();
        customer3.start();
        customer4.start();
        customer5.start();
        customer6.start();

        try {
            // Wait for all threads to finish
            customer1.join();
            customer2.join();
            customer3.join();
            customer4.join();
            customer5.join();
            customer6.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nAll booking attempts completed. Remaining seats: " + ticketBooking.getAvailableSeats());
    }
}
