package Controllers.AMManagers;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import Common.AppointmentManager;
import Common.AppointmentStatus;
import DatabaseItems.AppointmentSlot;
import Databases.DoctorSchedule;


public class DoctorAM extends AppointmentManager {
    private DoctorSchedule doctorSchedule;

	public DoctorAM(DoctorSchedule doctorSchedule) {
		this.doctorSchedule = doctorSchedule;
	}

	// Check valid date
    public boolean isValidDate(int year, int month, int day) {
        try {
            LocalDate date = LocalDate.of(year, month, day);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

	// // Check valid time
    // private static boolean isValidTime(String time) {
    //     if (!time.matches("\\d{2}:\\d{2}"))
    //         return false;

    //     String[] parts = time.split(":");
    //     int hours = Integer.parseInt(parts[0]);
    //     int mins = Integer.parseInt(parts[1]);

    //     return (hours >= 8 && hours <= 18 && mins >= 0 && mins <= 59);
    // }

	// Check valid hour only
    public boolean isValidHour(int hours) {
        return (hours >= 8 && hours <= 18);
    }

	// Check if end time is after start time
    // private static boolean isEndTimeAfterStart(String startTime, String endTime) {
    //     String[] startParts = startTime.split(":");
    //     String[] endParts = endTime.split(":");

    //     int startHours = Integer.parseInt(startParts[0]);
    //     int startMinutes = Integer.parseInt(startParts[1]);

    //     int endHours = Integer.parseInt(endParts[0]);
    //     int endMinutes = Integer.parseInt(endParts[1]);

    //     return (endHours > startHours || (endHours == startHours && endMinutes > startMinutes));
    // }

	// Check if end hour is after start hour
    public boolean isEndTimeAfterStart(int startHours, int endHours) {
        return (endHours > startHours);
    }

	// Convert hours to time format
	public String convertHours(int hours) {
		return String.format("%02d:00", hours);
	}

	// Split start time and end time to individual 60mins timeslot and add to database item
    public void generateTimeSlot(String doctorID, LocalDate date, String startTime, String endTime) {
        LocalTime sT = LocalTime.parse(startTime);
        LocalTime eT = LocalTime.parse(endTime); // end time of schedule
        LocalTime end; // end time for each appointment slot

        while (sT.isBefore(eT)) {
            end = sT.plusMinutes(60);
            // LocalDateTime slotTime = LocalDateTime.of(date, sT);
			AppointmentSlot slot = new AppointmentSlot(doctorID, date.toString(), sT.toString(), end.toString(),
			AppointmentStatus.FREE);
			// Search if Appointment ID exists
			if(doctorSchedule.searchItem(slot.getAppointmentId()) == null)
            	doctorSchedule.addItem(slot);
            sT = sT.plusMinutes(60);
        }
    }
}
