public class Patient {
    private int PatientID;
    private String Name;
    private int Age;
    private String ContactInfo;
    private String MedicalHistory;
    private String VisitRecords;

    public Patient() {
    }

    public Patient(int patientID, String name, int age, String contactInfo, String medicalHistory, String visitRecords) {
        PatientID = patientID;
        Name = name;
        Age = age;
        ContactInfo = contactInfo;
        MedicalHistory = medicalHistory;
        VisitRecords = visitRecords;
    }

    public void UpdateContactInfo(String newContactInfo) {
        if (newContactInfo.equals(this.ContactInfo)) {         // if the old is the same as new
            System.out.println("Please Enter Different Contact Info !");
        } else {
            this.ContactInfo = newContactInfo;
        }
    }
    public void AddVisitRecord(String visitRecord) {
        this.VisitRecords += visitRecord + "\n";
    }

    public String GetPatientInfo(){
        return "Patient ID: " + this.PatientID + "\nName: " + this.Name + "\nAge: " + this.Age + "\nContact Info: " + ContactInfo + "\nMedical History: " + this.MedicalHistory + "\nVisit Records: " + this.VisitRecords ;
    }

    public String getName() {
        return Name;
    }

    public int getPatientID() {
        return PatientID;
    }

    public int getAge() {
        return Age;
    }

    public String getContactInfo() {
        return ContactInfo;
    }

    public String getMedicalHistory() {
        return MedicalHistory;
    }

    public String getVisitRecords() {
        return VisitRecords;
    }
}
class BSTNode{
    Patient patient;
    BSTNode Right,Left;

    public BSTNode(Patient patient) {
        this.patient=patient;
        Right=Left=null;
    }
}
class BST{
    BSTNode Root;

    public BST() {
        Root = null;
    }
    public void AddPatientImplement(Patient patient){         //the implementation

        Root=AddPatient(this.Root,patient);
    }

    private BSTNode AddPatient(BSTNode Root, Patient patient){     //Add Patient Data
        if (Root==null){
            Root=new BSTNode(patient);
            return Root;
        }
        if (patient.getPatientID() == Root.patient.getPatientID()) {
            System.out.println("A Patient With Same ID Already Found !");
            return Root;
        }
        if (patient.getPatientID()>Root.patient.getPatientID()) {
            Root.Right= AddPatient(Root.Right, patient);
        } else if (patient.getPatientID()<Root.patient.getPatientID()) {
            Root.Left= AddPatient(Root.Left, patient);
        }
        return Root;
    }

    public Patient PatientSearchImplement(int PatientID){
        return PatientSearch(Root, PatientID);
    }

    private Patient PatientSearch(BSTNode Root, int PatientID){
        if (Root==null){
            return null;
        }
        if (Root.patient.getPatientID()==PatientID){
            return Root.patient;
        }
        if (Root.patient.getPatientID()>PatientID){
            return PatientSearch(Root.Left, PatientID);
        }else {
            return PatientSearch(Root.Right, PatientID);
        }
    }
    //Pre-Order Method
    public static void main(String [] args){
        BST b1=new BST();
        Patient p1=new Patient(23101581,"Amr Khaled",19,"amrkhalid25sh@gmail.com","Anemia","5");
        Patient p2=new Patient(23101581,"Ahmed Mashaly",19,"ahmedMashaly@gmail.com","Nothing","3");
        Patient p3=new Patient(23102390,"Mohamed Ibrahim",19,"mohamedibrahm64@gmail.com","Nothing","1");
        b1.AddPatientImplement(p1);
        b1.AddPatientImplement(p2);
        b1.AddPatientImplement(p3);
        Patient search=b1.PatientSearchImplement(23101581);
        if (search==null){
            System.out.println("The Patient Not Found !");
        }else {
            System.out.println("Patient Found:\n" + search.GetPatientInfo());
        }
        waiting waiting = new waiting();
        Appointment appointment1 = new Appointment(101, p1);
        Appointment appointment2 = new Appointment(102, p2);
        Appointment appointment3 = new Appointment(103, p3);
        appointment1.schedule(101, "01/12/2024", "10:00",waiting);
        appointment2.schedule(102, "01/12/2024", "11:00",waiting);
        appointment1.reschedule(101, "01/11/2024", "12:00",waiting);
        appointment3.schedule(103, "01/11/2024", "12:00",waiting);
        appointment3.schedule(103, "01/11/2024", "09:00",waiting);

        System.out.println("All Appointments:");
/*
        Appointment.listAppointments();
*/
        waiting.displayWaitingList();

    }

}
