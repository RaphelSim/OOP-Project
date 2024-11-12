package UI;

import AppointmentSystem.Doctor;

public class DoctorUI {

    void setPersonalSchedule(Doctor d) {
        System.out.println("Welcome Dr." + d.getName());
		System.out.println("Please setup your Personal Schedule Availability:");
    }

}
