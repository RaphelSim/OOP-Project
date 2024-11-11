package AppointmentSystem;

public class AppointmentTestapp {
    public static void main(String[] args) {
        AppointmentManager am = new AppointmentManager();
        Doctor d = new Doctor("DOC1001", "OOP", null);
        

        d.getAM_D().setPersonalSchedule(d);
        //System.out.println("Hello");
    }
    
}
