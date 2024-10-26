package AppointmentSystem_Package;

public interface AppointmentManager {
	public static Appointment slot = new Appointment();
	
	public void viewSlots();
	
	public void schedule();
	
	public void reschedule();
	
	public void cancel();
	
	public void accept();
	
	public void reject();

}
