import java.util.ArrayList;
import java.util.List;

public class ReportGenerator {
    private String reportType;
    private List<Object> data;

    public ReportGenerator(String reportType, List<Object> data) {
        this.reportType = reportType;
        this.data = data;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public void generatePatientReport(List<Patient> patients) {
        patients = mergeSortPatients(patients);
        System.out.println("Patient Report:");
        for (Patient patient : patients) {
            System.out.println(patient.GetPatientInfo());
            System.out.println(); //space
        }
    }

    public void generateAppointmentReport(List<Appointment> appointments) {
        appointments = quickSortAppointments(appointments, 0, appointments.size() - 1);
        System.out.println("Appointment Report:");
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
            System.out.println();  //space
        }
    }

    public void generateRevenueReport(List<Bills> bills) {
        bills = mergeSortBills(bills);
        double totalRevenue = 0;
        System.out.println("Revenue Report:");
        for (Bills bill : bills) {
            System.out.println(bill);
            totalRevenue += bill.getTotalAmount();
        }
        System.out.println("Total Revenue: " + totalRevenue + " L.E");
    }

    private List<Patient> mergeSortPatients(List<Patient> patients) {
        if (patients.size() <= 1) return patients;

        int mid = patients.size() / 2;
        List<Patient> left = mergeSortPatients(patients.subList(0, mid));
        List<Patient> right = mergeSortPatients(patients.subList(mid, patients.size()));

        return mergePatients(left, right);
    }

    private List<Patient> mergePatients(List<Patient> left, List<Patient> right) {
        List<Patient> sorted = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).getPatientID() <= right.get(j).getPatientID()) {
                sorted.add(left.get(i));
                i++;
            } else {
                sorted.add(right.get(j));
                j++;
            }
        }

        while (i < left.size()) sorted.add(left.get(i++));
        while (j < right.size()) sorted.add(right.get(j++));

        return sorted;
    }

    private List<Appointment> quickSortAppointments(List<Appointment> appointments, int low, int high) {
        if (low < high) {
            int pivotIndex = partitionAppointments(appointments, low, high);
            quickSortAppointments(appointments, low, pivotIndex - 1);
            quickSortAppointments(appointments, pivotIndex + 1, high);
        }
        return appointments;
    }

    private int partitionAppointments(List<Appointment> appointments, int low, int high) {
        Appointment pivot = appointments.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (appointments.get(j).getDate().compareTo(pivot.getDate()) <= 0) {
                i++;
                swapAppointments(appointments, i, j);
            }
        }
        swapAppointments(appointments, i + 1, high);
        return i + 1;
    }

    private void swapAppointments(List<Appointment> appointments, int i, int j) {
        Appointment temp = appointments.get(i);
        appointments.set(i, appointments.get(j));
        appointments.set(j, temp);
    }

    private List<Bills> mergeSortBills(List<Bills> bills) {
        if (bills.size() <= 1) return bills;

        int mid = bills.size() / 2;
        List<Bills> left = mergeSortBills(bills.subList(0, mid));
        List<Bills> right = mergeSortBills(bills.subList(mid, bills.size()));

        return mergeBills(left, right);
    }

    private List<Bills> mergeBills(List<Bills> left, List<Bills> right) {
        List<Bills> sorted = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).getPatientID() <= right.get(j).getPatientID()) {
                sorted.add(left.get(i));
                i++;
            } else {
                sorted.add(right.get(j));
                j++;
            }
        }

        while (i < left.size()) sorted.add(left.get(i++));
        while (j < right.size()) sorted.add(right.get(j++));

        return sorted;
    }
}
