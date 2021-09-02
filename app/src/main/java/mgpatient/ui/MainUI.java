package mgpatient.ui;

import mgpatient.domain.User;
import mgpatient.logic.MGPatientAuth;

import java.util.Scanner;

public class MainUI {

    private final Scanner scanner = new Scanner(System.in);
    private final MGPatientAuth MGPatientAuth = new MGPatientAuth();
    private User signedInUser;

    public void start() {
        System.out.println("\nMGPatient - Authentication");
        while (true) {
            System.out.println("\n1. Sign in");
            System.out.println("2. Create new user");
            System.out.println("0. Exit program");
            System.out.print(">");
            int option = this.scanner.nextInt();
            if (option == 0) {
                System.out.println("\nExiting MGPatient..");
                return;
            } else if (option == 1) {
                signInUI();
                if (this.signedInUser != null) {
                    break;
                }
            } else if (option == 2) {
                createUserUI();
            } else {
                System.out.println("\nThere's no such option: " + option);
            }
        }
        new LoggedInUI(this.signedInUser).loggedInUI();
    }

    private void signInUI() {
        System.out.println("\nFields with \"*\" are required");
        System.out.print("\n*E-mail: ");
        String email = this.scanner.next();
        System.out.print("*Password: ");
        String password = this.scanner.next();
        this.signedInUser = this.MGPatientAuth.signIn(email, password);
        if (this.signedInUser != null) {
            System.out.println("\nSuccessfully signed in.");
            return;
        } else {
            System.out.println("Make sure that required fields (*) are not empty.");
        }
    }

    private void createUserUI() {
        System.out.println("\nFields with \"*\" are required");
        System.out.print("\n*Name: ");
        String name = this.scanner.next();
        System.out.print("*Surname: ");
        String surname = this.scanner.next();
        System.out.print("*Phone number (with country code, no spaces): ");
        String phoneNumber = this.scanner.next();
        System.out.print("*E-mail: ");
        String email = this.scanner.next();
        System.out.print("*Password: ");
        String password = this.scanner.next();
        if (this.MGPatientAuth.createUser(name, surname, phoneNumber, email, password)) {
            System.out.println("\nNew user has been created. Now you can sign in.");
        } else {
            System.out.println("Make sure that required fields (*) are not empty.");
        }
    }
}
