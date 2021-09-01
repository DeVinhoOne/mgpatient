package mgpatient.util;

import mgpatient.domain.Doctor;
import mgpatient.domain.Patient;
import org.apache.commons.validator.routines.EmailValidator;

public class Validator {

    public static boolean valCreatePatient(Patient patient) {
        // Validate not null input
        if (!validateNotNullInput(new String[] {patient.getName(), patient.getSurname(), patient.getPhoneNumber()})) {
            return false;
        }
        if (!validatePhoneNumber(patient.getPhoneNumber())) {
            System.out.println("\nInvalid phone number: " + patient.getPhoneNumber());
            return false;
        }
        if (!validateName(patient.getName()) || !validateName(patient.getSurname())) {
            System.out.println("\nInvalid name/surname: " + patient.getName() + " " + patient.getSurname());
            return false;
        }
        return true;
    }

    public static boolean valCreateDoctor(Doctor doctor) {
        // Validate not null input
        if (!validateNotNullInput(new String[] {doctor.getName(), doctor.getSurname(), doctor.getPhoneNumber(), doctor.getSpecialization(), doctor.getEmail()})) {
            return false;
        }
        if (!validatePhoneNumber(doctor.getPhoneNumber())) {
            System.out.println("\nInvalid phone number." + doctor.getPhoneNumber());
            return false;
        }
        if (!validateName(doctor.getName()) || !validateName(doctor.getSurname())) {
            System.out.println("\nInvalid name/surname: " + doctor.getName() + " " + doctor.getSurname());
            return false;
        }
        if (!validateSpecialization(doctor.getSpecialization())) {
            System.out.println("\nInvalid specialization: " + doctor.getSpecialization());
            return false;
        }
        if (!validateEmail(doctor.getEmail())){
            System.out.println("\nInvalid e-mail address: " + doctor.getEmail());
            return false;
        }
        return true;
    }

    private static boolean validateNotNullInput(String[] args) {
        for (String arg : args) {
            if (arg == null || arg.isBlank() || arg.equalsIgnoreCase("null")) {
                return false;
            }
        }
        return true;
    }

    private static boolean validateName(String name) {
        //accepted format: David david Thomas thomas
        if (name.length() < 2) {
            return false;
        }
        return name.matches("[A-Z]?[a-z]+");
    }

    private static boolean validatePhoneNumber(String phoneNumber) {
        //accepted format: +48123456789
        return phoneNumber.matches("\\+\\d{11}");
    }

    private static boolean validateSpecialization(String specialization) {
        //accepted format: Dentist dentist
        if (specialization.length() < 2) {
            return false;
        }
        return specialization.matches("[A-Z]?[a-z]+");
    }

    private static boolean validateEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
