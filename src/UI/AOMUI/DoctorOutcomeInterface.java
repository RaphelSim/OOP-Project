package UI.AOMUI;

import Controllers.AOManagers.DoctorAOM;
import Common.UserInterface;
import Common.AppointmentOutcomeStatus;
import DatabaseItems.AppointmentOutcome;
import Common.ClearOutput;

public class DoctorOutcomeInterface extends UserInterface {
    private DoctorAOM doctorManager;

    public DoctorOutcomeInterface(DoctorAOM doctorManager) {
        this.doctorManager = doctorManager;
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public void displayOptions() {
        ClearOutput.clearOutput();
        boolean exit = false;
        while (!exit) {
            System.out.println("Doctor Interface - Appointment Outcome Management");
            System.out.println("1. View Appointment Outcome");
            System.out.println("2. Edit Appointment Outcome");
            System.out.println("3. Record New Appointment Outcome");
            System.out.println("4. Exit");

            int choice = getIntInput(-1);
            switch (choice) {
                case 1:
                    viewOutcome();
                    break;
                case 2:
                    editOutcome();
                    break;
                case 3:
                    recordOutcome();
                    break;
                case 4:
                    System.out.println("Exiting Doctor Interface...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void viewOutcome() {
        ClearOutput.clearOutput();
        String appointmentId = getStringInput("Enter Appointment ID to view: ");

        AppointmentOutcome outcome = doctorManager.getOutcome(appointmentId);
        if (outcome != null) {
            outcome.printItem();
            System.out.println();
        } else {
            System.out.println("No outcome found for the given Appointment ID.");
            System.out.println();
        }
    }

    public void editOutcome() {
        ClearOutput.clearOutput();
        String appointmentId = getStringInput("Enter Appointment ID to edit: ");
        AppointmentOutcome outcome = doctorManager.getOutcome(appointmentId);

        if (outcome == null) {
            System.out.println("No outcome found for the given Appointment ID.");
            return;
        }

        String newDate = getStringInput("Enter new Date (YYYY-MM-DD): ");
        String newTypeOfService = getStringInput("Enter new Type of Service: ");
        String newMedication = getStringInput("Enter new Medication: ");
        String newConsultationNotes = getStringInput("Enter new Consultation Notes: ");
        
        AppointmentOutcomeStatus newStatus = null;

        String statusInput = getStringInput("Enter new Status (PENDING or DISPENSED): ").toUpperCase();
        if (statusInput.equals("PENDING") || statusInput.equals("DISPENSED")) {
            newStatus = AppointmentOutcomeStatus.valueOf(statusInput);
        } else {
            System.out.println("Invalid status entered. Defaulting to PENDING.");
            newStatus = AppointmentOutcomeStatus.PENDING;
        }
        boolean success = doctorManager.editOutcome(appointmentId, newDate, newTypeOfService, newMedication, newConsultationNotes, newStatus);
        
        if (success) {
            System.out.println("Appointment outcome updated successfully.");
            System.out.println();
        } else {
            System.out.println("Failed to update appointment outcome.");
            System.out.println();
        }
    }

    public void recordOutcome() {
        ClearOutput.clearOutput();
        String appointmentId = getStringInput("Enter Appointment ID: ");
        String doctorId = getStringInput("Enter Doctor ID: ");
        String patientId = getStringInput("Enter Patient ID: ");
        String date = getStringInput("Enter Date (YYYY-MM-DD): ");
        String typeOfService = getStringInput("Enter Type of Service: ");
        String medication = getStringInput("Enter Medication: ");
        String consultationNotes = getStringInput("Enter Consultation Notes: ");
        AppointmentOutcomeStatus status = AppointmentOutcomeStatus.PENDING; 

        AppointmentOutcome newOutcome = new AppointmentOutcome(
            appointmentId, doctorId, patientId, date, typeOfService, medication, consultationNotes, status
        );

        boolean success = doctorManager.addOutcome(newOutcome);
        if (success) {
            System.out.println("New appointment outcome recorded successfully.");
            System.out.println(); 
            System.out.println();
        } else {
            System.out.println("Failed to record appointment outcome. An outcome with this ID may already exist.");
            System.out.println(); 
        }
    }
}






