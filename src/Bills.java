import java.util.Scanner;

public class Bills {
    private int billID;
    private Appointment appointment;
    private double consultationFee;
    private double treatmentFee;
    private double medicationFee;
    private double discount;
    private String VisitRecords;
    private double totalAmount;
    private boolean isPaid;

    public Bills() {
    }

    public Bills(int billID, Appointment appointment, double consultationFee, double treatmentFee, double medicationFee, double discount, String VisitRecords ) {
        this.billID = billID;
        this.appointment = appointment;
        this.consultationFee = consultationFee;
        this.treatmentFee = treatmentFee;
        this.medicationFee = medicationFee;
        this.discount = discount;
        this.VisitRecords = VisitRecords;
        calculateTotal();
        this.isPaid = false;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    private void calculateTotal() {
        double grossAmount = consultationFee + treatmentFee + medicationFee;
        totalAmount = grossAmount - (grossAmount * (discount / 100));
    }

    public void PaymentStatus() {

        if (appointment == null || appointment.getPatient() == null) {
            System.out.println("Error: Bill is with a invalid appointment or patient.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        if (appointment !=null){
            Patient patient = appointment.getPatient();
            if (patient != null) {
                System.out.println("Patient: " + patient.getName());  // Display patient's name
            } else {
                System.out.println("Patient: Unknown");
            }}
        System.out.println("Current payment status: " + (isPaid ? "Paid" : "Unpaid"));

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1 - Mark as Paid");
            System.out.println("2 - Mark as Unpaid");
            System.out.println("3 - Check Payment Status again");
            System.out.println("4 - Print Bill");
            System.out.println("5 - Return to Main Menu");
            System.out.print("Choose your option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if (!isPaid) {
                        isPaid = true;
                        System.out.println("Bill marked as Paid.");
                    } else {
                        System.out.println("Bill is already marked as Paid.");
                    }
                    break;
                case 2:
                    if (isPaid) {
                        isPaid = false;
                        System.out.println("Bill marked as Unpaid.");
                    } else {
                        System.out.println("Bill is already marked as Unpaid.");
                    }
                    break;
                case 3:
                    System.out.println("Current payment status: " + (isPaid ? "Paid" : "Unpaid"));
                    break;
                case 4:
                    if (!isPaid ) {
                        System.out.println("");
                        System.out.println("Patient Unpaid yet!");
                        break;
                    } else {
                        System.out.println("\nBill Details:");
                        System.out.println(this);
                    }
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                case 5:
                    return;
            }
        }
    }

    public int getPatientID() {
        if (appointment != null && appointment.getPatient() != null) {
            return appointment.getPatient().getPatientID();
        }
        return 0;
    }

    @Override
    public String toString() {
        if (appointment == null || appointment.getPatient() == null) {
            return "Bill ID: " + billID + " - Invalid appointment or patient information.";
        }

        Patient patient = appointment.getPatient(); // get patient from appointment

        return "Bill ID: " + billID +
                "\n  Patient Info: " +
                "\n  Name: " + patient.getName() +
                "\n  ID: " + patient.getPatientID() +
                "\n  Age: " + patient.getAge() +
                "\n  Contact Info: " + patient.getContactInfo() +
                "\n  Medical History: " + patient.getMedicalHistory() +
                "\n  VisitRecords: " + patient.getVisitRecords() +
                "\n  Appointment Info: " +
                "\n  ID: " + appointment.getAppointmentID() +
                "\n  Date: " + appointment.getDate() +
                "\n  Time: " + appointment.time +
                "\n  Financial Info: " +
                "\n  Consultation Fee: L.E" + consultationFee +
                "\n  Treatment Fee: L.E" + treatmentFee +
                "\n  Medication Fee: L.E" + medicationFee +
                "\n  Discount: " + discount + "%" +
                "\n  Total Amount: L.E" + totalAmount +
                "\n  Status: " + (isPaid ? "Paid" : "Unpaid");
    }

    public int getBillID() {
        return billID;
    }
}
