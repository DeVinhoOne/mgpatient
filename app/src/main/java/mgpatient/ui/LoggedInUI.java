package mgpatient.ui;

import mgpatient.domain.User;
import mgpatient.logic.MGPatient;

import java.util.Scanner;

public class LoggedInUI {

    private final Scanner scanner = new Scanner(System.in);
    private final mgpatient.logic.MGPatient MGPatient = new MGPatient();
    private User signedInUser;

    public LoggedInUI(User user) {
        this.signedInUser = user;
    }

    void loggedInUI() {
        System.out.println("\n====================================");
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
            } else if (option == 3) {
                createVisitUI();
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
        System.out.print("*Phone number (with country code, no spaces): ");
        String phoneNumber = this.scanner.next();
        if (this.MGPatient.createPatient(name, surname, phoneNumber)) {
            System.out.println("\nNew patient has been added!");
        } else {
            System.out.println("Make sure that required fields (*) are not empty.");
        }
    }

    private void addDoctorUI() {
        System.out.println("\nFields with \"*\" are required");
        System.out.print("\n*Name: ");
        String name = this.scanner.next();
        System.out.print("*Surname: ");
        String surname = this.scanner.next();
        System.out.print("*Phone number (with country code, no spaces): ");
        String phoneNumber = this.scanner.next();
        System.out.print("*Specialization: ");
        String specialization = this.scanner.next();
        System.out.print("*E-mail: ");
        String email = this.scanner.next();
        if (this.MGPatient.createDoctor(name, surname, phoneNumber, specialization, email)) {
            System.out.println("\nNew doctor has been added.");
        } else {
            System.out.println("Make sure that required fields (*) are not empty.");
        }
    }

    private void createVisitUI() {
        System.out.println("\nFields with \"*\" are required");
        System.out.print("\n*Patient name: ");
        String pName = this.scanner.next();
        System.out.print("*Patient surname: ");
        String pSurname = this.scanner.next();
        System.out.print("\n*Doctor name: ");
        String dName = this.scanner.next();
        System.out.print("*Doctor surname: ");
        String dSurname = this.scanner.next();
        System.out.print("Description: ");
        String description = this.scanner.next();
        System.out.print("Urgent (yes/no): ");
        boolean urgent = this.scanner.next().equalsIgnoreCase("yes");
        System.out.print("Room: ");
        int room = this.scanner.nextInt();
        this.MGPatient.createNewVisit(pName, pSurname, dName, dSurname, description, urgent, room);
    }
}
