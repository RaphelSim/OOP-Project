package Databases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import Common.BloodType;
import Common.Database;
import Common.DatabaseItems;
import Common.Gender;
import Common.ListConverter;
import DatabaseItems.MedicalRecord;

public class MedicalRecordDatabase extends Database {

    public MedicalRecordDatabase() {
        setcsvPath("Database/MedicalRecords.csv");
        extractFromCSV();
    }

    public MedicalRecordDatabase(String csvpath) {
        setcsvPath(csvpath);
        extractFromCSV();
    }

    // id,name,dob,gender,email,phone,bloodtype,diagnoses,treatments
    @Override
    public void extractFromCSV() {
        // System.out.println(System.getProperty("user.dir")); uncomment this line to
        // print your directory
        try (Scanner scanner = new Scanner(new File(csvPath))) {
            // Skip the header
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                String id = values[0];
                String name = values[1];
                String dob = values[2];
                Gender gender = Gender.fromString(values[3]);
                String email = values[4];
                String phone = values[5];
                BloodType bloodType = BloodType.fromString(values[6]);
                List<String> diagnoses = ListConverter.stringToList(values[7]);
                List<String> treatments = ListConverter.stringToList(values[8]);
                // Add a new medicalRecord to the records list
                records.add(new MedicalRecord(id, name, dob, gender, email, phone, bloodType, diagnoses, treatments));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void storeToCSV() {
        try (FileWriter writer = new FileWriter(csvPath)) {
            // Write header line
            writer.write("id,name,dob,gender,email,phone,bloodtype,diagnoses,treatments\n");

            // Write medicalRecord details
            for (DatabaseItems record : records) {
                MedicalRecord medicalRecord = (MedicalRecord) record;
                writer.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s\n",
                        medicalRecord.getId(),
                        medicalRecord.getName(),
                        medicalRecord.getDob(),
                        medicalRecord.getGender().toString(),
                        medicalRecord.getEmail(),
                        medicalRecord.getPhone(),
                        medicalRecord.getBloodType().toString(),
                        ListConverter.listToString(medicalRecord.getDiagnoses()),
                        ListConverter.listToString(medicalRecord.getTreatments())

                ));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addItem(MedicalRecord record) {
        records.add(record);
    }

    public boolean removeItem(String userid) {
        boolean accountRemoved = records.removeIf(user -> {
            MedicalRecord account = (MedicalRecord) user;
            return account.getId().equals(userid);
        });

        if (accountRemoved) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void printItems() {
        // Print the users
        System.out.println("Medical Record Database: ");
        System.out.println("--------------------------");
        for (DatabaseItems record : records) {
            if (record instanceof MedicalRecord) { // Ensure it's a MedicalRecord
                MedicalRecord medicalRecord = (MedicalRecord) record;
                System.out.println(); // Print a new line for better readability
                System.out.println("ID: " + medicalRecord.getId());
                System.out.println("Name: " + medicalRecord.getName());
                System.out.println("Date of Birth: " + medicalRecord.getDob());
                System.out.println("Gender: " + medicalRecord.getGender());
                System.out.println("Email: " + medicalRecord.getEmail());
                System.out.println("Phone: " + medicalRecord.getPhone());
                System.out.println("Blood Type: " + medicalRecord.getBloodType());
                System.out.println("Diagnoses: " + String.join(", ", medicalRecord.getDiagnoses()));
                System.out.println("Treatments: " + String.join(", ", medicalRecord.getTreatments()));
                System.out.println(); // Print a new line for better readability
            }
        }
    }
}
