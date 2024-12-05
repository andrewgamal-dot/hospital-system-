import java.util.ArrayList;
import java.util.List;

public class Appointment {
    private  int appointmentID;
    private Patient patient;
    private String date;
    protected String time;
    private String status;
    private static List<Appointment> appointments = new ArrayList<>();

    public Patient getPatient() {
        return patient;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public Appointment(int appointmentID, Patient patient) {
        this.appointmentID = appointmentID;
        this.patient = patient;
        this.status = "Pending";
    }
    public String getDate() {
        if (date != null) {
            return date;
        } else {
            return "Not Scheduled";
        }
    }

    public void schedule(int appointmentID, String date, String time, waiting waitingList) {
        if (findAppointmentById(appointmentID) != null) {
            System.out.println("ID already exists."); ;
        }
        if (methodes.isValidDate(date) && methodes.isValidTime(time)) {
            Appointment newAppointment = new Appointment(appointmentID, this.patient);
            newAppointment.date = date;
            newAppointment.time = time;
            newAppointment.status = "Scheduled";
            appointments.add(newAppointment);

            if (waitingList != null) {
                waitingList.addToWaitList(newAppointment);
            }

        }
        else System.out.println("Failed to schedule appointment."); ;
    }
    public String cancel(int appointmentID, waiting waitingList) {
        Appointment appointment = findAppointmentById(appointmentID);
        if (appointment != null) {

            appointment.status = "Canceled";
            return "Appointment canceled: " + appointment;
        }
        return "Failed to cancel appointment.";
    }

    public String reschedule(int appointmentID, String newDate, String newTime, waiting waitingList) {
        Appointment appointment = findAppointmentById(appointmentID);
        if (appointment == null) {
            return "Failed to reschedule: Appointment ID not found.";
        }
        if (!methodes.isValidDate(newDate) || !methodes.isValidTime(newTime)) {
            return "Failed to reschedule: Invalid date or time.";
        }

        appointment.date = newDate;
        appointment.time = newTime;
        appointment.status = "Rescheduled";

        if (waitingList != null) {
            waitingList.removeSpecificAppointment(appointment);
            waitingList.addToWaitList(appointment);
        }

        return "Appointment rescheduled: " + appointment;
    }

    public static Appointment findAppointmentById(int appointmentID) {
        for (Appointment appointment : appointments) {
            if (appointment.appointmentID == appointmentID) {
                return appointment;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentID +
                ", Patient Name: " + patient.getName() +
                ", Date: " + (date != null ? date : "Not Scheduled") +
                ", Time: " + (time != null ? time : "Not Scheduled") +
                ", Status: " + status;
    }
}
