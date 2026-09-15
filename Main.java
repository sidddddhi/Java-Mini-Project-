import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
// MODULE 1 - ADMIN MANAGEMENT
class Admin {

    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean login(String username, String password) {
        return this.username.equals(username)
                && this.password.equals(password);
    }

    public boolean changePassword(String oldPassword,
                                   String newPassword) {

        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
            return true;
        }

        return false;
    }
}
// MODULE 2 - VEHICLE MANAGEMENT
abstract class Vehicle {

    private String vehicleNumber;
    private String ownerName;

    public Vehicle(String vehicleNumber, String ownerName) {
        this.vehicleNumber = vehicleNumber;
        this.ownerName = ownerName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public abstract String getVehicleType();

    public void displayDetails() {

        System.out.println("Vehicle Number : " + vehicleNumber);
        System.out.println("Owner Name     : " + ownerName);
        System.out.println("Vehicle Type   : " + getVehicleType());
    }
}
// CAR
class Car extends Vehicle {

    public Car(String vehicleNumber, String ownerName) {
        super(vehicleNumber, ownerName);
    }

    @Override
    public String getVehicleType() {
        return "Car";
    }
}
// BIKE
class Bike extends Vehicle {

    public Bike(String vehicleNumber, String ownerName) {
        super(vehicleNumber, ownerName);
    }

    @Override
    public String getVehicleType() {
        return "Bike";
    }
}
// BUS
class Bus extends Vehicle {

    public Bus(String vehicleNumber, String ownerName) {
        super(vehicleNumber, ownerName);
    }

    @Override
    public String getVehicleType() {
        return "Bus";
    }
}
// TRUCK
class Truck extends Vehicle {

    public Truck(String vehicleNumber, String ownerName) {
        super(vehicleNumber, ownerName);
    }

    @Override
    public String getVehicleType() {
        return "Truck";
    }
}
// VEHICLE MANAGER
class VehicleManager {

    private HashMap<String, Vehicle> vehicles =
            new HashMap<>();

    private final String FILE_NAME = "vehicles.txt";
    // Constructor
    public VehicleManager() {
        loadVehicles();
    }
    // REGISTER VEHICLE
    public void registerVehicle(Scanner scanner) {

        System.out.println("\n======================================");
        System.out.println("          REGISTER VEHICLE");
        System.out.println("======================================");

        System.out.print("Enter Vehicle Number: ");

        String vehicleNumber =
                scanner.nextLine().trim().toUpperCase();

        if (vehicleNumber.isEmpty()) {

            System.out.println("Vehicle number cannot be empty.");
            return;
        }

        if (vehicles.containsKey(vehicleNumber)) {

            System.out.println("Vehicle already exists!");
            return;
        }

        System.out.print("Enter Owner Name: ");

        String ownerName =
                scanner.nextLine().trim();

        if (ownerName.isEmpty()) {

            System.out.println("Owner name cannot be empty.");
            return;
        }

        System.out.println("\nSelect Vehicle Type:");

        System.out.println("1. Car");
        System.out.println("2. Bike");
        System.out.println("3. Bus");
        System.out.println("4. Truck");

        System.out.print("Enter choice: ");

        String choice = scanner.nextLine();

        Vehicle vehicle;

        switch (choice) {

            case "1":
                vehicle =
                        new Car(vehicleNumber, ownerName);
                break;

            case "2":
                vehicle =
                        new Bike(vehicleNumber, ownerName);
                break;

            case "3":
                vehicle =
                        new Bus(vehicleNumber, ownerName);
                break;

            case "4":
                vehicle =
                        new Truck(vehicleNumber, ownerName);
                break;

            default:

                System.out.println("Invalid vehicle type.");
                return;
        }

        vehicles.put(vehicleNumber, vehicle);

        saveVehicles();

        System.out.println(
                "\nVehicle registered successfully!"
        );
    }
    // SEARCH VEHICLE
    public void searchVehicle(Scanner scanner) {

        System.out.println("\n======================================");
        System.out.println("           SEARCH VEHICLE");
        System.out.println("======================================");

        System.out.print("Enter Vehicle Number: ");

        String vehicleNumber =
                scanner.nextLine().trim().toUpperCase();

        Vehicle vehicle =
                vehicles.get(vehicleNumber);

        if (vehicle == null) {

            System.out.println("\nVehicle not found.");
            return;
        }

        System.out.println("\nVehicle Found!");
        System.out.println("--------------------------------------");

        vehicle.displayDetails();

        System.out.println("--------------------------------------");
    }
    // UPDATE VEHICLE
    public void updateVehicle(Scanner scanner) {

        System.out.println("\n======================================");
        System.out.println("           UPDATE VEHICLE");
        System.out.println("======================================");

        System.out.print("Enter Vehicle Number: ");

        String vehicleNumber =
                scanner.nextLine().trim().toUpperCase();

        Vehicle vehicle =
                vehicles.get(vehicleNumber);

        if (vehicle == null) {

            System.out.println("\nVehicle not found.");
            return;
        }

        System.out.println("\nCurrent Details:");

        vehicle.displayDetails();

        System.out.print("\nEnter New Owner Name: ");

        String newOwnerName =
                scanner.nextLine().trim();

        if (newOwnerName.isEmpty()) {

            System.out.println(
                    "Owner name cannot be empty."
            );

            return;
        }

        vehicle.setOwnerName(newOwnerName);

        saveVehicles();

        System.out.println(
                "\nVehicle updated successfully!"
        );
    }
    // REMOVE VEHICLE
    public void removeVehicle(Scanner scanner) {

        System.out.println("\n======================================");
        System.out.println("           REMOVE VEHICLE");
        System.out.println("======================================");

        System.out.print("Enter Vehicle Number: ");

        String vehicleNumber =
                scanner.nextLine().trim().toUpperCase();

        if (!vehicles.containsKey(vehicleNumber)) {

            System.out.println("\nVehicle not found.");
            return;
        }

        vehicles.remove(vehicleNumber);

        saveVehicles();

        System.out.println(
                "\nVehicle removed successfully!"
        );
    }
    // VIEW ALL VEHICLE
    public void viewAllVehicles() {

        System.out.println("\n======================================");
        System.out.println("            ALL VEHICLES");
        System.out.println("======================================");

        if (vehicles.isEmpty()) {

            System.out.println("No vehicles registered.");
            return;
        }

        int count = 1;

        for (Map.Entry<String, Vehicle> entry :
                vehicles.entrySet()) {

            Vehicle vehicle = entry.getValue();

            System.out.println("\nVehicle " + count);

            System.out.println("--------------------------------------");

            vehicle.displayDetails();

            count++;
        }

        System.out.println("--------------------------------------");
        System.out.println(
                "Total Vehicles: " + vehicles.size()
        );
    }
    // SAVE VEHICLES
    private void saveVehicles() {

        try {

            FileWriter writer =
                    new FileWriter(FILE_NAME);

            for (Vehicle vehicle : vehicles.values()) {

                writer.write(
                        vehicle.getVehicleNumber()
                        + "|"
                        + vehicle.getOwnerName()
                        + "|"
                        + vehicle.getVehicleType()
                        + "\n"
                );
            }

            writer.close();

        } catch (IOException e) {

            System.out.println(
                    "Error while saving vehicle data."
            );
        }
    }
    // LOAD VEHICLES
    private void loadVehicles() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(FILE_NAME)
                    );

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data =
                        line.split("\\|");

                if (data.length != 3) {
                    continue;
                }

                String vehicleNumber = data[0];
                String ownerName = data[1];
                String vehicleType = data[2];

                Vehicle vehicle = null;

                switch (vehicleType) {

                    case "Car":

                        vehicle =
                                new Car(
                                        vehicleNumber,
                                        ownerName
                                );

                        break;

                    case "Bike":

                        vehicle =
                                new Bike(
                                        vehicleNumber,
                                        ownerName
                                );

                        break;

                    case "Bus":

                        vehicle =
                                new Bus(
                                        vehicleNumber,
                                        ownerName
                                );

                        break;

                    case "Truck":

                        vehicle =
                                new Truck(
                                        vehicleNumber,
                                        ownerName
                                );

                        break;
                }

                if (vehicle != null) {

                    vehicles.put(
                            vehicleNumber,
                            vehicle
                    );
                }
            }

            reader.close();

        } catch (IOException e) {

            System.out.println(
                    "Error while loading vehicle data."
            );
        }
    }
}
// MAIN SYSTEM
public class Main {

    static Scanner scanner =
            new Scanner(System.in);

    static Admin admin;

    static VehicleManager vehicleManager =
            new VehicleManager();
    // MAIN METHOD
    public static void main(String[] args) {

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "       SMART PARKING MANAGEMENT SYSTEM"
        );

        System.out.println(
                "=============================================="
        );


        // First create admin account
        createAdminAccount();


        boolean running = true;

        while (running) {

            // Login
            if (login()) {

                adminDashboard();
            }

            System.out.print(
                    "\nDo you want to continue? (yes/no): "
            );

            String answer =
                    scanner.nextLine();

            if (answer.equalsIgnoreCase("no")) {

                running = false;
            }
        }

        System.out.println(
                "\nThank you for using Smart Parking System!"
        );

        scanner.close();
    }
    // CREATE ADMIN ACCOUNT
    public static void createAdminAccount() {

        System.out.println(
                "\n----------- CREATE ADMIN ACCOUNT -----------"
        );

        String username;
        String password;

        while (true) {

            System.out.print("Enter Username: ");

            username =
                    scanner.nextLine().trim();

            if (username.isEmpty()) {

                System.out.println(
                        "Username cannot be empty."
                );

                continue;
            }

            System.out.print("Enter Password: ");

            password =
                    scanner.nextLine();

            if (password.isEmpty()) {

                System.out.println(
                        "Password cannot be empty."
                );

                continue;
            }

            System.out.print(
                    "Confirm Password: "
            );

            String confirmPassword =
                    scanner.nextLine();

            if (!password.equals(confirmPassword)) {

                System.out.println(
                        "Passwords do not match."
                );

                continue;
            }

            admin =
                    new Admin(
                            username,
                            password
                    );

            System.out.println(
                    "\nAdmin account created successfully!"
            );

            break;
        }
    }
    // LOGIN
    public static boolean login() {

        System.out.println(
                "\n----------- ADMIN LOGIN -----------"
        );

        System.out.print("Enter Username: ");

        String username =
                scanner.nextLine();

        System.out.print("Enter Password: ");

        String password =
                scanner.nextLine();

        if (admin.login(username, password)) {

            System.out.println(
                    "\nLogin Successful!"
            );

            System.out.println(
                    "Welcome, "
                    + admin.getUsername()
                    + "!"
            );

            return true;
        }

        System.out.println(
                "\nInvalid username or password."
        );

        return false;
    }
    // ADMIN DASHBOARD
    public static void adminDashboard() {

        boolean loggedIn = true;

        while (loggedIn) {

            System.out.println(
                    "\n=============================================="
            );

            System.out.println(
                    "              ADMIN DASHBOARD"
            );

            System.out.println(
                    "=============================================="
            );

            System.out.println(
                    "1. Change Password"
            );

            System.out.println(
                    "2. Vehicle Management"
            );

            System.out.println(
                    "3. Logout"
            );

            System.out.println(
                    "=============================================="
            );

            System.out.print(
                    "Enter your choice: "
            );

            String choice =
                    scanner.nextLine();


            switch (choice) {

                case "1":

                    changePassword();

                    break;

                case "2":

                    vehicleManagement();

                    break;

                case "3":

                    System.out.println(
                            "\nLogged out successfully."
                    );

                    loggedIn = false;

                    break;

                default:

                    System.out.println(
                            "\nInvalid choice."
                    );
            }
        }
    }
    // CHANGE PASSWORD
    public static void changePassword() {

        System.out.println(
                "\n----------- CHANGE PASSWORD -----------"
        );

        System.out.print(
                "Enter Old Password: "
        );

        String oldPassword =
                scanner.nextLine();

        System.out.print(
                "Enter New Password: "
        );

        String newPassword =
                scanner.nextLine();

        System.out.print(
                "Confirm New Password: "
        );

        String confirmPassword =
                scanner.nextLine();

        if (!newPassword.equals(confirmPassword)) {

            System.out.println(
                    "\nPasswords do not match."
            );

            return;
        }

        if (admin.changePassword(
                oldPassword,
                newPassword)) {

            System.out.println(
                    "\nPassword changed successfully!"
            );

        } else {

            System.out.println(
                    "\nIncorrect old password."
            );
        }
    }
    // VEHICLE MANAGEMENT
    public static void vehicleManagement() {

        boolean running = true;

        while (running) {

            System.out.println(
                    "\n=============================================="
            );

            System.out.println(
                    "             VEHICLE MANAGEMENT"
            );

            System.out.println(
                    "=============================================="
            );

            System.out.println(
                    "1. Register Vehicle"
            );

            System.out.println(
                    "2. Search Vehicle"
            );

            System.out.println(
                    "3. Update Vehicle"
            );

            System.out.println(
                    "4. Remove Vehicle"
            );

            System.out.println(
                    "5. View All Vehicles"
            );

            System.out.println(
                    "6. Back to Admin Dashboard"
            );

            System.out.println(
                    "=============================================="
            );

            System.out.print(
                    "Enter your choice: "
            );

            String choice =
                    scanner.nextLine();


            switch (choice) {

                case "1":

                    vehicleManager.registerVehicle(
                            scanner
                    );

                    break;

                case "2":

                    vehicleManager.searchVehicle(
                            scanner
                    );

                    break;

                case "3":

                    vehicleManager.updateVehicle(
                            scanner
                    );

                    break;

                case "4":

                    vehicleManager.removeVehicle(
                            scanner
                    );

                    break;

                case "5":

                    vehicleManager.viewAllVehicles();

                    break;

                case "6":

                    running = false;

                    break;

                default:

                    System.out.println(
                            "\nInvalid choice."
                    );
            }
        }
    }
}
