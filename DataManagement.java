import java.util.ArrayList;
import java.util.Scanner;

class DataRecord {
    int id;
    String name;
    String email;
    String phone;

    DataRecord(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    void display() {
        System.out.println("-----------------------------");
        System.out.println("ID    : " + id);
        System.out.println("Name  : " + name);
        System.out.println("Email : " + email);
        System.out.println("Phone : " + phone);
        System.out.println("-----------------------------");
    }
}

public class DataManagement {

    static ArrayList<DataRecord> dataList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int nextId = 1;

    // Add Data
    static void addData() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        DataRecord record = new DataRecord(
                nextId++,
                name,
                email,
                phone
        );

        dataList.add(record);

        System.out.println("Data added successfully!");
        System.out.println("Record ID: " + record.id);
    }

    // View Data
    static void viewData() {
        if (dataList.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        System.out.println("\n===== ALL DATA =====");

        for (DataRecord record : dataList) {
            record.display();
        }
    }

    // Search Data
    static void searchData() {
        System.out.print("Enter record ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (DataRecord record : dataList) {
            if (record.id == id) {
                record.display();
                return;
            }
        }

        System.out.println("Record not found.");
    }

    // Update Data
    static void updateData() {
        System.out.print("Enter record ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (DataRecord record : dataList) {

            if (record.id == id) {

                System.out.print("Enter new name: ");
                record.name = sc.nextLine();

                System.out.print("Enter new email: ");
                record.email = sc.nextLine();

                System.out.print("Enter new phone number: ");
                record.phone = sc.nextLine();

                System.out.println("Data updated successfully!");
                return;
            }
        }

        System.out.println("Record not found.");
    }

    // Delete Data
    static void deleteData() {
        System.out.print("Enter record ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (DataRecord record : dataList) {

            if (record.id == id) {
                dataList.remove(record);
                System.out.println("Data deleted successfully!");
                return;
            }
        }

        System.out.println("Record not found.");
    }

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== DATA MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Data");
            System.out.println("2. View Data");
            System.out.println("3. Search Data");
            System.out.println("4. Update Data");
            System.out.println("5. Delete Data");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addData();
                    break;

                case 2:
                    viewData();
                    break;

                case 3:
                    searchData();
                    break;

                case 4:
                    updateData();
                    break;

                case 5:
                    deleteData();
                    break;

                case 6:
                    System.out.println("Thank you for using Data Management System!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 6);

        sc.close();
    }
}
