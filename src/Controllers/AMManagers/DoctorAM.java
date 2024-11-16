package Controllers.AMManagers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import Common.AppointmentStatus;
import Common.DatabaseItems;
import DatabaseItems.AppointmentSlot;
import Databases.DoctorSchedule;

/**
 * The {@code DoctorAM} class manages appointment-related functionalities for doctors.
 * It provides methods for validating dates and times, generating appointment slots,
 * and managing appointment requests.
 */
public class DoctorAM {
    private DoctorSchedule doctorSchedule; // Schedule associated with the doctor

    /**
     * Constructs a {@code DoctorAM} with the specified doctor's schedule.
     *
     * @param doctorSchedule the {@link DoctorSchedule} associated with the doctor
     */
    public DoctorAM(DoctorSchedule doctorSchedule) {
        this.doctorSchedule = doctorSchedule;
    }

    /**
     * Checks if the provided date is valid.
     *
     * @param year  the year of the date
     * @param month the month of the date
     * @param day   the day of the date
     * @return true if the date is valid; false otherwise
     */
    public boolean isValidDate(int year, int month, int day) {
        try {
            LocalDate.of(year, month, day); // Attempt to create a LocalDate
            return true; // Date is valid
        } catch (DateTimeParseException e) {
            return false; // Invalid date
        }
    }

    /**
     * Checks if the provided hour is within valid working hours (8 AM to 6 PM).
     *
     * @param hours the hour to check
     * @return true if the hour is valid; false otherwise
     */
    public boolean isValidHour(int hours) {
        return (hours >= 8 && hours <= 18); // Valid if within working hours
    }

    /**
     * Checks if the end hour is after the start hour.
     *
     * @param startHours the starting hour
     * @param endHours   the ending hour
     * @return true if end hour is after start hour; false otherwise
     */
    public boolean isEndTimeAfterStart(int startHours, int endHours) {
        return (endHours > startHours); // End time must be after start time
    }

    /**
     * Converts an hour to a time format string (HH:00).
     *
     * @param hours the hour to convert
     * @return a string representing the time in HH:00 format
     */
    public String convertHours(int hours) {
        return String.format("%02d:00", hours); // Format hour as HH:00
    }

    /**
     * Checks if an appointment ID exists in the doctor's schedule.
     *
     * @param slotID the unique identifier of the appointment slot
     * @return true if the appointment ID exists; false otherwise
     */
    public boolean isAppIDExist(String slotID) {
        return doctorSchedule.searchItem(slotID) != null; // Return true if found
    }

    /**
     * Generates hourly appointment slots for a doctor between specified start and end times.
     *
     * @param doctorID      the unique identifier of the doctor
     * @param date          the date for which to generate slots
     * @param startTime     the starting time for generating slots (in HH:mm format)
     * @param endTime       the ending time for generating slots (in HH:mm format)
     */
    public void generateTimeSlot(String doctorID, LocalDate date, String startTime, String endTime) {
        LocalTime sT = LocalTime.parse(startTime); // Parse starting time
        LocalTime eT = LocalTime.parse(endTime);   // Parse ending time

        while (sT.isBefore(eT)) { // Generate slots until reaching end time
            LocalTime end = sT.plusMinutes(60); // End time for each appointment slot
            
            AppointmentSlot slot = new AppointmentSlot(doctorID, date.toString(), sT.toString(), end.toString(),
                    AppointmentStatus.FREE);
            if (!isAppIDExist(slot.getAppointmentId())) // Check if slot ID already exists
                doctorSchedule.addItem(slot); // Add new slot to schedule
            
            sT = sT.plusMinutes(60); // Move to next hour slot
        }
    }

    /**
     * Accepts an appointment slot request and updates its status to CONFIRMED.
     *
     * @param slot the {@link AppointmentSlot} to accept
     */
    public void acceptRequest(AppointmentSlot slot) {
        System.out.println("You have accepted the appointment slot.");
        slot.setStatus(AppointmentStatus.CONFIRMED); // Update status to CONFIRMED
    }

    /**
     * Rejects an appointment slot request and updates its status to DECLINED.
     *
     * @param slot the {@link AppointmentSlot} to reject
     */
    public void rejectRequest(AppointmentSlot slot) {
        System.out.println("You have rejected the appointment slot.");
        slot.setStatus(AppointmentStatus.DECLINED); // Update status to DECLINED
    }

    /**
     * Retrieves all available appointment slots from the doctor's schedule.
     *
     * @return a list of available {@link AppointmentSlot} objects; null if no schedule found
     */
    public List<AppointmentSlot> getAvailableSlots() {
        if (doctorSchedule == null)
            return null; // No schedule found
        
        List<AppointmentSlot> slots = new ArrayList<AppointmentSlot>();
        for (DatabaseItems item : doctorSchedule.getRecords()) { 
            AppointmentSlot slot = (AppointmentSlot) item; 
            slots.add(slot); // Add each appointment slot to list 
        }
        return slots; // Return all slots 
    }

    /**
     * Retrieves a specific appointment slot by its ID.
     *
     * @param AppID the unique identifier of the appointment slot
     * @return an {@link AppointmentSlot} object if found; null otherwise
     */
    public AppointmentSlot getSlotWithAppID(String AppID) {
        return (AppointmentSlot) doctorSchedule.searchItem(AppID); // Return found slot or null 
    }
}