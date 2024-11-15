package UI.AOMUI;

import Controllers.AOManagers.DoctorAOM;
import Common.UserInterface;
import Common.AppointmentStatus;
import DatabaseItems.AppointmentSlot;
import Databases.DoctorSchedule;
import Common.ClearOutput;
import Common.DatabaseItems;

public class DoctorOutcomeInterface extends UserInterface {
    private DoctorAOM doctorManager;
    private DoctorSchedule schedule;

    public DoctorOutcomeInterface(DoctorAOM doctorManager, DoctorSchedule doctorSchedule) {
        this.doctorManager = doctorManager;
        this.schedule = doctorSchedule;
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public void displayOptions() {
        boolean exit = false;
        while (!exit) {
            ClearOutput.clearOutput();
            System.out.println("Doctor Interface - Appointment Outcome Management");
            System.out.println("1. View Confirmed Appointments");
            System.out.println("2. Record Appointment Outcome");
            System.out.println("3. Exit");

            int choice = getIntInput(-1);
            switch (choice) {
                case 1:
                    viewConfirmedAppointments();
                    break;
                case 2:
                    selectAppointment();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    displayError("Invalid option. Please try again.");
            }
        }
    }

    public void viewConfirmedAppointments() {
        ClearOutput.clearOutput();
        System.out.println("All confirmed appointments: ");
        System.out.println("------------------------------");
        for (DatabaseItems item : schedule.getRecords()) {
            AppointmentSlot slot = (AppointmentSlot) item;
            if (slot.getStatus() == AppointmentStatus.CONFIRMED) {
                slot.printItem();
            }
        }
        pauseAndView();
    }

    public void selectAppointment() {
        ClearOutput.clearOutput();
        System.out.println("All confirmed appointments");
        System.out.println("--------------------------------");
        for (DatabaseItems item : schedule.getRecords()) {
            AppointmentSlot slot = (AppointmentSlot) item;
            System.out.println(slot.getPatientId() + "  " + slot.getDate() + "  " + slot.getTimestart() + " to "
                    + slot.getTimeend());
        }

        System.out.println();
        String aptID = getValidatedString(
                "Enter the date and time in the format YYYY-MM-DD/HH:mm \n e.g 2024-12-31/13:00");
        aptID = doctorManager.getDoctorId() + "/" + aptID;
        if (schedule.searchItem(aptID) == null) {
            displayError("Appointment does not exist");
            return;
        } else {
            AppointmentSlot slot = (AppointmentSlot) schedule.searchItem(aptID);
            if (slot == null || slot.getStatus() != AppointmentStatus.CONFIRMED) {
                displayError("Invalid slot");
                return;
            } else {
                recordOutcome(slot.getAppointmentId(), slot.getDoctorId(), slot.getPatientId(), slot.getDate());
                displaySuccess("Outcome recorded");
            }
        }
    }

    public void recordOutcome(String appointmentId, String doctorId, String patientId, String date) {
        ClearOutput.clearOutput();
        String typeOfService = getStringInput("Enter Type of Service: ");
        String medication = getStringInput("Enter Medication: ");
        String consultationNotes = getStringInput("Enter Consultation Notes: ");

        if (doctorManager.writeOutcome(appointmentId, patientId, date, typeOfService, medication, consultationNotes)) {
            displaySuccess("Appointment outcome recorded successfully.");
        } else {
            displayError("Failed to record appointment outcome. An outcome with this ID may already exist.");
        }
    }
}
