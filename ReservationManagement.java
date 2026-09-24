import java.util.ArrayList;
import java.util.Scanner;

class Reservation {
    int id;
    String name;
    String date;
    String time;
    int guests;

    Reservation(int id, String name, String date, String time, int guests) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.guests = guests;
    }

    void display() {
        System.out.println("Reservation ID : " + id);
        System.out.println("Name           : " + name);
        System.out.println("Date           : " + date);
        System.out.println("Time           : " + time);
        System.out.println("Guests         : " + guests);
        System.out.println("-----------------------------");
    }
}

public class ReservationManagement {

    static ArrayList<Reservation> reservations = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int nextId = 1;

    // Add Reservation
    static void addReservation() {
        System.out.print("Enter customer name: ");
        String name = sc.nextLine();

        System.out.print("Enter reservation date (DD-MM-YYYY): ");
        String date = sc.nextLine();

        System.out.print("Enter reservation time: ");
        String time = sc.nextLine();

        System.out.print("Enter number of guests: ");
        int guests = sc.nextInt();
        sc.nextLine();

        Reservation r = new Reservation(nextId++, name, date, time, guests);
        reservations.add(r);

        System.out.println("Reservation added successfully!");
        System.out.println("Your Reservation ID is: " + r.id);
    }

    // View Reservations
    static void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }

        System.out.println("\n===== ALL RESERVATIONS =====");

        for (Reservation r : reservations) {
            r.display();
        }
    }

    // Search Reservation
    static void searchReservation() {
        System.out.print("Enter Reservation ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Reservation r : reservations) {
            if (r.id == id) {
                r.display();
                return;
            }
        }

        System.out.println("Reservation not found.");
    }

    // Update Reservation
    static void updateReservation() {
        System.out.print("Enter Reservation ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Reservation r : reservations) {
            if (r.id == id) {
                System.out.print("Enter new name: ");
                r.name = sc.nextLine();

                System.out.print("Enter new date: ");
                r.date = sc.nextLine();

                System.out.print("Enter new time: ");
                r.time = sc.nextLine();

                System.out.print("Enter new number of guests: ");
                r.guests = sc.nextInt();
                sc.nextLine();

                System.out.println("Reservation updated successfully!");
                return;
            }
        }

        System.out.println("Reservation not found.");
    }

    // Cancel Reservation
    static void cancelReservation() {
        System.out.print("Enter Reservation ID to cancel: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Reservation r : reservations) {
            if (r.id == id) {
                reservations.remove(r);
                System.out.println("Reservation cancelled successfully!");
                return;
            }
        }

        System.out.println("Reservation not found.");
    }

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== RESERVATION MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Reservation");
            System.out.println("2. View Reservations");
            System.out.println("3. Search Reservation");
            System.out.println("4. Update Reservation");
            System.out.println("5. Cancel Reservation");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addReservation();
                    break;

                case 2:
                    viewReservations();
                    break;

                case 3:
                    searchReservation();
                    break;

                case 4:
                    updateReservation();
                    break;

                case 5:
                    cancelReservation();
                    break;

                case 6:
                    System.out.println("Thank you for using Reservation Management System!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 6);

        sc.close();
    }
}
