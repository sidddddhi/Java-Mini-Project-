import java.util.ArrayList;
import java.util.Scanner;

class Report {
    int reportId;
    String title;
    String description;
    String date;
    String status;

    Report(int reportId, String title, String description, String date, String status) {
        this.reportId = reportId;
        this.title = title;
        this.description = description;
        this.date = date;
        this.status = status;
    }

    void display() {
        System.out.println("-----------------------------");
        System.out.println("Report ID    : " + reportId);
        System.out.println("Title        : " + title);
        System.out.println("Description  : " + description);
        System.out.println("Date         : " + date);
        System.out.println("Status       : " + status);
        System.out.println("-----------------------------");
    }
}

public class ReportManagement {

    static ArrayList<Report> reports = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int nextId = 1;

    // Add Report
    static void addReport() {
        System.out.print("Enter report title: ");
        String title = sc.nextLine();

        System.out.print("Enter report description: ");
        String description = sc.nextLine();

        System.out.print("Enter report date (DD-MM-YYYY): ");
        String date = sc.nextLine();

        System.out.print("Enter report status: ");
        String status = sc.nextLine();

        Report report = new Report(
                nextId++,
                title,
                description,
                date,
                status
        );

        reports.add(report);

        System.out.println("Report added successfully!");
        System.out.println("Report ID: " + report.reportId);
    }

    // View Reports
    static void viewReports() {
        if (reports.isEmpty()) {
            System.out.println("No reports available.");
            return;
        }

        System.out.println("\n===== ALL REPORTS =====");

        for (Report report : reports) {
            report.display();
        }
    }

    // Search Report
    static void searchReport() {
        System.out.print("Enter Report ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Report report : reports) {
            if (report.reportId == id) {
                report.display();
                return;
            }
        }

        System.out.println("Report not found.");
    }

    // Delete Report
    static void deleteReport() {
        System.out.print("Enter Report ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Report report : reports) {
            if (report.reportId == id) {
                reports.remove(report);
                System.out.println("Report deleted successfully!");
                return;
            }
        }

        System.out.println("Report not found.");
    }

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== REPORT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Report");
            System.out.println("2. View Reports");
            System.out.println("3. Search Report");
            System.out.println("4. Delete Report");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addReport();
                    break;

                case 2:
                    viewReports();
                    break;

                case 3:
                    searchReport();
                    break;

                case 4:
                    deleteReport();
                    break;

                case 5:
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}
