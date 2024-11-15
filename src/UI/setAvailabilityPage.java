package UI;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import DatabaseItems.Account;
import DatabaseItems.AppointmentSlot;
import Databases.DoctorSchedule;
import Common.AppointmentStatus;
import Common.ClearOutput;
import Common.UserInterface;

public class SetAvailabilityPage extends UserInterface {
    private Account doctor;
    private DoctorSchedule doctorSchedule;

    public SetAvailabilityPage(Account doctor, DoctorSchedule doctorSchedule) {
        this.doctor = doctor;
        this.doctorSchedule = doctorSchedule;
    }

    // need to check for duplication of slots
    // problem with assuming doctor will enter time correctly?
    public void setAvailability() {
        ClearOutput.clearOutput();
        int day = 0, month = 0, year = 0;
        String startTime = "", endTime = "";

        System.out.println("Welcome Dr." + doctor.getName());
        System.out.println("Please setup your Personal Schedule Availability:");

        // Year
        while (true) {
            System.out.print("Enter Year (e.g.: 2024): ");
            try {
                year = getIntInput(-1);
                if (year > 0)
                    break;
                else
                    System.out.println("Invalid year! Please enter again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a numeric value.");
            }
        }

        // Month
        while (true) {
            System.out.print("Enter Month (1-12): ");
            try {
                month = getIntInput(-1);
                if (month >= 1 && month <= 12)
                    break;
                else
                    System.out.println("Invalid month. Please enter a value between 1 and 12.");

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }

        // Day
        while (true) {
            System.out.print("Enter Day (1-31): ");
            try {
                day = getIntInput(-1);
                if (isValidDate(year, month, day))
                    break;
                else
                    System.out.println("Invalid date! Please enter a valid date for your input month and year.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }

        // Start Time
        while (true) {
            // System.out.print("Enter Start Time (HH:MM, from 08:00 to 18:00): ");
            startTime = getValidatedString("Enter Start Time (HH:MM, from 08:00 to 18:00): ");
            if (isValidTime(startTime))
                break;
            else
                System.out.println("Invalid start time! Please enter a start time between 08:00 and 18:00.");
        }

        // End Time
        while (true) {
            // System.out.print("Enter End Time (HH:MM, must be after Start Time): ");
            endTime = getValidatedString("Enter End Time (HH:MM, must be after Start Time): ");
            if (isValidTime(endTime) && isEndTimeAfterStart(startTime, endTime))
                break;
            else
                System.out.println("Invalid end time! Ensure it is after start time and within 08:00 to 18:00.");
        }

        // Display Personal Schedule Date and Time
        System.out.printf("\nYou have entered:\nDate: %02d/%02d/%04d\nTime: %s - %s\n", day, month, year, startTime,
                endTime);
        LocalDate confirmDate = LocalDate.of(year, month, day);
        // Should we ask to confirm date and time?

        // Code to format them and split to 60mins interval time slots assigning each to
        // appointment
        generateTimeSlot(doctor.getid(), confirmDate, startTime, endTime);
        displaySuccess("Available slot updated");

        // System.out.println("Doc:" + doctor.getid());
        // Display Timeslots
        // displayDocTimeSlot(doctor);

        // for(AppointmentSlot a: this.doctorSchedule) {
        // a.printItem();
        // }
    }

    // Just a method to check valid date
    private static boolean isValidDate(int year, int month, int day) {
        try {
            LocalDate date = LocalDate.of(year, month, day);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // Just a method to check valid time
    private static boolean isValidTime(String time) {
        if (!time.matches("\\d{2}:\\d{2}"))
            return false;

        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int mins = Integer.parseInt(parts[1]);

        return (hours >= 8 && hours <= 18 && mins >= 0 && mins <= 59);
    }

    // Just a method to check if end time is after start time
    private static boolean isEndTimeAfterStart(String startTime, String endTime) {
        String[] startParts = startTime.split(":");
        String[] endParts = endTime.split(":");

        int startHours = Integer.parseInt(startParts[0]);
        int startMinutes = Integer.parseInt(startParts[1]);

        int endHours = Integer.parseInt(endParts[0]);
        int endMinutes = Integer.parseInt(endParts[1]);

        return (endHours > startHours || (endHours == startHours && endMinutes > startMinutes));
    }

    // Just a method to split start time and end time to individual 30mins time
    // slots
    private void generateTimeSlot(String doctorID, LocalDate date, String startTime,
            String endTime) {
        LocalTime sT = LocalTime.parse(startTime);
        LocalTime eT = LocalTime.parse(endTime); // end time of schedule
        LocalTime end; // end time for each appointment slot
        while (sT.plusMinutes(60).isBefore(eT)) {
            end = sT.plusMinutes(60);
            // LocalDateTime slotTime = LocalDateTime.of(date, sT);

            doctorSchedule.addItem(new AppointmentSlot(doctorID, date.toString(), sT.toString(), end.toString(),
                    AppointmentStatus.FREE));
            sT = sT.plusMinutes(60);
        }
    }

}
