package UI.AccountManagementPages;

import Common.ClearOutput;
import Controllers.AccountManager;
import DatabaseItems.Account;
import Common.Role;
import Common.UserInterface;

/**
 * The {@code UpdateDetailsPage} class provides functionality for updating
 * account details such as password, phone number, and email. It extends
 * {@link UserInterface} and interacts with the {@link AccountManager} to manage
 * account details based on the user's role.
 */
public class UpdateDetailsPage extends UserInterface {

    private AccountManager accountManager; // Manager for handling account-related operations

    /**
     * Constructs an {@code UpdateDetailsPage} with the specified
     * {@link AccountManager} instance.
     *
     * @param accountManager the manager used to access and manage account details
     */
    public UpdateDetailsPage(AccountManager accountManager) {
        this.accountManager = accountManager; // Initialize the account manager
    }

    /**
     * Displays options for updating account details based on the user's role.
     *
     * @param account the {@link Account} of the user whose details are being updated
     */
    public void displayOptions(Account account) {
        if (account.getrole() == Role.PAT) { // Check if user is a patient
            displayPatientOptions(); // Display patient-specific options
        } else {
            displayStaffOptions(); // Display staff-specific options
        }
    }

    /**
     * Displays options for patients to update their account details.
     */
    private void displayPatientOptions() {
        boolean quit = false; // Flag to control the loop
        while (!quit) {
            ClearOutput.clearOutput(); // Clear previous output
            int choice = getPatientChoice(); // Get user's choice

            switch (choice) {
                case 1 -> handlePasswordChange(); // Handle password change
                case 2 -> handlePhoneChange(); // Handle phone number change
                case 3 -> handleEmailChange(); // Handle email change
                case 4 -> {
                    quit = true; // Exit option selected
                    continue;
                }
                default -> displayError("Invalid option selected."); // Handle invalid input
            }
        }
    }

    /**
     * Displays options for staff to update their account details.
     */
    private void displayStaffOptions() {
        boolean quit = false; // Flag to control the loop
        while (!quit) {
            ClearOutput.clearOutput(); // Clear previous output
            int choice = getStaffChoice(); // Get user's choice

            switch (choice) {
                case 1 -> handlePasswordChange(); // Handle password change
                case 2 -> {
                    quit = true; // Exit option selected
                    continue;
                }
                default -> displayError("Invalid option selected."); // Handle invalid input
            }
        }
    }

    /**
     * Prompts the user to select an option for changing their password, phone number,
     * or email address and returns the user's choice.
     *
     * @return an integer representing the user's choice of action
     */
    private int getPatientChoice() {
        System.out.println("Please select an option: ");
        System.out.println("1. Change password");
        System.out.println("2. Change phone number");
        System.out.println("3. Change email");
        System.out.println("4. Back");
        return getIntInput(4); // Get user input with a maximum of 4 choices
    }

    /**
     * Prompts the user to select an option for changing their password and returns 
     * the user's choice.
     *
     * @return an integer representing the user's choice of action
     */
    private int getStaffChoice() {
        System.out.println("Please select an option: ");
        System.out.println("1. Change password");
        System.out.println("2. Back");
        return getIntInput(2); // Get user input with a maximum of 2 choices
    }

    /**
     * Handles the process of changing the user's password.
     */
    private void handlePasswordChange() {
        String newPassword = getNewPassword(); // Get new password from user
        if (accountManager.changePassword(newPassword)) { // Attempt to change password
            displaySuccess("Password changed successfully."); // Success message on changing password
        } else {
            displayError("Failed to change password. Please try again."); // Error message on failure
        }
    }

    /**
     * Handles the process of changing the user's phone number.
     */
    private void handlePhoneChange() {
        String newPhone = getNewPhone(); // Get new phone number from user
        if (accountManager.changePhone(newPhone)) { // Attempt to change phone number
            displaySuccess("Phone number changed successfully."); // Success message on changing phone number
        } else {
            displayError("Failed to change phone number. Please try again."); // Error message on failure
        }
    }

    /**
     * Handles the process of changing the user's email address.
     */
    private void handleEmailChange() {
        String newEmail = getNewEmail(); // Get new email from user
        if (accountManager.changeEmail(newEmail)) { // Attempt to change email address
            displaySuccess("Email changed successfully."); // Success message on changing email address
        } else {
            displayError("Failed to change email. Please try again."); // Error message on failure
        }
    }

    /**
     * Prompts the user to enter a new password and returns it.
     *
     * @return a string representing the new password entered by the user
     */
    private String getNewPassword() {
        System.out.println("Please enter your new password:");
        return scanner.nextLine(); // Read and return new password input from user
    }

    /**
     * Prompts the user to enter a new phone number and returns it.
     *
     * @return a string representing the new phone number entered by the user
     */
    private String getNewPhone() {
        System.out.println("Please enter your new phone number:");
        return getNumericString(); // Read and return new phone number input from user, ensuring it's numeric 
    }

    /**
     * Prompts the user to enter a new email address and returns it.
     *
     * @return a string representing the new email address entered by the user 
     */
    private String getNewEmail() {
        System.out.println("Please enter your new email:");
        return scanner.nextLine(); // Read and return new email input from user 
    }
}