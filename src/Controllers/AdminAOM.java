package Controllers;

import Databases.AppointmentOutcomeDatabase;

public class AdminAOM extends BaseAppointmentOutcomeManager {

    public AdminAOM(AppointmentOutcomeDatabase database) {
        super(database);
    }

    @Override
    public void printAllOutcomes() {
        database.printItems(); // Admin can view all outcomes
    }
}
