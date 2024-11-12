package UI;

import Common.ClearOutput;
import Common.CustomTimer;
import Controllers.AccountManager;
import DatabaseItems.Account;
import Common.Role;
import Common.UserInterface;

public class UpdateDetailsPage extends UserInterface {

    private AccountManager accountManager;

    public UpdateDetailsPage(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void displayOptions(Account account) {
        if (account.getrole() == Role.PAT) {
            displayPatientOptions();
        } else {
            displayStaffOptions();
        }
    }

    private void displayPatientOptions() {
        boolean quit = false;
        while (!quit) {
            ClearOutput.clearOutput();
            int choice = getPatientChoice();

            switch (choice) {
                case 1 -> handlePasswordChange();
                case 2 -> handlePhoneChange();
                case 3 -> handleEmailChange();
                case 4 -> {quit = true;continue;}
                default -> displayError("Invalid option selected.");
            }
            CustomTimer.pause(1500);
        }
    }

    private void displayStaffOptions() {
        boolean quit = false;
        while (!quit) {
            ClearOutput.clearOutput();
            int choice = getStaffChoice();

            switch (choice) {
                case 1 -> handlePasswordChange();
                case 2 -> {quit = true;continue;}
                default -> displayError("Invalid option selected.");
            }
            CustomTimer.pause(1500);
        }
    }

    private int getPatientChoice() {
        System.out.println("Please select an option: ");
        System.out.println("1. Change password");
        System.out.println("2. Change phone number");
        System.out.println("3. Change email");
        System.out.println("4. Back");
        return getIntInput(4);
    }

    private int getStaffChoice() {
        System.out.println("Please select an option: ");
        System.out.println("1. Change password");
        System.out.println("2. Back");
        return getIntInput(2);
    }

    private void handlePasswordChange() {
        String newPassword = getNewPassword();
        if (accountManager.changePassword(newPassword)) {
            displaySuccess("Password changed successfully.");
        } else {
            displayError("Failed to change password. Please try again.");
        }
    }

    private void handlePhoneChange() {
        String newPhone = getNewPhone();
        if (accountManager.changePhone(newPhone)) {
            displaySuccess("Phone number changed successfully.");
        } else {
            displayError("Failed to change phone number. Please try again.");
        }
    }

    private void handleEmailChange() {
        String newEmail = getNewEmail();
        if (accountManager.changeEmail(newEmail)) {
            displaySuccess("Email changed successfully.");
        } else {
            displayError("Failed to change email. Please try again.");
        }
    }

    private String getNewPassword() {
        System.out.println("Please enter your new password:");
        return scanner.nextLine();
    }

    private String getNewPhone() {
        System.out.println("Please enter your new phone number:");
        return getNumericString();
    }

    private String getNewEmail() {
        System.out.println("Please enter your new email:");
        return scanner.nextLine();
    }
}