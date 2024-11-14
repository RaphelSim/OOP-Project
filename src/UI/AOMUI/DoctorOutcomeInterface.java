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
        System.out.println();
        System.out.println("Press ENTER to return to menu");
        scanner.nextLine();
    }

    public void selectAppointment() {
        ClearOutput.clearOutput();
        String aptID = getValidatedString("Enter the appointment id to record outcome");
        if (schedule.searchItem(aptID) == null) {
            displayError("Appointment does not exist");
            return;
        } else {
            AppointmentSlot slot = (AppointmentSlot) schedule.searchItem(aptID);
            if (slot.getStatus() != AppointmentStatus.CONFIRMED) {
                displayError("Slot has not been confirmed");
                return;
            } else {
                recordOutcome(slot.getAppointmentId(), slot.getDoctorId(), slot.getPatientId(), slot.getDate());
            }
        }
    }

    public void recordOutcome(String appointmentId, String doctorId, String patientId, String date) {
        ClearOutput.clearOutput();
        String typeOfService = getStringInput("Enter Type of Service: ");
        String medication = getStringInput("Enter Medication: ");
        String consultationNotes = getStringInput("Enter Consultation Notes: ");

        if (doctorManager.writeOutcome(appointmentId, patientId, date, typeOfService, medication, consultationNotes)) {
            System.out.println("Appointment outcome recorded successfully.");
        } else {
            System.out.println("Failed to record appointment outcome. An outcome with this ID may already exist.");
        }
    }
}
