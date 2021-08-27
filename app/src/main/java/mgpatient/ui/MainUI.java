package mgpatient.ui;

import mgpatient.logic.MGPatient;
import mgpatient.util.Validator;

import java.util.Scanner;

public class MainUI {

    private final Scanner scanner = new Scanner(System.in);
    private final MGPatient MGPatient = new MGPatient();

    public void start() {
        System.out.println("\nMGPatient - Add patients and visits to database");
        while (true) {
            System.out.println("\n1. Add new patient");
            System.out.println("2. Add new doctor");
            System.out.println("3. Create new visit");
            System.out.println("0. Exit program");
            System.out.print(">");
            int option = this.scanner.nextInt();
            if (option == 0) {
                System.out.println("\nExiting MGPatient..");
                return;
            } else if (option == 1) {
                addPatientUI();
            } else if (option == 2) {
                addDoctorUI();
            } else {
                System.out.println("\nThere's no such option: " + option);
            }
        }
    }

    private void addPatientUI() {
        System.out.println("\nFields with \"*\" are required");
        System.out.print("\n*Name: ");
        String name = this.scanner.next();
        System.out.print("*Surname: ");
        String surname = this.scanner.next();
        System.out.print("*Phone number: ");
        String phoneNumber = this.scanner.next();
        //Validate input
        if (Validator.validateStringNotNullInput(new String[] {name, surname, phoneNumber})) {
            this.MGPatient.createPatient(name, surname, phoneNumber);
            System.out.println("\nNew patient has been added!");
        } else {
            System.out.println("\nInvalid input!");
            System.out.println("None of the fields cannot be empty.");
        }
    }

    private void addDoctorUI() {
        System.out.println("\nFields with \"*\" are required");
        System.out.print("\n*Name: ");
        String name = this.scanner.next();
        System.out.print("*Surname: ");
        String surname = this.scanner.next();
        System.out.print("*Phone number: ");
        String phoneNumber = this.scanner.next();
        System.out.print("Specialization: ");
        String specialization = this.scanner.next();
        System.out.print("E-mail: ");
        String email = this.scanner.next();
        if (Validator.validateStringNotNullInput(new String[] {name, surname, phoneNumber})) {
            this.MGPatient.createDoctor(name, surname, phoneNumber, specialization,email);
            System.out.println("\nNew doctor has been added.");
        } else {
            System.out.println("\nInvalid input!");
            System.out.println("Make sure that required fields (*) are not empty.");
        }
    }
}
