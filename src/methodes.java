import java.util.regex.Pattern;

public class methodes {
    protected static boolean isValidDate(String date) {     // For Appointment M.alaa
        // Matches format DD/MM/YYYY
        String datePattern = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";
        return Pattern.matches(datePattern, date);
    }

    protected static boolean isValidTime(String time) {     // For Appointment M.alaa
        // Matches format HH:MM (24-hour)
        String timePattern = "^([01]\\d|2[0-3]):[0-5]\\d$";
        return Pattern.matches(timePattern, time);
    }

    protected boolean Valid(int Option) {                      //For main Amr
        if (Option == 1 || Option == 2 || Option == 3 || Option == 4 || Option == 5 || Option == -1) {
            return true;
        } else {
            return false;
        }
    }
    protected boolean ValidPhoneNumber(String PhoneNumber) {            // For main Amr
        if (PhoneNumber == null || PhoneNumber.isEmpty()) {
            System.out.println("Phone number cannot be empty.");
            return false;
        }
        if (!PhoneNumber.matches("\\d+")) {
            System.out.println("Entre A valid Int Number");
            return false;
        }
        if (PhoneNumber.length() == 11) {
            return true;
        } else {
            System.out.println("Invalid Phone Number !");
            return false;
        }
    }
}
