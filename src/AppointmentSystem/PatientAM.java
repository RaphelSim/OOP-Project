package AppointmentSystem;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

// [Patient] View available appointment slots with doctors
// 	  [ 5 ]Methods: Schedule (Choose Doctor), Reschedule, Cancel, 
// 	 	View Status of Scheduled, View Past Appointment Outcomes
// 	 
// 	 Status (based on Doctor) - Confirmed, Cancelled, Completed)
// 	  
// 	 Should doctors be already assigned to patients? Should we allow patients to choose doctors?

public class PatientAM extends AppointmentManager {
    private ArrayList<Doctor> doctorList;
    private Appointment patientSlot;


    public PatientAM(ArrayList<Doctor> doctorList) {
        this.doctorList = doctorList;
        
    }

    /*	[Patient] Schedule an Appointment
	 * Choose doctor, date, available time slot
	 */
	public void scheduleAppointment() {
		String choiceDoc = "";
        Doctor chosenDoc = new Doctor();
        Scanner sc = new Scanner(System.in);
        boolean exists = true;
        while (exists) {
            System.out.println("Here are the list of Doctors you can choose from (Enter Doctor ID):");
            System.out.println("Doctor ID   |   Doctor Name");
            for(Doctor d:doctorList) {
                System.out.println(d.getDoctorID() + "      " + d.getName());
            }
            
            choiceDoc = sc.nextLine();

            for(Doctor d:doctorList) {
                if(d.getDoctorID().equalsIgnoreCase(choiceDoc)) {
                    chosenDoc = d;
                    exists = false;
                    break;
                }
            }
            if(exists)
                System.out.println("Invalid Doctor ID entered! Please enter again.");

        }

        chosenDoc.getAM_D().displayDocTimeSlot();

        // User input to choose timeslot then add to Patient's Appointment


		//System.out.println("Appointment slot is taken. Please choose another available slot.");

	}
	
	/* [Patient] Reschedule an Existing Appointment
	 * Change existing appointment to a new slot with no conflict
	 * Slot availability updated automatically
	 */ 
	public void rescheduleAppointment(Appointment oldApp, Appointment newApp, int patientID, int doctorID) {
		oldApp.unAssigned();
		newApp.setAssigned(newApp.getSlotTime(), patientID, doctorID);
	}
	
	/*  [Patient] Cancel an Existing Appointment
	 * 	Upon success cancellation, slot availability updated automatically
	 * 
	 */
	public void cancelAppointment(Appointment app) {
		app.unAssigned();
	}

}
