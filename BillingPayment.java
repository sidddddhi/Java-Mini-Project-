import java.util.Scanner;

public class BillingPayment {

    static Scanner sc = new Scanner(System.in);

    // Calculate parking fee
    static double calculateFee(String vehicleType, int hours) {

        double rate;

        if (vehicleType.equalsIgnoreCase("Car")) {
            rate = 50;
        } else if (vehicleType.equalsIgnoreCase("Bike")) {
            rate = 20;
        } else {
            rate = 30;
        }

        return rate * hours;
    }

    // Generate bill
    static void generateBill() {

        System.out.println("\n===== PARKING BILL =====");

        System.out.print("Enter vehicle number: ");
        String vehicleNumber = sc.next();

        System.out.print("Enter vehicle type (Car/Bike): ");
        String vehicleType = sc.next();

        System.out.print("Enter number of parking hours: ");
        int hours = sc.nextInt();

        if (hours <= 0) {
            System.out.println("Invalid number of hours!");
            return;
        }

        double parkingFee = calculateFee(vehicleType, hours);

        double gst = parkingFee * 0.18;
        double totalAmount = parkingFee + gst;

        System.out.println("\n-----------------------------");
        System.out.println("        PARKING BILL");
        System.out.println("-----------------------------");
        System.out.println("Vehicle Number : " + vehicleNumber);
        System.out.println("Vehicle Type   : " + vehicleType);
        System.out.println("Parking Hours  : " + hours);
        System.out.println("Parking Fee    : Rs. " + parkingFee);
        System.out.println("GST (18%)      : Rs. " + gst);
        System.out.println("-----------------------------");
        System.out.println("Total Amount   : Rs. " + totalAmount);
        System.out.println("-----------------------------");

        processPayment(totalAmount);
    }

    // Process payment
    static void processPayment(double totalAmount) {

        System.out.println("\n===== PAYMENT =====");
        System.out.println("1. Cash");
        System.out.println("2. UPI");
        System.out.println("3. Card");

        System.out.print("Select payment method: ");
        int paymentMethod = sc.nextInt();

        String method;

        switch (paymentMethod) {
            case 1:
                method = "Cash";
                break;

            case 2:
                method = "UPI";
                break;

            case 3:
                method = "Card";
                break;

            default:
                System.out.println("Invalid payment method!");
                return;
        }

        System.out.println("Payment Method: " + method);

        if (paymentMethod == 1) {

            System.out.print("Enter cash amount: Rs. ");
            double cash = sc.nextDouble();

            if (cash < totalAmount) {
                System.out.println("Insufficient payment!");
                System.out.println(
                    "Remaining Amount: Rs. " +
                    (totalAmount - cash)
                );
            } else {
                double change = cash - totalAmount;

                System.out.println(
                    "Change: Rs. " + change
                );

                System.out.println(
                    "Payment successful!"
                );
            }

        } else {

            System.out.print(
                "Enter transaction ID: "
            );
            String transactionId = sc.next();

            System.out.println(
                "Transaction ID: " + transactionId
            );

            System.out.println(
                "Payment successful!"
            );
        }

        System.out.println("\n===== PAYMENT RECEIPT =====");
        System.out.println("Amount Paid: Rs. " + totalAmount);
        System.out.println("Payment Status: SUCCESS");
        System.out.println("Thank you!");
    }

    public static void main(String[] args) {

        while (true) {

            System.out.println(
                "\n===== BILLING AND PAYMENT SYSTEM ====="
            );

            System.out.println("1. Generate Bill");
            System.out.println("2. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    generateBill();
                    break;

                case 2:
                    System.out.println(
                        "Thank you for using the system!"
                    );
                    sc.close();
                    return;

                default:
                    System.out.println(
                        "Invalid choice!"
                    );
            }
        }
    }
}
