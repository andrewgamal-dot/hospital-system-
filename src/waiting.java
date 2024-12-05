import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

class Node1 {
    Appointment data;
    Node1 next;

    Node1(Appointment appointment) {
        this.data = appointment;
        this.next = null;
    }
}
class Queue {
    private Node1 front;
    private Node1 end;

    public Queue() {
        front = null;
        end = null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(Appointment appointment) {
        Node1 newNode = new Node1(appointment);
        if (isEmpty()) {
            front = newNode;
            end = newNode;
        } else {
            if (compareDates(newNode.data.getDate(), front.data.getDate()) < 0) {

                newNode.next = front;
                front = newNode;
            } else {
                Node1 current = front;
                while (current.next != null && compareDates(current.next.data.getDate(), newNode.data.getDate()) <= 0) {
                    current = current.next;
                }
                newNode.next = current.next;
                current.next = newNode;
                if (newNode.next == null) {
                    end = newNode;
                }
            }
        }
        System.out.println("Appointment added to the waiting list: " + appointment);
    }


    private int compareDates(String date1, String date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date d1 = sdf.parse(date1);
            Date d2 = sdf.parse(date2);
            return d1.compareTo(d2);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format. Use 'dd/MM/yyyy'.");
        }
    }
}
public class waiting {
    private LinkedList<Appointment> queue;
    public waiting() {
        this.queue = new LinkedList<>();
    }
    Scanner n=new Scanner(System.in);
    public void addToWaitList(Appointment appointment) {
        int index = 0;
        for (Appointment a : queue) {
            if (( (compareDates(appointment.getDate(), a.getDate()) == 0 && compareTimes(appointment.time, a.time) == 0))) {
                System.out.println("unfoultunatly this time is already taken");
                return;
            }
            if (compareDates(appointment.getDate(), a.getDate()) < 0 ||( (compareDates(appointment.getDate(), a.getDate()) == 0 && compareTimes(appointment.time, a.time) < 0))) {
                break;}
            index++;
        }
        queue.add(index, appointment);
    }

    public void removeSpecificAppointment(Appointment appointment) {
        queue.removeIf(a -> a.equals(appointment));
    }
    private int compareDates(String date1, String date2) {                    //number restarted the programme
        String[] parts1 = date1.split("/");
        String[] parts2 = date2.split("/");
        int yearDifference = Integer.parseInt(parts1[2]) - Integer.parseInt(parts2[2]);
        if (yearDifference != 0) return yearDifference;

        int monthDifference = Integer.parseInt(parts1[1]) - Integer.parseInt(parts2[1]);
        if (monthDifference != 0) return monthDifference;

        return Integer.parseInt(parts1[0]) - Integer.parseInt(parts2[0]);
    }
    private int compareTimes(String time1, String time2) {
        String[] parts1 = time1.split(":");
        String[] parts2 = time2.split(":");
        int hourDiff = Integer.parseInt(parts1[0]) - Integer.parseInt(parts2[0]);
        if (hourDiff != 0) return hourDiff;
        else {
            return Integer.parseInt(parts1[1]) - Integer.parseInt(parts2[1]);
        }
    }

    public void displayWaitingList() {
        if (queue.isEmpty()) {
            System.out.println("Waiting list is empty.");
        } else {
            System.out.println("Waiting List:");
            for (Appointment appointment : queue) {
                System.out.println("- " + appointment);
            }
        }
    }
}
