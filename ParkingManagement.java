import java.util.Scanner;

class ParkingSlot {
    int slotNumber;
    boolean occupied;
    String vehicleNumber;

    ParkingSlot(int slotNumber) {
        this.slotNumber = slotNumber;
        this.occupied = false;
        this.vehicleNumber = "";
    }
}

public class ParkingManagement {

    static final int TOTAL_SLOTS = 10;
    static ParkingSlot[] slots = new ParkingSlot[TOTAL_SLOTS];

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Initialize parking slots
        for (int i = 0; i < TOTAL_SLOTS; i++) {
            slots[i] = new ParkingSlot(i + 1);
        }

        while (true) {

            System.out.println("\n===== PARKING SLOT MANAGEMENT =====");
            System.out.println("1. Park Vehicle");
            System.out.println("2. Remove Vehicle");
            System.out.println("3. Display Parking Slots");
            System.out.println("4. Check Available Slots");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter slot number (1-" + TOTAL_SLOTS + "): ");
                    int slot = sc.nextInt();

                    if (slot < 1 || slot > TOTAL_SLOTS) {
                        System.out.println("Invalid slot number!");
                    }
                    else if (slots[slot - 1].occupied) {
                        System.out.println("Slot " + slot + " is already occupied!");
                    }
                    else {
                        System.out.print("Enter vehicle number: ");
                        String vehicle = sc.next();

                        slots[slot - 1].occupied = true;
                        slots[slot - 1].vehicleNumber = vehicle;

                        System.out.println(
                            "Vehicle parked successfully in slot " + slot
                        );
                    }
                    break;

                case 2:
                    System.out.print("Enter slot number to remove vehicle: ");
                    int removeSlot = sc.nextInt();

                    if (removeSlot < 1 || removeSlot > TOTAL_SLOTS) {
                        System.out.println("Invalid slot number!");
                    }
                    else if (!slots[removeSlot - 1].occupied) {
                        System.out.println("Slot " + removeSlot + " is already empty!");
                    }
                    else {
                        System.out.println(
                            "Vehicle " + slots[removeSlot - 1].vehicleNumber +
                            " removed from slot " + removeSlot
                        );

                        slots[removeSlot - 1].occupied = false;
                        slots[removeSlot - 1].vehicleNumber = "";
                    }
                    break;

                case 3:
                    System.out.println("\n===== PARKING STATUS =====");

                    for (int i = 0; i < TOTAL_SLOTS; i++) {

                        if (slots[i].occupied) {
                            System.out.println(
                                "Slot " + slots[i].slotNumber +
                                " : Occupied - " +
                                slots[i].vehicleNumber
                            );
                        }
                        else {
                            System.out.println(
                                "Slot " + slots[i].slotNumber +
                                " : Available"
                            );
                        }
                    }
                    break;

                case 4:
                    System.out.println("\n===== AVAILABLE SLOTS =====");

                    boolean available = false;

                    for (int i = 0; i < TOTAL_SLOTS; i++) {
                        if (!slots[i].occupied) {
                            System.out.print(slots[i].slotNumber + " ");
                            available = true;
                        }
                    }

                    if (!available) {
                        System.out.println("No slots available!");
                    }
                    else {
                        System.out.println();
                    }
                    break;

                case 5:
                    System.out.println("Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
