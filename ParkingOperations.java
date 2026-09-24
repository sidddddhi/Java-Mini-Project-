import java.util.Scanner;

class Vehicle {
    String vehicleNumber;
    String vehicleType;
    int slotNumber;
    long entryTime;

    Vehicle(String vehicleNumber, String vehicleType, int slotNumber) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.slotNumber = slotNumber;
        this.entryTime = System.currentTimeMillis();
    }
}

public class ParkingOperations {

    static final int TOTAL_SLOTS = 10;
    static Vehicle[] parking = new Vehicle[TOTAL_SLOTS];

    static Scanner sc = new Scanner(System.in);

    // Vehicle Entry
    static void vehicleEntry() {
        System.out.print("Enter vehicle number: ");
        String number = sc.next();

        System.out.print("Enter vehicle type (Car/Bike): ");
        String type = sc.next();

        System.out.print("Enter parking slot (1-" + TOTAL_SLOTS + "): ");
        int slot = sc.nextInt();

        if (slot < 1 || slot > TOTAL_SLOTS) {
            System.out.println("Invalid slot number!");
            return;
        }

        if (parking[slot - 1] != null) {
            System.out.println("Slot is already occupied!");
            return;
        }

        parking[slot - 1] = new Vehicle(number, type, slot);

        System.out.println("Vehicle entered successfully!");
        System.out.println("Vehicle Number: " + number);
        System.out.println("Slot Number: " + slot);
    }

    // Vehicle Exit
    static void vehicleExit() {
        System.out.print("Enter vehicle number: ");
        String number = sc.next();

        for (int i = 0; i < TOTAL_SLOTS; i++) {

            if (parking[i] != null &&
                parking[i].vehicleNumber.equalsIgnoreCase(number)) {

                long exitTime = System.currentTimeMillis();

                long durationMillis =
                        exitTime - parking[i].entryTime;

                long hours =
                        (durationMillis / (1000 * 60 * 60));

                // Minimum parking time = 1 hour
                if (hours == 0) {
                    hours = 1;
                }

                double rate;

                if (parking[i].vehicleType.equalsIgnoreCase("Car")) {
                    rate = 50;
                } else {
                    rate = 20;
                }

                double fee = hours * rate;

                System.out.println("\n===== PARKING BILL =====");
                System.out.println("Vehicle Number : " +
                                   parking[i].vehicleNumber);
                System.out.println("Vehicle Type   : " +
                                   parking[i].vehicleType);
                System.out.println("Slot Number    : " +
                                   parking[i].slotNumber);
                System.out.println("Parking Hours  : " + hours);
                System.out.println("Total Fee      : Rs. " + fee);

                parking[i] = null;

                System.out.println("Vehicle exited successfully!");
                return;
            }
        }

        System.out.println("Vehicle not found!");
    }

    // Display parking status
    static void displayStatus() {

        System.out.println("\n===== PARKING STATUS =====");

        for (int i = 0; i < TOTAL_SLOTS; i++) {

            if (parking[i] == null) {
                System.out.println(
                    "Slot " + (i + 1) + " : Available"
                );
            } else {
                System.out.println(
                    "Slot " + (i + 1) +
                    " : Occupied - " +
                    parking[i].vehicleNumber +
                    " (" + parking[i].vehicleType + ")"
                );
            }
        }
    }

    // Count available slots
    static void availableSlots() {

        int count = 0;

        for (int i = 0; i < TOTAL_SLOTS; i++) {
            if (parking[i] == null) {
                count++;
            }
        }

        System.out.println(
            "Available Parking Slots: " + count
        );
    }

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== PARKING OPERATIONS =====");
            System.out.println("1. Vehicle Entry");
            System.out.println("2. Vehicle Exit");
            System.out.println("3. Display Parking Status");
            System.out.println("4. Check Available Slots");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    vehicleEntry();
                    break;

                case 2:
                    vehicleExit();
                    break;

                case 3:
                    displayStatus();
                    break;

                case 4:
                    availableSlots();
                    break;

                case 5:
                    System.out.println(
                        "Thank you for using Parking Operations!"
                    );
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
