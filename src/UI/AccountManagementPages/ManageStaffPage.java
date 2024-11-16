package UI.AccountManagementPages;

import Common.ClearOutput;
import Common.FilterParam;
import Common.Gender;
import Common.Role;
import Common.UserInterface;
import Controllers.StaffManager;
import DatabaseItems.Account;

/**
 * Represents the staff management page in the user interface.
 * Provides functionalities to add, remove, edit, and display staff details.
 */
public class ManageStaffPage extends UserInterface {
    private final StaffManager staffManager;

    /**
     * Constructs a new instance of the ManageStaffPage with the specified StaffManager.
     * @param staffManager the staff manager responsible for handling staff data.
     */
    public ManageStaffPage(StaffManager staffManager) {
        this.staffManager = staffManager;
    }

    /**
     * Displays the main menu options for managing staff and handles user input.
     */
    public void displayOptions() {
        boolean quit = false;
        while (!quit) {
            ClearOutput.clearOutput();
            System.out.println("Please select an option: ");
            System.out.println("1. Add Staff");
            System.out.println("2. Remove Staff");
            System.out.println("3. Edit Staff");
            System.out.println("4. Display Staff");
            System.out.println("5. Back");

            int choice = getIntInput(5);
            ClearOutput.clearOutput(); // clear input after making a choice
            switch (choice) {
                case 1 -> handleAddStaff();
                case 2 -> handleRemoveStaff();
                case 3 -> handleEditStaff();
                case 4 -> handleDisplayStaff();
                case 5 -> quit = true;
                default -> displayError("Invalid option selected.");
            }
        }
    }

    /**
     * Handles the addition of a new staff member by collecting input and delegating to the StaffManager.
     */
    private void handleAddStaff() {
        Role role = getRole();
        Gender gender = getGender();
        int age = getValidatedInt("Enter age: ");
        String name = getValidatedString("Enter name: ");

        if (role != null && gender != null && age > 0 && name != null) {
            try {
                Account account = new Account(name, "", role, gender, age);
                staffManager.addStaff(account);
                displaySuccess("Staff added successfully!");
            } catch (Exception e) {
                displayError(e.getMessage());
            }
        } else {
            displayError("Invalid input provided. Please try again.");
        }
    }

    /**
     * Handles the removal of a staff member by their ID.
     */
    private void handleRemoveStaff() {
        System.out.println("Please enter target user id to remove:");
        String id = scanner.nextLine().trim();
        try {
            if (staffManager.removeStaff(id)) {
                displaySuccess("User removed successfully.");
            } else {
                displayError("User not found.");
            }
        } catch (Exception e) {
            displayError(e.getMessage());
        }
    }

    /**
     * Handles editing details of an existing staff member.
     */
    private void handleEditStaff() {
        System.out.println("Please enter target user id to edit:");
        String id = scanner.nextLine().trim();
        Account user = staffManager.getUserInfo(id);

        if (user == null) {
            displayError("User not found.");
            return;
        }

        boolean quit = false;
        while (!quit) {
            ClearOutput.clearOutput();
            System.out.println("User details:");
            user.printItem();
            System.out.println(); // line break
            System.out.println("Select an option to edit:");
            System.out.println("1. Name");
            System.out.println("2. Gender");
            System.out.println("3. Password");
            System.out.println("4. Age");
            System.out.println("5. Cancel");

            int choice = getIntInput(5);
            boolean success;
            switch (choice) {
                case 1:
                    String newName = getValidatedString("Enter new name: ");
                    success = staffManager.editName(id, newName);
                    if (success)
                        displaySuccess("Name updated successfully.");
                    else
                        displayError("Failed to update name.");
                    break;
                case 2:
                    Gender gender = getGender();
                    if (gender != null) {
                        success = staffManager.editGender(id, gender);
                        if (success)
                            displaySuccess("Gender updated successfully.");
                        else
                            displayError("Failed to update gender.");
                    } else {
                        displayError("Invalid gender.");
                    }
                    break;
                case 3:
                    String newPassword = getValidatedString("Enter new password: ");
                    success = staffManager.editPassword(id, newPassword);
                    if (success)
                        displaySuccess("Password updated successfully.");
                    else
                        displayError("Failed to update password.");
                    break;
                case 4:
                    int newAge = getValidatedInt("Enter new age: ");
                    if (newAge > 0) {
                        success = staffManager.editAge(id, newAge);
                        if (success)
                            displaySuccess("Age updated successfully.");
                        else
                            displayError("Failed to update age.");
                    } else {
                        displayError("Invalid age.");
                    }
                    break;
                case 5:
                    quit = true;
                    break;
                default:
                    displayError("Invalid choice.");
                    break;
            }
        }
    }

    /**
     * Displays staff details based on selected filters.
     */
    private void handleDisplayStaff() {
        boolean backToFilter = false;
        while (!backToFilter) {
            ClearOutput.clearOutput();
            System.out.println("Select Display Filter: ");
            System.out.println("1. Display All");
            System.out.println("2. Filter by Gender");
            System.out.println("3. Filter by Role");
            System.out.println("4. Filter by Age");
            System.out.println("5. Back");

            int choice = getIntInput(5);

            ClearOutput.clearOutput(); // clear after making a choice
            switch (choice) {
                case 1:
                    staffManager.displayAllStaff();
                    break;
                case 2:
                    filterByGender();
                    break;
                case 3:
                    filterByRole();
                    break;
                case 4:
                    filterByAge();
                    break;
                case 5:
                    backToFilter = true;
                    return;
                default:
                    displayError("Invalid filter option selected.");
            }
            pauseAndView();
        }
    }

    /**
     * Filters staff members by gender.
     */
    private void filterByGender() {
        Gender gender = getGender();
        if (gender != null)
            staffManager.displayStaffs(FilterParam.GENDER, gender.toString());
        else
            displayError("Invalid gender selection.");
    }

    /**
     * Filters staff members by role.
     */
    private void filterByRole() {
        Role role = getRole();
        if (role != null)
            staffManager.displayStaffs(FilterParam.ROLE, role.toString());
        else
            displayError("Invalid role selection.");
    }

    /**
     * Filters staff members by age range.
     */
    private void filterByAge() {
        System.out.println("Enter age filter lower bound:");
        int lowerBound = getValidatedInt("Enter lower bound: ");
        System.out.println("Enter age filter upper bound:");
        int upperBound = getValidatedInt("Enter upper bound: ");

        if (lowerBound <= upperBound) {
            staffManager.displayStaffs(FilterParam.AGE, lowerBound, upperBound);
        } else {
            displayError("Invalid age range.");
        }
    }

    /**
     * Prompts the user to select a staff role.
     * @return the selected role, or null if the selection is invalid.
     */
    private Role getRole() {
        System.out.println("Select Role: ");
        System.out.println("1. Doctor");
        System.out.println("2. Pharmacist");

        int choice = getIntInput(3);
        return switch (choice) {
            case 1 -> Role.DOC;
            case 2 -> Role.PHA;
            default -> {
                yield null;
            }
        };
    }

    /**
     * Prompts the user to select a gender.
     * @return the selected gender, or null if the selection is invalid.
     */
    private Gender getGender() {
        System.out.println("Select Gender: ");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. NA");

        int choice = getIntInput(4);
        return switch (choice) {
            case 1 -> Gender.MALE;
            case 2 -> Gender.FEMALE;
            case 3 -> Gender.NA;
            default -> {
                displayError("Invalid gender selection.");
                yield null;
            }
        };
    }
}
