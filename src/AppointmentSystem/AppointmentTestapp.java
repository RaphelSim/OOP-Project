package AppointmentSystem;

public class AppointmentTestapp {
    public static void main(String[] args) {
        AppointmentManager am = new AppointmentManager();
        Doctor d = new Doctor(1001, "OOP", null);

        am.setPersonalSchedule(d);
        //System.out.println("Hello");
    }
    
}
