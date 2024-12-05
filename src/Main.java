import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        BST DataBase = new BST();
        methodes methodes = new methodes();
        waiting waiting=new waiting();
        List<Patient> allPatients = new ArrayList<>();
        List<Appointment> allAppointments = new ArrayList<>();
        List<Bills> allBills = new ArrayList<>();
        ReportGenerator reportGenerator = new ReportGenerator("Patient Report", new ArrayList<>());
        Patient patient;
        System.out.println("***** Welcome To AIU Hospital System ******");
        int option = 0;
        while (option != -1) {
            System.out.println("** Main Menu **");
            System.out.println("1. Add Patient");
            System.out.println("2. Find Patient");
            System.out.println("3. Schedule Appointment/Reschedule/Cancel/View Appointments");
            System.out.println("4. Bills");
            System.out.println("5. Hospital Reports");
            System.out.println("-1. Exit");
            System.out.print("Choose An Option: ");
            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.nextLine();
                continue;
            }

            if (methodes.Valid(option)) {
                switch (option) {
                    case 1:
                        int PatientID;
                        while (true) {
                            System.out.print("Enter The Patient ID (From 1 To 1000): ");
                            if (scanner.hasNextInt()) {
                                PatientID = scanner.nextInt();
                                if (PatientID > 0 && PatientID <= 1000) {
                                    // Check if the Patient ID already exists
                                    if (DataBase.PatientSearchImplement(PatientID) != null) {
                                        System.out.println("A Patient with the same ID already exists. Please enter a unique ID.");
                                        continue;
                                    }
                                    break;
                                } else {
                                    System.out.println("Invalid ID! Please enter an ID between 1 and 1000.");
                                }
                            } else {
                                System.out.println("Invalid input! Please enter a valid integer.");
                                scanner.next();
                            }
                        }

                        int Age;
                        while (true) {
                            System.out.print("Enter The Patient Age (Between 1 and 200): ");
                            if (scanner.hasNextInt()) {
                                Age = scanner.nextInt();
                                if (Age > 0 && Age <= 200) {
                                    break;
                                } else {
                                    System.out.println("Invalid Age! Please enter an age between 1 and 200.");
                                }
                            } else {
                                System.out.println("Invalid input! Please enter a valid integer.");
                                scanner.next();
                            }
                        }
                        System.out.print("Enter The Patient Name: ");
                        String PatientName = scanner.next();

                        System.out.print("Enter The Patient Phone Number (11 Digit): ");
                        String PhoneNumber = scanner.next();
                        while (!methodes.ValidPhoneNumber(PhoneNumber)) {
                            System.out.print("Enter The Patient Phone Number (11 Digit): ");
                            PhoneNumber = scanner.next();
                        }

                        System.out.print("Enter The Patient Medical History: ");
                        String MedicalHistory = scanner.next();

                        System.out.print("Enter The Patient Visit Record: ");
                        String VisitRecord = scanner.next();

                        patient = new Patient(PatientID, PatientName, Age, PhoneNumber, MedicalHistory, VisitRecord);
                        DataBase.AddPatientImplement(patient);
                        allPatients.add(patient); // Add to the list
                        System.out.println("Patient added successfully!");
                        break;
                    case 2:
                        System.out.print("Enter The Patient ID To Search: ");
                        int SearchID;
                        if (scanner.hasNextInt()) {
                            SearchID = scanner.nextInt();
                        } else {
                            System.out.println("Invalid input! Please enter a valid integer.");
                            scanner.next();
                            continue;
                        }
                        Patient SearchFromData = DataBase.PatientSearchImplement(SearchID);
                        if (SearchFromData == null) {
                            System.out.println("The Patient Not Found!");
                        } else {
                            System.out.println("Patient Found:\n" + SearchFromData.GetPatientInfo());
                        }
                        break;

                    case 3:
                        System.out.println("1.Schedule");
                        System.out.println("2.Reschedule");
                        System.out.println("3.Cancel");
                        System.out.println("4.View List");
                        System.out.print("Choose An Option: ");
                        int cases= scanner.nextInt();
                        if (cases==1) {
                            System.out.print("Enter Patient ID for Appointment: ");
                            int appointmentPatientID = scanner.nextInt();
                            scanner.nextLine();
                            Patient appointmentPatient = DataBase.PatientSearchImplement(appointmentPatientID);
                            if (appointmentPatient != null) {
                                System.out.print("Enter Appointment ID: ");
                                int appointmentID = scanner.nextInt();
                                scanner.nextLine();
                                if (Appointment.findAppointmentById(appointmentID) != null) {
                                    System.out.println("Error: An appointment with this ID already exists.");
                                    break;
                                }
                                System.out.print("Enter Date (DD/MM/YYYY): ");
                                String date = scanner.nextLine();

                                System.out.print("Enter Time (HH:MM): ");
                                String time = scanner.nextLine();

                                if (methodes.isValidDate(date) && methodes.isValidTime(time)) {
                                    Appointment appointment = new Appointment(appointmentID, appointmentPatient);
                                    appointment.schedule(appointmentID, date, time, waiting);
                                    allAppointments.add(appointment);
                                } else {
                                    System.out.println("Invalid date or time format.");
                                }
                            } else {
                                System.out.println("Patient not found.");
                            }
                            waiting.displayWaitingList();           //There is 2 Points Here !!!!!!!
                            break;
                        } else if (cases==2) {
                            System.out.print("Enter Appointment ID to Reschedule: ");
                            int rescheduleID = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            System.out.print("Enter New Date (DD/MM/YYYY): ");
                            String newDate = scanner.nextLine();

                            System.out.print("Enter New Time (HH:MM): ");
                            String newTime = scanner.nextLine();

                            Appointment rescheduleAppointment = Appointment.findAppointmentById(rescheduleID);
                            if (rescheduleAppointment != null) {
                                System.out.println(rescheduleAppointment.reschedule(rescheduleID, newDate, newTime, waiting));
                            } else {
                                System.out.println("Appointment not found.");
                            }
                            break;
                        } else if (cases==3) {
                            System.out.print("Enter Appointment ID to Cancel: ");
                            int cancelID = scanner.nextInt();
                            scanner.nextLine();
                            Appointment cancelAppointment = Appointment.findAppointmentById(cancelID);
                            if (cancelAppointment != null) {
                                System.out.println(cancelAppointment.cancel(cancelID, waiting));
                            } else {
                                System.out.println("Appointment not found.");
                            }
                            break;
                        } else if (cases==4) {
                            waiting.displayWaitingList();
                            break;
                        }else {
                            System.out.println("Invalid Input");
                            break;
                        }

                    case 4:
                        System.out.println("1. Create a Bill");
                        System.out.println("2. View Bills");
                        System.out.println("3. Update Payment Status");
                        System.out.println("4. Print Bill Details");
                        System.out.println("0. Return to Main Menu");
                        System.out.print("Choose an option: ");
                        int billingOption = scanner.nextInt();
                        scanner.nextLine();
                        switch (billingOption) {
                            case 1:
                                System.out.print("Enter Bill ID: ");
                                int billID = scanner.nextInt();
                                scanner.nextLine();

                                System.out.print("Enter Appointment ID: ");
                                int appointmentID = scanner.nextInt();
                                scanner.nextLine();

                                Appointment appointment = Appointment.findAppointmentById(appointmentID);
                                if (appointment == null) {
                                    System.out.println("Invalid Appointment ID. Please try again.");
                                    break;
                                }

                                System.out.print("Enter Consultation Fee: ");
                                double consultationFee = scanner.nextDouble();
                                System.out.print("Enter Treatment Fee: ");
                                double treatmentFee = scanner.nextDouble();
                                System.out.print("Enter Medication Fee: ");
                                double medicationFee = scanner.nextDouble();
                                System.out.print("Enter Discount (%): ");
                                double discount = scanner.nextDouble();
                                scanner.nextLine();

                                System.out.print("Enter Visit Records: ");
                                String visitRecords = scanner.nextLine();

                                Bills newBill = new Bills(billID, appointment, consultationFee, treatmentFee, medicationFee, discount, visitRecords);
                                allBills.add(newBill);
                                System.out.println("Bill created successfully!");
                                break;

                            case 2:
                                if (allBills.isEmpty()) {
                                    System.out.println("No bills found.");
                                } else {
                                    System.out.println("All Bills:");
                                    for (Bills bill : allBills) {
                                        System.out.println(bill);
                                    }
                                }
                                break;

                            case 3:
                                System.out.print("Enter Bill ID to update payment status: ");
                                int updateBillID = scanner.nextInt();
                                scanner.nextLine();

                                Bills updateBill = allBills.stream()
                                        .filter(b -> b.getBillID() == updateBillID)
                                        .findFirst()
                                        .orElse(null);

                                if (updateBill != null) {
                                    updateBill.PaymentStatus();   //from Bills method
                                } else {
                                    System.out.println("Bill not found!");
                                }
                                break;

                            case 0:
                                System.out.println("Returning to Main Menu...");
                                break;

                            default:
                                System.out.println("Invalid option! Please try again.");
                        }
                        break;
                    case 5:
                        System.out.println("1.Patient Report");
                        System.out.println("2.Appointments Report");
                        System.out.println("3.Billing Report");
                        System.out.print("Select The Option: ");
                        int option5= scanner.nextInt();
                        switch (option5) {
                            case 1:
                                if (allPatients.isEmpty()){
                                    System.out.println("No Patients Found !");
                                }else {
                                    reportGenerator.generatePatientReport(allPatients);
                                }
                                break;
                            case 2:
                                if (allAppointments.isEmpty()){
                                    System.out.println("No Appointments Found !");
                                }else {
                                    reportGenerator.generateAppointmentReport(allAppointments);
                                }
                                break;
                            case 3:
                                if (allBills.isEmpty()){
                                    System.out.println("No Bills Found !");
                                }else {
                                    reportGenerator.generateRevenueReport(allBills);
                                }
                                break;
                        }
                        break;

                    case -1:
                        System.out.println("Shut Down...");
                        break;
                }
            } else {
                System.out.println("Invalid Input! Try Again.");
            }
        }
    }

    }
