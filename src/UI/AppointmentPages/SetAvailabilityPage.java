package UI.AppointmentPages;

import java.time.LocalDate;
import DatabaseItems.Account;
import DatabaseItems.AppointmentSlot;
import Databases.DoctorSchedule;
import Common.ClearOutput;
import Common.DatabaseItems;
import Common.UserInterface;
import Controllers.AMManagers.DoctorAM;

public class SetAvailabilityPage extends UserInterface {
    private Account doctor;
    private DoctorSchedule doctorSchedule;
    private DoctorAM doctorAM;

    public SetAvailabilityPage(Account doctor, DoctorSchedule doctorSchedule, DoctorAM doctorAM) {
        this.doctor = doctor;
        this.doctorSchedule = doctorSchedule;
        this.doctorAM = doctorAM;
    }

    public void setAvailability() {
        ClearOutput.clearOutput();
        int day = 0, month = 0, year = 0;
        int startHours, endHours;

        System.out.println("Welcome Dr." + doctor.getName());
        System.out.println("This is your current schedule: ");
        System.out.println("------------------------------------");
        for (DatabaseItems item : doctorSchedule.getRecords()) {
            AppointmentSlot slot = (AppointmentSlot) item;
            System.out.println(
                    slot.getDate() + " " + slot.getTimestart() + " to " + slot.getTimeend() + " " + slot.getStatus());
        }

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
                if (doctorAM.isValidDate(year, month, day))
                    break;
                else
                    System.out.println("Invalid date! Please enter a valid date for your input month and year.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }

        // Start Time
        while (true) {
            System.out.print("Enter Start Hour e.g. 9 (Must be within 8 to 18): ");
            startHours = getIntInput(-1);
            if (doctorAM.isValidHour(startHours))
                break;
            else
                System.out.println("Invalid start time! Please enter a start time between 8 and 18.");
        }

        // End Time
        while (true) {
            System.out.print("Enter End Hour e.g. 18 (Must be within 8 to 18 and after Start Hour): ");
            endHours = getIntInput(-1);
            if (doctorAM.isValidHour(endHours) && doctorAM.isEndTimeAfterStart(startHours, endHours))
                break;
            else
                System.out.println("Invalid end time! Ensure it is after start time and within 08 to 18.");
        }

        // Display Personal Schedule Date and Time
        String startTime = doctorAM.convertHours(startHours);
        String endTime = doctorAM.convertHours(endHours);
        System.out.printf("\nYou have entered:\nDate: %02d/%02d/%04d\nTime: %s - %s\n", day, month, year, startTime,
                endTime);
        LocalDate confirmDate = LocalDate.of(year, month, day);

        // Format them and split to 60mins interval time slots assigning each to
        // Appointment Slot Database Item
        doctorAM.generateTimeSlot(doctor.getid(), confirmDate, startTime, endTime);
        doctorSchedule.sortAppointments();
        displaySuccess("Available slots updated");
        pauseAndView();
    }

}
